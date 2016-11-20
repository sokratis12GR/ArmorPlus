/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.client.gui.GuiArmorPlusAbilities;
import net.thedragonteam.armorplus.client.gui.GuiArmorPlusInfo;
import net.thedragonteam.armorplus.items.Items;

import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseItem extends Item implements IItemHelper {

    private Items items;
    private TextFormatting textFormatting;

    public BaseItem(String name) {
        setRegistryName(name);
        setUnlocalizedName(setName(name));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    public BaseItem(Items itemsIn) {
        this(itemsIn.getName());
        this.items = itemsIn;
        this.textFormatting = items.getFormatting();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (items == null)
            return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
        else switch (items.getId()) {
            case 10:
                Minecraft.getMinecraft().displayGuiScreen(new GuiArmorPlusAbilities());
                break;
            case 11:
                Minecraft.getMinecraft().displayGuiScreen(new GuiArmorPlusInfo());
                break;
        }
        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return items == null ? (localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim() : (textFormatting + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public void getItemStack(ItemStack stack) {
    }

    @Override
    public void getItem(Item item) {
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(this);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
