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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.items.enums.Swords;
import net.thedragonteam.armorplus.util.ArmorPlusItemUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity;
import static net.thedragonteam.armorplus.util.Utils.setName;

public class ItemSpecialSword extends ItemSword implements IModelHelper {

    public static ToolMaterial swordCoalMaterial = addToolMaterial("swordCoalMaterial", 1, APConfig.coalSwordDurability, 1.0F, (float) APConfig.coalSwordDamage, 15);
    public static ToolMaterial swordLapisMaterial = addToolMaterial("swordLapisMaterial", 1, APConfig.lapisSwordDurability, 1.0F, (float) APConfig.lapisSwordDamage, 30);
    public static ToolMaterial swordRedstoneMaterial = addToolMaterial("swordRedstoneMaterial", 1, APConfig.redstoneSwordDurability, 1.0F, (float) APConfig.redstoneSwordDamage, 20);
    public static ToolMaterial swordEmeraldMaterial = addToolMaterial("swordEmeraldMaterial", 1, APConfig.emeraldSwordDurability, 1.0F, (float) APConfig.emeraldSwordDamage, 20);
    public static ToolMaterial swordObsidianMaterial = addToolMaterial("swordObsidianMaterial", 1, APConfig.obsidianSwordDurability, 1.0F, (float) APConfig.obsidianSwordDamage, 20);
    public static ToolMaterial swordLavaMaterial = addToolMaterial("swordLavaMaterial", 1, APConfig.lavaSwordDurability, 1.0F, (float) APConfig.lavaSwordDamage, 20);
    public static ToolMaterial swordGuardianMaterial = addToolMaterial("swordGuardianMaterial", 1, APConfig.guardianSwordDurability, 1.0F, (float) APConfig.guardianSwordDamage, 30);
    public static ToolMaterial swordSuperStarMaterial = addToolMaterial("swordSuperStarMaterial", 1, APConfig.superStarSwordDurability, 1.0F, (float) APConfig.superStarSwordDamage, 20);
    public static ToolMaterial swordEnderDragonMaterial = addToolMaterial("swordEnderDragonMaterial", 1, APConfig.enderDragonSwordDurability, 1.0F, (float) APConfig.enderDragonSwordDamage, 20);
    public ItemStack itemEasy;
    public ItemStack itemExpert;
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
        this.setRegistryName(swords.getName() + "_sword");
        this.setUnlocalizedName(setName(swords.getName() + "_sword"));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
        this.formattingName = addRarity("SPECIAL_SWORD", formatting, "Special Sword");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        swords.addInformation(tooltip);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        return swords.hitEntity(stack, target, attacker);
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return formattingName;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return ArmorPlusItemUtils.isItemRepairable(repair, itemEasy, itemExpert);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(this, getRegistryName(), 0);
    }
}