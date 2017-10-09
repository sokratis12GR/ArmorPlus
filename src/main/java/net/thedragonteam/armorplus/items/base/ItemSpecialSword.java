/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

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
import net.thedragonteam.armorplus.items.enums.Swords;
import net.thedragonteam.armorplus.util.ArmorPlusItemUtils;

import javax.annotation.Nonnull;
import java.util.List;

import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.util.Utils.setName;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ItemSpecialSword extends ItemSword implements IModdedItem {

    public static ToolMaterial swordCoalMaterial = addToolMaterial("swordCoalMaterial", 1, coalSwordDurability, 1.0F, (float) coalSwordDamage, 15);
    public static ToolMaterial swordLapisMaterial = addToolMaterial("swordLapisMaterial", 1, lapisSwordDurability, 1.0F, (float) lapisSwordDamage, 30);
    public static ToolMaterial swordRedstoneMaterial = addToolMaterial("swordRedstoneMaterial", 1, redstoneSwordDurability, 1.0F, (float) redstoneSwordDamage, 20);
    public static ToolMaterial swordEmeraldMaterial = addToolMaterial("swordEmeraldMaterial", 1, emeraldSwordDurability, 1.0F, (float) emeraldSwordDamage, 20);
    public static ToolMaterial swordObsidianMaterial = addToolMaterial("swordObsidianMaterial", 1, obsidianSwordDurability, 1.0F, (float) obsidianSwordDamage, 20);
    public static ToolMaterial swordLavaMaterial = addToolMaterial("swordLavaMaterial", 1, lavaSwordDurability, 1.0F, (float) lavaSwordDamage, 20);
    public static ToolMaterial swordGuardianMaterial = addToolMaterial("swordGuardianMaterial", 1, guardianSwordDurability, 1.0F, (float) guardianSwordDamage, 30);
    public static ToolMaterial swordSuperStarMaterial = addToolMaterial("swordSuperStarMaterial", 1, superStarSwordDurability, 1.0F, (float) superStarSwordDamage, 20);
    public static ToolMaterial swordEnderDragonMaterial = addToolMaterial("swordEnderDragonMaterial", 1, enderDragonSwordDurability, 1.0F, (float) enderDragonSwordDamage, 20);
    public ItemStack itemExpert;
    public TextFormatting formatting;
    public String effect;
    public String itemName;
    private Swords swords;

    public ItemSpecialSword(Swords swords) {
        super(swords.getToolMaterial());
        this.swords = swords;
        this.itemName = swords.getName();
        this.itemExpert = swords.getRepairExpert();
        this.formatting = swords.getTextFormatting();
        this.effect = swords.getEffect();
        this.setRegistryName(swords.getName() + "_sword");
        this.setUnlocalizedName(setName(swords.getName() + "_sword"));
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        swords.addInformation(tooltip);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return swords.hitEntity(stack, target, attacker);
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return this.getRarity("SPECIAL_SWORD", formatting, "Special Sword");
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ArmorPlusItemUtils.isItemRepairable(repair, itemExpert);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(swords.getName(), 0);
    }
}