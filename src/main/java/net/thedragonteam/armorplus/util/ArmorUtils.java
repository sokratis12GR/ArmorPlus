package net.thedragonteam.armorplus.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import static net.minecraft.inventory.EntityEquipmentSlot.*;

public class ArmorUtils {

    public static ItemStack[] getArmorItems(final EntityPlayer entity) {
        return new ItemStack[]{entity.getItemStackFromSlot(HEAD), entity.getItemStackFromSlot(CHEST), entity.getItemStackFromSlot(LEGS), entity.getItemStackFromSlot(FEET)};
    }
}
