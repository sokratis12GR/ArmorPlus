package net.thedragonteam.armorplus.items.bows;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

/**
 * Created by Socrates on 4/19/2016.
 */
public class CoalBow extends ItemBow {


    public CoalBow() {
        this.setMaxDamage(59);
        setRegistryName("coal_bow");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("CoalBow");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_WEAPONS);
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.COAL;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.GRAY + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(ArmorPlus.MODID + ":" + "CoalBow", "inventory"));
    }

}
