package net.thedragonteam.armorplus.entity.dungeon.guardian;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
public class RenderGuardianOverlord extends RenderLiving<EntityGuardianOverlord> {
    private static final ResourceLocation GUARDIAN_OVERLORD_TEXTURE = new ResourceLocation("armorplus:textures/entity/guardian_overlord.png");

    public RenderGuardianOverlord(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelGuardianOverlord(), 5.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGuardianOverlord entity) {
        return GUARDIAN_OVERLORD_TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityGuardianOverlord entityLivingBaseIn, float partialTickTime) {
        GlStateManager.scale(7.0F, 7.0F, 7.0F);
    }
}