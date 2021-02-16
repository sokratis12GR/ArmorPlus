package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

import java.util.Arrays;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.util.text.TextFormatting.BLUE;
import static net.minecraft.util.text.event.ClickEvent.Action.OPEN_URL;

/**
 * @author Sokratis Fotkatzikis
 */
public class NodecraftCommand {

    public static int execute(CommandSource sender) {
        ClickEvent promoLink = new ClickEvent(OPEN_URL, "https://nodecraft.com/r/armorplus");
        Style linkStyle = Style.EMPTY.applyFormatting(BLUE).setUnderlined(true).setClickEvent(promoLink);
        sendMessages(sender, translate(TextFormatting.AQUA, "commands.armorplus.nodecraft.about.text"),
                translate("commands.armorplus.nodecraft.promo.start.text")
                        .append(translate("commands.armorplus.nodecraft.promo.middle.text").setStyle(linkStyle))
                        .append(translate("commands.armorplus.nodecraft.promo.end.text", "30%")
                        ));
        return Command.SINGLE_SUCCESS;
    }

    private static void sendMessages(CommandSource sender, ITextComponent... messages) {
        Arrays.stream(messages).forEach(msg -> sender.sendFeedback(msg, false));
    }
}