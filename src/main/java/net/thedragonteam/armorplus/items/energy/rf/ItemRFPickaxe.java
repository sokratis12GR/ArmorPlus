/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.energy.rf;

import com.google.common.collect.Sets;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.items.base.energy.rf.BaseRFPickaxe;

import java.util.List;
import java.util.Set;

import static net.thedragonteam.armorplus.ARPConfig.*;

public class ItemRFPickaxe extends BaseRFPickaxe {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE);

    public ItemRFPickaxe() {
        super(ToolMaterial.DIAMOND, "redstone_flux_pickaxe", EFFECTIVE_ON, maxCapacityPickaxe, inputPickaxe, outputPickaxe);
        setMaxStackSize(1);
        canRepair = false;
        setMaxDamage(0);
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
        this.extractEnergy(stack, outputPickaxe, false);
        return true;
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
        return Items.DIAMOND_PICKAXE.canHarvestBlock(state);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (this.getEnergyStored(stack) < outputPickaxe) return 0.5F;
        return Items.WOODEN_PICKAXE.getStrVsBlock(stack, state) > 1.0F ? 5.5F : super.getStrVsBlock(stack, state);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        createTooltip(stack, tooltip);
    }

    private int createPoweredStack(ItemStack container, boolean simulate) {
        if ((container.getTagCompound() == null) || (!container.getTagCompound().hasKey("Energy"))) return 0;
        if (!simulate) container.getTagCompound().setInteger("Energy", maxCapacityPickaxe);

        return maxCapacityPickaxe;
    }

    private void createTooltip(ItemStack stack, List<String> tooltip) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add(TextFormatting.DARK_RED + I18n.format("") + this.getEnergyStored(stack) + "/" + this.getMaxEnergyStored(stack) + I18n.format(" RF"));
            tooltip.add(TextFormatting.DARK_RED + I18n.format("tooltip.rf.cost.tool", Integer.toString(this.getMaxReceive(stack))));
        } else
            tooltip.add(I18n.format("tooltip.rf.showinfo", TextFormatting.DARK_RED, keyBindSneak.getDisplayName(), TextFormatting.GRAY));
    }
}