package com.sofodev.armorplus.registry.block;

import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.RegistryContext;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.level.block.Blocks.STONE;
import static net.minecraft.world.level.material.MapColor.COLOR_RED;

public class APLavaBlock extends Block {

    public APLavaBlock() {
        super(RegistryContext.blockProperties(BlockBehaviour.Properties.ofFullCopy(STONE).mapColor(COLOR_RED).strength(5.0f, 1000.0f).lightLevel((light) -> 8).dynamicShape().requiresCorrectToolForDrops()));
    }

    public APLavaBlock(Properties props) {
        super(RegistryContext.blockProperties(props.lightLevel((light) -> 8)));
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.blockEnabled(this);
    }

}