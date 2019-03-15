/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.benches;

import com.sofodev.armorplus.client.GuiHandler;
import com.sofodev.armorplus.tileentity.TileCB;
import com.sofodev.armorplus.tileentity.TileHTB;
import com.sofodev.armorplus.tileentity.TileUTB;
import com.sofodev.armorplus.tileentity.TileWB;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * @author Sokratis Fotkatzikis
 */
public enum Benches implements IStringSerializable {
    WORKBENCH(new TileWB(), GuiHandler.GUI_WORKBENCH, 1),
    HIGH_TECH_BENCH(new TileHTB(), GuiHandler.GUI_HIGH_TECH_BENCH, 2),
    ULTI_TECH_BENCH(new TileUTB(), GuiHandler.GUI_ULTI_TECH_BENCH, 3),
    CHAMPION_BENCH(new TileCB(), GuiHandler.GUI_CHAMPION_BENCH, 4),
    ;

    private final TileEntity tileEntity;
    private final ResourceLocation location;
    private final int id;

    Benches(TileEntity tileEntityIn, ResourceLocation guiNumberIn, int id) {
        this.tileEntity = tileEntityIn;
        this.location = guiNumberIn;
        this.id = id;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name().toLowerCase();
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    public ResourceLocation getGuiLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public Benches getBench() {
        return this;
    }
}
