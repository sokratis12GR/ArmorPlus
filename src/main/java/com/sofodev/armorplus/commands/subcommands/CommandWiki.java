/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.commands.subcommands;

import com.sofodev.armorplus.util.TextUtils;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.thedragonteam.thedragonlib.client.util.ClientUtills;

import static net.minecraft.util.text.TextFormatting.AQUA;
import static net.minecraft.util.text.TextFormatting.ITALIC;

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
        sender.sendMessage(TextUtils.setText("Opening a link to the site:\n" + AQUA + "" + ITALIC + link));
        ClientUtills.openLink(link);
        sender.sendMessage(TextUtils.setText("Link opened successfully"));
    }
}