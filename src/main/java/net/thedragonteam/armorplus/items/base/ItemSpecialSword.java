/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.enums.Swords;
import net.thedragonteam.armorplus.util.Utils;

import java.util.List;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;
import static net.thedragonteam.thedragonlib.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.thedragonlib.util.PotionUtils.addPotion;
import static net.thedragonteam.thedragonlib.util.PotionUtils.getPotion;

public class ItemSpecialSword extends ItemSword implements IItemHelper {

    public static Item.ToolMaterial swordCoalMaterial = EnumHelper.addToolMaterial("swordCoalMaterial", 1, APConfig.coalSwordDurability, 1.0F, (float) APConfig.coalSwordDamage, 15);
    public static Item.ToolMaterial swordLapisMaterial = EnumHelper.addToolMaterial("swordLapisMaterial", 1, APConfig.lapisSwordDurability, 1.0F, (float) APConfig.lapisSwordDamage, 30);
    public static Item.ToolMaterial swordRedstoneMaterial = EnumHelper.addToolMaterial("swordRedstoneMaterial", 1, APConfig.redstoneSwordDurability, 1.0F, (float) APConfig.redstoneSwordDamage, 20);
    public static Item.ToolMaterial swordEmeraldMaterial = EnumHelper.addToolMaterial("swordEmeraldMaterial", 1, APConfig.emeraldSwordDurability, 1.0F, (float) APConfig.emeraldSwordDamage, 20);
    public static Item.ToolMaterial swordObsidianMaterial = EnumHelper.addToolMaterial("swordObsidianMaterial", 1, APConfig.obsidianSwordDurability, 1.0F, (float) APConfig.obsidianSwordDamage, 20);
    public static Item.ToolMaterial swordLavaMaterial = EnumHelper.addToolMaterial("swordLavaMaterial", 1, APConfig.lavaSwordDurability, 1.0F, (float) APConfig.lavaSwordDamage, 20);
    public static Item.ToolMaterial swordGuardianMaterial = EnumHelper.addToolMaterial("swordGuardianMaterial", 1, APConfig.guardianSwordDurability, 1.0F, (float) APConfig.guardianSwordDamage, 30);
    public static Item.ToolMaterial swordSuperStarMaterial = EnumHelper.addToolMaterial("swordSuperStarMaterial", 1, APConfig.superStarSwordDurability, 1.0F, (float) APConfig.superStarSwordDamage, 20);
    public static Item.ToolMaterial swordEnderDragonMaterial = EnumHelper.addToolMaterial("swordEnderDragonMaterial", 1, APConfig.enderDragonSwordDurability, 1.0F, (float) APConfig.enderDragonSwordDamage, 20);
    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;
    public EnumRarity formattingName;
    public String effect;
    public String itemName;
    private Swords swords;

    public ItemSpecialSword(Swords swords) {
        super(swords.getToolMaterial());
        this.swords = swords;
        this.itemName = swords.getName();
        this.itemEasy = swords.getRepairEasy();
        this.itemExpert = swords.getRepairExpert();
        this.formatting = swords.getTextFormatting();
        this.effect = swords.getEffect();
        setRegistryName(swords.getName() + "_sword");
        setUnlocalizedName(Utils.setName(swords.getName() + "_sword"));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        formattingName = addRarity("SPECIAL_SWORD", formatting, "Special Sword");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        switch (swords) {
            case COAL:
                if (enableCoalWeaponsEffects)
                    addPotion(target, getPotion(coalWeaponsAddPotionEffect), 180, coalWeaponsEffectLevel, BAD);
                break;
            case LAPIS:
                if (enableLapisWeaponsEffects)
                    addPotion(target, getPotion(lapisWeaponsAddPotionEffect), lapisWeaponsEffectLevel, BAD);
                break;
            case REDSTONE:
                if (enableRedstoneWeaponsEffects)
                    addPotion(target, getPotion(redstoneWeaponsAddPotionEffect), 180, redstoneWeaponsEffectLevel, BAD);
                break;
            case EMERALD:
                if (enableEmeraldWeaponsEffects)
                    addPotion(target, getPotion(emeraldWeaponsAddPotionEffect), emeraldWeaponsEffectLevel, BAD);
                break;
            case OBSIDIAN:
                if (enableObsidianWeaponsEffects)
                    addPotion(target, getPotion(obsidianWeaponsAddPotionEffect), obsidianWeaponsEffectLevel, BAD);
                break;
            case LAVA:
                target.setFire(8);
                break;
            case GUARDIAN:
                if (enableGuardianWeaponsEffects)
                    addPotion(target, getPotion(guardianWeaponsAddPotionEffect), guardianWeaponsEffectLevel, BAD);
                break;
            case SUPER_STAR:
                if (enableSuperStarWeaponsEffects)
                    addPotion(target, getPotion(superStarWeaponsAddPotionEffect), superStarWeaponsEffectLevel, BAD);
                break;
            case ENDER_DRAGON:
                if (enableEnderDragonWeaponsEffects)
                    addPotion(target, getPotion(enderDragonWeaponsAddPotionEffect), 60, enderDragonWeaponsEffectLevel, BAD);
                break;
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247r" + effect);
            tooltip.add("\2473Use: " + "\247rHit a Target");
        } else
            tooltip.add(I18n.format("tooltip.tesla.showinfo", formatting, keyBindSneak.getDisplayName(), TextFormatting.GRAY));
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return formattingName;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        switch (getRD()) {
            case EASY:
                return repair.getItem() == itemEasy;
            case EXPERT:
                return repair.getItem() == itemExpert;
            case HELLISH:
                return false;
        }
        return true;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public ItemStack getItemStack(ItemStack stack) {
        return stack;
    }

    @Override
    public Item getItem(Item item) {
        return item;
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(this);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public String getName(String name) {
        return name;
    }

    @Override
    public String getName() {
        return this.itemName;
    }
}