package com.sofodev.armorplus.registry.entities.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sofodev.armorplus.utils.RenderingUtils;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Dragon - West
 * Created using Tabula 7.0.0
 */
public class DemonicDragonModel extends EntityModel {
    public ModelRenderer torso;
    public ModelRenderer middleNeckPiece;
    public ModelRenderer Stomach;
    public ModelRenderer leftHumerus;
    public ModelRenderer rightHumerus;
    public ModelRenderer neckHeadConector;
    public ModelRenderer head;
    public ModelRenderer nose;
    public ModelRenderer mouth;
    public ModelRenderer noseExtension;
    public ModelRenderer mouthExtension;
    public ModelRenderer noseExtension_1;
    public ModelRenderer tail;
    public ModelRenderer leftFemur;
    public ModelRenderer rightFemur;
    public ModelRenderer tail_1;
    public ModelRenderer tail_2;
    public ModelRenderer tail_3;
    public ModelRenderer tail_4;
    public ModelRenderer leftTibia;
    public ModelRenderer leftFoot;
    public ModelRenderer rightTibia;
    public ModelRenderer rightFoot;
    public ModelRenderer leftRadius;
    public ModelRenderer leftHumerusWing;
    public ModelRenderer leftBigFinger;
    public ModelRenderer leftRadiusWing;
    public ModelRenderer leftBigFingerWing;
    public ModelRenderer rightRadius;
    public ModelRenderer rightHumerusWing;
    public ModelRenderer rightBigFinger;
    public ModelRenderer rightRadiusWing;
    public ModelRenderer rightBigFingerWing;

