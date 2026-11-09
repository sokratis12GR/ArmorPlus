package com.sofodev.armorplus.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.rune.RuneRitual;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Shows where a Rune Altar's missing pedestals go as see-through pedestals in the world.
 * <p>
 * Purely visual and only on this client: it just draws, no block or block entity is placed and the server never hears of it. The
 * altar is only kept in memory, so it's gone after a crash or restart, and it's forgotten on logout, respawn and when changing
 * dimensions, when walking away, when the altar is gone and when the circle is complete.
 */
@EventBusSubscriber(modid = ArmorPlus.MODID, value = Dist.CLIENT)
public final class RitualCircleGhost {

    private static final double MAX_DISTANCE_SQR = 48.0 * 48.0;
    /** The boxes of models/block/empowering_pedestal.json, in pixels: from x, y, z, to x, y, z. */
    private static final int[][] BOXES = {{1, 0, 1, 15, 3, 15}, {4, 3, 4, 12, 11, 12}, {2, 11, 2, 14, 14, 14}};
    /** The top row of the side texture each box uses, from the same model. */
    private static final int[] SIDE_V = {13, 5, 2};

    private static @Nullable BlockPos altar;
    private static @Nullable ResourceKey<Level> dimension;

    /** @param blocked true if something is in the way of placing the pedestal there */
    private record Ghost(BlockPos pos, boolean blocked) {
    }

    private RitualCircleGhost() {
    }

    private static RenderType texture(String face) {
        return RenderType.entityTranslucent(ResourceLocation.fromNamespaceAndPath(MODID, "textures/block/empowering_pedestal_" + face + ".png"));
    }

    /** @return true if this altar's circle is being shown */
    public static boolean isShowing(BlockPos pos) {
        return pos.equals(altar);
    }

    /**
     * Shows this altar's circle, or hides it if it's already showing.
     *
     * @return true if it's showing now
     */
    public static boolean toggle(Level level, BlockPos pos) {
        if (isShowing(pos)) {
            clear();
            return false;
        }
        altar = pos.immutable();
        dimension = level.dimension();
        return true;
    }

    private static void clear() {
        altar = null;
        dimension = null;
    }

