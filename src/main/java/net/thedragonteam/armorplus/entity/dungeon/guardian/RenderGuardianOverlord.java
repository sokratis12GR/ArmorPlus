package net.thedragonteam.armorplus.entity.dungeon.guardian;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
@SideOnly(Side.CLIENT)
public class RenderGuardianOverlord extends RenderLiving<EntityGuardianOverlord> {

    public RenderGuardianOverlord(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelGuardianOverlord(), 3.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGuardianOverlord entity) {
        return setRL("textures/entity/dungeon/guardian_overlord.png");
    }

    @Override
    protected void preRenderCallback(EntityGuardianOverlord entityLivingBaseIn, float partialTickTime) {
        GlStateManager.scale(4.0F, 4.0F, 4.0F);
    }
}