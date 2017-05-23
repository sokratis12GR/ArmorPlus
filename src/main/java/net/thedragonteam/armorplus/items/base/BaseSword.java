/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.util.ArmorPlusItemUtils;
import net.thedragonteam.armorplus.util.Utils;

import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;

public class BaseSword extends ItemSword implements IModelHelper {

    public ItemStack itemEasy;
    public ItemStack itemExpert;
    public TextFormatting formatting;
    public EnumRarity formattingName;
    public String itemName;

    public BaseSword(ToolMaterial material, String name, ItemStack repairEasy, ItemStack repairExpert, String textFormatting) {
        super(material);
        this.itemName = name;
        this.itemEasy = repairEasy;
        this.itemExpert = repairExpert;
        this.formatting = TextFormatting.getValueByName(textFormatting);
        this.setRegistryName(name);
        this.setUnlocalizedName(Utils.INSTANCE.setName(name));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        this.formattingName = addRarity("SWORD", formatting, "Sword");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return formattingName;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ArmorPlusItemUtils.INSTANCE.isItemRepairable(repair, itemEasy, itemExpert);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        initModel(this, getRegistryName(), 0);
    }
}