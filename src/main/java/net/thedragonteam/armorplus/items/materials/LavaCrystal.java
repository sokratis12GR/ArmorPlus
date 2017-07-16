/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;

import javax.annotation.Nonnull;

import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.util.Utils.setName;

/**
 * net.thedragonteam.armorplus.items.materials
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class LavaCrystal extends Item implements IFuelHandler, IModelHelper {

    private String[] LAVA_CRYSTAL_NAMES = new String[]{"", "_infused"};

    private int[] BURN_TIME = new int[]{20000, 22000};

    public LavaCrystal() {
        this.setHasSubtypes(true);
        GameRegistry.registerFuelHandler(this);
        this.setRegistryName("lava_crystal");      // The unique name (within your mod) that identifies this item
        this.setUnlocalizedName(setName("lava_crystal"));   // Used for localization (en_US.lang)
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        this.setMaxDamage(0);
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.getItem() == this)
            for (int i = 0; i < LAVA_CRYSTAL_NAMES.length; i++)
                if (fuel.getItemDamage() == i)
                    return BURN_TIME[i];
        return 0;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i <= 1; i++)
            if (stack.getItemDamage() == i)
                return super.getUnlocalizedName(stack) + LAVA_CRYSTAL_NAMES[i];
        return super.getUnlocalizedName(stack);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return super.getDurabilityForDisplay(stack);
    }

    @Override
    public boolean getShareTag() {
        return super.getShareTag();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        IntStream.range(0, LAVA_CRYSTAL_NAMES.length).forEachOrdered(i -> this.initModel(getRegistryName(), LAVA_CRYSTAL_NAMES[i], "lava", i));
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (isInCreativeTab(tab)) {
            IntStream.range(0, LAVA_CRYSTAL_NAMES.length).mapToObj(i -> new ItemStack(this, 1, i)).forEachOrdered(subItems::add);
        }
    }
}
