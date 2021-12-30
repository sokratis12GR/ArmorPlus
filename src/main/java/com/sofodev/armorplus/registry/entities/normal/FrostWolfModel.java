//package com.sofodev.armorplus.registry.entities.normal;
//
//import com.sofodev.armorplus.ArmorPlus;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.TamableAnimal;
//import software.bernie.geckolib3.core.IAnimatable;
//import software.bernie.geckolib3.model.AnimatedGeoModel;
//
//public class FrostWolfModel<T extends TamableAnimal & IAnimatable &> extends AnimatedGeoModel<T> {
//
//    @Override
//    public ResourceLocation getModelLocation(T object) {
//        return new ResourceLocation(ArmorPlus.MODID, "geo/frost_wolf.geo.json");
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(T object) {
//        if (object.isTame()) {
//            return new ResourceLocation(ArmorPlus.MODID, "textures/entity/frost_wolf_tame.png");
//        } else {
//            return new ResourceLocation(ArmorPlus.MODID, "textures/entity/frost_wolf.png");
//        }
//    }
//
//    @Override
//    public ResourceLocation getAnimationFileLocation(T object) {
//        return new ResourceLocation(ArmorPlus.MODID, "animations/frost_wolf.animation.json");
//    }
//}