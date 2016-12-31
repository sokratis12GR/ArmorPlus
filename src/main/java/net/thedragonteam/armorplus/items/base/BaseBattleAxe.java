/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.enums.BattleAxes;
import net.thedragonteam.armorplus.util.Utils;

import java.util.List;

import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.addPotion;
import static net.thedragonteam.armorplus.util.PotionUtils.getPotion;

public class BaseBattleAxe extends ItemSword implements IItemHelper {

    public static ToolMaterial battleAxeCoalMaterial = addToolMaterial("battleAxeCoalMaterial", 1, coalBattleAxeDurability, 1.0F, (float) coalBattleAxeDamage, 15);
    public static ToolMaterial battleAxeLapisMaterial = addToolMaterial("battleAxeLapisMaterial", 1, lapisBattleAxeDurability, 1.0F, (float) lapisBattleAxeDamage, 30);
    public static ToolMaterial battleAxeRedstoneMaterial = addToolMaterial("battleAxeRedstoneMaterial", 1, redstoneBattleAxeDurability, 1.0F, (float) redstoneBattleAxeDamage, 20);
    public static ToolMaterial battleAxeEmeraldMaterial = addToolMaterial("battleAxeEmeraldMaterial", 1, emeraldBattleAxeDurability, 1.0F, (float) emeraldBattleAxeDamage, 20);
    public static ToolMaterial battleAxeObsidianMaterial = addToolMaterial("battleAxeObsidianMaterial", 1, obsidianBattleAxeDurability, 1.0F, (float) obsidianBattleAxeDamage, 20);
    public static ToolMaterial battleAxeLavaMaterial = addToolMaterial("battleAxeLavaMaterial", 1, lavaBattleAxeDurability, 1.0F, (float) lavaBattleAxeDamage, 20);
    public static ToolMaterial battleAxeGuardianMaterial = addToolMaterial("battleAxeGuardianMaterial", 1, guardianBattleAxeDurability, 1.0F, (float) guardianBattleAxeDamage, 30);
    public static ToolMaterial battleAxeSuperStarMaterial = addToolMaterial("battleAxeSuperStarMaterial", 1, superStarBattleAxeDurability, 1.0F, (float) superStarBattleAxeDamage, 20);
    public static ToolMaterial battleAxeEnderDragonMaterial = addToolMaterial("battleAxeEnderDragonMaterial", 1, enderDragonBattleAxeDurability, 1.0F, (float) enderDragonBattleAxeDamage, 20);
    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;
    public String effect;
    public BattleAxes battleAxes;
    public float efficiency;
    public EnumRarity formattingName;
    public String itemName;

    public BaseBattleAxe(BattleAxes battleAxes) {
        super(battleAxes.getToolMaterial());
        setHasSubtypes(true);
        this.itemName = battleAxes.getName();
        this.battleAxes = battleAxes;
        this.itemEasy = battleAxes.getRepairEasy();
        this.itemExpert = battleAxes.getRepairExpert();
        this.formatting = battleAxes.getTextFormatting();
        this.effect = battleAxes.getEffect();
        this.efficiency = battleAxes.getEfficiency();
        setRegistryName(battleAxes.getName() + "_battle_axe");
        setUnlocalizedName(Utils.setName(battleAxes.getName() + "_battle_axe"));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        formattingName = addRarity("BATTLE_AXE", formatting, "Battle Axe");
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getStrVsBlock(stack, state) : battleAxes.getEfficiency();
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        switch (battleAxes) {
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
            tooltip.add("\2479Ability: " + "\247rApplies " + effect);
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
        switch (getRecipesDifficulty()) {
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
