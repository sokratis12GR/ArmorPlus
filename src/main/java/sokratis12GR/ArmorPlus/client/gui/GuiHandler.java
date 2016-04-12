package sokratis12GR.ArmorPlus.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import sokratis12GR.ArmorPlus.inventory.ContainerArmorWorkshop;


public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) {
            return new ContainerArmorWorkshop(player.inventory, world, BlockPos.ORIGIN);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) {
            return new ContainerArmorWorkshop(player.inventory, world, BlockPos.ORIGIN);
        }
        return null;
    }
}