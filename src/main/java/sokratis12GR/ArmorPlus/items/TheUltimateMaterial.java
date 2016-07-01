package sokratis12GR.ArmorPlus.items;


import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.ArmorPlus;

public class TheUltimateMaterial extends Item {

    public TheUltimateMaterial() {
        setRegistryName("TheUltimateMaterial");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("TheUltimateMaterial");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_ITEMS);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.GREEN + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:TheUltimateMaterial", "inventory"));
    }
}
