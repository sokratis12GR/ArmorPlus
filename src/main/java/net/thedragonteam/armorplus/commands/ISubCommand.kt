/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer

/**
 * net.thedragonteam.armorplus.commands
 * ArmorPlus created by sokratis12GR on 6/25/2016 9:53 AM.
 * - TheDragonTeam
 */
interface ISubCommand {

    val subCommandName: String

    val parentCommand: ICommand

    fun getArgUsage(commandSender: ICommandSender): String

    fun getHelpText(): String

    fun processSubCommand(server: MinecraftServer, commandSender: ICommandSender, args: Array<String>)
}