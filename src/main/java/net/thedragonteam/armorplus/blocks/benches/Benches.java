/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.blocks.benches;

import net.minecraft.util.IStringSerializable;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench;
import net.thedragonteam.armorplus.tileentity.base.TileEntityBaseBench;

public enum Benches implements IStringSerializable {
    WORKBENCH("workbench", new TileEntityWorkbench()),
    HIGH_TECH("high_tech_bench", new TileEntityHighTechBench()),
    ULIT_TECH("ulti_tech_bench", new TileEntityUltiTechBench());

    private final String name;

    private final TileEntityBaseBench entityBench;

    Benches(String nameIn, TileEntityBaseBench tileEntityBaseBench) {
        this.name = nameIn;
        this.entityBench = tileEntityBaseBench;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public TileEntityBaseBench getEntityBench() {
        return entityBench;
    }
}
