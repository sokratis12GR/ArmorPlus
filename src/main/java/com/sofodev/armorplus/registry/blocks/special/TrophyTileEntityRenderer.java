//package com.sofodev.armorplus.registry.blocks.special;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
//import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
//import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
//import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
//import net.minecraft.world.entity.Entity;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//@OnlyIn(Dist.CLIENT)
//public class TrophyTileEntityRenderer implements BlockEntityRenderer<TrophyTile> {
//    public TrophyTileEntityRenderer(BlockEntityRendererProvider.Context ctx) {
////        super(p_i226016_1_);
//    }
//
//    @Override
//    public void render(TrophyTile tile, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
//        matrix.pushPose();
//        matrix.translate(0.5D, 1, 0.5D);
//        TrophyTile trophy = (TrophyTile) tile;
//        Entity entity = trophy.getDisplayEntity();
//        if (entity != null) {
//            float scale = trophy.getEntityScale();
//            matrix.translate(0.0D, 0.4F, 0.0D);
//            //      matrix.mulPose(Vector3f.YP.rotationDegrees((float) MathHelper.lerp(partialTicks, oSpin, spin) * 0.2f));
//            matrix.translate(0.0D, -0.2F, 0.0D);
//            matrix.scale(scale, scale, scale);
//            EntityRenderDispatcher manager = Minecraft.getInstance().getEntityRenderDispatcher();
//            float f = entity.getYRot() + (entity.getYRot() - entity.yRotO) * partialTicks;
//            manager.render(entity, 0.0D, 0.0D, 0.0D, f, partialTicks, matrix, buffer, combinedLight);
//        }
//        matrix.popPose();
//    }
//
//    @Override
//    public boolean shouldRenderOffScreen(TrophyTile tile) {
//        return true;
//    }
//}
