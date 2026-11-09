package com.sofodev.armorplus.client;

import com.sofodev.armorplus.ArmorPlus;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;

/** Lays out long buff lists in item tooltips as a grid, see {@link BuffGridTooltip}. */
@EventBusSubscriber(modid = ArmorPlus.MODID, value = Dist.CLIENT)
public final class TooltipEvents {

    private TooltipEvents() {
    }

    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(BuffGridTooltip.class, grid -> grid);
    }

    @SubscribeEvent
    public static void gatherTooltipComponents(RenderTooltipEvent.GatherComponents event) {
        BuffGridTooltip.collapse(event.getTooltipElements(), event.getScreenWidth());
    }
}
