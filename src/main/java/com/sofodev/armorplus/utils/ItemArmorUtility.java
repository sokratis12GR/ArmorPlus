package com.sofodev.armorplus.utils;

import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

import static com.sofodev.armorplus.utils.Utils.getAPItemByName;
import static com.sofodev.armorplus.utils.Utils.getNormalizedName;

public class ItemArmorUtility {

    public static boolean isExactMatch(APArmorMaterial mat, PlayerEntity player, EquipmentSlotType slotType) {
        return player.hasItemInSlot(slotType) && player.getItemStackFromSlot(slotType).getItem() == getAPItemByName(String.format("%s_%s", mat.getName(), getNormalizedName(slotType)));
    }
}
