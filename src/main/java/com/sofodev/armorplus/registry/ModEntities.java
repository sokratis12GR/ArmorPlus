package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import com.sofodev.armorplus.registry.entities.arrows.ArrowType;
import com.sofodev.armorplus.registry.entities.arrows.impl.*;
import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingEntity;
import com.sofodev.armorplus.registry.entities.bosses.data.MobType;
import com.sofodev.armorplus.registry.entities.normal.WitherlingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.entities.arrows.ArrowType.*;
import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.world.entity.MobCategory.MISC;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    //Arrows
    public static final RegistryObject<EntityType<APArrowEntity>> COAL_ARROW = register("coal_arrow",
            () -> buildArrow(CoalArrowEntity::new, COAL));
    public static final RegistryObject<EntityType<APArrowEntity>> LAPIS_ARROW = register("lapis_arrow",
            () -> buildArrow(LapisArrowEntity::new, LAPIS));
    public static final RegistryObject<EntityType<APArrowEntity>> REDSTONE_ARROW = register("redstone_arrow",
            () -> buildArrow(RedstoneArrowEntity::new, REDSTONE));
    public static final RegistryObject<EntityType<APArrowEntity>> EMERALD_ARROW = register("emerald_arrow",
            () -> buildArrow(EmeraldArrowEntity::new, EMERALD));
    public static final RegistryObject<EntityType<APArrowEntity>> OBSIDIAN_ARROW = register("obsidian_arrow",
            () -> buildArrow(ObsidianArrowEntity::new, OBSIDIAN));
    public static final RegistryObject<EntityType<APArrowEntity>> INFUSED_LAVA_ARROW = register("infused_lava_arrow",
            () -> buildArrow(InfusedLavaArrowEntity::new, INFUSED_LAVA));
    public static final RegistryObject<EntityType<APArrowEntity>> GUARDIAN_ARROW = register("guardian_arrow",
            () -> buildArrow(GuardianArrowEntity::new, GUARDIAN));
    public static final RegistryObject<EntityType<APArrowEntity>> SUPER_STAR_ARROW = register("super_star_arrow",
            () -> buildArrow(SuperStarArrowEntity::new, SUPER_STAR));
    public static final RegistryObject<EntityType<APArrowEntity>> ENDER_DRAGON_ARROW = register("ender_dragon_arrow",
            () -> buildArrow(EnderDragonArrowEntity::new, ENDER_DRAGON));
    //Bosses-Minions-Projectiles
    public static final RegistryObject<EntityType<SkeletalKingEntity>> SKELETAL_KING = register("skeletal_king",
            () -> build(SkeletalKingEntity::new, MobType.SKELETAL_KING));
    public static final RegistryObject<EntityType<WitherlingEntity>> WITHERLING = register("witherling",
            () -> build(WitherlingEntity::new, MobType.WITHERLING));
//    public static final RegistryObject<EntityType<DemonicDragonEntity>> DEMONIC_DRAGON = register("demonic_dragon",
//            () -> build(DemonicDragonEntity::new, MobType.DEMONIC_DRAGON));
//    public static final RegistryObject<EntityType<FrostWolfEntity>> FROST_WOLF = register("frost_wolf",
//            () -> build(FrostWolfEntity::new, MobType.FROST_WOLF));
//    public static final RegistryObject<EntityType<FrostWolfAlphaEntity>> FROST_WOLF_ALPHA = register("frost_wolf_alpha",
//            () -> build(FrostWolfAlphaEntity::new, MobType.FROST_WOLF_ALPHA));
//    public static final RegistryObject<EntityType<BoreasEntity>> BOREAS = register("boreas",
//            () -> build(BoreasEntity::new, MobType.BOREAS));

    /////////////////////
    // UTILITY METHODS //
    /////////////////////

    public static <ENTITY extends Entity> RegistryObject<EntityType<ENTITY>> register(String name, Supplier<EntityType<ENTITY>> sup) {
        return ENTITY_TYPES.register(name, sup);
    }

    /**
     * This is a preset for generating ArmorPlus arrow entity types
     *
     * @param factoryIn The Entity Class
     * @param data      The Arrow data used for the creation of the entityAttachCapabilitiesEvent (in this case its name)
     * @return an EntityType object with all the required information.
     */
    private static <ENTITY extends AbstractArrow> EntityType<ENTITY> buildArrow(EntityType.EntityFactory<ENTITY> factoryIn, ArrowType data) {
        return build(data.getItemArrowName(), EntityType.Builder.of(factoryIn, MISC).sized(0.5f, 0.5f));
    }

    private static <ENTITY extends Entity> EntityType<ENTITY> build(EntityType.EntityFactory<ENTITY> factory, MobType data) {
        EntityType.Builder<ENTITY> builder = EntityType.Builder.of(factory, data.getClassification())
                .setTrackingRange(data.getTrackingRange())
                .sized(data.getWidth(), data.getHeight());
        if (data.isImmuneToFire()) {
            builder.fireImmune();
        }

        return build(data.getName(), builder);
    }

    private static <ENTITY extends Entity> EntityType<ENTITY> build(String id, EntityType.Builder<ENTITY> builder) {
        ResourceLocation rl = setRL(id);
        return builder.build(rl.toString());
    }
}