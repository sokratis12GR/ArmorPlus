/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;

import javax.annotation.Nonnull;

/**
 * net.thedragonteam.armorplus.entity.render
 * ArmorPlus created by sokratis12GR on 8/21/2016.
 * - TheDragonTeam
 */
public class RenderEnderDragonZombie extends RenderBiped<EntityEnderDragonZombie> {

    public static final Factory FACTORY = new Factory();
    public ModelBiped defaultModel;
    private ResourceLocation mobTexture = new ResourceLocation("armorplus:textures/entity/ender_dragon_zombie.png");

    public RenderEnderDragonZombie(RenderManager rendermanagerIn) {
        //Using Minecraft Zombie's Texture and Re-Texturing it
        super(rendermanagerIn, new ModelZombie(), 0.5F, 1.0F);
        LayerRenderer<?> layerrenderer = (LayerRenderer) this.layerRenderers.get(0);
        this.defaultModel = this.modelBipedMain;
        this.addLayer(new LayerHeldItem(this));
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
            protected void initArmor() {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityEnderDragonZombie entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityEnderDragonZombie> {

        @Override
        public Render<? super EntityEnderDragonZombie> createRenderFor(RenderManager manager) {
            return new RenderEnderDragonZombie(manager);
        }

    }

}