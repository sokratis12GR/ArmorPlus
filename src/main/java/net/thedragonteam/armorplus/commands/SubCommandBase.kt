/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.thedragonteam.thedragonlib.util.TextUtils.formattedText
import net.thedragonteam.thedragonlib.util.TextUtils.setText
import java.util.*

/**
 * net.thedragonteam.armorplus.commands
 * ArmorPlus created by sokratis12GR on 6/25/2016 9:54 AM.
 * - TheDragonTeam
 */
open class SubCommandBase(override val parentCommand: ICommand, override val subCommandName: String) : ISubCommand {

    override fun getHelpText(): String = ""

    override fun getArgUsage(commandSender: ICommandSender): String = ""

    override fun processSubCommand(server: MinecraftServer, cmdSender: ICommandSender, args: Array<String>) {}

    protected fun capitalizeFirstLetter(toCapital: String): String = toCapital[0].toString().toUpperCase(Locale.ENGLISH) + toCapital.substring(1)

    companion object {

        fun displayHelpString(commandSender: ICommandSender, display: String, vararg info: Any) = commandSender.sendMessage(setText(formattedText(display, *info)))

        fun displayErrorString(commandSender: ICommandSender, display: String, vararg info: Any) = commandSender.sendMessage(setText(formattedText(display, *info)))

        fun displaySuccessString(commandSender: ICommandSender, display: String, vararg info: Any) = commandSender.sendMessage(setText(formattedText(display, *info)))
    }
}