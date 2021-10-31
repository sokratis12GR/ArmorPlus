package com.sofodev.armorplus.registry.items.tools.properties.tool;

import com.sofodev.armorplus.registry.items.extras.BuffInstance;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Supplier;

public interface IAPTool {

    /**
     * @return The IITemTier properties of the tool
     */
    IItemTier get();

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
    default void onBlockMined(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity player) {

    }

}