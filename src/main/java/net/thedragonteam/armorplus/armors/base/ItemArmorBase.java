/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.armors.APArmorMaterial;
import net.thedragonteam.armorplus.iface.IModdedItem;
import net.thedragonteam.armorplus.util.EnumTiers;
import net.thedragonteam.armorplus.util.Utils;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.List;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.isItemRepairable;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addAction;
import static net.thedragonteam.armorplus.util.EnumHelperUtil.addArmorMaterial;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ItemArmorBase extends ItemArmor implements IModdedItem {

    public static ArmorMaterial coalArmor = addArmorMaterial("COAL", "coal_armor", 2,
        coal.armor.protectionPoints, coal.armor.toughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial lapisArmor = addArmorMaterial("LAPIS", "lapis_armor", 11,
        lapis.armor.protectionPoints, lapis.armor.toughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial redstoneArmor = addArmorMaterial("REDSTONE", "redstone_armor", 11,
        redstone.armor.protectionPoints, redstone.armor.toughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial emeraldArmor = addArmorMaterial("EMERALD", "emerald_armor", 35,
        emerald.armor.protectionPoints, emerald.armor.toughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial obsidianArmor = addArmorMaterial("OBSIDIAN", "obsidian_armor", 40,
        obsidian.armor.protectionPoints, obsidian.armor.toughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial lavaArmor = addArmorMaterial("LAVA", "lava_armor", 45,
        lava.armor.protectionPoints, lava.armor.toughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial guardianArmor = addArmorMaterial("GUARDIAN", "guardian_armor", 50,
        guardian.armor.protectionPoints, guardian.armor.toughnessPoints, EnumTiers.TIER_3);
    public static ArmorMaterial superStarArmor = addArmorMaterial("SUPER_STAR", "super_star_armor", 50,
        super_star.armor.protectionPoints, super_star.armor.toughnessPoints, EnumTiers.TIER_3);
    public static ArmorMaterial enderDragonArmor = addArmorMaterial("ENDER_DRAGON", "ender_dragon_armor", 60,
        ender_dragon.armor.protectionPoints, ender_dragon.armor.toughnessPoints, EnumTiers.TIER_3);
    public static ArmorMaterial chickenArmor = addArmorMaterial("CHICKEN", "chicken_armor", 1,
        chicken.armor.protectionPoints, chicken.armor.toughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial slimeArmor = addArmorMaterial("SLIME", "slime_armor", 1,
        slime.armor.protectionPoints, slime.armor.toughnessPoints, EnumTiers.TIER_1);
    public static ArmorMaterial arditeArmor = addArmorMaterial("ARDITE", "ardite_armor", 55,
        ardite.armor.protectionPoints, ardite.armor.toughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial cobaltArmor = addArmorMaterial("COBALT", "cobalt_armor", 44,
        cobalt.armor.protectionPoints, cobalt.armor.toughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial knightSlimeArmor = addArmorMaterial("KNIGHT_SLIME", "knight_slime_armor", 33,
        knight_slime.armor.protectionPoints, knight_slime.armor.toughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial manyullynArmor = addArmorMaterial("MANYULLYN", "manyullyn_armor", 66,
        manyullyn.armor.protectionPoints, manyullyn.armor.toughnessPoints, EnumTiers.TIER_2);
    public static ArmorMaterial pigIronArmor = addArmorMaterial("PIG_IRON", "pig_iron_armor", 33,
        pig_iron.armor.protectionPoints, pig_iron.armor.toughnessPoints, EnumTiers.TIER_2);

    public EntityEquipmentSlot slot;
    private EnumAction wear = addAction("WEAR");
    public APArmorMaterial material;
    private ItemStack repairStack;

    public ItemArmorBase(APArmorMaterial material, EntityEquipmentSlot slot) {
        super(material.getArmorMaterial(), 0, slot);
        this.setMaterial(material);
        this.setRepairStack(material.getRepairStack());
        this.setSlot(slot);
        this.setMaxStackSize(1);
        this.createPieces(slot, material.getName());
        this.setCreativeTab(ArmorPlus.tabArmorplus);
    }

    private void createPieces(EntityEquipmentSlot slot, String name) {
        String piece = name + (slot == HEAD ? "_helmet" : slot == CHEST ? "_chestplate" : slot == LEGS ? "_leggings" : slot == FEET ? "_boots" : "");
        this.setRegistryName(setRL(piece));
        this.setTranslationKey(setName(piece));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        boolean isUnbreakable = false;
        if (tagCompound != null) {
            isUnbreakable = tagCompound.getBoolean("Unbreakable");
        }
        if (material.isUnbreakable() && !isUnbreakable) {
            Utils.setUnbreakable(stack);
            LogHelper.info("Making The Ultimate Armor Unbreakable!");
        }
    }

    public void setMaterial(APArmorMaterial material) {
        this.material = material;
    }

    public void setSlot(EntityEquipmentSlot slot) {
        this.slot = slot;
    }

    public void setRepairStack(ItemStack repairStack) {
        this.repairStack = repairStack;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return this.getRarity("ARMOR_COLOR", material.getFormatting(), "Armor Color");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (material.isEnabled()) {
            material.onArmorTick(player, slot);
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return isItemRepairable(repair, this.repairStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        material.addInformation(stack, world, tooltip, advanced);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return wear;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(material.getName());
    }

}
