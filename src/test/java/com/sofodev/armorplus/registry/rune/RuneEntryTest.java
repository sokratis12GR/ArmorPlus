package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.api.RuneData;
import com.sofodev.armorplus.api.RuneKind;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuneEntryTest {

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "armorplus:flight; 1",
            "minecraft:haste|3; 3",
            "minecraft:haste|2|100; 2",
            "minecraft:haste|2|100@head; 2",
            "minecraft:haste|4@legs,feet:1; 4",
            "minecraft:haste|0; 1",
            "minecraft:haste|-2; 1",
            "minecraft:haste|lots; 1"
    })
    void parsesLevels(String entry, int level) {
        assertEquals(level, Runes.parseLevel(entry));
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "minecraft:haste; minecraft:haste",
            "minecraft:haste|2; minecraft:haste",
            "minecraft:haste|2|100@head; minecraft:haste",
            "armorplus:flight@2; armorplus:flight",
            "haste|2; minecraft:haste"
    })
    void buffIdIgnoresEverythingButTheId(String entry, String id) {
        assertEquals(id, new RuneData(RuneKind.ARMOR, entry).buffId());
    }
}
