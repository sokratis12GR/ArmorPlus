package com.sofodev.armorplus.common.events;

import com.sofodev.armorplus.common.compat.draconicevolution.DEUtils;
import com.sofodev.armorplus.common.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemSpecialArmor;
import com.sofodev.armorplus.common.registry.items.armors.base.ItemUltimateArmor;
import com.sofodev.armorplus.common.util.LoaderUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.common.config.ModConfig.IntegrationsConfig.normalChaosResistance;
import static com.sofodev.armorplus.common.config.ModConfig.IntegrationsConfig.ultimateChaosImmunity;
import static com.sofodev.armorplus.common.registry.items.armors.APArmorMaterial.*;
import static com.sofodev.armorplus.common.util.ArmorPlusItemUtils.getChest;

@Mod.EventBusSubscriber(modid = MODID)
public class IntegrationEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingDamaged(LivingHurtEvent event) {
        EntityLivingBase living = event.getEntityLiving();
        if (living == null || living.getEntityWorld().isRemote) return;

        float normalMit = (float) normalChaosResistance;

        DamageSource source = event.getSource();
        if (LoaderUtils.isDELoaded()) {
            ItemStack chest = getChest(living);
            if (chest.getItem() instanceof ItemSpecialArmor) {
                APArmorMaterial material = ((ItemSpecialArmor) chest.getItem()).getMaterial();
                if (material == GUARDIAN || material == SUPER_STAR || material == ENDER_DRAGON) {
                    mitigateChaos(event, living, normalMit, source);
                }
            }
            if (chest.getItem() instanceof ItemUltimateArmor && DEUtils.isChaosDamage(source) && living instanceof EntityPlayer && ultimateChaosImmunity) {
                event.setCanceled(true);
            }
        }
    }

    private static void mitigateChaos(LivingHurtEvent event, EntityLivingBase living, float normalMit, DamageSource source) {
        if (DEUtils.isChaosDamage(source)) {
            if (living instanceof EntityPlayer && ((EntityPlayer) living).isCreative()) {
                event.setCanceled(true);
                return;
            }
            event.setAmount(event.getAmount() * (1F - normalMit));
            if (event.getAmount() <= 1E-04) {
                event.setCanceled(true);
                return;
            }
        }
    }
}