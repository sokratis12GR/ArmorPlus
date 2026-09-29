package com.sofodev.armorplus.utils;

import net.minecraft.world.item.equipment.ArmorType;

import com.sofodev.armorplus.registry.item.armor.IAPArmor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import static com.sofodev.armorplus.utils.Utils.getAPItem;

public class ItemArmorUtility {

    public static boolean isExactMatch(IAPArmor mat, Player player, ArmorType slotType) {
        String itemName = String.format("%s_%s", mat.getName(), slotType.getName());
        Item equippedItem = player.getItemBySlot(slotType.getSlot()).getItem();
        boolean hasItemInSlot = player.hasItemInSlot(slotType.getSlot());
        boolean isSameItem = equippedItem == getAPItem(itemName);

        return hasItemInSlot && isSameItem;
    }

    public static boolean areExactMatch(IAPArmor mat, Player player) {
        boolean helmetMatch = isExactMatch(mat, player, ArmorType.HELMET);
        boolean chestplateMatch = isExactMatch(mat, player, ArmorType.CHESTPLATE);
        boolean leggingsMatch = isExactMatch(mat, player, ArmorType.LEGGINGS);
        boolean bootsMatch = isExactMatch(mat, player, ArmorType.BOOTS);

        return helmetMatch && chestplateMatch && leggingsMatch && bootsMatch;
    }
}
