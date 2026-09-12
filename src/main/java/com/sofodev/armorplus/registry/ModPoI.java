package com.sofodev.armorplus.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.utils.Utils.setRL;

public class ModPoI {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, MODID);

    public static final DeferredHolder<PoiType, PoiType> EXCHANGER_POI = registerPOI("soul_exchanger", ModBlocks.SOUL_BOX, 1, 10);

    public static DeferredHolder<PoiType, PoiType> registerPOI(String name, Supplier<? extends PoiType> sup) {
        return POI_TYPES.register(name, sup);
    }

    private static DeferredHolder<PoiType, PoiType> registerPOI(String key, Supplier<Block> state, int maxFreeTickets, int validRange) {
        return registerPOI(key, () -> new PoiType(getBlockStates(state.get()), maxFreeTickets, validRange));
    }

    private static DeferredHolder<PoiType, PoiType> registerPOI(String key, Supplier<Block> state, int maxFreeTickets, Predicate<PoiType> predicate, int validRange) {
        return registerPOI(key, () -> new PoiType(getBlockStates(state.get()), maxFreeTickets, validRange));
    }

    private static Set<BlockState> getBlockStates(Block state) {
        return ImmutableSet.copyOf(state.getStateDefinition().getPossibleStates());
    }

    public static Supplier<Set<BlockState>> getAllStates() {
        return () -> ImmutableSet.copyOf(BuiltInRegistries.BLOCK.get(setRL("soul_box"))
                .getStateDefinition()
                .getPossibleStates());
    }

}
