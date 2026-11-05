package com.sofodev.armorplus.registry.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;


public record LifeStealEnchantmentEffect() implements EnchantmentEntityEffect {

    public static final MapCodec<LifeStealEnchantmentEffect> CODEC = MapCodec.unit(LifeStealEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (enchantmentLevel > Levels.limit()) {
            enchantmentLevel = Levels.limit();
        }
        Levels lvl = Levels.values()[enchantmentLevel];
        if (enchantedItemInUse.owner() == null) {
            return;
        }
        LivingEntity user = enchantedItemInUse.owner();
        ItemStack mainHand = enchantedItemInUse.owner().getMainHandItem();
        if (mainHand.isEmpty()) return;
        user.heal(lvl.healingFactor);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }

    public enum Levels {
        ZERO(0.0f),
        ONE(0.5f),
        TWO(1.5f),
        THREE(2.5f);

        public final float healingFactor;

        Levels(float healingFactor) {
            this.healingFactor = healingFactor;
        }

        public static int limit() {
            return values().length - 1;
        }
    }
}