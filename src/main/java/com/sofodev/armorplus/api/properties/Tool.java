/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.properties;

import com.sofodev.armorplus.api.properties.iface.IDurable;

/**
 * @author Sokratis Fotkatzikis
 */
public class Tool implements IDurable {

    private final double efficiency;
    private final int dur;

    /**
     * Creates an object with the basic data needed to create a tool
     *
     * @param efficiency the mining speed that the tool will have.
     * @param dur        the durability of the tool
     */
    public Tool(double efficiency, int dur) {
        this.efficiency = efficiency;
        this.dur = dur;
    }

    /**
     * @return The mining speed that the tool will have
     */
    public double getEfficiency() {
        return efficiency;
    }

    /**
     * @return The durability that the tool will have
     */
    @Override
    public int getDurability(boolean unbreakable) {
        return unbreakable ? -1 : dur;
    }
}
