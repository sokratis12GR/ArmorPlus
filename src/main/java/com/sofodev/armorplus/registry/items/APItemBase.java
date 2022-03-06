package com.sofodev.armorplus.registry.items;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;

public class APItemBase extends APItem {

    public APItemBase() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(8));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(translate(TextColor.parseColor("#252874"), "tooltip.armorplus.base_soulless"));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}