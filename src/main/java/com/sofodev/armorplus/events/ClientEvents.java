package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.core.controller.AnimationController;

import static net.minecraftforge.api.distmarker.Dist.CLIENT;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, value = CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onItemFrameRender(RenderItemInFrameEvent event) {
        ItemStack stack = event.getItem();
        Item item = stack.getItem();
        if (item instanceof APMaceItem) {
            APMaceItem mace = (APMaceItem) item;
            AnimationController<?> controller = APMaceItem.getControllerForStack(mace.factory, stack, mace.controllerName);
            controller.clearAnimationCache();
        }
    }
}
