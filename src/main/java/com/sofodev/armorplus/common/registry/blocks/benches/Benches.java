/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.blocks.benches;

import com.sofodev.armorplus.client.gui.GuiHandler;
import com.sofodev.armorplus.common.tileentity.TileCB;
import com.sofodev.armorplus.common.tileentity.TileHTB;
import com.sofodev.armorplus.common.tileentity.TileUTB;
import com.sofodev.armorplus.common.tileentity.TileWB;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

/**
 * @author Sokratis Fotkatzikis
 */
public enum Benches implements IStringSerializable {
    WORKBENCH(new TileWB(), GuiHandler.GUI_WORKBENCH),
    HIGH_TECH_BENCH(new TileHTB(), GuiHandler.GUI_HIGH_TECH_BENCH),
    ULTI_TECH_BENCH(new TileUTB(), GuiHandler.GUI_ULTI_TECH_BENCH),
    CHAMPION_BENCH(new TileCB(), GuiHandler.GUI_CHAMPION_BENCH),
    ;

    private final TileEntity tileEntity;
    private final int guiNumber;

    Benches(TileEntity tileEntityIn, int guiNumberIn) {
        this.tileEntity = tileEntityIn;
        this.guiNumber = guiNumberIn;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name().toLowerCase();
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    public int getGuiNumber() {
        return guiNumber;
    }

    public Benches getBench() {
        return this;
    }
}
