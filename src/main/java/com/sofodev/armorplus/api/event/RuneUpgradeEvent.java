package com.sofodev.armorplus.api.event;

import com.sofodev.armorplus.api.RuneData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.jetbrains.annotations.Nullable;

/**
 * Fired on {@code NeoForge.EVENT_BUS} when two identical runes that can be upgraded are in the ritual of a Rune Altar, on both
 * sides and whenever the screen needs the result, so it can fire often. Canceling it gives no result;
 * {@link #setCost(int)} changes the experience level cost and {@link #setCatalyst(ItemStack)} and {@link #setTypeCatalyst(ItemStack)} the items used up from the pedestals.
 */
public class RuneUpgradeEvent extends Event implements ICancellableEvent {

    private final @Nullable Player player;
    private final RuneData rune;
    private final RuneData result;
    private int cost;
    private ItemStack catalyst;
    private ItemStack typeCatalyst;

    public RuneUpgradeEvent(@Nullable Player player, RuneData rune, RuneData result, int cost, ItemStack catalyst, ItemStack typeCatalyst) {
        this.player = player;
        this.rune = rune;
        this.result = result;
        this.cost = cost;
        this.catalyst = catalyst;
        this.typeCatalyst = typeCatalyst;
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

    /** {@return the item and count for the rune's type used up from the pedestals on top of the catalyst, EMPTY for none} */
    public ItemStack getTypeCatalyst() {
        return typeCatalyst;
    }

    public void setTypeCatalyst(ItemStack typeCatalyst) {
        this.typeCatalyst = typeCatalyst.copy();
    }
}
