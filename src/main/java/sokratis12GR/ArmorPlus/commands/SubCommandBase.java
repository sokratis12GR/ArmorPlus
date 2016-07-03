package sokratis12GR.ArmorPlus.commands;

/**
 * sokratis12GR.ArmorPlus.commands.subcommands
 * ArmorPlus created by sokratis12GR on 6/25/2016 9:54 AM.
 */

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.util.Locale;

public class SubCommandBase implements ISubCommand {

    private ICommand parent;
    private String name;

    public SubCommandBase(ICommand parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public String getHelpText() {
        return null;
    }

    @Override
    public String getArgUsage(ICommandSender commandSender) {
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getSubCommandName() {
        return name;
    }

    @Override
    public ICommand getParentCommand() {
        return parent;
    }

    @Override
    public void processSubCommand(MinecraftServer server, ICommandSender commandSender, String[] args) {
    }

    protected String capitalizeFirstLetter(String toCapital) {
        return String.valueOf(toCapital.charAt(0)).toUpperCase(Locale.ENGLISH) + toCapital.substring(1);
    }

    public static void displayHelpString(ICommandSender commandSender, String display, Object... info) {
        commandSender.addChatMessage(new TextComponentString(TextHelper.localizeEffect(display, info)));
    }

    public static void displayErrorString(ICommandSender commandSender, String display, Object... info) {
        commandSender.addChatMessage(new TextComponentString(TextHelper.localizeEffect(display, info)));
    }

    public static void displaySuccessString(ICommandSender commandSender, String display, Object... info) {
        commandSender.addChatMessage(new TextComponentString(TextHelper.localizeEffect(display, info)));
    }
}