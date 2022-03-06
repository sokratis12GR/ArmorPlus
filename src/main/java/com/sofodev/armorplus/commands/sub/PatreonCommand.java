package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.AQUA;
import static net.minecraft.network.chat.ClickEvent.Action.OPEN_URL;
import static net.minecraft.network.chat.HoverEvent.Action.SHOW_TEXT;

/**
 * @author Sokratis Fotkatzikis
 */
public class PatreonCommand {

    public static int execute(CommandSourceStack sender) {
        String link = "https://www.patreon.com/sokratis12GR";
        Style wiki = Style.EMPTY.withClickEvent(new ClickEvent(OPEN_URL, link)).withHoverEvent(new HoverEvent(SHOW_TEXT, translate("commands.armorplus.patreon.link_open")));
        sender.sendSuccess(translate(AQUA, "commands.armorplus.patreon.link_details", link).setStyle(wiki), false);
        return Command.SINGLE_SUCCESS;
    }

}