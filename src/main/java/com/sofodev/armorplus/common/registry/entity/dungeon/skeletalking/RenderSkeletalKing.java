/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking;

import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * @author Sokratis Fotkatzikis
 **/
public class RenderSkeletalKing extends RenderLiving<EntitySkeletalKing> {

    public RenderSkeletalKing(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelDungeonSkeletalKing(), 3.0F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySkeletalKing entity) {
        return Utils.setRL("textures/entity/dungeon/skeletal_king.png");
    }

    @Override
    protected void preRenderCallback(EntitySkeletalKing entityLivingBaseIn, float partialTickTime) {
        GlStateManager.scalef(7.0F, 7.0F, 7.0F);
    }
}
