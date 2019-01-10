/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.commands.subcommands;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.util.TextUtils;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;

import static net.minecraft.util.text.TextFormatting.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class CommandInfo extends CommandSubBase {

    public CommandInfo() {
        super("info");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        sendMessages(sender,
            TextUtils.formatText(DARK_RED, "commands.armorplus.info_line_one.part_one.text", ArmorPlus.MODNAME, ArmorPlus.VERSION, TextUtils.formatText(GOLD, "commands.armorplus.info_line_one.part_two.text")),
            TextUtils.formatText(RED, "commands.armorplus.info_line_two.text", sender.getName(), ArmorPlus.MODNAME),
            TextUtils.formatText(GREEN, "commands.armorplus.info_line_three.text", ArmorPlus.MODID),
            TextUtils.formatText(GRAY, "commands.armorplus.info_line_four.text", server.getMinecraftVersion()),
            TextUtils.formatText(GOLD, "commands.armorplus.info_line_six.text"),
            TextUtils.formatText(GOLD, "commands.armorplus.info_line_seven.text")
        );
    }

    private void sendMessages(ICommandSender sender, ITextComponent... messages) {
        Arrays.stream(messages).forEach(sender::sendMessage);
    }
}