/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.UltimateItems;

import static net.thedragonteam.armorplus.util.Utils.setARPUnlocalizedName;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseUltimateItem extends Item {

    public BaseUltimateItem(UltimateItems ultimateItems) {
        setRegistryName("the_ultimate_" + ultimateItems.getName());
        setUnlocalizedName(setARPUnlocalizedName("the_ultimate_" + ultimateItems.getName()));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.getValueByName(ARPConfig.theUltimateArmorItemNameColor) + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
