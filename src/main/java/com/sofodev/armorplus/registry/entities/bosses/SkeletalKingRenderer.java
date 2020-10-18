package com.sofodev.armorplus.registry.entities.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * @author Sokratis Fotkatzikis
 **/
public class SkeletalKingRenderer extends MobRenderer<SkeletalKingEntity, SkeletalKingModel<SkeletalKingEntity>> {

    public SkeletalKingRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SkeletalKingModel<>(), 3.0F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(SkeletalKingEntity entity) {
        return Utils.setRL("textures/entity/dungeon/skeletal_king.png");
    }

    @Override
    protected void preRenderCallback(SkeletalKingEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(7.0F, 7.0F, 7.0F);
    }

}