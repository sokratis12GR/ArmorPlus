/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.api.util.NBTHelper;

/**
 * net.thedragonteam.armorplus.util
 * ArmorPlus created by sokratis12GR on 7/18/2016 8:17 PM.
 * - TheDragonTeam
 */
public final class Utils {

    @SuppressWarnings("ConstantConditions")
    public static ItemStack setUnbreakable(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        stack.getTagCompound().setBoolean("Unbreakable", true);
        return stack;
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemInWorld(ItemStack stack) {
        if (stack != null) {
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

    public static String setARPUnlocalizedName(String name) {
        return ArmorPlus.MODID + "." + name;
    }
}