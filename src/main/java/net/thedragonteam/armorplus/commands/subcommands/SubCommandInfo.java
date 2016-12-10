/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
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
        sender.sendMessage(new TextComponentString(TextFormatting.DARK_RED + "[" + ArmorPlus.MODNAME + " (" + ArmorPlus.VERSION + ") " + "by" + TextFormatting.GOLD + " sokratis12GR (TheDragonTeam)]"));
        sender.sendMessage(new TextComponentString(TextFormatting.RED + "[Thank You " + sender.getName() + " for using " + ArmorPlus.MODNAME + "]"));
        sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "[" + "modid: " + ArmorPlus.MODID + "]"));
        sender.sendMessage(new TextComponentString(TextFormatting.GRAY + "[Minecraft Version: " + server.getMinecraftVersion() + "]"));
        sender.sendMessage(new TextComponentString(TextFormatting.GRAY + "[ArmorPlus recipe difficulty: " + APConfig.recipes + "]"));
        sender.sendMessage(new TextComponentTranslation(TextFormatting.GOLD + "[Recipes] " + "use command /armorplus wiki"));
    }
}