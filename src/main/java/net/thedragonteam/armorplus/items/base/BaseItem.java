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
import net.thedragonteam.armorplus.client.gui.GuiArmorPlus;
import net.thedragonteam.armorplus.client.gui.GuiArmorPlusInfo;
import net.thedragonteam.armorplus.items.Items;

import static net.thedragonteam.armorplus.util.Utils.setARPUnlocalizedName;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseItem extends Item {

    private Items items;
    private TextFormatting textFormatting;
    public BaseItem(String name) {
        setRegistryName(name);
        setUnlocalizedName(setARPUnlocalizedName(name));
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
    public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer player, EnumHand hand) {
        if (items == null)
            return new ActionResult<>(EnumActionResult.PASS, item);
        else switch (items.getId()) {
            case 10:
                Minecraft.getMinecraft().displayGuiScreen(new GuiArmorPlus());
                break;
            case 11:
                Minecraft.getMinecraft().displayGuiScreen(new GuiArmorPlusInfo());
                break;
        }
        return new ActionResult<>(EnumActionResult.PASS, item);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return items == null ? (localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim() : (textFormatting + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
