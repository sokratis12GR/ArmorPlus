package sokratis12gr.armorplus.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import sokratis12gr.armorplus.commands.subcommands.SubCommandHelp;
import sokratis12gr.armorplus.commands.subcommands.SubCommandInfo;
import sokratis12gr.armorplus.commands.subcommands.SubCommandWiki;
import sokratis12gr.armorplus.util.TextHelper;

import javax.annotation.Nullable;
import java.util.*;

public class CommandArmorPlus extends CommandBase {

    private final List<String> aliases = new ArrayList<String>();
    private final Map<String, ISubCommand> subCommands = new HashMap<String, ISubCommand>();

    public CommandArmorPlus() {
        aliases.add("armorplus");

        subCommands.put("help", new SubCommandHelp(this));
        subCommands.put("info", new SubCommandInfo(this));
        subCommands.put("wiki", new SubCommandWiki(this));
    }

    @Override
    public String getCommandName() {
        return "/armorplus";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canCommandSenderUseCommand(this.getRequiredPermissionLevel(), this.getCommandName());
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        return super.getTabCompletionOptions(server, sender, args, pos);
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return getCommandName() + " help";
    }

    @Override
    public List<String> getCommandAliases() {
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        System.out.println(getRequiredPermissionLevel());
        if (args.length > 0 && subCommands.containsKey(args[0])) {

            ISubCommand subCommand = subCommands.get(args[0]);
            String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
            subCommand.processSubCommand(server, sender, subArgs);
        } else {
            sender.addChatMessage(new TextComponentString(TextHelper.localizeEffect("commands.error.unknown")));
        }
    }

    public Map<String, ISubCommand> getSubCommands() {
        return subCommands;
    }
}