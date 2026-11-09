package com.sofodev.armorplus.commands.sub;

import com.mojang.brigadier.Command;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.registry.rune.RuneDefinitions;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import static com.sofodev.armorplus.utils.ToolTipUtils.translate;
import static net.minecraft.ChatFormatting.GOLD;
import static net.minecraft.ChatFormatting.GRAY;
import static net.minecraft.ChatFormatting.GREEN;
import static net.minecraft.ChatFormatting.RED;

/**
 * "/armorplus status": which of the main features the server's config has switched on.
 */
public class StatusCommand {

    public static int execute(CommandSourceStack source) {
        send(source, translate(GOLD, "commands.armorplus.status.header"));
        feature(source, "runes", ArmorPlusConfig.enableRunes.get());
        feature(source, "vanilla_gear", ArmorPlusConfig.allowVanillaGear.get());
        feature(source, "modded_gear", ArmorPlusConfig.allowModdedGear.get());
        feature(source, "random_sockets", ArmorPlusConfig.randomSockets.get());
        feature(source, "socket_punch", ArmorPlusConfig.enableSocketPunch.get());
        feature(source, "return_runes", ArmorPlusConfig.returnRunesOnRemoval.get());
        feature(source, "effect_toggles", ArmorPlusConfig.allowEffectToggles.get());
        feature(source, "auto_smelting", ArmorPlusConfig.enableAutoSmelting.get());
        send(source, translate(GRAY, "commands.armorplus.status.max_sockets", ArmorPlusConfig.maxArmorSockets.get(), ArmorPlusConfig.maxWeaponSockets.get(), ArmorPlusConfig.maxToolSockets.get(), ArmorPlusConfig.maxRangedSockets.get()));
        send(source, translate(GRAY, "commands.armorplus.status.excluded_mods", ArmorPlusConfig.excludedRuneMods.get().size()));
        send(source, translate(GRAY, "commands.armorplus.status.datapack_runes", RuneDefinitions.all().size()));
        return Command.SINGLE_SUCCESS;
    }

    private static void feature(CommandSourceStack source, String name, boolean enabled) {
        Component state = enabled ? translate(GREEN, "commands.armorplus.status.on") : translate(RED, "commands.armorplus.status.off");
        send(source, translate(GRAY, "commands.armorplus.status.feature", translate("commands.armorplus.status.feature." + name), state));
    }

    private static void send(CommandSourceStack source, Component message) {
        source.sendSuccess(() -> message, false);
    }
}
