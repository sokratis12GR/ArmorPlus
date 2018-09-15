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
import net.thedragonteam.armorplus.items.base.ItemBase;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public class ItemAPBook extends ItemBase {

    public ItemAPBook() {
        super("book");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        if (!player.getHeldItem(hand).isEmpty()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiArmorPlusInfo());
        }
        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

}