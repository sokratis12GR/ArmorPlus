package com.sofodev.armorplus.registry.item;

import com.sofodev.armorplus.config.AvailabilityConfig;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;

public class APItem extends Item {
    public APItem() {
        super(new Item.Properties());
    }


    public APItem(Item.Properties props) {
        super(props);
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }

}