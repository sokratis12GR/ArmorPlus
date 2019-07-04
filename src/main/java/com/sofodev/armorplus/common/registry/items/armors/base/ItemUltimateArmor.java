/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.armors.base;

import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.UltimateMaterial.Armor;
import com.sofodev.armorplus.common.util.LoaderUtils;
import com.sofodev.armorplus.common.util.Utils;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.sofodev.armorplus.client.utils.ToolTipUtils.showInfo;
import static com.sofodev.armorplus.common.config.ModConfig.IntegrationsConfig.ultimateChaosImmunity;
import static com.sofodev.armorplus.common.config.ModConfig.Misc.enableFlightAbility;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.ultimate;
import static com.sofodev.armorplus.common.registry.ModItems.materials;
import static com.sofodev.armorplus.common.registry.constants.APItems.*;
import static com.sofodev.armorplus.common.registry.items.armors.ArmorMaterials.THE_ULTIMATE_ARMOR;
import static com.sofodev.armorplus.common.util.ArmorPlusItemUtils.*;
import static com.sofodev.armorplus.common.util.PotionUtils.PotionType.BAD;
import static com.sofodev.armorplus.common.util.PotionUtils.PotionType.GOOD;
import static com.sofodev.armorplus.common.util.PotionUtils.addPotion;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemUltimateArmor extends ItemArmorBase implements IEffectHolder {

    private Armor armor = ultimate.armor;

    public ItemUltimateArmor(EntityEquipmentSlot slot) {
        super(THE_ULTIMATE_ARMOR, slot, "the_ultimate", "ultimate");
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
    public IRarity getForgeRarity(ItemStack stack) {
        return this.getRarity(ultimate.armor.itemNameColor, "Ultimate Armor Color");
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (LoaderUtils.isDELoaded() && ultimateChaosImmunity) {
            String out = new TextComponentTranslation("misc.armorplus.chaos.resistance.max").getFormattedText();
            tooltip.add(TextFormatting.DARK_PURPLE + out);
        }
        if (GameSettings.isKeyDown(keyBindSneak)) {
            if (isFullSet(Minecraft.getMinecraft().player, theUltimateHelmet, theUltimateChestplate, theUltimateLeggings, theUltimateBoots)) {
                tooltip.add("\u00a79Answer: \u00a7rYou are the chosen one!");
                tooltip.add("\u00a7cSet is completed, you now hold the ultimate power");
            } else {
                tooltip.add("\u00a79Question: \u00a7rAre you the chosen one ?");
                tooltip.add("\u00a7cRequires the full set equipped");
            }
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
}
