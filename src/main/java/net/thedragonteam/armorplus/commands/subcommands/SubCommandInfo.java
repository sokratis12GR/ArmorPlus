/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.thedragonteam.armorplus.commands.SubCommandBase;
import net.thedragonteam.armorplus.util.TextUtils;

import static net.minecraft.util.text.TextFormatting.*;
import static net.thedragonteam.armorplus.ArmorPlus.*;
import static net.thedragonteam.armorplus.util.TextUtils.formatText;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class SubCommandInfo extends SubCommandBase {

    public SubCommandInfo(ICommand parent) {
        super(parent, "info");
    }

    @Override
    public String getArgUsage(ICommandSender commandSender) {
        return TextUtils.successText("commands.info.usage");
    }

    @Override
    public String getHelpText() {
        return TextUtils.errorText("commands.info.help");
    }

    public void processSubCommand(MinecraftServer server, ICommandSender cmdSender, String[] args) {
        super.processSubCommand(server, cmdSender, args);
        cmdSender.sendMessage(formatText(DARK_RED, "command.armorplus.info_line_one.part_one.text", MODNAME, VERSION, formatText(GOLD, "command.armorplus.info_line_one.part_two.text")));
        cmdSender.sendMessage(formatText(RED, "command.armorplus.info_line_two.text", cmdSender.getName(), MODNAME));
        cmdSender.sendMessage(formatText(GREEN, "command.armorplus.info_line_three.text", MODID));
        cmdSender.sendMessage(formatText(GRAY, "command.armorplus.info_line_four.text", server.getMinecraftVersion()));
        cmdSender.sendMessage(formatText(GOLD, "command.armorplus.info_line_six.text"));
        cmdSender.sendMessage(formatText(GOLD, "command.armorplus.info_line_seven.text"));
    }
}