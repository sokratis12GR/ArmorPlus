/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.commands.subcommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;

import static com.sofodev.armorplus.common.util.TextUtils.translate;
import static net.minecraft.util.text.TextFormatting.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class CommandInfo {

    public static int execute(CommandSource sender) {
        sendMessages(sender,
            translate(DARK_RED, "commands.armorplus.info_line_one.part_one.text", ArmorPlus.MODNAME, ArmorPlus.VERSION, translate(GOLD, "commands.armorplus.info_line_one.part_two.text")),
            translate(RED, "commands.armorplus.info_line_two.text", sender.getName(), ArmorPlus.MODNAME),
            translate(GREEN, "commands.armorplus.info_line_three.text", ArmorPlus.MODID),
            translate(GRAY, "commands.armorplus.info_line_four.text", sender.getServer().getMinecraftVersion()),
            translate(GOLD, "commands.armorplus.info_line_six.text"),
            translate(GOLD, "commands.armorplus.info_line_seven.text")
        );
        return Command.SINGLE_SUCCESS;
    }

    private static void sendMessages(CommandSource sender, ITextComponent... messages) {
        Arrays.stream(messages).forEach(message -> sender.sendFeedback(message, false));
    }
}