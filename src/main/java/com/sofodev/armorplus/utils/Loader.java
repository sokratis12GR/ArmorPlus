package com.sofodev.armorplus.utils;

import net.neoforged.fml.ModList;


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
        return ModList.get().getModContainerById(name().toLowerCase(java.util.Locale.ROOT)).isPresent();
    }
}