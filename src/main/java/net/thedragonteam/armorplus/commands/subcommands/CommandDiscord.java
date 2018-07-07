/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.thedragonteam.thedragonlib.client.util.ClientUtills;

import static net.thedragonteam.armorplus.util.TextUtils.setText;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CommandDiscord extends CommandSubBase {

    public CommandDiscord() {
        super("discord");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        sender.sendMessage(setText("Opening an invite link to our TheDragonTeam Community Discord server"));
        ClientUtills.openLink("http://discord.thedragonteam.info");
        sender.sendMessage(setText("Link opened successfully"));
    }
}