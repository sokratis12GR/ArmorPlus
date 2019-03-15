/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.armors.horse;

import com.sofodev.armorplus.items.armors.APArmorMaterial;
import com.sofodev.armorplus.items.base.ItemBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Collection;


public class ItemBaseHorseArmor extends ItemBase {

    //  public static HorseArmorType COAL_HORSE_ARMOR = addHorseArmor(COAL, 1, ModItems.horseArmors[0]);
    //  public static HorseArmorType LAPIS_HORSE_ARMOR = addHorseArmor(LAPIS, 3, ModItems.horseArmors[1]);
    //  public static HorseArmorType REDSTONE_HORSE_ARMOR = addHorseArmor(REDSTONE, 3, ModItems.horseArmors[2]);
    //  public static HorseArmorType EMERALD_HORSE_ARMOR = addHorseArmor(EMERALD, 11, ModItems.horseArmors[3]);
    //  public static HorseArmorType OBSIDIAN_HORSE_ARMOR = addHorseArmor(OBSIDIAN, 13, ModItems.horseArmors[4]);
    //  public static HorseArmorType INFUSED_LAVA_HORSE_ARMOR = addHorseArmor(INFUSED_LAVA, 15, ModItems.horseArmors[5]);
    //  public static HorseArmorType GUARDIAN_HORSE_ARMOR = addHorseArmor(GUARDIAN, 18, ModItems.horseArmors[6]);
    //  public static HorseArmorType SUPER_STAR_HORSE_ARMOR = addHorseArmor(SUPER_STAR, 18, ModItems.horseArmors[7]);
    //  public static HorseArmorType ENDER_DRAGON_HORSE_ARMOR = addHorseArmor(ENDER_DRAGON, 18, ModItems.horseArmors[8]);

    private APArmorMaterial material;

    public ItemBaseHorseArmor(APArmorMaterial material, Properties properties) {
        super(properties);//todo material.getName() + "_horse_armor");
        this.material = material;
    }

    @Override
    public HorseArmorType getHorseArmorType(ItemStack stack) {
        //  switch (material) {
        //      case COAL:
        //          return COAL_HORSE_ARMOR;
        //      case LAPIS:
        //          return LAPIS_HORSE_ARMOR;
        //      case REDSTONE:
        //          return REDSTONE_HORSE_ARMOR;
        //      case EMERALD:
        //          return EMERALD_HORSE_ARMOR;
        //      case OBSIDIAN:
        //          return OBSIDIAN_HORSE_ARMOR;
        //      case INFUSED_LAVA:
        //          return INFUSED_LAVA_HORSE_ARMOR;
        //      case GUARDIAN:
        //          return GUARDIAN_HORSE_ARMOR;
        //      case SUPER_STAR:
        //          return SUPER_STAR_HORSE_ARMOR;
        //      case ENDER_DRAGON:
        //          return ENDER_DRAGON_HORSE_ARMOR;
        //  }
        return HorseArmorType.NONE;
    }

    @Override
    public void onHorseArmorTick(ItemStack stack, World world, EntityLiving horse) {
        //TODO: Add functionality
    }

    @Override
    public Collection<ItemGroup> getCreativeTabs() {
        return null;
    }
}