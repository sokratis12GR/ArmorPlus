package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.sofodev.armorplus.events.ModGlobalEvents;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffNames;
import com.sofodev.armorplus.registry.item.tool.RangedEffects;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

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
                .executes(ctx -> list(ctx.getSource(), ctx.getSource().getPlayerOrException()))
                .then(Commands.argument("player", EntityArgument.player()).requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .executes(ctx -> list(ctx.getSource(), EntityArgument.getPlayer(ctx, "player"))))
                .then(Commands.literal("reset")
                        .executes(ctx -> reset(ctx.getSource(), ctx.getSource().getPlayerOrException()))
                        .then(Commands.argument("player", EntityArgument.player()).requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .executes(ctx -> reset(ctx.getSource(), EntityArgument.getPlayer(ctx, "player")))));
    }

    private static int list(CommandSourceStack source, ServerPlayer player) {
        send(source, translate(GOLD, "commands.armorplus.effects.header", player.getDisplayName()));
        int shown = 0;
        for (ArmorEffects.Entry entry : ArmorEffects.entries(player)) {
            // effects that still need more pieces aren't active or switched off yet
            if (!entry.available()) continue;
            send(source, line(entry.buff(), Component.literal(entry.source()), entry.disabled()));
            shown++;
        }
        for (ToolEffects.Entry entry : ToolEffects.entries(player)) {
            send(source, line(entry.buff(), translate("commands.armorplus.effects.source.tool"), entry.disabled()));
            shown++;
        }
        for (WeaponEffects.Entry entry : WeaponEffects.entries(player)) {
            if (entry.buff().getBuff() == Buff.NONE) continue;
            send(source, line(entry.buff(), translate("commands.armorplus.effects.source.weapon"), entry.disabled()));
            shown++;
        }
        for (RangedEffects.Entry entry : RangedEffects.entries(player)) {
            send(source, line(entry.buff(), translate("commands.armorplus.effects.source.ranged"), entry.disabled()));
            shown++;
        }
        if (shown == 0) send(source, translate(GRAY, "commands.armorplus.effects.none"));
        return shown;
    }

    private static Component line(BuffInstance buff, Component effectSource, boolean disabled) {
        Component name = buff.getTranslatedName().append(BuffNames.levelSuffix(buff));
        return disabled
                ? translate(GRAY, "commands.armorplus.effects.off", name, effectSource)
                : translate(GREEN, "commands.armorplus.effects.on", name, effectSource);
    }

    private static int reset(CommandSourceStack source, ServerPlayer player) {
        int count = player.getData(ModAttachments.DISABLED_EFFECTS.get()).size()
                + player.getData(ModAttachments.DISABLED_TOOL_EFFECTS.get()).size()
                + player.getData(ModAttachments.DISABLED_WEAPON_EFFECTS.get()).size()
                + player.getData(ModAttachments.DISABLED_RANGED_EFFECTS.get()).size();
        player.setData(ModAttachments.DISABLED_EFFECTS.get(), List.of());
        player.setData(ModAttachments.DISABLED_TOOL_EFFECTS.get(), List.of());
        player.setData(ModAttachments.DISABLED_WEAPON_EFFECTS.get(), List.of());
        player.setData(ModAttachments.DISABLED_RANGED_EFFECTS.get(), List.of());
        ArmorEffects.invalidate(player);
        ToolEffects.invalidate(player);
        ModGlobalEvents.checkAndApplyBuffs(player);
        source.sendSuccess(() -> translate(GREEN, "commands.armorplus.effects.reset.success", count, player.getDisplayName()), source.getEntity() != player);
        return count;
    }

    private static void send(CommandSourceStack source, Component message) {
        source.sendSuccess(() -> message, false);
    }
}
