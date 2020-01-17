package com.sofodev.armorplus.common.dimension.realmofinsanity;

import com.sofodev.armorplus.common.registry.ModDimensions;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static com.sofodev.armorplus.common.util.Utils.setRL;

public class RealmWorldProvider extends WorldProvider {

    @Override
    protected void init() {
        this.biomeProvider = new BiomeProviderSingle(ForgeRegistries.BIOMES.getValue(setRL("realm_of_insanity")));
        super.init();
    }

    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.REALM;
    }

    @Override
    public String getSaveFolder() {
        return "Realm Of Insanity";
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new RealmChunkGenerator(world);
    }
}