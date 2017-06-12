/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.benches;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.thedragonteam.armorplus.client.gui.GuiHandler;
import net.thedragonteam.armorplus.tileentity.TileEntityChampionBench;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench;

import javax.annotation.Nonnull;

public enum Benches implements IStringSerializable {
    WORKBENCH("workbench", new TileEntityWorkbench(), GuiHandler.Companion.getGUI_WORKBENCH()),
    HIGH_TECH("high_tech_bench", new TileEntityHighTechBench(), GuiHandler.Companion.getGUI_HIGH_TECH_BENCH()),
    ULTI_TECH("ulti_tech_bench", new TileEntityUltiTechBench(), GuiHandler.Companion.getGUI_ULTI_TECH_BENCH()),
    CHAMPION("champion_bench", new TileEntityChampionBench(), GuiHandler.Companion.getGUI_CHAMPION_BENCH()),;

    private final String name;
    private final TileEntity tileEntity;
    private final int guiNumber;

    Benches(String nameIn, TileEntity tileEntityIn, int guiNumberIn) {
        this.name = nameIn;
        this.tileEntity = tileEntityIn;
        this.guiNumber = guiNumberIn;
    }

    public String toString() {
        return this.name;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
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
