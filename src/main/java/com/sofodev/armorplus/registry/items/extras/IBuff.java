package com.sofodev.armorplus.registry.items.extras;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.world.World;

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
     * @return {@link Effect} if {{@link #isEffect()}} is true and the value (enum name of the effect) is valid
     */
    Effect getEffect();

    /**
     * Copies the method from {@link ArmorItem#onArmorTick(ItemStack, World, PlayerEntity)}
     * <p>
     * Used to apply buff (effects) or abilities, on armor tick.
     */
    default void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
    }

    default void hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    }

    default boolean requiresFullSet() {
        return true;
    }

}