/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.base;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvanced extends ItemBase {

    private final TextFormatting formatting;
    private final String displayName;

    public ItemAdvanced(String itemName, TextFormatting formatting, String displayName) {
        super(itemName);
        this.formatting = formatting;
        this.displayName = displayName;
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return this.getRarity(formatting, displayName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        super.initModel("material", 0);
    }
}
