package com.sofodev.armorplus.utils;

import net.minecraftforge.fml.ModList;

import java.util.Locale;

public enum Loader {
    THEDRAGONLIB,
    TCONSTRUCT,
    DRACONICEVOLUTION,
    THEONEPROBE,
    BAUBLES,
    TESLA;

    Loader() {
    }

    public boolean isLoaded() {
        return ModList.get().isLoaded(this.name().toLowerCase(Locale.ENGLISH));
    }
}