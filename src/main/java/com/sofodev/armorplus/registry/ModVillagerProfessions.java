package com.sofodev.armorplus.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModPoI.EXCHANGER_POI;
import static com.sofodev.armorplus.utils.Utils.setRL;

public class ModVillagerProfessions {

    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MODID);

    public static final RegistryObject<VillagerProfession> SOUL_EXCHANGER = register("soul_exchanger", EXCHANGER_POI.getKey(), SoundEvents.VILLAGER_WORK_CLERIC);

    public static RegistryObject<VillagerProfession> register(String name, Supplier<? extends VillagerProfession> sup) {
        return PROFESSIONS.register(name, sup);
    }

    private static RegistryObject<VillagerProfession> register(String nameIn, ResourceKey<PoiType> jobSite, @Nullable SoundEvent soundIn) {
        return register(nameIn, (heldJobSite) -> heldJobSite.is(jobSite), (heldJobSite) -> heldJobSite.is(jobSite), soundIn);
    }

    public static RegistryObject<VillagerProfession> register(String nameIn, Predicate<Holder<PoiType>> heldJobSite, Predicate<Holder<PoiType>> acquirableJobSite, @Nullable SoundEvent soundIn) {
        return register(nameIn, heldJobSite, acquirableJobSite, ImmutableSet.of(), ImmutableSet.of(), soundIn);
    }

    public static RegistryObject<VillagerProfession> register(String nameIn, Predicate<Holder<PoiType>> heldJobSite, Predicate<Holder<PoiType>> acquirableJobSite, ImmutableSet<Item> specificItemsIn, ImmutableSet<Block> relatedWorldBlocksIn, @Nullable SoundEvent soundIn) {
        return register(nameIn, () -> new VillagerProfession(nameIn, heldJobSite, acquirableJobSite, specificItemsIn, relatedWorldBlocksIn, soundIn));
    }

}
