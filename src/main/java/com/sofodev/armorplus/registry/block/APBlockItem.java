package com.sofodev.armorplus.registry.block;

import com.sofodev.armorplus.config.AvailabilityConfig;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class APBlockItem extends BlockItem {

    public APBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    public APBlockItem(Block blockIn) {
        super(blockIn, com.sofodev.armorplus.registry.RegistryContext.itemProperties().useBlockDescriptionPrefix());
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.itemEnabled(this);
    }
}