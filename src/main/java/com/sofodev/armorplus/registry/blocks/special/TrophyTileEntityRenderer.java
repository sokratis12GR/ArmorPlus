package com.sofodev.armorplus.registry.blocks.special;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TrophyTileEntityRenderer extends TileEntityRenderer<TrophyTile> {
    public TrophyTileEntityRenderer(TileEntityRendererDispatcher p_i226016_1_) {
        super(p_i226016_1_);
    }

    public void render(TrophyTile tile, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrix.pushPose();
        matrix.translate(0.5D, 1, 0.5D);
        TrophyTile trophy = (TrophyTile) tile.getTileEntity();
        Entity entity = trophy.getDisplayEntity();
        if (entity != null) {
            float scale = trophy.getEntityScale();
            matrix.translate(0.0D, 0.4F, 0.0D);
            //      matrix.mulPose(Vector3f.YP.rotationDegrees((float) MathHelper.lerp(partialTicks, oSpin, spin) * 0.2f));
            matrix.translate(0.0D, -0.2F, 0.0D);
            matrix.scale(scale, scale, scale);
            EntityRendererManager manager = Minecraft.getInstance().getEntityRenderDispatcher();
            float f = entity.yRot + (entity.yRot - entity.yRotO) * partialTicks;
            manager.render(entity, 0.0D, 0.0D, 0.0D, f, partialTicks, matrix, buffer, combinedLight);
        }
        matrix.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(TrophyTile tile) {
        return true;
    }
}
