/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entityarrow.EntityCoalArrow;

@SideOnly(Side.CLIENT)
public class RenderCoalArrow extends RenderArrow<EntityCoalArrow> {

    private static final ResourceLocation res = new ResourceLocation(ArmorPlus.MODID, "textures/entity/projectiles/coal_arrow.png");

    public RenderCoalArrow(RenderManager rm) {
        super(rm);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityCoalArrow entity) {
        return res;
    }

}