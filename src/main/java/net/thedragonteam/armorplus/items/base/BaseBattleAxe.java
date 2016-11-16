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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.BattleAxes;

import java.util.List;

import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;
import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.armorplus.util.PotionUtils.addEffect;
import static net.thedragonteam.armorplus.util.PotionUtils.getPotion;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseBattleAxe extends ItemSword {

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

    public BaseBattleAxe(BattleAxes battleAxes) {
        super(battleAxes.getToolMaterial());
        setHasSubtypes(true);
        this.battleAxes = battleAxes;
        this.itemEasy = battleAxes.getRepairEasy();
        this.itemExpert = battleAxes.getRepairExpert();
        this.formatting = battleAxes.getTextFormatting();
        this.effect = battleAxes.getEffect();
        this.efficiency = battleAxes.getEfficiency();
        setRegistryName(battleAxes.getName() + "_battle_axe");
        setUnlocalizedName(setName(battleAxes.getName() + "_battle_axe"));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
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
                    addEffect(target, getPotion(coalWeaponsAddPotionEffect), 180, coalWeaponsEffectLevel, 'B');
                break;
            case LAPIS:
                if (enableLapisWeaponsEffects)
                    addEffect(target, getPotion(lapisWeaponsAddPotionEffect), lapisWeaponsEffectLevel, 'B');
                break;
            case REDSTONE:
                if (enableRedstoneWeaponsEffects)
                    addEffect(target, getPotion(redstoneWeaponsAddPotionEffect), 180, redstoneWeaponsEffectLevel, 'B');
                break;
            case EMERALD:
                if (enableEmeraldWeaponsEffects)
                    addEffect(target, getPotion(emeraldWeaponsAddPotionEffect), emeraldWeaponsEffectLevel, 'B');
                break;
            case OBSIDIAN:
                if (enableObsidianWeaponsEffects)
                    addEffect(target, getPotion(obsidianWeaponsAddPotionEffect), obsidianWeaponsEffectLevel, 'B');
                break;
            case LAVA:
                target.setFire(8);
                break;
            case GUARDIAN:
                if (enableGuardianWeaponsEffects)
                    addEffect(target, getPotion(guardianWeaponsAddPotionEffect), guardianWeaponsEffectLevel, 'B');
                break;
            case SUPER_STAR:
                if (enableSuperStarWeaponsEffects)
                    addEffect(target, getPotion(superStarWeaponsAddPotionEffect), superStarWeaponsEffectLevel, 'B');
                break;
            case ENDER_DRAGON:
                if (enableEnderDragonWeaponsEffects)
                    addEffect(target, getPotion(enderDragonWeaponsAddPotionEffect), 60, enderDragonWeaponsEffectLevel, 'B');
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
    public String getItemStackDisplayName(ItemStack stack) {
        return (formatting + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        switch (recipes) {
            case 0:
                return repair.getItem() == itemEasy;
            case 1:
                return repair.getItem() == itemExpert;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
