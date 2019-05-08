/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.sofodev.armorplus.common.caps.abilities.CapabilityAbility;
import com.sofodev.armorplus.common.caps.abilities.data.AbilityData;
import com.sofodev.armorplus.common.commands.subcommands.CommandDiscord;
import com.sofodev.armorplus.common.commands.subcommands.CommandInfo;
import com.sofodev.armorplus.common.commands.subcommands.CommandNodecraft;
import com.sofodev.armorplus.common.commands.subcommands.CommandWiki;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.command.arguments.EnchantmentArgument;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Objects;

import static com.sofodev.armorplus.common.caps.abilities.data.AbilityData.*;
import static com.sofodev.armorplus.common.util.TextUtils.*;

/**
 * @author Sokratis Fotkatzikis
 */
public class CommandArmorPlus {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("armorplus").requires(cs -> cs.hasPermissionLevel(0))
            .then(Commands.literal("discord").executes(ctx -> discord(ctx.getSource())))
            .then(Commands.literal("info").executes(ctx -> info(ctx.getSource())))
            .then(Commands.literal("nodecraft").executes(ctx -> nodecraft(ctx.getSource())))
            .then(Commands.literal("wiki").executes(ctx -> wiki(ctx.getSource())))
        );
    }

    private static int discord(CommandSource source) {
        return CommandDiscord.execute(source);
    }

    private static int info(CommandSource source) {
        return CommandInfo.execute(source);
    }

    private static int nodecraft(CommandSource source) {
        return CommandNodecraft.execute(source);
    }

    private static int wiki(CommandSource source) {
        return CommandWiki.execute(source);
    }
}