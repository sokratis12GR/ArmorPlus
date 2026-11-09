package com.sofodev.armorplus.registry.rune;

import com.sofodev.armorplus.api.RuneEffectType;
import com.sofodev.armorplus.registry.item.extra.Buff;
import com.sofodev.armorplus.registry.item.extra.BuffParser;
import com.sofodev.armorplus.registry.item.extra.DeBuff;
import com.sofodev.armorplus.registry.item.extra.IBuff;
import com.sofodev.armorplus.registry.item.extra.RangedBuff;
import com.sofodev.armorplus.registry.item.extra.ToolBuff;
import net.minecraft.core.Registry;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * The {@code armorplus:rune_effect_type} registry of abilities ({@link RuneEffectType}).
 * <p>
 * The ArmorPlus abilities ({@link Buff}, {@link DeBuff}, {@link ToolBuff} and {@link RangedBuff} constants that aren't mob effects) are
 * registered as {@link Builtin} wrappers; {@link BuffParser} resolves those to the enum constants themselves, so the rest of
 * the mod keeps comparing against the constants.
 */
public final class RuneEffectTypes {

    public static final ResourceKey<Registry<RuneEffectType>> REGISTRY_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MODID, "rune_effect_type"));

    public static final DeferredRegister<RuneEffectType> RUNE_EFFECT_TYPES = DeferredRegister.create(REGISTRY_KEY, MODID);
    public static final Registry<RuneEffectType> REGISTRY = RUNE_EFFECT_TYPES.makeRegistry(builder -> {
    });

    /** Wraps an ArmorPlus enum ability. */
    public record Builtin(IBuff buff, Set<BuffParser.Use> uses) implements RuneEffectType {
        @Override
        public String name() {
            return buff.name();
        }

        @Override
        public @Nullable Component getDescription() {
            String key = "armorplus.buff." + buff.name().toLowerCase(Locale.ENGLISH) + ".description";
            return Language.getInstance().has(key) ? Component.translatable(key) : null;
        }
    }

    static {
        register(Buff.values(), EnumSet.of(BuffParser.Use.ARMOR));
        register(DeBuff.values(), EnumSet.of(BuffParser.Use.WEAPON, BuffParser.Use.TOOL, BuffParser.Use.RANGED));
        register(ToolBuff.values(), EnumSet.of(BuffParser.Use.TOOL));
        register(RangedBuff.values(), EnumSet.of(BuffParser.Use.RANGED));
    }

    private RuneEffectTypes() {
    }

    private static void register(IBuff[] buffs, Set<BuffParser.Use> uses) {
        for (IBuff buff : buffs) {
            if (buff.isEffect() || buff.name().equals("NONE")) continue;
            Builtin type = new Builtin(buff, Set.copyOf(uses));
            RUNE_EFFECT_TYPES.register(buff.name().toLowerCase(Locale.ENGLISH), () -> type);
        }
    }

    public static void register(IEventBus modBus) {
        RUNE_EFFECT_TYPES.register(modBus);
    }

    /** @return the registered type, or null */
    public static @Nullable RuneEffectType get(ResourceLocation id) {
        return REGISTRY.get(id);
    }

    public static @Nullable ResourceLocation idOf(RuneEffectType type) {
        return REGISTRY.getKey(type);
    }
}
