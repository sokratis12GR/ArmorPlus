package com.sofodev.armorplus.registry.blocks.special;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TESRSwordDisplay extends TileEntityRenderer<SwordDisplayTile> {

    @Override
    public void render(SwordDisplayTile te, double x, double y, double z, float partialTicks, int destroyStage) {
        ItemStack sword = te.getSword();
        if (!sword.isEmpty()) {
            this.renderItem(te, sword, x, y, z);
        }
        super.render(te, x, y, z, partialTicks, destroyStage);
    }

    private void renderItem(TileEntity te, ItemStack stack, double x, double y, double z) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();

        GlStateManager.pushMatrix();

        GlStateManager.translated(x + 0.500D, y + 0.600D, z + 0.500D);
        GlStateManager.scaled(1D, 1D, 1D);
        switch (te.getBlockState().get(SwordDisplayBlock.FACING)) {
            case WEST:
            case EAST:
                this.rotateItem(180f, 90f, -45f); // default vlues
                break;
            case NORTH:
            case SOUTH:
                this.rotateItem(180f, 180f, -45f);
                break;
        }
        renderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED);

        GlStateManager.popMatrix();
    }

    private void rotateItem(float a, float b, float c) {
        GlStateManager.rotatef(a, 1f, 0f, 0f);
        GlStateManager.rotatef(b, 0f, 1f, 0f);
        GlStateManager.rotatef(c, 0f, 0f, 1f);
    }

}