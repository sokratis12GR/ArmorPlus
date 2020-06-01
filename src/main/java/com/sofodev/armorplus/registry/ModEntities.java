package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import com.sofodev.armorplus.registry.entities.arrows.ArrowType;
import com.sofodev.armorplus.registry.entities.arrows.impl.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import static com.sofodev.armorplus.ArmorPlus.ENTITIES;
import static com.sofodev.armorplus.registry.entities.arrows.ArrowType.*;
import static net.minecraft.entity.EntityClassification.MISC;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final RegistryObject<EntityType<APArrowEntity>> COAL_ARROW = ENTITIES.register("coal_arrow",
            () -> buildArrow(CoalArrowEntity::new, COAL));
    public static final RegistryObject<EntityType<APArrowEntity>> LAPIS_ARROW = ENTITIES.register("lapis_arrow",
            () -> buildArrow(LapisArrowEntity::new, LAPIS));
    public static final RegistryObject<EntityType<APArrowEntity>> REDSTONE_ARROW = ENTITIES.register("redstone_arrow",
            () -> buildArrow(RedstoneArrowEntity::new, REDSTONE));
    public static final RegistryObject<EntityType<APArrowEntity>> EMERALD_ARROW = ENTITIES.register("emerald_arrow",
            () -> buildArrow(EmeraldArrowEntity::new, EMERALD));
    public static final RegistryObject<EntityType<APArrowEntity>> OBSIDIAN_ARROW = ENTITIES.register("obsidian_arrow",
            () -> buildArrow(ObsidianArrowEntity::new, OBSIDIAN));
    public static final RegistryObject<EntityType<APArrowEntity>> INFUSED_LAVA_ARROW = ENTITIES.register("infused_lava_arrow",
            () -> buildArrow(InfusedLavaArrowEntity::new, INFUSED_LAVA));
    public static final RegistryObject<EntityType<APArrowEntity>> GUARDIAN_ARROW = ENTITIES.register("guardian_arrow",
            () -> buildArrow(GuardianArrowEntity::new, GUARDIAN));
    public static final RegistryObject<EntityType<APArrowEntity>> SUPER_STAR_ARROW = ENTITIES.register("super_star_arrow",
            () -> buildArrow(SuperStarArrowEntity::new, SUPER_STAR));
    public static final RegistryObject<EntityType<APArrowEntity>> ENDER_DRAGON_ARROW = ENTITIES.register("ender_dragon_arrow",
            () -> buildArrow(EnderDragonArrowEntity::new, ENDER_DRAGON));

    private static <T extends Entity> EntityType<T> build(String id, EntityType.Builder<T> builder) {
        return builder.build(id);
    }

    ///////////////////
    // ARROW METHODS //
    ///////////////////

    private static <T extends AbstractArrowEntity> EntityType<T> buildArrow(EntityType.IFactory<T> factoryIn, ArrowType data) {
        return build(data.getItemArrowName(), EntityType.Builder.create(factoryIn, MISC).size(0.5f, 0.5f));
    }
}
