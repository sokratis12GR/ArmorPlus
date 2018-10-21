/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.benches;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.thedragonteam.armorplus.client.gui.GuiHandler;
import net.thedragonteam.armorplus.tileentity.TileCB;
import net.thedragonteam.armorplus.tileentity.TileHTB;
import net.thedragonteam.armorplus.tileentity.TileUTB;
import net.thedragonteam.armorplus.tileentity.TileWB;

import javax.annotation.Nonnull;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
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
