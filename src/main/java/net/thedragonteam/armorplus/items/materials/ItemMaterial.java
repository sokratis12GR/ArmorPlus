/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class ItemMaterial extends Item implements IModelHelper {

    private String[] MATERIAL_NAMES = new String[]{"_chainmail", "_guardian_scale", "_wither_bone", "_ender_dragon_scale", "_the_ultimate_material"};

    public ItemMaterial() {
        this.setRegistryName("material");
        this.setUnlocalizedName(setName("material"));
        this.setHasSubtypes(true);
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    //0 = Chainmail
    //1 = Guardian Scale
    //2 = Wither Bone
    //3 = Ender Dragon Scale
    //4 = The Ultimate Material
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < MATERIAL_NAMES.length; i++)
            if (stack.getItemDamage() == i) return super.getUnlocalizedName(stack) + MATERIAL_NAMES[i];
        return super.getUnlocalizedName(stack);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (int i = 0; i < MATERIAL_NAMES.length; i++)
            subItems.add(new ItemStack(this, 1, i));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        for (int i = 0; i < MATERIAL_NAMES.length; i++)
            this.initModel(this, getRegistryName() + MATERIAL_NAMES[i], i);
    }
}
