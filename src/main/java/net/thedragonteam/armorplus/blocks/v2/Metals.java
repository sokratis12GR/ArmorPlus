/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.v2;

import net.minecraft.block.Block;
import net.minecraft.util.IStringSerializable;
import net.thedragonteam.armorplus.registry.ModBlocks;

import javax.annotation.Nonnull;

public enum Metals implements IStringSerializable {
    STEEL("steel", ModBlocks.steelBlock),
    ELECTRICAL("electrical", ModBlocks.electricalBlock);

    private final String name;

    private final Block beaconBase;

    Metals(String nameIn, Block beaconBaseIn) {
        this.name = nameIn;
        this.beaconBase = beaconBaseIn;
    }

    public String toString() {
        return this.name;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    public Block getBeaconBase() {
        return this.beaconBase;
    }
}
