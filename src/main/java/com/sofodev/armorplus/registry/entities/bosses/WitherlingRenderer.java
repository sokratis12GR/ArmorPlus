package com.sofodev.armorplus.registry.entities.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import javax.swing.text.html.parser.Entity;

/**
 * @author Sokratis Fotkatzikis
 **/
public class WitherlingRenderer extends MobRenderer<WitherlingEntity, SkeletalKingModel<WitherlingEntity>> {

    public WitherlingRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SkeletalKingModel<>(), 1.0f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(WitherlingEntity entity) {
        return Utils.setRL("textures/entity/dungeon/skeletal_king.png");
    }

    @Override
    protected void preRenderCallback(WitherlingEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
    }

}