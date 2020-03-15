package com.sofodev.armorplus.events;


import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.armors.APArmorItem;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.sofodev.armorplus.utils.ItemArmorUtility.isExactMatch;
import static net.minecraft.inventory.EquipmentSlotType.*;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID)
public class ModGlobalEvents {

    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent e) {
        PlayerEntity player = e.player;
        for (ItemStack stack : player.getArmorInventoryList()) {
            if (stack.getItem() instanceof APArmorItem) {
                APArmorItem item = ((APArmorItem) stack.getItem());
                APArmorMaterial mat = item.getMat();
                if (mat == APArmorMaterial.ENDER_DRAGON || mat == APArmorMaterial.SLAYER) {
                    if ((isExactMatch(mat, player, HEAD) && isExactMatch(mat, player, CHEST) && isExactMatch(mat, player, LEGS) &&
                        isExactMatch(mat, player, FEET)) || player.abilities.isCreativeMode || player.isSpectator()) {
                        player.abilities.allowFlying = true;
                    } else {
                        player.abilities.allowFlying = false;
                        player.abilities.isFlying = false;
                    }
                }
            }
        }
    }
}
