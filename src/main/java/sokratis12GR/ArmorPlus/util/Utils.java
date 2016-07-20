package sokratis12GR.ArmorPlus.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.api.Constants;
import sokratis12GR.ArmorPlus.api.util.NBTHelper;

import java.util.Set;

/**
 * sokratis12GR.ArmorPlus.util
 * ArmorPlus created by sokratis12GR on 7/18/2016 8:17 PM.
 */
public class Utils
{
    public static ItemStack setUnbreakable(ItemStack stack)
    {
        NBTHelper.checkNBT(stack);
        stack.getTagCompound().setBoolean("Unbreakable", true);
        return stack;
    }

    public static void registerHandlers(Set<ASMDataTable.ASMData> eventHandlers)
    {
        for (ASMDataTable.ASMData data : eventHandlers)
        {
            try
            {
                Class<?> handlerClass = Class.forName(data.getClassName());
                Object handlerImpl = handlerClass.newInstance();
                MinecraftForge.EVENT_BUS.register(handlerImpl);
            } catch (Exception e)
            {
                // No-op
            }
        }
    }

}