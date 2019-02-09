/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.commands.subcommands;

import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;

import static com.sofodev.armorplus.util.TextUtils.translate;
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
            translate(DARK_RED, "commands.armorplus.info_line_one.part_one.text", ArmorPlus.MODNAME, ArmorPlus.VERSION, translate(GOLD, "commands.armorplus.info_line_one.part_two.text")),
            translate(RED, "commands.armorplus.info_line_two.text", sender.getName(), ArmorPlus.MODNAME),
            translate(GREEN, "commands.armorplus.info_line_three.text", ArmorPlus.MODID),
            translate(GRAY, "commands.armorplus.info_line_four.text", server.getMinecraftVersion()),
            translate(GOLD, "commands.armorplus.info_line_six.text"),
            translate(GOLD, "commands.armorplus.info_line_seven.text")
        );
    }

    private void sendMessages(ICommandSender sender, ITextComponent... messages) {
        Arrays.stream(messages).forEach(sender::sendMessage);
    }
}