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
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        this.openBook(player, stack, hand);
        player.addStat(Stats.ITEM_USED.get(this));
        return ActionResult.func_233538_a_(stack, world.isRemote());
    }

    public void openBook(PlayerEntity player, ItemStack stack, Hand hand) {
        Minecraft.getInstance().displayGuiScreen(new BookScreen());
    }
}
