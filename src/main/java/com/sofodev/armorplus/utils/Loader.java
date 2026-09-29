package com.sofodev.armorplus.utils;

import net.minecraftforge.fml.ModList;


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
        return ModList.getModContainerById(name().toLowerCase(java.util.Locale.ROOT)).isPresent();
    }
}