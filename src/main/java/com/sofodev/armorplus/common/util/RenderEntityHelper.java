/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.util;

import com.sofodev.armorplus.client.misc.RenderCosmetics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RenderEntityHelper {

    //RENDERING MOBS ABOVE THE PLAYER

    @OnlyIn(Dist.CLIENT)
    public static void renderBoss(Entity entity, EntityPlayer player, float partialTicks) {
        render(entity, player, partialTicks, 0.05f);
    }

    @OnlyIn(Dist.CLIENT)
    public static void render(Entity entity, EntityPlayer player, float partialTicks, float size) {
        RenderCosmetics.renderLocation(player, partialTicks);

        GlStateManager.translated(0.0, 2.375 - (player.isSneaking() ? 0.125 : 0.0) + 0.1875, 0.0);
        GlStateManager.rotatef(180f, 1.0f, 0.0f, 1.0f);

        RenderCosmetics.renderSize(size);
        GlStateManager.translated(0.0, 0.5, 0.0);
        GlStateManager.rotatef(180f, 1f, 0f, 0f);
        renderMob(entity, player, partialTicks);
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    /**
     * Render the mob on top of the player
     */
    @OnlyIn(Dist.CLIENT)
    public static void renderMob(Entity entity, EntityPlayer player, float partialTicks) {
        Vec3d currentPos = Minecraft.getInstance().player.getEyePosition(partialTicks);
        Vec3d playerPos = player.getEyePosition(partialTicks);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.popAttrib();
        RenderHelper.enableStandardItemLighting();
        if (entity != null) {
            Minecraft.getInstance().getRenderManager().renderEntity(entity,
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
