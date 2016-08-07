package sokratis12gr.armorplus.common;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import sokratis12gr.armorplus.api.ObfuscatedNames;
import sokratis12gr.armorplus.api.util.ReflectionUtils;

public class CommonPlayerTickHandler {

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == Side.SERVER) {
            tickEnd(event.player);
        }
    }

    public void tickEnd(EntityPlayer player) {
    }

    public static boolean isOnGround(EntityPlayer player) {
        int x = MathHelper.floor_double(player.posX);
        int y = (int) Math.round(player.posY - 1);
        int z = MathHelper.floor_double(player.posZ);

        BlockPos pos = new BlockPos(x, y, z);
        IBlockState s = player.worldObj.getBlockState(pos);
        AxisAlignedBB box = s.getCollisionBoundingBox(player.worldObj, pos);
        AxisAlignedBB playerBox = player.getCollisionBoundingBox();

        return box != null && playerBox != null && playerBox.offset(0, -0.01, 0).intersectsWith(box);
    }
}