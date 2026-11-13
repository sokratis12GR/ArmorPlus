package com.sofodev.armorplus.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.sofodev.armorplus.commands.sub.DiscordCommand;
import com.sofodev.armorplus.commands.sub.EffectsCommand;
import com.sofodev.armorplus.commands.sub.HelpCommand;
import com.sofodev.armorplus.commands.sub.InfoCommand;
import com.sofodev.armorplus.commands.sub.NodecraftCommand;
import com.sofodev.armorplus.commands.sub.RuneCommand;
import com.sofodev.armorplus.commands.sub.StatusCommand;
import com.sofodev.armorplus.commands.sub.SupportCommand;
import com.sofodev.armorplus.commands.sub.WikiCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ArmorPlusCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        register(dispatcher, "armorplus");
        register(dispatcher, "ap");
    }

    private static void register(CommandDispatcher<CommandSourceStack> dispatcher, String name) {
        dispatcher.register(Commands.literal(name)
                .then(Commands.literal("discord").executes(ctx -> discord(ctx.getSource())))
                .then(Commands.literal("info").executes(ctx -> info(ctx.getSource())))
                .then(Commands.literal("nodecraft").executes(ctx -> nodecraft(ctx.getSource())))
                .then(Commands.literal("wiki").executes(ctx -> wiki(ctx.getSource())))
                .then(Commands.literal("support").executes(ctx -> support(ctx.getSource())))
                .then(Commands.literal("help").executes(ctx -> help(ctx.getSource()))
                        .then(Commands.argument("page", IntegerArgumentType.integer(1))
                                .executes(ctx -> HelpCommand.execute(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "page")))))
                .then(Commands.literal("status").executes(ctx -> status(ctx.getSource())))
                .then(EffectsCommand.node())
                .then(RuneCommand.node())
        );
    }

    private static int help(CommandSourceStack source) {
        return HelpCommand.execute(source);
    }

    private static int status(CommandSourceStack source) {
        return StatusCommand.execute(source);
    }

    private static int discord(CommandSourceStack source) {
        return DiscordCommand.execute(source);
    }

    private static int info(CommandSourceStack source) {
        return InfoCommand.execute(source);
    }

    private static int nodecraft(CommandSourceStack source) {
        return NodecraftCommand.execute(source);
    }

    private static int wiki(CommandSourceStack source) {
        return WikiCommand.execute(source);
    }

    private static int support(CommandSourceStack source) {
        return SupportCommand.execute(source);
    }
}
