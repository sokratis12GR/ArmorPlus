package com.sofodev.armorplus.client;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.client.renderer.APArrowRenderer;
import com.sofodev.armorplus.client.renderer.EmpoweringPedestalRenderer;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModEntities;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.ModMenus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterSelectItemModelPropertyEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
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
        event.registerEntityRenderer(ModEntities.WARDEN_ARROW.get(), context -> new APArrowRenderer(context, texture("warden_arrow")));
        event.registerEntityRenderer(ModEntities.AMETHYST_ARROW.get(), context -> new APArrowRenderer(context, texture("amethyst_arrow")));
        event.registerEntityRenderer(ModEntities.QUARTZ_ARROW.get(), context -> new APArrowRenderer(context, texture("quartz_arrow")));
        event.registerEntityRenderer(ModEntities.GLOWSTONE_ARROW.get(), context -> new APArrowRenderer(context, texture("glowstone_arrow")));
        event.registerEntityRenderer(ModEntities.HONEY_ARROW.get(), context -> new APArrowRenderer(context, texture("honey_arrow")));
        event.registerBlockEntityRenderer(ModBlocks.EMPOWERING_PEDESTAL_ENTITY.get(), EmpoweringPedestalRenderer::new);
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.RUNE_ALTAR.get(), RuneAltarScreen::new);
        event.register(ModMenus.RITUAL_ALTAR.get(), RitualScreen::new);
    }

    @SubscribeEvent
    public static void registerSelectItemModelProperties(RegisterSelectItemModelPropertyEvent event) {
        event.register(Identifier.fromNamespaceAndPath(ArmorPlus.MODID, "socket_state"), SocketStateProperty.TYPE);
    }

    /** ArmorPlus equipment has the socket symbol in its item models, everything else gets it drawn by the decorator. */
    @SubscribeEvent
    public static void registerItemDecorations(RegisterItemDecorationsEvent event) {
        for (Item item : BuiltInRegistries.ITEM) {
            if (!BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(ArmorPlus.MODID)) {
                event.register(item, SocketOverlayDecorator.INSTANCE);
            }
        }
        event.register(ModItems.ARMOR_RUNE.get(), RuneMarkerDecorator.INSTANCE);
        event.register(ModItems.WEAPON_RUNE.get(), RuneMarkerDecorator.INSTANCE);
        event.register(ModItems.TOOL_RUNE.get(), RuneMarkerDecorator.INSTANCE);
        event.register(ModItems.RANGED_RUNE.get(), RuneMarkerDecorator.INSTANCE);
    }

    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(BuffGridTooltip.class, grid -> grid);
    }

    @SubscribeEvent
    public static void gatherTooltipComponents(RenderTooltipEvent.GatherComponents event) {
        BuffGridTooltip.collapse(event.getTooltipElements(), event.getScreenWidth());
    }

    private static Identifier texture(String name) {
        return Identifier.fromNamespaceAndPath(ArmorPlus.MODID, "textures/entity/projectiles/" + name + ".png");
    }
}
