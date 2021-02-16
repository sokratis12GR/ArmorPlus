package com.sofodev.armorplus.registry.entities.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

/**
 * @author Sokratis Fotkatzikis
 **/
public class DemonicDragonRenderer extends GeoEntityRenderer<DemonicDragonEntity> {
    public DemonicDragonRenderer(EntityRendererManager renderManager) {
        super(renderManager, new DemonicDragonAnimatedModel<>());
    }

    @Override
    public void render(DemonicDragonEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        stack.scale(7, 7, 7);
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
    }
}