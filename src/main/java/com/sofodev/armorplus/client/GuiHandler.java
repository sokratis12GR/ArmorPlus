package com.sofodev.armorplus.client;

import com.sofodev.armorplus.client.gui.*;
import com.sofodev.armorplus.common.container.ContainerLavaInfuser;
import com.sofodev.armorplus.common.container.base.ContainerBase;
import com.sofodev.armorplus.common.registry.tileentity.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

import static com.sofodev.armorplus.common.util.Utils.setRL;

public class GuiHandler {

    public static final ResourceLocation GUI_ARMORPLUS_INFO = setRL("armorplus_book_gui");
    public static final ResourceLocation GUI_LAVA_INFUSER = setRL("lava_infuser_gui");

    private static void openBenchGUI(ResourceLocation location, int id, EntityPlayerMP playerIn, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = playerIn.world.getTileEntity(new BlockPos(x, y, z));
        Server svr = null;
        if (GUI_LAVA_INFUSER.equals(location)) {
            if (te instanceof TileLavaInfuser) {
                svr = new Server(new ContainerLavaInfuser(playerIn.inventory, (TileLavaInfuser) te), GUI_LAVA_INFUSER, id, pos);
            }
        }
        if (svr != null) {
            NetworkHooks.openGui(playerIn, svr, svr::encode);
        }

    }

    public static void openBenchGUI(ResourceLocation location, BlockPos pos, EntityPlayerMP playerIn, int id) {
        openBenchGUI(location, id, playerIn, pos.getX(), pos.getY(), pos.getZ());
    }

    public static class Server implements IInteractionObject {
        private final ContainerBase containerBase;
        private final ResourceLocation location;
        private final int id;
        private final BlockPos pos;

        public Server(ContainerBase containerBase, ResourceLocation location, int id, BlockPos pos) {
            this.containerBase = containerBase;
            this.location = location;
            this.id = id;
            this.pos = pos;
        }

        @Override
        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
            return containerBase;
        }

        @Override
        public String getGuiID() {
            return location.toString();
        }

        @Override
        public ITextComponent getName() {
            return new TextComponentString(location.toString());
        }

        @Override
        public boolean hasCustomName() {
            return false;
        }

        @Nullable
        @Override
        public ITextComponent getCustomName() {
            return null;
        }

        public void encode(PacketBuffer packetBuffer) {
            packetBuffer.writeVarInt(id);
            packetBuffer.writeBlockPos(pos);
        }
    }

    public static class Client {
        public static GuiScreen getClientGuiElement(FMLPlayMessages.OpenContainer message) {
            ResourceLocation id = message.getId();
            Minecraft mc = Minecraft.getInstance();
            PacketBuffer data = message.getAdditionalData();
            BlockPos pos = data.readBlockPos();
            TileEntity te = mc.world.getTileEntity(pos);
            if (GUI_ARMORPLUS_INFO.equals(id)) {
                return new GuiArmorPlusInfo();
            } else if (GUI_LAVA_INFUSER.equals(id)) {
                if (te instanceof TileLavaInfuser) {
                    return new GuiLavaInfuser(mc.player.inventory, (TileLavaInfuser) te);
                }
            }
            return null;
        }
    }
}