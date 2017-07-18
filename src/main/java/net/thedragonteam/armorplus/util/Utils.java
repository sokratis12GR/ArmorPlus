/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.thedragonteam.thedragonlib.util.ItemStackUtils;

import java.util.Arrays;
import java.util.Collections;

import static java.lang.String.format;
import static net.thedragonteam.armorplus.ArmorPlus.MODID;

/**
 * net.thedragonteam.armorplus.util
 * ArmorPlus created by sokratis12GR on 7/18/2016 8:17 PM.
 * - TheDragonTeam
 */
public final class Utils {

    public static final int WILDCARD = OreDictionary.WILDCARD_VALUE;

    public static ItemStack checkNBT(ItemStack stack) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        return stack;
    }

    public static ItemStack setUnbreakable(ItemStack stack) {
        checkNBT(stack);
        stack.getTagCompound().setBoolean("unbreakable", true);
        return stack;
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemInWorld(ItemStack stack) {
        if (stack.getCount() > 0) {
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

    public static String setName(String name) {
        return format("%s.%s", MODID, name);
    }

    public static NonNullList<ItemStack> getItemStacks(Item... items) {
        NonNullList<ItemStack> list = NonNullList.create();
        Arrays.stream(items).map(ItemStackUtils::getItemStack).forEachOrdered(list::add);
        return list;
    }

    public static NonNullList<ItemStack> getItemStacks(ItemStack... itemStacks) {
        NonNullList<ItemStack> list = NonNullList.create();
        Collections.addAll(list, itemStacks);
        return list;
    }

    public static ResourceLocation setRL(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static String setLocation(String path) {
        return format("%s:%s", MODID, path);
    }

    public static boolean areItemsEqual(ItemStack stack1, ItemStack stack2, boolean checkWildcard) {
        return isValid(stack1) && isValid(stack2) && (stack1.isItemEqual(stack2) || checkWildcard && stack1.getItem() == stack2.getItem() && (stack1.getItemDamage() == WILDCARD || stack2.getItemDamage() == WILDCARD));
    }

    public static boolean isValid(ItemStack stack) {
        return isNotNull(stack) && !stack.isEmpty();
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }
}