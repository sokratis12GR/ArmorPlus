package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.GOLD;
import static net.minecraft.ChatFormatting.GRAY;

/**
 * "/armorplus help": the commands the source is allowed to use, from their "usage" and "help" translations.
 */
public class HelpCommand {

    private static final List<String> COMMANDS = List.of("help", "info", "status", "effects", "effects.reset", "rune.info", "rune.list",
            "wiki", "discord", "nodecraft", "support");
    private static final List<String> GAMEMASTER_COMMANDS = List.of("rune.give", "rune.socket", "rune.unsocket", "rune.sockets");

    public static int execute(CommandSourceStack source) {
        COMMANDS.forEach(command -> send(source, command));
        if (Commands.LEVEL_GAMEMASTERS.check(source.permissions())) GAMEMASTER_COMMANDS.forEach(command -> send(source, command));
        return Command.SINGLE_SUCCESS;
    }

    private static void send(CommandSourceStack source, String command) {
        source.sendSuccess(() -> translate(GRAY, "commands.armorplus.format.help",
                translate(GOLD, "commands.armorplus." + command + ".usage"),
                translate("commands.armorplus." + command + ".help")), false);
    }
}
