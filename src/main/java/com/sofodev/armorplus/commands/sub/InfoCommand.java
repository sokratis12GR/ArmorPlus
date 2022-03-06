package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.util.Arrays;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class InfoCommand {

    public static int execute(CommandSourceStack sender) {
        sendMessages(sender,
                translate(DARK_RED, "commands.armorplus.line_one", ArmorPlus.MODNAME, ArmorPlus.MODID),
                translate(RED, "commands.armorplus.line_two", sender.getTextName(), ArmorPlus.MODNAME),
                translate(GREEN, "commands.armorplus.line_three", ArmorPlus.MODID),
                translate(GRAY, "commands.armorplus.line_four", sender.getServer().getServerVersion()),
                translate(GOLD, "commands.armorplus.line_six"),
                translate(GOLD, "commands.armorplus.line_seven")
        );
        return Command.SINGLE_SUCCESS;
    }

    private static void sendMessages(CommandSourceStack sender, Component... messages) {
        Arrays.stream(messages).forEach(message -> sender.sendSuccess(message, false));
    }
}