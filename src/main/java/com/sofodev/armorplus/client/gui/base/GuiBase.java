/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

/**
 * @author Sokratis Fotkatzikis
 */
public abstract class GuiBase extends GuiContainer {

    public GuiBase(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }
}
