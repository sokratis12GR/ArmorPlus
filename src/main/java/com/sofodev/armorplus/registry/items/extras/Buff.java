package com.sofodev.armorplus.registry.items.extras;

import com.sofodev.armorplus.utils.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.potion.Effects.WITHER;

public enum Buff implements IBuff {
    NONE,
    /*Potion Effect Related*/
    NIGHT_VISION,
    WATER_BREATHING,
    STRENGTH,
    SPEED,
    HASTE,
    JUMP_BOOST,
    REGENERATION,
    RESISTANCE,
    FIRE_RESISTANCE,
    SATURATION,
    INVISIBILITY,
    HEALTH_BOOST,
    ABSORPTION,
    SLOW_FALLING,
    /*Mechanical*/
    FLIGHT(true),
    WITHER_IMMUNITY(true) {
        @Override
        public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
            if (!world.isRemote) {
                player.removeActivePotionEffect(WITHER);
            }
        }
    },
    WATER_WEAKNESS(true) {
        int ticks = 0;

        @Override
        public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
            if (!world.isRemote && player.isInWater()) {
                ticks++;
                if ((ticks + 1) % 20 == 0) {
                    this.damageArmorSet(player, stack);
                }
            }
        }

        private void damageArmorSet(PlayerEntity playerEntity, ItemStack stack) {
            stack.damageItem(1, playerEntity, e -> e.sendBreakAnimation(EquipmentSlotType.HEAD));
            stack.damageItem(1, playerEntity, e -> e.sendBreakAnimation(EquipmentSlotType.CHEST));
            stack.damageItem(1, playerEntity, e -> e.sendBreakAnimation(EquipmentSlotType.LEGS));
            stack.damageItem(1, playerEntity, e -> e.sendBreakAnimation(EquipmentSlotType.FEET));
        }
    };

    private final boolean isEffect;
    private final Effect effect;
    private final boolean requireFullSet;

    Buff(boolean isEffect, boolean requireFullSet) {
        this.isEffect = isEffect;
        this.effect = ForgeRegistries.POTIONS.getValue(Utils.setVanillaLocation(this.name().toLowerCase()));
        this.requireFullSet = requireFullSet;
    }

    Buff(boolean requireFullSet) {
        this(true, requireFullSet);
    }

    Buff() {
        this(true, true);
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
    public boolean requiresFullSet() {
        return requireFullSet;
    }
}