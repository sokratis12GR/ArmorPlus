/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.ArmorPlus;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class ItemMaterial extends Item {
    public ItemMaterial() {
        setRegistryName("material");
        setUnlocalizedName(setName("material"));
        setHasSubtypes(true);
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        switch (stack.getMetadata()) {
            //0 = Chainmail
            case 0:
                return super.getUnlocalizedName(stack) + "_chainmail";
            //1 = Guardian Scale
            case 1:
                return super.getUnlocalizedName(stack) + "_guardian_scale";
            //2 = Wither Bone
            case 2:
                return super.getUnlocalizedName(stack) + "_wither_bone";
            //3 = Ender Dragon Scale
            case 3:
                return super.getUnlocalizedName(stack) + "_ender_dragon_scale";
            //4 = The Ultimate Material
            case 4:
                return super.getUnlocalizedName(stack) + "_the_ultimate_material";
        }
        return super.getUnlocalizedName(stack);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (int i = 0; i <= 4; i++)
            subItems.add(new ItemStack(itemIn, 1, i));
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName() + "_chainmail", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(getRegistryName() + "_guardian_scale", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 2, new ModelResourceLocation(getRegistryName() + "_wither_bone", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 3, new ModelResourceLocation(getRegistryName() + "_ender_dragon_scale", "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 4, new ModelResourceLocation(getRegistryName() + "_the_ultimate_material", "inventory"));
    }
}
