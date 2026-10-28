package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.SpawnData;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.Map;
import java.util.function.Supplier;

import com.sofodev.armorplus.config.ArmorPlusConfig;

import static com.sofodev.armorplus.ArmorPlus.config;
import static com.sofodev.armorplus.events.ModGlobalEvents.RAND;
import static com.sofodev.armorplus.registry.ModEnchantments.SOUL_STEALER;
import static com.sofodev.armorplus.utils.Utils.getAPItem;

@EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModDropsEvents {


    @SubscribeEvent
    public static void onMobDeathEvent(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        Entity trueSource = event.getSource().getEntity();
        boolean isSourcePlayer = trueSource instanceof ServerPlayer;
        ServerPlayer player;
        ItemStack heldItem;
        boolean hasSoulStealer = false;
        if (isSourcePlayer) {
            player = (ServerPlayer) trueSource;
            heldItem = player.getMainHandItem();
            int enchantmentLevel = heldItem.getEnchantmentLevel(player.level().registryAccess().getOrThrow(SOUL_STEALER));
            if (enchantmentLevel > 0) {
                hasSoulStealer = true;
            }
        }

        // Map the mob class to the configuration
        Map<Class<? extends LivingEntity>, MobDropConfig> dropMap = Map.of(
                WitherBoss.class, MobDropConfig.boss(config.witherBossDrops, "wither_bone", "soul_wither_boss", 0.2f),
                EnderDragon.class, MobDropConfig.boss(config.enderDragonDrops, "ender_dragon_scale", "soul_ender_dragon", 0.1f),
                ElderGuardian.class, MobDropConfig.boss(config.elderGuardianDrops, "guardian_scale", "soul_elder_guardian", 0.2f),
                WitherSkeleton.class, MobDropConfig.mob(config.witherSkeletonDrops, "wither_bone", "soul_wither_skeleton"),
                Guardian.class, MobDropConfig.mob(config.guardianDrops, "guardian_scale", "soul_guardian"),
                EnderMan.class, MobDropConfig.mob(config.endermanDrops, null, "soul_enderman"),
                Blaze.class, MobDropConfig.mob(config.blazeDrops, null, "soul_blaze"),
                Warden.class, MobDropConfig.mob(config.wardenDrops, "warden_heart", null)
        );


        MobDropConfig cfg = dropMap.entrySet().stream()
                .filter(entry -> entry.getKey().isInstance(entity))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);

        if (cfg == null) return;

        // Trophy
        if (hasSoulStealer && cfg.trophyEnabled.get()) dropTrophyItem(entity, entity.getType(), cfg.trophyScale);

        // Regular drops
        if (cfg.regularEnabled.get() && cfg.regularItem != null && RAND.nextDouble() < cfg.regularChance.get()) {
            int min = cfg.regularMin.get();
            int amount = min + RAND.nextInt(Math.max(cfg.regularMax.get(), min) - min + 1);
            if (amount > 0) dropItem(entity, cfg.regularItem, amount);
        }

        // Soul drops
        if (hasSoulStealer && cfg.soulEnabled.get() && cfg.soulItem != null && RAND.nextDouble() < cfg.soulChance.get()) {
            dropItem(entity, cfg.soulItem, 1);
        }
    }

    /** The config values (resolved lazily, as they can change at runtime) that decide what a mob drops. */
    private record MobDropConfig(Supplier<Boolean> trophyEnabled, Supplier<Boolean> regularEnabled,
                                 Supplier<Boolean> soulEnabled, String regularItem, Supplier<Integer> regularMin,
                                 Supplier<Integer> regularMax, Supplier<Double> regularChance,
                                 Supplier<Double> soulChance, String soulItem, float trophyScale) {

        static MobDropConfig boss(ArmorPlusConfig.BossDropConfig c, String regularItem, String soulItem, float trophyScale) {
            return new MobDropConfig(c.enableTrophyDrops()::get, c.enableRegularDrops()::get, c.enableSoulDrops()::get,
                    regularItem, c.regularDropMin()::get, c.regularDropMax()::get, c.regularDropChance()::get,
                    c.soulDropChance()::get, soulItem, trophyScale);
        }

        static MobDropConfig mob(ArmorPlusConfig.DropConfig c, String regularItem, String soulItem) {
            return new MobDropConfig(() -> false,
                    regularItem == null ? () -> false : c.enableRegularDrops()::get,
                    soulItem == null ? () -> false : c.enableSoulDrops()::get,
                    regularItem, regularItem == null ? () -> 0 : c.regularDropMin()::get,
                    regularItem == null ? () -> 0 : c.regularDropMax()::get,
                    regularItem == null ? () -> 0.0 : c.regularDropChance()::get,
                    soulItem == null ? () -> 0.0 : c.soulDropChance()::get, soulItem, 0f);
        }
    }

    private static void dropTrophyItem(LivingEntity entity, EntityType<?> type, float scale) {
        ItemStack trophy = new ItemStack(getAPItem("trophy"));
        CompoundTag tag = new CompoundTag();
        SpawnData trophyEntity = new SpawnData();
        Identifier key = BuiltInRegistries.ENTITY_TYPE.getKey(type);
        if (key == null) key = Identifier.parse("minecraft:pig");
        trophyEntity.getEntityToSpawn().putString("id", key.toString());
        tag.put("DisplayEntity", trophyEntity.getEntityToSpawn().copy());
        tag.putFloat("EntityScale", scale);
//        trophy.(tag);
        if (entity.level() instanceof ServerLevel server) entity.spawnAtLocation(server, trophy);
    }

    private static void dropItem(Entity entity, String item, int amount) {
        if (entity.level() instanceof ServerLevel server) entity.spawnAtLocation(server, new ItemStack(getAPItem(item), amount));
    }
}
