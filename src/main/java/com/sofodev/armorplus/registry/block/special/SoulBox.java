package com.sofodev.armorplus.registry.block.special;

import com.sofodev.armorplus.config.AvailabilityConfig;
import net.minecraft.world.flag.FeatureFlagSet;
import com.sofodev.armorplus.registry.block.APBlock;

import static net.minecraft.world.level.block.Blocks.IRON_BLOCK;

public class SoulBox extends APBlock {
    public SoulBox() {
        super(com.sofodev.armorplus.registry.RegistryContext.blockProperties(Properties.ofFullCopy(IRON_BLOCK).strength(5f, 100f).requiresCorrectToolForDrops().lightLevel((i) -> 10)));
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.blockEnabled(this);
    }
}
