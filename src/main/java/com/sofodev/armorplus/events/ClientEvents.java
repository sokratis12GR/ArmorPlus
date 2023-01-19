package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.util.GeckoLibUtil;

import static net.minecraftforge.api.distmarker.Dist.CLIENT;

@Mod.EventBusSubscriber(modid = ArmorPlus.MODID, value = CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onItemFrameRender(RenderItemInFrameEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
//        if (item instanceof APMaceItem mace) {
//            AnimationController<?> controller = GeckoLibUtil.getControllerForStack(mace.factory, stack, mace.controllerName);
//            controller.clearAnimationCache();
//        }
    }
}
