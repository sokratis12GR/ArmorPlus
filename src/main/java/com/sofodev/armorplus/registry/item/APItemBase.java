package com.sofodev.armorplus.registry.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;

public class APItemBase extends APItem {

    public APItemBase() {
        super(com.sofodev.armorplus.registry.RegistryContext.itemProperties().rarity(Rarity.UNCOMMON).stacksTo(8));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flagIn) {
        tooltip.accept(translate(TextColor.parseColor("#252874").getOrThrow(), "tooltip.armorplus.base_soulless"));
        super.appendHoverText(stack, ctx, display, tooltip, flagIn);
    }
}