/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.events;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thedragonteam.armorplus.ArmorPlus;

import java.awt.*;
import java.util.Random;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class GlobalEventArmorPlus {

    public static Random random = new Random();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onTooltipColour(RenderTooltipEvent.Color event) {
        ResourceLocation rl = event.getStack().getItem().getRegistryName();

        Color gold = new Color(255, 148, 0);
        Color dark_purple = new Color(60, 26, 70);
        Color purple = new Color(100, 27, 129);
        Color light_green = new Color(101, 255, 93);

        if (rl != null && rl.getResourceDomain().equals("armorplus")) {
            String rp = rl.getResourcePath();
            if (rp.contains("lava") && rp.contains("obsidian")) {
                event.setBorderStart(gold.getRGB());
                event.setBorderEnd(dark_purple.getRGB());
            } else if (rp.contains("coal")) {
                setBorderColor(event, Color.GRAY);
            } else if (rp.contains("lapis")) {
                setBorderColor(event, Color.BLUE);
            } else if (rp.contains("redstone") || rp.contains("ardite")) {
                setBorderColor(event, Color.RED);
            } else if (rp.contains("emerald")) {
                setBorderColor(event, Color.GREEN);
            } else if (rp.contains("obsidian")) {
                setBorderColor(event, dark_purple);
            } else if (rp.contains("lava")) {
                setBorderColor(event, gold);
            } else if (rp.contains("guardian") || rp.contains("chicken")) {
                setBorderColor(event, Color.CYAN);
            } else if (rp.contains("super_star")) {
                setBorderColor(event, Color.WHITE);
            } else if (rp.contains("ender_dragon") || rp.contains("manyullyn") || rp.contains("knight_slime")) {
                setBorderColor(event, purple);
            } else if (rp.contains("ultimate") || rp.contains("chicken")) {
                setBorderColor(event, light_green);
            }
        }
    }

    public static void setBorderColor(RenderTooltipEvent.Color event, Color color) {
        event.setBorderStart(color.getRGB());
        event.setBorderEnd(color.getRGB());
    }
}