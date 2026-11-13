package com.sofodev.armorplus.api;

import com.sofodev.armorplus.api.event.RuneRemoveEvent;
import com.sofodev.armorplus.api.event.RuneSocketEvent;
import com.sofodev.armorplus.api.event.RuneUpgradeEvent;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.rune.RuneItem;
import com.sofodev.armorplus.registry.rune.RuneRitual;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * Entry point for other mods: rune stacks, sockets and the effects a player has. Custom abilities are added with
 * {@link RuneEffectType}, and the events in {@link com.sofodev.armorplus.api.event} change or cancel socketing, extraction,
 * removal, upgrading and applying effects.
 * <p>
 * Armor and tool effects are separate, so a player's armor effects never include the held tool's effects or vice versa.
 */
public final class ArmorPlusAPI {

    private ArmorPlusAPI() {
    }

    /* ---------------------------------------- Rune stacks ---------------------------------------- */

    /**
     * {@return a new rune item}
     *
     * @param kind  the equipment the rune fits
     * @param entry a buff entry, e.g. {@code "minecraft:haste|2"}
     */
    public static ItemStack createRune(RuneKind kind, String entry) {
        return RuneItem.create(kind, entry);
    }

    /** {@return the rune held by a rune item, or null if the stack has none} */
    public static @Nullable RuneData getRune(ItemStack stack) {
        return Runes.runeData(stack);
    }

    /** {@return the rune's buff, or null if the rune is inactive (runes or the buff disabled, or the buff doesn't exist)} */
    public static @Nullable BuffInstance resolve(RuneData rune) {
        return Runes.resolve(rune);
    }

    /** {@return every rune that can currently be extracted or found in the world, once per buff and kind, at the highest level it's available at} */
    public static List<RuneData> getAllRunes() {
        return Runes.allObtainableRunes();
    }

    /* ---------------------------------------- Sockets ---------------------------------------- */

    /** {@return the runes socketed in the equipment} */
    public static List<RuneData> getSockets(ItemStack gear) {
        return Runes.sockets(gear);
    }

    /** {@return the number of sockets, 0 for items that can't hold runes or haven't rolled their sockets yet} */
    public static int getSocketCapacity(ItemStack gear) {
        return Runes.socketCapacity(gear);
    }

    /** {@return {@link SocketResult#OK} if the rune fits the equipment, otherwise the first reason it doesn't} */
    public static SocketResult canSocket(ItemStack gear, RuneData rune) {
        return Runes.canSocket(gear, rune);
    }

    /**
     * Doesn't check the socketing rules or fire {@link RuneSocketEvent}, so check
     * {@link #canSocket(ItemStack, RuneData)} first.
     *
     * @return a copy of the gear (count 1) with the rune socketed
     */
    public static ItemStack withRune(ItemStack gear, RuneData rune) {
        return Runes.withRune(gear, rune);
    }

    /**
     * Doesn't fire {@link RuneRemoveEvent} or give the rune back.
     *
     * @return a copy of the gear (count 1) without that one rune
     */
    public static ItemStack withoutRune(ItemStack gear, RuneData rune) {
        return Runes.withoutRune(gear, rune);
    }

    /* ---------------------------------------- Upgrading ---------------------------------------- */

    /** {@return true if two of this rune can be combined in the ritual: it's active, its level matters and it's below the maximum} */
    public static boolean canUpgrade(RuneData rune) {
        return Runes.canUpgrade(rune);
    }

    /**
     * {@return the highest level runes and equipment effects of the buff can be upgraded to}
     *
     * @param buffId the rune's {@link RuneData#buffId()}
     */
    public static int getMaxRuneLevel(String buffId) {
        return Runes.maxLevel(buffId);
    }

    /** {@return true if the buff was extracted from this item in a Rune Altar, so it no longer has it} */
    public static boolean isExtracted(ItemStack gear, String buffId) {
        return Runes.isExtracted(gear, buffId);
    }

    /**
     * Doesn't check the upgrading rules or fire {@link RuneUpgradeEvent}, so check
     * {@link #canUpgrade(RuneData)} first.
     *
     * @return the rune one level higher
     */
    public static RuneData getUpgraded(RuneData rune) {
        return Runes.upgraded(rune);
    }

    /** {@return true if the rune can raise the equipment's own effect by a level in the ritual} */
    public static boolean canUpgradeNative(ItemStack gear, RuneData rune) {
        return Runes.canUpgradeNative(gear, rune);
    }

    /**
     * {@return the level of the material's effect on this item, including upgrades; 0 if it doesn't have it}
     *
     * @param gear   the equipment
     * @param buffId the {@link RuneData#buffId()} of the effect
     */
    public static int getNativeLevel(ItemStack gear, String buffId) {
        return Runes.nativeLevel(gear, buffId);
    }

    /** {@return the experience level cost to upgrade the rune, before {@link RuneUpgradeEvent}} */
    public static int getUpgradeCost(RuneData rune) {
        return Runes.upgradeCost(rune);
    }

