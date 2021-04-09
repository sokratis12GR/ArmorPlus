package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.util.text.TextFormatting.AQUA;
import static net.minecraft.util.text.event.ClickEvent.Action.OPEN_URL;
import static net.minecraft.util.text.event.HoverEvent.Action.SHOW_TEXT;

/**
 * @author Sokratis Fotkatzikis
 */
public class PatreonCommand {

    public static int execute(CommandSource sender) {
        String link = "https://www.patreon.com/sokratis12GR";
        Style wiki = Style.EMPTY.withClickEvent(new ClickEvent(OPEN_URL, link)).withHoverEvent(new HoverEvent(SHOW_TEXT, translate("commands.armorplus.patreon.link_open")));
        sender.sendSuccess(translate(AQUA, "commands.armorplus.patreon.link_details", link).setStyle(wiki), false);
        return Command.SINGLE_SUCCESS;
    }

}