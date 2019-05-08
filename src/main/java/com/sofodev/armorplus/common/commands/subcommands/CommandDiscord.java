/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.commands.subcommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import static java.lang.String.format;
import static net.minecraft.util.text.TextFormatting.AQUA;
import static net.minecraft.util.text.TextFormatting.ITALIC;
import static net.minecraft.util.text.event.ClickEvent.Action.OPEN_URL;
import static net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT;

/**
 * @author Sokratis Fotkatzikis
 */
public class CommandDiscord {

    public static int execute(CommandSource sender) {
        String tdtDiscord = "https://discord.gg/ZVwmqyx";
        String s12GRDiscord = "https://discord.gg/tmFPHb2";
        Style tdt = new Style().setClickEvent(new ClickEvent(OPEN_URL, tdtDiscord)).setHoverEvent(new HoverEvent(SHOW_TEXT, new TextComponentTranslation("commands.armorplus.discord.line_one.hover")));
        Style s12GR = new Style().setClickEvent(new ClickEvent(OPEN_URL, tdtDiscord)).setHoverEvent(new HoverEvent(SHOW_TEXT, new TextComponentTranslation("commands.armorplus.discord.line_two.hover")));
        sender.sendFeedback(new TextComponentTranslation("commands.armorplus.discord.line_one").appendText(format(" %s%s%s", AQUA.toString(), ITALIC.toString(), tdtDiscord)).setStyle(tdt), true);
        sender.sendFeedback(new TextComponentTranslation("commands.armorplus.discord.line_two").appendText(format(" %s%s%s", AQUA.toString(), ITALIC.toString(), s12GRDiscord)).setStyle(s12GR), true);
        return Command.SINGLE_SUCCESS;
    }
}