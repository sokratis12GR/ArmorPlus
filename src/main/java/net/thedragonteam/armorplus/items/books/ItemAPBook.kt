/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.books

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.items.base.BaseItem

class ItemAPBook : BaseItem("book") {

    @SideOnly(Side.CLIENT)
    override fun onItemRightClick(world: World?, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        //     if (!player.getHeldItem(hand).isEmpty)
        //         Minecraft.getMinecraft().displayGuiScreen(GuiArmorPlusInfo())
        return ActionResult(EnumActionResult.PASS, player.getHeldItem(hand))
    }

}