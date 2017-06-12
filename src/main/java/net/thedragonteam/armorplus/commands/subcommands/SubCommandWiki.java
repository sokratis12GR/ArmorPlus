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

public class SubCommandWiki extends SubCommandBase {

    public SubCommandWiki(ICommand parent) {
        super(parent, "wiki");
    }

    @Override
    public String getArgUsage(ICommandSender commandSender) {
        return successText("commands.wiki.usage");
    }

    @Override
    public String getHelpText() {
        return errorText("commands.wiki.help");
    }


    public void processSubCommand(MinecraftServer server, ICommandSender cmdSender, String[] args) {
        super.processSubCommand(server, cmdSender, args);
        cmdSender.sendMessage(setText("Opening a link to the site:\n$AQUA${ITALIC}https://ftb.gamepedia.com/com/ArmorPlus"));
        ClientUtills.openLink("https://ftb.gamepedia.com/ArmorPlus");
        cmdSender.sendMessage(setText("Link opened successfully"));
    }
}