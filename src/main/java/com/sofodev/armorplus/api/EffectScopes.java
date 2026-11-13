package com.sofodev.armorplus.api;

import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.tool.RangedEffects;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.armorplus.registry.item.tool.WeaponEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Every {@link EffectScope}, by name. The toggle payload, "/armorplus effects" and the API go through these, so a scope
 * added here can be switched on/off, listed and reset like the built-in ones. Register a scope during mod construction, on
 * both sides, so the client and the server know the same names.
 */
public final class EffectScopes {

    private static final Map<String, EffectScope> SCOPES = Collections.synchronizedMap(new LinkedHashMap<>());

    public static final EffectScope ARMOR = register(ArmorEffects.SCOPE);
    public static final EffectScope TOOL = register(ToolEffects.SCOPE);
    public static final EffectScope WEAPON = register(WeaponEffects.SCOPE);
    public static final EffectScope RANGED = register(RangedEffects.SCOPE);

    private EffectScopes() {
    }

    /** @throws IllegalStateException if a scope with the name is already registered */
    public static <T extends EffectScope> T register(T scope) {
        if (SCOPES.putIfAbsent(scope.name(), scope) != null) throw new IllegalStateException("Duplicate effect scope: " + scope.name());
        return scope;
    }

    /** {@return the scope with the name, or null} */
    public static @Nullable EffectScope byName(String name) {
        return SCOPES.get(name);
    }

    /** Forgets the entity's cached effects of every scope, e.g. when its equipment changes. */
    public static void invalidateAll(LivingEntity entity) {
        all().forEach(scope -> scope.invalidate(entity));
    }

    /** Forgets every entity's cached effects of every scope, e.g. when the config reloads. */
    public static void clearAllCaches() {
        all().forEach(EffectScope::clearCaches);
    }

    /** {@return every scope, in the order they were registered} */
    public static Collection<EffectScope> all() {
        synchronized (SCOPES) {
            return List.copyOf(SCOPES.values());
        }
    }
}
