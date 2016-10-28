/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseArmor extends ItemArmor {

    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;

    public BaseArmor(ArmorMaterial armorMaterial, int armorPreffix, EntityEquipmentSlot slot, String name, Item repairEasy, Item repairExpert, TextFormatting textFormatting) {
        super(armorMaterial, armorPreffix, slot);
        this.itemEasy = repairEasy;
        this.itemExpert = repairExpert;
        this.formatting = textFormatting;
        setMaxStackSize(1);
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.tabArmorplus);
    }

    public BaseArmor(ArmorMaterial armorMaterial, int armorPreffix, EntityEquipmentSlot slot, String name, ItemStack repairEasy, ItemStack repairExpert, TextFormatting textFormatting) {
        this(armorMaterial, armorPreffix, slot, name, repairEasy.getItem(), repairExpert.getItem(), textFormatting);
    }

    public BaseArmor(ArmorMaterial armorMaterial, int armorPreffix, EntityEquipmentSlot slot, String name, ItemStack repairEasy, Block repairExpert, TextFormatting textFormatting) {
        this(armorMaterial, armorPreffix, slot, name, repairEasy.getItem(), Item.getItemFromBlock(repairExpert), textFormatting);
    }

    public BaseArmor(ArmorMaterial armorMaterial, int armorPreffix, EntityEquipmentSlot slot, String name, Item repairEasy, ItemStack repairExpert, TextFormatting textFormatting) {
        this(armorMaterial, armorPreffix, slot, name, repairEasy, repairExpert.getItem(), textFormatting);
    }

    public BaseArmor(ArmorMaterial armorMaterial, int armorPreffix, EntityEquipmentSlot slot, String name, Item repairEasy, Block repairExpert, TextFormatting textFormatting) {
        this(armorMaterial, armorPreffix, slot, name, repairEasy, Item.getItemFromBlock(repairExpert), textFormatting);
    }

    public BaseArmor(ArmorMaterial armorMaterial, int armorPreffix, EntityEquipmentSlot slot, String name, Block repairEasy, Block repairExpert, TextFormatting textFormatting) {
        this(armorMaterial, armorPreffix, slot, name, Item.getItemFromBlock(repairEasy), Item.getItemFromBlock(repairExpert), textFormatting);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (formatting + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        switch (ARPConfig.recipes) {
            case 0:
                return repair.getItem() == itemEasy;
            case 1:
                return repair.getItem() == itemExpert;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
