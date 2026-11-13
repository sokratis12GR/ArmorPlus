package com.sofodev.armorplus.api;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Supplier;

/**
 * One kind of effects an entity can have, with its own screen and toggles: armor, tool, weapon or ranged effects (see
 * {@link EffectScopes}). Every scope works the same way, a new one only says where its effects come from
 * ({@link #collect(LivingEntity)}); the toggles, the active effects and the cache are shared.
 * <ul>
 *     <li>Players switch effects off by their {@link EffectEntry#toggleKey()}, saved in the scope's attachment. Mobs can't
 *     switch effects off.</li>
 *     <li>{@link #active(LivingEntity)} is cached per entity if the scope is cached (weak keys, so a respawned player starts
 *     fresh); call {@link #invalidate(LivingEntity)} when what the effects depend on changes.</li>
 * </ul>
 */
public abstract class EffectScope {

    private final String name;
    private final Supplier<AttachmentType<List<String>>> disabledEffects;
    private final boolean cached;
    private final Map<LivingEntity, List<EffectEntry>> active = Collections.synchronizedMap(new WeakHashMap<>());
    private final Map<LivingEntity, Set<IBuff>> activeBuffs = Collections.synchronizedMap(new WeakHashMap<>());

    /**
     * @param name            the scope's id, e.g. "armor", also sent by the effect screens to switch effects off
     * @param disabledEffects the player attachment holding the toggle keys of the effects switched off
     * @param cached          cache the active effects until {@link #invalidate(LivingEntity)}; leave it off for effects that
     *                        change without an event to invalidate them
     */
    protected EffectScope(String name, Supplier<AttachmentType<List<String>>> disabledEffects, boolean cached) {
        this.name = name;
        this.disabledEffects = disabledEffects;
        this.cached = cached;
    }

    /** {@return every effect the entity has in this scope, including the ones switched off or needing more pieces} */
    protected abstract List<EffectEntry> collect(LivingEntity entity);

    /**
     * Combines the active entries, e.g. into one per effect. Keeps them all by default.
     *
     * @param active the entries that are {@link EffectEntry#active()}, in order
     */
    protected List<EffectEntry> combine(List<EffectEntry> active) {
        return active;
    }

    /**
     * Called on the server after a player switched one of this scope's effects off or back on, and the toggle is saved.
     * The caches are already invalidated.
     *
     * @param effect the effect switched off, empty when one was switched back on
     */
    protected void onToggled(ServerPlayer player, Optional<BuffInstance> effect) {
    }

    /** {@return where the entry is from, as "/armorplus effects" lists it; translates {@code commands.armorplus.effects.source.<name>} by default} */
    public Component sourceName(EffectEntry entry) {
        return Component.translatable("commands.armorplus.effects.source." + name);
    }

    public final String name() {
        return name;
    }

    public final AttachmentType<List<String>> disabledEffects() {
        return disabledEffects.get();
    }

    /** {@return the key saved when a player switches the effect of the source off; the effect id unless the scope has several sources per effect} */
    public String toggleKey(String source, String id) {
        return id;
    }

    /** {@return a new entry of the buff without a piece requirement, switched off if the entity switched it off} */
    protected final EffectEntry entry(LivingEntity entity, BuffInstance buff, String source) {
        String id = BuffParser.idOf(buff.getBuff());
        String key = toggleKey(source, id);
        return EffectEntry.of(buff, id, source, key, isDisabled(entity, key));
    }

    /** {@return true if the entity is a player who switched the effect with the toggle key off; mobs can't switch effects off} */
    public final boolean isDisabled(LivingEntity entity, String toggleKey) {
        return entity instanceof Player player && ArmorPlusConfig.allowEffectToggles.get() && player.getData(disabledEffects()).contains(toggleKey);
    }

    /** {@return true if the entity is a player who switched the effect of the source off, see {@link #toggleKey(String, String)}} */
    public final boolean isDisabled(LivingEntity entity, String source, String id) {
        return isDisabled(entity, toggleKey(source, id));
    }

    /** {@return every effect of the entity in this scope with its status} */
    public final List<EffectEntry> entries(LivingEntity entity) {
        return List.copyOf(collect(entity));
    }

    /** {@return the effects the entity gets right now, with their sources (cached if the scope is)} */
    public final List<EffectEntry> activeEntries(LivingEntity entity) {
        return cached ? active.computeIfAbsent(entity, this::computeActive) : computeActive(entity);
    }

    private List<EffectEntry> computeActive(LivingEntity entity) {
        return List.copyOf(combine(collect(entity).stream().filter(EffectEntry::active).toList()));
    }

    /** {@return the effects the entity gets right now (cached if the scope is)} */
    public final List<BuffInstance> active(LivingEntity entity) {
        return activeEntries(entity).stream().map(EffectEntry::buff).toList();
    }

    /** {@return true if the entity gets the buff (ability) right now (cached if the scope is)} */
    public final boolean has(LivingEntity entity, IBuff buff) {
        return cached ? activeBuffs.computeIfAbsent(entity, this::computeBuffs).contains(buff) : computeBuffs(entity).contains(buff);
    }

    private Set<IBuff> computeBuffs(LivingEntity entity) {
        Set<IBuff> buffs = new HashSet<>();
        activeEntries(entity).forEach(entry -> buffs.add(entry.buff().getBuff()));
        return Set.copyOf(buffs);
    }

    /** {@return the entity's effect with the toggle key, if it has it right now (switched off or not)} */
    public final Optional<EffectEntry> find(LivingEntity entity, String toggleKey) {
        return collect(entity).stream().filter(entry -> entry.toggleKey().equals(toggleKey)).findFirst();
    }

    /** Forgets the entity's cached effects. */
    public void invalidate(LivingEntity entity) {
        active.remove(entity);
        activeBuffs.remove(entity);
    }

    /** Forgets every entity's cached effects. */
    public void clearCaches() {
        active.clear();
        activeBuffs.clear();
    }

    /** Called by the toggle payload once the toggle is saved. */
    public final void toggled(ServerPlayer player, Optional<BuffInstance> effect) {
        invalidate(player);
        onToggled(player, effect);
    }

    /** {@return the entries with one per effect id, the highest level of each} */
    protected static List<EffectEntry> highestPerEffect(List<EffectEntry> entries) {
        Map<String, EffectEntry> best = new LinkedHashMap<>();
        for (EffectEntry entry : entries) best.merge(entry.id(), entry, EffectScope::higher);
        return List.copyOf(best.values());
    }

    /** {@return the entry of the higher level, the first one if they're the same} */
    protected static EffectEntry higher(EffectEntry a, EffectEntry b) {
        return b.buff().getAmplifier() > a.buff().getAmplifier() ? b : a;
    }

    @Override
    public String toString() {
        return name;
    }
}
