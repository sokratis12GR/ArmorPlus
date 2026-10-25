package com.sofodev.armorplus.registry.block.castle;

import com.sofodev.armorplus.config.AvailabilityConfig;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;

public class CastleBlock extends Block {

    public CastleBlock(Block block) {
        super(com.sofodev.armorplus.registry.RegistryContext.blockProperties(ofFullCopy(block).requiresCorrectToolForDrops()));
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.blockEnabled(this);
    }
}