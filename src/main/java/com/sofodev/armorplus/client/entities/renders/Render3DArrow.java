/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.entities.renders;

import com.sofodev.armorplus.client.entities.models.Model3DArrow;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Sokratis Fotkatzikis
 */
@SideOnly(Side.CLIENT)
public class Render3DArrow<T extends EntityArrow> extends Render<T> {
    private ResourceLocation res;
    public Model3DArrow model;

    public Render3DArrow(RenderManager rm, String typeName) {
        super(rm);
        this.model = new Model3DArrow();
        this.res = Utils.setRL("textures/entity/projectiles/" + typeName + "_arrow.png");
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        //  GlStateManager.pushMatrix();
        float f = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F;
        float f1 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        float ageInTicks = (float) entity.ticksExisted + partialTicks;
        //  GlStateManager.translate((float) x, (float) y + 0.15F, (float) z);
        //  GlStateManager.enableRescaleNormal();
        //  GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        //  //this.bindEntityTexture(entity);
        //  this.model.render(entity, 0.0F, 0.0F, 0.0F, f, f1, 1);
        //  GlStateManager.enableBlend();
        //  GlStateManager.disableBlend();
        //  GlStateManager.popMatrix();
        //  super.doRender(entity, x, y, z, entityYaw, partialTicks);
        //
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f1, 0.0F, 0.0F, 1.0F);
        this.bindEntityTexture(entity);

        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.model.render(entity, partialTicks, 0.0F, ageInTicks, 0.0F, 0.0F, 0.0625F);

        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return res;
    }

}