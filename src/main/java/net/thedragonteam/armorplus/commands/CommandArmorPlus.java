/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.thedragonteam.armorplus.commands.subcommands.SubCommandDiscord;
import net.thedragonteam.armorplus.commands.subcommands.SubCommandHelp;
import net.thedragonteam.armorplus.commands.subcommands.SubCommandInfo;
import net.thedragonteam.armorplus.commands.subcommands.SubCommandWiki;
import net.thedragonteam.thedragonlib.util.LogHelper;

import java.util.*;

import static net.minecraft.util.text.TextFormatting.RED;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;
import static net.thedragonteam.armorplus.util.TextUtils.setText;

public class CommandArmorPlus extends CommandBase {

    private ArrayList<String> aliases = new ArrayList<>();
    private HashMap<String, ISubCommand> subCommands = new HashMap<>();

    public CommandArmorPlus() {
        aliases.add("armorplus");
        aliases.add("arp");
        aliases.add("a+");

        subCommands.put("help", new SubCommandHelp(this));
        subCommands.put("info", new SubCommandInfo(this));
        subCommands.put("wiki", new SubCommandWiki(this));
        subCommands.put("discord", new SubCommandDiscord(this));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        return super.getTabCompletions(server, sender, args, targetPos);
    }

    @Override
    public String getName() {
        return "ap";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return getName() + " help";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        LogHelper.info(getRequiredPermissionLevel());
        if (args.length > 0 && subCommands.containsKey(args[0])) {
            ISubCommand subCommand = subCommands.get(args[0]);
            String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
            subCommand.processSubCommand(server, sender, subArgs);
        } else
            sender.sendMessage(setText(formattedText(RED, "commands.error.unknown")));
    }

    public Map<String, ISubCommand> getSubCommands() {
        return subCommands;
    }
}