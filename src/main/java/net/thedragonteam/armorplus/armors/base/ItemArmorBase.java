/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.APArmorMaterial;
import net.thedragonteam.armorplus.iface.IModdedItem;
import net.thedragonteam.armorplus.util.EnumTiers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.isItemRepairable;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addAction;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addArmorMaterial;
import static net.thedragonteam.armorplus.util.Utils.setLocation;
import static net.thedragonteam.armorplus.util.Utils.setName;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ItemArmorBase extends ItemArmor implements IModdedItem {

    public static ArmorMaterial coalArmor = addArmorMaterial("COAL", setLocation("coal_armor"), 2,
        coalArmorProtectionPoints, coalArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial emeraldArmor = addArmorMaterial("EMERALD", setLocation("emerald_armor"), 35,
        emeraldArmorProtectionPoints, emeraldArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial lapisArmor = addArmorMaterial("LAPIS", setLocation("lapis_armor"), 11,
        lapisArmorProtectionPoints, lapisArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial lavaArmor = addArmorMaterial("LAVA", setLocation("lava_armor"), 45,
        lavaArmorProtectionPoints, lavaArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial obsidianArmor = addArmorMaterial("OBSIDIAN", setLocation("obsidian_armor"), 40,
        obsidianArmorProtectionPoints, obsidianArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial redstoneArmor = addArmorMaterial("REDSTONE", setLocation("redstone_armor"), 11,
        redstoneArmorProtectionPoints, redstoneArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial chickenArmor = addArmorMaterial("CHICKEN", setLocation("chicken_armor"), 1,
        chickenArmorProtectionPoints, chickenArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial slimeArmor = addArmorMaterial("SLIME", setLocation("slime_armor"), 1,
        slimeArmorProtectionPoints, slimeArmorToughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial arditeArmor = addArmorMaterial("ARDITE", setLocation("ardite_armor"), 55,
        arditeArmorProtectionPoints, arditeArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial cobaltArmor = addArmorMaterial("COBALT", setLocation("cobalt_armor"), 44,
        cobaltArmorProtectionPoints, cobaltArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial knightSlimeArmor = addArmorMaterial("KNIGHT_SLIME", setLocation("knight_slime_armor"), 33,
        knightSlimeArmorProtectionPoints, knightSlimeArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial manyullynArmor = addArmorMaterial("MANYULLYN", setLocation("manyullyn_armor"), 66,
        manyullynArmorProtectionPoints, manyullynArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial pigIronArmor = addArmorMaterial("PIG_IRON", setLocation("pig_iron_armor"), 33,
        pigIronArmorProtectionPoints, pigIronArmorToughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial enderDragonArmor = addArmorMaterial("ENDER_DRAGON", setLocation("ender_dragon_armor"), 60,
        enderDragonArmorProtectionPoints, enderDragonArmorToughnessPoints, EnumTiers.TIER_3);
    public static ArmorMaterial guardianArmor = addArmorMaterial("GUARDIAN", setLocation("guardian_armor"), 50,
        guardianArmorProtectionPoints, guardianArmorToughnessPoints, EnumTiers.TIER_3);
    public static ArmorMaterial superStarArmor = addArmorMaterial("SUPER_STAR", setLocation("super_star_armor"), 50,
        superStarArmorProtectionPoints, superStarArmorToughnessPoints, EnumTiers.TIER_3);
    private EnumAction wear = addAction("WEAR");

    private APArmorMaterial material;
    private ItemStack itemExpert;
    private EntityEquipmentSlot slot;

    public ItemArmorBase(APArmorMaterial material, EntityEquipmentSlot slot) {
        super(material.getArmorMaterial(), 0, slot);
        this.material = material;
        this.itemExpert = material.getRepairStack();
        this.slot = slot;
        this.setMaxStackSize(1);
        switch (slot) {
            case FEET:
                String boots = material.getName() + "_boots";
                this.setRegistryName(boots);
                this.setUnlocalizedName(setName(boots));
                break;
            case LEGS:
                String leggings = material.getName() + "_leggings";
                this.setRegistryName(leggings);
                this.setUnlocalizedName(setName(leggings));
                break;
            case CHEST:
                String chestplate = material.getName() + "_chestplate";
                this.setRegistryName(chestplate);
                this.setUnlocalizedName(setName(chestplate));
                break;
            case HEAD:
                String helmet = material.getName() + "_helmet";
                this.setRegistryName(helmet);
                this.setUnlocalizedName(setName(helmet));
                break;
        }
        this.setCreativeTab(ArmorPlus.tabArmorplus);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return this.getRarity("ARMOR_COLOR", material.getFormatting(), "Armor Color");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        material.onArmorTick(player, slot);
      // if (!material.enableFullArmorEffect()) {
      //     material.addArmorEffects(player, slot);
      // } else {
      //     material.addArmorEffects(player);
      // }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return isItemRepairable(repair, this.itemExpert);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        material.addInformation(stack, world, tooltip, advanced);
    }

    @NotNull
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return wear;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(getRegistryName(), material.getName());
    }

}
