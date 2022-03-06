package com.sofodev.armorplus.registry.enchantments;

import com.sofodev.armorplus.utils.Utils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import static com.sofodev.armorplus.registry.enchantments.FuriousEnchantment.Levels.limit;
import static net.minecraft.world.effect.MobEffects.DAMAGE_BOOST;
import static net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED;
import static net.minecraft.world.entity.EquipmentSlot.*;
import static net.minecraft.world.item.enchantment.Enchantment.Rarity.RARE;
import static net.minecraft.world.item.enchantment.EnchantmentCategory.WEARABLE;

public class FuriousEnchantment extends APEnchantment {

    public FuriousEnchantment() {
        super(RARE, WEARABLE, new EquipmentSlot[]{HEAD, CHEST, LEGS, FEET},
                1, 3, 10, 15, true, true
        );
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (level > limit()) {
            level = limit();
        }
        super.doPostAttack(user, target, level);
    }

    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int level) {
        if (level > limit()) {
            level = limit();
        }
        Levels lvl = Levels.values()[level];
        user.addEffect(new MobEffectInstance(DAMAGE_BOOST, Utils.convertToSeconds(lvl.strSecs), lvl.strLevel, false, false));
        if (lvl.hasFastLegs) {
            user.addEffect(new MobEffectInstance(MOVEMENT_SPEED, Utils.convertToSeconds(lvl.speedSecs), lvl.speedLevel, false, false));
        }
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