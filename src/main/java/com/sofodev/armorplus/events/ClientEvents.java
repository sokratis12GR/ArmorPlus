package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.ModEntities;
import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingRenderer;
import com.sofodev.armorplus.registry.entities.bosses.WitherlingRenderer;
import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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
