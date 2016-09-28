/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/
package net.thedragonteam.armorplus.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * models by Unknown
 */
@SideOnly(Side.CLIENT)
public class ModelMiniBossGuardian extends ModelBase {
    public ModelRenderer PartOne;
    public ModelRenderer PartTwo;
    public ModelRenderer PartThree;
    public ModelRenderer PartFour;
    public ModelRenderer PartFive;
    public ModelRenderer PartSix;

    public ModelMiniBossGuardian() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.PartOne = new ModelRenderer(this, 0, 0);
        this.PartOne.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.PartOne.addBox(0.0F, 0.0F, 0.0F, 9, 9, 1);
        this.PartTwo = new ModelRenderer(this, 0, 0);
        this.PartTwo.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.PartTwo.addBox(0.0F, 0.0F, 0.0F, 1, 9, 9);
        this.PartThree = new ModelRenderer(this, 0, 0);
        this.PartThree.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.PartThree.addBox(0.0F, 0.0F, 0.0F, 9, 9, 1);
        this.PartFour = new ModelRenderer(this, 0, 0);
        this.PartFour.setRotationPoint(8.0F, 0.0F, 0.0F);
        this.PartFour.addBox(0.0F, 0.0F, 0.0F, 1, 9, 9);
        this.PartFive = new ModelRenderer(this, 0, 0);
        this.PartFive.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.PartFive.addBox(0.0F, 0.0F, 0.0F, 6, 5, 1);
        this.setRotationAngles(this.PartFive, 0.0F, 0.6981317007977318F, 0.0F);
        this.PartSix = new ModelRenderer(this, 0, 0);
        this.PartSix.setRotationPoint(0.5F, 0.0F, 8.0F);
        this.PartSix.addBox(0.0F, 0.0F, 0.0F, 6, 5, 1);
        this.setRotationAngles(this.PartSix, 0.0F, -0.6981317007977318F, 0.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.PartOne.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.PartTwo.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.PartThree.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.PartFour.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.PartFive.offsetX, this.PartFive.offsetY, this.PartFive.offsetZ);
        GlStateManager.translate(this.PartFive.rotationPointX * scale, this.PartFive.rotationPointY * scale, this.PartFive.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.PartFive.offsetX, -this.PartFive.offsetY, -this.PartFive.offsetZ);
        GlStateManager.translate(-this.PartFive.rotationPointX * scale, -this.PartFive.rotationPointY * scale, -this.PartFive.rotationPointZ * scale);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.PartFive.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.PartSix.offsetX, this.PartSix.offsetY, this.PartSix.offsetZ);
        GlStateManager.translate(this.PartSix.rotationPointX * scale, this.PartSix.rotationPointY * scale, this.PartSix.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.PartSix.offsetX, -this.PartSix.offsetY, -this.PartSix.offsetZ);
        GlStateManager.translate(-this.PartSix.rotationPointX * scale, -this.PartSix.rotationPointY * scale, -this.PartSix.rotationPointZ * scale);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.0F);
        this.PartSix.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
