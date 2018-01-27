/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import static net.minecraft.util.text.TextFormatting.*;
import static net.thedragonteam.armorplus.ArmorPlus.*;
import static net.thedragonteam.armorplus.util.TextUtils.formatText;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CommandInfo extends CommandBase {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.info.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        sender.sendMessage(formatText(DARK_RED, "command.armorplus.info_line_one.part_one.text", MODNAME, VERSION, formatText(GOLD, "command.armorplus.info_line_one.part_two.text")));
        sender.sendMessage(formatText(RED, "command.armorplus.info_line_two.text", sender.getName(), MODNAME));
        sender.sendMessage(formatText(GREEN, "command.armorplus.info_line_three.text", MODID));
        sender.sendMessage(formatText(GRAY, "command.armorplus.info_line_four.text", server.getMinecraftVersion()));
        sender.sendMessage(formatText(GOLD, "command.armorplus.info_line_six.text"));
        sender.sendMessage(formatText(GOLD, "command.armorplus.info_line_seven.text"));
    }
}