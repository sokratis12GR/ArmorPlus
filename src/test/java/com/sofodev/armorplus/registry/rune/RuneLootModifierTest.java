package com.sofodev.armorplus.registry.rune;

import net.minecraft.resources.Identifier;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RuneLootModifierTest {

    private static final Identifier DUNGEON = Identifier.parse("minecraft:chests/simple_dungeon");

    @Test
    void parsesChances() {
        assertEquals(Map.of(DUNGEON, 0.25), RuneLootModifier.parseChances(List.of("minecraft:chests/simple_dungeon|0.25")));
    }

    @Test
    void ignoresWhitespace() {
        assertEquals(Map.of(DUNGEON, 0.25), RuneLootModifier.parseChances(List.of(" minecraft:chests/simple_dungeon | 0.25 ")));
    }

    @Test
    void firstEntryOfATableWins() {
        Map<Identifier, Double> chances = RuneLootModifier.parseChances(List.of(
                "minecraft:chests/simple_dungeon|0.25",
                "minecraft:chests/simple_dungeon|0.75"));
        assertEquals(0.25, chances.get(DUNGEON));
    }

    @Test
    void skipsMalformedEntriesAndKeepsTheRest() {
        Map<Identifier, Double> chances = RuneLootModifier.parseChances(List.of(
                "minecraft:chests/simple_dungeon",
                "minecraft:chests/simple_dungeon|",
                "minecraft:chests/simple_dungeon|lots",
                "minecraft:chests/simple_dungeon|0.1|0.2",
                "Not An Id|0.5",
                "",
                "minecraft:chests/simple_dungeon|0.25"));
        assertEquals(Map.of(DUNGEON, 0.25), chances);
    }

    @Test
    void noEntriesNoChances() {
        assertTrue(RuneLootModifier.parseChances(List.of()).isEmpty());
    }
}
