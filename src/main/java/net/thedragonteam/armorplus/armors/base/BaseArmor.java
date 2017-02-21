/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.APArmorMaterial;
import net.thedragonteam.armorplus.registry.ModPotions;

import java.util.List;

import static net.minecraft.init.SoundEvents.*;
import static net.minecraftforge.common.util.EnumHelper.addArmorMaterial;
import static net.minecraftforge.common.util.EnumHelper.addRarity;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.getArmorPlusLocation;
import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.isItemRepairable;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.armorplus.util.Utils.setName;

public class BaseArmor extends ItemArmor {

    public static ArmorMaterial coalArmor = addArmorMaterial("coal", getArmorPlusLocation("coal_armor"), 7,
            coalArmorProtectionPoints, 8, ITEM_ARMOR_EQUIP_LEATHER, (float) coalArmorToughnessPoints);
    public static ArmorMaterial emeraldArmor = addArmorMaterial("emerald", getArmorPlusLocation("emerald_armor"), 35,
            emeraldArmorProtectionPoints, 20, ITEM_ARMOR_EQUIP_DIAMOND, (float) emeraldArmorToughnessPoints);
    public static ArmorMaterial lapisArmor = addArmorMaterial("lapis", getArmorPlusLocation("lapis_armor"), 11,
            lapisArmorProtectionPoints, 25, ITEM_ARMOR_EQUIP_GOLD, (float) lapisArmorToughnessPoints);
    public static ArmorMaterial lavaArmor = addArmorMaterial("lava", getArmorPlusLocation("lava_armor"), 45,
            lavaArmorProtectionPoints, 28, ITEM_ARMOR_EQUIP_DIAMOND, (float) lavaArmorToughnessPoints);
    public static ArmorMaterial obsidianArmor = addArmorMaterial("obsidian", getArmorPlusLocation("obsidian_armor"), 40,
            obsidianArmorProtectionPoints, 25, ITEM_ARMOR_EQUIP_DIAMOND, (float) obsidianArmorToughnessPoints);
    public static ArmorMaterial redstoneArmor = addArmorMaterial("redstone", getArmorPlusLocation("redstone_armor"), 11,
            redstoneArmorProtectionPoints, 25, ITEM_ARMOR_EQUIP_GOLD, (float) redstoneArmorToughnessPoints);
    public static ArmorMaterial chickenArmor = addArmorMaterial("chicken", getArmorPlusLocation("chicken_armor"), 3,
            chickenArmorProtectionPoints, 10, ITEM_ARMOR_EQUIP_LEATHER, (float) chickenArmorToughnessPoints);
    public static ArmorMaterial slimeArmor = addArmorMaterial("slime", getArmorPlusLocation("slime_armor"), 3,
            slimeArmorProtectionPoints, 10, ITEM_ARMOR_EQUIP_LEATHER, (float) slimeArmorToughnessPoints);
    public static ArmorMaterial enderDragonArmor = addArmorMaterial("enderDragon", getArmorPlusLocation("ender_dragon_armor"), 60,
            enderDragonArmorProtectionPoints, 30, ITEM_ARMOR_EQUIP_DIAMOND, (float) enderDragonArmorToughnessPoints);
    public static ArmorMaterial guardianArmor = addArmorMaterial("guardian", getArmorPlusLocation("guardian_armor"), 50,
            guardianArmorProtectionPoints, 28, ITEM_ARMOR_EQUIP_DIAMOND, (float) guardianArmorToughnessPoints);
    public static ArmorMaterial superStarArmor = addArmorMaterial("superStar", getArmorPlusLocation("super_star_armor"), 50,
            superStarArmorProtectionPoints, 30, ITEM_ARMOR_EQUIP_DIAMOND, (float) superStarArmorToughnessPoints);
    public static ArmorMaterial arditeArmor = addArmorMaterial("ardite", getArmorPlusLocation("ardite_armor"), 55,
            arditeArmorProtectionPoints, 30, ITEM_ARMOR_EQUIP_DIAMOND, (float) arditeArmorToughnessPoints);
    public static ArmorMaterial cobaltArmor = addArmorMaterial("cobalt", getArmorPlusLocation("cobalt_armor"), 44,
            cobaltArmorProtectionPoints, 30, ITEM_ARMOR_EQUIP_DIAMOND, (float) cobaltArmorToughnessPoints);
    public static ArmorMaterial knightSlimeArmor = addArmorMaterial("knightSlime", getArmorPlusLocation("knight_slime_armor"), 33,
            knightSlimeArmorProtectionPoints, 10, ITEM_ARMOR_EQUIP_DIAMOND, (float) knightSlimeArmorToughnessPoints);
    public static ArmorMaterial manyullynArmor = addArmorMaterial("manyullyn", getArmorPlusLocation("manyullyn_armor"), 66,
            manyullynArmorProtectionPoints, 30, ITEM_ARMOR_EQUIP_DIAMOND, (float) manyullynArmorToughnessPoints);
    public static ArmorMaterial pigIronArmor = addArmorMaterial("pigIron", getArmorPlusLocation("pig_iron_armor"), 33,
            pigIronArmorProtectionPoints, 10, ITEM_ARMOR_EQUIP_DIAMOND, (float) pigIronArmorToughnessPoints);
    public EnumRarity formattingName;
    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;
    private APArmorMaterial material;
    private EntityEquipmentSlot slot;

