package com.sofodev.armorplus.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.sofodev.armorplus.commands.sub.DiscordCommand;
import com.sofodev.armorplus.commands.sub.InfoCommand;
import com.sofodev.armorplus.commands.sub.NodecraftCommand;
import com.sofodev.armorplus.commands.sub.WikiCommand;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class ArmorPlusCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("armorplus").requires(cs -> cs.hasPermissionLevel(0))
                .then(Commands.literal("discord").executes(ctx -> discord(ctx.getSource())))
                .then(Commands.literal("info").executes(ctx -> info(ctx.getSource())))
                .then(Commands.literal("nodecraft").executes(ctx -> nodecraft(ctx.getSource())))
                .then(Commands.literal("wiki").executes(ctx -> wiki(ctx.getSource())))
        );
    }

    private static int discord(CommandSource source) {
        return DiscordCommand.execute(source);
    }

    private static int info(CommandSource source) {
        return InfoCommand.execute(source);
    }

    private static int nodecraft(CommandSource source) {
        return NodecraftCommand.execute(source);
    }

    private static int wiki(CommandSource source) {
        return WikiCommand.execute(source);
    }
}
