package com.sofodev.armorplus.registry.item.material;

import com.sofodev.armorplus.registry.item.APItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

import static com.sofodev.armorplus.utils.ToolTipUtils.addItemLore;

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
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
        addItemLore(tooltip, this);
        super.appendHoverText(stack, ctx, display, tooltip, flag);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasGlint;
    }
}