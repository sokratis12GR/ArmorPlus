/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.armors.horse;

import com.sofodev.armorplus.common.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.common.registry.items.base.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.common.registry.items.armors.APArmorMaterial.*;
import static com.sofodev.armorplus.common.util.EnumHelperUtil.addHorseArmor;

public class ItemBaseHorseArmor extends ItemBase {

    public static final HorseArmorType coal = addHorseArmor(COAL, 1);
    public static final HorseArmorType lapis = addHorseArmor(LAPIS, 3);
    public static final HorseArmorType redstone = addHorseArmor(REDSTONE, 3);
    public static final HorseArmorType emerald = addHorseArmor(EMERALD, 11);
    public static final HorseArmorType obsidian = addHorseArmor(OBSIDIAN, 13);
    public static final HorseArmorType lava = addHorseArmor(INFUSED_LAVA, 15);
    public static final HorseArmorType guardian = addHorseArmor(GUARDIAN, 18);
    public static final HorseArmorType super_star = addHorseArmor(SUPER_STAR, 18);
    public static final HorseArmorType ender_dragon = addHorseArmor(ENDER_DRAGON, 18);

    private APArmorMaterial material;

    public ItemBaseHorseArmor(APArmorMaterial material) {
        super(material.getName() + "_horse_armor");
        this.material = material;
    }

    @Override
    public HorseArmorType getHorseArmorType(ItemStack stack) {
        switch (material) {
            case COAL:
                return coal;
            case LAPIS:
                return lapis;
            case REDSTONE:
                return redstone;
            case EMERALD:
                return emerald;
            case OBSIDIAN:
                return obsidian;
            case INFUSED_LAVA:
                return lava;
            case GUARDIAN:
                return guardian;
            case SUPER_STAR:
                return super_star;
            case ENDER_DRAGON:
                return ender_dragon;
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getHorseArmorTexture(EntityLiving wearer, ItemStack stack) {
        return super.getHorseArmorTexture(wearer, stack);
    }

    @Override
    public void onHorseArmorTick(World world, EntityLiving wearer, ItemStack itemStack) {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel("horse");
    }
}