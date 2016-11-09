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
import net.thedragonteam.armorplus.armors.ARPArmorMaterial;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.ArmorUtils;
import net.thedragonteam.armorplus.util.ParticlesHelper;

import java.util.List;
import java.util.Objects;

import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.getArmorPlusLocation;
import static net.thedragonteam.armorplus.util.Utils.setARPUnlocalizedName;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseArmor extends ItemArmor {

    public static ItemArmor.ArmorMaterial coalArmor = EnumHelper.addArmorMaterial("coalArmor", getArmorPlusLocation("coal_armor"), 7,
            ARPConfig.coalArmorProtectionPoints, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, (float) ARPConfig.coalArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial emeraldArmor = EnumHelper.addArmorMaterial("emeraldArmor", getArmorPlusLocation("emerald_armor"), 35,
            ARPConfig.emeraldArmorProtectionPoints, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.emeraldArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial lapisArmor = EnumHelper.addArmorMaterial("lapisArmor", getArmorPlusLocation("lapis_armor"), 11,
            ARPConfig.lapisArmorProtectionPoints, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, (float) ARPConfig.lapisArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial lavaArmor = EnumHelper.addArmorMaterial("lavaArmor", getArmorPlusLocation("lava_armor"), 45,
            ARPConfig.lavaArmorProtectionPoints, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.lavaArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial obsidianArmor = EnumHelper.addArmorMaterial("obsidianArmor", getArmorPlusLocation("obsidian_armor"), 40,
            ARPConfig.obsidianArmorProtectionPoints, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.obsidianArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial redstoneArmor = EnumHelper.addArmorMaterial("redstoneArmor", getArmorPlusLocation("redstone_armor"), 11,
            ARPConfig.redstoneArmorProtectionPoints, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, (float) ARPConfig.redstoneArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial chickenArmor = EnumHelper.addArmorMaterial("chickenArmor", getArmorPlusLocation("chicken_armor"), 3,
            ARPConfig.chickenArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, (float) ARPConfig.chickenArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial slimeArmor = EnumHelper.addArmorMaterial("slimeArmor", getArmorPlusLocation("slime_armor"), 3,
            ARPConfig.slimeArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, (float) ARPConfig.slimeArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial enderDragonArmor = EnumHelper.addArmorMaterial("enderDragonArmor", getArmorPlusLocation("ender_dragon_armor"), 60,
            ARPConfig.enderDragonArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.enderDragonArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial guardianArmor = EnumHelper.addArmorMaterial("guardianArmor", getArmorPlusLocation("guardian_armor"), 50,
            ARPConfig.guardianArmorProtectionPoints, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.guardianArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial superStarArmor = EnumHelper.addArmorMaterial("superStarArmor", getArmorPlusLocation("super_star_armor"), 50,
            ARPConfig.superStarArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.superStarArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial arditeArmor = EnumHelper.addArmorMaterial("arditeArmor", getArmorPlusLocation("ardite_armor"), 55,
            ARPConfig.arditeArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.arditeArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial cobaltArmor = EnumHelper.addArmorMaterial("cobaltArmor", getArmorPlusLocation("cobalt_armor"), 44,
            ARPConfig.cobaltArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.cobaltArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial knightSlimeArmor = EnumHelper.addArmorMaterial("knightSlimeArmor", getArmorPlusLocation("knight_slime_armor"), 33,
            ARPConfig.knightSlimeArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.knightSlimeArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial manyullynArmor = EnumHelper.addArmorMaterial("manyullynArmor", getArmorPlusLocation("manyullyn_armor"), 66,
            ARPConfig.manyullynArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.manyullynArmorToughnessPoints);
    public static ItemArmor.ArmorMaterial pigIronArmor = EnumHelper.addArmorMaterial("pigIronArmor", getArmorPlusLocation("pig_iron_armor"), 33,
            ARPConfig.pigIronArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.pigIronArmorToughnessPoints);
    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;
    private ARPArmorMaterial material;
    private EntityEquipmentSlot slot;

    public BaseArmor(ARPArmorMaterial armorMaterial, EntityEquipmentSlot slot) {
        super(armorMaterial.getArmorMaterial(), 0, slot);
        this.itemEasy = armorMaterial.getItemEasy();
        this.itemExpert = armorMaterial.getItemExpert();
        this.formatting = armorMaterial.getFormatting();
        this.material = armorMaterial;
        this.slot = slot;
        setMaxStackSize(1);
        switch (slot) {
            case FEET:
                String boots = armorMaterial.getName() + "_boots";
                setRegistryName(boots);
                setUnlocalizedName(setARPUnlocalizedName(boots));
                break;
            case LEGS:
                String leggings = armorMaterial.getName() + "_leggings";
                setRegistryName(leggings);
                setUnlocalizedName(setARPUnlocalizedName(leggings));
                break;
            case CHEST:
                String chestplate = armorMaterial.getName() + "_chestplate";
                setRegistryName(chestplate);
                setUnlocalizedName(setARPUnlocalizedName(chestplate));
                break;
            case HEAD:
                String helmet = armorMaterial.getName() + "_helmet";
                setRegistryName(helmet);
                setUnlocalizedName(setARPUnlocalizedName(helmet));
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
                switch (slot) {
                    case FEET:
                        if (ARPConfig.enableCoalBNightVision && !ARPConfig.enableFullCoalArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.NIGHT_VISION, 240, 0);
                    case LEGS:
                        if (ARPConfig.enableCoalLNightVision && !ARPConfig.enableFullCoalArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.NIGHT_VISION, 240, 0);
                    case CHEST:
                        if (ARPConfig.enableCoalCNightVision && !ARPConfig.enableFullCoalArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.NIGHT_VISION, 240, 0);
                    case HEAD:
                        if (ARPConfig.enableCoalHNightVision && !ARPConfig.enableFullCoalArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.NIGHT_VISION, 240, 0);
                }
                break;
            case EMERALD:
                switch (slot) {
                    case FEET:
                        if (ARPConfig.enableEmeraldBHaste && !ARPConfig.enableFullEmeraldArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.HASTE, emeraldArmorEffectlevel);
                        break;
                    case LEGS:
                        if (ARPConfig.enableEmeraldLHaste && !ARPConfig.enableFullEmeraldArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.HASTE, emeraldArmorEffectlevel);
                        break;
                    case CHEST:
                        if (ARPConfig.enableEmeraldCHaste && !ARPConfig.enableFullEmeraldArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.HASTE, emeraldArmorEffectlevel);
                        break;
                    case HEAD:
                        if (ARPConfig.enableEmeraldHHaste && !ARPConfig.enableFullEmeraldArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.HASTE, emeraldArmorEffectlevel);
                        break;
                }
                break;
            case LAPIS:
                switch (slot) {
                    case FEET:
                        if (ARPConfig.enableLapisBBreathing && !ARPConfig.enableFullLapisArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.WATER_BREATHING, 0);
                        break;
                    case LEGS:
                        if (ARPConfig.enableLapisLBreathing && !ARPConfig.enableFullLapisArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.WATER_BREATHING, 0);
                        break;
                    case CHEST:
                        if (ARPConfig.enableLapisCBreathing && !ARPConfig.enableFullLapisArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.WATER_BREATHING, 0);
                        break;
                    case HEAD:
                        if (ARPConfig.enableLapisHBreathing && !ARPConfig.enableFullLapisArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.WATER_BREATHING, 0);
                        break;
                }
                break;
            case LAVA:
                switch (slot) {
                    case FEET:
                        if (ARPConfig.enableLavaBEffects && !ARPConfig.enableFullLavaArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.FIRE_RESISTANCE, 0);
                        lavaEffects(entity, itemStack);
                        break;
                    case LEGS:
                        if (ARPConfig.enableLavaLEffects && !ARPConfig.enableFullLavaArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.FIRE_RESISTANCE, 0);
                        lavaEffects(entity, itemStack);
                        break;
                    case CHEST:
                        if (ARPConfig.enableLavaCEffects && !ARPConfig.enableFullLavaArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.FIRE_RESISTANCE, 0);
                        lavaEffects(entity, itemStack);
                        break;
                    case HEAD:
                        if (ARPConfig.enableLavaHEffects && !ARPConfig.enableFullLavaArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.FIRE_RESISTANCE, 0);
                        lavaEffects(entity, itemStack);
                        break;
                }
                break;
            case REDSTONE:
                switch (slot) {
                    case FEET:
                        if (ARPConfig.enableRedstoneBSpeed && !ARPConfig.enableFullRedstoneArmorEffect) {
                            ArmorUtils.armorEffects(entity, MobEffects.SPEED, redstoneArmorEffectlevel);
                            if (world.isRemote) {
                                ParticlesHelper.spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case LEGS:
                        if (ARPConfig.enableRedstoneLSpeed && !ARPConfig.enableFullRedstoneArmorEffect) {
                            ArmorUtils.armorEffects(entity, MobEffects.SPEED, redstoneArmorEffectlevel);
                            if (world.isRemote) {
                                ParticlesHelper.spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case CHEST:
                        if (ARPConfig.enableRedstoneCSpeed && !ARPConfig.enableFullRedstoneArmorEffect) {
                            ArmorUtils.armorEffects(entity, MobEffects.SPEED, redstoneArmorEffectlevel);
                            if (world.isRemote) {
                                ParticlesHelper.spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case HEAD:
                        if (ARPConfig.enableRedstoneHSpeed && !ARPConfig.enableFullRedstoneArmorEffect) {
                            ArmorUtils.armorEffects(entity, MobEffects.SPEED, redstoneArmorEffectlevel);
                            if (world.isRemote) {
                                ParticlesHelper.spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                }
                break;
            case OBSIDIAN:
                switch (slot) {
                    case FEET:
                        if (ARPConfig.enableObsidianBResistance && !ARPConfig.enableFullObsidianArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.RESISTANCE, obsidianArmorEffectlevel);
                        break;
                    case LEGS:
                        if (ARPConfig.enableObsidianLResistance && !ARPConfig.enableFullObsidianArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.RESISTANCE, obsidianArmorEffectlevel);
                        break;
                    case CHEST:
                        if (ARPConfig.enableObsidianCResistance && !ARPConfig.enableFullObsidianArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.RESISTANCE, obsidianArmorEffectlevel);
                        break;
                    case HEAD:
                        if (ARPConfig.enableObsidianHResistance && !ARPConfig.enableFullObsidianArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.RESISTANCE, obsidianArmorEffectlevel);
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
                switch (slot) {
                    case FEET:
                        if (ARPConfig.enableSuperStarBRegen && !ARPConfig.enableFullSuperStarArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.REGENERATION, superstarArmorEffectlevel);
                        entity.removePotionEffect(MobEffects.WITHER);
                        break;
                    case LEGS:
                        if (ARPConfig.enableSuperStarLRegen && !ARPConfig.enableFullSuperStarArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.REGENERATION, superstarArmorEffectlevel);
                        entity.removePotionEffect(MobEffects.WITHER);
                        break;
                    case CHEST:
                        if (ARPConfig.enableSuperStarCRegen && !ARPConfig.enableFullSuperStarArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.REGENERATION, superstarArmorEffectlevel);
                        entity.removePotionEffect(MobEffects.WITHER);
                        break;
                    case HEAD:
                        if (ARPConfig.enableSuperStarHRegen && !ARPConfig.enableFullSuperStarArmorEffect)
                            ArmorUtils.armorEffects(entity, MobEffects.REGENERATION, superstarArmorEffectlevel);
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

    public void lavaEffects(EntityPlayer entity, ItemStack itemStack) {
        if (!ARPConfig.enableFullLavaArmorEffect) {
            entity.extinguish();
            entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
        } else if (entity.isInWater() && !ARPConfig.enableFullLavaArmorEffect && entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 120, 1, true, true));
            itemStack.damageItem(1, entity);
            entity.attackEntityFrom(DamageSource.drown, 1F);
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        switch (ARPConfig.recipes) {
            case 0:
                return Objects.equals(repair.getItem(), itemEasy);
            case 1:
                return Objects.equals(repair.getItem(), itemExpert);
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
                        tooltip.add("\2479Ability: " + "\247r" + "Night Vision");
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullCoalArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Night Vision");
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case EMERALD:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullEmeraldArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Haste" + " " + (emeraldArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullEmeraldArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Haste" + " " + (emeraldArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case LAPIS:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullLapisArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Water Breathing");
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullLapisArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Water Breathing");
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case LAVA:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullLavaArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Fire Resistance");
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullLavaArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Fire Resistance");
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case REDSTONE:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullRedstoneArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Speed" + " " + (redstoneArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullRedstoneArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Speed" + " " + (redstoneArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case OBSIDIAN:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!ARPConfig.enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Resistance" + " " + (obsidianArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Resistance" + " " + (obsidianArmorEffectlevel + 1));
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
                        tooltip.add("\2479Ability: " + "\247r" + "Regeneration" + " " + (superstarArmorEffectlevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (ARPConfig.enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + "Regeneration" + " " + (superstarArmorEffectlevel + 1));
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

    private void showInfo(List<String> tooltip, KeyBinding keyBinding) {
        tooltip.add(I18n.format("tooltip.shift.showinfo", formatting, keyBinding.getDisplayName(), ChatFormatting.GRAY));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
