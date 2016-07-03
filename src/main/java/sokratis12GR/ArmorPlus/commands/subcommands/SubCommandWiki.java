package sokratis12GR.ArmorPlus.commands.subcommands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import sokratis12GR.ArmorPlus.commands.SubCommandBase;
import sokratis12GR.ArmorPlus.util.TextHelper;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
        try {
            URL myURL = new URL("http://ftb.gamepedia.com/ArmorPlus");
            openWebpage(myURL);
        } catch (MalformedURLException e) {
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}