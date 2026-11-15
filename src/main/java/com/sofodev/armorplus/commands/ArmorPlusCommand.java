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
                .executes(ctx -> HelpCommand.execute(ctx.getSource()))
                .then(Commands.literal("discord").executes(ctx -> DiscordCommand.execute(ctx.getSource())))
                .then(Commands.literal("info").executes(ctx -> InfoCommand.execute(ctx.getSource())))
                .then(Commands.literal("nodecraft").executes(ctx -> NodecraftCommand.execute(ctx.getSource())))
                .then(Commands.literal("wiki").executes(ctx -> WikiCommand.execute(ctx.getSource())))
                .then(Commands.literal("support").executes(ctx -> SupportCommand.execute(ctx.getSource())))
                .then(Commands.literal("help").executes(ctx -> HelpCommand.execute(ctx.getSource()))
                        .then(Commands.argument("page", IntegerArgumentType.integer(1))
                                .executes(ctx -> HelpCommand.execute(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "page")))))
                .then(Commands.literal("status").executes(ctx -> StatusCommand.execute(ctx.getSource())))
                .then(EffectsCommand.node())
                .then(RuneCommand.node())
        );
    }
}
