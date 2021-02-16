package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import com.sofodev.armorplus.ArmorPlus;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.util.text.TextFormatting.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class InfoCommand {

    public static int execute(CommandSource sender) {
        sendMessages(sender,
                translate(DARK_RED, "commands.armorplus.line_one", ArmorPlus.MODNAME, ArmorPlus.MODID),
                translate(RED, "commands.armorplus.line_two", sender.getName(), ArmorPlus.MODNAME),
                translate(GREEN, "commands.armorplus.line_three", ArmorPlus.MODID),
                translate(GRAY, "commands.armorplus.line_four", sender.getServer().getMinecraftVersion()),
                translate(GOLD, "commands.armorplus.line_six"),
                translate(GOLD, "commands.armorplus.line_seven")
        );
        return Command.SINGLE_SUCCESS;
    }

    private static void sendMessages(CommandSource sender, ITextComponent... messages) {
        Arrays.stream(messages).forEach(message -> sender.sendFeedback(message, false));
    }
}