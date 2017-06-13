/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.APArmorMaterial;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.registry.ModPotions;
import net.thedragonteam.armorplus.util.EnumTiers;
import net.thedragonteam.armorplus.util.PotionUtils;

import javax.annotation.Nullable;
import java.util.List;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.isItemRepairable;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.*;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.getPotion;
import static net.thedragonteam.armorplus.util.Utils.setLocation;
import static net.thedragonteam.armorplus.util.Utils.setName;

public class ItemArmorBase extends ItemArmor implements IModelHelper {

    public static ArmorMaterial coalArmor = addArmorMaterial("COAL", setLocation("coal_armor"), 2,
            coalArmorProtectionPoints, coalArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial emeraldArmor = addArmorMaterial("EMERALD", setLocation("emerald_armor"), 35,
            emeraldArmorProtectionPoints, emeraldArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial lapisArmor = addArmorMaterial("LAPIS", setLocation("lapis_armor"), 11,
            lapisArmorProtectionPoints, lapisArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial lavaArmor = addArmorMaterial("LAVA", setLocation("lava_armor"), 45,
            lavaArmorProtectionPoints, lavaArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial obsidianArmor = addArmorMaterial("OBSIDIAN", setLocation("obsidian_armor"), 40,
            obsidianArmorProtectionPoints, obsidianArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial redstoneArmor = addArmorMaterial("REDSTONE", setLocation("redstone_armor"), 11,
            redstoneArmorProtectionPoints, redstoneArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial chickenArmor = addArmorMaterial("CHICKEN", setLocation("chicken_armor"), 1,
            chickenArmorProtectionPoints, chickenArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial slimeArmor = addArmorMaterial("SLIME", setLocation("slime_armor"), 1,
            slimeArmorProtectionPoints, slimeArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial arditeArmor = addArmorMaterial("ARDITE", setLocation("ardite_armor"), 55,
            arditeArmorProtectionPoints, arditeArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial cobaltArmor = addArmorMaterial("COBALT", setLocation("cobalt_armor"), 44,
            cobaltArmorProtectionPoints, cobaltArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial knightSlimeArmor = addArmorMaterial("KNIGHT_SLIME", setLocation("knight_slime_armor"), 33,
            knightSlimeArmorProtectionPoints, knightSlimeArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial manyullynArmor = addArmorMaterial("MANYULLYN", setLocation("manyullyn_armor"), 66,
            manyullynArmorProtectionPoints, manyullynArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial pigIronArmor = addArmorMaterial("PIG_IRON", setLocation("pig_iron_armor"), 33,
            pigIronArmorProtectionPoints, pigIronArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial enderDragonArmor = addArmorMaterial("ENDER_DRAGON", setLocation("ender_dragon_armor"), 60,
            enderDragonArmorProtectionPoints, enderDragonArmorToughnessPoints, EnumTiers.TIER_3);
    public static ArmorMaterial guardianArmor = addArmorMaterial("GUARDIAN", setLocation("guardian_armor"), 50,
            guardianArmorProtectionPoints, guardianArmorToughnessPoints, EnumTiers.TIER_3);
    public static ArmorMaterial superStarArmor = addArmorMaterial("SUPER_STAR", setLocation("super_star_armor"), 50,
            superStarArmorProtectionPoints, superStarArmorToughnessPoints, EnumTiers.TIER_3);
    private EnumAction wear = addAction("WEAR");

    private APArmorMaterial material;
    private EnumRarity formattingName;
    private ItemStack itemEasy;
    private ItemStack itemExpert;
    private TextFormatting formatting;
    private EntityEquipmentSlot slot;

    public ItemArmorBase(APArmorMaterial material, EntityEquipmentSlot slot) {
        super(material.getArmorMaterial(), 0, slot);
        this.material = material;
        this.itemEasy = material.getItemEasy();
        this.itemExpert = material.getItemExpert();
        this.formatting = material.getFormatting();
        this.slot = slot;
        this.setMaxStackSize(1);
        switch (slot) {
            case FEET:
                String boots = material.getName() + "_boots";
                this.setRegistryName(boots);
                this.setUnlocalizedName(setName(boots));
                break;
            case LEGS:
                String leggings = material.getName() + "_leggings";
                this.setRegistryName(leggings);
                this.setUnlocalizedName(setName(leggings));
                break;
            case CHEST:
                String chestplate = material.getName() + "_chestplate";
                this.setRegistryName(chestplate);
                this.setUnlocalizedName(setName(chestplate));
                break;
            case HEAD:
                String helmet = material.getName() + "_helmet";
                this.setRegistryName(helmet);
                this.setUnlocalizedName(setName(helmet));
                break;
        }
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplus);
        this.formattingName = addRarity("ARMOR_COLOR", formatting, "Armor Color");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        material.onArmorTick(world, player, itemStack);
        addEffects(player);
    }

    private void addEffects(EntityPlayer entity) {
        switch (slot) {
            case FEET:
                addAbilities(entity, material.getAreEffectsEnabled()[0], material.enableFullArmorEffect(), getPotion(material.getAddPotionEffect()), material.getAddPotionEffectAmplifier(), getPotion(material.getRemovePotionEffect()));
            case LEGS:
                addAbilities(entity, material.getAreEffectsEnabled()[1], material.enableFullArmorEffect(), getPotion(material.getAddPotionEffect()), material.getAddPotionEffectAmplifier(), getPotion(material.getRemovePotionEffect()));
            case CHEST:
                addAbilities(entity, material.getAreEffectsEnabled()[2], material.enableFullArmorEffect(), getPotion(material.getAddPotionEffect()), material.getAddPotionEffectAmplifier(), getPotion(material.getRemovePotionEffect()));
            case HEAD:
                addAbilities(entity, material.getAreEffectsEnabled()[3], material.enableFullArmorEffect(), getPotion(material.getAddPotionEffect()), material.getAddPotionEffectAmplifier(), getPotion(material.getRemovePotionEffect()));
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
    public void addInformation(ItemStack stack,  World world, List<String> tooltip, ITooltipFlag advanced) {
        material.addInformation(stack, world, tooltip, advanced);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return wear;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(this, getRegistryName(), 0);
    }


    public void addAbilities(EntityPlayer entity, boolean isEnabled, boolean isFullArmorSet, Potion addPotion, int potionAmplifier, Potion removePotion) {
        if (isFullArmorSet) return;
        if (removePotion == ModPotions.EMPTY) return;
        if (addPotion == ModPotions.EMPTY) return;
        if (isEnabled) {
            if (entity.getActivePotionEffect(addPotion) == null || addPotion == MobEffects.NIGHT_VISION) {
                PotionUtils.addPotion(entity, addPotion, potionAmplifier, GOOD);
            }
            if (removePotion != null) {
                PotionUtils.removePotion(entity, removePotion);
            }
        }
    }
}
