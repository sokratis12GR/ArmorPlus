/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.items.armors.base;

import com.sofodev.armorplus.common.iface.IModdedItem;
import com.sofodev.armorplus.common.items.armors.APArmorMaterial;
import com.sofodev.armorplus.common.util.EnumHelperUtil;
import com.sofodev.armorplus.common.util.LoaderUtils;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.List;

import static com.sofodev.armorplus.common.config.ModConfig.IntegrationsConfig.normalChaosResistance;
import static com.sofodev.armorplus.common.items.armors.APArmorMaterial.*;
import static com.sofodev.armorplus.common.util.ArmorPlusItemUtils.isItemRepairable;
import static net.minecraft.util.text.TextFormatting.DARK_PURPLE;

/**
 * @author Sokratis Fotkatzikis
 */
public class ItemSpecialArmor extends ItemArmorBase {

    public EntityEquipmentSlot slot;
    private EnumAction wear = EnumHelperUtil.addAction("WEAR");
    public APArmorMaterial material;
    private ItemStack repairStack;

    public ItemSpecialArmor(APArmorMaterial material, EntityEquipmentSlot slot) {
        super(material.getArmorMaterial(), slot, material.getName(), material.getName());
        this.setMaterial(material);
        this.setRepairStack(material.getRepairStack());
        this.setSlot(slot);
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

    public APArmorMaterial getMaterial() {
        return material;
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
    public IRarity getForgeRarity(ItemStack stack) {
        return this.getRarity(material.getFormatting(), "Armor Color");
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
        if ((material == GUARDIAN || material == SUPER_STAR || material == ENDER_DRAGON) && LoaderUtils.isDELoaded()) {
            float perc = (float) normalChaosResistance;
            if (perc > 0) {
                int displayPerc = MathHelper.floor(perc * 100);
                String out = new TextComponentTranslation("misc.armorplus.chaos.resistance", displayPerc + "%").setStyle(new Style().setColor(DARK_PURPLE)).getFormattedText();
                tooltip.add(DARK_PURPLE + out);
            }
        }
        material.addInformation(stack, world, tooltip, advanced);
        super.addInformation(stack, world, tooltip, advanced);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return wear;
    }
}
