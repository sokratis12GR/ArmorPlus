package com.sofodev.armorplus.registry.enchantments;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

import static net.minecraft.enchantment.EnchantmentType.ARMOR;
import static net.minecraft.item.ArmorMaterial.*;

import net.minecraft.enchantment.Enchantment.Rarity;

public class EnhancedEnchantment extends APEnchantment {

    public EnhancedEnchantment() {
        super(Rarity.VERY_RARE, ARMOR, EquipmentSlotType.values(),
                1, 1, 10, 40, false, true
        );
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.matchMaterial(stack);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return this.matchMaterial(stack);
    }

    private boolean matchMaterial(ItemStack stack) {
        if (stack.getItem() instanceof ArmorItem) {
            ArmorItem armor = (ArmorItem) stack.getItem();
            return armor.getArmorMaterial() == CHAIN || armor.getArmorMaterial() == GOLD
                    || armor.getArmorMaterial() == IRON || armor.getArmorMaterial() == DIAMOND;
        } else return false;
    }
}