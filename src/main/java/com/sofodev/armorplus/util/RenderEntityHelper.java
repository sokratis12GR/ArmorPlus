/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderEntityHelper {

    //RENDERING MOBS ABOVE THE PLAYER

    @SideOnly(Side.CLIENT)
    public static void renderBoss(Entity entity, EntityPlayer player, float partialTicks) {
        render(entity, player, partialTicks, 0.05f);
    }

    @SideOnly(Side.CLIENT)
    public static void render(Entity entity, EntityPlayer player, float partialTicks, float size) {
        GlStateManager.pushMatrix();
        Vec3d currentPos = Minecraft.getMinecraft().player.getPositionEyes(partialTicks);
        Vec3d playerPos = player.getPositionEyes(partialTicks);

        GlStateManager.translate(playerPos.x - currentPos.x, playerPos.y - currentPos.y, playerPos.z - currentPos.z);

        GlStateManager.translate(0.0, 2.375 - (player.isSneaking() ? 0.125 : 0.0) + 0.1875, 0.0);
        GlStateManager.rotate(180f, 1.0f, 0.0f, 1.0f);

        GlStateManager.scale(size, size, size);
        double boop = Minecraft.getSystemTime() / 1000.0;
        GlStateManager.translate(0.0, Math.sin(boop % (2 * Math.PI)) * 0.25, 0.0);
        GlStateManager.rotate((float) (boop * 40.0 % 360), 0f, 1f, 0f);

        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, 0.5, 0.0);
        GlStateManager.rotate(180f, 1f, 0f, 0f);
        renderMob(entity, player, partialTicks);
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    /**
     * Render the mob on top of the player
     */
    @SideOnly(Side.CLIENT)
    public static void renderMob(Entity entity, EntityPlayer player, float partialTicks) {
        Vec3d currentPos = Minecraft.getMinecraft().player.getPositionEyes(partialTicks);
        Vec3d playerPos = player.getPositionEyes(partialTicks);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.pushAttrib();
        RenderHelper.enableStandardItemLighting();
        if (entity != null) {
            Minecraft.getMinecraft().getRenderManager().renderEntity(entity,
                playerPos.x - currentPos.x, playerPos.y - currentPos.y, playerPos.z - currentPos.z,
                entity.rotationYaw, partialTicks, false
            );
        }
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popAttrib();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
}
