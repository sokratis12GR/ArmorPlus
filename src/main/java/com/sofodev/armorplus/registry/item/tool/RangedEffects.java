package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.api.RuneKind;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * The ranged rune effects of the bow or crossbow a player holds, and the numbers behind the abilities.
 * <p>
 * Separate from the other effects: ranged effects have their own screen (Ranged Effects key) and toggles. Mobs and other
 * non-player shooters always use every effect.
 */
public final class RangedEffects {

    /** Volley: the spread between the arrows at level I, divided by the level. */
    private static final float VOLLEY_SPREAD_DEGREES = 10.0F;
    private static final double LONG_SHOT_START_DISTANCE = 8.0;
    private static final double LONG_SHOT_BONUS_PER_BLOCK = 0.02;
    private static final double LONG_SHOT_BONUS_LIMIT_PER_LEVEL = 0.25;
    private static final double MARKED_BONUS_PER_LEVEL = 0.2;
    private static final double AMMO_SAVER_CHANCE_PER_LEVEL = 0.2;
    private static final double AMMO_SAVER_MAX_CHANCE = 0.8;
    /** Quick Draw above this level would finish a shot in a handful of ticks. */
    private static final int QUICK_DRAW_MAX_LEVEL = 4;

    private RangedEffects() {
    }

    /**
     * One ranged rune effect of the held bow or crossbow.
     *
     * @param id       the buff id, also the key saved when the player switches it off, e.g. "armorplus:piercing"
     * @param disabled switched off by the player
     * @param rune     from a socketed rune, not the material
     */
    public record Entry(BuffInstance buff, String id, boolean disabled, boolean rune) {
    }

    /** @return the bow or crossbow in the main hand, else the off hand, else EMPTY */
    public static ItemStack heldWeapon(LivingEntity entity) {
        if (Runes.gearKind(entity.getMainHandItem()) == RuneKind.RANGED) return entity.getMainHandItem();
        return Runes.gearKind(entity.getOffhandItem()) == RuneKind.RANGED ? entity.getOffhandItem() : ItemStack.EMPTY;
    }

    /** @return every ranged effect of the held bow or crossbow (its material's and its runes') with its status */
    public static List<Entry> entries(Player player) {
        List<Entry> entries = new ArrayList<>();
        ItemStack weapon = heldWeapon(player);
        for (RuneData rune : Runes.rangedEffects(weapon)) {
            BuffInstance buff = Runes.resolve(rune);
            String id = BuffParser.idOf(buff.getBuff());
            entries.add(new Entry(buff, id, isDisabled(player, id), Runes.sockets(weapon).contains(rune)));
        }
        return List.copyOf(entries);
    }

    public static boolean isDisabled(Player player, String id) {
        return ArmorPlusConfig.allowEffectToggles.get() && player.getData(ModAttachments.DISABLED_RANGED_EFFECTS.get()).contains(id);
    }

    /** @return the material effects and runes of the weapon that the shooter hasn't switched off */
    public static List<RuneData> activeRunes(ItemStack weapon, LivingEntity shooter) {
        List<RuneData> runes = Runes.rangedEffects(weapon);
        if (!(shooter instanceof Player player)) return runes;
        return runes.stream().filter(rune -> !isDisabled(player, rune.buffId())).toList();
    }

    /** @return the level of the rune's buff, 1 for abilities without levels */
    public static int level(BuffInstance buff) {
        return Math.max(1, buff.getAmplifier() + 1);
    }

    /** @return the angle between the Volley arrows in degrees, narrower at higher levels */
    public static float volleySpread(int level) {
        return VOLLEY_SPREAD_DEGREES / Math.max(1, level);
    }

    /** @return the Long Shot damage multiplier: rises with the distance past 8 blocks, up to a limit that rises per level */
    public static double longShotMultiplier(int level, double distance) {
        double bonus = Math.max(0.0, distance - LONG_SHOT_START_DISTANCE) * LONG_SHOT_BONUS_PER_BLOCK;
        return 1.0 + Math.min(LONG_SHOT_BONUS_LIMIT_PER_LEVEL * level, bonus);
    }

    /** @return the damage multiplier of an arrow hitting a target marked at this level */
    public static double markedMultiplier(int level) {
        return 1.0 + MARKED_BONUS_PER_LEVEL * level;
    }

    /** @return the chance not to use an arrow */
    public static double ammoSaverChance(int level) {
        return Math.min(AMMO_SAVER_MAX_CHANCE, AMMO_SAVER_CHANCE_PER_LEVEL * level);
    }

    /**
     * @param tickCount the shooter's age, odd levels alternate between ticks to give half a tick per level
     * @return the extra ticks of drawing done in this tick: half a tick per level
     */
    public static int quickDrawExtraTicks(int level, int tickCount) {
        int capped = Math.min(level, QUICK_DRAW_MAX_LEVEL);
        return capped / 2 + (capped % 2 == 1 && tickCount % 2 == 0 ? 1 : 0);
    }
}
