/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;

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
        sendMessages(sender,
            formatText(DARK_RED, "command.armorplus.info_line_one.part_one.text", MODNAME, VERSION, formatText(GOLD, "command.armorplus.info_line_one.part_two.text")),
            formatText(RED, "command.armorplus.info_line_two.text", sender.getName(), MODNAME),
            formatText(GREEN, "command.armorplus.info_line_three.text", MODID),
            formatText(GRAY, "command.armorplus.info_line_four.text", server.getMinecraftVersion()),
            formatText(GOLD, "command.armorplus.info_line_six.text"),
            formatText(GOLD, "command.armorplus.info_line_seven.text")
        );
    }

    private void sendMessages(ICommandSender sender, ITextComponent... messages) {
        Arrays.stream(messages).forEach(sender::sendMessage);
    }
}