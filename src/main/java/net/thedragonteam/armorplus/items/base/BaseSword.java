/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
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

import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseSword extends ItemSword {

    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;
    public String effect;

    public BaseSword(ToolMaterial material, String name, Item repairEasy, Item repairExpert, TextFormatting textFormatting, String effectName) {
        super(material);
        this.itemEasy = repairEasy;
        this.itemExpert = repairExpert;
        this.formatting = textFormatting;
        this.effect = effectName;
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    public BaseSword(ToolMaterial material, String name, ItemStack repairEasy, ItemStack repairExpert, TextFormatting textFormatting, String effectName) {
        this(material, name, repairEasy.getItem(), repairExpert.getItem(), textFormatting, effectName);
    }

    public BaseSword(ToolMaterial material, String name, ItemStack repairEasy, Block repairExpert, TextFormatting textFormatting, String effectName) {
        this(material, name, repairEasy.getItem(), Item.getItemFromBlock(repairExpert), textFormatting, effectName);
    }

    public BaseSword(ToolMaterial material, String name, Item repairEasy, ItemStack repairExpert, TextFormatting textFormatting, String effectName) {
        this(material, name, repairEasy, repairExpert.getItem(), textFormatting, effectName);
    }

    public BaseSword(ToolMaterial material, String name, Item repairEasy, Block repairExpert, TextFormatting textFormatting, String effectName) {
        this(material, name, repairEasy, Item.getItemFromBlock(repairExpert), textFormatting, effectName);
    }

    public BaseSword(ToolMaterial material, String name, Block repairEasy, Block repairExpert, TextFormatting textFormatting, String effectName) {
        this(material, name, Item.getItemFromBlock(repairEasy), Item.getItemFromBlock(repairExpert), textFormatting, effectName);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247r" + effect);
            tooltip.add("\2473Use: " + "\247rHit a Target");
        } else
            tooltip.add(I18n.format("tooltip.tesla.showinfo", formatting, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
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
