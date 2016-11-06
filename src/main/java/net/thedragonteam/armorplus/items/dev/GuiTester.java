/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.dev;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.client.gui.GuiARPExperiments;
import net.thedragonteam.armorplus.items.base.BaseItem;

public class GuiTester extends BaseItem {

    public GuiTester() {
        super("gui_tester");
    }

    private Minecraft mc = Minecraft.getMinecraft();

    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer player, EnumHand hand) {
        while (Minecraft.getMinecraft().gameSettings.keyBindSneak.isPressed()) {
            mc.displayGuiScreen(new GuiARPExperiments());
        }
        return new ActionResult(EnumActionResult.PASS, item);
    }
}
