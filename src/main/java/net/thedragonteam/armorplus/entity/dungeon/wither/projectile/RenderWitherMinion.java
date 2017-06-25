package net.thedragonteam.armorplus.entity.dungeon.wither.projectile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
public class RenderWitherMinion extends Render<EntityWitherMinion> {
    private static final ResourceLocation WITHER_MINION_TEXTURE = new ResourceLocation(ArmorPlus.MODID, "textures/entity/wither/wither_minion.png");

    public RenderWitherMinion(RenderManager renderManager) {
        super(renderManager);
        this.shadowSize = 0.5F;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWitherMinion entity) {
        return WITHER_MINION_TEXTURE;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityWitherMinion entity, double x, double y, double z, float entityYaw, float partialTicks) {
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y + 0.5F, (float) z);

        if (partialTicks + 1.0F < 10.0F) {
            float f = 1.0F - (partialTicks + 1.0F) / 10.0F;
            f = MathHelper.clamp(f, 0.0F, 1.0F);
            f = f * f;
            f = f * f;
            float f1 = 1.0F + f * 0.3F;
            GlStateManager.scale(f1, f1, f1);
        }
        this.bindEntityTexture(entity);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(-0.5F, -0.5F, 0.5F);
        blockrendererdispatcher.renderBlockBrightness(Blocks.TNT.getDefaultState(), entity.getBrightness());
        GlStateManager.translate(0.0F, 0.0F, 1.0F);

        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
            blockrendererdispatcher.renderBlockBrightness(Blocks.TNT.getDefaultState(), 1.0F);
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

}