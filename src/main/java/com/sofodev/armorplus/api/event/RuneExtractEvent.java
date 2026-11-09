package com.sofodev.armorplus.api.event;

import com.sofodev.armorplus.api.RuneData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

/**
 * Fired on {@code NeoForge.EVENT_BUS} by the Rune Altar whenever it works out an extraction option (its cost, catalyst and
 * result), on both sides, so the screen shows what is charged. It fires often (also while the screen is drawn): listeners
 * must not have side effects and should give the same answer for the same input.
 * <p>
 * Canceling it means the option can't be extracted (no result). {@link #setCost(int)} and {@link #setCatalyst(ItemStack)}
 * change the experience level cost and the catalyst needed (EMPTY = none).
 */
public class RuneExtractEvent extends Event implements ICancellableEvent {

    private final Player player;
    private final ItemStack gear;
    private final RuneData rune;
    private int cost;
    private ItemStack catalyst;

    public RuneExtractEvent(Player player, ItemStack gear, RuneData rune, int cost, ItemStack catalyst) {
        this.player = player;
        this.gear = gear;
        this.rune = rune;
        this.cost = cost;
        this.catalyst = catalyst;
    }

    public Player getPlayer() {
        return player;
    }

    /** {@return the equipment the rune is extracted from (don't modify)} */
    public ItemStack getGear() {
        return gear;
    }

    /** {@return the rune the option makes (already at its reduced level)} */
    public RuneData getRune() {
        return rune;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = Math.max(0, cost);
    }

    public ItemStack getCatalyst() {
        return catalyst;
    }

    public void setCatalyst(ItemStack catalyst) {
        this.catalyst = catalyst.copy();
    }
}
