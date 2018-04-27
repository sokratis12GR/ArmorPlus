/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.thedragonteam.thedragonlib.client.util.ClientUtills;

import static net.minecraft.util.text.TextFormatting.AQUA;
import static net.minecraft.util.text.TextFormatting.ITALIC;
import static net.thedragonteam.armorplus.util.TextUtils.setText;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CommandWiki extends CommandBase {

    @Override
    public String getName() {
        return "wiki";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.wiki.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        String link = "https://ftb.gamepedia.com/ArmorPlus";
        sender.sendMessage(setText("Opening a link to the site:\n" + AQUA + "" + ITALIC + link));
        ClientUtills.openLink(link);
        sender.sendMessage(setText("Link opened successfully"));
    }
}