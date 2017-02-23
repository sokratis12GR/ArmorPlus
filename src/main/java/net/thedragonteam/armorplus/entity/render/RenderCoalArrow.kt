/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.render

import net.minecraft.client.renderer.entity.RenderArrow
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.entity.entityarrow.EntityCoalArrow

@SideOnly(Side.CLIENT)
class RenderCoalArrow(rm: RenderManager) : RenderArrow<EntityCoalArrow>(rm) {

    public override fun getEntityTexture(entity: EntityCoalArrow): ResourceLocation? {
        return res
    }

    companion object {

        private val res = ResourceLocation(ArmorPlus.MODID, "textures/entity/projectiles/coal_arrow.png")
    }

}