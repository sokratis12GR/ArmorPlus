package com.sofodev.armorplus.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sofodev.armorplus.registry.block.special.EmpoweringPedestalBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

/**
 * Draws the stack on an Empowering Pedestal, floating and turning above it.
 */
public class EmpoweringPedestalRenderer implements BlockEntityRenderer<EmpoweringPedestalBlockEntity> {

    private static final float DEGREES_PER_TICK = 2.0F;
    private static final double HEIGHT = 0.95;

    private final ItemRenderer itemRenderer;

    public EmpoweringPedestalRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(EmpoweringPedestalBlockEntity pedestal, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        ItemStack stack = pedestal.getItem();
        if (stack.isEmpty()) return;
        float rotation = pedestal.getLevel() == null ? 0.0F : (pedestal.getLevel().getGameTime() + partialTick) * DEGREES_PER_TICK;
        poseStack.pushPose();
        poseStack.translate(0.5, HEIGHT, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        this.itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, pedestal.getLevel(), (int) pedestal.getBlockPos().asLong());
        poseStack.popPose();
    }
}
