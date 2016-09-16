/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public class CommandTreeBaseTest extends CommandTreeBase {

    @Override
    public String getCommandName() {
        return null;
    }

    @Override
    public void addSubcommand(ICommand c) {
        super.addSubcommand(c);
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }


}
