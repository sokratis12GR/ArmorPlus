/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.books;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.client.gui.GuiArmorPlus;

public class ArmorPlusBook extends Item {

    public ArmorPlusBook() {
        setRegistryName("armorplus_book");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(ArmorPlus.MODID + "." + "armorplus_book");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_ITEMS);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer player, EnumHand hand) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiArmorPlus());
        return super.onItemRightClick(item, world, player, hand);
    }
}
