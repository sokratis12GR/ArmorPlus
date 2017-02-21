/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.thedragonteam.armorplus.container.ContainerHighTechBench;
import net.thedragonteam.armorplus.container.ContainerUltiTechBench;
import net.thedragonteam.armorplus.container.ContainerWorkbench;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_ARMORPLUS_INFO = 1;
    public static final int GUI_WORKBENCH = 2;
    public static final int GUI_HIGH_TECH_BENCH = 3;
    public static final int GUI_ULTI_TECH_BENCH = 4;
    public static final int GUI_EXPERIMENT = 100;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_ARMORPLUS_INFO:
                return new GuiArmorPlusInfo();
            case GUI_WORKBENCH:
                return new ContainerWorkbench(player.inventory, world);
            case GUI_HIGH_TECH_BENCH:
                return new ContainerHighTechBench(player.inventory, world);
            case GUI_ULTI_TECH_BENCH:
                return new ContainerUltiTechBench(player.inventory, world);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_ARMORPLUS_INFO:
                return new GuiArmorPlusInfo();
            case GUI_WORKBENCH:
                return new GuiWorkbench(player.inventory, world);
            case GUI_HIGH_TECH_BENCH:
                return new GuiHighTechBench(player.inventory, world);
            case GUI_ULTI_TECH_BENCH:
                return new GuiUltiTechBench(player.inventory, world);
            default:
                break;
        }
        return null;
    }
}