/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.commands.SubCommandBase;
import net.thedragonteam.thedragonlib.client.util.ClientUtills;
import net.thedragonteam.thedragonlib.util.TextHelper;

public class SubCommandWiki extends SubCommandBase {

    public SubCommandWiki(ICommand parent) {
        super(parent, "wiki");
    }

    @Override
    public String getArgUsage(ICommandSender commandSender) {
        return TextHelper.localizeEffect("commands.wiki.usage");
    }

    @Override
    public String getHelpText() {
        return TextHelper.localizeEffect("commands.wiki.help");
    }

    @Override
    public void processSubCommand(MinecraftServer server, ICommandSender sender, String[] args) {
        super.processSubCommand(server, sender, args);
        sender.sendMessage(new TextComponentString("Opening a link to the site:\n" + TextFormatting.AQUA + "" + TextFormatting.ITALIC + "https://ftb.gamepedia.com/com/ArmorPlus"));
        ClientUtills.openLink("https://ftb.gamepedia.com/ArmorPlus");
        sender.sendMessage(new TextComponentString("Link opened successfully"));
    }
}