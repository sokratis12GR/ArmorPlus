package sokratis12GR.ArmorPlus.items;


import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.ArmorPlus;

public class EnderDragonScale extends Item {

    public EnderDragonScale() {
        setRegistryName("enderDragonScale");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("enderDragonScale");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorPlus);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:enderDragonScale", "inventory"));
    }

}
