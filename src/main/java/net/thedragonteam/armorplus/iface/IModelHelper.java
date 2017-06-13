/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.iface;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

import static net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItem;

public interface IModelHelper {

    @SideOnly(Side.CLIENT)
    default void initModel(Object object, String registryName, int meta) {
        if (object instanceof Block) {
            setCustomModelResourceLocation(getItem((Block) object), meta, new ModelResourceLocation(registryName, "inventory"));
        } else if (object instanceof Item) {
            setCustomModelResourceLocation((Item) object, meta, new ModelResourceLocation(registryName, "inventory"));
        }
    }

    @SideOnly(Side.CLIENT)
    default void initModel(Object object, String registryName, int meta, String variantIn) {
        if (variantIn == null || Objects.equals(variantIn, "")) variantIn = "inventory";
        if (object instanceof Block) {
            setCustomModelResourceLocation(getItem((Block) object), meta, new ModelResourceLocation(registryName, variantIn));
        } else if (object instanceof Item) {
            setCustomModelResourceLocation((Item) object, meta, new ModelResourceLocation(registryName, variantIn));
        }
    }

    @SideOnly(Side.CLIENT)
    default void initModel(Object object, ResourceLocation registryName, int meta) {
        if (object instanceof Block) {
            setCustomModelResourceLocation(getItem((Block) object), meta, new ModelResourceLocation(registryName, "inventory"));
        } else if (object instanceof Item) {
            setCustomModelResourceLocation((Item) object, meta, new ModelResourceLocation(registryName, "inventory"));
        }
    }

    @SideOnly(Side.CLIENT)
    default void initModel(Object object, ResourceLocation registryName, int meta, String variantIn) {
        if (variantIn == null || Objects.equals(variantIn, "")) variantIn = "inventory";
        if (object instanceof Block) {
            setCustomModelResourceLocation(getItem((Block) object), meta, new ModelResourceLocation(registryName, variantIn));
        } else if (object instanceof Item) {
            setCustomModelResourceLocation((Item) object, meta, new ModelResourceLocation(registryName, variantIn));
        }
    }

    @SideOnly(Side.CLIENT)
    void initModel();
}
