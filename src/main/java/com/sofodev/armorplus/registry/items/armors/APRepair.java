package com.sofodev.armorplus.registry.items.armors;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APRepair {

    private List<ItemStack> repairStacks = new ArrayList<>();
    private List<String> repair = new ArrayList<>();
    private List<IItemProvider> repairItems = new ArrayList<>();

    public APRepair(ItemStack... repair) {
        this.repairStacks = Arrays.asList(repair);
    }

    public APRepair(String... repair) {
        this.repair = Arrays.asList(repair);
    }

    public APRepair(IItemProvider... repair) {
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

    public List<IItemProvider> getRepairItems() {
        return repairItems;
    }

    public void setRepairItems(List<IItemProvider> repairItems) {
        this.repairItems = repairItems;
    }
}
