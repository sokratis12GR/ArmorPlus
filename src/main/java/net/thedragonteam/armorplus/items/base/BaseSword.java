/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseSword extends ItemSword {

    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;

    public BaseSword(ToolMaterial material, String name, Item repairEasy, Item repairExpert, TextFormatting textFormatting) {
        super(material);
        this.itemEasy = repairEasy;
        this.itemExpert = repairExpert;
        this.formatting = textFormatting;
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    public BaseSword(ToolMaterial material, String name, ItemStack repairEasy, ItemStack repairExpert, TextFormatting textFormatting) {
        this(material, name, repairEasy.getItem(), repairExpert.getItem(), textFormatting);
    }

    public BaseSword(ToolMaterial material, String name, ItemStack repairEasy, Block repairExpert, TextFormatting textFormatting) {
        this(material, name, repairEasy.getItem(), Item.getItemFromBlock(repairExpert), textFormatting);
    }

    public BaseSword(ToolMaterial material, String name, Item repairEasy, ItemStack repairExpert, TextFormatting textFormatting) {
        this(material, name, repairEasy, repairExpert.getItem(), textFormatting);
    }

    public BaseSword(ToolMaterial material, String name, Item repairEasy, Block repairExpert, TextFormatting textFormatting) {
        this(material, name, repairEasy, Item.getItemFromBlock(repairExpert), textFormatting);
    }

    public BaseSword(ToolMaterial material, String name, Block repairEasy, Block repairExpert, TextFormatting textFormatting) {
        this(material, name, Item.getItemFromBlock(repairEasy), Item.getItemFromBlock(repairExpert), textFormatting);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (formatting + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
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
