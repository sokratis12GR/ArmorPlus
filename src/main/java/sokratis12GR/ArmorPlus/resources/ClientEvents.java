/*package sokratis12GR.ArmorPlus.resources;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.ClientProxy;
import sokratis12GR.ArmorPlus.armors.special.TheUltimateArmor;
*/

/**
 * sokratis12GR.ArmorPlus.resources
 * ArmorPlus created by sokratis12GR on 7/19/2016 6:03 PM.
 */

/*public class ClientEvents {

    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent event) {
        EntityPlayer entity = Minecraft.getMinecraft().thePlayer;

        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if (head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots) {
            if (ClientProxy.KEY_NIGHT_VION.isKeyDown()) {
                if (entity instanceof EntityLivingBase)
                    entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, true, true));
            }
        }
    }
}*/