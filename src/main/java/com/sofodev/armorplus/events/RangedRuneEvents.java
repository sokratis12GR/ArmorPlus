package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.api.RuneEffectType;
import com.sofodev.armorplus.api.RuneKind;
import com.sofodev.armorplus.api.event.EffectApplyEvent;
import com.sofodev.armorplus.api.event.RuneApplyEvent;
import com.sofodev.armorplus.api.event.SetEffectApplyEvent;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.RangedBuff;
import com.sofodev.armorplus.registry.item.tool.APBowItem;
import com.sofodev.armorplus.registry.item.tool.ArrowRunes;
import com.sofodev.armorplus.registry.item.tool.RangedEffects;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.function.Predicate;

/**
 * Ranged runes: when an arrow enters the world its shooter's runes are stored on it ({@link ArrowRunes}), and the hits go
 * through the damage events, so they work for vanilla, modded and ArmorPlus arrows alike and stack with the arrow's own effect.
 */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public final class RangedRuneEvents {

    private static final int MAX_PIERCE_LEVEL = 127;
    private static final double RICOCHET_RANGE = 8.0;
    private static final double SEEKER_RANGE = 16.0;
    /** A locked target can get this far away before the arrow lets go of it. */
    private static final double SEEKER_KEEP_RANGE = 24.0;
    /** Cosine of the angle ahead of the arrow that Seeker looks in (75 degrees). */
    private static final double SEEKER_CONE = 0.26;
    private static final double SEEKER_TURN = 0.3;
    /** How much of the gravity a locked arrow ignores, it should still arc a little. */
    private static final double SEEKER_GRAVITY_OFFSET = 0.5;
    /** Ticks between the searches and line of sight checks. A full speed arrow flies about 3 blocks per tick, so this can't be long. */
    private static final int SEEKER_SCAN_INTERVAL = 3;
    private static final double SEEKER_MIN_SPEED_SQR = 0.01;
    private static final int SEEKER_MAX_AGE = 200;
    private static final int MARK_TICKS = 200;
    /** Multishot fires its arrows together, only the first is an Ender Shot and the others shouldn't complain about the cooldown. */
    private static final long ENDER_SHOT_SAME_VOLLEY_TICKS = 20;

    /** @param spent an arrow used it in this hit, which shouldn't mark the target again */
    private record Mark(UUID owner, int level, long expiresAt, boolean spent) {
    }

    private record EnderShot(long firedAt, long readyAt) {
    }

    /** The target of a Marked arrow, until the next arrow of its shooter hits it. Weak keys, so dead mobs don't stay. */
    private static final Map<LivingEntity, Mark> MARKS = Collections.synchronizedMap(new WeakHashMap<>());
    private static final Map<Player, EnderShot> ENDER_SHOTS = Collections.synchronizedMap(new WeakHashMap<>());
    private static final Map<AbstractArrow, LivingEntity> SEEKER_LOCKS = Collections.synchronizedMap(new WeakHashMap<>());
    /** The Quick Draw level of the bow being drawn, looked up once per draw. */
    private static final Map<LivingEntity, Integer> QUICK_DRAWS = Collections.synchronizedMap(new WeakHashMap<>());

    private RangedRuneEvents() {
    }

    /* ---------------------------------------- Firing ---------------------------------------- */

    @SubscribeEvent
    public static void onArrowJoin(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide() || event.loadedFromDisk()) return;
        if (!(event.getEntity() instanceof AbstractArrow arrow) || arrow.hasData(ModAttachments.ARROW_RUNES.get())) return;
        if (!(arrow.getOwner() instanceof LivingEntity shooter)) return;

        // a copy: an Ender Shot can break the real one, and new arrows need a weapon that isn't empty
        ItemStack weapon = firedFrom(arrow, shooter).copy();
        if (weapon.isEmpty()) return;
        List<RuneData> runes = RangedEffects.activeRunes(weapon, shooter);
        if (runes.isEmpty()) return;

        ArrowRunes data = new ArrowRunes(runes, arrow.position(), 0);
        data = data.withBounces(data.level(RangedBuff.RICOCHET));
        if (data.level(RangedBuff.ENDER_SHOT) > 0 && !armEnderShot(shooter)) data = data.without(RangedBuff.ENDER_SHOT);
        arrow.setData(ModAttachments.ARROW_RUNES.get(), data);

        addPiercing(arrow, data);
        saveAmmo(arrow, data, shooter);
        fireVolley(arrow, data, weapon, shooter);
        onShoot(arrow, data, weapon, shooter);
    }

    /** @return the bow or crossbow the arrow came from. An ArmorPlus arrow created without one (e.g. by another mod) uses the one in use or held */
    private static ItemStack firedFrom(AbstractArrow arrow, LivingEntity shooter) {
        ItemStack fired = arrow.getWeaponItem();
        if (fired != null && Runes.gearKind(fired) == RuneKind.RANGED) return fired;
        if (!(arrow instanceof APArrowEntity)) return ItemStack.EMPTY;
        ItemStack using = shooter.getUseItem();
        return Runes.gearKind(using) == RuneKind.RANGED ? using : RangedEffects.heldWeapon(shooter);
    }

    private static void addPiercing(AbstractArrow arrow, ArrowRunes data) {
        int level = data.level(RangedBuff.PIERCING);
        if (level > 0) arrow.setPierceLevel((byte) Math.min(MAX_PIERCE_LEVEL, arrow.getPierceLevel() + level));
    }

    /** Gives the arrow back right away; it can't be picked up as well then. Arrows that can't be picked up didn't use one. */
    private static void saveAmmo(AbstractArrow arrow, ArrowRunes data, LivingEntity shooter) {
        int level = data.level(RangedBuff.AMMO_SAVER);
        if (level <= 0 || !(shooter instanceof Player player) || player.hasInfiniteMaterials() || arrow.pickup != AbstractArrow.Pickup.ALLOWED) return;
        if (shooter.getRandom().nextDouble() >= RangedEffects.ammoSaverChance(level)) return;
        player.getInventory().placeItemBackInInventory(arrow.getPickupItemStackOrigin().copyWithCount(1));
        arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
    }

    private static void fireVolley(AbstractArrow arrow, ArrowRunes data, ItemStack weapon, LivingEntity shooter) {
        int level = data.level(RangedBuff.VOLLEY);
        if (level <= 0) return;
        ArrowRunes extraData = data.without(RangedBuff.VOLLEY).without(RangedBuff.ENDER_SHOT);
        Vec3 motion = arrow.getDeltaMovement();
        float speed = (float) motion.length();
        float spread = (float) Math.toRadians(RangedEffects.VOLLEY_SPREAD_DEGREES);
        int perSide = RangedEffects.volleyArrowsPerSide(level);
        for (int side = -perSide; side <= perSide; side++) {
            if (side == 0) continue;
            AbstractArrow extra = extraArrow(arrow, shooter, weapon);
            extra.setBaseDamage(arrow.baseDamage);
            extra.setCritArrow(arrow.isCritArrow());
            Vec3 direction = motion.yRot(side * spread);
            extra.shoot(direction.x, direction.y, direction.z, speed, 1.0F);
            extra.setData(ModAttachments.ARROW_RUNES.get(), extraData);
            addPiercing(extra, extraData);
            arrow.level().addFreshEntity(extra);
            onShoot(extra, extraData, weapon, shooter);
        }
    }

    /** @return a new arrow of the same ammo that can't be picked up, at the shooter's eyes */
    private static AbstractArrow extraArrow(AbstractArrow arrow, LivingEntity shooter, @Nullable ItemStack weapon) {
        ItemStack ammo = arrow.getPickupItemStackOrigin().copyWithCount(1);
        ArrowItem item = ammo.getItem() instanceof ArrowItem arrowItem ? arrowItem : (ArrowItem) Items.ARROW;
        AbstractArrow extra = item.createArrow(arrow.level(), ammo, shooter, weapon);
        extra.pickup = AbstractArrow.Pickup.DISALLOWED;
        extra.setRemainingFireTicks(arrow.getRemainingFireTicks());
        return extra;
    }

    private static void onShoot(AbstractArrow arrow, ArrowRunes data, ItemStack weapon, LivingEntity shooter) {
        for (RuneData rune : data.runes()) {
            BuffInstance buff = Runes.resolve(rune);
            if (buff != null && buff.getBuff() instanceof RuneEffectType type) type.onShoot(arrow, weapon, shooter);
        }
    }

    /* ---------------------------------------- Ender Shot ---------------------------------------- */

    /** @return true if the player's shot is an Ender Shot, which costs durability and starts the cooldown. Only players can have one */
    private static boolean armEnderShot(LivingEntity shooter) {
        if (!(shooter instanceof ServerPlayer player)) return false;
        long now = player.level().getGameTime();
        EnderShot last = ENDER_SHOTS.get(player);
        if (last != null && now < last.readyAt()) {
            if (now - last.firedAt() >= ENDER_SHOT_SAME_VOLLEY_TICKS) {
                int seconds = (int) Math.ceil((last.readyAt() - now) / 20.0);
                player.sendOverlayMessage(Component.translatable("armorplus.buff.ender_shot.cooldown", seconds).withStyle(ChatFormatting.RED));
            }
            return false;
        }
        ENDER_SHOTS.put(player, new EnderShot(now, now + ArmorPlusConfig.enderShotCooldown.get() * 20L));
        ItemStack held = RangedEffects.heldWeapon(player);
        if (!held.isEmpty()) {
            held.hurtAndBreak(ArmorPlusConfig.enderShotDurability.get(), player, held == player.getMainHandItem() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND);
        }
        return true;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.isCanceled() || !(event.getProjectile() instanceof AbstractArrow arrow) || arrow.level().isClientSide()) return;
        ArrowRunes data = runesOf(arrow);
        if (data == null || data.level(RangedBuff.ENDER_SHOT) <= 0) return;
        // a piercing arrow lands more than once
        arrow.setData(ModAttachments.ARROW_RUNES.get(), data.without(RangedBuff.ENDER_SHOT));
        if (arrow.getOwner() instanceof ServerPlayer player && player.isAlive() && player.level() == arrow.level()) {
            landingSpot(event.getRayTraceResult(), player).ifPresent(spot -> teleport(player, spot));
        }
    }

    private static Optional<Vec3> landingSpot(HitResult hit, ServerPlayer player) {
        Vec3 spot;
        if (hit instanceof BlockHitResult blockHit) {
            BlockPos pos = blockHit.getBlockPos().relative(blockHit.getDirection());
            spot = Vec3.atBottomCenterOf(pos);
        } else {
            spot = hit.getLocation();
        }
        if (fits(player, spot)) return Optional.of(spot);
        Vec3 above = spot.add(0.0, 1.0, 0.0);
        return fits(player, above) ? Optional.of(above) : Optional.empty();
    }

    private static boolean fits(ServerPlayer player, Vec3 spot) {
        AABB box = player.getDimensions(player.getPose()).makeBoundingBox(spot);
        return player.level().noCollision(player, box);
    }

    private static void teleport(ServerPlayer player, Vec3 spot) {
        if (EventHooks.onEnderTeleport(player, spot.x, spot.y, spot.z).isCanceled()) return;
        player.stopRiding();
        player.teleportTo(spot.x, spot.y, spot.z);
        player.resetFallDistance();
        player.level().playSound(null, spot.x, spot.y, spot.z, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    /* ---------------------------------------- Hits ---------------------------------------- */

    /** @return the runes of the arrow that caused the damage, or null */
    private static @Nullable ArrowRunes runesOf(Entity arrow) {
        return arrow.hasData(ModAttachments.ARROW_RUNES.get()) ? arrow.getData(ModAttachments.ARROW_RUNES.get()) : null;
    }

    @SubscribeEvent
    public static void onDamage(LivingDamageEvent.Pre event) {
        DamageSource source = event.getSource();
        if (!(source.getDirectEntity() instanceof AbstractArrow arrow) || arrow.level().isClientSide()) return;
        LivingEntity target = event.getEntity();
        double multiplier = consumeMark(target, arrow);
        ArrowRunes data = runesOf(arrow);
        if (data != null) {
            int level = data.level(RangedBuff.LONG_SHOT);
            if (level > 0) multiplier *= RangedEffects.longShotMultiplier(level, data.origin().distanceTo(target.position()));
        }
        if (multiplier != 1.0) event.setNewDamage((float) (event.getNewDamage() * multiplier));
    }

    /** @return the damage multiplier of the target's mark, if the mark is from this arrow's shooter */
    private static double consumeMark(LivingEntity target, AbstractArrow arrow) {
        if (MARKS.isEmpty()) return 1.0;
        Mark mark = MARKS.get(target);
        Entity owner = arrow.getOwner();
        if (mark == null || owner == null || !mark.owner().equals(owner.getUUID())) return 1.0;
        if (mark.spent()) return 1.0;
        if (target.level().getGameTime() >= mark.expiresAt()) {
            MARKS.remove(target);
            return 1.0;
        }
        MARKS.put(target, new Mark(mark.owner(), mark.level(), mark.expiresAt(), true));
        // the glow of a mark has no particles, unlike Glowing from other sources
        MobEffectInstance glow = target.getEffect(MobEffects.GLOWING);
        if (glow != null && !glow.isVisible()) target.removeEffect(MobEffects.GLOWING);
        return RangedEffects.markedMultiplier(mark.level());
    }

    /** @return true if this hit used up the target's mark, which is then gone */
    private static boolean clearSpentMark(LivingEntity target, AbstractArrow arrow) {
        if (MARKS.isEmpty()) return false;
        Mark mark = MARKS.get(target);
        Entity owner = arrow.getOwner();
        if (mark == null || !mark.spent() || owner == null || !mark.owner().equals(owner.getUUID())) return false;
        MARKS.remove(target);
        return target.level().getGameTime() < mark.expiresAt();
    }

    @SubscribeEvent
    public static void onHit(LivingDamageEvent.Post event) {
        if (!(event.getSource().getDirectEntity() instanceof AbstractArrow arrow) || arrow.level().isClientSide()) return;
        LivingEntity target = event.getEntity();
        boolean markUsed = clearSpentMark(target, arrow);
        ArrowRunes data = runesOf(arrow);
        if (data == null || !(arrow.getOwner() instanceof LivingEntity shooter)) return;

        ItemStack weapon = arrow.getWeaponItem() != null ? arrow.getWeaponItem() : ItemStack.EMPTY;
        for (RuneData rune : data.runes()) {
            BuffInstance buff = Runes.resolve(rune);
            if (buff == null || buff.getBuff() instanceof RangedBuff) continue;
            if (!NeoForge.EVENT_BUS.post(applyEvent(rune, buff, weapon, target, shooter)).isCanceled()) buff.hitEntity(weapon, target, shooter);
        }

        int marked = data.level(RangedBuff.MARKED);
        if (marked > 0 && !markUsed) {
            target.addEffect(new MobEffectInstance(MobEffects.GLOWING, MARK_TICKS, 0, false, false), shooter);
            MARKS.put(target, new Mark(shooter.getUUID(), marked, target.level().getGameTime() + MARK_TICKS, false));
        }
        if (data.level(RangedBuff.AMMO_SAVER) > 0 && shooter instanceof Player player && arrow.pickup == AbstractArrow.Pickup.ALLOWED) {
            player.getInventory().placeItemBackInInventory(arrow.getPickupItemStackOrigin().copyWithCount(1));
            arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
        }
        if (data.bounces() > 0) ricochet(arrow, data, target, shooter);
        if (data.level(RangedBuff.SEEKER) > 0) {
            // a piercing arrow flies on through its target, and shouldn't turn back to circle around it
            SEEKER_LOCKS.remove(arrow);
            arrow.setData(ModAttachments.ARROW_RUNES.get(), runesOf(arrow).without(RangedBuff.SEEKER));
        }
    }

    /** @return the event of the rune's effect: the arrow keeps a copy of its bow, so the bow's own material effects can be told from socketed runes */
    private static EffectApplyEvent applyEvent(RuneData rune, BuffInstance buff, ItemStack weapon, LivingEntity target, LivingEntity shooter) {
        if (weapon.getItem() instanceof APBowItem bow && bow.getMat().config() instanceof MaterialConfig cfg && Runes.nativeRangedRunes(weapon).contains(rune)) {
            return new SetEffectApplyEvent(shooter, buff, EffectApplyEvent.Context.HIT, weapon, target, cfg.name());
        }
        return new RuneApplyEvent(shooter, buff, EffectApplyEvent.Context.HIT, weapon, target);
    }

    /** Sends a weaker arrow to the nearest other hostile mob. Only that arrow bounces on, so piercing arrows don't multiply. */
    private static void ricochet(AbstractArrow arrow, ArrowRunes data, LivingEntity from, LivingEntity shooter) {
        arrow.setData(ModAttachments.ARROW_RUNES.get(), data.withBounces(0));
        LivingEntity next = nearestTarget(arrow, shooter, from.getBoundingBox().inflate(RICOCHET_RANGE), from.position(),
                candidate -> candidate != from, from::hasLineOfSight);
        if (next == null) return;

        Vec3 start = from.getBoundingBox().getCenter();
        Vec3 direction = next.getBoundingBox().getCenter().subtract(start).normalize();
        // outside the box of the mob it hit, which is still invulnerable and would deflect it
        Vec3 spawn = start.add(direction.scale(from.getBbWidth() / 2.0 + 0.5));

        ItemStack weapon = arrow.getWeaponItem();
        AbstractArrow bounce = extraArrow(arrow, shooter, weapon);
        bounce.setBaseDamage(arrow.baseDamage / 2.0);
        bounce.setPos(spawn);
        bounce.shoot(direction.x, direction.y, direction.z, (float) Math.max(1.0, arrow.getDeltaMovement().length()), 1.0F);
        // the effects and the mark carry over, the other abilities belong to the arrow that was fired
        ArrowRunes bounceRunes = data.filtered(rune -> {
            BuffInstance buff = Runes.resolve(rune);
            return buff != null && (!(buff.getBuff() instanceof RangedBuff ranged) || ranged == RangedBuff.MARKED);
        });
        bounce.setData(ModAttachments.ARROW_RUNES.get(), new ArrowRunes(bounceRunes.runes(), spawn, data.bounces() - 1));
        arrow.level().addFreshEntity(bounce);
    }

    /**
     * @param check a cheap check of each mob
     * @param sight the line of sight check, a raycast, so it only runs from the nearest mob up until one passes
     * @return the nearest hostile mob around the point that isn't the shooter or on their team, and passes the checks
     */
    private static @Nullable LivingEntity nearestTarget(AbstractArrow arrow, LivingEntity shooter, AABB area, Vec3 point, Predicate<LivingEntity> check, Predicate<LivingEntity> sight) {
        return arrow.level().getEntitiesOfClass(LivingEntity.class, area, candidate -> candidate instanceof Enemy && candidate.isAlive()
                        && candidate != shooter && !candidate.isAlliedTo(shooter) && check.test(candidate)).stream()
                .sorted(Comparator.comparingDouble(candidate -> candidate.distanceToSqr(point)))
                .filter(sight)
                .findFirst()
                .orElse(null);
    }

    /** {@link RuneEffectType#onKill} of the arrow's runes, once no other mod stopped the death. */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onDeath(LivingDeathEvent event) {
        if (event.isCanceled() || !(event.getSource().getDirectEntity() instanceof AbstractArrow arrow) || arrow.level().isClientSide()) return;
        ArrowRunes data = runesOf(arrow);
        if (data == null || !(arrow.getOwner() instanceof LivingEntity shooter)) return;
        ItemStack weapon = arrow.getWeaponItem() != null ? arrow.getWeaponItem() : ItemStack.EMPTY;
        for (RuneData rune : data.runes()) {
            BuffInstance buff = Runes.resolve(rune);
            if (buff != null && buff.getBuff() instanceof RuneEffectType type) type.onKill(weapon, event.getEntity(), shooter);
        }
    }

    /* ---------------------------------------- While drawing and in flight ---------------------------------------- */

    @SubscribeEvent
    public static void onUseStart(LivingEntityUseItemEvent.Start event) {
        ItemStack weapon = event.getItem();
        if (!(weapon.getItem() instanceof ProjectileWeaponItem)) return;
        LivingEntity shooter = event.getEntity();
        QUICK_DRAWS.remove(shooter);
        for (RuneData rune : RangedEffects.activeRunes(weapon, shooter)) {
            BuffInstance buff = Runes.resolve(rune);
            if (buff == null || buff.getBuff() != RangedBuff.QUICK_DRAW) continue;
            QUICK_DRAWS.put(shooter, RangedEffects.level(buff));
            return;
        }
    }

    /** Quick Draw counts extra ticks of use. Runs on both sides, so the bow's animation matches. */
    @SubscribeEvent
    public static void onUseTick(LivingEntityUseItemEvent.Tick event) {
        if (QUICK_DRAWS.isEmpty() || !(event.getItem().getItem() instanceof ProjectileWeaponItem)) return;
        LivingEntity shooter = event.getEntity();
        Integer level = QUICK_DRAWS.get(shooter);
        if (level == null) return;
        int extra = RangedEffects.quickDrawExtraTicks(level, shooter.tickCount);
        event.setDuration(Math.max(1, event.getDuration() - extra));
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow) || arrow.level().isClientSide() || arrow.tickCount > SEEKER_MAX_AGE) return;
        ArrowRunes data = runesOf(arrow);
        if (data == null || data.level(RangedBuff.SEEKER) <= 0 || !(arrow.getOwner() instanceof LivingEntity shooter)) return;
        Vec3 motion = arrow.getDeltaMovement();
        if (motion.lengthSqr() <= SEEKER_MIN_SPEED_SQR || arrow.tickCount == SEEKER_MAX_AGE) {
            SEEKER_LOCKS.remove(arrow);
            return;
        }

        Vec3 direction = motion.normalize();
        LivingEntity target = seekerTarget(arrow, shooter, direction);
        if (target == null) return;

        Vec3 toTarget = target.getBoundingBox().getCenter().subtract(arrow.position()).normalize();
        Vec3 lifted = motion.add(0.0, arrow.getGravity() * SEEKER_GRAVITY_OFFSET, 0.0);
        arrow.setDeltaMovement(lifted.normalize().lerp(toTarget, SEEKER_TURN).normalize().scale(lifted.length()));
        arrow.needsSync = true;
    }

    /** @return the arrow's locked target while it's valid and ahead, else the nearest one it can lock onto. Most ticks don't search or raycast */
    private static @Nullable LivingEntity seekerTarget(AbstractArrow arrow, LivingEntity shooter, Vec3 direction) {
        // the first check is on the arrow's first tick, before it has flown past anything close
        boolean check = arrow.tickCount % SEEKER_SCAN_INTERVAL == 1;
        Vec3 position = arrow.position();
        LivingEntity locked = SEEKER_LOCKS.get(arrow);
        if (locked != null) {
            Vec3 toLocked = locked.getBoundingBox().getCenter().subtract(position).normalize();
            if (locked.isAlive() && locked.level() == arrow.level() && locked.distanceToSqr(position) <= SEEKER_KEEP_RANGE * SEEKER_KEEP_RANGE
                    && toLocked.dot(direction) > 0.0 && (!check || locked.hasLineOfSight(arrow))) {
                return locked;
            }
            SEEKER_LOCKS.remove(arrow);
        }
        if (!check) return null;

        AABB area = arrow.getBoundingBox().inflate(SEEKER_RANGE);
        Predicate<LivingEntity> ahead = candidate -> candidate.getBoundingBox().getCenter().subtract(position).normalize().dot(direction) > SEEKER_CONE;
        Predicate<LivingEntity> inSight = candidate -> candidate.hasLineOfSight(arrow);
        // the shooter's other arrows would only bounce off a mob they just hit, so share them out if there are more targets
        Set<LivingEntity> taken = seekerTargetsOfShooter(arrow, shooter);
        LivingEntity target = nearestTarget(arrow, shooter, area, position, ahead.and(candidate -> !taken.contains(candidate)), inSight);
        if (target == null && !taken.isEmpty()) target = nearestTarget(arrow, shooter, area, position, ahead, inSight);
        if (target != null) SEEKER_LOCKS.put(arrow, target);
        return target;
    }

    /** @return what the shooter's other arrows are locked onto */
    private static Set<LivingEntity> seekerTargetsOfShooter(AbstractArrow arrow, LivingEntity shooter) {
        Set<LivingEntity> targets = new HashSet<>();
        synchronized (SEEKER_LOCKS) {
            SEEKER_LOCKS.forEach((other, target) -> {
                if (other != arrow && other.getOwner() == shooter) targets.add(target);
            });
        }
        return targets;
    }
}
