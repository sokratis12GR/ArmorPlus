package com.sofodev.armorplus.registry.items.materials;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;

public class MaterialItem extends APItem {

    private final boolean hasGlint;
    private final ChatFormatting color;

    public MaterialItem(boolean hasGlint, ChatFormatting color, Properties props) {
        super(props);
        this.hasGlint = hasGlint;
        this.color = color;
    }

    public MaterialItem(ChatFormatting color, Properties props) {
        super(props);
        this.hasGlint = false;
        this.color = color;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create(stack.getHoverName().getString(), color);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasGlint;
    }
}