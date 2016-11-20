/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.books;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.client.gui.GuiArmorPlusAbilities;
import net.thedragonteam.armorplus.client.gui.GuiArmorPlusInfo;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class ItemARPBook extends Item {
    public ItemARPBook() {
        setRegistryName("book");
        setUnlocalizedName(setName("book"));
        setHasSubtypes(true);
        setCreativeTab(ArmorPlus.tabArmorplusItems);
        GameRegistry.register(this);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        switch (stack.getMetadata()) {
            case 0:
                return super.getUnlocalizedName(stack) + "_abilities";
            case 1:
                return super.getUnlocalizedName(stack) + "_info";
        }
        return super.getUnlocalizedName(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (player.getHeldItem(hand) == new ItemStack(ModItems.books))
            switch (player.getHeldItem(hand).getMetadata()) {
                case 0:
                    Minecraft.getMinecraft().displayGuiScreen(new GuiArmorPlusAbilities());
                case 1:
                    Minecraft.getMinecraft().displayGuiScreen(new GuiArmorPlusInfo());
            }
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (int i = 0; i <= 1; i++)
            subItems.add(new ItemStack(itemIn, 1, i));
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        for (int i = 0; i <= 1; i++)
            ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
