package sokratis12GR.ArmorPlus.resources;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Socrates on 4/4/2016.
 */
public class MobDrops {

    @SubscribeEvent
    public void playerKilledEnderDragon(LivingDropsEvent event)
    {
        if(event.getEntity() instanceof EntityDragon)
        {
            EntityItem entityItem = event.getEntityLiving().dropItem(ArmorPlusItems.EnderDragonScale, 24);
        }
    }
}
