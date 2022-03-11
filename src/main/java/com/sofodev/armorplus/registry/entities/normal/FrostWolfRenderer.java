package com.sofodev.armorplus.registry.entities.normal;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

/**
 * @author Sokratis Fotkatzikis
 **/
public class FrostWolfRenderer extends GeoEntityRenderer<FrostWolfEntity> {
    public FrostWolfRenderer(EntityRendererManager renderManager) {
        super(renderManager, new FrostWolfModel<>());
    }
}