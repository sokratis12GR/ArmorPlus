/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.energy.tesla;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.energy.tesla.BaseTeslaHoe;
import net.thedragonteam.armorplus.util.ARPTeslaUtils;

import java.util.List;

import static net.thedragonteam.armorplus.ARPConfig.*;

public class ItemTeslaHoe extends BaseTeslaHoe {

    public ItemTeslaHoe() {
        super(ToolMaterial.DIAMOND, "tesla_hoe", maxCapacityHoe, inputHoe, outputHoe);
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ArmorPlus.tabArmorplusTesla;
    }

    @Optional.Method(modid = "tesla")
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        ARPTeslaUtils.usePower(stack, outputHoe);
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return Items.DIAMOND_HOE.canHarvestBlock(state);
    }

    @Optional.Method(modid = "tesla")
    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (ARPTeslaUtils.getStoredPower(stack) < outputHoe) {
            return 0.5F;
        }
        if (Items.WOODEN_HOE.getStrVsBlock(stack, state) > 1.0F) {
            return 5.5F;
        } else {
            return super.getStrVsBlock(stack, state);
        }
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
            tooltip.add(ChatFormatting.DARK_AQUA + I18n.format("tooltip.tesla.powerinfo", Long.toString(ARPTeslaUtils.getStoredPower(stack)), Long.toString(ARPTeslaUtils.getMaxCapacity(stack))));
            tooltip.add(ChatFormatting.DARK_AQUA + I18n.format("tooltip.tesla.cost.tool", Long.toString(outputHoe)));
        } else
            tooltip.add(I18n.format("tooltip.tesla.showinfo", ChatFormatting.DARK_AQUA, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }
}
