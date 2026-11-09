package com.sofodev.armorplus.registry.rune;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RitualCatalystsTest {

    @Test
    void typeEntriesStayOutOfLevelsAndSocketing() {
        List<RitualCatalysts.Entry> entries = List.of(RitualCatalysts.parse("bow|minecraft:stone"), RitualCatalysts.parse("2|minecraft:coal"), RitualCatalysts.parse("socket|minecraft:diamond"));

        assertEquals("stone", RitualCatalysts.typeEntry(entries, RitualCatalysts.Type.BOW).item().getPath());
        assertNull(RitualCatalysts.typeEntry(entries, RitualCatalysts.Type.TOOL));
        assertNull(RitualCatalysts.typeEntry(entries, null));
        assertEquals("coal", RitualCatalysts.levelEntry(entries, 3).item().getPath());
        assertEquals("diamond", RitualCatalysts.socketEntry(entries).item().getPath());
    }

    @Test
    void rejectsUnknownTypesAndTypesInOverrides() {
        assertFalse(RitualCatalysts.isValid("shield|minecraft:stone"));
        assertFalse(RitualCatalysts.isValid("bow:2|minecraft:stone"));
        assertFalse(RuneOverrides.isValidLevelCatalysts("bow=minecraft:stone"));
    }

    private static final List<RitualCatalysts.Entry> TIERS = List.of(
            RitualCatalysts.parse("3|minecraft:diamond"), RitualCatalysts.parse("5|minecraft:emerald|2"), RitualCatalysts.parse("socket|minecraft:coal"));

    @ParameterizedTest
    @ValueSource(strings = {"socket|armorplus:lava_crystal", "2|armorplus:ember_sigil", "5|armorplus:astral_sigil|4", "256|minecraft:stone|1"})
    void acceptsEntries(String entry) {
        assertTrue(RitualCatalysts.isValid(entry));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "socket", "1|minecraft:stone", "257|minecraft:stone", "x|minecraft:stone", "2|minecraft:stone|0", "2|minecraft:stone|5", "2|minecraft:stone|1|1"})
    void rejectsEntries(String entry) {
        assertFalse(RitualCatalysts.isValid(entry));
    }

    @Test
    void levelsBelowTheFirstEntryNeedNone() {
        assertNull(RitualCatalysts.levelEntry(TIERS, 2));
    }

    @Test
    void anEntryCountsUntilTheNextOne() {
        assertEquals(3, RitualCatalysts.levelEntry(TIERS, 3).level());
        assertEquals(3, RitualCatalysts.levelEntry(TIERS, 4).level());
        assertEquals(5, RitualCatalysts.levelEntry(TIERS, 5).level());
        assertEquals(5, RitualCatalysts.levelEntry(TIERS, 100).level());
    }

    @Test
    void socketingHasItsOwnEntry() {
        assertEquals("coal", RitualCatalysts.socketEntry(TIERS).item().getPath());
        assertNull(RitualCatalysts.socketEntry(List.of()));
    }

    @Test
    void countDefaultsToOne() {
        assertEquals(1, RitualCatalysts.parse("2|minecraft:stone").count());
        assertEquals(2, RitualCatalysts.parse("2|minecraft:stone|2").count());
    }
}
