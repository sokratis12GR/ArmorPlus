/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.thedragonteam.armorplus.commands.CommandArmorPlus;
import net.thedragonteam.armorplus.commands.SubCommandBase;

import static net.thedragonteam.armorplus.util.TextUtils.*;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class SubCommandHelp extends SubCommandBase {

    public SubCommandHelp(ICommand parent) {
        super(parent, "help");
    }

    @Override
    public String getArgUsage(ICommandSender commandSender) {
        return successText("commands.help.usage");
    }

    @Override
    public String getHelpText() {
        return errorText("commands.help.help");
    }

    @Override
    public void processSubCommand(MinecraftServer server, ICommandSender cmdSender, String[] args) {
        super.processSubCommand(server, cmdSender, args);

        if (args.length > 0) return;

        ((CommandArmorPlus) getParentCommand()).getSubCommands().values().forEach(
            subCommand -> cmdSender.sendMessage(setText(formattedText("commands.format.help", capitalizeFirstLetter(subCommand.getSubCommandName()), subCommand.getArgUsage(cmdSender))))
        );
    }
}