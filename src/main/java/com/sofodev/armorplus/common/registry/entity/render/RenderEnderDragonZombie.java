/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.entity.render;

import com.sofodev.armorplus.common.registry.entity.mobs.EntityEnderDragonZombie;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.model.ModelZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * @author Sokratis Fotkatzikis
 */
@OnlyIn(Dist.CLIENT)
public class RenderEnderDragonZombie extends RenderBiped<EntityEnderDragonZombie> {

    public static final Factory FACTORY = new Factory();

    private ResourceLocation mobTexture = Utils.setRL("textures/entity/ender_dragon_zombie.png");

    public RenderEnderDragonZombie(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelZombie(), 0.5f);
        this.layerRenderers.get(0).shouldCombineTextures();
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this) {
            @Override
            protected void initArmor() {
                this.modelLeggings = new ModelZombie(1.5f, true);
                this.modelArmor = new ModelZombie(1.5f, true);
            }
        });
        //this.addLayer(new LayerElytra(this));
    }

    //Using Minecraft Zombie's Texture and Re-Texturing it
    @Override
    protected ResourceLocation getEntityTexture(EntityEnderDragonZombie entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityEnderDragonZombie> {
        @Override
        public Render<? super EntityEnderDragonZombie> createRenderFor(RenderManager manager) {
            return new RenderEnderDragonZombie(manager);
        }
    }

}