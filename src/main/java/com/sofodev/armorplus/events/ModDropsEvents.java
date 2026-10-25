package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.SpawnData;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.Supplier;

import static com.sofodev.armorplus.registry.ModEnchantments.SOUL_STEALER;
import static com.sofodev.armorplus.utils.Utils.getAPItem;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModDropsEvents {


    @SubscribeEvent
    public static void onMobDeathEvent(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        Entity trueSource = event.getSource().getEntity();
        boolean isSourcePlayer = trueSource instanceof ServerPlayer;
        ServerPlayer player;
        ItemStack heldItem;
        boolean hasSoulStealer = false;
        if (entity.level().isClientSide()) return;
        if (isSourcePlayer) {
            player = (ServerPlayer) trueSource;
            heldItem = player.getMainHandItem();
            int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(SOUL_STEALER.getOrThrow(player), heldItem);
            if (enchantmentLevel > 0) {
                hasSoulStealer = true;
            }
        }

        record MobDropConfig(Supplier<Boolean> trophyEnabled, Supplier<Boolean> regularEnabled,
                             Supplier<Boolean> soulEnabled, String regularItem, int regularAmountMin,
                             int regularAmountMax, String soulItem, float trophyScale) {
        }

        // Map the mob class to the configuration
        Map<Class<? extends LivingEntity>, MobDropConfig> dropMap = Map.of(
                WitherBoss.class, new MobDropConfig(() -> ArmorPlusConfig.witherBossDrops.enableTrophyDrops().get(),
                        () -> ArmorPlusConfig.witherBossDrops.enableRegularDrops().get(),
                        () -> ArmorPlusConfig.witherBossDrops.enableSoulDrops().get(),
                        "wither_bone", 4, 6, "soul_wither_boss", 0.2f),
                EnderDragon.class, new MobDropConfig(() -> ArmorPlusConfig.enderDragonDrops.enableTrophyDrops().get(),
                        () -> ArmorPlusConfig.enderDragonDrops.enableRegularDrops().get(),
                        () -> ArmorPlusConfig.enderDragonDrops.enableSoulDrops().get(),
                        "ender_dragon_scale", 4, 6, "soul_ender_dragon", 0.1f),
                ElderGuardian.class, new MobDropConfig(() -> ArmorPlusConfig.elderGuardianDrops.enableTrophyDrops().get(),
                        () -> ArmorPlusConfig.elderGuardianDrops.enableRegularDrops().get(),
                        () -> ArmorPlusConfig.elderGuardianDrops.enableSoulDrops().get(),
                        "guardian_scale", 4, 6, "soul_elder_guardian", 0.2f),
                WitherSkeleton.class, new MobDropConfig(() -> false,
                        () -> ArmorPlusConfig.witherSkeletonDrops.enableRegularDrops().get(),
                        () -> ArmorPlusConfig.witherSkeletonDrops.enableSoulDrops().get(),
                        "wither_bone", 0, 3, "soul_wither_skeleton", 0f),
                Guardian.class, new MobDropConfig(() -> false,
                        () -> ArmorPlusConfig.guardianDrops.enableRegularDrops().get(),
                        () -> ArmorPlusConfig.guardianDrops.enableSoulDrops().get(),
                        "guardian_scale", 0, 3, "soul_guardian", 0f),
                EnderMan.class, new MobDropConfig(() -> false,
                        () -> false,
                        () -> ArmorPlusConfig.endermanDrops.enableSoulDrops().get(),
                        null, 0, 0, "soul_enderman", 0f),
                Blaze.class, new MobDropConfig(() -> false,
                        () -> false,
                        () -> ArmorPlusConfig.blazeDrops.enableSoulDrops().get(),
                        null, 0, 0, "soul_blaze", 0f)
        );


        MobDropConfig cfg = dropMap.entrySet().stream()
                .filter(entry -> entry.getKey().isInstance(entity))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);

        if (cfg == null) return;

        // Trophy
        if (hasSoulStealer && cfg.trophyEnabled.get()) dropTrophyItem(event, entity, entity.getType(), cfg.trophyScale);

        // Regular drops
        if (cfg.regularEnabled.get() && cfg.regularItem != null) {
            int amount = entity.getRandom().nextInt(cfg.regularAmountMax - cfg.regularAmountMin + 1) + cfg.regularAmountMin;
            dropItem(event, entity, cfg.regularItem, amount);
        }

        // Soul drops
        if (hasSoulStealer && cfg.soulEnabled.get() && cfg.soulItem != null) {
            boolean drop = !(entity instanceof WitherSkeleton || entity instanceof Guardian
                    || entity instanceof EnderMan || entity instanceof Blaze) || entity.getRandom().nextInt(4) == 0;
            if (drop) dropItem(event, entity, cfg.soulItem, 1);
        }
    }


    private static void dropTrophyItem(LivingDropsEvent event, LivingEntity entity, EntityType<?> type, float scale) {
        ItemStack trophy = new ItemStack(getAPItem("trophy"));
        CompoundTag tag = new CompoundTag();
        SpawnData trophyEntity = new SpawnData();
        ResourceLocation key = ForgeRegistries.ENTITY_TYPES.getKey(type);
        if (key == null) key = ResourceLocation.parse("minecraft:pig");
        trophyEntity.getEntityToSpawn().putString("id", key.toString());
        tag.put("DisplayEntity", trophyEntity.getEntityToSpawn().copy());
        tag.putFloat("EntityScale", scale);
trophy.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        addDrop(event, entity, trophy);
    }

    private static void dropItem(LivingDropsEvent event, LivingEntity entity, String item, int amount) {
        if (amount > 0) addDrop(event, entity, new ItemStack(getAPItem(item), amount));
    }

    private static void addDrop(LivingDropsEvent event, LivingEntity entity, ItemStack stack) {
        if (!stack.isEmpty()) event.getDrops().add(new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack));
    }
}
