package com.sofodev.armorplus.registry.rune;

import net.minecraft.core.BlockPos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RuneRitualTest {

    @Test
    void pedestalsAreTwoBlocksAwayOnEachSide() {
        BlockPos altar = new BlockPos(10, 64, -3);
        List<BlockPos> positions = RuneRitual.pedestalPositions(altar);

        assertEquals(RuneRitual.PEDESTAL_COUNT, new HashSet<>(positions).size());
        for (BlockPos pos : positions) {
            assertEquals(RuneRitual.PEDESTAL_DISTANCE, pos.distManhattan(altar));
            assertEquals(altar.getY(), pos.getY());
        }
    }

    /** {@code RitualScreen} draws the pedestals in this order. */
    @Test
    void pedestalsGoNorthEastSouthWest() {
        BlockPos altar = BlockPos.ZERO;
        assertEquals(List.of(altar.north(2), altar.east(2), altar.south(2), altar.west(2)), RuneRitual.pedestalPositions(altar));
    }

    @ParameterizedTest
    @ValueSource(strings = {"armorplus:flight|upgrade_catalyst=minecraft:nether_star", "armorplus:flight|upgrade_catalyst=minecraft:nether_star*4", "armorplus:flight|upgrade_catalyst=none",
            "armorplus:flight|socket_catalyst=minecraft:nether_star*4", "armorplus:flight|socket_catalyst=none",
            "armorplus:flight|upgrade_catalyst@5=armorplus:astral_sigil", "armorplus:flight|upgrade_catalyst@2=none|upgrade_catalyst@5=minecraft:nether_star*4"})
    void acceptsPedestalCatalysts(String entry) {
        assertTrue(RuneOverrides.isValid(entry));
    }

    @ParameterizedTest
    @ValueSource(strings = {"armorplus:flight|upgrade_catalyst=minecraft:nether_star*0", "armorplus:flight|upgrade_catalyst=minecraft:nether_star*5",
            "armorplus:flight|socket_catalyst=minecraft:nether_star*0", "armorplus:flight|socket_catalyst=minecraft:nether_star*5",
            "armorplus:flight|upgrade_catalyst@5=minecraft:nether_star*5", "armorplus:flight|upgrade_catalyst@1=minecraft:nether_star",
            "armorplus:flight|upgrade_catalyst@socket=minecraft:nether_star", "armorplus:flight|upgrade_catalyst@=minecraft:nether_star"})
    void rejectsPedestalCatalystCountsOutOfRange(String entry) {
        assertFalse(RuneOverrides.isValid(entry));
    }

    /** A level entry counts up to the next one, and below the first the buff falls back on its other catalysts. */
    @Test
    void levelCatalystsCountFromTheirLevelOn() {
        RuneOverrides.Rules rules = RuneOverrides.parse("armorplus:flight|upgrade_catalyst=minecraft:diamond|upgrade_catalyst@3=armorplus:rime_sigil|upgrade_catalyst@5=armorplus:astral_sigil*2");

        assertNull(RitualCatalysts.levelEntry(rules.levelCatalysts(), 2));
        assertEquals("armorplus:rime_sigil", RitualCatalysts.levelEntry(rules.levelCatalysts(), 4).item().toString());
        RitualCatalysts.Entry top = RitualCatalysts.levelEntry(rules.levelCatalysts(), 6);
        assertEquals("armorplus:astral_sigil", top.item().toString());
        assertEquals(2, top.count());
        assertEquals("minecraft:diamond", rules.upgradeCatalyst().toString());
    }

    @Test
    void checksLevelCatalystsOfTheConfigEditor() {
        assertTrue(RuneOverrides.isValidLevelCatalysts("5=armorplus:astral_sigil"));
        assertTrue(RuneOverrides.isValidLevelCatalysts("3=armorplus:rime_sigil,5=armorplus:astral_sigil*2"));
        assertFalse(RuneOverrides.isValidLevelCatalysts("5"));
        assertFalse(RuneOverrides.isValidLevelCatalysts("socket=armorplus:astral_sigil"));
        assertFalse(RuneOverrides.isValidLevelCatalysts("5=armorplus:astral_sigil*9"));
    }

    @Test
    void fourPedestalsForTheJeiLayout() {
        assertEquals(4, RuneRitual.PEDESTAL_COUNT);
    }
}
