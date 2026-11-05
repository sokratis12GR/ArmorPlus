package com.sofodev.armorplus.api.event;

import com.sofodev.armorplus.api.RuneData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.jspecify.annotations.Nullable;

/**
 * Fired on {@code NeoForge.EVENT_BUS} when two identical runes that can be upgraded are in the ritual of a Rune Altar, on both
 * sides and whenever the screen needs the result, so it can fire often. Canceling it gives no result;
 * {@link #setCost(int)} changes the experience level cost and {@link #setCatalyst(ItemStack)} the item used up from the pedestals.
 */
public class RuneUpgradeEvent extends Event implements ICancellableEvent {

    private final @Nullable Player player;
    private final RuneData rune;
    private final RuneData result;
    private int cost;
    private ItemStack catalyst;

    public RuneUpgradeEvent(@Nullable Player player, RuneData rune, RuneData result, int cost, ItemStack catalyst) {
        this.player = player;
        this.rune = rune;
        this.result = result;
        this.cost = cost;
        this.catalyst = catalyst;
    }

    public @Nullable Player getPlayer() {
        return player;
    }

    /** {@return the rune being upgraded, both inputs hold it} */
    public RuneData getRune() {
        return rune;
    }

    /** {@return the rune the ritual makes, one level higher} */
    public RuneData getResult() {
        return result;
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
