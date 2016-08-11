package net.thedragonteam.armorplus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Loader;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.commands.SubCommandBase;
import net.thedragonteam.core.util.TextHelper;

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
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processSubCommand(MinecraftServer server, ICommandSender sender, String[] args) {
        super.processSubCommand(server, sender, args);
        sender.addChatMessage(new TextComponentString(TextFormatting.DARK_RED + "[" + ArmorPlus.MODNAME + " (" + ArmorPlus.VERSION + ") " + "by" + TextFormatting.GOLD + " sokratis12GR]"));
        sender.addChatMessage(new TextComponentString(TextFormatting.RED + "[Thank You " + sender.getName() + " For Using " + ArmorPlus.MODNAME + "]"));
        sender.addChatMessage(new TextComponentString(TextFormatting.GREEN + "[" + "modid: " + ArmorPlus.MODID + "]"));
        sender.addChatMessage(new TextComponentString(TextFormatting.BLUE + "Supported Mods:\n" + TextFormatting.DARK_GREEN + "Tinkers' Construct\n" + TextFormatting.DARK_GREEN + "WeaponsPlus"));
        sender.addChatMessage(new TextComponentString(TextFormatting.DARK_GREEN + "Is Tinkers' Construct Loaded: " + Loader.isModLoaded("tconstruct")));
        sender.addChatMessage(new TextComponentString(TextFormatting.GRAY + "[Minecraft Version: " + server.getMinecraftVersion() + "]"));
        sender.addChatMessage(new TextComponentTranslation(TextFormatting.GOLD + "[Recipes] " + "use command /armorplus wiki"));

        if (!Loader.isModLoaded("tconstruct")) {
            sender.addChatMessage(new TextComponentString(TextFormatting.RED + "Tinkers' Construct Armor Recipes wont work until Tinkers' Construct is Loaded"));
        }
    }
}