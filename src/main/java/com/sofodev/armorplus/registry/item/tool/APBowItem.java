package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

public class APBowItem extends BowItem {

    public APBowItem(IAPTool tool) {
        super(new Properties().durability((int) (tool.get().getUses() * 0.5)).rarity(tool.getRarity())
        );
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return super.isValidRepairItem(toRepair, repair);
    }
}
