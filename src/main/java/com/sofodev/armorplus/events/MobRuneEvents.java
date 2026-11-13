package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.api.EffectEntry;
import com.sofodev.armorplus.api.EffectScopes;
import com.sofodev.armorplus.api.event.EffectApplyEvent;
import com.sofodev.armorplus.api.event.RuneApplyEvent;
import com.sofodev.armorplus.api.event.SetEffectApplyEvent;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

import static com.sofodev.armorplus.registry.item.extra.Buff.FLIGHT;

/**
 * Armor effects of mobs: the effects of the ArmorPlus sets and armor runes they wear, and Flight, which lets them chase
 * their target through the air. Players are handled by {@link RuneEvents} and {@link ModGlobalEvents}.
 * <p>
 * Built to stay cheap on servers: a mob is only looked at when its armor changes, only the mobs that wear armor with
 * effects are kept (weak references, so unloaded mobs drop out), their effects are refreshed once a second, and only
 * mobs with Flight are steered every tick.
 */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public final class MobRuneEvents {

    private static final int EFFECT_INTERVAL_TICKS = 20;
    /** Refreshing below two intervals means an effect never runs out between checks. */
    private static final int EFFECT_REFRESH_TICKS = EFFECT_INTERVAL_TICKS * 2;
    /** Marks a mob that ArmorPlus took gravity from, so it's given back even after a restart. */
    private static final String FLIGHT_TAG = "ArmorPlusMobFlight";
    /** A target this much higher than the mob makes it take off. */
    private static final double TAKE_OFF_HEIGHT = 2.0;
    /** A flying mob keeps flying until its target is this far below it, then glides down. */
    private static final double LAND_HEIGHT = -1.0;
    /** A mob that can't reach its target on foot only takes off if it's further away than this. */
    private static final double UNREACHABLE_TAKE_OFF_DISTANCE_SQR = 3.0 * 3.0;
    /** Close enough to fight, the mob hovers instead of pushing into its target. */
    private static final double HOVER_DISTANCE_SQR = 1.5 * 1.5;
    /** How quickly a flying mob turns towards where it wants to go, per tick. */
    private static final double STEERING = 0.2;
    /** Fastest fall of a mob with Flight that isn't flying: it glides down without fall damage. */
    private static final double GLIDE_FALL_SPEED = -0.2;

    /** Mobs wearing armor with effects. */
    private static final Set<Mob> GEARED = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap<>()));
    /** Mobs with Flight, a subset of {@link #GEARED}. */
    private static final Set<Mob> FLYERS = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap<>()));

    private MobRuneEvents() {
    }

    /* ---------------------------------------- Tracking ---------------------------------------- */

    /** Also fires on a mob's first tick for the gear it spawned or was loaded with. */
    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Mob mob) || mob.level().isClientSide()) return;
        if (!Arrays.asList(Runes.ARMOR_SLOTS).contains(event.getSlot())) return;
        EffectScopes.ARMOR.invalidate(mob);

        boolean geared = ArmorPlusConfig.mobsUseRuneEffects.get() && !listed(mob, ArmorPlusConfig.mobEffectBlacklist.get())
                && !EffectScopes.ARMOR.active(mob).isEmpty();
        boolean flies = geared && ArmorPlusConfig.mobFlight.get() && EffectScopes.ARMOR.has(mob, FLIGHT) && !listed(mob, ArmorPlusConfig.mobFlightBlacklist.get());
        if (geared) {
            GEARED.add(mob);
        } else {
            GEARED.remove(mob);
            // most mobs never wear ArmorPlus armor, don't keep an empty cache entry for each of them
            EffectScopes.ARMOR.invalidate(mob);
        }
        if (flies) {
            FLYERS.add(mob);
        } else {
            FLYERS.remove(mob);
            land(mob);
        }
    }

    private static boolean listed(Mob mob, List<? extends String> ids) {
        return !ids.isEmpty() && ids.contains(BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType()).toString());
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        if (!FLYERS.isEmpty()) {
            for (Mob mob : snapshot(FLYERS)) {
                if (mob.level() == level) tickFlight(mob);
            }
        }
        if (GEARED.isEmpty() || level.getGameTime() % EFFECT_INTERVAL_TICKS != 0) return;
        boolean enabled = ArmorPlusConfig.mobsUseRuneEffects.get();
        for (Mob mob : snapshot(GEARED)) {
            if (mob.level() != level) continue;
            if (!enabled || mob.isRemoved() || !mob.isAlive()) {
                GEARED.remove(mob);
                continue;
            }
            applyEffects(mob);
        }
    }

    private static List<Mob> snapshot(Set<Mob> mobs) {
        synchronized (mobs) {
            return List.copyOf(mobs);
        }
    }

    /* ---------------------------------------- Effects ---------------------------------------- */

    /** Mob effects only: the other abilities and instant effects (Instant Health would hurt undead mobs) stay with players. */
    private static void applyEffects(Mob mob) {
        for (EffectEntry entry : EffectScopes.ARMOR.activeEntries(mob)) {
            BuffInstance instance = entry.buff();
            IBuff buff = instance.getBuff();
            Holder<MobEffect> effect = buff.isEffect() ? buff.getEffect() : null;
            if (effect == null || effect.value().isInstantenous() || !needsRefresh(mob, effect, instance)) continue;
            EffectApplyEvent applyEvent = entry.fromRune()
                    ? new RuneApplyEvent(mob, instance, EffectApplyEvent.Context.WORN, ItemStack.EMPTY, null)
                    : new SetEffectApplyEvent(mob, instance, EffectApplyEvent.Context.WORN, ItemStack.EMPTY, null, entry.source());
            if (NeoForge.EVENT_BUS.post(applyEvent).isCanceled()) continue;
            mob.addEffect(new MobEffectInstance(effect, Math.max(instance.getDurationTicks(), EFFECT_REFRESH_TICKS * 2), instance.getAmplifier(), false, false));
        }
    }

    private static boolean needsRefresh(Mob mob, Holder<MobEffect> effect, BuffInstance instance) {
        MobEffectInstance existing = mob.getEffect(effect);
        if (existing == null || existing.getAmplifier() < instance.getAmplifier()) return true;
        return !existing.isInfiniteDuration() && existing.getDuration() <= EFFECT_REFRESH_TICKS;
    }

    /* ---------------------------------------- Flight ---------------------------------------- */

    private static void tickFlight(Mob mob) {
        if (mob.isRemoved() || !mob.isAlive() || !ArmorPlusConfig.mobFlight.get() || !ArmorPlusConfig.mobsUseRuneEffects.get()) {
            FLYERS.remove(mob);
            land(mob);
            return;
        }
        LivingEntity target = mob.getTarget();
        if (target != null && target.isAlive() && target.level() == mob.level() && wantsToFly(mob, target)) {
            fly(mob, target);
        } else {
            land(mob);
            glide(mob);
        }
    }

    /** @return true if the target is high above, or the mob can't find a way to it on foot */
    private static boolean wantsToFly(Mob mob, LivingEntity target) {
        double height = target.getY() - mob.getY();
        if (isFlying(mob)) return height > LAND_HEIGHT;
        if (height > TAKE_OFF_HEIGHT) return true;
        Path path = mob.getNavigation().getPath();
        return path != null && !path.canReach() && mob.distanceToSqr(target) > UNREACHABLE_TAKE_OFF_DISTANCE_SQR;
    }

    private static void fly(Mob mob, LivingEntity target) {
        if (!isFlying(mob)) {
            mob.setNoGravity(true);
            mob.getPersistentData().putBoolean(FLIGHT_TAG, true);
        }
        Vec3 toTarget = target.position().subtract(mob.position());
        Vec3 wanted = toTarget.lengthSqr() <= HOVER_DISTANCE_SQR ? Vec3.ZERO : toTarget.normalize().scale(ArmorPlusConfig.mobFlightSpeed.get());
        mob.setDeltaMovement(mob.getDeltaMovement().lerp(wanted, STEERING));
        mob.getLookControl().setLookAt(target);
        mob.resetFallDistance();
    }

    private static boolean isFlying(Mob mob) {
        return mob.getPersistentData().getBoolean(FLIGHT_TAG).orElse(false);
    }

    /** Gives back the gravity ArmorPlus took, if it did. */
    private static void land(Mob mob) {
        if (!isFlying(mob)) return;
        mob.getPersistentData().remove(FLIGHT_TAG);
        mob.setNoGravity(false);
    }

    private static void glide(Mob mob) {
        if (mob.onGround()) return;
        Vec3 motion = mob.getDeltaMovement();
        if (motion.y < GLIDE_FALL_SPEED) {
            mob.setDeltaMovement(motion.x, GLIDE_FALL_SPEED, motion.z);
        }
        mob.resetFallDistance();
    }

    @SubscribeEvent
    public static void onFall(LivingFallEvent event) {
        if (event.getEntity() instanceof Mob mob && !FLYERS.isEmpty() && FLYERS.contains(mob)) event.setCanceled(true);
    }
}
