package com.sofodev.armorplus.api.event;

import com.sofodev.armorplus.api.RuneData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.jspecify.annotations.Nullable;

/**
 * Fired on {@link NeoForge#EVENT_BUS} when a rune that can raise one of the equipment's own effects is in the ritual of a Rune
 * Altar with it, on both sides and whenever the screen needs the result, so it can fire often. Canceling it gives no result;
 * {@link #setCost(int)} changes the experience level cost and {@link #setCatalyst(ItemStack)} the item used up from the pedestals.
 */
public class NativeEffectUpgradeEvent extends Event implements ICancellableEvent {

    private final @Nullable Player player;
    private final ItemStack gear;
    private final RuneData rune;
    private final int level;
    private int cost;
    private ItemStack catalyst;

    public NativeEffectUpgradeEvent(@Nullable Player player, ItemStack gear, RuneData rune, int level, int cost, ItemStack catalyst) {
        this.player = player;
        this.gear = gear;
        this.rune = rune;
        this.level = level;
        this.cost = cost;
        this.catalyst = catalyst;
    }

    public @Nullable Player getPlayer() {
        return player;
    }

    /** {@return the equipment being upgraded} */
    public ItemStack getGear() {
        return gear;
    }

    /** {@return the rune being used up, its {@link RuneData#buffId()} is the effect being raised} */
    public RuneData getRune() {
        return rune;
    }

    /** {@return the level the effect will have} */
    public int getLevel() {
        return level;
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
