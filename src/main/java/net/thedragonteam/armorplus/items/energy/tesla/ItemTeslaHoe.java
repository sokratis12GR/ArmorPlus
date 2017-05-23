/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.energy.tesla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.energy.tesla.BaseTeslaHoe;
import net.thedragonteam.armorplus.util.APTeslaUtils;

import javax.annotation.Nonnull;
import java.util.List;

import static net.minecraft.util.text.TextFormatting.DARK_AQUA;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.util.APTeslaUtils.getMaxCapacity;
import static net.thedragonteam.armorplus.util.APTeslaUtils.getStoredPower;
import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;
import static net.thedragonteam.thedragonlib.util.TextUtils.INSTANCE;

public class ItemTeslaHoe extends BaseTeslaHoe {

    public ItemTeslaHoe() {
        super(ToolMaterial.DIAMOND, "tesla_hoe", maxEnergyCapacity[4], energyInput[4], energyOutput[4]);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusTesla;
    }

    @Method(modid = "tesla")
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        APTeslaUtils.usePower(stack, energyOutput[4]);
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
        return getStoredPower(stack) < energyOutput[4] ? 0.5F : Items.WOODEN_HOE.getStrVsBlock(stack, state) > 1.0F ? 5.5F : super.getStrVsBlock(stack, state);
    }

    @Method(modid = "tesla")
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        createTooltip(stack, tooltip);
    }

    @Method(modid = "tesla")
    @SideOnly(Side.CLIENT)
    private void createTooltip(ItemStack stack, List<String> tooltip) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(INSTANCE.formattedText(DARK_AQUA, "tooltip.tesla.powerinfo", Long.toString(getStoredPower(stack)), Long.toString(getMaxCapacity(stack))));
            tooltip.add(INSTANCE.formattedText(DARK_AQUA, "tooltip.tesla.cost.tool", Long.toString(energyOutput[4])));
        } else {
            showInfo(tooltip, keyBindSneak, DARK_AQUA);
        }
    }
}
