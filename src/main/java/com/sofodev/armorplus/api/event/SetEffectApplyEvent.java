package com.sofodev.armorplus.api.event;

import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Fired before an effect of an ArmorPlus material is applied: armor set effects ({@link Context#WORN}) and the on-hit
 * effects of ArmorPlus weapons, tools and the arrows of ArmorPlus bows ({@link Context#HIT}). Cancelable. Socketed runes fire {@link RuneApplyEvent}
 * instead.
 */
public class SetEffectApplyEvent extends EffectApplyEvent {

    private final String material;

    public SetEffectApplyEvent(LivingEntity entity, BuffInstance effect, Context context, ItemStack stack,
                               @Nullable LivingEntity target, String material) {
        super(entity, effect, context, stack, target);
        this.material = material;
    }

    /** {@return the material's config name, e.g. "coal" or "enhanced_iron"} */
    public String getMaterial() {
        return material;
    }
}
