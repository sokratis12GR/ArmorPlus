package com.sofodev.armorplus.common.tileentity.tesr;

import com.sofodev.armorplus.common.tileentity.TileSwordDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRSwordDisplay extends TileEntitySpecialRenderer<TileSwordDisplay> {
    @Override
    public void render(TileSwordDisplay te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        this.render(te, x, y, z, partialTicks, destroyStage);
    }

    public void render(TileSwordDisplay te, double x, double y, double z, float partialTicks, int destroyStage) {
        ItemStack sword = te.getItemStack();
        if (!sword.isEmpty()) {
            this.renderItem(sword, x, y, z);
        }
    }

    private void renderItem(ItemStack stack, double x, double y, double z) {
        RenderItem renderer = Minecraft.getMinecraft().getRenderItem();

        GlStateManager.pushMatrix();

        GlStateManager.translate(x + 0.500D, y + 0.600D, z + 0.500D);
        GlStateManager.scale(1D, 1D, 1D);
        GlStateManager.rotate(180f, 1f, 0f, 0f);
        GlStateManager.rotate(90f, 0f, 1f, 0f);
        GlStateManager.rotate(-45f, 0f, 0f, 1f);
        renderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED);

        GlStateManager.popMatrix();
    }

}
