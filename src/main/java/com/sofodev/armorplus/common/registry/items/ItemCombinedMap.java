package com.sofodev.armorplus.common.registry.items;

import com.sofodev.armorplus.common.registry.ModDimensions;
import com.sofodev.armorplus.common.registry.items.base.ItemBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;

public class ItemCombinedMap extends ItemBase {

    public final Variants variant;

    public ItemCombinedMap(Variants variant) {
        super(variant.getName());
        this.variant = variant;
    }

    public DimensionType getDimension() {
        return variant.getType();
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    public enum Variants {
        MAP(null),
        MAP_OF_OVERWORLD(DimensionType.OVERWORLD),
        MAP_OF_THE_NETHER(DimensionType.NETHER),
        MAP_OF_THE_END(DimensionType.THE_END),
        MAP_OF_SACRIFICE(null),
        MAP_OF_THE_REALM(ModDimensions.REALM),
        MAP_OF_ARENA(ModDimensions.ARENA),
        MAP_OF_THE_SHAPER(null),
        MAP_OF_THE_ELDER(null);

        private final DimensionType type;

        Variants(DimensionType type) {
            this.type = type;
        }

        public DimensionType getType() {
            return type;
        }

        public String getName() {
            return name().toLowerCase();
        }
    }
}
