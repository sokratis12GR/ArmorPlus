package com.sofodev.armorplus.registry.item.extra;

import com.sofodev.armorplus.registry.item.armor.SetRequirement;
import net.minecraft.world.entity.EquipmentSlot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuffParserTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "minecraft:haste",
            "minecraft:haste|1",
            "minecraft:haste|256",
            "minecraft:regeneration|2|200",
            "armorplus:flight",
            "othermod:some_effect|3",
            "!minecraft:haste|2",
            "minecraft:night_vision|1@head",
            "minecraft:conduit_power|2@legs,feet",
            "minecraft:speed|1@chest,legs,feet:2",
            "minecraft:haste|2@2"
    })
    void acceptsValidEntries(String entry) {
        assertTrue(BuffParser.isValid(entry));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Not An Id",
            "minecraft:haste|0",
            "minecraft:haste|257",
            "minecraft:haste|lots",
            "minecraft:haste|",
            "minecraft:haste|1|0",
            "minecraft:haste|1|2|3",
            "minecraft:haste|1@hands",
            "minecraft:haste|1@5",
            "minecraft:haste|1@"
    })
    void rejectsInvalidEntries(String entry) {
        assertFalse(BuffParser.isValid(entry));
    }

    @Test
    void rejectsValuesThatAreNotText() {
        assertFalse(BuffParser.isValid(5));
        assertFalse(BuffParser.isValid(null));
    }

    @Test
    void recognizesSwitchedOffEntries() {
        assertTrue(BuffParser.isDisabled("!minecraft:haste|2"));
        assertTrue(BuffParser.isDisabled(" !minecraft:haste|2"));
        assertFalse(BuffParser.isDisabled("minecraft:haste|2"));
        assertEquals("minecraft:haste|2", BuffParser.withoutDisabledPrefix("!minecraft:haste|2"));
        assertEquals("minecraft:haste|2", BuffParser.withoutDisabledPrefix("minecraft:haste|2"));
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "minecraft:haste|1; minecraft:haste",
            "!minecraft:haste|2|30@head; minecraft:haste",
            "armorplus:flight; armorplus:flight",
            "haste; minecraft:haste"
    })
    void findsTheIdOfAnEntry(String entry, String id) {
        assertEquals(id, BuffParser.idOf(entry));
    }

    @Test
    void readsTheRequirementOfAnEntry() {
        assertEquals("minecraft:haste|2", BuffParser.withoutRequirement("minecraft:haste|2@chest"));
        assertEquals(SetRequirement.slots(EquipmentSlot.CHEST), BuffParser.requirementOf("minecraft:haste|2@chest"));
        assertNull(BuffParser.requirementOf("minecraft:haste|2"));
    }

    @Test
    void levelOneIsAmplifierZero() {
        assertEquals(0, BuffParser.armor("minecraft:haste").getAmplifier());
        assertEquals(0, BuffParser.armor("minecraft:haste|1").getAmplifier());
        assertEquals(1, BuffParser.armor("minecraft:haste|2").getAmplifier());
    }

    @Test
    void explicitSecondsSetTheDuration() {
        assertEquals(200 * 20, BuffParser.armor("minecraft:regeneration|2|200").getDurationTicks());
        assertEquals(4 * 20, BuffParser.weapon("minecraft:wither|2|4").getDurationTicks());
    }

    @Test
    void weaponEffectsHaveTheirOwnDefaultDuration() {
        assertEquals(BuffParser.DEFAULT_WEAPON_SECONDS * 20, BuffParser.weapon("minecraft:wither|1").getDurationTicks());
    }

    @Test
    void abilitiesOnlyResolveForTheirUse() {
        assertEquals(Buff.FLIGHT, BuffParser.armor("armorplus:flight").getBuff());
        assertNull(BuffParser.tool("armorplus:flight"));
        assertNull(BuffParser.weapon("armorplus:flight"));

        assertEquals(ToolBuff.AUTO_SMELT, BuffParser.tool("armorplus:auto_smelt").getBuff());
        assertNull(BuffParser.armor("armorplus:auto_smelt"));
    }

    @Test
    void unknownAbilitiesDontResolve() {
        assertNull(BuffParser.armor("armorplus:not_an_ability"));
    }

    @Test
    void otherEffectIdsBecomeGenericEffects() {
        assertInstanceOf(EffectBuff.class, BuffParser.armor("othermod:some_effect|2").getBuff());
    }

    @Test
    void onlyArmorKeepsRequirements() {
        assertEquals(SetRequirement.pieces(2), BuffParser.armor("minecraft:haste|2@2").getRequirement());
        assertNull(BuffParser.tool("minecraft:haste|2@2").getRequirement());
    }

    @Test
    void listsSkipInvalidAndSwitchedOffEntries() {
        List<BuffInstance> buffs = BuffParser.armor(List.of("minecraft:haste|2", "!minecraft:speed|1", "minecraft:haste|0", "armorplus:not_an_ability", 5));
        assertEquals(1, buffs.size());
        assertEquals(1, buffs.getFirst().getAmplifier());
    }

    @Test
    void invalidEntriesDontResolve() {
        assertNull(BuffParser.armor("minecraft:haste|0"));
    }
}
