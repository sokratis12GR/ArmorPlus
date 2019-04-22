/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.entity.dungeon.skeletalking;

import com.sofodev.armorplus.common.util.RenderingUtils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created using Tabula 5.1.0
 */
public class ModelDungeonSkeletalKing extends ModelBase {
    public ModelRenderer headHolder;
    public ModelRenderer centerBodyRod;
    public ModelRenderer bottomCenterRod;
    public ModelRenderer topBodyCross;
    public ModelRenderer middleBodyCross;
    public ModelRenderer bigHeadCenter;
    public ModelRenderer lastBodyCross;
    public ModelRenderer miniHead3;
    public ModelRenderer miniLeftHead;
    public ModelRenderer body;
    public ModelRenderer armLeft;
    public ModelRenderer legLeft;
    public ModelRenderer legRight;
    public ModelRenderer armRight;
    public ModelRenderer handSkullLeft;
    public ModelRenderer handSkullRight;

    public ModelDungeonSkeletalKing() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.miniHead3 = new ModelRenderer(this, 40, 0);
        this.miniHead3.mirror = true;
        this.miniHead3.setRotationPoint(-8.0F, 4.0F, 0.0F);
        this.miniHead3.addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, 0.0F);
        this.centerBodyRod = new ModelRenderer(this, 13, 35);
        this.centerBodyRod.setRotationPoint(-2.0F, 6.9F, -0.5F);
        this.centerBodyRod.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(centerBodyRod, 0.36128315516282616F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 0, 33);
        this.legRight.mirror = true;
        this.legRight.setRotationPoint(-4.0F, 12.0F, 0.0F);
        this.legRight.addBox(-1.0F, 0.0F, -1.0F, 3, 12, 3, 0.0F);
        this.lastBodyCross = new ModelRenderer(this, 0, 0);
        this.lastBodyCross.mirror = true;
        this.lastBodyCross.setRotationPoint(-4.0F, 6.9F, -0.5F);
        this.lastBodyCross.addBox(-4.0F, 6.5F, 0.5F, 15, 2, 2, 0.0F);
        this.setRotateAngle(lastBodyCross, 0.36128315516282616F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 0, 33);
        this.legLeft.mirror = true;
        this.legLeft.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.legLeft.addBox(-1.0F, 0.0F, -1.0F, 3, 12, 3, 0.0F);
        this.miniLeftHead = new ModelRenderer(this, 40, 0);
        this.miniLeftHead.setRotationPoint(10.0F, 4.0F, 0.0F);
        this.miniLeftHead.addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, 0.0F);
        this.headHolder = new ModelRenderer(this, 0, 16);
        this.headHolder.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.headHolder.addBox(-10.0F, 3.9F, -0.5F, 20, 3, 3, 0.0F);
        this.armRight = new ModelRenderer(this, 52, 12);
        this.armRight.mirror = true;
        this.armRight.setRotationPoint(-9.0F, 1.0F, 0.7F);
        this.armRight.addBox(-1.0F, -2.0F, -1.0F, 3, 15, 3, 0.0F);
        this.setRotateAngle(armRight, 0.0F, 0.0F, 0.10000736613927509F);
        this.topBodyCross = new ModelRenderer(this, 0, 0);
        this.topBodyCross.setRotationPoint(-4.0F, 6.9F, -0.5F);
        this.topBodyCross.addBox(-4.0F, 1.5F, 0.5F, 15, 2, 2, 0.0F);
        this.setRotateAngle(topBodyCross, 0.36128315516282616F, 0.0F, 0.0F);
        this.bottomCenterRod = new ModelRenderer(this, 16, 22);
        this.bottomCenterRod.setRotationPoint(-2.0F, 17.25F, 3.03F);
        this.bottomCenterRod.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.setRotateAngle(bottomCenterRod, 1.1466813185602744F, 0.0F, 0.0F);
        this.armLeft = new ModelRenderer(this, 52, 12);
        this.armLeft.mirror = true;
        this.armLeft.setRotationPoint(7.0F, 1.0F, 0.7F);
        this.armLeft.addBox(-1.0F, -2.0F, -1.0F, 3, 15, 3, 0.0F);
        this.setRotateAngle(armLeft, 0.0F, 0.0F, -0.10000736613927509F);
        this.body = new ModelRenderer(this, 32, 30);
        this.body.setRotationPoint(0.0F, 6.9F, 0.5F);
        this.body.addBox(-5.0F, 0.0F, -2.0F, 10, 12, 6, 0.0F);
        this.middleBodyCross = new ModelRenderer(this, 0, 0);
        this.middleBodyCross.mirror = true;
        this.middleBodyCross.setRotationPoint(-4.0F, 6.9F, -0.5F);
        this.middleBodyCross.addBox(-4.0F, 4.0F, 0.5F, 15, 2, 2, 0.0F);
        this.setRotateAngle(middleBodyCross, 0.36128315516282616F, 0.0F, 0.0F);
        this.bigHeadCenter = new ModelRenderer(this, 0, 48);
        this.bigHeadCenter.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bigHeadCenter.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.handSkullRight = new ModelRenderer(this, 32, 48);
        this.handSkullRight.mirror = true;
        this.handSkullRight.setRotationPoint(0.1F, 10.0F, -0.6F);
        this.handSkullRight.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 8, 0.0F);
        this.handSkullLeft = new ModelRenderer(this, 32, 48);
        this.handSkullLeft.mirror = true;
        this.handSkullLeft.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.handSkullLeft.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(handSkullLeft, 0.0F, 0.0F, -0.091106186954104F);
        this.headHolder.addChild(this.miniHead3);
        this.headHolder.addChild(this.centerBodyRod);
        this.body.addChild(this.legRight);
        this.headHolder.addChild(this.lastBodyCross);
        this.body.addChild(this.legLeft);
        this.headHolder.addChild(this.miniLeftHead);
        this.body.addChild(this.armRight);
        this.headHolder.addChild(this.topBodyCross);
        this.headHolder.addChild(this.bottomCenterRod);
        this.body.addChild(this.armLeft);
        this.headHolder.addChild(this.body);
        this.headHolder.addChild(this.middleBodyCross);
        this.headHolder.addChild(this.bigHeadCenter);
        this.armRight.addChild(this.handSkullRight);
        this.armLeft.addChild(this.handSkullLeft);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.headHolder.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;

    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        //    EntitySkeletalKing entitySkeletalKing = (EntitySkeletalKing) entity;

        //    limbSwing = entitySkeletalKing.ticksExisted;
        //    limbSwingAmount = 0.5f;

        float globalSpeed = 1.0f;
        float globalDegree = 1.0f;
        float globalHeight = 1.0f;

        // walk(body, 0.1f * globalSpeed, 1.0f * globalDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        RenderingUtils.swing(armLeft, 0.2f * globalSpeed, 0.7f * globalDegree, 0.3f, 0.0f, limbSwing, limbSwingAmount);
        RenderingUtils.swing(armRight, 0.2f * globalSpeed, 0.7f * globalDegree, 0.3f, 0.0f, limbSwing, limbSwingAmount);

        RenderingUtils.flap(handSkullRight, 0.2f * globalSpeed, -0.7f * globalDegree, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        RenderingUtils.flap(handSkullLeft, 0.2f * globalSpeed, 0.7f * globalDegree, 0.0f, 0.0f, limbSwing, limbSwingAmount);

        RenderingUtils.walk(legLeft, 0.2f * globalSpeed, -1.0f * globalDegree, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        RenderingUtils.walk(legRight, 0.2f * globalSpeed, 1.0f * globalDegree, 0.0f, 0.0f, limbSwing, limbSwingAmount);
    }
}
