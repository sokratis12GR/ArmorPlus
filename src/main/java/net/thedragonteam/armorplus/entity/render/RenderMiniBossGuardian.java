/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.thedragonteam.armorplus.entity.entityminibossguardian.EntityMiniBossGuardian;
import net.thedragonteam.armorplus.entity.models.ModelMiniBossGuardian;

import javax.annotation.Nonnull;

public class RenderMiniBossGuardian extends RenderLivingBase<EntityMiniBossGuardian> {

    public static final RenderMiniBossGuardian.Factory FACTORY = new RenderMiniBossGuardian.Factory();
    private ResourceLocation mobTexture = new ResourceLocation("armorplus:textures/entity/mini_boss_guardian.png");

    public RenderMiniBossGuardian(RenderManager rendermanagerIn) {
        // We use the vanilla zombie model here and we simply
        // retexture it. Of course you can make your own model
        super(rendermanagerIn, new ModelMiniBossGuardian(), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityMiniBossGuardian entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityMiniBossGuardian> {

        @Override
        public Render<? super EntityMiniBossGuardian> createRenderFor(RenderManager manager) {
            return new RenderMiniBossGuardian(manager);
        }

    }

}