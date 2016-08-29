/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

/**
 * net.thedragonteam.armorplus.items.materials
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class ElectricalIngot extends Item {

    public ElectricalIngot() {
        setRegistryName("electrical_ingot");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(ArmorPlus.MODID + "." + "electrical_ingot");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_ITEMS);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
