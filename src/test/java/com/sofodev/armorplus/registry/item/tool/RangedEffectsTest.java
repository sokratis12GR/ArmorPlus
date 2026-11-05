package com.sofodev.armorplus.registry.item.tool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RangedEffectsTest {

    private static final double DELTA = 1.0E-9;

    @Test
    void volleySpreadNarrowsWithLevel() {
        assertEquals(10.0F, RangedEffects.volleySpread(1));
        assertEquals(5.0F, RangedEffects.volleySpread(2));
        assertTrue(RangedEffects.volleySpread(5) < RangedEffects.volleySpread(4));
    }

    @Test
    void longShotHasNoBonusUpClose() {
        assertEquals(1.0, RangedEffects.longShotMultiplier(1, 0.0), DELTA);
        assertEquals(1.0, RangedEffects.longShotMultiplier(5, 8.0), DELTA);
    }

    @Test
    void longShotRisesWithDistanceUpToTheLimitOfTheLevel() {
        assertEquals(1.2, RangedEffects.longShotMultiplier(1, 18.0), DELTA);
        assertEquals(1.25, RangedEffects.longShotMultiplier(1, 500.0), DELTA);
        assertEquals(1.5, RangedEffects.longShotMultiplier(2, 500.0), DELTA);
    }

    @Test
    void markedBonusRisesPerLevel() {
        assertEquals(1.2, RangedEffects.markedMultiplier(1), DELTA);
        assertEquals(1.6, RangedEffects.markedMultiplier(3), DELTA);
    }

    @Test
    void ammoSaverIsCapped() {
        assertEquals(0.2, RangedEffects.ammoSaverChance(1), DELTA);
        assertEquals(0.8, RangedEffects.ammoSaverChance(4), DELTA);
        assertEquals(0.8, RangedEffects.ammoSaverChance(10), DELTA);
    }

    @Test
    void quickDrawAddsHalfATickPerLevel() {
        // level I alternates, a tick is skipped every other tick
        assertEquals(1, RangedEffects.quickDrawExtraTicks(1, 10));
        assertEquals(0, RangedEffects.quickDrawExtraTicks(1, 11));
        assertEquals(1, RangedEffects.quickDrawExtraTicks(2, 11));
        assertEquals(2, RangedEffects.quickDrawExtraTicks(3, 10));
        assertEquals(1, RangedEffects.quickDrawExtraTicks(3, 11));
    }

    @Test
    void quickDrawIsCapped() {
        assertEquals(2, RangedEffects.quickDrawExtraTicks(4, 11));
        assertEquals(2, RangedEffects.quickDrawExtraTicks(100, 10));
    }
}
