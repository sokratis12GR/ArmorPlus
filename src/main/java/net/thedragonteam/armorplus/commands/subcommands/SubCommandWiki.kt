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
import net.thedragonteam.thedragonlib.util.TextUtils.errorText
import net.thedragonteam.thedragonlib.util.TextUtils.successText

class SubCommandWiki(parent: ICommand) : SubCommandBase(parent, "wiki") {

    override fun getArgUsage(commandSender: ICommandSender): String = successText("commands.wiki.usage")

    override fun getHelpText(): String = errorText("commands.wiki.help")

    override fun processSubCommand(server: MinecraftServer, cmdSender: ICommandSender, args: Array<String>) {
        super.processSubCommand(server, cmdSender, args)
        cmdSender.sendMessage(TextComponentString("Opening a link to the site:\n$AQUA${ITALIC}https://ftb.gamepedia.com/com/ArmorPlus"))
        ClientUtills.openLink("https://ftb.gamepedia.com/ArmorPlus")
        cmdSender.sendMessage(TextComponentString("Link opened successfully"))
    }
}