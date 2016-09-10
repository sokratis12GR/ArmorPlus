/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.consumables;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.thedragonteam.armorplus.ARPConfig.*;
import static net.thedragonteam.core.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.items.consumables
 * ArmorPlus created by sokratis12GR on 6/30/2016 2:59 PM.
 * - TheDragonTeam
 */
public class TheGiftOfTheGods extends Item {

    private static Random random = new Random();

    public TheGiftOfTheGods() {
        super();
        setRegistryName("the_gift_of_the_gods");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(ArmorPlus.MODID + "." + "the_gift_of_the_gods");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
        this.setMaxStackSize(1);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.GOLD + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        List<String> blackListedItems = Arrays.asList(ARPConfig.blackListedItems);
        if (worldIn.isRemote) {
            return new ActionResult(EnumActionResult.PASS, itemStackIn);
        }

        int count;
        Item item = null;
        do {
            if (!ARPConfig.enableWhiteList) {
                count = 256 + random.nextInt(32000 - 256);
                item = Item.getItemById(count);
            }
            if (ARPConfig.enableWhiteList) {
                item = Item.getByNameOrId(whiteListedItems[random.nextInt(whitelistmax - whitelistmin + 1) + whitelistmin]);
            }
        }
        while (item == null /*|| (item != null && item instanceof ItemBlock)*/ || item == Item.getByNameOrId(blackListedItems.toString()) && enableBlackList);

        itemStackIn = new ItemStack(item);

        playerIn.addChatMessage(new TextComponentString("You got: " + itemStackIn.getItem().getRegistryName()));

        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
        infoList.add("\2479Ability: " + "\247rGrants Random Item");
        infoList.add("\2473Use: " + "\247rRight-Click");
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}