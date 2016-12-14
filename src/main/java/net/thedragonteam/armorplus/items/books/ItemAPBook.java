/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.books;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.client.gui.GuiArmorPlusInfo;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class ItemAPBook extends Item {

    public ItemAPBook() {
        setRegistryName("book");
        setUnlocalizedName(setName("book"));
        setCreativeTab(ArmorPlus.tabArmorplusItems);
        GameRegistry.register(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!player.getHeldItem(hand).isEmpty())
            Minecraft.getMinecraft().displayGuiScreen(new GuiArmorPlusInfo());
        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
