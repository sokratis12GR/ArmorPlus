/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.entities.renders;

import com.sofodev.armorplus.client.entities.models.ModelDungeonSkeletalKing;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.projectile.EntityWitherling;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * @author Sokratis Fotkatzikis
 **/
public class RenderWitherling extends RenderLiving<EntityWitherling> {

    public RenderWitherling(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelDungeonSkeletalKing(), 1.0f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityWitherling entity) {
        return Utils.setRL("textures/entity/dungeon/skeletal_king.png");
    }

    @Override
    protected void preRenderCallback(EntityWitherling entityLivingBaseIn, float partialTickTime) {
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
    }
}
