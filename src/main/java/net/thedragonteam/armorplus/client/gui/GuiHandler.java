/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.thedragonteam.armorplus.container.ContainerAdvancedArmorForge;
import net.thedragonteam.armorplus.container.ContainerArmorForge;
import net.thedragonteam.armorplus.tileentity.TileEntityAdvancedArmorForge;
import net.thedragonteam.armorplus.tileentity.TileEntityArmorForge;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_ARMORPLUS = 0;
    public static final int GUI_ARMORPLUS_INFO = 1;
    public static final int GUI_ARMOR_FORGE = 2;
    public static final int GUI_ADVANCED_ARMOR_FORGE = 3;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_ARMORPLUS)
            return new GuiArmorPlus();
        if (ID == GUI_ARMORPLUS_INFO)
            return new GuiArmorPlusInfo();
        if (ID == GUI_ARMOR_FORGE) {
            return new ContainerArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ADVANCED_ARMOR_FORGE) {
            return new ContainerAdvancedArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityAdvancedArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_ARMORPLUS)
            return new GuiArmorPlus();
        if (ID == GUI_ARMORPLUS_INFO)
            return new GuiArmorPlusInfo();
        if (ID == GUI_ARMOR_FORGE) {
            return new GuiArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ADVANCED_ARMOR_FORGE) {
            return new GuiAdvancedArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityAdvancedArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}