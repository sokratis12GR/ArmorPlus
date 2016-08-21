/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;

import javax.annotation.Nonnull;

/**
 * net.thedragonteam.armorplus.entity.render
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */
public class RenderPowerfulZombie extends RenderLiving<EntityEnderDragonZombie> {

    private ResourceLocation mobTexture = new ResourceLocation("armorplus:textures/entity/ender_dragon_zombie.png");

    public static final Factory FACTORY = new Factory();

    public RenderPowerfulZombie(RenderManager rendermanagerIn) {
        //Using Minecraft Zombie's Texture and Re-Texturing it
        super(rendermanagerIn, new ModelZombie(), 0.5F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityEnderDragonZombie entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityEnderDragonZombie> {

        @Override
        public Render<? super EntityEnderDragonZombie> createRenderFor(RenderManager manager) {
            return new RenderPowerfulZombie(manager);
        }

    }

}