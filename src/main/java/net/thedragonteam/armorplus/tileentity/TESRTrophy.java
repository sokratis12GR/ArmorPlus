package net.thedragonteam.armorplus.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRTrophy extends TileEntitySpecialRenderer<TileEntityTrophy> {

    @Override
    public void render(TileEntityTrophy te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        render(te, x, y, z, partialTicks, te.getEntityScale());
    }

    public void render(TileEntityTrophy te, double x, double y, double z, float partialTicks, float size) {
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        double boop = Minecraft.getSystemTime() / 1000.0;
        GlStateManager.translate(x + 0.5, y + Math.sin(boop % (2 * Math.PI)) * 0.25 + 1.5, z + 0.5);
        GlStateManager.rotate((float) (boop * 40.0 % 360), 0f, 1f, 0f);
        GlStateManager.scale(size, size, size);
        renderMob(te, partialTicks);
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
    }

    /**
     * Render the mob on top of the block
     */
    private void renderMob(TileEntityTrophy te, float partialTicks) {
        Entity entity = te.getCachedEntity();
        if (entity != null) {
            RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
            float f = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;
            renderManager.renderEntity(entity, 0, 0, 0, f, partialTicks, false);
        }
    }
}
