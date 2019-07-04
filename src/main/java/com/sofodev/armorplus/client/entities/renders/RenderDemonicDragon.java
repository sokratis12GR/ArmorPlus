/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.entities.renders;

import com.sofodev.armorplus.client.entities.models.ModelDemonicDragon;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.demonicdragon.EntityDemonicDragon;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Sokratis Fotkatzikis
 **/
@SideOnly(Side.CLIENT)
public class RenderDemonicDragon extends RenderLiving<EntityDemonicDragon> {

    public RenderDemonicDragon(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelDemonicDragon(), 3.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityDemonicDragon entity) {
        return Utils.setRL("textures/entity/dungeon/demonic_dragon.png");
    }

    @Override
    protected void preRenderCallback(EntityDemonicDragon entityLivingBaseIn, float partialTickTime) {
        GlStateManager.scale(4.0F, 4.0F, 4.0F);
    }
}