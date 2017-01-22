/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class ItemMaterial extends Item {

    private final String[] MATERIAL_NAMES = new String[]{
            "_chainmail", "_guardian_scale", "_wither_bone",
            "_ender_dragon_scale", "_the_ultimate_material"
    };

    public ItemMaterial() {
        setRegistryName("material");
        setUnlocalizedName(setName("material"));
        setHasSubtypes(true);
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    //0 = Chainmail
    //1 = Guardian Scale
    //2 = Wither Bone
    //3 = Ender Dragon Scale
    //4 = The Ultimate Material
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i <= 4; i++)
            if (stack.getItemDamage() == i) return super.getUnlocalizedName(stack) + MATERIAL_NAMES[i];
        return super.getUnlocalizedName(stack);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (int i = 0; i <= 4; i++)
            subItems.add(new ItemStack(itemIn, 1, i));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        if (getRegistryName() != null)
            for (int i = 0; i <= 4; i++)
                ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(getRegistryName() + MATERIAL_NAMES[i], "inventory"));
    }
}
