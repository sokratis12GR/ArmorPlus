/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.commands.subcommands;

import com.sofodev.armorplus.util.TextUtils;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
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
public class CommandNodecraft extends CommandSubBase {

    public CommandNodecraft() {
        super("nodecraft");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        ClickEvent promoLink = new ClickEvent(OPEN_URL, "https://nodecraft.com/r/thedragonteam");
        Style linkStyle = new Style().setColor(BLUE).setUnderlined(true).setClickEvent(promoLink);
        sendMessages(sender, TextUtils.formatText(TextFormatting.AQUA, "commands.armorplus.nodecraft.about.text"), TextUtils.formatText(
            "commands.armorplus.nodecraft.promo.start.text", ""
        ).appendSibling(TextUtils.formatText(
            "commands.armorplus.nodecraft.promo.middle.text"
        ).setStyle(linkStyle)).appendSibling(TextUtils.formatText(
            "commands.armorplus.nodecraft.promo.end.text", "30%")
        ));
    }

    private void sendMessages(ICommandSender sender, ITextComponent... messages) {
        Arrays.stream(messages).forEach(sender::sendMessage);
    }
}
