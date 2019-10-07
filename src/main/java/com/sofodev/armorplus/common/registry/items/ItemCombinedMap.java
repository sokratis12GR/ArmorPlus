package com.sofodev.armorplus.common.registry.items;

import com.sofodev.armorplus.common.registry.items.base.ItemBase;
import net.minecraft.item.ItemStack;

public class ItemCombinedMap extends ItemBase {

    private final Variants variant;

    public ItemCombinedMap(Variants variant) {
        super(variant.getName());
        this.variant = variant;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    public enum Variants {
        MAP_OF_OVERWORLD,
        MAP_OF_THE_NETHER,
        MAP_OF_THE_END,
        MAP_OF_SACRIFICE,
        MAP_OF_THE_REALM,
        MAP_OF_ARENA,
        MAP_OF_THE_SHAPER,
        MAP_OF_THE_ELDER;

        public String getName() {
            return name().toLowerCase();
        }
    }
}
