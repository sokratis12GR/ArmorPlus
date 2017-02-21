/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

/**
 * net.thedragonteam.armorplus.commands
 * ArmorPlus created by sokratis12GR on 6/25/2016 9:53 AM.
 * - TheDragonTeam
 */
public interface ISubCommand {

    String getSubCommandName();

    ICommand getParentCommand();

    String getArgUsage(ICommandSender commandSender);

    String getHelpText();

    void processSubCommand(MinecraftServer server, ICommandSender commandSender, String[] args);
}