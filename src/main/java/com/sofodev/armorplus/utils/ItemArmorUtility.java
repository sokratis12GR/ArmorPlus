package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.registry.items.armors.IAPArmor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import static com.sofodev.armorplus.ArmorPlus.LOGGER;
import static com.sofodev.armorplus.utils.Utils.getAPItem;

public class ItemArmorUtility {

    public static boolean isExactMatch(IAPArmor mat, Player player, ArmorItem.Type slotType) {
        String itemName = String.format("%s_%s", mat.getName(), slotType.getName());
        Item equippedItem = player.getItemBySlot(slotType.getSlot()).getItem();
        boolean hasItemInSlot = player.hasItemInSlot(slotType.getSlot());
        boolean isSameItem = equippedItem == getAPItem(itemName);

        return hasItemInSlot && isSameItem;
    }

    public static boolean areExactMatch(IAPArmor mat, Player player) {
        boolean helmetMatch = isExactMatch(mat, player, ArmorItem.Type.HELMET);
        boolean chestplateMatch = isExactMatch(mat, player, ArmorItem.Type.CHESTPLATE);
        boolean leggingsMatch = isExactMatch(mat, player, ArmorItem.Type.LEGGINGS);
        boolean bootsMatch = isExactMatch(mat, player, ArmorItem.Type.BOOTS);

        return helmetMatch && chestplateMatch && leggingsMatch && bootsMatch;
    }
}