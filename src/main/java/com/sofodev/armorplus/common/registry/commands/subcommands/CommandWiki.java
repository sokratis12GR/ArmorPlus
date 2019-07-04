/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.commands.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import static net.minecraft.util.text.TextFormatting.AQUA;
import static net.minecraft.util.text.TextFormatting.ITALIC;
import static net.minecraft.util.text.event.ClickEvent.Action.OPEN_URL;
import static net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT;

/**
 * @author Sokratis Fotkatzikis
 */
public class CommandWiki extends CommandSubBase {

    public CommandWiki() {
        super("wiki");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        String link = "https://ftb.gamepedia.com/ArmorPlus";
        Style wiki = new Style().setClickEvent(new ClickEvent(OPEN_URL, link)).setHoverEvent(new HoverEvent(SHOW_TEXT, new TextComponentTranslation("commands.armorplus.wiki.link_open")));
        sender.sendMessage(new TextComponentTranslation("commands.armorplus.wiki.link_details", String.format(" %s%s%s", AQUA.toString(), ITALIC.toString(), link)).setStyle(wiki));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}