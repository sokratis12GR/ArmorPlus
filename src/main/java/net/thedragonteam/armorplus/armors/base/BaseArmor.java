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
import net.minecraft.potion.Potion;
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

    private static float coalArmorToughnessPoints = (float) ARPConfig.coalArmorToughnessPoints;
    public static ArmorMaterial coalArmor = EnumHelper.addArmorMaterial("coal", getArmorPlusLocation("coal_armor"), 7,
            coalArmorProtectionPoints, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, coalArmorToughnessPoints);
    private static float emeraldArmorToughnessPoints = (float) ARPConfig.emeraldArmorToughnessPoints;
    public static ArmorMaterial emeraldArmor = EnumHelper.addArmorMaterial("emerald", getArmorPlusLocation("emerald_armor"), 35,
            emeraldArmorProtectionPoints, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, emeraldArmorToughnessPoints);
    private static float lapisArmorToughnessPoints = (float) ARPConfig.lapisArmorToughnessPoints;
    public static ArmorMaterial lapisArmor = EnumHelper.addArmorMaterial("lapis", getArmorPlusLocation("lapis_armor"), 11,
            lapisArmorProtectionPoints, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, lapisArmorToughnessPoints);
    private static float lavaArmorToughnessPoints = (float) ARPConfig.lavaArmorToughnessPoints;
    public static ArmorMaterial lavaArmor = EnumHelper.addArmorMaterial("lava", getArmorPlusLocation("lava_armor"), 45,
            lavaArmorProtectionPoints, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, lavaArmorToughnessPoints);
    private static float obsidianArmorToughnessPoints = (float) ARPConfig.obsidianArmorToughnessPoints;
    public static ArmorMaterial obsidianArmor = EnumHelper.addArmorMaterial("obsidian", getArmorPlusLocation("obsidian_armor"), 40,
            obsidianArmorProtectionPoints, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, obsidianArmorToughnessPoints);
    private static float redstoneArmorToughnessPoints = (float) ARPConfig.redstoneArmorToughnessPoints;
    public static ArmorMaterial redstoneArmor = EnumHelper.addArmorMaterial("redstone", getArmorPlusLocation("redstone_armor"), 11,
            redstoneArmorProtectionPoints, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, redstoneArmorToughnessPoints);
    private static float chickenArmorToughnessPoints = (float) ARPConfig.chickenArmorToughnessPoints;
    public static ArmorMaterial chickenArmor = EnumHelper.addArmorMaterial("chicken", getArmorPlusLocation("chicken_armor"), 3,
            chickenArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, chickenArmorToughnessPoints);
    private static float slimeArmorToughnessPoints = (float) ARPConfig.slimeArmorToughnessPoints;
    public static ArmorMaterial slimeArmor = EnumHelper.addArmorMaterial("slime", getArmorPlusLocation("slime_armor"), 3,
            slimeArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, slimeArmorToughnessPoints);
    private static float enderDragonArmorToughnessPoints = (float) ARPConfig.enderDragonArmorToughnessPoints;
    public static ArmorMaterial enderDragonArmor = EnumHelper.addArmorMaterial("enderDragon", getArmorPlusLocation("ender_dragon_armor"), 60,
            enderDragonArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, enderDragonArmorToughnessPoints);
    private static float guardianArmorToughnessPoints = (float) ARPConfig.guardianArmorToughnessPoints;
    public static ArmorMaterial guardianArmor = EnumHelper.addArmorMaterial("guardian", getArmorPlusLocation("guardian_armor"), 50,
            guardianArmorProtectionPoints, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, guardianArmorToughnessPoints);
    private static float superStarArmorToughnessPoints = (float) ARPConfig.superStarArmorToughnessPoints;
    public static ArmorMaterial superStarArmor = EnumHelper.addArmorMaterial("superStar", getArmorPlusLocation("super_star_armor"), 50,
            superStarArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, superStarArmorToughnessPoints);
    private static float arditeArmorToughnessPoints = (float) ARPConfig.arditeArmorToughnessPoints;
    public static ArmorMaterial arditeArmor = EnumHelper.addArmorMaterial("ardite", getArmorPlusLocation("ardite_armor"), 55,
            arditeArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, arditeArmorToughnessPoints);
    private static float cobaltArmorToughnessPoints = (float) ARPConfig.cobaltArmorToughnessPoints;
    public static ArmorMaterial cobaltArmor = EnumHelper.addArmorMaterial("cobalt", getArmorPlusLocation("cobalt_armor"), 44,
            cobaltArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, cobaltArmorToughnessPoints);
    private static float knightSlimeArmorToughnessPoints = (float) ARPConfig.knightSlimeArmorToughnessPoints;
    public static ArmorMaterial knightSlimeArmor = EnumHelper.addArmorMaterial("knightSlime", getArmorPlusLocation("knight_slime_armor"), 33,
            knightSlimeArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, knightSlimeArmorToughnessPoints);
    private static float manyullynArmorToughnessPoints = (float) ARPConfig.manyullynArmorToughnessPoints;
    public static ArmorMaterial manyullynArmor = EnumHelper.addArmorMaterial("manyullyn", getArmorPlusLocation("manyullyn_armor"), 66,
            manyullynArmorProtectionPoints, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, manyullynArmorToughnessPoints);
    private static float pigIronArmorToughnessPoints = (float) ARPConfig.pigIronArmorToughnessPoints;
    public static ArmorMaterial pigIronArmor = EnumHelper.addArmorMaterial("pigIron", getArmorPlusLocation("pig_iron_armor"), 33,
            pigIronArmorProtectionPoints, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, pigIronArmorToughnessPoints);

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

    @SuppressWarnings("ConstantConditions")
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
                        if (enableCoalBEffect && !enableFullCoalArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(coalArmorAddPotionEffect), 240, coalArmorEffectLevel);
                    case LEGS:
                        if (enableCoalLEffect && !enableFullCoalArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(coalArmorAddPotionEffect), 240, coalArmorEffectLevel);
                    case CHEST:
                        if (enableCoalCEffect && !enableFullCoalArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(coalArmorAddPotionEffect), 240, coalArmorEffectLevel);
                    case HEAD:
                        if (enableCoalHEffect && !enableFullCoalArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(coalArmorAddPotionEffect), 240, coalArmorEffectLevel);
                }
                break;
            case EMERALD:
                switch (slot) {
                    case FEET:
                        if (enableEmeraldBEffect && !enableFullEmeraldArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel);
                        break;
                    case LEGS:
                        if (enableEmeraldLEffect && !enableFullEmeraldArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel);
                        break;
                    case CHEST:
                        if (enableEmeraldCEffect && !enableFullEmeraldArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel);
                        break;
                    case HEAD:
                        if (enableEmeraldHEffect && !enableFullEmeraldArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel);
                        break;
                }
                break;
            case LAPIS:
                switch (slot) {
                    case FEET:
                        if (enableLapisBEffect && !enableFullLapisArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(lapisArmorAddPotionEffect), lapisArmorEffectLevel);
                        break;
                    case LEGS:
                        if (enableLapisLEffect && !enableFullLapisArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(lapisArmorAddPotionEffect), lapisArmorEffectLevel);
                        break;
                    case CHEST:
                        if (enableLapisCEffect && !enableFullLapisArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(lapisArmorAddPotionEffect), lapisArmorEffectLevel);
                        break;
                    case HEAD:
                        if (enableLapisHEffect && !enableFullLapisArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(lapisArmorAddPotionEffect), lapisArmorEffectLevel);
                        break;
                }
                break;
            case LAVA:
                switch (slot) {
                    case FEET:
                        if (enableLavaBEffect && !enableFullLavaArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(lavaArmorAddPotionEffect), lavaArmorEffectLevel);
                        lavaEffects(entity, itemStack);
                        break;
                    case LEGS:
                        if (enableLavaLEffect && !enableFullLavaArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(lavaArmorAddPotionEffect), lavaArmorEffectLevel);
                        lavaEffects(entity, itemStack);
                        break;
                    case CHEST:
                        if (enableLavaCEffect && !enableFullLavaArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(lavaArmorAddPotionEffect), lavaArmorEffectLevel);
                        lavaEffects(entity, itemStack);
                        break;
                    case HEAD:
                        if (enableLavaHEffect && !enableFullLavaArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(lavaArmorAddPotionEffect), lavaArmorEffectLevel);
                        lavaEffects(entity, itemStack);
                        break;
                }
                break;
            case REDSTONE:
                switch (slot) {
                    case FEET:
                        if (enableRedstoneBEffect && !enableFullRedstoneArmorEffect) {
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel);
                            if (world.isRemote) {
                                ParticlesHelper.spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case LEGS:
                        if (enableRedstoneLEffect && !enableFullRedstoneArmorEffect) {
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel);
                            if (world.isRemote) {
                                ParticlesHelper.spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case CHEST:
                        if (enableRedstoneCEffect && !enableFullRedstoneArmorEffect) {
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel);
                            if (world.isRemote) {
                                ParticlesHelper.spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case HEAD:
                        if (enableRedstoneHEffect && !enableFullRedstoneArmorEffect) {
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel);
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
                        if (enableObsidianBEffect && !enableFullObsidianArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel);
                        break;
                    case LEGS:
                        if (enableObsidianLEffect && !enableFullObsidianArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel);
                        break;
                    case CHEST:
                        if (enableObsidianCEffect && !enableFullObsidianArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel);
                        break;
                    case HEAD:
                        if (enableObsidianHEffect && !enableFullObsidianArmorEffect)
                            ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel);
                        break;
                }
                break;
            case ENDER_DRAGON:
                if (enableFlightAbility) {
                    if (head != null && head.getItem() == ModItems.enderDragonHelmet && chest != null && chest.getItem() == ModItems.enderDragonChestplate && legs != null && legs.getItem() == ModItems.enderDragonLeggings && feet != null && feet.getItem() == ModItems.enderDragonBoots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                        entity.capabilities.allowFlying = true;
                    } else {
                        entity.capabilities.isFlying = false;
                        entity.capabilities.allowFlying = false;
                    }
                }
                if (Potion.getPotionFromResourceLocation(enderDragonArmorRemovePotionEffect) != null)
                    entity.removePotionEffect(Potion.getPotionFromResourceLocation(enderDragonArmorRemovePotionEffect));
                break;
            case SUPER_STAR:
                switch (slot) {
                    case FEET:
                        if (enableSuperStarBEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect)) == null)
                                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect), superStarArmorEffectLevel);
                        entity.removePotionEffect(Potion.getPotionFromResourceLocation(superStarArmorRemovePotionEffect));
                        break;
                    case LEGS:
                        if (enableSuperStarLEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect)) == null)
                                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect), superStarArmorEffectLevel);
                        entity.removePotionEffect(Potion.getPotionFromResourceLocation(superStarArmorRemovePotionEffect));
                        break;
                    case CHEST:
                        if (enableSuperStarCEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect)) == null)
                                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect), superStarArmorEffectLevel);
                        entity.removePotionEffect(Potion.getPotionFromResourceLocation(superStarArmorRemovePotionEffect));
                        break;
                    case HEAD:
                        if (enableSuperStarHEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect)) == null)
                                ArmorUtils.addArmorEffect(entity, Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect), superStarArmorEffectLevel);
                        entity.removePotionEffect(Potion.getPotionFromResourceLocation(superStarArmorRemovePotionEffect));
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
        if (!enableFullLavaArmorEffect) {
            entity.extinguish();
            entity.setAbsorptionAmount(entity.isInLava() ? 4.0F : 0.0F);
        } else if (entity.isInWater() && !enableFullLavaArmorEffect && entity.getActivePotionEffect(MobEffects.WATER_BREATHING) == null) {
            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 120, 1, true, true));
            itemStack.damageItem(1, entity);
            entity.attackEntityFrom(DamageSource.drown, 1F);
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        switch (recipes) {
            case 0:
                return Objects.equals(repair.getItem(), itemEasy);
            case 1:
                return Objects.equals(repair.getItem(), itemExpert);
        }
        return true;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        switch (material) {
            case COAL:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!enableFullCoalArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(coalArmorAddPotionEffect).getName() + ".name").trim() + " " + (coalArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (enableFullCoalArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(coalArmorAddPotionEffect).getName() + ".name").trim() + " " + (coalArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case EMERALD:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!enableFullEmeraldArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(emeraldArmorAddPotionEffect).getName() + ".name").trim() + " " + (emeraldArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (enableFullEmeraldArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(emeraldArmorAddPotionEffect).getName() + ".name").trim() + " " + (emeraldArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case LAPIS:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!enableFullLapisArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(lapisArmorAddPotionEffect).getName() + ".name").trim() + " " + (lapisArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (enableFullLapisArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(lapisArmorAddPotionEffect).getName() + ".name").trim() + " " + (lapisArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case LAVA:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!enableFullLavaArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(lavaArmorAddPotionEffect).getName() + ".name").trim() + " " + (lavaArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (enableFullLavaArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(lavaArmorAddPotionEffect).getName() + ".name").trim() + " " + (lavaArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case REDSTONE:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!enableFullRedstoneArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(redstoneArmorAddPotionEffect).getName() + ".name").trim() + " " + (redstoneArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (enableFullRedstoneArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(redstoneArmorAddPotionEffect).getName() + ".name").trim() + " " + (redstoneArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case OBSIDIAN:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(obsidianArmorAddPotionEffect).getName() + ".name").trim() + " " + (obsidianArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(obsidianArmorAddPotionEffect).getName() + ".name").trim() + " " + (obsidianArmorEffectLevel + 1));
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
                    tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(guardianArmorAddPotionEffect).getName() + ".name").trim() + " " + (guardianArmorEffectLevel + 1));
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case SLIME:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(slimeArmorAddPotionEffect).getName() + ".name").trim() + " " + (slimeArmorEffectLevel + 1));
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case CHICKEN:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(chickenArmorAddPotionEffect).getName() + ".name").trim() + " " + (chickenArmorEffectLevel + 1));
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case SUPER_STAR:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    if (!enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect).getName() + ".name").trim() + " " + (superStarArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip A Piece");
                    } else if (enableFullObsidianArmorEffect) {
                        tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(superStarArmorAddPotionEffect).getName() + ".name").trim() + " " + (superStarArmorEffectLevel + 1));
                        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case ARDITE:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(arditeArmorAddPotionEffect).getName() + ".name").trim() + " " + (arditeArmorEffectLevel + 1));
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case COBALT:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(cobaltArmorAddPotionEffect).getName() + ".name").trim() + " " + (cobaltArmorEffectLevel + 1));
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case MANYULLYN:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(manyullynArmorAddPotionEffect).getName() + ".name").trim() + " " + (manyullynArmorEffectLevel + 1));
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case KNIGHT_SLIME:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(knightSlimeArmorAddPotionEffect).getName() + ".name").trim() + " " + (knightSlimeArmorEffectLevel + 1));
                    tooltip.add("\2473Use: " + "\247rEquip The Full Set");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case PIG_IRON:
                if (GameSettings.isKeyDown(keyBindSneak)) {
                    tooltip.add("\2479Ability: " + "\247r" + localize(Potion.getPotionFromResourceLocation(pigIronArmorAddPotionEffect).getName() + ".name").trim() + " " + (pigIronArmorEffectLevel + 1));
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
