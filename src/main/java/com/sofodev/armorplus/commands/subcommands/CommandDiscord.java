/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.commands.subcommands;

import com.sofodev.armorplus.util.TextUtils;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.thedragonteam.thedragonlib.client.util.ClientUtills;

/**
 * @author Sokratis Fotkatzikis
 */
public class CommandDiscord extends CommandSubBase {

    public CommandDiscord() {
        super("discord");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        sender.sendMessage(TextUtils.setTextTranslation("commands.armorplus.discord.help"));
        ClientUtills.openLink("http://discord.gg/tmFPHb2");
        sender.sendMessage(TextUtils.setTextTranslation("Link opened successfully"));
    }
}