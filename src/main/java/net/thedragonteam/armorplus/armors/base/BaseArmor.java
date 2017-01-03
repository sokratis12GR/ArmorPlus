/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.ARPArmorMaterial;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;
import java.util.Objects;

import static net.minecraft.init.SoundEvents.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraftforge.common.util.EnumHelper.addArmorMaterial;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.ArmorPlus.getArmorPlusLocation;
import static net.thedragonteam.armorplus.util.ParticlesHelper.spawnParticle;
import static net.thedragonteam.armorplus.util.PotionUtils.EffectType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

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
        setCreativeTab(ArmorPlus.tabArmorplus);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        ItemStack head = entity.getItemStackFromSlot(HEAD);
        ItemStack chest = entity.getItemStackFromSlot(CHEST);
        ItemStack legs = entity.getItemStackFromSlot(LEGS);
        ItemStack feet = entity.getItemStackFromSlot(FEET);
        switch (material) {
            case COAL:
                switch (slot) {
                    case FEET:
                        if (enableCoalBEffect && !enableFullCoalArmorEffect)
                            addEffect(entity, getPotion(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
                    case LEGS:
                        if (enableCoalLEffect && !enableFullCoalArmorEffect)
                            addEffect(entity, getPotion(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
                    case CHEST:
                        if (enableCoalCEffect && !enableFullCoalArmorEffect)
                            addEffect(entity, getPotion(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
                    case HEAD:
                        if (enableCoalHEffect && !enableFullCoalArmorEffect)
                            addEffect(entity, getPotion(coalArmorAddPotionEffect), 240, coalArmorEffectLevel, GOOD);
                }
                break;
            case EMERALD:
                switch (slot) {
                    case FEET:
                        if (enableEmeraldBEffect && !enableFullEmeraldArmorEffect)
                            addEffect(entity, getPotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel, GOOD);
                        break;
                    case LEGS:
                        if (enableEmeraldLEffect && !enableFullEmeraldArmorEffect)
                            addEffect(entity, getPotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel, GOOD);
                        break;
                    case CHEST:
                        if (enableEmeraldCEffect && !enableFullEmeraldArmorEffect)
                            addEffect(entity, getPotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel, GOOD);
                        break;
                    case HEAD:
                        if (enableEmeraldHEffect && !enableFullEmeraldArmorEffect)
                            addEffect(entity, getPotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel, GOOD);
                        break;
                }
                break;
            case LAPIS:
                switch (slot) {
                    case FEET:
                        if (enableLapisBEffect && !enableFullLapisArmorEffect)
                            addEffect(entity, getPotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel, GOOD);
                        break;
                    case LEGS:
                        if (enableLapisLEffect && !enableFullLapisArmorEffect)
                            addEffect(entity, getPotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel, GOOD);
                        break;
                    case CHEST:
                        if (enableLapisCEffect && !enableFullLapisArmorEffect)
                            addEffect(entity, getPotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel, GOOD);
                        break;
                    case HEAD:
                        if (enableLapisHEffect && !enableFullLapisArmorEffect)
                            addEffect(entity, getPotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel, GOOD);
                        break;
                }
                break;
            case LAVA:
                switch (slot) {
                    case FEET:
                        if (enableLavaBEffect && !enableFullLavaArmorEffect)
                            addEffect(entity, getPotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel, GOOD);
                        lavaEffects(entity, itemStack);
                        break;
                    case LEGS:
                        if (enableLavaLEffect && !enableFullLavaArmorEffect)
                            addEffect(entity, getPotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel, GOOD);
                        lavaEffects(entity, itemStack);
                        break;
                    case CHEST:
                        if (enableLavaCEffect && !enableFullLavaArmorEffect)
                            addEffect(entity, getPotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel, GOOD);
                        lavaEffects(entity, itemStack);
                        break;
                    case HEAD:
                        if (enableLavaHEffect && !enableFullLavaArmorEffect)
                            addEffect(entity, getPotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel, GOOD);
                        lavaEffects(entity, itemStack);
                        break;
                }
                break;
            case REDSTONE:
                switch (slot) {
                    case FEET:
                        if (enableRedstoneBEffect && !enableFullRedstoneArmorEffect) {
                            addEffect(entity, getPotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel, GOOD);
                            if (world.isRemote) {
                                spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case LEGS:
                        if (enableRedstoneLEffect && !enableFullRedstoneArmorEffect) {
                            addEffect(entity, getPotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel, GOOD);
                            if (world.isRemote) {
                                spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case CHEST:
                        if (enableRedstoneCEffect && !enableFullRedstoneArmorEffect) {
                            addEffect(entity, getPotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel, GOOD);
                            if (world.isRemote) {
                                spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                    case HEAD:
                        if (enableRedstoneHEffect && !enableFullRedstoneArmorEffect) {
                            addEffect(entity, getPotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel, GOOD);
                            if (world.isRemote) {
                                spawnParticle(entity, EnumParticleTypes.REDSTONE, entity.posX, entity.posY, entity.posZ);
                            }
                        }
                        break;
                }
                break;
            case OBSIDIAN:
                switch (slot) {
                    case FEET:
                        if (enableObsidianBEffect && !enableFullObsidianArmorEffect)
                            addEffect(entity, getPotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel, GOOD);
                        break;
                    case LEGS:
                        if (enableObsidianLEffect && !enableFullObsidianArmorEffect)
                            addEffect(entity, getPotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel, GOOD);
                        break;
                    case CHEST:
                        if (enableObsidianCEffect && !enableFullObsidianArmorEffect)
                            addEffect(entity, getPotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel, GOOD);
                        break;
                    case HEAD:
                        if (enableObsidianHEffect && !enableFullObsidianArmorEffect)
                            addEffect(entity, getPotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel, GOOD);
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
                if (getPotion(enderDragonArmorRemovePotionEffect) != null)
                    removeEffect(entity, getPotion(enderDragonArmorRemovePotionEffect));
                break;
            case SUPER_STAR:
                switch (slot) {
                    case FEET:
                        if (enableSuperStarBEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                                addEffect(entity, getPotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel, GOOD);
                        removeEffect(entity, getPotion(superStarArmorRemovePotionEffect));
                        break;
                    case LEGS:
                        if (enableSuperStarLEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                                addEffect(entity, getPotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel, GOOD);
                        removeEffect(entity, getPotion(superStarArmorRemovePotionEffect));
                        break;
                    case CHEST:
                        if (enableSuperStarCEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                                addEffect(entity, getPotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel, GOOD);
                        removeEffect(entity, getPotion(superStarArmorRemovePotionEffect));
                        break;
                    case HEAD:
                        if (enableSuperStarHEffect && !enableFullSuperStarArmorEffect)
                            if (entity.getActivePotionEffect(getPotion(superStarArmorAddPotionEffect)) == null)
                                addEffect(entity, getPotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel, GOOD);
                        removeEffect(entity, getPotion(superStarArmorRemovePotionEffect));
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

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        switch (material) {
            case COAL:
                if (isKeyDown()) {
                    if (!enableFullCoalArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(coalArmorAddPotionEffect), coalArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(coalArmorAddPotionEffect), coalArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case EMERALD:
                if (isKeyDown()) {
                    if (!enableFullEmeraldArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(emeraldArmorAddPotionEffect), emeraldArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case LAPIS:
                if (isKeyDown()) {
                    if (!enableFullLapisArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(lapisArmorAddPotionEffect), lapisArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case LAVA:
                if (isKeyDown()) {
                    if (!enableFullLavaArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(lavaArmorAddPotionEffect), lavaArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case REDSTONE:
                if (isKeyDown()) {
                    if (!enableFullRedstoneArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(redstoneArmorAddPotionEffect), redstoneArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case OBSIDIAN:
                if (isKeyDown()) {
                    if (!enableFullObsidianArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(obsidianArmorAddPotionEffect), obsidianArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case ENDER_DRAGON:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, "Flight");
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case GUARDIAN:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(guardianArmorAddPotionEffect), guardianArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case SLIME:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(slimeArmorAddPotionEffect), slimeArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case CHICKEN:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(chickenArmorAddPotionEffect), chickenArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case SUPER_STAR:
                if (isKeyDown()) {
                    if (!enableFullObsidianArmorEffect) {
                        addToolTipPiece(tooltip, localizePotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel);
                    } else {
                        addToolTipFull(tooltip, localizePotion(superStarArmorAddPotionEffect), superStarArmorEffectLevel);
                    }
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case ARDITE:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(arditeArmorAddPotionEffect), arditeArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case COBALT:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(cobaltArmorAddPotionEffect), cobaltArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case MANYULLYN:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(manyullynArmorAddPotionEffect), manyullynArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case KNIGHT_SLIME:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(knightSlimeArmorAddPotionEffect), knightSlimeArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
            case PIG_IRON:
                if (isKeyDown()) {
                    addToolTipFull(tooltip, localizePotion(pigIronArmorAddPotionEffect), pigIronArmorEffectLevel);
                } else
                    showInfo(tooltip, keyBindSneak);
                break;
        }
    }

    private void showInfo(List<String> tooltip, KeyBinding keyBinding) {
        tooltip.add(I18n.format("tooltip.shift.showinfo", formatting, keyBinding.getDisplayName(), TextFormatting.GRAY));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    private void addToolTipFull(List<String> tooltip, String ability, int amplifier) {
        this.addToolTipFull(tooltip, ability + " " + (amplifier + 1));
    }

    private void addToolTipFull(List<String> tooltip, String ability) {
        tooltip.add("\2479Ability: " + "\247r" + ability);
        tooltip.add("\2473Use: " + "\247rEquip The Full Set");
    }

    private void addToolTipPiece(List<String> tooltip, String ability, int amplifier) {
        this.addToolTipPiece(tooltip, ability + " " + (amplifier + 1));
    }

    private void addToolTipPiece(List<String> tooltip, String ability) {
        tooltip.add("\2479Ability: " + "\247r" + ability);
        tooltip.add("\2473Use: " + "\247rEquip A Piece");
    }

    public boolean isKeyDown() {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        return GameSettings.isKeyDown(keyBindSneak);
    }
}
