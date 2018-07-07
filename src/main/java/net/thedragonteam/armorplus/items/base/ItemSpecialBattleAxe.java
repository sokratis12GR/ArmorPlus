/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModdedItem;
import net.thedragonteam.armorplus.items.weapons.BattleAxes;
import net.thedragonteam.armorplus.util.ArmorPlusItemUtils;
import net.thedragonteam.armorplus.util.Utils;

import javax.annotation.Nonnull;
import java.util.List;

import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ItemSpecialBattleAxe extends ItemSword implements IModdedItem {

    public static ToolMaterial battleAxeCoalMaterial = addToolMaterial("battleAxeCoalMaterial", 1, coal.weapons.battle_axe.durability, 1.0F, (float) coal.weapons.battle_axe.damage, 15);
    public static ToolMaterial battleAxeLapisMaterial = addToolMaterial("battleAxeLapisMaterial", 1, lapis.weapons.battle_axe.durability, 1.0F, (float) lapis.weapons.battle_axe.damage, 30);
    public static ToolMaterial battleAxeRedstoneMaterial = addToolMaterial("battleAxeRedstoneMaterial", 1, redstone.weapons.battle_axe.durability, 1.0F, (float) redstone.weapons.battle_axe.damage, 20);
    public static ToolMaterial battleAxeEmeraldMaterial = addToolMaterial("battleAxeEmeraldMaterial", 1, emerald.weapons.battle_axe.durability, 1.0F, (float) emerald.weapons.battle_axe.damage, 20);
    public static ToolMaterial battleAxeObsidianMaterial = addToolMaterial("battleAxeObsidianMaterial", 1, obsidian.weapons.battle_axe.durability, 1.0F, (float) obsidian.weapons.battle_axe.damage, 20);
    public static ToolMaterial battleAxeLavaMaterial = addToolMaterial("battleAxeLavaMaterial", 1, lava.weapons.battle_axe.durability, 1.0F, (float) lava.weapons.battle_axe.damage, 20);
    public static ToolMaterial battleAxeGuardianMaterial = addToolMaterial("battleAxeGuardianMaterial", 1, guardian.weapons.battle_axe.durability, 1.0F, (float) guardian.weapons.battle_axe.damage, 30);
    public static ToolMaterial battleAxeSuperStarMaterial = addToolMaterial("battleAxeSuperStarMaterial", 1, super_star.weapons.battle_axe.durability, 1.0F, (float) super_star.weapons.battle_axe.damage, 20);
    public static ToolMaterial battleAxeEnderDragonMaterial = addToolMaterial("battleAxeEnderDragonMaterial", 1, ender_dragon.weapons.battle_axe.durability, 1.0F, (float) ender_dragon.weapons.battle_axe.damage, 20);
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
        this.setUnlocalizedName(Utils.setName(battleAxes.getName() + "_battle_axe"));
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
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
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return this.getRarity("BATTLE_AXE", formatting, "Battle Axe");
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
