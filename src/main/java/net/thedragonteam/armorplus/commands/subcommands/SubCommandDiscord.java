/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.thedragonteam.armorplus.commands.SubCommandBase;
import net.thedragonteam.thedragonlib.client.util.ClientUtills;

import static net.thedragonteam.armorplus.util.TextUtils.*;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class SubCommandDiscord extends SubCommandBase {

    public SubCommandDiscord(ICommand parent) {
        super(parent, "discord");
    }

    @Override
    public String getArgUsage(ICommandSender commandSender) {
        return successText("commands.discord.usage");
    }

    @Override
    public String getHelpText() {
        return errorText("commands.discord.help");
    }

    @Override
    public void processSubCommand(MinecraftServer server, ICommandSender cmdSender, String[] args) {
        super.processSubCommand(server, cmdSender, args);
        cmdSender.sendMessage(setText("Opening an invite link to our TheDragonTeam Community Discord server"));
        ClientUtills.openLink("http://discord.thedragonteam.info");
        cmdSender.sendMessage(setText("Link opened successfully"));
    }
}