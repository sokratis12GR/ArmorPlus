/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModdedItem;
import net.thedragonteam.armorplus.registry.ModPotions;
import net.thedragonteam.armorplus.util.PotionUtils;
import net.thedragonteam.armorplus.util.Utils;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.Misc.enableFlightAbility;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.ultimate;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.isItemRepairable;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addArmorMaterial;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ItemUltimateArmor extends ItemArmor implements IModdedItem {

    public static ArmorMaterial theUltimateArmor = addArmorMaterial("THE_ULTIMATE_ARMOR", "the_ultimate_armor", 160,
        ultimate.armor.protectionPoints, 1, ITEM_ARMOR_EQUIP_DIAMOND, ultimate.armor.toughnessPoints);

    public ItemUltimateArmor(EntityEquipmentSlot slot) {
        super(ItemUltimateArmor.theUltimateArmor, 0, slot);
        this.setMaxStackSize(1);
        this.createPieces(slot);
        this.setCreativeTab(ArmorPlus.tabArmorplus);
    }

    private void createPieces(EntityEquipmentSlot slot) {
        String piece = slot == HEAD ? "the_ultimate_helmet" : slot == CHEST ? "the_ultimate_chestplate" : slot == LEGS ? "the_ultimate_leggings" : slot == FEET ? "the_ultimate_boots" : "";
        this.setRegistryName(piece);
        this.setUnlocalizedName(setName(piece));
    }

    public static void onArmorTick(EntityPlayer player) {
        ItemStack head = player.getItemStackFromSlot(HEAD);
        ItemStack chest = player.getItemStackFromSlot(CHEST);
        ItemStack legs = player.getItemStackFromSlot(LEGS);
        ItemStack feet = player.getItemStackFromSlot(FEET);
        if (enableFlightAbility) {
            if (head.getItem() == theUltimateHelmet && chest.getItem() == theUltimateChestplate && legs.getItem() == theUltimateLeggings &&
                feet.getItem() == theUltimateBoots || player.capabilities.isCreativeMode || player.isSpectator()) {
                player.capabilities.allowFlying = true;
            } else {
                player.capabilities.isFlying = false;
                player.capabilities.allowFlying = false;
            }
        }
        if (ultimate.armor.setInvincible) {
            player.capabilities.disableDamage = head.getItem() == theUltimateHelmet && chest.getItem() == theUltimateChestplate && legs.getItem() == theUltimateLeggings && feet.getItem() == theUltimateBoots || player.capabilities.isCreativeMode || player.isSpectator();
            addPotion(player, MobEffects.SATURATION, 120, 0, GOOD);
        }
        if (!head.isEmpty() && head.getItem() == theUltimateHelmet && !chest.isEmpty() && chest.getItem() == theUltimateChestplate && !legs.isEmpty() && legs.getItem() == theUltimateLeggings && !feet.isEmpty() && feet.getItem() == theUltimateBoots) {
            IntStream.range(0, ultimate.armor.addPotionEffects.length).forEach(i -> addPotion(player, getPotion(ultimate.armor.addPotionEffects[i]), 120, ultimate.armor.effectLevels[i], GOOD));
            List<Potion> removablePotions = Arrays.stream(ultimate.armor.removePotionEffects).map(PotionUtils::getPotion).collect(Collectors.toList());
            removablePotions.stream().filter(
                potionEffect -> Utils.isNotNull(potionEffect) && potionEffect != ModPotions.EMPTY
            ).forEach(
                potionEffect -> removePotion(player, potionEffect)
            );
        } else if (ultimate.armor.enableDeBuffs) {
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
            tooltip.add("\u00a73Use: \u00a7rEquip The Full Set");
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
            LogHelper.info("Making The Ultimate Armor Unbreakable!");
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
