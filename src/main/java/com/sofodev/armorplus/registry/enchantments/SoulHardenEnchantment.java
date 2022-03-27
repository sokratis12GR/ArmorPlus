package com.sofodev.armorplus.registry.enchantments;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.config.ArmorPlusConfig.enchantsThatWontWorkWithSoulHarden;
import static com.sofodev.armorplus.registry.enchantments.LifeStealEnchantment.Levels.limit;
import static net.minecraft.world.entity.EquipmentSlot.*;
import static net.minecraft.world.item.enchantment.EnchantmentCategory.*;

public class SoulHardenEnchantment extends APEnchantment {

    public SoulHardenEnchantment() {
        super(Rarity.VERY_RARE, WEARABLE, new EquipmentSlot[]{HEAD, CHEST, LEGS, FEET},
                1, 1, 30, 60, true, true
        );
    }

    public static boolean areAllCompatible(List<Boolean> array) {
        for (boolean b : array) if (!b) return false;
        return true;
    }

    @Override
    protected boolean checkCompatibility(Enchantment ench) {
        List<Boolean> compatibilityList = enchantsThatWontWorkWithSoulHarden.get()
                .stream()
                .filter(enchant -> Objects.requireNonNull(ench.getRegistryName()).toString().equals(enchant))
                .map(enchant -> false)
                .collect(Collectors.toList());
        return areAllCompatible(compatibilityList) && super.checkCompatibility(ench);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return ARMOR.canEnchant(stack.getItem());
    }

    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}