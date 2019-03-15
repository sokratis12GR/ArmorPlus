package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.tileentity.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;
import java.util.function.Supplier;

import static com.sofodev.armorplus.ArmorPlus.MODID;

public enum ModTileEntities {
    WORKBENCH(TileWB::new),
    HIGH_TECH_BENCH(TileHTB::new),
    ULTI_TECH_BENCH(TileUTB::new),
    CHAMPION_BENCH(TileCB::new),
    LAVA_INFUSER(TileLavaInfuser::new),
    TROPHY(TileTrophy::new),
    ;

    private final LazyLoadBase<TileEntityType<?>> type;

    ModTileEntities(Supplier<TileEntity> tileEntitySupplier) {
        this.type = new LazyLoadBase<>(() ->
            TileEntityType.Builder.create(tileEntitySupplier).build(null));
    }

    public TileEntityType<?> type() {
        return type.getValue();
    }

    public static void registerAll(RegistryEvent.Register<TileEntityType<?>> event) {
        if (!event.getName().equals(ForgeRegistries.TILE_ENTITIES.getRegistryName())) return;

        for (ModTileEntities tileEnum : values()) {
            register(tileEnum.name().toLowerCase(Locale.ROOT), tileEnum.type());
        }
    }

    private static <T extends TileEntity> void register(String name, TileEntityType<T> type) {
        ResourceLocation id = new ResourceLocation(MODID, name);
        type.setRegistryName(id);
        ForgeRegistries.TILE_ENTITIES.register(type);
    }
}