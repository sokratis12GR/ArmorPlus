/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextFormatting.*
import net.thedragonteam.armorplus.APConfig.gameMode
import net.thedragonteam.armorplus.ArmorPlus.*
import net.thedragonteam.armorplus.commands.SubCommandBase
import net.thedragonteam.thedragonlib.util.TextUtils.errorText
import net.thedragonteam.thedragonlib.util.TextUtils.formatText
import net.thedragonteam.thedragonlib.util.TextUtils.successText

class SubCommandInfo(parent: ICommand) : SubCommandBase(parent, "info") {

    override fun getArgUsage(commandSender: ICommandSender): String = successText("commands.info.usage")

    override fun getHelpText(): String = errorText("commands.info.help")

    override fun processSubCommand(server: MinecraftServer, cmdSender: ICommandSender, args: Array<String>) {
        super.processSubCommand(server, cmdSender, args)
        cmdSender.sendMessage(formatText(DARK_RED, "command.armorplus.info_line_one.part_one.text", MODNAME, VERSION, formatText(GOLD, "command.armorplus.info_line_one.part_two.text")))
        cmdSender.sendMessage(formatText(RED, "command.armorplus.info_line_two.text", cmdSender.name, MODNAME))
        cmdSender.sendMessage(formatText(GREEN, "command.armorplus.info_line_three.text", MODID))
        cmdSender.sendMessage(formatText(GRAY, "command.armorplus.info_line_four.text", server.minecraftVersion))
        cmdSender.sendMessage(formatText(GRAY, "command.armorplus.info_line_five.text", gameMode))
        cmdSender.sendMessage(formatText(GOLD, "command.armorplus.info_line_six.text"))
        cmdSender.sendMessage(formatText(GOLD, "command.armorplus.info_line_seven.text"))
    }
}