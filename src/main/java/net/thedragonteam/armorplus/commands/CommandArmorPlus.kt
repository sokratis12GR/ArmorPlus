/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands

import net.minecraft.command.CommandBase
import net.minecraft.command.CommandException
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentString
import net.thedragonteam.armorplus.commands.subcommands.SubCommandDiscord
import net.thedragonteam.armorplus.commands.subcommands.SubCommandHelp
import net.thedragonteam.armorplus.commands.subcommands.SubCommandInfo
import net.thedragonteam.armorplus.commands.subcommands.SubCommandWiki
import net.thedragonteam.thedragonlib.util.TextHelper.localizeEffect
import java.util.*

class CommandArmorPlus : CommandBase() {

    private val aliases = ArrayList<String>()
    val subCommands = HashMap<String, ISubCommand>()

    init {
        aliases.add("armorplus")
        aliases.add("arp")
        aliases.add("a+")

        subCommands.put("help", SubCommandHelp(this))
        subCommands.put("info", SubCommandInfo(this))
        subCommands.put("wiki", SubCommandWiki(this))
        subCommands.put("discord", SubCommandDiscord(this))
    }

    override fun checkPermission(server: MinecraftServer?, sender: ICommandSender): Boolean {
        return sender is EntityPlayer
    }

    override fun getAliases(): List<String> {
        return Arrays.asList("armorplus", "arp", "a+")
    }

    override fun getName(): String {
        return "ap"
    }

    override fun getTabCompletions(server: MinecraftServer?, sender: ICommandSender?, args: Array<String>?, pos: BlockPos?): List<String> {
        return super.getTabCompletions(server, sender, args, pos)
    }

    override fun getUsage(sender: ICommandSender): String {
        return name + " help"
    }

    @Throws(CommandException::class)
    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        println(requiredPermissionLevel)
        if (args.isNotEmpty() && subCommands.containsKey(args[0])) {
            val subCommand = subCommands[args[0]]
            val subArgs = Arrays.copyOfRange(args, 1, args.size)
            subCommand?.processSubCommand(server, sender, subArgs)
        } else
            sender.sendMessage(TextComponentString(localizeEffect("commands.error.unknown")))
    }

    fun getSubCommands(): Map<String, ISubCommand> {
        return subCommands
    }
}