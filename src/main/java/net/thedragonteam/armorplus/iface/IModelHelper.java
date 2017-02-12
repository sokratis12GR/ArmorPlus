/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.iface;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelHelper {

    @SideOnly(Side.CLIENT)
    default void initModel(Block block, ResourceLocation registryName, int meta) {
        initModel(Item.getItemFromBlock(block), registryName.toString(), meta);
    }

    @SideOnly(Side.CLIENT)
    default void initModel(Block block, String registryName, int meta) {
        initModel(Item.getItemFromBlock(block), registryName, meta);
    }

    @SideOnly(Side.CLIENT)
    default void initModel(Item item, ResourceLocation registryName, int meta) {
        initModel(item, registryName.toString(), meta);
    }

    @SideOnly(Side.CLIENT)
    default void initModel(Item item, String registryName, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(registryName, "inventory"));
    }
}
