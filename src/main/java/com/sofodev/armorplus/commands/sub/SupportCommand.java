package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;

import java.net.URI;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class SupportCommand {

    public static int execute(CommandSourceStack sender) {
        String kofiLink = "https://ko-fi.com/sofodev";
        String patreonLink = "https://www.patreon.com/sokratis12GR";
        String githubSponsorLink = "https://github.com/sponsors/sokratis12GR";
        Style kofi = Style.EMPTY.withColor(AQUA).withClickEvent(new ClickEvent.OpenUrl(URI.create(kofiLink))).withHoverEvent(new HoverEvent.ShowText(translate("commands.armorplus.kofi.link_open")));
//        Style patreon = Style.EMPTY.withColor(GOLD).withClickEvent(new ClickEvent.OpenUrl(URI.create(patreonLink))).withHoverEvent(new HoverEvent.ShowText(translate("commands.armorplus.patreon.link_open")));
//        Style github = Style.EMPTY.withColor(AQUA).withClickEvent(new ClickEvent.OpenUrl(URI.create(githubSponsorLink))).withHoverEvent(new HoverEvent.ShowText(translate("commands.armorplus.github.link_open")));
        sender.sendSuccess(() -> translate(AQUA, "commands.armorplus.kofi.link_details", kofiLink).setStyle(kofi),
                false);
        return Command.SINGLE_SUCCESS;
    }

}