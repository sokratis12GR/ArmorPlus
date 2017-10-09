/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.blocks.v2;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public enum Metals implements IStringSerializable {
    STEEL("steel"),
    ELECTRICAL("electrical");

    private final String name;

    Metals(String nameIn) {
        this.name = nameIn;
    }

    public String toString() {
        return this.name;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

}
