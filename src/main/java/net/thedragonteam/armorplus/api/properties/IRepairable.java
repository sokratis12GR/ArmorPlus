package net.thedragonteam.armorplus.api.properties;

import net.minecraft.item.ItemStack;

public interface IRepairable {

    default ItemStack getRepairStack() {
        return ItemStack.EMPTY;
    }

}
