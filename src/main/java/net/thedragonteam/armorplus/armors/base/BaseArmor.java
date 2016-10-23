/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;

public class BaseArmor extends ItemArmor {

    public Item itemEasy;
    public Item itemExpert;

    public BaseArmor(ArmorMaterial armorMaterial, int armorPreffix, EntityEquipmentSlot slot, String name, Item repairEasy, Item repairExpert) {
        super(armorMaterial, armorPreffix, slot);
        this.itemEasy = repairEasy;
        this.itemExpert = repairExpert;
        setMaxStackSize(1);
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.tabArmorplus);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        if (ARPConfig.recipes == 0) {
            return repair.getItem() == itemEasy;
        }
        if (ARPConfig.recipes == 1) {
            return repair.getItem() == itemExpert;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
