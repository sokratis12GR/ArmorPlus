/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entitygolem.EntityIceGolem;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderIceGolem extends RenderLiving<EntityIceGolem> {
    private static final ResourceLocation ICE_GOLEM_TEXTURES = new ResourceLocation(ArmorPlus.MODID, "textures/entity/ice_golem.png");

    public RenderIceGolem(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelArmorStand(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(@Nonnull EntityIceGolem entity) {
        return ICE_GOLEM_TEXTURES;
    }

    protected void applyRotations(EntityIceGolem entityLiving, float p_77043_2_, float p_77043_3_, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, p_77043_3_, partialTicks);

        if ((double) entityLiving.limbSwingAmount >= 0.01D) {
            float f = 13.0F;
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            GlStateManager.rotate(6.5F * f2, 0.0F, 0.0F, 1.0F);
        }
    }
}