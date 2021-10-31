package com.sofodev.armorplus.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class APConfig {

    /**
     * The COMMON configuration file, it is loaded on both the client and server and is NOT synced to the clients.
     */
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
    }

    //public static void loadConfig(ForgeConfigSpec config, String path) {
    //    final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
    //            .writingMode(WritingMode.REPLACE).build();
    //    file.load();
    //    config.setConfig(file);
    //}

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading configEvent) {
    }

    public static class CommonConfig {
        public final OreConfig oreLavaCrystalStone;
        public final OreConfig oreLavaCrystalObsidian;
        public final OreConfig oreLavaCrystalCompressed;
        public final OreConfig oreFrostCrystalStone;
        public final OreConfig oreFrostCrystalObsidian;
        public final OreConfig oreFrostCrystalCompressed;

        public CommonConfig(Builder builder) {
            builder.push("world_gen");
            oreLavaCrystalStone = new OreConfig(builder, "lava_crystal_stone", 5, 12, 20);
            oreLavaCrystalObsidian = new OreConfig(builder, "lava_crystal_obsidian", 4, 6, 10);
            oreLavaCrystalCompressed = new OreConfig(builder, "lava_crystal_compressed", 3, 0, 4);
            oreFrostCrystalStone = new OreConfig(builder, "frost_crystal_stone", 5, 12, 20);
            oreFrostCrystalObsidian = new OreConfig(builder, "frost_crystal_obsidian", 4, 6, 10);
            oreFrostCrystalCompressed = new OreConfig(builder, "frost_crystal_compressed", 3, 0, 4);
            builder.pop();
        }

        public static class OreConfig {
            public final BooleanValue enabled;
            public final IntValue veinSize;
            public final IntValue offset;
            public final IntValue range;

            public OreConfig(ForgeConfigSpec.Builder builder, String name, int vein, int offset, int range) {
                builder.comment("Customize the world generation of ore").push(name);
                this.enabled = builder.comment("enable/disable their world generation")
                        .define("enable", true);
                this.veinSize = builder.comment("Set the vein size")
                        .defineInRange("vein_size", vein, 0, 254);
                this.offset = builder.comment("Set the Y offset, Example: ", "Offset: 4 - Sets the minimum Y height to 4.")
                        .defineInRange("offset", offset, 0, 254);
                this.range = builder.comment("Set the vein size, Example: ", "Range: 8 - Sets the maximum Y height to 8.")
                        .defineInRange("range", range, 0, 254);
                builder.pop();
            }
        }
    }

}