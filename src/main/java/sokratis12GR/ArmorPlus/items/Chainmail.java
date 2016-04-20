package sokratis12GR.ArmorPlus.items;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.ArmorPlus;

/**
 * Created by Socrates on 4/19/2016.
 */
public class Chainmail extends Item {

    public Chainmail() {
        setRegistryName("Chainmail");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("Chainmail");     // Used for localization (en_US.lang)
        GameRegistry.registerItem(this);
        this.setCreativeTab(ArmorPlus.tabArmorPlus);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:Chainmail", "inventory"));
    }

}
