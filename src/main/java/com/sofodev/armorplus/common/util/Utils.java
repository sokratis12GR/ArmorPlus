/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.thedragonteam.thedragonlib.util.ItemStackUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * @author Sokratis Fotkatzikis
 **/
public final class Utils {

    public static final int WILDCARD = OreDictionary.WILDCARD_VALUE;
    public static ItemStack[] emptyArmor = new ItemStack[4];
    public static EntityEquipmentSlot[] equipmentSlots = new EntityEquipmentSlot[]{HEAD, CHEST, LEGS, FEET};

    static {
        Arrays.fill(emptyArmor, ItemStack.EMPTY);
    }

    public static List<String> boxList(String[] objects) {
        return stream(objects).collect(toList());
    }

    public static List<Integer> boxList(int[] objects) {
        return stream(objects).boxed().collect(toList());
    }

    public static int convertToSeconds(int seconds) {
        return seconds * 20;
    }

    public static ItemStack checkNBT(ItemStack stack) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        return stack;
    }

    public static ItemStack setUnbreakable(ItemStack stack) {
        checkNBT(stack);
        stack.getTagCompound().setBoolean("Unbreakable", true);
        return stack;
    }

    public static ItemStack getStackFromSlot(EntityPlayer player, EntityEquipmentSlot slot) {
        return player.getItemStackFromSlot(slot);
    }

    public static String setName(String name) {
        return format("%s.%s", MODID, name);
    }

    public static NonNullList<ItemStack> getItemStacks(Item... items) {
        NonNullList<ItemStack> list = NonNullList.create();
        if (items != null) Arrays.stream(items).map(ItemStackUtils::getItemStack).forEachOrdered(list::add);
        return list;
    }

    public static NonNullList<ItemStack> getItemStacks(ItemStack... items) {
        NonNullList<ItemStack> list = NonNullList.create();
        list.addAll(Arrays.asList(items));
        return list;
    }

    public static String getUnlocalizedNames(ItemStack stack, String key, String... names) {
        for (int i = 0; i < names.length; i++) {
            if (stack.getItemDamage() == i) {
                return key + names[i];
            }
        }
        return key;
    }

    public static ResourceLocation setRL(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static String setLocation(String path) {
        return format("%s:%s", MODID, path);
    }

    public static boolean isNotEmpty(ItemStack stack) {
        return !stack.isEmpty();
    }

    public static boolean isEmpty(ItemStack stack) {
        return stack.isEmpty();
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean areNotNull(Object object1, Object object2) {
        return object1 != null && object2 != null;
    }

    public static boolean isNotNullNorEmpty(String object) {
        return isNotNull(object) && !Objects.equals(object, "");
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNullOrEmpty(String object) {
        return isNull(object) || Objects.equals(object, "");
    }

    public static ItemStack getTCIngot(int meta) {
        if (LoaderUtils.isTiCLoaded()) {
            Item ingot = ForgeRegistries.ITEMS.getValue(new ResourceLocation("tconstruct:ingots"));
            if (ingot != null) {
                return new ItemStack(ingot, 1, meta);
            }
            return ItemStack.EMPTY;
        }
        return ItemStack.EMPTY;
    }

}