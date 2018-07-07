package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public abstract class CommandSubBase extends CommandBase {

    private final String name;
    private final String usage;

    public CommandSubBase(String name) {
        this.name = name;
        this.usage = "commands.armorplus." + name + ".usage";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return this.usage;
    }
}
