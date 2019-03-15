/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.base;

import com.sofodev.armorplus.items.weapons.BattleAxes;
import com.sofodev.armorplus.util.ArmorPlusItemUtils;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.*;
import static com.sofodev.armorplus.util.EnumHelperUtil.addMaterial;
import static net.minecraft.init.Blocks.OBSIDIAN;
import static net.minecraft.init.Items.*;
import static net.minecraft.item.crafting.Ingredient.fromItems;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemSpecialBattleAxe extends ItemSword {

    public static final IItemTier BATTLE_AXE_COAL_MATERIAL = addMaterial(1, coal.weapons.battle_axe.durability, 1.0F, (float) coal.weapons.battle_axe.damage, 15, () -> () -> fromItems(COAL));
    public static final IItemTier BATTLE_AXE_LAPIS_MATERIAL = addMaterial(1, lapis.weapons.battle_axe.durability, 1.0F, (float) lapis.weapons.battle_axe.damage, 30, () -> () -> fromItems(LAPIS_LAZULI));
    public static final IItemTier BATTLE_AXE_REDSTONE_MATERIAL = addMaterial(1, redstone.weapons.battle_axe.durability, 1.0F, (float) redstone.weapons.battle_axe.damage, 20, () -> () -> fromItems(REDSTONE));
    public static final IItemTier BATTLE_AXE_EMERALD_MATERIAL = addMaterial(1, emerald.weapons.battle_axe.durability, 1.0F, (float) emerald.weapons.battle_axe.damage, 20, () -> () -> fromItems(EMERALD));
    public static final IItemTier BATTLE_AXE_OBSIDIAN_MATERIAL = addMaterial(1, obsidian.weapons.battle_axe.durability, 1.0F, (float) obsidian.weapons.battle_axe.damage, 20, () -> () -> fromItems(OBSIDIAN));
    public static final IItemTier BATTLE_AXE_LAVA_MATERIAL = addMaterial(1, lava.weapons.battle_axe.durability, 1.0F, (float) lava.weapons.battle_axe.damage, 20, () -> () -> fromItems(Utils.getItem("infused_lava_crystal")));
    public static final IItemTier BATTLE_AXE_GUARDIAN_MATERIAL = addMaterial(1, guardian.weapons.battle_axe.durability, 1.0F, (float) guardian.weapons.battle_axe.damage, 30, () -> () -> fromItems(Utils.getItem("guardian_scale")));
    public static final IItemTier BATTLE_AXE_SUPER_STAR_MATERIAL = addMaterial(1, super_star.weapons.battle_axe.durability, 1.0F, (float) super_star.weapons.battle_axe.damage, 20, () -> () -> fromItems(Utils.getItem("wither_bone")));
    public static final IItemTier BATTLE_AXE_ENDER_DRAGON_MATERIAL = addMaterial(1, ender_dragon.weapons.battle_axe.durability, 1.0F, (float) ender_dragon.weapons.battle_axe.damage, 20, () -> () -> fromItems(Utils.getItem("ender_dragon_scale")));

    public ItemStack itemExpert;
    public TextFormatting formatting;
    public List<String> effect;
    public BattleAxes battleAxes;
    public float efficiency;
    public String itemName;

    public ItemSpecialBattleAxe(BattleAxes battleAxes, Properties properties) {
        super(battleAxes.getToolMaterial(), 3, (float) -2.4D, properties);
        this.itemName = battleAxes.getName();
        this.battleAxes = battleAxes;
        this.itemExpert = battleAxes.getRepairStack();
        this.formatting = battleAxes.getTextFormatting();
        this.effect = battleAxes.getEffects();
        this.efficiency = battleAxes.getEfficiency();
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        battleAxes.addInformation(tooltip);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ArmorPlusItemUtils.isItemRepairable(repair, itemExpert);
    }
}
