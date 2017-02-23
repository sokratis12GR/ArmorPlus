/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentString
import net.thedragonteam.armorplus.commands.CommandArmorPlus
import net.thedragonteam.armorplus.commands.SubCommandBase
import net.thedragonteam.thedragonlib.util.TextHelper

/**
 * net.thedragonteam.armorplus.commands.subcommands
 * ArmorPlus created by sokratis12GR on 6/25/2016 9:59 AM.
 * - TheDragonTeam
 */
class SubCommandHelp(parent: ICommand) : SubCommandBase(parent, "help") {

    override fun getArgUsage(commandSender: ICommandSender): String {
        return TextHelper.localize("commands.help.usage")
    }

    override fun getHelpText(): String {
        return TextHelper.localizeEffect("commands.help.help")
    }

    override fun processSubCommand(server: MinecraftServer, commandSender: ICommandSender, args: Array<String>) {
        super.processSubCommand(server, commandSender, args)

        if (args.isNotEmpty()) return

        for (subCommand in (parentCommand as CommandArmorPlus).subCommands.values)
            commandSender.sendMessage(TextComponentString(TextHelper.localizeEffect("commands.format.help", capitalizeFirstLetter(subCommand.subCommandName), subCommand.getArgUsage(commandSender))))
    }
}