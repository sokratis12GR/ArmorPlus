package com.sofodev.armorplus.registry.entities.bosses;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DemonicDragonAnimatedModel<T extends MobEntity & IAnimatable> extends AnimatedGeoModel<T> {

    @Override
    public ResourceLocation getModelLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "geo/demonic_dragon_v2.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(T object) {
        //        return new ResourceLocation(ArmorPlus.MODID, "textures/entity/dungeon/demonic_dragon_texture.png");
        //        return new ResourceLocation("textures/block/basalt_side.png");
        //        return new ResourceLocation(ArmorPlus.MODID, "textures/blocks/compressed_obsidian.png");
        //        return new ResourceLocation( "textures/block/crying_obsidian.png");
        //        return new ResourceLocation("textures/block/gilded_blackstone.png");
        return new ResourceLocation("textures/block/stone.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(T object) {
        return new ResourceLocation(ArmorPlus.MODID, "animations/demonic_dragon.animation.json");
    }
}