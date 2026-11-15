package com.sofodev.armorplus.api;

import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.Set;

/**
 * One effect of one source in an {@link EffectScope}, e.g. Night Vision of the worn Coal set, or Haste of the held tool's runes.
 * Every scope lists its effects as these, so they all look the same to the screens, the command and the API.
 *
 * @param buff      the effect at the level it's given at
 * @param id        the buff id, e.g. "minecraft:night_vision"
 * @param source    where it's from: {@link #RUNE} for socketed runes, {@link #MATERIAL} for the held item's material, or the
 *                  config name of a worn set, e.g. "coal"
 * @param toggleKey the key saved when the player switches it off, see {@link EffectScope#toggleKey(String, String)}
 * @param required  the worn pieces it needs (1 for anything but set effects)
 * @param worn      how many of those are worn (1 for anything but set effects)
 * @param slots     the pieces that count for a slot bound set effect ({@code required} of them are needed), empty if all pieces do
 * @param disabled  switched off by the player
 */
public record EffectEntry(BuffInstance buff, String id, String source, String toggleKey, int required, int worn,
                          Set<EquipmentSlot> slots, boolean disabled) {

    /** Source of socketed runes. */
    public static final String RUNE = "rune";
    /** Source of the held weapon's, tool's, bow's or crossbow's own material effects. */
    public static final String MATERIAL = "material";

    public EffectEntry {
        slots = Set.copyOf(slots);
    }

    /** {@return an entry without a piece requirement, as tool, weapon, ranged and armor rune effects are} */
    public static EffectEntry of(BuffInstance buff, String id, String source, String toggleKey, boolean disabled) {
        return new EffectEntry(buff, id, source, toggleKey, 1, 1, Set.of(), disabled);
    }

    /** {@return true if enough pieces are worn for it, whether or not it's switched off} */
    public boolean available() {
        return worn >= required;
    }

    /** {@return true if the entity gets it right now: available and not switched off} */
    public boolean active() {
        return available() && !disabled;
    }

    /** {@return true if it's from a socketed rune} */
    public boolean fromRune() {
        return RUNE.equals(source);
    }
}
