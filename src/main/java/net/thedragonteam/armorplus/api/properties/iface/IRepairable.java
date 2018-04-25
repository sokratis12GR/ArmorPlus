package net.thedragonteam.armorplus.api.properties.iface;

import net.minecraft.item.ItemStack;

public interface IRepairable {

    default ItemStack getRepairStack() {
        return ItemStack.EMPTY;
    }

}
