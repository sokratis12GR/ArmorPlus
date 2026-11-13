package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.api.EffectEntry;
import com.sofodev.armorplus.api.EffectScopes;
import com.sofodev.armorplus.api.RuneEffectType;
import com.sofodev.armorplus.api.event.EffectApplyEvent;
import com.sofodev.armorplus.api.event.RuneApplyEvent;
import com.sofodev.armorplus.api.event.SetEffectApplyEvent;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.ModDataComponents;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.registry.rune.RuneItem;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.GrindstoneEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * The Socket Punch (Anvil), removal (Grindstone) and the effects of socketed runes. Socketing and upgrading are in the
 * {@link com.sofodev.armorplus.registry.rune.RitualMenu}.
 * Event based abilities (flight, illumination, sculk immunity, ...) check the socketed runes in {@link ModGlobalEvents}.
 */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class RuneEvents {

    /** Effects are applied for 20 seconds and refreshed when they run low, so once a second is plenty. */
    private static final int ARMOR_EFFECT_INTERVAL_TICKS = 20;

    /**
     * Refreshing below two intervals means an effect never expires between checks, which would cut Health Boost hearts.
     * Applying Absorption again refills its shield, so that also happens with each refresh, about every 18 seconds.
     */
    private static final int EFFECT_REFRESH_TICKS = ARMOR_EFFECT_INTERVAL_TICKS * 2;

    /** Night vision flashes below 11 seconds. */
    private static final int NIGHT_VISION_REFRESH_TICKS = 220;

    private static final int SOCKET_ROLL_INTERVAL_TICKS = 20;

    /** The game time each instant effect of a player can fire again, shared by all worn and held gear. */
    private static final Map<Player, Map<MobEffect, Long>> INSTANT_READY_TIMES = new WeakHashMap<>();

    /** Cached buffs depend on the config (enabled runes, disabled buffs, ...). */
    @SubscribeEvent
    public static void onConfigReload(ModConfigEvent.Reloading event) {
        if (event.getConfig().getModId().equals(ArmorPlus.MODID)) {
            EffectScopes.clearAllCaches();
        }
    }

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack gear = event.getLeft();
        if (gear.isEmpty() || !event.getRight().is(ModItems.SOCKET_PUNCH.get()) || !Runes.canAddSocket(gear)) return;
        event.setOutput(Runes.withExtraSocket(gear));
        event.setXpCost(ArmorPlusConfig.socketPunchXpCost.get());
        event.setMaterialCost(1);
    }

    /** Equipment rolls its sockets when it's crafted... */
    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            Runes.rollSockets(event.getCrafting(), event.getEntity().getRandom());
        }
    }

    /** ...or, if it came from anywhere else (loot, mobs, trades, older worlds), once it's in a player's inventory. */
    private static void rollInventorySockets(Player player) {
        Inventory inventory = player.getInventory();
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            if (Runes.rollSockets(inventory.getItem(slot), player.getRandom())) inventory.setChanged();
        }
    }

    /** @return the single input of the grindstone if it has runes socketed, otherwise null */
    private static @Nullable ItemStack socketedInput(GrindstoneEvent event) {
        ItemStack top = event.getTopItem();
        ItemStack bottom = event.getBottomItem();
        ItemStack gear = bottom.isEmpty() ? top : top.isEmpty() ? bottom : ItemStack.EMPTY;
        return gear.isEmpty() || Runes.sockets(gear).isEmpty() ? null : gear;
    }

    /** Grinding equipment with runes removes the runes first; its enchantments stay until it's ground again. */
    @SubscribeEvent
    public static void onGrindstonePlace(GrindstoneEvent.OnPlaceItem event) {
        ItemStack gear = socketedInput(event);
        if (gear == null) return;

        ItemStack result = gear.copy();
        result.remove(ModDataComponents.SOCKETS.get());
        event.setOutput(result);
        event.setXp(0);
    }

    @SubscribeEvent
    public static void onGrindstoneTake(GrindstoneEvent.OnTakeItem event) {
        ItemStack gear = socketedInput(event);
        Player player = event.getPlayer();
        if (gear == null || player.level().isClientSide() || !ArmorPlusConfig.returnRunesOnRemoval.get()) return;

        for (RuneData rune : Runes.sockets(gear)) {
            player.getInventory().placeItemBackInInventory(RuneItem.create(rune.kind(), rune.entry()));
        }
    }

    /**
     * Applies the armor effects of the worn sets and socketed runes (see {@link ArmorEffects}) and the tool effects of the
     * held tool (see {@link ToolEffects}) once a second: piece requirements and the player's own toggles are already taken
     * into account there.
     */
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (!(player.level() instanceof ServerLevel level)) return;
        if (player.tickCount % SOCKET_ROLL_INTERVAL_TICKS == 0) rollInventorySockets(player);
        if (player.tickCount % ARMOR_EFFECT_INTERVAL_TICKS != 0) return;

        applyEffects(player, level, false);
    }

    /** @param missingOnly only apply the regular mob effects that need it, so the once a second hooks and instant effects keep their timing */
    private static void applyEffects(Player player, ServerLevel level, boolean missingOnly) {
        for (EffectEntry entry : EffectScopes.ARMOR.activeEntries(player)) {
            if (missingOnly && !isMissing(player, entry.buff())) continue;
            EffectApplyEvent applyEvent = entry.fromRune()
                    ? new RuneApplyEvent(player, entry.buff(), EffectApplyEvent.Context.WORN, ItemStack.EMPTY, null)
                    : new SetEffectApplyEvent(player, entry.buff(), EffectApplyEvent.Context.WORN, ItemStack.EMPTY, null, entry.source());
            if (!NeoForge.EVENT_BUS.post(applyEvent).isCanceled()) apply(player, level, entry.buff(), false);
        }
        ItemStack tool = player.getMainHandItem();
        for (BuffInstance instance : ToolEffects.held(player)) {
            if (missingOnly && !isMissing(player, instance)) continue;
            if (!NeoForge.EVENT_BUS.post(new RuneApplyEvent(player, instance, EffectApplyEvent.Context.HELD, tool, null)).isCanceled()) {
                apply(player, level, instance, true);
            }
        }
    }

    /**
     * Brings the player's effects up to date right away after the equipment or toggles changed: removes the effects that
     * nothing gives anymore and applies the new ones. Call it after invalidating the caches.
     *
     * @param previous the armor and held tool effects the player had before the change
     */
    public static void syncEffects(Player player, Collection<BuffInstance> previous) {
        if (!(player.level() instanceof ServerLevel level)) return;
        for (BuffInstance instance : previous) removeIfNotGiven(player, instance);
        applyEffects(player, level, true);
    }

    /** Removes the effect if it's one ArmorPlus applied (hidden, same level) and no source gives that level anymore, so potions aren't touched. */
    private static void removeIfNotGiven(Player player, BuffInstance instance) {
        IBuff buff = instance.getBuff();
        Holder<MobEffect> effect = buff.isEffect() ? buff.getEffect() : null;
        MobEffectInstance existing = effect == null ? null : player.getEffect(effect);
        if (existing == null || existing.isVisible() || existing.getAmplifier() != instance.getAmplifier()) return;
        if (gives(EffectScopes.ARMOR.active(player), effect, instance.getAmplifier()) || gives(ToolEffects.held(player), effect, instance.getAmplifier())) return;
        player.removeEffect(effect);
    }

    private static boolean gives(List<BuffInstance> instances, Holder<MobEffect> effect, int amplifier) {
        for (BuffInstance instance : instances) {
            if (instance.getBuff().isEffect() && effect.equals(instance.getBuff().getEffect()) && instance.getAmplifier() >= amplifier) return true;
        }
        return false;
    }

    private static boolean isMissing(Player player, BuffInstance instance) {
        IBuff buff = instance.getBuff();
        if (buff instanceof RuneEffectType || !buff.isEffect()) return false;
        Holder<MobEffect> effect = buff.getEffect();
        return effect != null && !effect.value().isInstantenous() && needsRefresh(player, effect, instance);
    }

    /** @return true if the player doesn't have the effect, has a weaker level (e.g. from a potion) or it's running low */
    private static boolean needsRefresh(Player player, Holder<MobEffect> effect, BuffInstance instance) {
        MobEffectInstance existing = player.getEffect(effect);
        if (existing == null || existing.getAmplifier() < instance.getAmplifier()) return true;
        if (existing.isInfiniteDuration()) return false;
        return existing.getDuration() <= (effect.value() == MobEffects.NIGHT_VISION.value() ? NIGHT_VISION_REFRESH_TICKS : EFFECT_REFRESH_TICKS);
    }

    private static void apply(Player player, ServerLevel level, BuffInstance instance, boolean held) {
        IBuff buff = instance.getBuff();
        if (buff instanceof RuneEffectType type) {
            if (held) type.onHeldTick(player, player.getMainHandItem());
            else type.onWornTick(player);
        } else if (buff.isEffect()) {
            var effect = buff.getEffect();
            if (effect == null) return;
            if (effect.value().isInstantenous()) {
                applyInstant(player, level, effect.value(), instance.getAmplifier(), held);
                return;
            }
            if (needsRefresh(player, effect, instance)) instance.onInventoryTick(ItemStack.EMPTY, level, player);
        } else {
            instance.onInventoryTick(ItemStack.EMPTY, level, player);
        }
    }

    /**
     * Fires an instant effect directly: added as a regular effect, it would fire on every tick of its duration. The cooldown
     * only starts once it fired, so it's ready as soon as the player needs it.
     */
    private static void applyInstant(Player player, ServerLevel level, MobEffect effect, int amplifier, boolean held) {
        if (!wouldHelp(player, effect)) return;
        Map<MobEffect, Long> readyTimes = INSTANT_READY_TIMES.computeIfAbsent(player, p -> new HashMap<>());
        long now = level.getGameTime();
        if (now < readyTimes.getOrDefault(effect, 0L)) return;
        readyTimes.put(effect, now + ArmorPlusConfig.instantEffectCooldown.get() * 20L);
        effect.applyInstantenousEffect(level, null, null, player, amplifier, held ? ArmorPlusConfig.heldInstantEffectStrength.get() : 1.0);
    }

    /** @return false if the effect would be wasted on the player right now: Instant Health at full health, Saturation when full */
    private static boolean wouldHelp(Player player, MobEffect effect) {
        if (effect == MobEffects.INSTANT_HEALTH.value()) return player.getHealth() < player.getMaxHealth();
        if (effect == MobEffects.SATURATION.value()) {
            FoodData food = player.getFoodData();
            return food.needsFood() || food.getSaturationLevel() < food.getFoodLevel();
        }
        return true;
    }

    /**
     * Applies the socketed weapon runes and the on-hit debuffs of tool runes of the attacker's main hand on melee hits,
     * without the ones a player switched off.
     */
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        LivingEntity target = event.getEntity();
        DamageSource source = event.getSource();
        if (target.level().isClientSide() || !isMeleeAttack(source)) return;
        if (!(source.getEntity() instanceof LivingEntity attacker) || source.getDirectEntity() != attacker) return;

        ItemStack weapon = attacker.getMainHandItem();
        WeaponEffects.hitRunes(weapon, target, attacker);
        if (attacker instanceof Player player) {
            for (BuffInstance instance : ToolEffects.onHit(player)) {
                RuneApplyEvent applyEvent = new RuneApplyEvent(player, instance, EffectApplyEvent.Context.HIT, weapon, target);
                if (!NeoForge.EVENT_BUS.post(applyEvent).isCanceled()) instance.hitEntity(weapon, target, attacker);
            }
        }
    }

    /** {@link RuneEffectType#onKill} of the attacker's main hand, once no other mod stopped the death. */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity target = event.getEntity();
        DamageSource source = event.getSource();
        if (target.level().isClientSide() || !isMeleeAttack(source)) return;
        if (!(source.getEntity() instanceof LivingEntity attacker) || source.getDirectEntity() != attacker) return;

        ItemStack weapon = attacker.getMainHandItem();
        WeaponEffects.kill(weapon, target, attacker);
        if (attacker instanceof Player player) {
            for (BuffInstance instance : ToolEffects.onHit(player)) {
                if (instance.getBuff() instanceof RuneEffectType type) type.onKill(weapon, target, attacker);
            }
        }
    }

    /** {@link RuneEffectType#onBlockBreak} of the held tool's abilities. */
    @SubscribeEvent
    public static void onBlockBreak(BreakBlockEvent event) {
        Player player = event.getPlayer();
        if (player == null || player.level().isClientSide()) return;
        for (BuffInstance instance : ToolEffects.held(player)) {
            if (instance.getBuff() instanceof RuneEffectType type) type.onBlockBreak(player, player.getMainHandItem(), event);
        }
    }

    /** {@link RuneEffectType#onDamageTaken} of the worn armor's abilities. */
    @SubscribeEvent
    public static void onDamageTaken(LivingDamageEvent.Pre event) {
        if (!(event.getEntity() instanceof Player player) || player.level().isClientSide()) return;
        for (BuffInstance instance : EffectScopes.ARMOR.active(player)) {
            if (instance.getBuff() instanceof RuneEffectType type) type.onDamageTaken(player, event);
        }
    }

    /** Excludes thorns, explosions etc. Projectiles are excluded by the direct entity check. */
    private static boolean isMeleeAttack(DamageSource source) {
        return source.is(DamageTypes.PLAYER_ATTACK) || source.is(DamageTypes.MOB_ATTACK)
                || source.is(DamageTypes.MOB_ATTACK_NO_AGGRO) || source.is(DamageTypes.MACE_SMASH);
    }
}
