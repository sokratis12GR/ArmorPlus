package com.sofodev.armorplus.registry.blocks.special;

import com.sofodev.armorplus.registry.blocks.APBlock;
import net.minecraft.block.material.Material;

public class SoulBox extends APBlock {
    public SoulBox() {
        super(Properties.of(Material.METAL).strength(5f, 100f).lightLevel((i) -> 10));
    }
}
