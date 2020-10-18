package com.sofodev.armorplus.registry.entities.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.WitherSkullRenderer;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author Sokratis Fotkatzikis
 **/
@OnlyIn(Dist.CLIENT)
public class WitherMinionRenderer extends EntityRenderer<WitherMinionEntity> {
    private static final ResourceLocation WITHER_TEXTURES = new ResourceLocation("textures/entity/wither/wither.png");

    /**
     * The Skeleton's head model.
     */
    private final GenericHeadModel skeletonHeadModel = new GenericHeadModel();

    public WitherMinionRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    private float getRenderYaw(float prevRotationYaw, float rotationYaw, float partialTicks) {
        float rotation;

        rotation = rotationYaw - prevRotationYaw;
        while (rotation < -180.0F) {

            rotation += 360.0F;
        }

        while (rotation >= 180.0F) {
            rotation -= 360.0F;
        }

        return prevRotationYaw + partialTicks * rotation;
    }

    @Override
    public void render(WitherMinionEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(WitherMinionEntity entity) {
        return null;
    }
}