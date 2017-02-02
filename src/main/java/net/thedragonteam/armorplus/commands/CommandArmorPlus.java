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
import net.minecraft.util.text.TextComponentString;
import net.thedragonteam.armorplus.commands.subcommands.SubCommandHelp;
import net.thedragonteam.armorplus.commands.subcommands.SubCommandInfo;
import net.thedragonteam.armorplus.commands.subcommands.SubCommandWiki;
import net.thedragonteam.thedragonlib.util.TextHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class CommandArmorPlus extends CommandBase {

    private final List<String> aliases = new ArrayList<>();
    private final Map<String, ISubCommand> subCommands = new HashMap<>();

    public CommandArmorPlus() {
        aliases.add("armorplus");
        aliases.add("arp");
        aliases.add("a+");

        subCommands.put("help", new SubCommandHelp(this));
        subCommands.put("info", new SubCommandInfo(this));
        subCommands.put("wiki", new SubCommandWiki(this));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }

    @Override
    @Nonnull
    public List<String> getAliases() {
        return Arrays.asList("armorplus", "arp", "a+");
    }

    @Override
    @Nonnull
    public String getName() {
        return "ap";
    }

    @Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        return super.getTabCompletions(server, sender, args, pos);
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return getName() + " help";
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        System.out.println(getRequiredPermissionLevel());
        if (args.length > 0 && subCommands.containsKey(args[0])) {
            ISubCommand subCommand = subCommands.get(args[0]);
            String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
            subCommand.processSubCommand(server, sender, subArgs);
        } else sender.sendMessage(new TextComponentString(TextHelper.localizeEffect("commands.error.unknown")));
    }

    public Map<String, ISubCommand> getSubCommands() {
        return subCommands;
    }
}