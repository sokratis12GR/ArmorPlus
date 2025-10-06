package com.sofodev.armorplus.registry.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record SoulStealerEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<SoulStealerEnchantmentEffect> CODEC = MapCodec.unit(SoulStealerEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel p_342783_, int p_345287_, EnchantedItemInUse p_343696_, Entity p_343545_, Vec3 p_342660_) {

    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
//
//    @Override
//    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
//
//        record MobDropConfig(Supplier<Boolean> trophyEnabled, Supplier<Boolean> regularEnabled,
//                             Supplier<Boolean> soulEnabled, String regularItem, int regularAmountMin,
//                             int regularAmountMax, String soulItem, float trophyScale) {
//        }
//
//        // Map the mob class to the configuration
//        Map<Class<? extends LivingEntity>, MobDropConfig> dropMap = Map.of(
//                WitherBoss.class, new MobDropConfig(() -> config.witherBossDrops().enableTrophyDrops().get(),
//                        () -> config.witherBossDrops().enableRegularDrops().get(),
//                        () -> config.witherBossDrops().enableSoulDrops().get(),
//                        "wither_bone", 4, 6, "soul_wither_boss", 0.2f),
//                EnderDragon.class, new MobDropConfig(() -> config.enderDragonDrops().enableTrophyDrops().get(),
//                        () -> config.enderDragonDrops().enableRegularDrops().get(),
//                        () -> config.enderDragonDrops().enableSoulDrops().get(),
//                        "ender_dragon_scale", 4, 6, "soul_ender_dragon", 0.1f),
//                ElderGuardian.class, new MobDropConfig(() -> config.elderGuardianDrops().enableTrophyDrops().get(),
//                        () -> config.elderGuardianDrops().enableRegularDrops().get(),
//                        () -> config.elderGuardianDrops().enableSoulDrops().get(),
//                        "guardian_scale", 4, 6, "soul_elder_guardian", 0.2f),
//                WitherSkeleton.class, new MobDropConfig(() -> false,
//                        () -> config.witherSkeletonDrops().enableRegularDrops().get(),
//                        () -> config.witherSkeletonDrops().enableSoulDrops().get(),
//                        "wither_bone", 0, 3, "soul_wither_skeleton", 0f),
//                Guardian.class, new MobDropConfig(() -> false,
//                        () -> config.guardianDrops().enableRegularDrops().get(),
//                        () -> config.guardianDrops().enableSoulDrops().get(),
//                        "guardian_scale", 0, 3, "soul_guardian", 0f),
//                EnderMan.class, new MobDropConfig(() -> false,
//                        () -> false,
//                        () -> config.endermanDrops().enableSoulDrops().get(),
//                        null, 0, 0, "soul_enderman", 0f),
//                Blaze.class, new MobDropConfig(() -> false,
//                        () -> false,
//                        () -> config.blazeDrops().enableSoulDrops().get(),
//                        null, 0, 0, "soul_blaze", 0f)
//        );
//
//
//        MobDropConfig cfg = dropMap.entrySet().stream()
//                .filter(entry -> entry.getKey().isInstance(entity))
//                .map(Map.Entry::getValue)
//                .findFirst()
//                .orElse(null);
//
//        if (cfg == null) return;
//
//        // Trophy
//        if (cfg.trophyEnabled.get()) dropTrophyItem((LivingEntity) entity, entity.getType(), cfg.trophyScale);
//
//        // Regular drops
//        if (cfg.regularEnabled.get() && cfg.regularItem != null) {
//            int amount = RAND.nextInt(cfg.regularAmountMax - cfg.regularAmountMin + 1) + cfg.regularAmountMin;
//            dropItem(entity, cfg.regularItem, amount);
//        }
//
//        // Soul drops
//        if (cfg.soulEnabled.get() && cfg.soulItem != null) {
//            boolean drop = !(entity instanceof WitherSkeleton || entity instanceof Guardian
//                    || entity instanceof EnderMan || entity instanceof Blaze) || RAND.nextInt(4) == 0;
//            if (drop) dropItem(entity, cfg.soulItem, 1);
//        }
//    }
//
//
//    private static void dropTrophyItem(LivingEntity entity, EntityType<?> type, float scale) {
//        ItemStack trophy = new ItemStack(getAPItem("trophy"));
//        CompoundTag tag = new CompoundTag();
//        SpawnData trophyEntity = new SpawnData();
//        ResourceLocation key = ForgeRegistries.ENTITY_TYPES.getKey(type);
//        if (key == null) key = ResourceLocation.parse("minecraft:pig");
//        trophyEntity.getEntityToSpawn().putString("id", key.toString());
//        tag.put("DisplayEntity", trophyEntity.getEntityToSpawn().copy());
//        tag.putFloat("EntityScale", scale);
////        trophy.(tag);
//        entity.spawnAtLocation(trophy);
//    }
//
//    private static void dropItem(Entity entity, String item, int amount) {
//        entity.spawnAtLocation(new ItemStack(getAPItem(item), amount));
//    }

}