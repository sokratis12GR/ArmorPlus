package com.sofodev.armorplus.registry.entities.bosses;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

/**
 * @author Sokratis Fotkatzikis
 **/
public class SkeletalKingRenderer extends GeoEntityRenderer<SkeletalKingEntity> {
    public SkeletalKingRenderer(Context renderManager) {
        super(renderManager, new SkeletalKingAnimatedModel<>());
    }

    @Override
    public void render(SkeletalKingEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
        stack.scale(7, 7, 7);
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
    }
}