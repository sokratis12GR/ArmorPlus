package com.sofodev.armorplus.registry.items.materials;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;

public class MaterialItem extends APItem {

    private TextFormatting color;

    public MaterialItem(TextFormatting color) {
        this.color = color;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create(stack.getDisplayName().getString(), color);
    }
}
