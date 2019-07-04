package com.sofodev.armorplus.api.caps.ailments;

import net.minecraftforge.registries.IForgeRegistry;

public class ImplementedAilments {

    public static IForgeRegistry<Ailment> AILMENT_REGISTRY;
    public static final Ailment NONE = new Ailment("armorplus:empty", "ailment.armorplus.empty.name");

}
