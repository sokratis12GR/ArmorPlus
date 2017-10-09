/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.render

import net.minecraft.client.model.ModelArmorStand
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.entity.RenderLiving
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.entity.entitygolem.EntityIceGolem

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
class RenderIceGolem(renderManagerIn: RenderManager) : RenderLiving<EntityIceGolem>(renderManagerIn, ModelArmorStand(), 0.5f) {

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    override fun getEntityTexture(entity: EntityIceGolem): ResourceLocation? {
        return ICE_GOLEM_TEXTURES
    }

    override fun applyRotations(entityLiving: EntityIceGolem, p_77043_2_: Float, p_77043_3_: Float, partialTicks: Float) {
        super.applyRotations(entityLiving, p_77043_2_, p_77043_3_, partialTicks)

        if (entityLiving.limbSwingAmount.toDouble() >= 0.01) {
            //  val f = 13.0f
            val f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0f - partialTicks) + 6.0f
            val f2 = (Math.abs(f1 % 13.0f - 6.5f) - 3.25f) / 3.25f
            GlStateManager.rotate(6.5f * f2, 0.0f, 0.0f, 1.0f)
        }
    }

    companion object {
        private val ICE_GOLEM_TEXTURES = ResourceLocation(ArmorPlus.MODID, "textures/entity/ice_golem.png")
    }
}