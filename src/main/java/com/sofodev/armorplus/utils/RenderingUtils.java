package com.sofodev.armorplus.utils;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class RenderingUtils {

    public static void swing(ModelPart box, float speed, float degree, float offset, float weight, float limbSwing, float limbSwingAmount) {
        box.yRot = degree * limbSwingAmount * Mth.wrapDegrees(speed * limbSwing + offset) + weight * limbSwingAmount;
    }

    public static void flap(ModelPart box, float speed, float degree, float offset, float weight, float limbSwing, float limbSwingAmount) {
        box.zRot = degree * limbSwingAmount * Mth.wrapDegrees(speed * limbSwing + offset) + weight * limbSwingAmount;
    }

    public static void bob(ModelPart box, float speed, float degree, float offset, float weight, float limbSwing, float limbSwingAmount) {
        box.y = degree * limbSwingAmount * Mth.wrapDegrees(speed * limbSwing + offset) + weight * limbSwingAmount;
    }

    public static void walk(ModelPart box, float speed, float degree, float offset, float weight, float limbSwing, float limbSwingAmount) {
        box.xRot = degree * limbSwingAmount * Mth.wrapDegrees(speed * limbSwing + offset) + weight * limbSwingAmount;
    }
}