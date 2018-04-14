package net.thedragonteam.armorplus.entity.dungeon.skeletalking;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class RenderSkeletalKing extends RenderLiving<EntitySkeletalKing> {

    public RenderSkeletalKing(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelDungeonSkeletalKing(), 3.0F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySkeletalKing entity) {
        return setRL("textures/entity/dungeon/skeletal_king.png");
    }

    @Override
    protected void preRenderCallback(EntitySkeletalKing entityLivingBaseIn, float partialTickTime) {
        GlStateManager.scale(7.0F, 7.0F, 7.0F);
    }
}
