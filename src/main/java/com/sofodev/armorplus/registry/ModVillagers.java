package com.sofodev.armorplus.registry;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public class ModVillagers {

    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(Registries.VILLAGER_PROFESSION, MODID);

    public static final DeferredHolder<VillagerProfession, VillagerProfession> SOUL_EXCHANGER = PROFESSIONS.register("soul_exchanger", () -> {
        Int2ObjectOpenHashMap<ResourceKey<TradeSet>> trades = new Int2ObjectOpenHashMap<>();
        for (int level = 1; level <= 5; level++) {
            trades.put(level, ResourceKey.create(Registries.TRADE_SET,
                    Identifier.fromNamespaceAndPath(MODID, "soul_exchanger/level_" + level)));
        }

        return new VillagerProfession(
                Component.translatable("entity.minecraft.villager.armorplus.soul_exchanger"),
                holder -> holder.is(ModPoI.EXCHANGER_POI.getKey()),
                holder -> holder.is(ModPoI.EXCHANGER_POI.getKey()),
                ImmutableSet.of(),
                ImmutableSet.of(),
                SoundEvents.VILLAGER_WORK_CLERIC,
                trades
        );
    });
}
