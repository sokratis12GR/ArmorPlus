package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

import java.util.function.Supplier;

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
        boolean hasSoulStealer = false;
        if (trueSource instanceof ServerPlayer player) {
            ItemStack heldItem = player.getMainHandItem();
            int enchantmentLevel = heldItem.getEnchantmentLevel(player.level().registryAccess()
                    .lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(SOUL_STEALER));
            hasSoulStealer = enchantmentLevel > 0;
        }

        // Map the mob class to the configuration (subclasses first: an Elder Guardian is also a Guardian)
        MobDropConfig cfg = switch (entity) {
            case WitherBoss ignored -> MobDropConfig.of(config.witherBossDrops, "wither_bone", "soul_wither_boss");
            case EnderDragon ignored -> MobDropConfig.of(config.enderDragonDrops, "ender_dragon_scale", "soul_ender_dragon");
            case ElderGuardian ignored -> MobDropConfig.of(config.elderGuardianDrops, "guardian_scale", "soul_elder_guardian");
            case WitherSkeleton ignored -> MobDropConfig.of(config.witherSkeletonDrops, "wither_bone", "soul_wither_skeleton");
            case Guardian ignored -> MobDropConfig.of(config.guardianDrops, "guardian_scale", "soul_guardian");
            case EnderMan ignored -> MobDropConfig.of(config.endermanDrops, null, "soul_enderman");
            case Blaze ignored -> MobDropConfig.of(config.blazeDrops, null, "soul_blaze");
            case Warden ignored -> MobDropConfig.of(config.wardenDrops, "warden_heart", null);
            default -> null;
        };

        if (cfg == null) return;

        // Regular drops (a chance of 0 disables them)
        if (cfg.regularItem != null && RAND.nextDouble() < cfg.regularChance.get()) {
            int min = cfg.regularMin.get();
            int amount = min + RAND.nextInt(Math.max(cfg.regularMax.get(), min) - min + 1);
            if (amount > 0) dropItem(entity, cfg.regularItem, amount);
        }

        // Soul drops
        if (hasSoulStealer && cfg.soulItem != null && RAND.nextDouble() < cfg.soulChance.get()) {
            dropItem(entity, cfg.soulItem, 1);
        }
    }

    /** The config values (resolved lazily, as they can change at runtime) that decide what a mob drops. */
    private record MobDropConfig(String regularItem, Supplier<Integer> regularMin, Supplier<Integer> regularMax,
                                 Supplier<Double> regularChance, Supplier<Double> soulChance, String soulItem) {

        /** @param regularItem the material, or null if the mob has none (same for {@code soulItem}) */
        static MobDropConfig of(ArmorPlusConfig.DropConfig c, String regularItem, String soulItem) {
            return new MobDropConfig(regularItem,
                    regularItem == null ? () -> 0 : c.regularDropMin()::get,
                    regularItem == null ? () -> 0 : c.regularDropMax()::get,
                    regularItem == null ? () -> 0.0 : c.regularDropChance()::get,
                    soulItem == null ? () -> 0.0 : c.soulDropChance()::get, soulItem);
        }
    }

    private static void dropItem(Entity entity, String item, int amount) {
        entity.spawnAtLocation(new ItemStack(getAPItem(item), amount));
    }
}
