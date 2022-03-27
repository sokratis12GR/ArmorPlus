package com.sofodev.armorplus.registry.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sofodev.armorplus.config.ArmorPlusConfig.enchantsThatWontWorkWithSoulHarden;
import static net.minecraft.enchantment.EnchantmentType.ARMOR;
import static net.minecraft.enchantment.EnchantmentType.WEARABLE;
import static net.minecraft.inventory.EquipmentSlotType.*;

public class SoulHardenEnchantment extends APEnchantment {

    public SoulHardenEnchantment() {
        super(Rarity.VERY_RARE, WEARABLE, new EquipmentSlotType[]{HEAD, CHEST, LEGS, FEET},
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