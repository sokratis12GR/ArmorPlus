/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.jei.misc;

/**
 * @author Sokratis Fotkatzikis
 */
public class UVData {

    private final int u;
    private final int v;
    private final int widthU;
    private final int heightV;

    public UVData(int u, int v, int widthU, int heightV) {
        this.u = u;
        this.v = v;
        this.widthU = widthU;
        this.heightV = heightV;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public int getWidthU() {
        return widthU;
    }

    public int getHeightV() {
        return heightV;
    }
}
