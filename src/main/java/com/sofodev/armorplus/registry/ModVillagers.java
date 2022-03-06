package com.sofodev.armorplus.registry;

import com.google.common.collect.ImmutableSet;
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
import static com.sofodev.armorplus.utils.Utils.setRL;

public class ModVillagers {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, MODID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, MODID);

    public static final RegistryObject<PoiType> EXCHANGER = registerPOI("soul_exchanger", 1, 1);

    public static final RegistryObject<VillagerProfession> SOUL_EXCHANGER = register("soul_exchanger", EXCHANGER, SoundEvents.VILLAGER_WORK_CLERIC);

    public static RegistryObject<PoiType> registerPOI(String name, Supplier<? extends PoiType> sup) {
        return POI_TYPES.register(name, sup);
    }

    private static RegistryObject<PoiType> registerPOI(String key, int maxFreeTickets, int validRange) {
        return registerPOI(key, () -> new PoiType(key, getAllStates().get(), maxFreeTickets, validRange));
    }

    private static RegistryObject<PoiType> registerPOI(String key, int maxFreeTickets, Predicate<PoiType> predicate, int validRange) {
        return registerPOI(key, () -> new PoiType(key, getAllStates().get(), maxFreeTickets, predicate, validRange));
    }

    public static Supplier<Set<BlockState>> getAllStates() {
        return () -> ImmutableSet.copyOf(ForgeRegistries.BLOCKS.getValue(setRL("soul_box")).getStateDefinition().getPossibleStates());
    }

    public static RegistryObject<VillagerProfession> register(String name, Supplier<? extends VillagerProfession> sup) {
        return PROFESSIONS.register(name, sup);
    }

    public static RegistryObject<VillagerProfession> register(String nameIn, Supplier<PoiType> pointOfInterestIn, @Nullable SoundEvent soundIn) {
        return register(nameIn, pointOfInterestIn, ImmutableSet.of(), ImmutableSet.of(), soundIn);
    }

    public static RegistryObject<VillagerProfession> register(String nameIn, Supplier<PoiType> pointOfInterestIn, ImmutableSet<Item> specificItemsIn, ImmutableSet<Block> relatedWorldBlocksIn, @Nullable SoundEvent soundIn) {
        return register(nameIn, () -> new VillagerProfession(nameIn, pointOfInterestIn.get(), specificItemsIn, relatedWorldBlocksIn, soundIn));
    }

}
