package com.sofodev.armorplus.registry.enchantment;

import com.mojang.serialization.MapCodec;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

import static com.sofodev.armorplus.registry.enchantment.FuriousEnchantmentEffect.Levels.limit;
import static net.minecraft.world.effect.MobEffects.DAMAGE_BOOST;
import static net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED;

public record FuriousEnchantmentEffect() implements EnchantmentEntityEffect {

    public static final MapCodec<FuriousEnchantmentEffect> CODEC = MapCodec.unit(FuriousEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (enchantmentLevel > limit()) {
            enchantmentLevel = limit();
        }
        Levels lvl = Levels.values()[enchantmentLevel];
        LivingEntity user = enchantedItemInUse.owner();
        if (user == null) {
            return;
        }
        user.addEffect(new MobEffectInstance(DAMAGE_BOOST, Utils.convertToSeconds(lvl.strSecs), lvl.strLevel, false, false));
        if (lvl.hasFastLegs) {
            user.addEffect(new MobEffectInstance(MOVEMENT_SPEED, Utils.convertToSeconds(lvl.speedSecs), lvl.speedLevel, false, false));
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }

    public enum Levels {
        ZERO(),
        ONE(23, 0),
        TWO(23, 0, 23, 0),
        THREE(23, 1, 46, 0),
        FOUR(30, 2, 60, 1);

        public final int strSecs;
        public final int strLevel;
        public final boolean hasFastLegs;
        public final int speedSecs;
        public final int speedLevel;

        Levels() {
            this(0, 0, false, 0, 0);
        }

        Levels(int strSecs, int strLevel) {
            this(strSecs, strLevel, false, 0, 0);

        }

        Levels(int strSecs, int strLevel, int speedSecs, int speedLevel) {
            this(strSecs, strLevel, true, speedSecs, speedLevel);
        }

        Levels(int strSecs, int strLevel, boolean hasFastLegs, int speedSecs, int speedLevel) {
            this.strSecs = strSecs;
            this.strLevel = strLevel;
            this.hasFastLegs = hasFastLegs;
            this.speedSecs = speedSecs;
            this.speedLevel = speedLevel;
        }

        public static int limit() {
            return values().length - 1;
        }
    }
}