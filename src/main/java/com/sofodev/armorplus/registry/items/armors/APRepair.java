package com.sofodev.armorplus.registry.items.armors;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APRepair {

    private List<ItemStack> repairStacks = new ArrayList<>();
    private List<String> repair = new ArrayList<>();
    private List<ItemLike> repairItems = new ArrayList<>();

    public APRepair(ItemStack... repair) {
        this.repairStacks = Arrays.asList(repair);
    }

    public APRepair(String... repair) {
        this.repair = Arrays.asList(repair);
    }

    public APRepair(ItemLike... repair) {
        repairItems = Arrays.asList(repair);
    }

    public List<ItemStack> getRepairStacks() {
        return repairStacks;
    }

    public void setRepairStacks(List<ItemStack> repairStacks) {
        this.repairStacks = repairStacks;
    }

    public List<String> getRepair() {
        return repair;
    }

    public void setRepair(List<String> repair) {
        this.repair = repair;
    }

    public List<ItemLike> getRepairItems() {
        return repairItems;
    }

    public void setRepairItems(List<ItemLike> repairItems) {
        this.repairItems = repairItems;
    }
}
