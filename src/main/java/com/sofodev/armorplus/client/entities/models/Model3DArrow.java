package com.sofodev.armorplus.client.entities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Model3DArrow extends ModelBase {
    private final ModelRenderer model;
    private final ModelRenderer feathers;
    private final ModelRenderer arrow_head;

    public Model3DArrow() {
        textureWidth = 32;
        textureHeight = 32;

        model = new ModelRenderer(this);
        model.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(model, 0.0F, -1.5708F, 0.0F);
        model.cubeList.add(new ModelBox(model, 0, 0, -4.0F, -3.0F, -0.5F, 8, 1, 1, 0.0F, false));

        feathers = new ModelRenderer(this);
        feathers.setRotationPoint(0.0F, 0.0F, 0.0F);
        model.addChild(feathers);
        feathers.cubeList.add(new ModelBox(feathers, 0, 10, 4.0F, -3.0F, 0.5F, 2, 1, 1, 0.0F, false));
        feathers.cubeList.add(new ModelBox(feathers, 10, 10, 5.0F, -3.0F, 1.5F, 2, 1, 1, 0.0F, false));
        feathers.cubeList.add(new ModelBox(feathers, 0, 6, 4.0F, -2.0F, -0.5F, 2, 1, 1, 0.0F, false));
        feathers.cubeList.add(new ModelBox(feathers, 5, 7, 5.0F, -1.0F, -0.5F, 2, 1, 1, 0.0F, false));
        feathers.cubeList.add(new ModelBox(feathers, 10, 4, 4.0F, -3.0F, -1.5F, 2, 1, 1, 0.0F, false));
        feathers.cubeList.add(new ModelBox(feathers, 10, 2, 5.0F, -3.0F, -2.5F, 2, 1, 1, 0.0F, false));
        feathers.cubeList.add(new ModelBox(feathers, 5, 9, 4.0F, -4.0F, -0.5F, 2, 1, 1, 0.0F, false));
        feathers.cubeList.add(new ModelBox(feathers, 0, 8, 5.0F, -5.0F, -0.5F, 2, 1, 1, 0.0F, false));

        arrow_head = new ModelRenderer(this);
        arrow_head.setRotationPoint(0.0F, 0.0F, 0.0F);
        model.addChild(arrow_head);
        arrow_head.cubeList.add(new ModelBox(arrow_head, 5, 3, -6.0F, -4.0F, -0.5F, 2, 1, 1, 0.0F, false));
        arrow_head.cubeList.add(new ModelBox(arrow_head, 5, 5, -6.0F, -3.0F, -1.5F, 2, 1, 1, 0.0F, false));
        arrow_head.cubeList.add(new ModelBox(arrow_head, 0, 2, -6.0F, -2.0F, -0.5F, 2, 1, 1, 0.0F, false));
        arrow_head.cubeList.add(new ModelBox(arrow_head, 0, 4, -6.0F, -3.0F, 0.5F, 2, 1, 1, 0.0F, false));
        arrow_head.cubeList.add(new ModelBox(arrow_head, 10, 6, -7.0F, -3.0F, -0.5F, 1, 1, 1, 0.0F, false));
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        model.render(scale);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}