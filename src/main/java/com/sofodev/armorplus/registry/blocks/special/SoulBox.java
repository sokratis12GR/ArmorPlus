package com.sofodev.armorplus.registry.blocks.special;

import com.sofodev.armorplus.registry.blocks.APBlock;
import net.minecraft.world.level.material.Material;

public class SoulBox extends APBlock {
    public SoulBox() {
        super(Properties.of(Material.METAL).strength(5f, 100f).requiresCorrectToolForDrops().lightLevel((i) -> 10));
    }
}
