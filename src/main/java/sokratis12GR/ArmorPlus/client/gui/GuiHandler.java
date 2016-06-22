package sokratis12GR.ArmorPlus.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import sokratis12GR.ArmorPlus.container.ContainerArmorForge;
import sokratis12GR.ArmorPlus.tileentity.TileEntityArmorForge;


public class GuiHandler implements IGuiHandler {

    public static final int GUI_ARMORPLUS = 0;
    public static final int GUI_ARMOR_FORGE = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_ARMORPLUS)
            return new GuiArmorPlus();
        if (ID == GUI_ARMOR_FORGE) {
            return new ContainerArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityArmorForge) world.getTileEntity(new BlockPos(x,y,z)));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_ARMORPLUS)
            return new GuiArmorPlus();
        if (ID == GUI_ARMOR_FORGE) {
            return new GuiArmorForge(player.inventory, world, new BlockPos(x, y, z), (TileEntityArmorForge) world.getTileEntity(new BlockPos(x,y,z)));
        }
        return null;
    }
}