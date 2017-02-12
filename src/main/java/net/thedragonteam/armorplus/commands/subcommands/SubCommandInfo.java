/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.commands.SubCommandBase;
import net.thedragonteam.thedragonlib.util.TextHelper;

public class SubCommandInfo extends SubCommandBase {

    public SubCommandInfo(ICommand parent) {
        super(parent, "info");
    }

    @Override
    public String getArgUsage(ICommandSender commandSender) {
        return TextHelper.localizeEffect("commands.info.usage");
    }

    @Override
    public String getHelpText() {
        return TextHelper.localizeEffect("commands.info.help");
    }

    @Override
    public void processSubCommand(MinecraftServer server, ICommandSender sender, String[] args) {
        super.processSubCommand(server, sender, args);
        sender.sendMessage(new TextComponentTranslation("command.armorplus.info_line_one", TextFormatting.DARK_RED, ArmorPlus.MODNAME, ArmorPlus.VERSION, TextFormatting.GOLD));
        sender.sendMessage(new TextComponentTranslation("command.armorplus.info_line_two", TextFormatting.RED, sender.getName(), ArmorPlus.MODNAME));
        sender.sendMessage(new TextComponentTranslation("command.armorplus.info_line_three", TextFormatting.GREEN, ArmorPlus.MODID));
        sender.sendMessage(new TextComponentTranslation("command.armorplus.info_line_four", TextFormatting.GRAY, server.getMinecraftVersion()));
        sender.sendMessage(new TextComponentTranslation("command.armorplus.info_line_five", TextFormatting.GRAY, APConfig.gameMode));
        sender.sendMessage(new TextComponentTranslation("command.armorplus.info_line_six", TextFormatting.GOLD));
        sender.sendMessage(new TextComponentTranslation("command.armorplus.info_line_seven", TextFormatting.GOLD));
    }
}