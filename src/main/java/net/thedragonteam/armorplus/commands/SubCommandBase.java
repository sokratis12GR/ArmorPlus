/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.util.Locale;

import static net.thedragonteam.armorplus.util.TextUtils.formattedText;
import static net.thedragonteam.armorplus.util.TextUtils.setText;

/**
 * net.thedragonteam.armorplus.commands
 * ArmorPlus created by sokratis12GR on 6/25/2016 9:54 AM.
 * - TheDragonTeam
 */
public class SubCommandBase implements ISubCommand {

    private String subCommandName;
    private ICommand parentCommand;

    public SubCommandBase(ICommand parentCommand, String subCommandName) {
        this.subCommandName = subCommandName;
        this.parentCommand = parentCommand;
    }

    protected String capitalizeFirstLetter(String toCapital) {
        return toCapital.toUpperCase(Locale.ENGLISH) + toCapital.substring(1);
    }

    public void displayHelpString(ICommandSender commandSender, String display, Object... info) {
        commandSender.sendMessage(setText(formattedText(display, info)));
    }

    public void displayErrorString(ICommandSender commandSender, String display, Object... info) {
        commandSender.sendMessage(setText(formattedText(display, info)));
    }

    public void displaySuccessString(ICommandSender commandSender, String display, Object... info) {
        commandSender.sendMessage(setText(formattedText(display, info)));
    }

    @Override
    public String getSubCommandName() {
        return subCommandName;
    }

    @Override
    public ICommand getParentCommand() {
        return parentCommand;
    }

    @Override
    public String getArgUsage(ICommandSender commandSender) {
        return "";
    }

    @Override
    public String getHelpText() {
        return "";
    }

    @Override
    public void processSubCommand(MinecraftServer server, ICommandSender cmdSender, String[] args) {

    }
}