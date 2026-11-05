package com.sofodev.armorplus.registry.block;

import com.sofodev.armorplus.config.AvailabilityConfig;
import com.sofodev.armorplus.registry.RegistryContext;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class APBlock extends Block {

    public APBlock(Block material, MapColor color, float hardness, float resistance, int lightLevel) {
        super(RegistryContext.blockProperties(BlockBehaviour.Properties.ofFullCopy(material)
                .mapColor(color)
                .strength(hardness, resistance)
                .lightLevel((light) -> lightLevel)
                .requiresCorrectToolForDrops()));
    }

    public APBlock(Block material, float hardness, float resistance) {
        super(RegistryContext.blockProperties(BlockBehaviour.Properties.ofFullCopy(material).strength(hardness, resistance).requiresCorrectToolForDrops()));
    }

    public APBlock(Block material) {
        super(RegistryContext.blockProperties(BlockBehaviour.Properties.ofFullCopy(material).requiresCorrectToolForDrops()));
    }

    public APBlock(Properties props) {
        super(RegistryContext.blockProperties(props.requiresCorrectToolForDrops()));
    }

    @Override
    public boolean isEnabled(FeatureFlagSet ignoredFeatureFlags) {
        return AvailabilityConfig.blockEnabled(this);
    }
}