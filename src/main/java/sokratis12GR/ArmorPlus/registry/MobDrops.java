package sokratis12GR.ArmorPlus.registry;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

/**
 * Created by sokratis12GR on 4/4/2016.
 */
public class MobDrops {
    Random random = new Random();
    int min = 0;
    int max = 1;

    @SubscribeEvent
    public void playerKilledEnderDragon(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityDragon) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.ENDER_DRAGON_SCALE, 12);
        }
    }

    @SubscribeEvent
    public void playerKilledWither(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityWither) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.WITHER_BONE, 6);
        }
    }

    @SubscribeEvent
    public void playerKilledElderGuardian(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityGuardian && ((EntityGuardian) event.getEntity()).isElder()) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.GUARDIAN_SCALE, 6);
        }
    }

    @SubscribeEvent
    public void playerKilledGuardian(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityGuardian && !((EntityGuardian) event.getEntity()).isElder()) {
            EntityItem entityItem = event.getEntityLiving().dropItem(ModItems.GUARDIAN_SCALE, random.nextInt(max - min + 1) + min
            );
        }
    }

}
