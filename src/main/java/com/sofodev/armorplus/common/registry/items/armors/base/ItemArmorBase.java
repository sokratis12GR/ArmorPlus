/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.armors.base;

import com.sofodev.armorplus.common.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.common.util.Utils;
import com.sofodev.thedragonlib.util.LogHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.common.util.ArmorPlusItemUtils.isItemRepairable;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemArmorBase extends ItemPlaceholderArmor {

    public EntityEquipmentSlot slot;
    public APArmorMaterial material;
    private ItemStack repairStack;

    public ItemArmorBase(APArmorMaterial material, EntityEquipmentSlot slot, Properties properties) {
        super(material.getArmorMaterial(), slot, properties);
        this.setMaterial(material);
        this.setRepairStack(material.getRepairStack());
        this.setSlot(slot);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        NBTTagCompound tagCompound = stack.getTag();
        boolean isUnbreakable = false;
        if (tagCompound != null) {
            isUnbreakable = tagCompound.getBoolean("Unbreakable");
        }
        if (material.isUnbreakable() && !isUnbreakable) {
            Utils.setUnbreakable(stack);
            LogHelper.info("Making The Ultimate Armor Unbreakable!");
        }
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
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
    public void onArmorTick(ItemStack stack, World world, EntityPlayer player) {
        material.onArmorTick(player, slot);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return isItemRepairable(repair, this.repairStack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
        material.addInformation(stack, world, tooltip, advanced);
    }
}
