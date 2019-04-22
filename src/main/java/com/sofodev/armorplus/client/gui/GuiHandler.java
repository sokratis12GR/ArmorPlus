/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.gui;

import com.sofodev.armorplus.common.container.*;
import com.sofodev.armorplus.common.tileentity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * @author Sokratis Fotkatzikis
 */
public class GuiHandler implements IGuiHandler {

    public static final int GUI_ARMORPLUS_INFO = 0;
    public static final int GUI_WORKBENCH = 2;
    public static final int GUI_HIGH_TECH_BENCH = 3;
    public static final int GUI_ULTI_TECH_BENCH = 4;
    public static final int GUI_CHAMPION_BENCH = 5;
    public static final int GUI_LAVA_INFUSER = 9;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case GUI_ARMORPLUS_INFO:
                return new GuiArmorPlusInfo();
            case GUI_WORKBENCH:
                if (te instanceof TileWB)
                    return new ContainerWorkbench(player.inventory, (TileWB) te);
                break;
            case GUI_HIGH_TECH_BENCH:
                if (te instanceof TileHTB)
                    return new ContainerHighTechBench(player.inventory, (TileHTB) te);
                break;
            case GUI_ULTI_TECH_BENCH:
                if (te instanceof TileUTB)
                    return new ContainerUltiTechBench(player.inventory, (TileUTB) te);
                break;
            case GUI_CHAMPION_BENCH:
                if (te instanceof TileCB)
                    return new ContainerChampionBench(player.inventory, (TileCB) te);
                break;
            case GUI_LAVA_INFUSER:
                if (te instanceof TileLavaInfuser)
                    return new ContainerLavaInfuser(player.inventory, (TileLavaInfuser) te);
                break;
            default:
                return null;
        }
        return null;
    }

    @Nullable
    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case GUI_ARMORPLUS_INFO:
                return new GuiArmorPlusInfo();
            case GUI_WORKBENCH:
                if (te instanceof TileWB)
                    return new GuiWorkbench(player.inventory, (TileWB) te);
                break;
            case GUI_HIGH_TECH_BENCH:
                if (te instanceof TileHTB)
                    return new GuiHighTechBench(player.inventory, (TileHTB) te);
                break;
            case GUI_ULTI_TECH_BENCH:
                if (te instanceof TileUTB)
                    return new GuiUltiTechBench(player.inventory, (TileUTB) te);
                break;
            case GUI_CHAMPION_BENCH:
                if (te instanceof TileCB)
                    return new GuiChampionBench(player.inventory, (TileCB) te);
                break;
            case GUI_LAVA_INFUSER:
                if (te instanceof TileLavaInfuser)
                    return new GuiLavaInfuser(player.inventory, (TileLavaInfuser) te);
                break;
            default:
                return null;
        }
        return null;
    }
}