package com.sofodev.armorplus.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sofodev.armorplus.registry.block.special.EmpoweringPedestalBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

/**
 * Draws the stack on an Empowering Pedestal, floating and turning above it.
 */
public class EmpoweringPedestalRenderer implements BlockEntityRenderer<EmpoweringPedestalBlockEntity, EmpoweringPedestalRenderer.State> {

    private static final float DEGREES_PER_TICK = 2.0F;
    private static final double HEIGHT = 0.95;

    private final ItemModelResolver itemModelResolver;

    public EmpoweringPedestalRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public State createRenderState() {
        return new State();
    }

    @Override
    public void extractRenderState(EmpoweringPedestalBlockEntity pedestal, State state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(pedestal, state, partialTicks, cameraPosition, breakProgress);
        this.itemModelResolver.updateForTopItem(state.item, pedestal.getItem(), ItemDisplayContext.GROUND, pedestal.getLevel(), null, (int) pedestal.getBlockPos().asLong());
        state.rotation = pedestal.getLevel() == null ? 0.0F : (pedestal.getLevel().getGameTime() + partialTicks) * DEGREES_PER_TICK;
    }

    @Override
    public void submit(State state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState camera) {
        if (state.item.isEmpty()) return;
        poseStack.pushPose();
        poseStack.translate(0.5, HEIGHT, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.rotation));
        state.item.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }

    public static class State extends BlockEntityRenderState {
        private final ItemStackRenderState item = new ItemStackRenderState();
        private float rotation;
    }
}
