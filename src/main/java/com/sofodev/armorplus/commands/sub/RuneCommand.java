package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.sofodev.armorplus.api.event.RuneSocketEvent;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.ModDataComponents;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.rune.RuneData;
import com.sofodev.armorplus.registry.rune.RuneItem;
import com.sofodev.armorplus.registry.rune.RuneKind;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.*;

/**
 * "/armorplus rune": anyone can look up runes, changing them needs gamemaster permissions. The commands follow the same
 * rules as the Anvil ({@link Runes#canSocket(ItemStack, RuneData)}, {@link RuneSocketEvent}), they only don't cost experience.
 */
public class RuneCommand {

    private static final SuggestionProvider<CommandSourceStack> SOCKETED = (ctx, builder) -> {
        Player player = ctx.getSource().getPlayer();
        return SharedSuggestionProvider.suggest(player == null ? List.<String>of()
                : Runes.sockets(player.getMainHandItem()).stream().map(RuneData::buffId).toList(), builder);
    };

    public static LiteralArgumentBuilder<CommandSourceStack> node() {
        LiteralArgumentBuilder<CommandSourceStack> list = Commands.literal("list").executes(ctx -> list(ctx.getSource(), null));
        LiteralArgumentBuilder<CommandSourceStack> socket = Commands.literal("socket").requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS));
        ArgumentBuilder<CommandSourceStack, ?> give = Commands.argument("players", EntityArgument.players());
        for (RuneKind kind : RuneKind.values()) {
            list.then(Commands.literal(kind.getSerializedName()).executes(ctx -> list(ctx.getSource(), kind)));
            give.then(Commands.literal(kind.getSerializedName()).then(Commands.argument("effect", IdentifierArgument.id())
                    .suggests(obtainable(kind))
                    .executes(ctx -> give(ctx.getSource(), EntityArgument.getPlayers(ctx, "players"), kind, IdentifierArgument.getId(ctx, "effect"), 1))
                    .then(Commands.argument("level", IntegerArgumentType.integer(1, 256))
                            .executes(ctx -> give(ctx.getSource(), EntityArgument.getPlayers(ctx, "players"), kind, IdentifierArgument.getId(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "level"))))));
            socket.then(Commands.literal(kind.getSerializedName()).then(Commands.argument("effect", IdentifierArgument.id())
                    .suggests(obtainable(kind))
                    .executes(ctx -> socket(ctx.getSource(), kind, IdentifierArgument.getId(ctx, "effect"), 1))
                    .then(Commands.argument("level", IntegerArgumentType.integer(1, 256))
                            .executes(ctx -> socket(ctx.getSource(), kind, IdentifierArgument.getId(ctx, "effect"), IntegerArgumentType.getInteger(ctx, "level"))))));
        }
        return Commands.literal("rune")
                .then(Commands.literal("info").executes(ctx -> info(ctx.getSource())))
                .then(list)
                .then(Commands.literal("give").requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS)).then(give))
                .then(socket)
                .then(Commands.literal("unsocket").requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .executes(ctx -> unsocket(ctx.getSource(), null))
                        .then(Commands.argument("effect", IdentifierArgument.id())
                                .suggests(SOCKETED)
                                .executes(ctx -> unsocket(ctx.getSource(), IdentifierArgument.getId(ctx, "effect")))))
                .then(Commands.literal("sockets").requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .then(Commands.argument("count", IntegerArgumentType.integer(0))
                                .executes(ctx -> sockets(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "count")))));
    }

    private static SuggestionProvider<CommandSourceStack> obtainable(RuneKind kind) {
        return (ctx, builder) -> SharedSuggestionProvider.suggest(
                Runes.creativeRunes().stream().filter(rune -> rune.kind() == kind).map(RuneData::buffId), builder);
    }

    private static int info(CommandSourceStack source) throws CommandSyntaxException {
        ItemStack stack = source.getPlayerOrException().getMainHandItem();
        RuneData rune = Runes.runeData(stack);
        if (rune != null) return runeInfo(source, rune);
        if (Runes.gearKind(stack) != null) return gearInfo(source, stack);
        return fail(source, "commands.armorplus.rune.info.nothing");
    }

    private static int runeInfo(CommandSourceStack source, RuneData rune) {
        send(source, translate(GOLD, "commands.armorplus.rune.info.rune", name(rune), rune.buffId()));
        send(source, translate(GRAY, "commands.armorplus.rune.info.kind", rune.kind().getSerializedName()));
        send(source, Runes.resolve(rune) != null
                ? translate(GREEN, "commands.armorplus.rune.info.active")
                : translate(RED, "commands.armorplus.rune.info.inactive"));
        Set<EquipmentSlot> slots = Runes.runeSlots(rune);
        if (!slots.isEmpty()) send(source, translate(GRAY, "commands.armorplus.rune.info.slots", BuffNames.slotNames(slots)));
        send(source, translate(GRAY, "commands.armorplus.rune.info.socket_cost", Runes.socketCost(rune)));
        return Command.SINGLE_SUCCESS;
    }

    private static int gearInfo(CommandSourceStack source, ItemStack gear) {
        List<RuneData> socketed = Runes.sockets(gear);
        send(source, translate(GOLD, "commands.armorplus.rune.info.gear", gear.getHoverName(), Runes.gearKind(gear).getSerializedName()));
        send(source, Runes.hasRolledSockets(gear)
                ? translate(GRAY, "commands.armorplus.rune.info.sockets", socketed.size(), Runes.socketCapacity(gear))
                : translate(GRAY, "commands.armorplus.rune.info.not_rolled"));
        for (RuneData rune : socketed) {
            send(source, translate(Runes.resolve(rune) != null ? GREEN : DARK_GRAY, "commands.armorplus.rune.info.socketed", name(rune)));
        }
        for (String entry : Runes.extractableEntries(gear)) {
            RuneData extracted = new RuneData(Runes.extractionKind(gear), Runes.extractedEntry(entry));
            ItemStack catalyst = Runes.extractionCatalyst(extracted);
            send(source, catalyst.isEmpty()
                    ? translate(GRAY, "commands.armorplus.rune.info.extract", name(extracted), Runes.extractionCost(extracted))
                    : translate(GRAY, "commands.armorplus.rune.info.extract_catalyst", name(extracted), Runes.extractionCost(extracted), catalyst.getCount(), catalyst.getHoverName()));
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int list(CommandSourceStack source, @Nullable RuneKind kind) {
        if (!Runes.enabled()) return fail(source, "commands.armorplus.rune.disabled");
        List<RuneData> runes = Runes.creativeRunes().stream().filter(rune -> kind == null || rune.kind() == kind).toList();
        if (runes.isEmpty()) return fail(source, "commands.armorplus.rune.list.empty");
        send(source, translate(GOLD, "commands.armorplus.rune.list.header", runes.size()));
        for (RuneData rune : runes) {
            send(source, translate(GRAY, "commands.armorplus.rune.list.entry", rune.kind().getSerializedName(), name(rune), rune.buffId()));
        }
        return runes.size();
    }

    private static int give(CommandSourceStack source, Collection<ServerPlayer> players, RuneKind kind, Identifier effect, int level) {
        RuneData rune = createRune(source, kind, effect, level);
        if (rune == null) return 0;
        for (ServerPlayer player : players) {
            player.getInventory().placeItemBackInInventory(RuneItem.create(rune.kind(), rune.entry()));
        }
        source.sendSuccess(() -> translate(GREEN, "commands.armorplus.rune.give.success", name(rune), kind.getSerializedName(), players.size()), true);
        return players.size();
    }

    private static int socket(CommandSourceStack source, RuneKind kind, Identifier effect, int level) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        RuneData rune = createRune(source, kind, effect, level);
        if (rune == null) return 0;
        ItemStack gear = player.getMainHandItem();
        Runes.SocketResult result = Runes.canSocket(gear, rune);
        if (result != Runes.SocketResult.OK) return fail(source, "commands.armorplus.rune.socket." + result.name().toLowerCase(Locale.ENGLISH));
        if (NeoForge.EVENT_BUS.post(new RuneSocketEvent(player, gear, rune, Runes.socketCost(rune))).isCanceled()) {
            return fail(source, "commands.armorplus.rune.socket.canceled");
        }
        replaceHeld(player, Runes.withRune(gear, rune));
        source.sendSuccess(() -> translate(GREEN, "commands.armorplus.rune.socket.success", name(rune), player.getMainHandItem().getHoverName()), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int unsocket(CommandSourceStack source, @Nullable Identifier effect) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        ItemStack gear = player.getMainHandItem();
        String id = effect == null ? null : effect.toString();
        long removed = Runes.sockets(gear).stream().filter(rune -> id == null || rune.buffId().equals(id)).count();
        if (removed == 0) return fail(source, "commands.armorplus.rune.unsocket.nothing");
        replaceHeld(player, Runes.withoutRunes(gear, id));
        source.sendSuccess(() -> translate(GREEN, "commands.armorplus.rune.unsocket.success", removed, player.getMainHandItem().getHoverName()), true);
        return (int) removed;
    }

    private static int sockets(CommandSourceStack source, int count) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        ItemStack gear = player.getMainHandItem();
        RuneKind kind = Runes.gearKind(gear);
        if (kind == null) return fail(source, "commands.armorplus.rune.socket.not_socketable");
        // without random sockets all equipment has the configured maximum
        if (!ArmorPlusConfig.randomSockets.get()) return fail(source, "commands.armorplus.rune.sockets.not_random");
        int max = Runes.maxSockets(kind);
        if (count > max) return fail(source, "commands.armorplus.rune.sockets.too_many", max);
        int used = Runes.sockets(gear).size();
        if (count < used) return fail(source, "commands.armorplus.rune.sockets.too_few", used);
        ItemStack result = gear.copyWithCount(1);
        result.set(ModDataComponents.SOCKET_SLOTS.get(), count);
        replaceHeld(player, result);
        source.sendSuccess(() -> translate(GREEN, "commands.armorplus.rune.sockets.success", player.getMainHandItem().getHoverName(), count), true);
        return Command.SINGLE_SUCCESS;
    }

    /** @return the rune, or null after telling the source why it can't be made. Abilities have no level, so theirs is left out. */
    private static @Nullable RuneData createRune(CommandSourceStack source, RuneKind kind, Identifier effect, int level) {
        if (!Runes.enabled()) {
            fail(source, "commands.armorplus.rune.disabled");
            return null;
        }
        RuneData rune = new RuneData(kind, effect + "|" + level);
        BuffInstance buff = Runes.parse(rune);
        if (buff == null) {
            fail(source, "commands.armorplus.rune.unknown", effect.toString(), kind.getSerializedName());
            return null;
        }
        if (!buff.getBuff().isEffect()) rune = new RuneData(kind, effect.toString());
        if (Runes.resolve(rune) == null) {
            fail(source, "commands.armorplus.rune.effect_disabled", effect.toString());
            return null;
        }
        return rune;
    }

    private static void replaceHeld(ServerPlayer player, ItemStack stack) {
        player.setItemInHand(InteractionHand.MAIN_HAND, stack);
        ToolEffects.invalidate(player);
    }

    private static Component name(RuneData rune) {
        BuffInstance buff = Runes.parse(rune);
        return buff == null ? Component.literal(rune.buffId()) : buff.getTranslatedName().append(BuffNames.levelSuffix(buff));
    }

    private static void send(CommandSourceStack source, Component message) {
        source.sendSuccess(() -> message, false);
    }

    private static int fail(CommandSourceStack source, String key, Object... args) {
        source.sendFailure(translate(RED, key, args));
        return 0;
    }
}
