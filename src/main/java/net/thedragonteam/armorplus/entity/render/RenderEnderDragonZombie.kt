/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.render

import net.minecraft.client.model.ModelZombie
import net.minecraft.client.renderer.entity.Render
import net.minecraft.client.renderer.entity.RenderBiped
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor
import net.minecraft.client.renderer.entity.layers.LayerHeldItem
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.client.registry.IRenderFactory
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie

/**
 * net.thedragonteam.armorplus.entity.render
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
class RenderEnderDragonZombie(rendermanagerIn: RenderManager) : RenderBiped<EntityEnderDragonZombie>(rendermanagerIn, ModelZombie(), 0.5f) {
    private val mobTexture = ResourceLocation("armorplus:textures/entity/ender_dragon_zombie.png")

    init {
        this.layerRenderers[0]
        this.addLayer(LayerHeldItem(this))
        object : LayerBipedArmor(this) {
            override fun initArmor() {
                this.modelLeggings = ModelZombie(0.5f, true)
                this.modelArmor = ModelZombie(1.0f, true)
            }
        }
    }//Using Minecraft Zombie's Texture and Re-Texturing it

    override fun getEntityTexture(entity: EntityEnderDragonZombie): ResourceLocation {
        return mobTexture
    }

    class Factory : IRenderFactory<EntityEnderDragonZombie> {

        override fun createRenderFor(manager: RenderManager): Render<in EntityEnderDragonZombie> {
            return RenderEnderDragonZombie(manager)
        }

    }

    companion object {

        val FACTORY = Factory()
    }

}