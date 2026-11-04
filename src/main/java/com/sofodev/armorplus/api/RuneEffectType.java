package com.sofodev.armorplus.api;

import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.rune.RuneEffectTypes;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;
import org.jspecify.annotations.Nullable;

import java.util.Set;

/**
 * An ability that rune and config entries can name by its registry id, e.g. {@code "examplemod:lifesteal"} in a buff entry
 * or as the {@code effect} of a datapack rune. Register it in {@link RuneEffectTypes#REGISTRY_KEY}
 * ({@code armorplus:rune_effect_type}) with a {@code DeferredRegister}.
 * <p>
 * The hooks run on the server only, for the players that have the ability and did not switch it off
 * ({@link #onHit} also runs for mobs attacking with a weapon that has the ability, so its attacker may not be a player):
 * <ul>
 *     <li>armor ({@link BuffParser.Use#ARMOR}): {@link #onWornTick} once a second, {@link #onDamageTaken}</li>
 *     <li>tools ({@link BuffParser.Use#TOOL}): {@link #onHeldTick} once a second, {@link #onBlockBreak}, {@link #onHit}</li>
 *     <li>weapons ({@link BuffParser.Use#WEAPON}): {@link #onHit}</li>
 * </ul>
 * The display name uses the translation key {@code rune_effect.<namespace>.<path>}.
 */
public interface RuneEffectType extends IBuff {

    /** @return the gear the ability can be used on; entries naming it for any other use are ignored */
    Set<BuffParser.Use> uses();

    /** Once a second while the armor (set or rune) gives the ability. */
    default void onWornTick(Player player) {
    }

    /** Once a second while the tool held in the main hand gives the ability. */
    default void onHeldTick(Player player, ItemStack tool) {
    }

    /** When the weapon or tool hits a mob in melee. */
    default void onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    }

    /** When the player breaks a block with the tool. The event can be canceled. */
    default void onBlockBreak(Player player, ItemStack tool, BreakBlockEvent event) {
    }

    /** Before the player wearing the armor takes damage, e.g. to change it with {@link LivingDamageEvent.Pre#setNewDamage}. */
    default void onDamageTaken(Player player, LivingDamageEvent.Pre event) {
    }

    /** @return the registry id, or null if the type is not registered */
    default @Nullable Identifier id() {
        return RuneEffectTypes.idOf(this);
    }

    default MutableComponent getDisplayName() {
        Identifier id = id();
        return id != null ? Component.translatable("rune_effect." + id.getNamespace() + "." + id.getPath()) : Component.literal(name());
    }

    @Override
    default String name() {
        Identifier id = id();
        return id != null ? id.toString() : getClass().getSimpleName();
    }

    @Override
    default boolean isEffect() {
        return false;
    }

    @Override
    default @Nullable Holder<MobEffect> getEffect() {
        return null;
    }

    @Override
    default void hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        onHit(stack, target, attacker);
    }
}
