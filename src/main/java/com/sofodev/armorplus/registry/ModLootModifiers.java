package com.sofodev.armorplus.registry;

import com.mojang.serialization.MapCodec;
import com.sofodev.armorplus.registry.item.tool.AutoSmeltLootModifier;
import com.sofodev.armorplus.registry.rune.RuneLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);

    /** Adds world runes to the loot tables listed in the "runeLootSources" config. */
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<RuneLootModifier>> RUNES = LOOT_MODIFIERS.register("runes", () -> RuneLootModifier.CODEC);

    /** Smelts the drops of blocks mined with Infused Lava tools and Auto-Smelt tool runes. Has a higher priority than {@link #RUNES}, so it never smelts or replaces runes. */
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AutoSmeltLootModifier>> AUTO_SMELT = LOOT_MODIFIERS.register("auto_smelt", () -> AutoSmeltLootModifier.CODEC);
}
