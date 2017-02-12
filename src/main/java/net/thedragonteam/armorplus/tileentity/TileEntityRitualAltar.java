/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.tileentity.base.TileEntityBase;
import net.thedragonteam.thedragonlib.util.ItemStackUtils;

import java.util.Arrays;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class TileEntityRitualAltar extends TileEntityBase implements ITickable {

    public boolean isValid = false;
    public boolean isActivated = false;
    public boolean isRecipeValid = false;
    public Item[] validStack = ModItems.templates;

    public TileEntityRitualAltar() {
        super();
    }

    public boolean isItemValid(ItemStack stack) {
        return Arrays.stream(validStack).map(ItemStackUtils::getItemStack).anyMatch(stacked -> !stacked.isEmpty() && stack == stacked);
    }

    public boolean isItemValid(Item item) {
        return this.isItemValid(getItemStack(item));
    }

    public boolean isItemValid(Block item) {
        return this.isItemValid(getItemStack(item));
    }

    @Override
    public void update() {
    }
}
