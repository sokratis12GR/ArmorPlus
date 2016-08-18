/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.thedragonteam.armorplus.commands.SubCommandBase;
import net.thedragonteam.core.client.util.ClientUtills;
import net.thedragonteam.core.util.TextHelper;

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

        ClientUtills.openLink("https://ftb.gamepedia.com/ArmorPlus");

    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}