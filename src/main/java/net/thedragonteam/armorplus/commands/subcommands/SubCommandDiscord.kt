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
import net.thedragonteam.thedragonlib.util.TextHelper.localizeEffect

class SubCommandDiscord(parent: ICommand) : SubCommandBase(parent, "discord") {

    override fun getArgUsage(commandSender: ICommandSender): String {
        return localizeEffect("commands.discord.usage")
    }

    override fun getHelpText(): String {
        return localizeEffect("commands.discord.help")
    }

    override fun processSubCommand(server: MinecraftServer, commandSender: ICommandSender, args: Array<String>) {
        super.processSubCommand(server, commandSender, args)
        commandSender.sendMessage(TextComponentString("Opening an invite link to our TheDragonTeam Community Discord server"))
        ClientUtills.openLink("https://discord.gg/ZVwmqyx")
        commandSender.sendMessage(TextComponentString("Link opened successfully"))
    }
}