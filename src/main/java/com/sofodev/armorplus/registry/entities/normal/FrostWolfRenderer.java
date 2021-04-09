package com.sofodev.armorplus.registry.entities.normal;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.passive.WolfEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

/**
 * @author Sokratis Fotkatzikis
 **/
public class FrostWolfRenderer extends GeoEntityRenderer<FrostWolfEntity> {
    public FrostWolfRenderer(EntityRendererManager renderManager) {
        super(renderManager, new FrostWolfModel<>());
    }
}