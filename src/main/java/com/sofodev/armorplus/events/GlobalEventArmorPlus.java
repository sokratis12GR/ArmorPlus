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
        if (rp.contains("lava") && rp.contains("obsidian")) {
            event.setBorderStart(gold.getRGB());
            event.setBorderEnd(darkPurple.getRGB());
        } else if (match(rl, "coal")) {
            setBorderColor(event, Color.GRAY);
        } else if (match(rl, "lapis")) {
            setBorderColor(event, Color.BLUE);
        } else if (match(rl, "redstone", "ardite", "high_tech_bench")) {
            setBorderColor(event, Color.RED);
        } else if (match(rl, "emerald")) {
            setBorderColor(event, Color.GREEN);
        } else if (match(rl, "obsidian")) {
            setBorderColor(event, darkPurple);
        } else if (match(rl, "lava", "champion_bench")) {
            setBorderColor(event, gold);
        } else if (match(rl, "Guardian", "guardian", "chicken")) {
            setBorderColor(event, Color.CYAN);
        } else if (match(rl, "Wither", "super_star")) {
            setBorderColor(event, Color.WHITE);
        } else if (match(rl, "Ender Dragon", "ender_dragon", "manyullyn", "knight_slime")) {
            setBorderColor(event, purple);
        } else if (match(rl, "Ultimate", "ultimate", "slime", "ulti_tech_bench")) {
            setBorderColor(event, lightGreen);
        } else if (match(rl, "cobalt", "workbench")) {
            setBorderColor(event, lightBlue);
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