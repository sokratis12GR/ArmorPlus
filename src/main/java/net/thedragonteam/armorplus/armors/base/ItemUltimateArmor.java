/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.util.ArmorPlusItemUtils;
import net.thedragonteam.armorplus.util.Utils;

import java.util.List;

import static net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addArmorMaterial;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.addPotion;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;
import static net.thedragonteam.armorplus.util.Utils.setLocation;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ItemUltimateArmor extends ItemArmor implements IModelHelper {

    private static ArmorMaterial theUltimateArmor = addArmorMaterial("THE_ULTIMATE_ARMOR", setLocation("the_ultimate_armor"), 160,
            theUltimateArmorProtectionPoints, 1, ITEM_ARMOR_EQUIP_DIAMOND, theUltimateArmorToughnessPoints);

    private EnumRarity formattingName;

    public ItemUltimateArmor(EntityEquipmentSlot slot) {
        super(ItemUltimateArmor.getTheUltimateArmor(), 0, slot);
        this.setMaxStackSize(1);
        switch (slot) {
            case FEET:
                String boots = "the_ultimate_boots";
                this.setRegistryName(boots);
                this.setUnlocalizedName(setName(boots));
                break;
            case LEGS:
                String leggings = "the_ultimate_leggings";
                this.setRegistryName(leggings);
                this.setUnlocalizedName(setName(leggings));
                break;
            case CHEST:
                String chestplate = "the_ultimate_chestplate";
                this.setRegistryName(chestplate);
                this.setUnlocalizedName(setName(chestplate));
                break;
            case HEAD:
                String helmet = "the_ultimate_helmet";
                this.setRegistryName(helmet);
                this.setUnlocalizedName(setName(helmet));
                break;
        }
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplus);
        this.formattingName = addRarity("ULTIMATE_ARMOR_COLOR", theUltimateArmorItemNameColor, "Ultimate Armor Color");
    }

    public static ArmorMaterial getTheUltimateArmor() {
        return theUltimateArmor;
    }

    public static void setTheUltimateArmor(ArmorMaterial theUltimateArmor) {
        ItemUltimateArmor.theUltimateArmor = theUltimateArmor;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return this.formattingName;
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
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\u00a79Question: \u00a7rAre you the chosen one ?");
            tooltip.add("\u00a73Use: \u00a7rEquip The Full Set");//getValueByName(theUltimateArmorItemNameColor)!!
        } else {
            tooltip.add(formattedText(GRAY, "tooltip.showinfo.beginning", formattedText(getValueByName(theUltimateArmorItemNameColor), "tooltip.showinfo.keybind", keyBindSneak.getDisplayName(),
                    formattedText(GRAY, "tooltip.showinfo.end"))));
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (makeTheUltimateArmorUnbreakable)
            Utils.setUnbreakable(stack);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        onArmorTick(player);
    }

    public static void onArmorTick(EntityPlayer player) {
        ItemStack head = player.getItemStackFromSlot(HEAD);
        ItemStack chest = player.getItemStackFromSlot(CHEST);
        ItemStack legs = player.getItemStackFromSlot(LEGS);
        ItemStack feet = player.getItemStackFromSlot(FEET);
        if (APConfig.enableFlightAbility) {
            if (head.getItem() == theUltimateHelmet && chest.getItem() == theUltimateChestplate && legs.getItem() == theUltimateLeggings &&
                    feet.getItem() == theUltimateBoots || player.capabilities.isCreativeMode || player.isSpectator()) {
                player.capabilities.allowFlying = true;
            } else {
                player.capabilities.isFlying = false;
                player.capabilities.allowFlying = false;
            }
        }
        if (APConfig.enableTheUltimateArmorInvincibility)
            player.capabilities.disableDamage = head.getItem() == theUltimateHelmet && chest.getItem() == theUltimateChestplate && legs.getItem() == theUltimateLeggings && feet.getItem() == theUltimateBoots || player.capabilities.isCreativeMode || player.isSpectator();
        if (head.isEmpty() || head.getItem() != theUltimateHelmet || chest.isEmpty() || chest.getItem() != theUltimateChestplate || legs.isEmpty() || legs.getItem() != theUltimateLeggings || feet.isEmpty() || feet.getItem() != theUltimateBoots) {
            if (!player.capabilities.isCreativeMode && !player.isSpectator() && enableTheUltimateArmorDeBuffs) {
                addPotion(player, MobEffects.POISON, 60, 2, BAD);
                addPotion(player, MobEffects.SLOWNESS, 60, 2, BAD);
                addPotion(player, MobEffects.BLINDNESS, 60, 0, BAD);

                player.motionX = 0.0;
                if (player.onGround) player.motionY = 0.0;
                player.motionZ = 0.0;
                player.velocityChanged = true; // assumes that player instanceof EntityPlayer
            }
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ArmorPlusItemUtils.INSTANCE.isItemRepairable(repair, getItemStack(materials, 4), getItemStack(materials, 4));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(this, getRegistryName(), 0);
    }
}
