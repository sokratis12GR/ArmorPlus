/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.events;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

import static java.awt.Color.*;

/**
 * @author Sokratis Fotkatzikis
 */
@EventBusSubscriber(modid = ArmorPlus.MODID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class GlobalEventArmorPlus {

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
        if ((rp.contains("lava") && rp.contains("obsidian")) || rp.contains("lava") && rp.contains("infuser")) {
            event.setBorderStart(gold.getRGB());
            event.setBorderEnd(darkPurple.getRGB());
        }
        setBorder(event, GRAY, rl, "Coal", "coal");
        setBorder(event, BLUE, rl, "Lapis", "lapis", "blue");
        setBorder(event, RED, rl, "Redstone", "redstone", "ardite", "high_tech_bench", "red");
        setBorder(event, GREEN, rl, "Emerald", "emerald", "green");
        setBorder(event, darkPurple, rl, "Obsidian", "obsidian");
        setBorder(event, gold, rl, "Lava", "champion_bench", "lava");
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
}