package com.sofodev.armorplus.registry.items.extras;

import com.sofodev.armorplus.utils.Utils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public enum DeBuff implements IBuff {
    NONE,
    /*Potion Effect Related*/
    WITHER,
    SLOWNESS,
    BLINDNESS,
    NAUSEA,
    POISON,
    WEAKNESS,
    MINING_FATIGUE,
    HUNGER,
    GLOWING,
    LEVITATION,
    /*Mechanical*/
    IGNITE(false) {
        @Override
        public void hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            if (!attacker.world.isRemote && target != null) {
                target.setFire(rand.nextInt(6) + 1);
            }
        }
    };

    public Random rand = new Random();
    private final boolean isEffect;
    private final Effect effect;

    DeBuff(boolean isEffect) {
        this.isEffect = isEffect;
        this.effect = ForgeRegistries.POTIONS.getValue(Utils.setVanillaLocation(this.name().toLowerCase()));
    }

    DeBuff() {
        this(true);
    }

    @Override
    public boolean isEffect() {
        return isEffect;
    }

    @Override
    public Effect getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return "DeBuff{" +
                "rand=" + rand +
                ", isEffect=" + isEffect +
                ", effect=" + effect +
                '}';
    }
}