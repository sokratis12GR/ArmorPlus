package com.sofodev.armorplus.registry.item.tool;

import com.sofodev.armorplus.api.EffectEntry;
import com.sofodev.armorplus.api.EffectScope;
import com.sofodev.armorplus.api.EffectScopes;
import com.sofodev.armorplus.api.RuneEffectType;
import com.sofodev.armorplus.api.event.EffectApplyEvent;
import com.sofodev.armorplus.api.event.RuneApplyEvent;
import com.sofodev.armorplus.api.event.SetEffectApplyEvent;
import com.sofodev.armorplus.config.ArmorPlusConfig.MaterialConfig;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.item.extra.BuffInstance;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.tool.properties.tool.IAPTool;
import com.sofodev.armorplus.registry.rune.Runes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;

import java.util.ArrayList;
import java.util.List;

/**
 * The weapon (on-hit) effects of the item a player holds in the main hand: the material's own on-hit effects of ArmorPlus
 * weapons and tools, and the socketed weapon runes.
 * <p>
 * Separate from the armor and tool effects: weapon effects have their own screen (Weapon Effects key) and toggles. Mobs
 * and other non-player attackers always use every effect.
 */
public final class WeaponEffects {

    /**
     * The weapon scope, use {@link EffectScopes#WEAPON}. Sources are {@link EffectEntry#MATERIAL} (first) and {@link EffectEntry#RUNE},
     * toggle keys the effect id. Not cached: the held item's runes and levels change without an event.
     */
    public static final EffectScope SCOPE = new Scope();

    private WeaponEffects() {
    }

    private static final class Scope extends EffectScope {

        private Scope() {
            super("weapon", ModAttachments.DISABLED_WEAPON_EFFECTS, false);
        }

        /** Material effects first, then runes. */
        @Override
        protected List<EffectEntry> collect(LivingEntity entity) {
            ItemStack weapon = entity.getMainHandItem();
            List<EffectEntry> entries = new ArrayList<>();
            IAPTool mat = Runes.toolMaterial(weapon.getItem());
            if (mat != null && mat.config().enableWeaponEffects().get()) {
                for (BuffInstance buff : Runes.nativeBuffs(weapon, mat.getBuffInstances().get())) entries.add(entry(entity, buff, EffectEntry.MATERIAL));
            }
            for (BuffInstance buff : Runes.weaponRunes(weapon)) entries.add(entry(entity, buff, EffectEntry.RUNE));
            return entries;
        }
    }


    /** Applies the material's own on-hit effects to the target (fires {@link SetEffectApplyEvent}). */
    public static void hit(ItemStack stack, IAPTool mat, LivingEntity target, LivingEntity attacker) {
        String material = mat.config() instanceof MaterialConfig cfg ? cfg.name() : "";
        for (BuffInstance buff : Runes.nativeBuffs(stack, mat.getBuffInstances().get())) {
            if (!isSkipped(buff, attacker)) {
                SetEffectApplyEvent event = new SetEffectApplyEvent(attacker, buff, EffectApplyEvent.Context.HIT, stack, target, material);
                if (!NeoForge.EVENT_BUS.post(event).isCanceled()) buff.hitEntity(stack, target, attacker);
            }
        }
    }

    /** Applies the socketed weapon runes to the target (fires {@link RuneApplyEvent}). */
    public static void hitRunes(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        for (BuffInstance buff : Runes.weaponRunes(stack)) {
            if (!isSkipped(buff, attacker)) {
                RuneApplyEvent event = new RuneApplyEvent(attacker, buff, EffectApplyEvent.Context.HIT, stack, target);
                if (!NeoForge.EVENT_BUS.post(event).isCanceled()) buff.hitEntity(stack, target, attacker);
            }
        }
    }

    /** {@link RuneEffectType#onKill} of the abilities of the weapon's material and its socketed weapon runes. */
    public static void kill(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        List<BuffInstance> buffs = new ArrayList<>(Runes.weaponRunes(stack));
        IAPTool mat = Runes.toolMaterial(stack.getItem());
        if (mat != null && mat.config().enableWeaponEffects().get()) buffs.addAll(Runes.nativeBuffs(stack, mat.getBuffInstances().get()));
        for (BuffInstance buff : buffs) {
            if (!isSkipped(buff, attacker) && buff.getBuff() instanceof RuneEffectType type) type.onKill(stack, target, attacker);
        }
    }

    /** @return true if a player attacker switched the effect off */
    private static boolean isSkipped(BuffInstance buff, LivingEntity attacker) {
        return SCOPE.isDisabled(attacker, BuffParser.idOf(buff.getBuff()));
    }
}
