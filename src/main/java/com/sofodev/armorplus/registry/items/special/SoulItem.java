package com.sofodev.armorplus.registry.items.special;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

import static com.sofodev.armorplus.ArmorPlus.AP_ITEM_GROUP;

public class SoulItem extends Item {

    private final boolean isBoss;

    public SoulItem(Properties properties) {
        super(properties.group(AP_ITEM_GROUP).rarity(Rarity.EPIC).isImmuneToFire().maxStackSize(16));
        this.isBoss = true;
    }

    public SoulItem(boolean isBoss, Properties properties) {
        super(properties.group(AP_ITEM_GROUP).rarity(Rarity.RARE).isImmuneToFire().maxStackSize(32));
        this.isBoss = isBoss;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return isBoss;
    }
}
