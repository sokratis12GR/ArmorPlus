/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TESRTrophy extends TileEntityRenderer<TileTrophy> {
    @Override
    public void render(TileTrophy te, double x, double y, double z, float partialTicks, int destroyStage) {
        render(te, x, y, z, partialTicks, te.getEntityScale());
    }

    public void render(TileTrophy te, double x, double y, double z, float partialTicks, float size) {
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        double boop = 1; // Minecraft.getSystemTime() / 1000.0;
        GlStateManager.translated(x + 0.5, y + Math.sin(boop % (2 * Math.PI)) * 0.25 + 1.5, z + 0.5);
        GlStateManager.rotatef((float) (boop * 40.0 % 360), 0f, 1f, 0f);
        GlStateManager.scalef(size, size, size);
        renderMob(te, partialTicks);
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
    }

    /**
     * Render the mob on top of the block
     */
    private void renderMob(TileTrophy te, float partialTicks) {
        Entity entity = te.getCachedEntity();
        if (entity != null) {
            RenderManager renderManager = Minecraft.getInstance().getRenderManager();
            float f = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;
            renderManager.renderEntity(entity, 0, 0, 0, f, partialTicks, false);
        }
    }
}
