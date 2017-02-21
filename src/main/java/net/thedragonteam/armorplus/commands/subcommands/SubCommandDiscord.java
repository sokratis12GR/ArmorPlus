/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.thedragonteam.armorplus.commands.SubCommandBase;
import net.thedragonteam.thedragonlib.client.util.ClientUtills;
import net.thedragonteam.thedragonlib.util.TextHelper;

public class SubCommandDiscord extends SubCommandBase {

    public SubCommandDiscord(ICommand parent) {
        super(parent, "discord");
    }

    @Override
    public String getArgUsage(ICommandSender commandSender) {
        return TextHelper.localizeEffect("commands.discord.usage");
    }

    @Override
    public String getHelpText() {
        return TextHelper.localizeEffect("commands.discord.help");
    }

    @Override
    public void processSubCommand(MinecraftServer server, ICommandSender sender, String[] args) {
        super.processSubCommand(server, sender, args);
        sender.sendMessage(new TextComponentString("Opening an invite link to our TheDragonTeam Community Discord server"));
        ClientUtills.openLink("https://discord.gg/ZVwmqyx");
        sender.sendMessage(new TextComponentString("Link opened successfully"));
    }
}