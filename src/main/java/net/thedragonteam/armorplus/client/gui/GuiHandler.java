/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.thedragonteam.armorplus.container.ContainerHighTechBench;
import net.thedragonteam.armorplus.container.ContainerUltiTechBench;
import net.thedragonteam.armorplus.container.ContainerWorkbench;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_ARMORPLUS = 0;
    public static final int GUI_ARMORPLUS_INFO = 1;
    public static final int GUI_WORKBENCH = 2;
    public static final int GUI_HIGH_TECH_BENCH = 3;
    public static final int GUI_ULTI_TECH_BENCH = 4;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_ARMORPLUS)
            return new GuiArmorPlus();
        if (ID == GUI_ARMORPLUS_INFO)
            return new GuiArmorPlusInfo();
        if (ID == GUI_WORKBENCH) {
            return new ContainerWorkbench(player.inventory, world, new BlockPos(x, y, z), (TileEntityWorkbench) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_HIGH_TECH_BENCH) {
            return new ContainerHighTechBench(player.inventory, world, new BlockPos(x, y, z), (TileEntityHighTechBench) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ULTI_TECH_BENCH) {
            return new ContainerUltiTechBench(player.inventory, world, new BlockPos(x, y, z), (TileEntityUltiTechBench) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_ARMORPLUS)
            return new GuiArmorPlus();
        if (ID == GUI_ARMORPLUS_INFO)
            return new GuiArmorPlusInfo();
        if (ID == GUI_WORKBENCH) {
            return new GuiWorkbench(player.inventory, world, new BlockPos(x, y, z), (TileEntityWorkbench) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_HIGH_TECH_BENCH) {
            return new GuiHighTechBench(player.inventory, world, new BlockPos(x, y, z), (TileEntityHighTechBench) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ULTI_TECH_BENCH) {
            return new ContainerUltiTechBench(player.inventory, world, new BlockPos(x, y, z), (TileEntityUltiTechBench) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}