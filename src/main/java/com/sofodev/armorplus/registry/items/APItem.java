package com.sofodev.armorplus.registry.items;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.world.item.Item;

public class APItem extends Item {

    public APItem() {
        super(new Item.Properties().tab(ArmorPlus.AP_ITEM_GROUP));
    }

    public APItem(Item.Properties props) {
        super(props.tab(ArmorPlus.AP_ITEM_GROUP));
    }

}