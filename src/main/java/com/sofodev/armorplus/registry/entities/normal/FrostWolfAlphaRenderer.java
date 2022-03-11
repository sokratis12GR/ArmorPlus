package com.sofodev.armorplus.registry.entities.normal;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

/**
 * @author Sokratis Fotkatzikis
 **/
public class FrostWolfAlphaRenderer extends GeoEntityRenderer<FrostWolfAlphaEntity> {
    public FrostWolfAlphaRenderer(EntityRendererManager renderManager) {
        super(renderManager, new FrostWolfAlphaModel<>());
    }
}