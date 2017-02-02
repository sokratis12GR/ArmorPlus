/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entityarrow.EntityEnderDragonArrow;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderEnderDragonArrow extends RenderArrow<EntityEnderDragonArrow> {

    private static final ResourceLocation res = new ResourceLocation(ArmorPlus.MODID, "textures/entity/projectiles/ender_dragon_arrow.png");

    public RenderEnderDragonArrow(RenderManager rm) {
        super(rm);
    }

    @Override
    public ResourceLocation getEntityTexture(@Nonnull EntityEnderDragonArrow entity) {
        return res;
    }

}