package com.sofodev.armorplus.registry.items.tools;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.properties.IAPTool;
import net.minecraft.item.BowItem;

import net.minecraft.item.Item.Properties;

public class APBowItem extends BowItem {

    public APBowItem(IAPTool tool) {
        super(new Properties().maxDamage((int) (tool.get().getMaxUses() * 0.5))
                .group(ArmorPlus.AP_WEAPON_GROUP)
                .rarity(tool.getRarity())
        );
    }


}
