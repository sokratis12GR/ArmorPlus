/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.baubles;

import baubles.api.IBauble;
import net.thedragonteam.armorplus.items.base.BaseItem;

public abstract class ItemBauble extends BaseItem implements IBauble {

    public ItemBauble(String name) {
        super(name);
        setMaxStackSize(1);
    }
}