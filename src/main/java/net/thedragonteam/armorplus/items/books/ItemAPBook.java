/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.books;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.client.gui.GuiArmorPlusInfo;
import net.thedragonteam.armorplus.items.base.BaseItem;

import javax.annotation.Nonnull;

public class ItemAPBook extends BaseItem {

    public ItemAPBook() {
        super("book");
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        if (!player.getHeldItem(hand).isEmpty())
            Minecraft.getMinecraft().displayGuiScreen(new GuiArmorPlusInfo());
        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
}
