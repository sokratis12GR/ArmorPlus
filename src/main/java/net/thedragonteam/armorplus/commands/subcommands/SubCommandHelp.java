/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.thedragonteam.armorplus.commands.CommandArmorPlus;
import net.thedragonteam.armorplus.commands.ISubCommand;
import net.thedragonteam.armorplus.commands.SubCommandBase;
import net.thedragonteam.thedragonlib.util.TextHelper;

/**
 * net.thedragonteam.armorplus.commands.subcommands
 * ArmorPlus created by sokratis12GR on 6/25/2016 9:59 AM.
 * - TheDragonTeam
 */
public class SubCommandHelp extends SubCommandBase {

    public SubCommandHelp(ICommand parent) {
        super(parent, "help");
    }

    @Override
    public String getArgUsage(ICommandSender commandSender) {
        return TextHelper.localize("commands.help.usage");
    }

    @Override
    public String getHelpText() {
        return TextHelper.localizeEffect("commands.help.help");
    }

    @Override
    public void processSubCommand(MinecraftServer server, ICommandSender commandSender, String[] args) {
        super.processSubCommand(server, commandSender, args);

        if (args.length > 0) return;

        for (ISubCommand subCommand : ((CommandArmorPlus) getParentCommand()).getSubCommands().values())
            commandSender.sendMessage(new TextComponentString(TextHelper.localizeEffect("commands.format.help", capitalizeFirstLetter(subCommand.getSubCommandName()), subCommand.getArgUsage(commandSender))));
    }
}