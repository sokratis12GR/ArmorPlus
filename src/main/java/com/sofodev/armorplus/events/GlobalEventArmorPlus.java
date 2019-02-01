/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.events;

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
        } else if (match(rl, "Coal", "coal")) {
            setBorderColor(event, GRAY);
        } else if (match(rl, "Lapis", "lapis", "blue")) {
            setBorderColor(event, BLUE);
        } else if (match(rl, "Redstone", "redstone", "ardite", "high_tech_bench", "red")) {
            setBorderColor(event, RED);
        } else if (match(rl, "Emerald", "emerald", "green")) {
            setBorderColor(event, GREEN);
        } else if (match(rl, "obsidian")) {
            setBorderColor(event, darkPurple);
        } else if (match(rl, "lava", "champion_bench", "lava")) {
            setBorderColor(event, gold);
        } else if (match(rl, "Guardian", "guardian", "chicken")) {
            setBorderColor(event, CYAN);
        } else if (match(rl, "Wither", "wither", "super_star", "white")) {
            setBorderColor(event, WHITE);
        } else if (match(rl, "Ender Dragon", "ender_dragon", "manyullyn", "knight_slime", "purple")) {
            setBorderColor(event, purple);
        } else if (match(rl, "Ultimate", "ultimate", "slime", "ulti_tech_bench")) {
            setBorderColor(event, lightGreen);
        } else if (match(rl, "Cobalt", "cobalt", "workbench")) {
            setBorderColor(event, lightBlue);
        } else if (match(rl, "Yellow", "yellow")) {
            setBorderColor(event, YELLOW);
        } else if (match(rl, "Black", "black")) {
            setBorderColor(event, BLACK);
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

    public static void setBorderColor(RenderTooltipEvent.Color event, Color color) {
        event.setBorderStart(color.getRGB());
        event.setBorderEnd(color.getRGB());
    }
}