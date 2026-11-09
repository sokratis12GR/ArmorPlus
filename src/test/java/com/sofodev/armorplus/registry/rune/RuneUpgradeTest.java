package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.api.RuneKind;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RuneUpgradeTest {

    private static final RuneData HASTE = new RuneData(RuneKind.TOOL, "minecraft:haste|1");
    private static final RuneData SPEED = new RuneData(RuneKind.TOOL, "minecraft:speed|1");

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "minecraft:haste|1; minecraft:haste|2",
            "minecraft:haste|4; minecraft:haste|5",
            "minecraft:haste; minecraft:haste|2",
            "minecraft:wither|2|4; minecraft:wither|3|4",
            "minecraft:night_vision|1@head; minecraft:night_vision|2@head",
            "minecraft:haste@legs,feet:1; minecraft:haste|2@legs,feet:1",
            "minecraft:regeneration|2|200@head; minecraft:regeneration|3|200@head",
            "armorplus:piercing; armorplus:piercing|2"
    })
    void raisesTheLevelAndKeepsTheRest(String entry, String upgraded) {
        assertEquals(upgraded, Runes.upgradedEntry(entry));
    }

    @Test
    void costRisesWithTheLevel() {
        assertEquals(8, Runes.upgradeCost(1, 5, 3));
        assertEquals(17, Runes.upgradeCost(4, 5, 3));
    }

    @Test
    void costStaysWithinTheLimit() {
        assertEquals(39, Runes.upgradeCost(255, 5, 3));
    }

    @Test
    void removesOnlyTheGivenRune() {
        assertEquals(List.of(SPEED), Runes.withoutOne(List.of(HASTE, SPEED), HASTE));
    }

    @Test
    void removesOneCopyOfADuplicate() {
        assertEquals(List.of(HASTE), Runes.withoutOne(List.of(HASTE, HASTE), HASTE));
    }

    @Test
    void removingTheLastRuneLeavesNothing() {
        assertTrue(Runes.withoutOne(List.of(HASTE), HASTE).isEmpty());
    }

    @Test
    void removingARuneThatIsntSocketedChangesNothing() {
        assertEquals(List.of(HASTE), Runes.withoutOne(List.of(HASTE), SPEED));
    }
}