    public BaseArmor(APArmorMaterial armorMaterial, EntityEquipmentSlot slot) {
        super(armorMaterial.getArmorMaterial(), 0, slot);
        this.itemEasy = armorMaterial.getItemEasy();
        this.itemExpert = armorMaterial.getItemExpert();
        this.formatting = armorMaterial.getFormatting();
        this.material = armorMaterial;
        this.slot = slot;
        this.setMaxStackSize(1);
        switch (slot) {
            case FEET:
                String boots = armorMaterial.getName() + "_boots";
                setRegistryName(boots);
                setUnlocalizedName(setName(boots));
                break;
            case LEGS:
                String leggings = armorMaterial.getName() + "_leggings";
                setRegistryName(leggings);
                setUnlocalizedName(setName(leggings));
                break;
            case CHEST:
                String chestplate = armorMaterial.getName() + "_chestplate";
                setRegistryName(chestplate);
                setUnlocalizedName(setName(chestplate));
                break;
            case HEAD:
                String helmet = armorMaterial.getName() + "_helmet";
                setRegistryName(helmet);
                setUnlocalizedName(setName(helmet));
                break;
        }
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplus);
        this.formattingName = addRarity("ARMOR_COLOR", formatting, "Armor Color");

    }

    public static void addAbilities(EntityPlayer entity, boolean isEnabled, boolean isFullArmorSet, Potion addPotion, int potionAmplifier, Potion removePotion) {
        if (isEnabled && !isFullArmorSet && entity.getActivePotionEffect(addPotion) == null) {
            addPotion(entity, addPotion, potionAmplifier, GOOD);
            if (removePotion != null && removePotion != ModPotions.EMPTY)
                removePotion(entity, removePotion);
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        material.onArmorTick(world, entity, itemStack);
        addEffects(entity);
    }

    private void addEffects(EntityPlayer entity) {
        switch (slot) {
            case FEET:
                addAbilities(entity, material.getAreEffectsEnabled()[0], material.enableFullArmorEffect(), getPotion(material.getAddPotionEffect()), material.getAddPotionEffectAmplifier(), getPotion(material.getRemovePotionEffect()));
                break;
            case LEGS:
                addAbilities(entity, material.getAreEffectsEnabled()[1], material.enableFullArmorEffect(), getPotion(material.getAddPotionEffect()), material.getAddPotionEffectAmplifier(), getPotion(material.getRemovePotionEffect()));
                break;
            case CHEST:
                addAbilities(entity, material.getAreEffectsEnabled()[2], material.enableFullArmorEffect(), getPotion(material.getAddPotionEffect()), material.getAddPotionEffectAmplifier(), getPotion(material.getRemovePotionEffect()));
                break;
            case HEAD:
                addAbilities(entity, material.getAreEffectsEnabled()[3], material.enableFullArmorEffect(), getPotion(material.getAddPotionEffect()), material.getAddPotionEffectAmplifier(), getPotion(material.getRemovePotionEffect()));
                break;
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return this.formattingName;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return isItemRepairable(repair, itemEasy, itemExpert);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        material.addInformation(stack, playerIn, tooltip, advanced);
    }


    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
