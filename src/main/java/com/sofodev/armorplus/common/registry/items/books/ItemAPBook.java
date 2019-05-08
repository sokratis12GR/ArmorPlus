/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.books;

import com.sofodev.armorplus.client.gui.GuiArmorPlusInfo;
import com.sofodev.armorplus.common.registry.items.base.ItemBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author Sokratis Fotkatzikis
 **/
public class ItemAPBook extends ItemBase {

    public ItemAPBook(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        if (!player.getHeldItem(hand).isEmpty()) {
            Minecraft.getInstance().displayGuiScreen(new GuiArmorPlusInfo());
        }
        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

}