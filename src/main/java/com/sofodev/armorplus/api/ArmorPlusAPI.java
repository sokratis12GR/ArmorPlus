package com.sofodev.armorplus.api;

import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.rune.RuneData;
import com.sofodev.armorplus.registry.rune.RuneItem;
import com.sofodev.armorplus.registry.rune.RuneKind;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Entry point for other mods: rune stacks, sockets and the effects a player has. Custom abilities are added with
 * {@link RuneEffectType}, and the events in {@code com.sofodev.armorplus.api.event} change or cancel socketing, extraction
 * and applying effects.
 * <p>
 * Armor and tool effects are separate, so a player's armor effects never include the held tool's effects or vice versa.
 */
public final class ArmorPlusAPI {

    private ArmorPlusAPI() {
    }

    /* ---------------------------------------- Rune stacks ---------------------------------------- */

    /** @param entry a buff entry, e.g. {@code "minecraft:haste|2"} */
    public static ItemStack createRune(RuneKind kind, String entry) {
        return RuneItem.create(kind, entry);
    }

    /** @return the rune held by a rune item, or null if the stack has none */
    public static @Nullable RuneData getRune(ItemStack stack) {
        return Runes.runeData(stack);
    }

    /** @return the rune's buff, or null if the rune is inactive (runes or the buff disabled, or the buff doesn't exist) */
    public static @Nullable BuffInstance resolve(RuneData rune) {
        return Runes.resolve(rune);
    }

    /** @return every rune that can currently be extracted or found in the world, once per buff and kind */
    public static List<RuneData> getAllRunes() {
        return Runes.allObtainableRunes();
    }

    /* ---------------------------------------- Sockets ---------------------------------------- */

    public static List<RuneData> getSockets(ItemStack gear) {
        return Runes.sockets(gear);
    }

    /** @return the number of sockets, 0 for items that can't hold runes or haven't rolled their sockets yet */
    public static int getSocketCapacity(ItemStack gear) {
        return Runes.socketCapacity(gear);
    }

    public static Runes.SocketResult canSocket(ItemStack gear, RuneData rune) {
        return Runes.canSocket(gear, rune);
    }

    /**
     * Doesn't check the socketing rules or fire {@link com.sofodev.armorplus.api.event.RuneSocketEvent}, so check
     * {@link #canSocket(ItemStack, RuneData)} first.
     *
     * @return a copy of the gear (count 1) with the rune socketed
     */
    public static ItemStack withRune(ItemStack gear, RuneData rune) {
        return Runes.withRune(gear, rune);
    }

    /* ---------------------------------------- Player effects (server side) ---------------------------------------- */

    /** @return true if the player's worn sets or armor runes give the buff and it isn't switched off */
    public static boolean hasArmorEffect(Player player, IBuff buff) {
        return ArmorEffects.has(player, buff);
    }

    /** @return true if the runes of the tool in the main hand give the buff and it isn't switched off */
    public static boolean hasToolEffect(Player player, IBuff buff) {
        return ToolEffects.has(player, buff);
    }

    /** @return the player's active armor effects, one per effect at the highest level of all sources */
    public static List<BuffInstance> getArmorEffects(Player player) {
        return ArmorEffects.active(player);
    }

    /** @return the active effects of the runes of the tool in the main hand */
    public static List<BuffInstance> getToolEffects(Player player) {
        return ToolEffects.active(player);
    }
}
