package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.SpawnData;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.Supplier;

import static com.sofodev.armorplus.events.ModGlobalEvents.RAND;
import static com.sofodev.armorplus.registry.ModEnchantments.SOUL_STEALER;
import static com.sofodev.armorplus.utils.Utils.getAPItem;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public final class ModDropsEvents {
    private ModDropsEvents() {
    }

    private record MobDropConfig(Supplier<Boolean> trophyEnabled, Supplier<Boolean> regularEnabled,
                                 Supplier<Boolean> soulEnabled, String regularItem, int regularAmountMin,
                                 int regularAmountMax, String soulItem, float trophyScale) {
    }

    // Suppliers defer config reads until the first mob death, after the COMMON config is registered.
    private static final Map<Class<? extends LivingEntity>, MobDropConfig> DROP_CONFIGS = Map.of(
            WitherBoss.class, new MobDropConfig(() -> ArmorPlus.config.witherBossDrops.enableTrophyDrops().get(),
                    () -> ArmorPlus.config.witherBossDrops.enableRegularDrops().get(),
                    () -> ArmorPlus.config.witherBossDrops.enableSoulDrops().get(),
                    "wither_bone", 4, 6, "soul_wither_boss", 0.2f),
            EnderDragon.class, new MobDropConfig(() -> ArmorPlus.config.enderDragonDrops.enableTrophyDrops().get(),
                    () -> ArmorPlus.config.enderDragonDrops.enableRegularDrops().get(),
                    () -> ArmorPlus.config.enderDragonDrops.enableSoulDrops().get(),
                    "ender_dragon_scale", 4, 6, "soul_ender_dragon", 0.1f),
            ElderGuardian.class, new MobDropConfig(() -> ArmorPlus.config.elderGuardianDrops.enableTrophyDrops().get(),
                    () -> ArmorPlus.config.elderGuardianDrops.enableRegularDrops().get(),
                    () -> ArmorPlus.config.elderGuardianDrops.enableSoulDrops().get(),
                    "guardian_scale", 4, 6, "soul_elder_guardian", 0.2f),
            WitherSkeleton.class, new MobDropConfig(() -> false,
                    () -> ArmorPlus.config.witherSkeletonDrops.enableRegularDrops().get(),
                    () -> ArmorPlus.config.witherSkeletonDrops.enableSoulDrops().get(),
                    "wither_bone", 0, 3, "soul_wither_skeleton", 0f),
            Guardian.class, new MobDropConfig(() -> false,
                    () -> ArmorPlus.config.guardianDrops.enableRegularDrops().get(),
                    () -> ArmorPlus.config.guardianDrops.enableSoulDrops().get(),
                    "guardian_scale", 0, 3, "soul_guardian", 0f),
            EnderMan.class, new MobDropConfig(() -> false, () -> false,
                    () -> ArmorPlus.config.endermanDrops.enableSoulDrops().get(),
                    null, 0, 0, "soul_enderman", 0f),
            Blaze.class, new MobDropConfig(() -> false, () -> false,
                    () -> ArmorPlus.config.blazeDrops.enableSoulDrops().get(),
                    null, 0, 0, "soul_blaze", 0f)
    );

    @SubscribeEvent
    public static void onMobDeathEvent(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide()) return;

        MobDropConfig cfg = DROP_CONFIGS.get(entity.getClass());
        if (cfg == null) {
            cfg = DROP_CONFIGS.entrySet().stream()
                    .filter(entry -> entry.getKey().isInstance(entity))
                    .min((left, right) -> left.getKey().isAssignableFrom(right.getKey()) ? 1
                            : right.getKey().isAssignableFrom(left.getKey()) ? -1 : 0)
                    .map(Map.Entry::getValue)
                    .orElse(null);
        }
        if (cfg == null) return;

        Entity source = event.getSource().getEntity();
        boolean hasSoulStealer = false;
        if (source instanceof ServerPlayer player) {
            hasSoulStealer = EnchantmentHelper.getItemEnchantmentLevel(
                    SOUL_STEALER.getOrThrow(player), player.getMainHandItem()) > 0;
        }

        // Regular material drops are governed only by their config toggle, not by Soul Stealer.
        if (cfg.regularEnabled().get() && cfg.regularItem() != null) {
            int amount = cfg.regularAmountMin() + RAND.nextInt(cfg.regularAmountMax() - cfg.regularAmountMin() + 1);
            if (amount > 0) dropItem(entity, cfg.regularItem(), amount);
        }

        // Souls and trophies intentionally remain gated behind Soul Stealer.
        if (!hasSoulStealer) return;
        if (cfg.trophyEnabled().get()) dropTrophyItem(entity, entity.getType(), cfg.trophyScale());
        if (cfg.soulEnabled().get() && cfg.soulItem() != null) {
            boolean drop = !(entity instanceof WitherSkeleton || entity instanceof Guardian
                    || entity instanceof EnderMan || entity instanceof Blaze) || RAND.nextInt(4) == 0;
            if (drop) dropItem(entity, cfg.soulItem(), 1);
        }
    }

    private static void dropTrophyItem(LivingEntity entity, EntityType<?> type, float scale) {
        ItemStack trophy = new ItemStack(getAPItem("trophy"));
        CompoundTag tag = new CompoundTag();
        SpawnData trophyEntity = new SpawnData();
        Identifier key = ForgeRegistries.ENTITY_TYPES.getKey(type);
        if (key == null) key = Identifier.parse("minecraft:pig");
        trophyEntity.getEntityToSpawn().putString("id", key.toString());
        tag.put("DisplayEntity", trophyEntity.getEntityToSpawn().copy());
        tag.putFloat("EntityScale", scale);
        trophy.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

        if (entity.level() instanceof ServerLevel server) {
            entity.spawnAtLocation(server, trophy);
        }
    }

    private static void dropItem(Entity entity, String item, int amount) {
        if (amount <= 0) return;
        if (entity.level() instanceof ServerLevel server) {
            ItemStack stack = new ItemStack(getAPItem(item), amount);
            if (!stack.isEmpty()) entity.spawnAtLocation(server, stack);
        }
    }
}
