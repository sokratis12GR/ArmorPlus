package com.sofodev.armorplus.commands.sub;

import java.net.URI;

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
public class WikiCommand {

    public static int execute(CommandSourceStack sender) {
        String link = "https://sokratis.space/armorplus/wiki/";
        Style wiki = Style.EMPTY.withClickEvent(new ClickEvent.OpenUrl(URI.create(link))).withHoverEvent(new HoverEvent.ShowText(translate("commands.armorplus.wiki.link_open")));
        sender.sendSuccess(() -> translate(AQUA, "commands.armorplus.wiki.link_details", link).setStyle(wiki), false);
        return Command.SINGLE_SUCCESS;
    }

}