package com.sofodev.armorplus.api.event;

import com.sofodev.armorplus.api.RuneData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

/**
 * Fired on {@code NeoForge.EVENT_BUS} by the Rune Altar whenever it works out the option to take a socketed rune out of
 * equipment, on both sides, so the screen shows what is charged. It fires often (also while the screen is drawn): listeners
 * must not have side effects and should give the same answer for the same input.
 * <p>
 * Canceling it means the rune can't be taken out in the Rune Altar (no result). {@link #setCost(int)} changes the experience
 * level cost. It isn't fired by the Grindstone, which removes all runes.
 */
public class RuneRemoveEvent extends Event implements ICancellableEvent {

    private final Player player;
    private final ItemStack gear;
    private final RuneData rune;
    private int cost;

    public RuneRemoveEvent(Player player, ItemStack gear, RuneData rune, int cost) {
        this.player = player;
        this.gear = gear;
        this.rune = rune;
        this.cost = cost;
    }

    public Player getPlayer() {
        return player;
    }

    /** {@return the equipment the rune is socketed in (don't modify)} */
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
