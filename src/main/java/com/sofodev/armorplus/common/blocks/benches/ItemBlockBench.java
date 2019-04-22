/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.blocks.benches;

import com.sofodev.armorplus.common.iface.IRarityHelper;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;

import java.util.Objects;

public class ItemBlockBench extends ItemBlock implements IRarityHelper {

    private Benches benches;

    public ItemBlockBench(BlockBench block) {
        super(block);
        this.benches = block.benches;
        this.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        switch (benches) {
            case WORKBENCH:
                return this.getRarity(TextFormatting.BLUE, "Workbench");
            case HIGH_TECH_BENCH:
                return this.getRarity(TextFormatting.DARK_RED, "High-Tech Bench");
            case ULTI_TECH_BENCH:
                return this.getRarity(TextFormatting.GREEN, "Ulti-Tech Bench");
            case CHAMPION_BENCH:
                return this.getRarity(TextFormatting.GOLD, "Champion Bench");
        }
        return EnumRarity.COMMON;
    }
}
