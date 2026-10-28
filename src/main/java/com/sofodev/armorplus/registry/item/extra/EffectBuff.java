package com.sofodev.armorplus.registry.item.extra;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

import static net.minecraft.core.registries.BuiltInRegistries.MOB_EFFECT;

/**
 * A buff backed by any registered {@link MobEffect} (vanilla or modded), e.g. "minecraft:haste" or "somemod:some_effect".
 * <p>
 * The effect is looked up lazily so that effects registered by other mods resolve once registries are populated.
 */
public record EffectBuff(Identifier id) implements IBuff {

    @Override
    public String name() {
        return id.toString();
    }

    @Override
    public boolean isEffect() {
        return true;
    }

    @Override
    public Holder<MobEffect> getEffect() {
        return MOB_EFFECT.get(id).<Holder<MobEffect>>map(holder -> holder).orElse(null);
    }

    /**
     * Uses the effect's own translation (e.g. "effect.minecraft.haste"), falling back to the raw id if it is not registered.
     */
    public MutableComponent getDisplayName() {
        Holder<MobEffect> effect = getEffect();
        return effect != null ? Component.translatable(effect.value().getDescriptionId()) : Component.literal(id.toString());
    }
}
