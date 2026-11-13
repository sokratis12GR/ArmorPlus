package com.sofodev.armorplus.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;

/**
 * Keeps the client-only {@code LocalPlayer} reference out of common classes such as ToolTipUtils: verifying a method that
 * returns {@code Minecraft.getInstance().player} as a {@link Player} loads {@code LocalPlayer}, which crashes dedicated servers.
 * This class is only loaded when {@link #player()} is first called, which only happens on the client.
 */
public final class ClientPlayerAccess {

    private ClientPlayerAccess() {}

    /** @return the local player, or null (e.g. on the title screen) */
    public static @Nullable Player player() {
        return Minecraft.getInstance().player;
    }
}
