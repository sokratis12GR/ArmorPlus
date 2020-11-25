package com.sofodev.armorplus.registry.entities.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/**
 * @author Sokratis Fotkatzikis
 **/
@OnlyIn(Dist.CLIENT)
public class SkeletalKingRenderer extends BipedRenderer<SkeletalKingEntity, SkeletalKingModel<SkeletalKingEntity>> {

    public SkeletalKingRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SkeletalKingModel<>(0), 0.0F);
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