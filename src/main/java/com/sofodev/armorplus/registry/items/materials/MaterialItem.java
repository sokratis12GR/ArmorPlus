package com.sofodev.armorplus.registry.items.materials;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;

public class MaterialItem extends APItem {

    private final boolean hasGlint;
    private TextFormatting color;

    public MaterialItem(boolean hasGlint, TextFormatting color, Properties props) {
        super(props);
        this.hasGlint = hasGlint;
        this.color = color;
    }

    public MaterialItem(TextFormatting color, Properties props) {
        super(props);
        this.hasGlint = false;
        this.color = color;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create(stack.getDisplayName().getString(), color);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return hasGlint;
    }
}