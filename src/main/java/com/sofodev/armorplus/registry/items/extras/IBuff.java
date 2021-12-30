package com.sofodev.armorplus.registry.items.extras;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * IBuff is the sharing point for buffs and de-buffs
 */
public interface IBuff {

    String name();

    /**
     * @return true if the effect above is a potion effect
     */
    boolean isEffect();

    /**
     * @return {@link MobEffect} if {{@link #isEffect()}} is true and the value (enum name of the effect) is valid
     */
    MobEffect getEffect();

    /**
     * Copies the method from {@link ArmorItem#onArmorTick(ItemStack, Level, Player)}
     * <p>
     * Used to apply buff (effects) or abilities, on armor tick.
     */
    default void onArmorTick(ItemStack stack, Level world, Player player) {
    }

    default void hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    }

    default boolean requiresFullSet() {
        return true;
    }

}