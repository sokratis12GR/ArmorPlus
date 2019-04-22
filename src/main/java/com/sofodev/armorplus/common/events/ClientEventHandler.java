/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.events;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import static java.awt.Color.*;

/**
 * @author Sokratis Fotkatzikis
 */
@EventBusSubscriber(modid = ArmorPlus.MODID, value = Side.CLIENT)
@SideOnly(Side.CLIENT)
public class ClientEventHandler {

    public static final Random random = new Random();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onTooltipColorEvent(RenderTooltipEvent.Color event) {
        ItemStack stack = event.getStack();
        ResourceLocation rl = stack.getItem().getRegistryName();

        Color gold = new Color(255, 148, 0);
        Color darkPurple = new Color(60, 26, 70);
        Color purple = new Color(100, 27, 129);
        Color lightGreen = new Color(101, 255, 93);
        Color lightBlue = new Color(53, 92, 255);

        if (rl == null || !rl.getNamespace().equals("armorplus")) {
            return;
        }
        String rp = rl.getPath();
        if ((rp.contains("lava") && rp.contains("obsidian")) || (rp.contains("lava") && rp.contains("infuser"))) {
            setBorderColor(event, gold, darkPurple);
        }
        setBorder(event, GRAY, rl, "Coal", "coal");
        setBorder(event, BLUE, rl, "Lapis", "lapis", "blue");
        setBorder(event, RED, rl, "Redstone", "redstone", "ardite", "high_tech_bench", "red");
        setBorder(event, GREEN, rl, "Emerald", "emerald", "green");
        setBorder(event, darkPurple, rl, "Obsidian", "obsidian");
        setBorder(event, gold, rl, "Lava", "champion_bench", "lava", "infused_lava", "lava_infused", "infused");
        setBorder(event, CYAN, rl, "Guardian", "guardian", "chicken");
        setBorder(event, WHITE, rl, "Wither", "wither", "super_star", "white");
        setBorder(event, purple, rl, "Ender Dragon", "ender_dragon", "manyullyn", "knight_slime", "purple");
        setBorder(event, lightGreen, rl, "Ultimate", "ultimate", "slime", "ulti_tech_bench");
        setBorder(event, lightBlue, rl, "Cobalt", "cobalt", "workbench");
        setBorder(event, YELLOW, rl, "Yellow", "yellow");
        setBorder(event, BLACK, rl, "Black", "black");
    }

    private static void setBorder(RenderTooltipEvent.Color event, Color color, ResourceLocation rl, String name, String... ids) {
        if (match(rl, name, ids)) {
            setBorderColor(event, color);
        }
    }

    private static void setBorder(RenderTooltipEvent.Color event, Color start, Color end, ResourceLocation rl, String name, String... ids) {
        if (match(rl, name, ids)) {
            setBorderColor(event, start, end);
        }
    }

    private static boolean match(ResourceLocation rl, String name, String... ids) {
        return rl != null && (match(rl.getPath(), ids) || match(rl.getNamespace(), name));
    }

    private static boolean match(String displayName, String name) {
        return displayName.contains(name);
    }

    private static boolean match(String rp, String... ids) {
        return Arrays.stream(ids).anyMatch(rp::contains);
    }

    /**
     * @param event the tooltip event that we will use to change the border's colors
     * @param color the color that we will set for both the start and the end of the border
     */
    public static void setBorderColor(RenderTooltipEvent.Color event, Color color) {
        event.setBorderStart(color.getRGB());
        event.setBorderEnd(color.getRGB());
    }

    /**
     * @param event the tooltip event that we will use to change the border's colors
     * @param start the color that we will set for the start of the border
     * @param end   the color that we will set for the end of the border
     */
    public static void setBorderColor(RenderTooltipEvent.Color event, Color start, Color end) {
        event.setBorderStart(start.getRGB());
        event.setBorderEnd(end.getRGB());
    }
}