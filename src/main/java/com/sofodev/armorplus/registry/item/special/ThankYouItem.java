package com.sofodev.armorplus.registry.item.special;

import com.sofodev.armorplus.registry.item.APItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.*;
import static net.minecraft.world.Containers.dropItemStack;

public class ThankYouItem extends APItem {

    public ThankYouItem() {
        super(new Properties().fireResistant().stacksTo(1));
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(translate(GOLD, "tooltip.armorplus.thank_you.1"));
        tooltip.add(translate(BLUE, "tooltip.armorplus.thank_you.2"));
        tooltip.add(translate(GREEN, "tooltip.armorplus.thank_you.3"));
        tooltip.add(translate(RED, "tooltip.armorplus.thank_you.4"));
        super.appendHoverText(stack, ctx, tooltip, flagIn);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        return super.use(world, player, hand);
    }
}
