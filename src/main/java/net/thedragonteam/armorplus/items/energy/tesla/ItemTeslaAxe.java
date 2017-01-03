/*
 * Copyright (c) TheDragonTeam 2016.
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
import net.minecraftforge.fml.common.Optional;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.energy.tesla.BaseTeslaAxe;
import net.thedragonteam.armorplus.util.ARPTeslaUtils;

import java.util.List;
import java.util.Set;

import static net.thedragonteam.armorplus.APConfig.*;

public class ItemTeslaAxe extends BaseTeslaAxe {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.LOG, Blocks.LOG2, Blocks.WOODEN_SLAB, Blocks.DOUBLE_WOODEN_SLAB, Blocks.CHEST, Blocks.LADDER,
            Blocks.CRAFTING_TABLE, Blocks.TRAPDOOR, Blocks.ACACIA_FENCE, Blocks.BIRCH_FENCE, Blocks.DARK_OAK_FENCE, Blocks.JUNGLE_FENCE, Blocks.OAK_FENCE, Blocks.SPRUCE_FENCE,
            Blocks.ACACIA_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.LEAVES,
            Blocks.LEAVES2, Blocks.BOOKSHELF, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, Blocks.NOTEBLOCK, Blocks.PUMPKIN, Blocks.MELON_BLOCK, Blocks.PLANKS, Blocks.WOODEN_PRESSURE_PLATE,
            Blocks.ACACIA_STAIRS, Blocks.BIRCH_STAIRS, Blocks.SPRUCE_STAIRS, Blocks.DARK_OAK_STAIRS, Blocks.JUNGLE_STAIRS, Blocks.OAK_STAIRS);

    public ItemTeslaAxe() {
        super(ToolMaterial.DIAMOND, "tesla_axe", EFFECTIVE_ON, maxCapacityAxe, inputAxe, outputAxe);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusTesla;
    }

    @Optional.Method(modid = "tesla")
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        ARPTeslaUtils.usePower(stack, outputAxe);
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return Items.DIAMOND_AXE.canHarvestBlock(state);
    }

    @Optional.Method(modid = "tesla")
    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (ARPTeslaUtils.getStoredPower(stack) < outputAxe) return 0.5F;
        return Items.WOODEN_AXE.getStrVsBlock(stack, state) > 1.0F ? 5.5F : super.getStrVsBlock(stack, state);
    }

    @Optional.Method(modid = "tesla")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        createTooltip(stack, tooltip);
    }

    @Optional.Method(modid = "tesla")
    private void createTooltip(ItemStack stack, List<String> tooltip) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(TextFormatting.DARK_AQUA + I18n.format("tooltip.tesla.powerinfo", Long.toString(ARPTeslaUtils.getStoredPower(stack)), Long.toString(ARPTeslaUtils.getMaxCapacity(stack))));
            tooltip.add(TextFormatting.DARK_AQUA + I18n.format("tooltip.tesla.cost.tool", Long.toString(outputAxe)));
        } else
            tooltip.add(I18n.format("tooltip.tesla.showinfo", TextFormatting.DARK_AQUA, keyBindSneak.getDisplayName(), TextFormatting.GRAY));
    }
}
