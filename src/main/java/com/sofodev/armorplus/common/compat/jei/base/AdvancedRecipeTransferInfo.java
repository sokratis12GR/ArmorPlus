/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.compat.jei.base;

import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdvancedRecipeTransferInfo<C extends Container> implements IRecipeTransferInfo<C> {
    private final Class<C> containerClass;
    private final String recipeCategoryUid;
    private final int recipeSlotStart;
    private final int recipeSlotCount;
    private final int inventorySlotStart;
    private final int inventorySlotCount;
    private final int additionalSlots;

    public AdvancedRecipeTransferInfo(Class<C> containerClass, String recipeCategoryUid, int recipeSlotStart, int recipeSlotCount, int inventorySlotStart, int inventorySlotCount, boolean hasAdditionalSlots, int additionalSlots) {
        this.containerClass = containerClass;
        this.recipeCategoryUid = recipeCategoryUid;
        this.recipeSlotStart = recipeSlotStart;
        this.recipeSlotCount = recipeSlotCount;
        this.inventorySlotStart = inventorySlotStart;
        this.inventorySlotCount = inventorySlotCount;
        this.additionalSlots = hasAdditionalSlots ? additionalSlots : 0;
    }

    public AdvancedRecipeTransferInfo(Class<C> containerClass, String recipeCategoryUid, int recipeSlotStart, int recipeSlotCount, int inventorySlotStart, int inventorySlotCount) {
        this(containerClass, recipeCategoryUid, recipeSlotStart, recipeSlotCount, inventorySlotStart, inventorySlotCount, false, 0);
    }

    @Override
    public Class<C> getContainerClass() {
        return containerClass;
    }


    @Override
    public String getRecipeCategoryUid() {
        return recipeCategoryUid;
    }

    @Override
    public boolean canHandle(C container) {
        return true;
    }

    @Override
    public List<Slot> getRecipeSlots(C container) {
        ArrayList<Slot> list;
        list = IntStream.range(recipeSlotStart, recipeSlotStart + recipeSlotCount).mapToObj(container::getSlot).collect(Collectors.toCollection(ArrayList::new));
        return list;
    }

    @Override
    public List<Slot> getInventorySlots(C container) {
        ArrayList<Slot> list;
        list = IntStream.range(inventorySlotStart, inventorySlotStart + inventorySlotCount + additionalSlots).filter(i -> i < container.inventorySlots.size()).mapToObj(container::getSlot).collect(Collectors.toCollection(ArrayList::new));
        return list;
    }
}