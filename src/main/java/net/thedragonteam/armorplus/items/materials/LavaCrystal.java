/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModdedItem;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * net.thedragonteam.armorplus.items.materials
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class LavaCrystal extends Item implements IModdedItem {

    private String[] lavaCrystalNames = new String[]{"", "_infused"};

    private int[] burnTime = new int[]{20000, 22000};

    public LavaCrystal() {
        this.setHasSubtypes(true);
        this.setRegistryName("lava_crystal");
        this.setUnlocalizedName(setName("lava_crystal"));
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        this.setMaxDamage(0);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return this.getRarity("LAVA_CRYSTAL", TextFormatting.GOLD, "Lava Crystalic");
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        if (itemStack.getItem() == this) {
            for (int i = 0; i < burnTime.length; i++) {
                if (itemStack.getItemDamage() == i) {
                    return burnTime[i];
                }
            }
        }
        return 0;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedNames(stack, lavaCrystalNames);
    }

    private String getUnlocalizedNames(ItemStack stack, String... names) {
        for (int i = 0; i < names.length; i++) {
            if (stack.getItemDamage() == i) {
                return super.getUnlocalizedName(stack) + names[i];
            }
        }
        return super.getUnlocalizedName();
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return super.getDurabilityForDisplay(stack);
    }

    @Override
    public boolean getShareTag() {
        return super.getShareTag();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (isInCreativeTab(tab)) {
            IntStream.range(0, lavaCrystalNames.length).mapToObj(
                    i -> getItemStack(this, i)
            ).forEachOrdered(subItems::add);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        IntStream.range(0, lavaCrystalNames.length).forEachOrdered(
                i -> this.initModel(lavaCrystalNames[i], "lava", i)
        );
    }
}
