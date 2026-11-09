package com.sofodev.armorplus.registry.item.tool;

import net.minecraft.resources.ResourceLocation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AutoSmeltLootModifierTest {

    private static final ResourceLocation CLAY = ResourceLocation.parse("minecraft:clay");
    private static final ResourceLocation TERRACOTTA = ResourceLocation.parse("minecraft:terracotta");

    @Test
    void findsTheResultOfTheBlock() {
        assertEquals(TERRACOTTA, AutoSmeltLootModifier.findResult(CLAY, List.of("minecraft:sand|minecraft:glass", "minecraft:clay|minecraft:terracotta")));
    }

    @Test
    void firstRecipeOfABlockWins() {
        assertEquals(TERRACOTTA, AutoSmeltLootModifier.findResult(CLAY, List.of("minecraft:clay|minecraft:terracotta", "minecraft:clay|minecraft:brick")));
    }

    @Test
    void ignoresWhitespace() {
        assertEquals(TERRACOTTA, AutoSmeltLootModifier.findResult(CLAY, List.of(" minecraft:clay | minecraft:terracotta ")));
    }

    @Test
    void noRecipeForTheBlock() {
        assertNull(AutoSmeltLootModifier.findResult(CLAY, List.of("minecraft:sand|minecraft:glass")));
        assertNull(AutoSmeltLootModifier.findResult(CLAY, List.of()));
    }

    @Test
    void skipsMalformedRecipes() {
        assertNull(AutoSmeltLootModifier.findResult(CLAY, List.of("minecraft:clay", "minecraft:clay|a|b")));
        assertEquals(TERRACOTTA, AutoSmeltLootModifier.findResult(CLAY, List.of("minecraft:clay", "minecraft:clay|minecraft:terracotta")));
    }

    @Test
    void configuredRecipesDropOneWithoutFortune() {
        // clay drops 4 balls, ore drops vary and glass drops nothing
        assertEquals(1, AutoSmeltLootModifier.configuredCount(0, 4));
        assertEquals(1, AutoSmeltLootModifier.configuredCount(0, 0));
    }

    @Test
    void configuredRecipesScaleWithFortune() {
        assertEquals(3, AutoSmeltLootModifier.configuredCount(3, 3));
        assertEquals(1, AutoSmeltLootModifier.configuredCount(3, 0));
    }
}
