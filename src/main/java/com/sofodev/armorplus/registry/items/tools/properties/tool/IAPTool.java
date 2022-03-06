package com.sofodev.armorplus.registry.items.tools.properties.tool;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Supplier;

public interface IAPTool {

    /**
     * @return The IITemTier properties of the tool
     */
    Tier get();

    /**
     * A list of all the BuffInstances for the tool
     * <p>
     * A tool can contain multiple buff instances, which means it can utilize many custom effects.
     *
     * @return
     */
    Supplier<List<BuffInstance>> getBuffInstances();

    /**
     * @return The name of the tool
     */
    String getName();

    /**
     * @return The rarity of the tool, (i.e the color of its name)
     */
    Rarity getRarity();

    /**
     * Applies a custom effect when a block is mined via the pickaxe
     *
     * @param stack  - The ItemStack object of the pickaxe
     * @param world  - The world object
     * @param state  - The blockstate being mined
     * @param pos    - BlockState's position in the world
     * @param player - The player breaking the block
     */
    default void onBlockMined(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity player) {

    }

    default ArmorPlusConfig.MaterialConfig config() {
        return ArmorPlusConfig.enhancedMaterial;
    }
}