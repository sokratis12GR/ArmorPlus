/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLapisArrow;

@SideOnly(Side.CLIENT)
public class RenderLapisArrow extends RenderArrow<EntityLapisArrow> {

    public static final ResourceLocation res = new ResourceLocation(ArmorPlus.MODID, "textures/entity/projectiles/lapis_arrow.png");

    public RenderLapisArrow(RenderManager rm) {
        super(rm);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityLapisArrow entity) {
        return res;
    }

}