    public DemonicDragonModel() {
        this.texWidth = 200;
        this.texHeight = 200;
        this.noseExtension = new ModelRenderer(this, 27, 106);
        this.noseExtension.setPos(0.0F, 0.0F, -8.0F);
        this.noseExtension.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 7, 0.0F);
        this.setRotateAngle(noseExtension, 0.27314402793711257F, 0.0F, 0.0F);
        this.mouth = new ModelRenderer(this, 75, 105);
        this.mouth.setPos(0.0F, 5.8F, 0.5F);
        this.mouth.addBox(-2.0F, 0.0F, -8.0F, 4, 2, 9, 0.0F);
        this.setRotateAngle(mouth, -0.091106186954104F, 0.0F, 0.0F);
        this.rightFoot = new ModelRenderer(this, 62, 19);
        this.rightFoot.setPos(0.5F, 6.9F, 3.0F);
        this.rightFoot.addBox(-2.0F, -0.1F, -3.6F, 4, 2, 7, 0.0F);
        this.setRotateAngle(rightFoot, -0.27314402793711257F, 0.0F, 0.0F);
        this.neckHeadConector = new ModelRenderer(this, 0, 82);
        this.neckHeadConector.setPos(0.0F, -0.1F, -9.6F);
        this.neckHeadConector.addBox(-2.5F, -3.5F, -8.0F, 5, 7, 10, 0.0F);
        this.setRotateAngle(neckHeadConector, -0.22759093446006054F, 0.0F, 0.0F);
        this.rightRadius = new ModelRenderer(this, 40, 133);
        this.rightRadius.setPos(-20.0F, 0.0F, 0.0F);
        this.rightRadius.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
        this.setRotateAngle(rightRadius, 0.0F, 3.141592653589793F, 0.0F);
        this.middleNeckPiece = new ModelRenderer(this, 32, 82);
        this.middleNeckPiece.setPos(0.0F, 4.8F, 2.3F);
        this.middleNeckPiece.addBox(-3.0F, -4.0F, -9.7F, 6, 8, 10, 0.0F);
        this.setRotateAngle(middleNeckPiece, -0.22759093446006054F, 0.0F, 0.0F);
        this.tail_2 = new ModelRenderer(this, 60, 54);
        this.tail_2.setPos(0.0F, 0.3F, 9.5F);
        this.tail_2.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 8, 0.0F);
        this.setRotateAngle(tail_2, -0.091106186954104F, 0.0F, 0.0F);
        this.leftBigFingerWing = new ModelRenderer(this, -15, 185);
        this.leftBigFingerWing.setPos(0.0F, 0.0F, 2.0F);
        this.leftBigFingerWing.addBox(0.0F, 0.0F, 0.0F, 10, 0, 15, 0.0F);
        this.leftHumerus = new ModelRenderer(this, 0, 122);
        this.leftHumerus.setPos(4.0F, 2.2F, 3.5F);
        this.leftHumerus.addBox(0.0F, -1.5F, -1.5F, 20, 3, 3, 0.0F);
        this.head = new ModelRenderer(this, 88, 122);
        this.head.setPos(0.0F, -1.0F, -5.6F);
        this.head.addBox(-3.0F, -3.5F, -8.0F, 6, 7, 8, 0.0F);
        this.setRotateAngle(head, 0.5009094953223726F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 58);
        this.tail.setPos(0.0F, 0.0F, 13.0F);
        this.tail.addBox(-2.5F, 0.0F, -2.0F, 5, 8, 12, 0.0F);
        this.setRotateAngle(tail, 0.136659280431156F, 0.0F, 0.0F);
        this.rightBigFinger = new ModelRenderer(this, 26, 143);
        this.rightBigFinger.setPos(17.0F, 0.0F, 1.0F);
        this.rightBigFinger.addBox(0.0F, -0.5F, -2.0F, 10, 1, 2, 0.0F);
        this.leftRadiusWing = new ModelRenderer(this, -16, 167);
        this.leftRadiusWing.setPos(0.0F, 0.0F, 1.0F);
        this.leftRadiusWing.addBox(0.0F, 0.0F, 0.0F, 17, 0, 16, 0.0F);
        this.rightBigFingerWing = new ModelRenderer(this, 7, 185);
        this.rightBigFingerWing.setPos(0.0F, 0.0F, -1.8F);
        this.rightBigFingerWing.addBox(0.0F, 0.0F, -15.0F, 10, 0, 15, 0.0F);
        this.nose = new ModelRenderer(this, 0, 102);
        this.nose.setPos(0.0F, -3.5F, -8.0F);
        this.nose.addBox(-2.5F, 0.0F, -8.0F, 5, 6, 8, 0.0F);
        this.tail_3 = new ModelRenderer(this, 64, 75);
        this.tail_3.setPos(0.0F, 0.7F, 8.0F);
        this.tail_3.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5, 0.0F);
        this.setRotateAngle(tail_3, -0.045553093477052F, 0.0F, 0.0F);
        this.leftHumerusWing = new ModelRenderer(this, -16, 149);
        this.leftHumerusWing.setPos(0.0F, 0.0F, 1.5F);
        this.leftHumerusWing.addBox(0.0F, 0.0F, 0.0F, 20, 0, 16, 0.0F);
        this.rightHumerusWing = new ModelRenderer(this, 24, 149);
        this.rightHumerusWing.setPos(-20.0F, 0.0F, 1.5F);
        this.rightHumerusWing.addBox(0.0F, 0.0F, 0.0F, 22, 0, 16, 0.0F);
        this.leftTibia = new ModelRenderer(this, 0, 9);
        this.leftTibia.setPos(1.0F, 10.0F, -3.2F);
        this.leftTibia.addBox(-0.5F, 0.0F, 0.0F, 2, 8, 6, 0.0F);
        this.setRotateAngle(leftTibia, 0.40980330836826856F, 0.0F, 0.0F);
        this.leftRadius = new ModelRenderer(this, 0, 133);
        this.leftRadius.setPos(20.0F, 0.0F, 0.0F);
        this.leftRadius.addBox(0.0F, -1.0F, -1.0F, 17, 2, 2, 0.0F);
        this.Stomach = new ModelRenderer(this, 120, 76);
        this.Stomach.setPos(0.0F, 0.0F, 18.0F);
        this.Stomach.addBox(-3.5F, 0.0F, 0.0F, 7, 11, 13, 0.0F);
        this.setRotateAngle(Stomach, -0.18203784098300857F, 0.0F, 0.0F);
        this.rightTibia = new ModelRenderer(this, 18, 9);
        this.rightTibia.setPos(-2.0F, 10.0F, -3.2F);
        this.rightTibia.addBox(-0.5F, 0.0F, 0.0F, 2, 8, 6, 0.0F);
        this.setRotateAngle(rightTibia, 0.40980330836826856F, 0.0F, 0.0F);
        this.torso = new ModelRenderer(this, 65, 70);
        this.torso.setPos(0.0F, 4.0F, -12.5F);
        this.torso.addBox(-4.5F, 0.0F, 0.0F, 9, 12, 18, 0.0F);
        this.setRotateAngle(torso, 0.045553093477052F, 0.0F, 0.0F);
        this.mouthExtension = new ModelRenderer(this, 103, 109);
        this.mouthExtension.setPos(0.0F, 2.0F, 1.0F);
        this.mouthExtension.addBox(-2.0F, -1.0F, 0.0F, 4, 1, 6, 0.0F);
        this.setRotateAngle(mouthExtension, 0.22759093446006054F, 0.0F, 0.0F);
        this.leftBigFinger = new ModelRenderer(this, 0, 143);
        this.leftBigFinger.setPos(17.0F, 0.0F, -1.0F);
        this.leftBigFinger.addBox(0.0F, -0.5F, -0.1F, 10, 1, 2, 0.0F);
        this.rightFemur = new ModelRenderer(this, 21, 26);
        this.rightFemur.setPos(-3.5F, 2.8F, 6.3F);
        this.rightFemur.addBox(-3.0F, -2.5F, -3.2F, 3, 13, 7, 0.0F);
        this.leftFemur = new ModelRenderer(this, 0, 26);
        this.leftFemur.setPos(3.5F, 2.8F, 6.3F);
        this.leftFemur.addBox(0.0F, -2.5F, -3.2F, 3, 13, 7, 0.0F);
        this.noseExtension_1 = new ModelRenderer(this, 48, 104);
        this.noseExtension_1.setPos(0.0F, 0.0F, 7.0F);
        this.noseExtension_1.addBox(-2.0F, 0.0F, 0.0F, 4, 3, 9, 0.0F);
        this.setRotateAngle(noseExtension_1, -0.5462880558742251F, 0.0F, 0.0F);
        this.rightHumerus = new ModelRenderer(this, 47, 122);
        this.rightHumerus.setPos(-4.0F, 2.2F, 3.5F);
        this.rightHumerus.addBox(-20.0F, -1.5F, -1.5F, 20, 3, 3, 0.0F);
        this.tail_1 = new ModelRenderer(this, 37, 60);
        this.tail_1.setPos(0.0F, 0.0F, 10.0F);
        this.tail_1.addBox(-2.0F, 0.0F, -1.5F, 4, 7, 11, 0.0F);
        this.setRotateAngle(tail_1, 0.045553093477052F, 0.0F, 0.0F);
        this.tail_4 = new ModelRenderer(this, 30, 58);
        this.tail_4.setPos(0.0F, 0.5F, 5.0F);
        this.tail_4.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 5, 0.0F);
        this.setRotateAngle(tail_4, -0.091106186954104F, 0.0F, 0.0F);
        this.rightRadiusWing = new ModelRenderer(this, 20, 167);
        this.rightRadiusWing.setPos(0.0F, 0.0F, -1.0F);
        this.rightRadiusWing.addBox(0.0F, 0.0F, -16.0F, 17, 0, 16, 0.0F);
        this.leftFoot = new ModelRenderer(this, 36, 19);
        this.leftFoot.setPos(0.5F, 6.9F, 3.0F);
        this.leftFoot.addBox(-2.0F, -0.1F, -3.6F, 4, 2, 7, 0.0F);
        this.setRotateAngle(leftFoot, -0.27314402793711257F, 0.0F, 0.0F);
        this.nose.addChild(this.noseExtension);
        this.nose.addChild(this.mouth);
        this.rightTibia.addChild(this.rightFoot);
        this.middleNeckPiece.addChild(this.neckHeadConector);
        this.rightHumerus.addChild(this.rightRadius);
        this.torso.addChild(this.middleNeckPiece);
        this.tail_1.addChild(this.tail_2);
        this.leftBigFinger.addChild(this.leftBigFingerWing);
        this.torso.addChild(this.leftHumerus);
        this.neckHeadConector.addChild(this.head);
        this.Stomach.addChild(this.tail);
        this.rightRadius.addChild(this.rightBigFinger);
        this.leftRadius.addChild(this.leftRadiusWing);
        this.rightBigFinger.addChild(this.rightBigFingerWing);
        this.head.addChild(this.nose);
        this.tail_2.addChild(this.tail_3);
        this.leftHumerus.addChild(this.leftHumerusWing);
        this.rightHumerus.addChild(this.rightHumerusWing);
        this.leftFemur.addChild(this.leftTibia);
        this.leftHumerus.addChild(this.leftRadius);
        this.torso.addChild(this.Stomach);
        this.rightFemur.addChild(this.rightTibia);
        this.mouth.addChild(this.mouthExtension);
        this.leftRadius.addChild(this.leftBigFinger);
        this.Stomach.addChild(this.rightFemur);
        this.Stomach.addChild(this.leftFemur);
        this.noseExtension.addChild(this.noseExtension_1);
        this.torso.addChild(this.rightHumerus);
        this.tail.addChild(this.tail_1);
        this.tail_3.addChild(this.tail_4);
        this.rightRadius.addChild(this.rightRadiusWing);
        this.leftTibia.addChild(this.leftFoot);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //    EntitySkeletalKing entitySkeletalKing = (EntitySkeletalKing) entity;

        //    limbSwing = entitySkeletalKing.ticksExisted;
        //    limbSwingAmount = 0.5f;

        float globalSpeed = 1.0f;
        float globalDegree = 1.0f;
        float globalHeight = 1.0f;

        // walk(body, 0.1f * globalSpeed, 1.0f * globalDegree, false, 0.0f, 0.0f, limbSwing, limbSwingAmount);
        flap(limbSwing, limbSwingAmount, globalSpeed, globalDegree, leftHumerusWing, leftRadiusWing, leftBigFingerWing);
        flap(limbSwing, limbSwingAmount, globalSpeed, globalDegree, rightHumerusWing, rightRadiusWing, rightBigFingerWing);
    }

    private void flap(float limbSwing, float limbSwingAmount, float globalSpeed, float globalDegree, ModelRenderer rightHumerusWing, ModelRenderer rightRadiusWing, ModelRenderer rightBigFingerWing) {
        RenderingUtils.flap(rightHumerusWing, 0.2f * globalSpeed, 0.7f * globalDegree, 0.3f, 0.0f, limbSwing, limbSwingAmount);
        RenderingUtils.flap(rightRadiusWing, 0.2f * globalSpeed, 0.7f * globalDegree, 0.3f, 0.0f, limbSwing, limbSwingAmount);
        RenderingUtils.flap(rightBigFingerWing, 0.2f * globalSpeed, 0.7f * globalDegree, 0.3f, 0.0f, limbSwing, limbSwingAmount);
    }
}