package com.sofodev.armorplus.registry.items.special;

import com.sofodev.armorplus.registry.items.special.gui.BookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class APBookItem extends BookItem {

    public APBookItem(Properties builder) {
        super(builder);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        this.openBook(player, stack, hand);
        player.awardStat(Stats.ITEM_USED.get(this));
        return ActionResult.sidedSuccess(stack, world.isClientSide());
    }

    public void openBook(PlayerEntity player, ItemStack stack, Hand hand) {
        Minecraft.getInstance().setScreen(new BookScreen());
    }
}
