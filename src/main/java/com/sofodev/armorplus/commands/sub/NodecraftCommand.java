package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.util.Arrays;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.BLUE;
import static net.minecraft.network.chat.ClickEvent.Action.OPEN_URL;

/**
 * @author Sokratis Fotkatzikis
 */
public class NodecraftCommand {

    public static int execute(CommandSourceStack sender) {
        ClickEvent promoLink = new ClickEvent(OPEN_URL, "https://nodecraft.com/r/armorplus");
        Style linkStyle = Style.EMPTY.applyFormat(BLUE).withUnderlined(true).withClickEvent(promoLink);
        sendMessages(sender, translate(ChatFormatting.AQUA, "commands.armorplus.nodecraft.about.text"),
                translate("commands.armorplus.nodecraft.promo.start.text")
                        .append(translate("commands.armorplus.nodecraft.promo.middle.text").setStyle(linkStyle))
                        .append(translate("commands.armorplus.nodecraft.promo.end.text", "30%")
                        ));
        return Command.SINGLE_SUCCESS;
    }

    private static void sendMessages(CommandSourceStack sender, Component... messages) {
        Arrays.stream(messages).forEach(msg -> sender.sendSuccess(()-> msg, false));
    }
}