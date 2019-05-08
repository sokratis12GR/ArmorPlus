/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.commands.subcommands;

import com.mojang.brigadier.Command;
import com.sofodev.armorplus.common.util.TextUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

import java.util.Arrays;

import static net.minecraft.util.text.TextFormatting.BLUE;
import static net.minecraft.util.text.event.ClickEvent.Action.OPEN_URL;

/**
 * @author Sokratis Fotkatzikis
 */
public class CommandNodecraft {

    public static int execute(CommandSource sender) {
        ClickEvent promoLink = new ClickEvent(OPEN_URL, "https://nodecraft.com/r/armorplus");
        Style linkStyle = new Style().setColor(BLUE).setUnderlined(true).setClickEvent(promoLink);
        sendMessages(sender, TextUtils.translate(TextFormatting.AQUA, "commands.armorplus.nodecraft.about.text"), TextUtils.translate(
            "commands.armorplus.nodecraft.promo.start.text", ""
        ).appendSibling(TextUtils.translate(
            "commands.armorplus.nodecraft.promo.middle.text"
        ).setStyle(linkStyle)).appendSibling(TextUtils.translate(
            "commands.armorplus.nodecraft.promo.end.text", "30%")
        ));
        return Command.SINGLE_SUCCESS;
    }

    private static void sendMessages(CommandSource sender, ITextComponent... messages) {
        Arrays.stream(messages).forEach(msg -> sender.sendFeedback(msg, false));
    }
}
