/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.armors.base;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.iface.IModdedItem;
import com.sofodev.armorplus.items.armors.APArmorMaterial;
import com.sofodev.armorplus.util.EnumHelperUtil;
import com.sofodev.armorplus.util.Utils;
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
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.List;

import static com.sofodev.armorplus.util.ArmorPlusItemUtils.isItemRepairable;
import static com.sofodev.armorplus.util.Utils.setName;
import static com.sofodev.armorplus.util.Utils.setRL;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemArmorBase extends ItemArmor implements IModdedItem {

    public EntityEquipmentSlot slot;
    private EnumAction wear = EnumHelperUtil.addAction("WEAR");
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
