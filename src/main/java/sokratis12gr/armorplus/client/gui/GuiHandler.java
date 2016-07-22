package sokratis12gr.armorplus.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import sokratis12gr.armorplus.container.ContainerArmorForge;
import sokratis12gr.armorplus.tileentity.TileEntityArmorForge;


public class GuiHandler implements IGuiHandler {

    public static final int GUI_ARMORPLUS = 0;
    public static final int GUI_ARMOR_FORGE = 1;
    public static final int GUI_ARMORPLUS_INFO = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_ARMORPLUS)
            return new GuiArmorPlus();
        if (ID == GUI_ARMOR_FORGE) {
            return new ContainerArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ARMORPLUS_INFO)
            return new GuiArmorPlusInfo();
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_ARMORPLUS)
            return new GuiArmorPlus();
        if (ID == GUI_ARMOR_FORGE) {
            return new GuiArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityArmorForge) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if (ID == GUI_ARMORPLUS_INFO)
            return new GuiArmorPlusInfo();
        return null;
    }
}