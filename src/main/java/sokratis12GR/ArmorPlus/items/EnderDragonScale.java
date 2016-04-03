package sokratis12GR.ArmorPlus.items;


import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EnderDragonScale extends Item {

    public EnderDragonScale() {
        setRegistryName("EnderDragonScale");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("EndeDragonScale");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
    }

}
