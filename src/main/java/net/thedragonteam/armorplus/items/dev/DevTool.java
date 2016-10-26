/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.dev;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class DevTool extends Item {

    public DevTool() {
        setRegistryName("dev_tool");
        setUnlocalizedName(ArmorPlus.MODID + "." + "dev_tool");
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target != null) {
            if (!playerIn.worldObj.isRemote)
                playerIn.addChatComponentMessage(new TextComponentString(TextFormatting.GOLD +
                        "[" + target.getName() + "]"
                        + " - " + "Health: " + target.getHealth()
                        + " - " + "Max Health: " + target.getMaxHealth()
                        + " - " + "Class: " + target.getClass()
                        + " - " + "Held Item Off Hand: " + target.getHeldItemOffhand()
                        + " - " + "Held Item Main Hand: " + target.getHeldItemMainhand()
                        + " - " + "Position: " + target.getPosition()
                        + " - " + "Tags: " + target.getTags()));
            return true;
        }
        return true;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.BOLD + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rGives Information about the Target");
            tooltip.add("\2473Use: " + "\247rRight Click a Target");
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", ChatFormatting.BOLD, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
    }
}
