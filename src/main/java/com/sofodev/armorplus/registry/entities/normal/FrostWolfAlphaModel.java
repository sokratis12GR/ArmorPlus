package com.sofodev.armorplus.registry.entities.normal;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FrostWolfAlphaModel<T extends FrostWolfAlphaEntity> extends AnimatedGeoModel<T> {

    @Override
    public ResourceLocation getModelLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "geo/frost_wolf_alpha.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "textures/entity/frost_wolf_alpha.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "animations/frost_wolf_alpha.animation.json");
    }
}