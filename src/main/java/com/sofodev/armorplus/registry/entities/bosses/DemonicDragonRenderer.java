//package com.sofodev.armorplus.registry.entities.bosses;
//
//import com.mojang.blaze3d.matrix.PoseStack;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
//import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
//
///**
// * @author Sokratis Fotkatzikis
// **/
//public class DemonicDragonRenderer extends GeoEntityRenderer<DemonicDragonEntity> {
//    public DemonicDragonRenderer(Context renderManager) {
//        super(renderManager, new DemonicDragonAnimatedModel<>());
//    }
//
//    @Override
//    public void render(DemonicDragonEntity entity, float entityYaw, float partialTicks, PoseStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
//        stack.scale(2, 2, 2);
//        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
//    }
//}