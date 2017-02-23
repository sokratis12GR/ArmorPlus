/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.energy.tesla;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional.Method;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.energy.tesla.BaseTeslaPickaxe;
import net.thedragonteam.armorplus.util.APTeslaUtils;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

import static net.thedragonteam.armorplus.APConfig.*;

public class ItemTeslaPickaxe extends BaseTeslaPickaxe {
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE);

    public ItemTeslaPickaxe() {
        super(ToolMaterial.DIAMOND, "tesla_pickaxe", EFFECTIVE_ON, maxEnergyCapacity[1], energyInput[1], energyOutput[1]);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusTesla;
    }

    @Method(modid = "tesla")
    @Override
    public boolean onBlockDestroyed(@Nonnull ItemStack stack, World worldIn, @Nonnull IBlockState state, @Nonnull BlockPos pos, @Nonnull EntityLivingBase entityLiving) {
        APTeslaUtils.INSTANCE.usePower(stack, energyOutput[1]);
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return Items.DIAMOND_PICKAXE.canHarvestBlock(state);
    }

    @Method(modid = "tesla")
    @Override
    public float getStrVsBlock(@Nonnull ItemStack stack, IBlockState state) {
        return APTeslaUtils.INSTANCE.getStoredPower(stack) < energyOutput[1] ? 0.5F : Items.WOODEN_PICKAXE.getStrVsBlock(stack, state) > 1.0F ? 5.5F : super.getStrVsBlock(stack, state);
    }

    @Method(modid = "tesla")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        createTooltip(stack, tooltip);
    }

    @Method(modid = "tesla")
    private void createTooltip(ItemStack stack, List<String> tooltip) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(TextFormatting.DARK_AQUA + I18n.format("tooltip.tesla.powerinfo", Long.toString(APTeslaUtils.INSTANCE.getStoredPower(stack)), Long.toString(APTeslaUtils.INSTANCE.getMaxCapacity(stack))));
            tooltip.add(TextFormatting.DARK_AQUA + I18n.format("tooltip.tesla.cost.tool", Long.toString(energyOutput[1])));
        } else
            tooltip.add(I18n.format("tooltip.tesla.showinfo", TextFormatting.DARK_AQUA, keyBindSneak.getDisplayName(), TextFormatting.GRAY));
    }
}