package com.sofodev.armorplus.registry.block.castle;

import com.sofodev.armorplus.config.AvailabilityConfig;
import net.minecraft.world.flag.FeatureFlagSet;
import com.sofodev.armorplus.registry.block.APBlock;

import static net.minecraft.world.level.block.Blocks.STONE;

public class StoneBrickBlock extends APBlock {

    public StoneBrickBlock(BrickColor color) {
        super(com.sofodev.armorplus.registry.RegistryContext.blockProperties(Properties.ofFullCopy(STONE)
                .mapColor(color.get()).dynamicShape().requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.blockEnabled(this);
    }
}