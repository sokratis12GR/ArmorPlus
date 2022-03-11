package com.sofodev.armorplus.utils;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class RenderingUtils {

    public static void swing(ModelRenderer box, float speed, float degree, float offset, float weight, float limbSwing, float limbSwingAmount) {
        box.yRot = degree * limbSwingAmount * MathHelper.cos(speed * limbSwing + offset) + weight * limbSwingAmount;
    }

    public static void flap(ModelRenderer box, float speed, float degree, float offset, float weight, float limbSwing, float limbSwingAmount) {
        box.zRot = degree * limbSwingAmount * MathHelper.cos(speed * limbSwing + offset) + weight * limbSwingAmount;
    }

    public static void bob(ModelRenderer box, float speed, float degree, float offset, float weight, float limbSwing, float limbSwingAmount) {
        box.y = degree * limbSwingAmount * MathHelper.cos(speed * limbSwing + offset) + weight * limbSwingAmount;
    }

    public static void walk(ModelRenderer box, float speed, float degree, float offset, float weight, float limbSwing, float limbSwingAmount) {
        box.xRot = degree * limbSwingAmount * MathHelper.cos(speed * limbSwing + offset) + weight * limbSwingAmount;
    }
}