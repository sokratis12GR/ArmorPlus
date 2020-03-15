package com.sofodev.armorplus.registry.items.tools.properties;

import com.sofodev.armorplus.registry.items.extras.BuffInstance;

import static com.sofodev.armorplus.registry.items.extras.DeBuff.*;
import static com.sofodev.armorplus.registry.items.tools.properties.APToolProperties.*;

public enum APToolMaterial {
    COAL_MAT(COAL_PROP, new BuffInstance(BLINDNESS, 0, 10)),
    REDSTONE_MAT(REDSTONE_PROP, new BuffInstance(MINING_FATIGUE, 1, 10)),
    LAPIS_MAT(LAPIS_PROP, new BuffInstance(NAUSEA, 0, 10)),
    EMERALD_MAT(EMERALD_PROP, new BuffInstance(SLOWNESS, 0, 20)),
    OBSIDIAN_MAT(OBSIDIAN_PROP, new BuffInstance(WEAKNESS, 2, 20)),
    INFUSED_LAVA_MAT(INFUSED_LAVA_PROP, new BuffInstance(IGNITE)),
    GUARDIAN_MAT(GUARDIAN_PROP, new BuffInstance(WEAKNESS, 1, 4), new BuffInstance(NAUSEA, 1, 20)),
    SUPER_STAR_MAT(SUPER_STAR_PROP, new BuffInstance(WITHER, 1, 4), new BuffInstance(GLOWING, 0, 20)),
    ENDER_DRAGON_MAT(ENDER_DRAGON_PROP, new BuffInstance(WITHER, 3, 4), new BuffInstance(SLOWNESS, 1, 20)),
    SLAYER_MAT(SLAYER_PROP);

    private final String name;
    private final APToolProperties properties;
    private final BuffInstance[] buffs;

    APToolMaterial(APToolProperties properties, BuffInstance... buffs) {
        this.name = this.name().toLowerCase().replace("_mat", "");
        this.properties = properties;
        this.buffs = buffs;
    }

    public APToolProperties get() {
        return properties;
    }

    public BuffInstance[] getBuffInstances() {
        return buffs;
    }

    public String getName() {
        return name;
    }

}
