package com.sofodev.armorplus.api.event;

import com.sofodev.armorplus.api.RuneData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.jetbrains.annotations.Nullable;

/**
 * Fired on {@code NeoForge.EVENT_BUS} when a rune that fits is in the ritual of a Rune Altar with equipment, on both sides and
 * whenever the screen needs the result, so it can fire often. Canceling it gives no result; {@link #setCost(int)} changes the
 * experience level cost and {@link #setCatalyst(ItemStack)} the item used up from the pedestals, at most one for each pedestal.
 * <p>
 * Also fired on the server by {@code /armorplus rune socket}. Canceling it stops the command, which never charges the cost or the catalyst.
 */
public class RuneSocketEvent extends Event implements ICancellableEvent {

    private final @Nullable Player player;
    private final ItemStack gear;
    private final RuneData rune;
    private int cost;
    private ItemStack catalyst;

    public RuneSocketEvent(@Nullable Player player, ItemStack gear, RuneData rune, int cost, ItemStack catalyst) {
        this.player = player;
        this.gear = gear;
        this.rune = rune;
        this.cost = cost;
        this.catalyst = catalyst;
    }

    public @Nullable Player getPlayer() {
        return player;
    }

    /** {@return the equipment the rune goes into (don't modify)} */
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

    /** {@return the item and count used up from the pedestals, EMPTY for none} */
    public ItemStack getCatalyst() {
        return catalyst;
    }

    public void setCatalyst(ItemStack catalyst) {
        this.catalyst = catalyst.copy();
    }
}
