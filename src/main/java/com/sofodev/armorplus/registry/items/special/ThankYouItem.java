package com.sofodev.armorplus.registry.items.special;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.*;
import static net.minecraft.world.Containers.dropItemStack;

public class ThankYouItem extends APItem {

    public ThankYouItem() {
        super(new Properties().fireResistant().stacksTo(0).tab(CreativeModeTab.TAB_SEARCH));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create(stack.getHoverName().getString(), ChatFormatting.RED);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(translate(GOLD, "tooltip.armorplus.thank_you_6m.1"));
        tooltip.add(translate(BLUE, "tooltip.armorplus.thank_you_6m.2"));
        tooltip.add(translate(GREEN, "tooltip.armorplus.thank_you_6m.3"));
        tooltip.add(translate(RED, "tooltip.armorplus.thank_you_6m.4"));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            dropItemStack(world, player.getX(), player.getY(), player.getZ(), new ItemStack(Items.CAKE));
            player.getMainHandItem().setCount(0);
        }
        return super.use(world, player, hand);
    }
}
