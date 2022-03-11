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
public class DiscordCommand {

    public static int execute(CommandSource sender) {
        String discord = "https://discord.gg/JCWbJvA";
        Style ap = Style.EMPTY.withClickEvent(new ClickEvent(OPEN_URL, discord)).withHoverEvent(new HoverEvent(SHOW_TEXT, translate("commands.armorplus.discord.hover")));
        sender.sendSuccess(translate(AQUA, "commands.armorplus.discord.line_one", discord).setStyle(ap), true);
        return Command.SINGLE_SUCCESS;
    }
}