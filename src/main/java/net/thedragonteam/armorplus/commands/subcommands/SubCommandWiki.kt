/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextFormatting.AQUA
import net.minecraft.util.text.TextFormatting.ITALIC
import net.thedragonteam.armorplus.commands.SubCommandBase
import net.thedragonteam.thedragonlib.client.util.ClientUtills
import net.thedragonteam.thedragonlib.util.TextHelper

class SubCommandWiki(parent: ICommand) : SubCommandBase(parent, "wiki") {

    override fun getArgUsage(commandSender: ICommandSender): String {
        return TextHelper.localizeEffect("commands.wiki.usage")
    }

    override fun getHelpText(): String {
        return TextHelper.localizeEffect("commands.wiki.help")
    }

    override fun processSubCommand(server: MinecraftServer, commandSender: ICommandSender, args: Array<String>) {
        super.processSubCommand(server, commandSender, args)
        commandSender.sendMessage(TextComponentString("Opening a link to the site:\n$AQUA${ITALIC}https://ftb.gamepedia.com/com/ArmorPlus"))
        ClientUtills.openLink("https://ftb.gamepedia.com/ArmorPlus")
        commandSender.sendMessage(TextComponentString("Link opened successfully"))
    }
}