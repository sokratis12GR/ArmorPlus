package com.sofodev.armorplus.registry.entities.bosses;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SkeletalKingAnimatedModel<T extends Mob & IAnimatable> extends AnimatedGeoModel<T> {

    @Override
    public ResourceLocation getModelLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "geo/skeletal_king.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "textures/entity/dungeon/skeletal_king_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "animations/skeletal_king.animation.json");
    }
}