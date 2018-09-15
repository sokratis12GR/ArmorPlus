package net.thedragonteam.armorplus.api.properties.iface;

import net.minecraft.item.ItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public interface IRepairable {

    /**
     * @return the represented ItemStack that will be used for repairing the item
     */
    default ItemStack getRepairStack() {
        return ItemStack.EMPTY;
    }
}
