package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.util.text.TextFormatting.AQUA;
import static net.minecraft.util.text.TextFormatting.GOLD;
import static net.minecraft.util.text.event.ClickEvent.Action.OPEN_URL;
import static net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT;

/**
 * @author Sokratis Fotkatzikis
 */
public class SupportCommand {

    public static int execute(CommandSource sender) {
        String patreonLink = "https://www.patreon.com/sokratis12GR";
        String githubSponsorLink = "https://github.com/sponsors/sokratis12GR";
        Style patreon = Style.EMPTY.withColor(GOLD).withClickEvent(new ClickEvent(OPEN_URL, patreonLink)).withHoverEvent(new HoverEvent(SHOW_TEXT, translate("commands.armorplus.patreon.link_open")));
        Style github = Style.EMPTY.withColor(AQUA).withClickEvent(new ClickEvent(OPEN_URL, githubSponsorLink)).withHoverEvent(new HoverEvent(SHOW_TEXT, translate("commands.armorplus.github.link_open")));
        sender.sendSuccess(translate(AQUA, "commands.armorplus.patreon.link_details", patreonLink).setStyle(patreon)
                        .append("\n")
                        .append(translate(AQUA, "commands.armorplus.github.link_details", githubSponsorLink).setStyle(github)),
                false);
        return Command.SINGLE_SUCCESS;
    }

}