package com.sofodev.armorplus.registry.entities.normal;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FrostWolfModel<T extends TameableEntity & IAnimatable & IAngerable> extends AnimatedGeoModel<T> {

    @Override
    public ResourceLocation getModelLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "geo/frost_wolf.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(T object) {
        if (object.isTame()) {
            return new ResourceLocation(ArmorPlus.MODID, "textures/entity/frost_wolf_tame.png");
        } else {
            return new ResourceLocation(ArmorPlus.MODID, "textures/entity/frost_wolf.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "animations/frost_wolf.animation.json");
    }
}