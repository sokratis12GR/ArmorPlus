package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.armors.APArmorItem;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.armors.IAPArmor;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.sofodev.armorplus.utils.GlobalVars.INFUSED_LAVA_CRYSTAL;
import static com.sofodev.armorplus.utils.ItemArmorUtility.areExactMatch;
import static com.sofodev.armorplus.utils.Utils.canAllowFlight;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static net.minecraftforge.eventbus.api.EventPriority.LOW;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModGlobalEvents {

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent e) {
        PlayerEntity player = e.player;
        for (ItemStack stack : player.getArmorInventoryList()) {
            if (stack.getItem() instanceof APArmorItem) {
                APArmorItem item = ((APArmorItem) stack.getItem());
                IAPArmor mat = item.getMat();
                if (mat == APArmorMaterial.ENDER_DRAGON || mat == APArmorMaterial.SLAYER) {
                    if (canAllowFlight(player, areExactMatch(mat, player))) {
                        player.abilities.allowFlying = true;
                    } else {
                        player.abilities.allowFlying = false;
                        player.abilities.isFlying = false;
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = LOW)
    public static void onFireEvent(LivingDamageEvent event) {
        if (event.getSource().isFireDamage() && event.getEntity() instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) event.getEntity();
            if (!itemEntity.getItem().isEmpty()){
                ItemStack prevItemStack = itemEntity.getItem();
                ItemStack itemStack = new ItemStack(INFUSED_LAVA_CRYSTAL);
                if (prevItemStack.getItem().getRegistryName().getPath().equals("lava_crystal")){
                    ((ItemEntity) event.getEntity()).setItem(itemStack);
                }
            }
        }
    }
}