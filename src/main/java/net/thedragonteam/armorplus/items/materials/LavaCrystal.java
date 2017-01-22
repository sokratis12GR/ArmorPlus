/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import static net.thedragonteam.armorplus.util.Utils.setName;

/**
 * net.thedragonteam.armorplus.items.materials
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class LavaCrystal extends Item implements IFuelHandler {

    private final String[] LAVA_CRYSTAL_NAMES = new String[]{
            "_normal", "_charged"
    };

    private final int[] BURN_TIME = new int[]{
            20000, 22000
    };

    public LavaCrystal() {
        this.setHasSubtypes(true);
        GameRegistry.registerFuelHandler(this);
        setRegistryName("lava_crystal");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(setName("lava_crystal"));     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        setMaxDamage(0);
    }

    public int getBurnTime(ItemStack fuel) {
        if (fuel.getItem() == this)
            for (int i = 0; i <= 1; i++)
                if (fuel.getItemDamage() == i)
                    return BURN_TIME[i];
        return 0;
    }

    @Override
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
    public void initModel() {
        if (getRegistryName() != null)
            for (int i = 0; i <= 1; i++)
                ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(getRegistryName() + LAVA_CRYSTAL_NAMES[i], "inventory"));
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (int i = 0; i <= 1; i++)
            subItems.add(new ItemStack(itemIn, 1, i));
    }
}
