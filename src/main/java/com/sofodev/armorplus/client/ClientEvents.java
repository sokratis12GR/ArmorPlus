package com.sofodev.armorplus.client;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.client.renderer.APArrowRenderer;
import com.sofodev.armorplus.registry.ModEntities;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = ArmorPlus.MODID, value = Dist.CLIENT)
public final class ClientEvents {

    private ClientEvents() {
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.COAL_ARROW.get(), context -> new APArrowRenderer(context, texture("coal_arrow")));
        event.registerEntityRenderer(ModEntities.LAPIS_ARROW.get(), context -> new APArrowRenderer(context, texture("lapis_arrow")));
        event.registerEntityRenderer(ModEntities.REDSTONE_ARROW.get(), context -> new APArrowRenderer(context, texture("redstone_arrow")));
        event.registerEntityRenderer(ModEntities.EMERALD_ARROW.get(), context -> new APArrowRenderer(context, texture("emerald_arrow")));
        event.registerEntityRenderer(ModEntities.OBSIDIAN_ARROW.get(), context -> new APArrowRenderer(context, texture("obsidian_arrow")));
        event.registerEntityRenderer(ModEntities.INFUSED_LAVA_ARROW.get(), context -> new APArrowRenderer(context, texture("lava_arrow")));
        event.registerEntityRenderer(ModEntities.GUARDIAN_ARROW.get(), context -> new APArrowRenderer(context, texture("guardian_arrow")));
        event.registerEntityRenderer(ModEntities.SUPER_STAR_ARROW.get(), context -> new APArrowRenderer(context, texture("super_star_arrow")));
        event.registerEntityRenderer(ModEntities.ENDER_DRAGON_ARROW.get(), context -> new APArrowRenderer(context, texture("ender_dragon_arrow")));
    }

    private static Identifier texture(String name) {
        return Identifier.fromNamespaceAndPath(ArmorPlus.MODID, "textures/entity/projectiles/" + name + ".png");
    }
}
