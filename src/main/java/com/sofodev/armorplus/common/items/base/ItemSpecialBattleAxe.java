/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.items.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.common.iface.IModdedItem;
import com.sofodev.armorplus.common.items.special.BattleAxes;
import com.sofodev.armorplus.common.util.ArmorPlusItemUtils;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.common.util.Utils.setRL;
import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemSpecialBattleAxe extends ItemSword implements IModdedItem {

    public static final ToolMaterial COAL_BATTLE_AXE = addToolMaterial("COAL_BATTLE_AXE", 1, coal.weapons.battle_axe.durability, 1.0F, (float) coal.weapons.battle_axe.damage, 15);
    public static final ToolMaterial LAPIS_BATTLE_AXE = addToolMaterial("LAPIS_BATTLE_AXE", 1, lapis.weapons.battle_axe.durability, 1.0F, (float) lapis.weapons.battle_axe.damage, 30);
    public static final ToolMaterial REDSTONE_BATTLE_AXE = addToolMaterial("REDSTONE_BATTLE_AXE", 1, redstone.weapons.battle_axe.durability, 1.0F, (float) redstone.weapons.battle_axe.damage, 20);
    public static final ToolMaterial EMERALD_BATTLE_AXE = addToolMaterial("EMERALD_BATTLE_AXE", 1, emerald.weapons.battle_axe.durability, 1.0F, (float) emerald.weapons.battle_axe.damage, 20);
    public static final ToolMaterial OBSIDIAN_BATTLE_AXE = addToolMaterial("OBSIDIAN_BATTLE_AXE", 1, obsidian.weapons.battle_axe.durability, 1.0F, (float) obsidian.weapons.battle_axe.damage, 20);
    public static final ToolMaterial INFUSED_LAVA_BATTLE_AXE = addToolMaterial("INFUSED_LAVA_BATTLE_AXE", 1, lava.weapons.battle_axe.durability, 1.0F, (float) lava.weapons.battle_axe.damage, 20);
    public static final ToolMaterial GUARDIAN_BATTLE_AXE = addToolMaterial("GUARDIAN_BATTLE_AXE", 1, guardian.weapons.battle_axe.durability, 1.0F, (float) guardian.weapons.battle_axe.damage, 30);
    public static final ToolMaterial SUPER_STAR_BATTLE_AXE = addToolMaterial("SUPER_STAR_BATTLE_AXE", 1, super_star.weapons.battle_axe.durability, 1.0F, (float) super_star.weapons.battle_axe.damage, 20);
    public static final ToolMaterial ENDER_DRAGON_BATTLE_AXE = addToolMaterial("ENDER_DRAGON_BATTLE_AXE", 1, ender_dragon.weapons.battle_axe.durability, 1.0F, (float) ender_dragon.weapons.battle_axe.damage, 20);
    public ItemStack itemExpert;
    public TextFormatting formatting;
    public List<String> effect;
    public BattleAxes battleAxes;
    public float efficiency;
    public String itemName;

    public ItemSpecialBattleAxe(BattleAxes battleAxes) {
        super(battleAxes.getToolMaterial());
        this.setHasSubtypes(true);
        this.itemName = battleAxes.getName();
        this.battleAxes = battleAxes;
        this.itemExpert = battleAxes.getRepairStack();
        this.formatting = battleAxes.getTextFormatting();
        this.effect = battleAxes.getEffects();
        this.efficiency = battleAxes.getEfficiency();
        this.setRegistryName(setRL(battleAxes.getName() + "_battle_axe"));
        this.setTranslationKey(Utils.setName(battleAxes.getName() + "_battle_axe"));
        this.setCreativeTab(ArmorPlus.tabArmorPlusWeapons);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity, EntityLivingBase attacker) {
        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if (stack == ItemStack.EMPTY || state == null) return 0.0F;
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : battleAxes.getEfficiency();
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        return battleAxes.hitEntity(stack, target, attacker);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        battleAxes.addInformation(tooltip);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    @Nonnull
    public IRarity getForgeRarity(ItemStack stack) {
        return this.getRarity(formatting, "Battle Axe");
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ArmorPlusItemUtils.isItemRepairable(repair, itemExpert);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(battleAxes.getName(), 0);
    }
}