    /** {@return the item and count the ritual uses up from the pedestals to raise the rune a level, before the events; EMPTY for none} */
    public static ItemStack getUpgradeCatalyst(RuneData rune) {
        return Runes.upgradeCatalyst(rune);
    }

    /** {@return the item and count the ritual uses up from the pedestals to upgrade an effect to that level with the rune, before the events; EMPTY for none} */
    public static ItemStack getUpgradeCatalyst(RuneData rune, int level) {
        return Runes.upgradeCatalyst(rune, level);
    }

    /** {@return the item and count the ritual uses up from the pedestals on top of the upgrade catalyst for the rune's type, before the events; EMPTY for none} */
    public static ItemStack getUpgradeTypeCatalyst(RuneData rune) {
        return Runes.upgradeTypeCatalyst(rune);
    }

    /** {@return the item and count the ritual uses up from the pedestals to socket the rune, before the events; EMPTY for none} */
    public static ItemStack getSocketCatalyst(RuneData rune) {
        return Runes.socketCatalyst(rune);
    }

    /* ---------------------------------------- Ritual ---------------------------------------- */

    /**
     * {@return the blocks that count as pedestals of the circle, the tag {@code armorplus:ritual_pedestals}} Only Empowering
     * Pedestals can hold items, other blocks in the tag just complete the circle.
     */
    public static TagKey<Block> getRitualPedestalTag() {
        return RuneRitual.PEDESTALS;
    }

    /**
     * {@return true if the Rune Altar's circle is complete, which unlocks socketing and upgrading}
     *
     * @param level the altar's level
     * @param altar the altar's position
     */
    public static boolean isRitualActive(Level level, BlockPos altar) {
        return RuneRitual.isActive(level, altar);
    }

    /** {@return where the pedestals of the altar's circle go, whether they are there or not} */
    public static List<BlockPos> getRitualPedestalPositions(BlockPos altar) {
        return RuneRitual.pedestalPositions(altar);
    }

    /** {@return what's on each of {@link #getRitualPedestalPositions(BlockPos)}, EMPTY where there's nothing (don't modify)} */
    public static List<ItemStack> getRitualPedestalItems(Level level, BlockPos altar) {
        return RuneRitual.pedestalItems(level, altar);
    }

    /* ---------------------------------------- Entity effects (server side) ---------------------------------------- */

    /** {@return every effect scope: armor, tool, weapon, ranged and any registered with {@link EffectScopes#register}} */
    public static Collection<EffectScope> getEffectScopes() {
        return EffectScopes.all();
    }

    /** {@return true if the entity gets the buff in the scope right now (players: and didn't switch it off)} */
    public static boolean hasEffect(EffectScope scope, LivingEntity entity, IBuff buff) {
        return scope.has(entity, buff);
    }

    /** {@return the entity's active effects in the scope} */
    public static List<BuffInstance> getEffects(EffectScope scope, LivingEntity entity) {
        return scope.active(entity);
    }

    /** {@return every effect of the entity in the scope with its source and status, including the ones switched off or needing more pieces} */
    public static List<EffectEntry> getEffectEntries(EffectScope scope, LivingEntity entity) {
        return scope.entries(entity);
    }

    /** {@return true if the player's worn sets or armor runes give the buff and it isn't switched off} */
    public static boolean hasArmorEffect(Player player, IBuff buff) {
        return hasEffect(EffectScopes.ARMOR, player, buff);
    }

    /** {@return true if the runes of the tool in the main hand give the buff and it isn't switched off} */
    public static boolean hasToolEffect(Player player, IBuff buff) {
        return hasEffect(EffectScopes.TOOL, player, buff);
    }

    /** {@return true if the item in the main hand gives the buff on hit and it isn't switched off} */
    public static boolean hasWeaponEffect(Player player, IBuff buff) {
        return hasEffect(EffectScopes.WEAPON, player, buff);
    }

    /** {@return true if the held bow or crossbow gives the buff to its arrows and it isn't switched off} */
    public static boolean hasRangedEffect(Player player, IBuff buff) {
        return hasEffect(EffectScopes.RANGED, player, buff);
    }

    /** {@return the player's active armor effects, one per effect at the highest level of all sources} */
    public static List<BuffInstance> getArmorEffects(Player player) {
        return getEffects(EffectScopes.ARMOR, player);
    }

    /** {@return the active effects of the runes of the tool in the main hand} */
    public static List<BuffInstance> getToolEffects(Player player) {
        return getEffects(EffectScopes.TOOL, player);
    }

    /** {@return the on-hit effects of the item in the main hand that aren't switched off: its material's effects and weapon runes} */
    public static List<BuffInstance> getWeaponEffects(Player player) {
        return getEffects(EffectScopes.WEAPON, player);
    }

    /** {@return the ranged effects of the bow or crossbow in the main hand, otherwise the off hand (its material's and its runes') that aren't switched off} */
    public static List<BuffInstance> getRangedEffects(Player player) {
        return getEffects(EffectScopes.RANGED, player);
    }
}
