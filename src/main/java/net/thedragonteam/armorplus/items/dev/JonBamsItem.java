/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.dev;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.List;

public class JonBamsItem extends Item {

    public JonBamsItem() {
        this.setHasSubtypes(true);
        setRegistryName("jon_bams");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(ArmorPlus.MODID + "." + "jon_bams");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        setMaxDamage(0);
    }

    @Override
    public Item setCreativeTab(CreativeTabs tab) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName() + "_second", "inventory"));
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }
}
