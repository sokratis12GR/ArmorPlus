/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentString
import net.thedragonteam.armorplus.commands.SubCommandBase
import net.thedragonteam.thedragonlib.client.util.ClientUtills
import net.thedragonteam.thedragonlib.util.TextUtils.errorText
import net.thedragonteam.thedragonlib.util.TextUtils.successText

class SubCommandDiscord(parent: ICommand) : SubCommandBase(parent, "discord") {

    override fun getArgUsage(commandSender: ICommandSender): String = successText("commands.discord.usage")

    override fun getHelpText(): String = errorText("commands.discord.help")

    override fun processSubCommand(server: MinecraftServer, cmdSender: ICommandSender, args: Array<String>) {
        super.processSubCommand(server, cmdSender, args)
        cmdSender.sendMessage(TextComponentString("Opening an invite link to our TheDragonTeam Community Discord server"))
        ClientUtills.openLink("http://discord.thedragonteam.info")
        cmdSender.sendMessage(TextComponentString("Link opened successfully"))
    }
}