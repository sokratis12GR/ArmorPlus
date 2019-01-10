/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.blocks.benches;

import com.sofodev.armorplus.util.EnumHelperUtil;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.Objects;

public class ItemBlockBench extends ItemBlock {

    private Benches benches;

    public ItemBlockBench(BlockBench block) {
        super(block);
        this.benches = block.benches;
        this.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        switch (benches) {
            case WORKBENCH:
                return EnumHelperUtil.addRarity("WORKBENCH", TextFormatting.BLUE, "Workbench");
            case HIGH_TECH_BENCH:
                return EnumHelperUtil.addRarity("HIGH_TECH_BENCH", TextFormatting.DARK_RED, "High-Tech Bench");
            case ULTI_TECH_BENCH:
                return EnumHelperUtil.addRarity("ULTI_TECH_BENCH", TextFormatting.GREEN, "Ulti-Tech Bench");
            case CHAMPION_BENCH:
                return EnumHelperUtil.addRarity("CHAMPION_BENCH", TextFormatting.GOLD, "Champion Bench");
        }
        return EnumRarity.COMMON;
    }
}
