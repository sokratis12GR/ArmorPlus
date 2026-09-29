package com.sofodev.armorplus.registry.item.tool.properties.tool;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ArmorPlusConfig.IMaterialConfig;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Supplier;

public interface IAPTool {

    /**
     * @return The IITemTier properties of the tool
     */
    ToolMaterial get();

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
    ChatFormatting getColor();

    /**
     * @return Additional base damage applied to arrows fired from this material's bow.
     */
    default double getBowDamageBonus() {
        return 0.0D;
    }

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

    default IMaterialConfig config() {
        return ArmorPlus.config.enhancedMaterial;
    }
}