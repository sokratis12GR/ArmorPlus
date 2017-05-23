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
import net.thedragonteam.thedragonlib.util.TextUtils.errorText
import net.thedragonteam.thedragonlib.util.TextUtils.formattedText
import net.thedragonteam.thedragonlib.util.TextUtils.successText

/**
 * net.thedragonteam.armorplus.commands.subcommands
 * ArmorPlus created by sokratis12GR on 6/25/2016 9:59 AM.
 * - TheDragonTeam
 */
class SubCommandHelp(parent: ICommand) : SubCommandBase(parent, "help") {

    override fun getArgUsage(commandSender: ICommandSender): String = successText("commands.help.usage")

    override fun getHelpText(): String = errorText("commands.help.help")

    override fun processSubCommand(server: MinecraftServer, cmdSender: ICommandSender, args: Array<String>) {
        super.processSubCommand(server, cmdSender, args)

        if (args.isNotEmpty()) return

        (parentCommand as CommandArmorPlus).subCommands.values.forEach { cmdSender.sendMessage(TextComponentString(formattedText("commands.formatText.help", capitalizeFirstLetter(it.subCommandName), it.getArgUsage(cmdSender)))) }
    }
}