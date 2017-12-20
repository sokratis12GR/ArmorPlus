package net.thedragonteam.armorplus.iface;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public interface IModdedItem extends IRarityHelper, IModelHelper, IForgeRegistryEntry<Item> {

    @SideOnly(Side.CLIENT)
    default void initModel(String suffix, String location, int meta, String variantIn) {
        this.initModel(this.getRegistryName(), suffix, location, meta, variantIn);
    }

    @SideOnly(Side.CLIENT)
    default void initModel(String suffix, String location, int meta) {
        this.initModel(suffix, location, meta, "");
    }

    @SideOnly(Side.CLIENT)
    default void initModel(String location, int meta, String variantIn) {
        this.initModel("", location, meta, variantIn);
    }

    @SideOnly(Side.CLIENT)
    default void initModel(String suffix, String location) {
        this.initModel(suffix, location, 0);
    }

    @SideOnly(Side.CLIENT)
    default void initModel(String location, int meta) {
        this.initModel(location, meta, "");
    }

    @SideOnly(Side.CLIENT)
    default void initModel(int meta, String variantIn) {
        this.initModel("", meta, variantIn);
    }

    @SideOnly(Side.CLIENT)
    default void initModel(Object locOrMeta) {
        if (locOrMeta instanceof String) {
            this.initModel((String) locOrMeta, 0);
        } else if (locOrMeta instanceof Integer) {
            this.initModel((Integer) locOrMeta, "");
        }
    }

}
