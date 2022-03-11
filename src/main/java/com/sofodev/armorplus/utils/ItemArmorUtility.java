package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.registry.items.armors.IAPArmor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.getNormalizedName;
import static net.minecraft.inventory.EquipmentSlotType.*;

public class ItemArmorUtility {

    public static boolean isExactMatch(IAPArmor mat, PlayerEntity player, EquipmentSlotType slotType) {
        return player.hasItemInSlot(slotType) && player.getItemBySlot(slotType).getItem() ==
                getAPItem(String.format("%s_%s", mat.getName(), getNormalizedName(slotType)));
    }

    public static boolean areExactMatch(IAPArmor mat, PlayerEntity player) {
        return isExactMatch(mat, player, HEAD) && isExactMatch(mat, player, CHEST) && isExactMatch(mat, player, LEGS) && isExactMatch(mat, player, FEET);
    }
}