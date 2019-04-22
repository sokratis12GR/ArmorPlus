/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.blocks.metals;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

/**
 * @author Sokratis Fotkatzikis
 */
public enum Metals implements IStringSerializable {
    STEEL,
    ELECTRICAL;

    @Override
    @Nonnull
    public String getName() {
        return this.name().toLowerCase();
    }

}
