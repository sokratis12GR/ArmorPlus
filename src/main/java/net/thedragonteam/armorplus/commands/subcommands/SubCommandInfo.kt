/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.text.TextFormatting
import net.thedragonteam.armorplus.APConfig
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.commands.SubCommandBase
import net.thedragonteam.thedragonlib.util.TextHelper

class SubCommandInfo(parent: ICommand) : SubCommandBase(parent, "info") {

    override fun getArgUsage(commandSender: ICommandSender): String {
        return TextHelper.localizeEffect("commands.info.usage")
    }

    override fun getHelpText(): String {
        return TextHelper.localizeEffect("commands.info.help")
    }

    override fun processSubCommand(server: MinecraftServer, commandSender: ICommandSender, args: Array<String>) {
        super.processSubCommand(server, commandSender, args)
        commandSender.sendMessage(TextComponentTranslation("command.armorplus.info_line_one", TextFormatting.DARK_RED, ArmorPlus.MODNAME, ArmorPlus.VERSION, TextFormatting.GOLD))
        commandSender.sendMessage(TextComponentTranslation("command.armorplus.info_line_two", TextFormatting.RED, commandSender.name, ArmorPlus.MODNAME))
        commandSender.sendMessage(TextComponentTranslation("command.armorplus.info_line_three", TextFormatting.GREEN, ArmorPlus.MODID))
        commandSender.sendMessage(TextComponentTranslation("command.armorplus.info_line_four", TextFormatting.GRAY, server.minecraftVersion))
        commandSender.sendMessage(TextComponentTranslation("command.armorplus.info_line_five", TextFormatting.GRAY, APConfig.gameMode))
        commandSender.sendMessage(TextComponentTranslation("command.armorplus.info_line_six", TextFormatting.GOLD))
        commandSender.sendMessage(TextComponentTranslation("command.armorplus.info_line_seven", TextFormatting.GOLD))
    }
}