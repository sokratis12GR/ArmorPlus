/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.energy.rf;

import com.google.common.collect.Sets;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.items.base.energy.rf.BaseRFHoe;

import java.util.List;
import java.util.Set;

import static net.thedragonteam.armorplus.ARPConfig.*;

public class ItemRFHoe extends BaseRFHoe {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL);

    public ItemRFHoe() {
        super(ToolMaterial.DIAMOND, "redstone_flux_hoe", EFFECTIVE_ON, maxCapacityHoe, inputHoe, outputHoe);
        setMaxStackSize(1);
        canRepair = false;
        setMaxDamage(0);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean showDurabilityBar(ItemStack stack) {
        return this.getEnergyStored(stack) < this.getMaxEnergyStored(stack);
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return 1 - ((double) this.getEnergyStored(stack) / (double) this.getMaxEnergyStored(stack));
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        this.extractEnergy(stack, outputHoe, false);
        return true;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        this.extractEnergy(stack, outputHoe, false);
        return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }


    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        ItemStack powered = new ItemStack(itemIn, 1, 1);
        ItemStack unpowered = new ItemStack(itemIn);
        poweredItem(powered);
        subItems.add(powered);
        createPoweredStack(powered, false);
        subItems.add(unpowered);
    }

    private int poweredItem(ItemStack itemStack) {
        return receiveEnergy(itemStack, getMaxEnergyStored(itemStack), false);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (stack.getItemDamage() == 1)
            createPoweredStack(stack, false);
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return this.capacity;
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state) {
        return Items.DIAMOND_HOE.canHarvestBlock(state);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (this.getEnergyStored(stack) < outputHoe) return 0.5F;
        return Items.WOODEN_HOE.getStrVsBlock(stack, state) > 1.0F ? 5.5F : super.getStrVsBlock(stack, state);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        createTooltip(stack, tooltip);
    }

    private int createPoweredStack(ItemStack container, boolean simulate) {
        if ((container.getTagCompound() == null) || (!container.getTagCompound().hasKey("Energy"))) return 0;
        if (!simulate) container.getTagCompound().setInteger("Energy", maxCapacityHoe);

        return maxCapacityHoe;
    }

    private void createTooltip(ItemStack stack, List<String> tooltip) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(ChatFormatting.DARK_RED + I18n.format("") + this.getEnergyStored(stack) + "/" + this.getMaxEnergyStored(stack) + I18n.format(" RF"));
            tooltip.add(ChatFormatting.DARK_RED + I18n.format("tooltip.rf.cost.tool", Integer.toString(this.getMaxReceive(stack))));
        } else
            tooltip.add(I18n.format("tooltip.rf.showinfo", ChatFormatting.DARK_RED, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }
}