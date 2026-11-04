package com.sofodev.armorplus.api.event;

import com.sofodev.armorplus.registry.rune.RuneData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.jspecify.annotations.Nullable;

/**
 * Fired on {@code NeoForge.EVENT_BUS} when a rune that fits is put into an anvil next to equipment, on both sides (like
 * {@code AnvilUpdateEvent}). Canceling it gives no anvil output; {@link #setCost(int)} changes the experience level cost.
 */
public class RuneSocketEvent extends Event implements ICancellableEvent {

    private final @Nullable Player player;
    private final ItemStack gear;
    private final RuneData rune;
    private int cost;

    public RuneSocketEvent(@Nullable Player player, ItemStack gear, RuneData rune, int cost) {
        this.player = player;
        this.gear = gear;
        this.rune = rune;
        this.cost = cost;
    }

    public @Nullable Player getPlayer() {
        return player;
    }

    /** @return the equipment the rune goes into (don't modify) */
    public ItemStack getGear() {
        return gear;
    }

    public RuneData getRune() {
        return rune;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = Math.max(0, cost);
    }
}
