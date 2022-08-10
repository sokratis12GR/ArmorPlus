package com.sofodev.armorplus.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.utils.Utils.setRL;

public class ModPoI {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, MODID);

    public static final RegistryObject<PoiType> EXCHANGER_POI = registerPOI("soul_exchanger", ModBlocks.SOUL_BOX, 1, 1);

    public static RegistryObject<PoiType> registerPOI(String name, Supplier<? extends PoiType> sup) {
        return POI_TYPES.register(name, sup);
    }

    private static RegistryObject<PoiType> registerPOI(String key, Supplier<Block> state, int maxFreeTickets, int validRange) {
        return registerPOI(key, () -> new PoiType(getBlockStates(state.get()), maxFreeTickets, validRange));
    }

    private static RegistryObject<PoiType> registerPOI(String key, Supplier<Block> state, int maxFreeTickets, Predicate<PoiType> predicate, int validRange) {
        return registerPOI(key, () -> new PoiType(getBlockStates(state.get()), maxFreeTickets, validRange));
    }

    private static Set<BlockState> getBlockStates(Block state) {
        return ImmutableSet.copyOf(state.getStateDefinition().getPossibleStates());
    }

    public static Supplier<Set<BlockState>> getAllStates() {
        return () -> ImmutableSet.copyOf(ForgeRegistries.BLOCKS.getValue(setRL("soul_box"))
                .getStateDefinition()
                .getPossibleStates());
    }

}
