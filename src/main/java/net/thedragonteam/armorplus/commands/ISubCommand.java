package net.thedragonteam.armorplus.commands;

/**
 * sokratis12GR.ArmorPlus.commands
 * ArmorPlus created by sokratis12GR on 6/25/2016 9:53 AM.
 */

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public interface ISubCommand {

    String getSubCommandName();

    ICommand getParentCommand();

    String getArgUsage(ICommandSender commandSender);

    String getHelpText();

    int getRequiredPermissionLevel();

    void processSubCommand(MinecraftServer server, ICommandSender commandSender, String[] args);
}