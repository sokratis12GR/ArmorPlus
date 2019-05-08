/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entity.render;

import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/**
 * @author Sokratis Fotkatzikis
 */
@OnlyIn(Dist.CLIENT)
public class RenderModdedArrow<T extends EntityArrow> extends RenderArrow<T> {

    private ResourceLocation res;

    public RenderModdedArrow(RenderManager rm, String typeName) {
        super(rm);
        this.res = Utils.setRL("textures/entity/projectiles/" + typeName + "_arrow.png");
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return res;
    }

}