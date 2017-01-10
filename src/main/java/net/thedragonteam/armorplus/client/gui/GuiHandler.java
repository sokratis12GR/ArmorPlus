/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.thedragonteam.armorplus.container.*;
import net.thedragonteam.armorplus.tileentity.TileEntityChampionBench;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench;
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser;

public class GuiHandler implements IGuiHandler {


    public static final int GUI_ARMORPLUS_INFO = 0;
    public static final int GUI_WORKBENCH = 2;
    public static final int GUI_HIGH_TECH_BENCH = 3;
    public static final int GUI_ULTI_TECH_BENCH = 4;
    public static final int GUI_CHAMPION_BENCH = 5;
    public static final int GUI_WORKBENCH_NEW = 6;
    public static final int GUI_LAVA_INFUSER = 9;
    public static final int GUI_EXPERIMENT = 100;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case GUI_ARMORPLUS_INFO:
                return new GuiArmorPlusInfo();
            case GUI_WORKBENCH:
                if (te != null && te instanceof TileEntityWorkbench)
                    return new ContainerWorkbench(player.inventory, (TileEntityWorkbench) te);
            case GUI_HIGH_TECH_BENCH:
                if (te != null && te instanceof TileEntityHighTechBench)
                    return new ContainerHighTechBench(player.inventory, (TileEntityHighTechBench) te);
            case GUI_ULTI_TECH_BENCH:
                if (te != null && te instanceof TileEntityUltiTechBench)
                    return new ContainerUltiTechBench(player.inventory, (TileEntityUltiTechBench) te);
            case GUI_CHAMPION_BENCH:
                if (te != null && te instanceof TileEntityChampionBench)
                    return new ContainerChampionBench(player.inventory, (TileEntityChampionBench) te);
            case GUI_WORKBENCH_NEW:
                return new ContainerWorkbenchNew(player.inventory, world);
            case GUI_LAVA_INFUSER:
                if (te != null && te instanceof TileEntityLavaInfuser)
                    return new ContainerLavaInfuser(player.inventory, (TileEntityLavaInfuser) te);
            default:
                break;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case GUI_ARMORPLUS_INFO:
                return new GuiArmorPlusInfo();
            case GUI_WORKBENCH:
                if (te != null && te instanceof TileEntityWorkbench)
                    return new GuiWorkbench(player.inventory, (TileEntityWorkbench) te);
            case GUI_HIGH_TECH_BENCH:
                if (te != null && te instanceof TileEntityHighTechBench)
                    return new GuiHighTechBench(player.inventory, (TileEntityHighTechBench) te);
            case GUI_ULTI_TECH_BENCH:
                if (te != null && te instanceof TileEntityUltiTechBench)
                    return new GuiUltiTechBench(player.inventory, (TileEntityUltiTechBench) te);
            case GUI_CHAMPION_BENCH:
                if (te != null && te instanceof TileEntityChampionBench)
                    return new GuiChampionBench(player.inventory, (TileEntityChampionBench) te);
            case GUI_WORKBENCH_NEW:
                return new GuiWorkbenchNew(player.inventory, world);
            case GUI_LAVA_INFUSER:
                if (te != null && te instanceof TileEntityLavaInfuser)
                    return new GuiLavaInfuser(player.inventory, (TileEntityLavaInfuser) te);
            default:
                break;
        }
        return null;
    }
}