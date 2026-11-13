package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.sofodev.armorplus.api.EffectEntry;
import com.sofodev.armorplus.api.EffectScope;
import com.sofodev.armorplus.api.EffectScopes;
import com.sofodev.armorplus.commands.CommandPages;
import com.sofodev.armorplus.events.ModGlobalEvents;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.GOLD;
import static net.minecraft.ChatFormatting.GRAY;
import static net.minecraft.ChatFormatting.GREEN;

/**
 * "/armorplus effects": the armor, tool, weapon and ranged effects of a player and the ones switched off in the effect screens.
 * Anyone can use it on themselves, other players need gamemaster permissions.
 */
public class EffectsCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> node() {
        return Commands.literal("effects")
                .executes(ctx -> list(ctx.getSource(), ctx.getSource().getPlayerOrException(), 1))
                .then(Commands.literal("page").then(Commands.argument("page", IntegerArgumentType.integer(1))
                        .executes(ctx -> list(ctx.getSource(), ctx.getSource().getPlayerOrException(), IntegerArgumentType.getInteger(ctx, "page")))))
                .then(Commands.argument("player", EntityArgument.player()).requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .executes(ctx -> list(ctx.getSource(), EntityArgument.getPlayer(ctx, "player"), 1))
                        .then(Commands.argument("page", IntegerArgumentType.integer(1))
                                .executes(ctx -> list(ctx.getSource(), EntityArgument.getPlayer(ctx, "player"), IntegerArgumentType.getInteger(ctx, "page")))))
                .then(Commands.literal("reset")
                        .executes(ctx -> reset(ctx.getSource(), ctx.getSource().getPlayerOrException()))
                        .then(Commands.argument("player", EntityArgument.player()).requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .executes(ctx -> reset(ctx.getSource(), EntityArgument.getPlayer(ctx, "player")))));
    }

    private static int list(CommandSourceStack source, ServerPlayer player, int page) {
        send(source, translate(GOLD, "commands.armorplus.effects.header", player.getDisplayName()));
        List<Component> lines = new ArrayList<>();
        for (EffectScope scope : EffectScopes.all()) {
            for (EffectEntry entry : scope.entries(player)) {
                // effects that still need more pieces aren't active or switched off yet
                if (!entry.available() || entry.buff().getBuff() == Buff.NONE) continue;
                lines.add(line(entry.buff(), scope.sourceName(entry), entry.disabled()));
            }
        }
        if (lines.isEmpty()) send(source, translate(GRAY, "commands.armorplus.effects.none"));
        // the page buttons show the same player's effects: "effects page <n>" for your own, "effects <player> <n>" for others
        String command = source.getEntity() == player ? "/armorplus effects page" : "/armorplus effects " + player.getScoreboardName();
        CommandPages.send(source, lines, page, command);
        return lines.size();
    }

    private static Component line(BuffInstance buff, Component effectSource, boolean disabled) {
        Component name = buff.getTranslatedName().append(BuffNames.levelSuffix(buff));
        return disabled
                ? translate(GRAY, "commands.armorplus.effects.off", name, effectSource)
                : translate(GREEN, "commands.armorplus.effects.on", name, effectSource);
    }

    private static int reset(CommandSourceStack source, ServerPlayer player) {
        int count = EffectScopes.all().stream().mapToInt(scope -> player.getData(scope.disabledEffects()).size()).sum();
        for (EffectScope scope : EffectScopes.all()) {
            player.setData(scope.disabledEffects(), List.of());
            scope.invalidate(player);
        }
        ModGlobalEvents.checkAndApplyBuffs(player);
        source.sendSuccess(() -> translate(GREEN, "commands.armorplus.effects.reset.success", count, player.getDisplayName()), source.getEntity() != player);
        return count;
    }

    private static void send(CommandSourceStack source, Component message) {
        source.sendSuccess(() -> message, false);
    }
}
