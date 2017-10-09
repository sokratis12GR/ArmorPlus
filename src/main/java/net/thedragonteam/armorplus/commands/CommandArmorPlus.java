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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Arrays.copyOfRange;
import static net.minecraft.util.text.TextFormatting.RED;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;
import static net.thedragonteam.armorplus.util.TextUtils.setText;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CommandArmorPlus extends CommandBase {

    private ArrayList<String> aliases = new ArrayList<>();
    private HashMap<String, ISubCommand> subCommands = new HashMap<>();
    private ArrayList<ISubCommand> subCommandsList = new ArrayList<>();
    private ArrayList<String> subCommandNames = new ArrayList<>();

    public CommandArmorPlus() {
        aliases.addAll(asList("armorplus", "arp", "a+", "armor+"));
        setSubCommandNames("help", "info", "wiki", "discord");
        setSubCommands(new SubCommandHelp(this), new SubCommandInfo(this), new SubCommandWiki(this), new SubCommandDiscord(this));
        IntStream.range(0, subCommandsList.size()).forEachOrdered(
            i -> subCommands.put(subCommandNames.get(i), subCommandsList.get(i))
        );
    }

    private void setSubCommandNames(String... names) {
        IntStream.range(0, names.length).forEachOrdered(i -> subCommandNames.add(i, names[i]));
    }

    private void setSubCommands(ISubCommand... commands) {
        IntStream.range(0, commands.length).forEachOrdered(i -> subCommandsList.add(i, commands[i]));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer;
    }

    @NotNull
    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @NotNull
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
            String[] subArgs = copyOfRange(args, 1, args.length);
            subCommand.processSubCommand(server, sender, subArgs);
        } else {
            sender.sendMessage(setText(formattedText(RED, "commands.error.unknown")));
        }
    }

    public Map<String, ISubCommand> getSubCommands() {
        return subCommands;
    }
}