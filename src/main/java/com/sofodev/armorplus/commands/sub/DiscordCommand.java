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
public class DiscordCommand {

    public static int execute(CommandSourceStack sender) {
        String discord = "https://discord.gg/JCWbJvA";
        Style ap = Style.EMPTY.withClickEvent(new ClickEvent.OpenUrl(URI.create(discord))).withHoverEvent(new HoverEvent.ShowText(translate("commands.armorplus.discord.hover")));
        sender.sendSuccess(() -> translate(AQUA, "commands.armorplus.discord.line_one", discord).setStyle(ap), true);
        return Command.SINGLE_SUCCESS;
    }
}