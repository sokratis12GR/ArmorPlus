package com.sofodev.armorplus.registry.item;

import net.minecraft.world.item.Item;

public class APItem extends Item {

    public APItem() {
        super(com.sofodev.armorplus.registry.RegistryContext.itemProperties());
    }

    public APItem(Item.Properties props) {
        super(props);
    }

}