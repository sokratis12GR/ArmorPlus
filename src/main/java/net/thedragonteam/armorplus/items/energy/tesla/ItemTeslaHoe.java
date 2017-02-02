/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.energy.tesla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional.Method;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.energy.tesla.BaseTeslaHoe;
import net.thedragonteam.armorplus.util.APTeslaUtils;

import javax.annotation.Nonnull;
import java.util.List;

import static net.thedragonteam.armorplus.APConfig.*;

public class ItemTeslaHoe extends BaseTeslaHoe {

    public ItemTeslaHoe() {
        super(ToolMaterial.DIAMOND, "tesla_hoe", maxCapacityHoe, inputHoe, outputHoe);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusTesla;
    }

    @Method(modid = "tesla")
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        APTeslaUtils.usePower(stack, outputHoe);
        return true;
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return Items.DIAMOND_HOE.canHarvestBlock(state);
    }

    @Method(modid = "tesla")
    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        return APTeslaUtils.getStoredPower(stack) < outputHoe ? 0.5F : Items.WOODEN_HOE.getStrVsBlock(stack, state) > 1.0F ? 5.5F : super.getStrVsBlock(stack, state);
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
            tooltip.add(TextFormatting.DARK_AQUA + I18n.format("tooltip.tesla.powerinfo", Long.toString(APTeslaUtils.getStoredPower(stack)), Long.toString(APTeslaUtils.getMaxCapacity(stack))));
            tooltip.add(TextFormatting.DARK_AQUA + I18n.format("tooltip.tesla.cost.tool", Long.toString(outputHoe)));
        } else
            tooltip.add(I18n.format("tooltip.tesla.showinfo", TextFormatting.DARK_AQUA, keyBindSneak.getDisplayName(), TextFormatting.GRAY));
    }
}
