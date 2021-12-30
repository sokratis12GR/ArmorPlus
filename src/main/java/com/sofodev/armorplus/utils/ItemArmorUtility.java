package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.registry.items.armors.IAPArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.getNormalizedName;
import static net.minecraft.world.entity.EquipmentSlot.*;

public class ItemArmorUtility {

    public static boolean isExactMatch(IAPArmor mat, Player player, EquipmentSlot slotType) {
        return player.hasItemInSlot(slotType) && player.getItemBySlot(slotType).getItem() ==
                getAPItem(String.format("%s_%s", mat.getName(), getNormalizedName(slotType)));
    }

    public static boolean areExactMatch(IAPArmor mat, Player player) {
        return isExactMatch(mat, player, HEAD) && isExactMatch(mat, player, CHEST) && isExactMatch(mat, player, LEGS) && isExactMatch(mat, player, FEET);
    }
}