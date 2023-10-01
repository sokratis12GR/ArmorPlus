package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.registry.items.armors.IAPArmor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;

import static com.sofodev.armorplus.utils.Utils.getAPItem;

public class ItemArmorUtility {

    public static boolean isExactMatch(IAPArmor mat, Player player, ArmorItem.Type slotType) {
        return player.hasItemInSlot(slotType.getSlot()) && player.getItemBySlot(slotType.getSlot()).getItem() ==
                getAPItem(String.format("%s_%s", mat.getName(), slotType.getName()));
    }

    public static boolean areExactMatch(IAPArmor mat, Player player) {
        return isExactMatch(mat, player, ArmorItem.Type.HELMET)
                && isExactMatch(mat, player, ArmorItem.Type.CHESTPLATE)
                && isExactMatch(mat, player, ArmorItem.Type.LEGGINGS)
                && isExactMatch(mat, player, ArmorItem.Type.BOOTS);
    }
}