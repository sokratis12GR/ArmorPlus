/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.base;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.ARPArmorEffects;
import net.thedragonteam.armorplus.armors.ARPArmorMaterial;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.armorplus.ArmorPlus.getArmorPlusLocation;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseArmor extends ItemArmor {

    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;
    private ARPArmorMaterial material;
    public static ItemArmor.ArmorMaterial coalArmor = EnumHelper.addArmorMaterial("coalArmor", getArmorPlusLocation() + "coal_armor", 7,
            ARPConfig.coalArmorProtectionPoints, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, (float) ARPConfig.coalArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial emeraldArmor = EnumHelper.addArmorMaterial("emeraldArmor", getArmorPlusLocation() + "emerald_armor", 35,
            ARPConfig.emeraldArmorProtectionPoints, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.emeraldArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial lapisArmor = EnumHelper.addArmorMaterial("lapisArmor", getArmorPlusLocation() + "lapis_armor", 11,
            ARPConfig.lapisArmorProtectionPoints, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, (float) ARPConfig.lapisArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial lavaArmor = EnumHelper.addArmorMaterial("lavaArmor", getArmorPlusLocation() + "lava_armor", 45,
            ARPConfig.lavaArmorProtectionPoints, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.lavaArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial obsidianArmor = EnumHelper.addArmorMaterial("obsidianArmor", getArmorPlusLocation() + "obsidian_armor", 40,
            ARPConfig.obsidianArmorProtectionPoints, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.obsidianArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial redstoneArmor = EnumHelper.addArmorMaterial("redstoneArmor", getArmorPlusLocation() + "redstone_armor", 11,
            ARPConfig.redstoneArmorProtectionPoints, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, (float) ARPConfig.redstoneArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial chickenArmor = EnumHelper.addArmorMaterial("chickenArmor", getArmorPlusLocation() + "chicken_armor", 3,
            ARPConfig.chickenArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, (float) ARPConfig.chickenArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial slimeArmor = EnumHelper.addArmorMaterial("slimeArmor", getArmorPlusLocation() + "slime_armor", 3,
            ARPConfig.slimeArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, (float) ARPConfig.slimeArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial enderDragonArmor = EnumHelper.addArmorMaterial("enderDragonArmor", getArmorPlusLocation() + "ender_dragon_armor", 60,
            ARPConfig.enderDragonArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.enderDragonArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial guardianArmor = EnumHelper.addArmorMaterial("guardianArmor", getArmorPlusLocation() + "guardian_armor", 50,
            ARPConfig.guardianArmorProtectionPoints, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.guardianArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial superStarArmor = EnumHelper.addArmorMaterial("superStarArmor", getArmorPlusLocation() + "super_star_armor", 50,
            ARPConfig.superStarArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.superStarArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial arditeArmor = EnumHelper.addArmorMaterial("arditeArmor", getArmorPlusLocation() + "ardite_armor", 55,
            ARPConfig.arditeArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.arditeArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial cobaltArmor = EnumHelper.addArmorMaterial("cobaltArmor", getArmorPlusLocation() + "cobalt_armor", 44,
            ARPConfig.cobaltArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.cobaltArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial knightSlimeArmor = EnumHelper.addArmorMaterial("knightSlimeArmor", getArmorPlusLocation() + "knight_slime_armor", 33,
            ARPConfig.knightSlimeArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.knightSlimeArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial manyullynArmor = EnumHelper.addArmorMaterial("manyullynArmor", getArmorPlusLocation() + "manyullyn_armor", 66,
            ARPConfig.manyullynArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.manyullynArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial pigIronArmor = EnumHelper.addArmorMaterial("pigIronArmor", getArmorPlusLocation() + "pig_iron_armor", 33,
            ARPConfig.pigIronArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.pigIronArmorToughnessPoints);

    public BaseArmor(ARPArmorMaterial armorMaterial, EntityEquipmentSlot slot) {
        super(armorMaterial.getArmorMaterial(), 0, slot);
        this.itemEasy = armorMaterial.getItemEasy();
        this.itemExpert = armorMaterial.getItemExpert();
        this.formatting = armorMaterial.getFormatting();
        this.material = armorMaterial;
        setMaxStackSize(1);
        switch (slot) {
            case FEET:
                String boots = armorMaterial.getName() + "_boots";
                setRegistryName(boots);
                setUnlocalizedName(ArmorPlus.MODID + "." + boots);
                break;
            case LEGS:
                String leggings = armorMaterial.getName() + "_leggings";
                setRegistryName(leggings);
                setUnlocalizedName(ArmorPlus.MODID + "." + leggings);
                break;
            case CHEST:
                String chestplate = armorMaterial.getName() + "_chestplate";
                setRegistryName(chestplate);
                setUnlocalizedName(ArmorPlus.MODID + "." + chestplate);
                break;
            case HEAD:
                String helmet = armorMaterial.getName() + "_helmet";
                setRegistryName(helmet);
                setUnlocalizedName(ArmorPlus.MODID + "." + helmet);
                break;
        }
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.tabArmorplus);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        switch (material) {
            case COAL:
                switch (getEquipmentSlot()) {
                    case FEET:
                        if (ARPConfig.enableCoalBNightVision && !ARPConfig.enableFullCoalArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.COAL.getPotionEffect());
                        break;
                    case LEGS:
                        if (ARPConfig.enableCoalLNightVision && !ARPConfig.enableFullCoalArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.COAL.getPotionEffect());
                        break;
                    case CHEST:
                        if (ARPConfig.enableCoalCNightVision && !ARPConfig.enableFullCoalArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.COAL.getPotionEffect());
                        break;
                    case HEAD:
                        if (ARPConfig.enableCoalHNightVision && !ARPConfig.enableFullCoalArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.COAL.getPotionEffect());
                        break;
                }
                break;
            case EMERALD:
                switch (getEquipmentSlot()) {
                    case FEET:
                        if (ARPConfig.enableEmeraldBHaste && !ARPConfig.enableFullEmeraldArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.EMERALD.getPotionEffect());
                        break;
                    case LEGS:
                        if (ARPConfig.enableEmeraldLHaste && !ARPConfig.enableFullEmeraldArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.EMERALD.getPotionEffect());
                        break;
                    case CHEST:
                        if (ARPConfig.enableEmeraldCHaste && !ARPConfig.enableFullEmeraldArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.EMERALD.getPotionEffect());
                        break;
                    case HEAD:
                        if (ARPConfig.enableEmeraldHHaste && !ARPConfig.enableFullEmeraldArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.EMERALD.getPotionEffect());
                        break;
                }
                break;
            case LAPIS:
                switch (getEquipmentSlot()) {
                    case FEET:
                        if (ARPConfig.enableLapisBBreathing && !ARPConfig.enableFullLapisArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.LAPIS.getPotionEffect());
                        break;
                    case LEGS:
                        if (ARPConfig.enableLapisLBreathing && !ARPConfig.enableFullLapisArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.LAPIS.getPotionEffect());
                        break;
                    case CHEST:
                        if (ARPConfig.enableLapisCBreathing && !ARPConfig.enableFullLapisArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.LAPIS.getPotionEffect());
                        break;
                    case HEAD:
                        if (ARPConfig.enableLapisHBreathing && !ARPConfig.enableFullLapisArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.LAPIS.getPotionEffect());
                        break;
                }
                break;
            case LAVA:
                switch (getEquipmentSlot()) {
                    case FEET:
                        if (ARPConfig.enableLavaBEffects && !ARPConfig.enableFullLavaArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.LAVA.getPotionEffect());
                        lavaEffects(entity, itemStack);
                        break;
                    case LEGS:
                        if (ARPConfig.enableLavaLEffects && !ARPConfig.enableFullLavaArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.LAVA.getPotionEffect());
                        lavaEffects(entity, itemStack);
                        break;
                    case CHEST:
                        if (ARPConfig.enableLavaCEffects && !ARPConfig.enableFullLavaArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.LAVA.getPotionEffect());
                        lavaEffects(entity, itemStack);
                        break;
                    case HEAD:
                        if (ARPConfig.enableLavaHEffects && !ARPConfig.enableFullLavaArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.LAVA.getPotionEffect());
                        lavaEffects(entity, itemStack);
                        break;
                }
                break;
            case REDSTONE:
                switch (getEquipmentSlot()) {
                    case FEET:
                        if (ARPConfig.enableRedstoneBSpeed && !ARPConfig.enableFullRedstoneArmorEffect) {
                            entity.addPotionEffect(ARPArmorEffects.REDSTONE.getPotionEffect());
                            if (world.isRemote) {
                                world.spawnParticle(EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
                            }
                        }
                        break;
                    case LEGS:
                        if (ARPConfig.enableRedstoneLSpeed && !ARPConfig.enableFullRedstoneArmorEffect) {
                            entity.addPotionEffect(ARPArmorEffects.REDSTONE.getPotionEffect());
                            if (world.isRemote) {
                                world.spawnParticle(EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
                            }
                        }
                        break;
                    case CHEST:
                        if (ARPConfig.enableRedstoneCSpeed && !ARPConfig.enableFullRedstoneArmorEffect) {
                            entity.addPotionEffect(ARPArmorEffects.REDSTONE.getPotionEffect());
                            if (world.isRemote) {
                                world.spawnParticle(EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
                            }
                        }
                        break;
                    case HEAD:
                        if (ARPConfig.enableRedstoneHSpeed && !ARPConfig.enableFullRedstoneArmorEffect) {
                            entity.addPotionEffect(ARPArmorEffects.REDSTONE.getPotionEffect());
                            if (world.isRemote) {
                                world.spawnParticle(EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
                            }
                        }
                        break;
                }
                break;
            case OBSIDIAN:
                switch (getEquipmentSlot()) {
                    case FEET:
                        if (ARPConfig.enableObsidianBResistance && !ARPConfig.enableFullObsidianArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.OBSIDIAN.getPotionEffect());
                        break;
                    case LEGS:
                        if (ARPConfig.enableObsidianLResistance && !ARPConfig.enableFullObsidianArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.OBSIDIAN.getPotionEffect());
                        break;
                    case CHEST:
                        if (ARPConfig.enableObsidianCResistance && !ARPConfig.enableFullObsidianArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.OBSIDIAN.getPotionEffect());
                        break;
                    case HEAD:
                        if (ARPConfig.enableObsidianHResistance && !ARPConfig.enableFullObsidianArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.OBSIDIAN.getPotionEffect());
                        break;
                }
                break;
            case ENDER_DRAGON:
                if (ARPConfig.enableFlightAbility) {
                    if (head != null && head.getItem() == ModItems.enderDragonHelmet && chest != null && chest.getItem() == ModItems.enderDragonChestplate && legs != null && legs.getItem() == ModItems.enderDragonLeggings && feet != null && feet.getItem() == ModItems.enderDragonBoots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
                entity.removePotionEffect(MobEffects.WITHER);
                break;
            case SUPER_STAR:
                switch (getEquipmentSlot()) {
                    case FEET:
                        if (ARPConfig.enableSuperStarBRegen && !ARPConfig.enableFullSuperStarArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.SUPER_STAR.getPotionEffect());
                        entity.removePotionEffect(MobEffects.WITHER);
                        break;
                    case LEGS:
                        if (ARPConfig.enableSuperStarLRegen && !ARPConfig.enableFullSuperStarArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.SUPER_STAR.getPotionEffect());
                        entity.removePotionEffect(MobEffects.WITHER);
                        break;
                    case CHEST:
                        if (ARPConfig.enableSuperStarCRegen && !ARPConfig.enableFullSuperStarArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.SUPER_STAR.getPotionEffect());
                        entity.removePotionEffect(MobEffects.WITHER);
                        break;
                    case HEAD:
                        if (ARPConfig.enableSuperStarHRegen && !ARPConfig.enableFullSuperStarArmorEffect)
                            entity.addPotionEffect(ARPArmorEffects.SUPER_STAR.getPotionEffect());
                        entity.removePotionEffect(MobEffects.WITHER);
                        break;
                }
                break;
        }
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

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        switch (material) {
            case COAL:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullCoalArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.COAL.getPotionEffectName());
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullCoalArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.COAL.getPotionEffectName());
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case EMERALD:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullEmeraldArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.EMERALD.getPotionEffectName() + " " + (ARPConfig.emeraldArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullEmeraldArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.EMERALD.getPotionEffectName() + " " + (ARPConfig.emeraldArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case LAPIS:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullLapisArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.LAPIS.getPotionEffectName());
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullLapisArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.LAPIS.getPotionEffectName());
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case LAVA:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullLavaArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.LAVA.getPotionEffectName());
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullLavaArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.LAVA.getPotionEffectName());
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case REDSTONE:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullRedstoneArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.REDSTONE.getPotionEffectName() + " " + (ARPConfig.redstoneArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullRedstoneArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.REDSTONE.getPotionEffectName() + " " + (ARPConfig.redstoneArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case OBSIDIAN:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.OBSIDIAN.getPotionEffectName() + " " + (ARPConfig.obsidianArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.OBSIDIAN.getPotionEffectName() + " " + (ARPConfig.obsidianArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case ENDER_DRAGON:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247rFlight");
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case GUARDIAN:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247rWater Breathing");
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case SLIME:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247rJump Boost 3");
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case CHICKEN:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247rSpeed 5");
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case SUPER_STAR:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.SUPER_STAR.getPotionEffectName() + " " + (ARPConfig.superstarArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + ARPArmorEffects.SUPER_STAR.getPotionEffectName() + " " + (ARPConfig.superstarArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case ARDITE:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247rFire Resistance");
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case COBALT:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247rHaste 3");
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case MANYULLYN:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247rStrength 2");
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case KNIGHT_SLIME:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247rHaste 2");
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case PIG_IRON:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247rSaturation");
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
        }
    }

    private void lavaEffects(EntityPlayer entity, ItemStack itemStack) {
        if (!ARPConfig.enableFullLavaArmorEffect) {
            entity.extinguish();
            entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
        } else if (entity.isInWater() && !ARPConfig.enableFullLavaArmorEffect && entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 120, 1, true, true));
            itemStack.damageItem(1, entity);
            entity.attackEntityFrom(DamageSource.drown, 1F);
        }
    }

    private void showInfo(List<String> tooltip, KeyBinding keyBinding) {
        tooltip.add(I18n.format("tooltip.shift.showinfo", formatting, keyBinding.getDisplayName(), ChatFormatting.GRAY));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
