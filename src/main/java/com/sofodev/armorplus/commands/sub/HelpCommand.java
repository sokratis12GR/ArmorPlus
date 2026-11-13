package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import com.sofodev.armorplus.commands.CommandPages;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.GOLD;
import static net.minecraft.ChatFormatting.GRAY;

/**
 * "/armorplus help [page]": the commands the source is allowed to use, from their "usage" and "help" translations.
 */
public class HelpCommand {

    private static final List<String> COMMANDS = List.of("help", "info", "status", "effects", "effects.reset", "rune.info", "rune.list",
            "wiki", "discord", "nodecraft", "support");
    private static final List<String> GAMEMASTER_COMMANDS = List.of("rune.give", "rune.socket", "rune.unsocket", "rune.sockets");

    public static int execute(CommandSourceStack source) {
        return execute(source, 1);
    }

    public static int execute(CommandSourceStack source, int page) {
        List<String> commands = new ArrayList<>(COMMANDS);
        if (Commands.LEVEL_GAMEMASTERS.check(source.permissions())) commands.addAll(GAMEMASTER_COMMANDS);
        CommandPages.send(source, commands.stream().map(HelpCommand::line).toList(), page, "/armorplus help");
        return Command.SINGLE_SUCCESS;
    }

    private static Component line(String command) {
        return translate(GRAY, "commands.armorplus.format.help",
                translate(GOLD, "commands.armorplus." + command + ".usage"),
                translate("commands.armorplus." + command + ".help"));
    }
}
