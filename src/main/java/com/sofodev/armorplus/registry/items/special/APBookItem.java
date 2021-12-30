package com.sofodev.armorplus.registry.items.special;

import com.sofodev.armorplus.registry.items.special.gui.BookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class APBookItem extends BookItem {

    public APBookItem(Properties builder) {
        super(builder);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        this.openBook(player, stack, hand);
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
    }

    public void openBook(Player player, ItemStack stack, InteractionHand hand) {
        Minecraft.getInstance().setScreen(new BookScreen());
    }
}
