package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.AQUA;
import static net.minecraft.ChatFormatting.GOLD;
import static net.minecraft.network.chat.ClickEvent.Action.OPEN_URL;
import static net.minecraft.network.chat.HoverEvent.Action.SHOW_TEXT;

/**
 * @author Sokratis Fotkatzikis
 */
public class SupportCommand {

    public static int execute(CommandSourceStack sender) {
        String patreonLink = "https://www.patreon.com/sokratis12GR";
        String githubSponsorLink = "https://github.com/sponsors/sokratis12GR";
        Style patreon = Style.EMPTY.withColor(GOLD).withClickEvent(new ClickEvent(OPEN_URL, patreonLink)).withHoverEvent(new HoverEvent(SHOW_TEXT, translate("commands.armorplus.patreon.link_open")));
        Style github = Style.EMPTY.withColor(AQUA).withClickEvent(new ClickEvent(OPEN_URL, githubSponsorLink)).withHoverEvent(new HoverEvent(SHOW_TEXT, translate("commands.armorplus.github.link_open")));
        sender.sendSuccess(()-> translate(AQUA, "commands.armorplus.patreon.link_details", patreonLink).setStyle(patreon)
                        .append("\n")
                        .append(translate(AQUA, "commands.armorplus.github.link_details", githubSponsorLink).setStyle(github)),
                false);
        return Command.SINGLE_SUCCESS;
    }

}