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

import static java.lang.String.format;
import static net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation;
import static net.thedragonteam.armorplus.ArmorPlus.MODID;
import static net.thedragonteam.armorplus.util.Utils.isNotNullNorEmpty;
import static net.thedragonteam.armorplus.util.Utils.isNullOrEmpty;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItem;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public interface IModelHelper {

    @SideOnly(Side.CLIENT)
    default void initModel(ResourceLocation registryName, String suffix, String location, int meta, String variantIn) {
        String resourcePath = (isNotNullNorEmpty(location))
            ? (location + "/" + registryName.getResourcePath() + suffix)
            : registryName.getResourcePath() + suffix;
        String resourceLocation = format("%s:%s", MODID, resourcePath);
        if (isNullOrEmpty(variantIn)) variantIn = "inventory";
        ModelResourceLocation mrl = new ModelResourceLocation(resourceLocation, variantIn);
        if (this instanceof Block) {
            setCustomModelResourceLocation(getItem(this), meta, mrl);
        } else if (this instanceof Item) {
            setCustomModelResourceLocation((Item) this, meta, mrl);
        }
    }

    @SideOnly(Side.CLIENT)
    default void initModel(ResourceLocation registryName, String suffix, String location, int meta) {
        this.initModel(registryName, suffix, location, meta, "");
    }

    @SideOnly(Side.CLIENT)
    default void initModel(ResourceLocation registryName, String location, int meta, String variantIn) {
        this.initModel(registryName, "", location, meta, variantIn);
    }

    @SideOnly(Side.CLIENT)
    default void initModel(ResourceLocation registryName, String suffix, String location) {
        this.initModel(registryName, suffix, location, 0);
    }

    @SideOnly(Side.CLIENT)
    default void initModel(ResourceLocation registryName, String location, int meta) {
        this.initModel(registryName, location, meta, "");
    }

    @SideOnly(Side.CLIENT)
    default void initModel(ResourceLocation registryName, int meta, String variantIn) {
        this.initModel(registryName, "", meta, variantIn);
    }

    @SideOnly(Side.CLIENT)
    default void initModel(ResourceLocation registryName, Object locOrMeta) {
        if (locOrMeta instanceof Integer) {
            this.initModel(registryName, (Integer) locOrMeta, "");
        } else if (locOrMeta instanceof String) {
            this.initModel(registryName, (String) locOrMeta, 0);
        }
    }

    @SideOnly(Side.CLIENT)
    void initModel();
}
