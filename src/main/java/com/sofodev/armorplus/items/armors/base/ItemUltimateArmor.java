/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.armors.base;

import com.sofodev.armorplus.api.properties.AbilityCanceller;
import com.sofodev.armorplus.api.properties.AbilityProvider;
import com.sofodev.armorplus.api.properties.iface.IEffectHolder;
import com.sofodev.armorplus.client.utils.ToolTipUtils;
import com.sofodev.armorplus.config.ModConfig.RegistryConfig.UltimateMaterial.Armor;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.client.Minecraft;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.client.utils.ToolTipUtils.showInfo;
import static com.sofodev.armorplus.config.ModConfig.Misc.enableFlightAbility;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.ultimate;
import static com.sofodev.armorplus.registry.APItems.*;
import static com.sofodev.armorplus.util.ArmorPlusItemUtils.*;
import static com.sofodev.armorplus.util.PotionUtils.PotionValue.BAD;
import static com.sofodev.armorplus.util.PotionUtils.PotionValue.GOOD;
import static com.sofodev.armorplus.util.PotionUtils.addPotion;
import static net.minecraft.util.text.TextFormatting.getValueByName;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemUltimateArmor extends ItemPlaceholderArmor implements IEffectHolder {

    private Armor armor = ultimate.armor;

    public ItemUltimateArmor(EntityEquipmentSlot slot, Properties properties) {
        super(THE_ULTIMATE_ARMOR, slot, properties);
    }

    public void onArmorTick(EntityPlayer player) {
        PlayerCapabilities caps = player.abilities;
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
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        final KeyBinding keyBindSneak = Minecraft.getInstance().gameSettings.keyBindSneak;
        if (ToolTipUtils.isKeyDown()) {
            tooltip.add(new TextComponentString("\u00a79Question: \u00a7rAre you the chosen one ?"));
            tooltip.add(new TextComponentString("\u00a7cRequires the full set equipped"));
        } else {
            showInfo(tooltip, keyBindSneak, getValueByName(ultimate.armor.itemNameColor));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        NBTTagCompound tagCompound = stack.getTag();
        boolean isUnbreakable = false;
        if (tagCompound != null) {
            isUnbreakable = tagCompound.getBoolean("Unbreakable");
        }
        if (ultimate.armor.setUnbreakable && !isUnbreakable) {
            Utils.setUnbreakable(stack);
        }
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        this.onArmorTick(player);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return isItemRepairable(repair, new ItemStack(Utils.getItem("the_ultimate_material")));
    }

}
