/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.armors.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.config.ModConfig.RegistryConfig.UltimateMaterial.Armor;
import com.sofodev.armorplus.iface.IModdedItem;
import com.sofodev.armorplus.util.EnumHelperUtil;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.sofodev.armorplus.client.utils.ToolTipUtils.showInfo;
import static com.sofodev.armorplus.config.ModConfig.Misc.enableFlightAbility;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.ultimate;
import static com.sofodev.armorplus.registry.APItems.*;
import static com.sofodev.armorplus.registry.ModItems.materials;
import static com.sofodev.armorplus.util.ArmorPlusItemUtils.*;
import static com.sofodev.armorplus.util.PotionUtils.PotionType.BAD;
import static com.sofodev.armorplus.util.PotionUtils.PotionType.GOOD;
import static com.sofodev.armorplus.util.PotionUtils.addPotion;
import static com.sofodev.armorplus.util.Utils.setName;
import static com.sofodev.armorplus.util.Utils.setRL;
import static net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemUltimateArmor extends ItemArmor implements IModdedItem, IEffectHolder {

    public static final ArmorMaterial theUltimateArmor = EnumHelperUtil.addArmorMaterial("THE_ULTIMATE_ARMOR", "the_ultimate_armor", 160,
        ultimate.armor.protectionPoints, 1, ITEM_ARMOR_EQUIP_DIAMOND, ultimate.armor.toughnessPoints);
    private Armor armor = ultimate.armor;

    public ItemUltimateArmor(EntityEquipmentSlot slot) {
        super(ItemUltimateArmor.theUltimateArmor, 0, slot);
        this.setMaxStackSize(1);
        this.createPieces(slot);
        this.setCreativeTab(ArmorPlus.tabArmorplus);
    }

    private void createPieces(EntityEquipmentSlot slot) {
        String piece;
        if (slot == HEAD) {
            piece = "the_ultimate_helmet";
        } else if (slot == CHEST) {
            piece = "the_ultimate_chestplate";
        } else if (slot == LEGS) {
            piece = "the_ultimate_leggings";
        } else piece = slot == FEET ? "the_ultimate_boots" : "";
        this.setRegistryName(setRL(piece));
        this.setTranslationKey(setName(piece));
    }

    public void onArmorTick(EntityPlayer player) {
        PlayerCapabilities caps = player.capabilities;
        boolean isFullSet = isFullSet(player, theUltimateHelmet, theUltimateChestplate, theUltimateLeggings, theUltimateBoots);
        boolean isAllowed = isFullSet || caps.isCreativeMode || player.isSpectator();
        if (enableFlightAbility) {
            if (isAllowed) {
                caps.allowFlying = true;
            } else {
                caps.isFlying = false;
                caps.allowFlying = false;
            }
        }
        if (armor.setInvincible) {
            caps.disableDamage = isAllowed;
            addPotion(player, MobEffects.SATURATION, 120, 0, GOOD);
        }
        if (isFullSet) {
            applyEffects(player, this);
        } else if (armor.enableDeBuffs) {
            addPotion(player, MobEffects.POISON, 60, 2, BAD);
            addPotion(player, MobEffects.SLOWNESS, 60, 2, BAD);
            addPotion(player, MobEffects.BLINDNESS, 60, 0, BAD);

            player.motionX = 0.0;
            if (player.onGround) player.motionY = 0.0;
            player.motionZ = 0.0;
            player.velocityChanged = true; // assumes that player instanceof EntityPlayer
        }
    }


    @Override
    public AbilityProvider getApplicableAbilities() {
        return new AbilityProvider(armor.addPotionEffects, armor.effectLevels, armor.effectDurations);
    }

    @Override
    public AbilityCanceller getRemovableAbilities() {
        return new AbilityCanceller(armor.removePotionEffects);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return this.getRarity("ULTIMATE_ARMOR_COLOR", ultimate.armor.itemNameColor, "Ultimate Armor Color");
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\u00a79Question: \u00a7rAre you the chosen one ?");
            tooltip.add("\u00a7cRequires the full set equipped");
        } else {
            showInfo(tooltip, keyBindSneak, getValueByName(ultimate.armor.itemNameColor));
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        boolean isUnbreakable = false;
        if (tagCompound != null) {
            isUnbreakable = tagCompound.getBoolean("Unbreakable");
        }
        if (ultimate.armor.setUnbreakable && !isUnbreakable) {
            Utils.setUnbreakable(stack);
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        onArmorTick(player);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return isItemRepairable(repair, getItemStack(materials, 4));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel("ultimate");
    }
}
