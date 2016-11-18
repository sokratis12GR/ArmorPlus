/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;

import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class ItemUltimateParts extends Item {

    public ItemUltimateParts() {
        setHasSubtypes(true);
        setRegistryName("the_ultimate_part");
        setUnlocalizedName(setName("the_ultimate_part"));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 0:
                return super.getUnlocalizedName(stack) + "_helmet_right";
            case 1:
                return super.getUnlocalizedName(stack) + "_helmet_middle";
            case 2:
                return super.getUnlocalizedName(stack) + "_helmet_left";

            case 3:
                return super.getUnlocalizedName(stack) + "_chestplate_right";
            case 4:
                return super.getUnlocalizedName(stack) + "_chestplate_middle";
            case 5:
                return super.getUnlocalizedName(stack) + "_chestplate_left";

            case 6:
                return super.getUnlocalizedName(stack) + "_leggings_right";
            case 7:
                return super.getUnlocalizedName(stack) + "_leggings_middle";
            case 8:
                return super.getUnlocalizedName(stack) + "_leggings_left";

            case 9:
                return super.getUnlocalizedName(stack) + "_leggings_right";
            case 10:
                return super.getUnlocalizedName(stack) + "_leggings_middle";
            case 11:
                return super.getUnlocalizedName(stack) + "_leggings_left";

        }
        return super.getUnlocalizedName();
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (int i = 0; i <= 11; i++)
            subItems.add(new ItemStack(itemIn, 1, i));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.getValueByName(ARPConfig.theUltimateArmorItemNameColor) + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName() + "_helmet_right", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(getRegistryName() + "_helmet_middle", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 2, new ModelResourceLocation(getRegistryName() + "_helmet_left", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 3, new ModelResourceLocation(getRegistryName() + "_chestplate_right", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 4, new ModelResourceLocation(getRegistryName() + "_chestplate_middle", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 5, new ModelResourceLocation(getRegistryName() + "_chestplate_left", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 6, new ModelResourceLocation(getRegistryName() + "_leggings_right", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 7, new ModelResourceLocation(getRegistryName() + "_leggings_middle", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 8, new ModelResourceLocation(getRegistryName() + "_leggings_left", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 9, new ModelResourceLocation(getRegistryName() + "_boots_right", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 10, new ModelResourceLocation(getRegistryName() + "_boots_middle", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 11, new ModelResourceLocation(getRegistryName() + "_boots_left", "inventory"));
    }
}
