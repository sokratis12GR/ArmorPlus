/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author Sokratis Fotkatzikis
 */
public class RenderCosmetics {

    public ItemStack renderedStack;

    public RenderCosmetics(ItemStack renderedItemStack) {
        this.renderedStack = renderedItemStack;
    }

    @OnlyIn(Dist.CLIENT)
    public static void render(RenderCosmetics cosmetics, EntityPlayer player, float partialTicks) {
        if (player.isInvisible() || !player.isWearing(EnumPlayerModelParts.CAPE) || player.isPotionActive(MobEffects.INVISIBILITY)) {
            return;
        }
        boolean isBlock = cosmetics.renderedStack.getItem() instanceof ItemBlock;

        renderLocation(player, partialTicks);

        GlStateManager.translated(0.0, 2.375 - ((player.isSneaking()) ? 0.125 : 0.0) + (isBlock ? 0.0 : 0.1875), 0.0);
        GlStateManager.rotatef(180f, 1.0f, 0.0f, 1.0f);

        float size = (isBlock) ? 0.5f : 0.4f;
        renderSize(size);
        if (!isBlock) GlStateManager.translated(0.0, 0.5, 0.0);
        GlStateManager.rotatef(180f, 1f, 0f, 0f);
        renderItemInWorld(cosmetics.renderedStack);
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();

        GlStateManager.popMatrix();
    }

    public static void renderLocation(EntityPlayer player, float partialTicks) {
        GlStateManager.pushMatrix();

        Vec3d currentPos = Minecraft.getInstance().player.getEyePosition(partialTicks);
        Vec3d playerPos = player.getEyePosition(partialTicks);
        GlStateManager.translated(playerPos.x - currentPos.x, playerPos.y - currentPos.y, playerPos.z - currentPos.z);
    }

    public static void renderSize(float size) {
        GlStateManager.scalef(size, size, size);

        double boop = 1000.0;//Minecraft.getSystemTime() / 1000.0;
        GlStateManager.translated(0.0, Math.sin(boop % (2 * Math.PI)) * 0.25, 0.0);
        GlStateManager.rotatef((float) (boop * 40.0 % 360), 0f, 1f, 0f);

        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
    }

    @OnlyIn(Dist.CLIENT)
    private static void renderItemInWorld(ItemStack stack) {
        if (stack.getCount() > 0) {
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.popAttrib();
            RenderHelper.enableStandardItemLighting();
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }

}