    @SubscribeEvent
    public static void onLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        clear();
    }

    /** The player respawned or changed dimensions. */
    @SubscribeEvent
    public static void onPlayerClone(ClientPlayerNetworkEvent.Clone event) {
        clear();
    }

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) return;
        BlockPos pos = altar;
        if (pos == null) return;
        Level level = Minecraft.getInstance().level;
        Vec3 camera = event.getCamera().getPosition();
        if (level == null || level.dimension() != dimension || !level.getBlockState(pos).is(ModBlocks.RUNE_ALTAR.get()) || RuneRitual.isActive(level, pos)
                || camera.distanceToSqr(Vec3.atCenterOf(pos)) > MAX_DISTANCE_SQR) {
            clear();
            return;
        }
        List<Ghost> ghosts = new ArrayList<>(RuneRitual.PEDESTAL_COUNT);
        for (BlockPos pedestal : RuneRitual.pedestalPositions(pos)) {
            BlockState state = level.getBlockState(pedestal);
            if (!state.is(RuneRitual.PEDESTALS)) ghosts.add(new Ghost(pedestal, !state.canBeReplaced()));
        }
        if (ghosts.isEmpty()) return;

        int alpha = 110 + (int) (50.0F * (0.5F + 0.5F * (float) Math.sin(Util.getMillis() / 400.0)));
        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource.BufferSource buffers = Minecraft.getInstance().renderBuffers().bufferSource();
        for (Ghost ghost : ghosts) {
            int green = ghost.blocked() ? 80 : 255;
            int blue = ghost.blocked() ? 80 : 255;
            poseStack.pushPose();
            poseStack.translate(ghost.pos().getX() - camera.x, ghost.pos().getY() - camera.y, ghost.pos().getZ() - camera.z);
            PoseStack.Pose pose = poseStack.last();
            VertexConsumer sides = buffers.getBuffer(texture("side"));
            for (int box = 0; box < BOXES.length; box++) sides(pose, sides, BOXES[box], SIDE_V[box], green, blue, alpha);
            VertexConsumer bottoms = buffers.getBuffer(texture("bottom"));
            for (int box = 0; box < BOXES.length; box++) {
                horizontal(pose, bottoms, BOXES[box], BOXES[box][1], -1.0F, green, blue, alpha);
                if (box < BOXES.length - 1) horizontal(pose, bottoms, BOXES[box], BOXES[box][4], 1.0F, green, blue, alpha);
            }
            VertexConsumer top = buffers.getBuffer(texture("top"));
            int[] topBox = BOXES[BOXES.length - 1];
            horizontal(pose, top, topBox, topBox[4], 1.0F, green, blue, alpha);
            poseStack.popPose();
        }
        buffers.endBatch(texture("side"));
        buffers.endBatch(texture("bottom"));
        buffers.endBatch(texture("top"));
    }

    /** The four sides of a box. */
    private static void sides(PoseStack.Pose pose, VertexConsumer buffer, int[] box, int v, int green, int blue, int alpha) {
        float x0 = box[0] / 16.0F, y0 = box[1] / 16.0F, z0 = box[2] / 16.0F, x1 = box[3] / 16.0F, y1 = box[4] / 16.0F, z1 = box[5] / 16.0F;
        float u0 = box[0] / 16.0F, u1 = box[3] / 16.0F, v0 = v / 16.0F, v1 = (v + box[4] - box[1]) / 16.0F;
        quad(pose, buffer, new float[][] {{x1, y1, z0}, {x1, y0, z0}, {x0, y0, z0}, {x0, y1, z0}}, u0, v0, u1, v1, 0.0F, 0.0F, -1.0F, green, blue, alpha);
        quad(pose, buffer, new float[][] {{x0, y1, z1}, {x0, y0, z1}, {x1, y0, z1}, {x1, y1, z1}}, u0, v0, u1, v1, 0.0F, 0.0F, 1.0F, green, blue, alpha);
        quad(pose, buffer, new float[][] {{x0, y1, z0}, {x0, y0, z0}, {x0, y0, z1}, {x0, y1, z1}}, u0, v0, u1, v1, -1.0F, 0.0F, 0.0F, green, blue, alpha);
        quad(pose, buffer, new float[][] {{x1, y1, z1}, {x1, y0, z1}, {x1, y0, z0}, {x1, y1, z0}}, u0, v0, u1, v1, 1.0F, 0.0F, 0.0F, green, blue, alpha);
    }

    /** The top or bottom of a box at the height. */
    private static void horizontal(PoseStack.Pose pose, VertexConsumer buffer, int[] box, int height, float normal, int green, int blue, int alpha) {
        float x0 = box[0] / 16.0F, z0 = box[2] / 16.0F, x1 = box[3] / 16.0F, z1 = box[5] / 16.0F, y = height / 16.0F;
        quad(pose, buffer, new float[][] {{x0, y, z0}, {x0, y, z1}, {x1, y, z1}, {x1, y, z0}}, x0, z0, x1, z1, 0.0F, normal, 0.0F, green, blue, alpha);
    }

    /** @param corners top left, bottom left, bottom right, top right on the texture */
    private static void quad(PoseStack.Pose pose, VertexConsumer buffer, float[][] corners, float u0, float v0, float u1, float v1, float nx, float ny, float nz, int green, int blue, int alpha) {
        vertex(pose, buffer, corners[0], u0, v0, nx, ny, nz, green, blue, alpha);
        vertex(pose, buffer, corners[1], u0, v1, nx, ny, nz, green, blue, alpha);
        vertex(pose, buffer, corners[2], u1, v1, nx, ny, nz, green, blue, alpha);
        vertex(pose, buffer, corners[3], u1, v0, nx, ny, nz, green, blue, alpha);
    }

    private static void vertex(PoseStack.Pose pose, VertexConsumer buffer, float[] corner, float u, float v, float nx, float ny, float nz, int green, int blue, int alpha) {
        buffer.addVertex(pose, corner[0], corner[1], corner[2]).setColor(255, green, blue, alpha).setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(pose, nx, ny, nz);
    }
}
