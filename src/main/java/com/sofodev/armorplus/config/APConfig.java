package com.sofodev.armorplus.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static java.util.Arrays.asList;

public class APConfig {

    /**
     * The SERVER configuration file, it is loaded on the server and is synced to the clients.
     */
    public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    public static class ServerConfig {

        //WorldGen
        //WorldGen.Lava_Crystal
        public static final String LAVA_CRYSTAL = "ore_lava_crystal";
        public final ConfigValue<List<Boolean>> enableOreLavaWorldGen;
        public final ConfigValue<List<Integer>> lavaCrystalVeinList;
        public final ConfigValue<List<Integer>> lavaCrystalOffsetList;
        public final ConfigValue<List<Integer>> lavaCrystalRangeList;
        //WorldGen.Frost_Crystal
        public static final String FROST_CRYSTAL = "ore_frost_crystal";
        public final ConfigValue<List<Boolean>> enableOreFrostWorldGen;
        public final ConfigValue<List<Integer>> frostCrystalVeinList;
        public final ConfigValue<List<Integer>> frostCrystalOffsetList;
        public final ConfigValue<List<Integer>> frostCrystalRangeList;
        //Advancements
        public final ConfigValue<Boolean> enableThankYouAdvancement;
        //Armor
        //    public final ConfigValue<List<Buff>> coalArmorBuffs;

        //Tests
        //    public final ConfigValue<Integer> coalArmor;
        //    public final ConfigValue<List<Boolean>> TEST_LIST;
        //    public final ConfigValue<List<Integer>> TEST_INT_LIST;

        ServerConfig(Builder builder) {
            builder.push("world_gen");
            //Lava Crystal
            builder.comment("Customize ore lava crystal stone, obsidian, compressed world generation (in that order)").push("lava_crystal");
            enableOreLavaWorldGen = this.withDefaultEnabled(builder, LAVA_CRYSTAL);
            lavaCrystalVeinList = this.withDefaultVeinSize(builder, LAVA_CRYSTAL);
            lavaCrystalOffsetList = this.withDefaultOffset(builder, LAVA_CRYSTAL);
            lavaCrystalRangeList = this.withDefaultRange(builder, LAVA_CRYSTAL);
            builder.pop();
            //Frost Crystal
            builder.comment("Customize ore frost crystal stone, obsidian, compressed world generation (in that order)").push("frost_crystal");
            enableOreFrostWorldGen = this.withDefaultEnabled(builder, FROST_CRYSTAL);
            frostCrystalVeinList = this.withDefaultVeinSize(builder, FROST_CRYSTAL);
            frostCrystalOffsetList = this.withDefaultOffset(builder, FROST_CRYSTAL);
            frostCrystalRangeList = this.withDefaultRange(builder, FROST_CRYSTAL);
            //Advancements
            builder.pop().pop();
            builder.push("advancements").comment("Configure advancements");
            enableThankYouAdvancement = builder.comment("Enable/Disable the Thank You 6M advancement")
                    .translation(MODID + ".config.advancement.thank_you_6m")
                    .define("enableThankYou6M", true);
            //CUSTOM_MAP_TEST = withValues(builder, "list", 4, 3, 12, 4, 3, 12, 4, 3, 12, 123);
            // builder.pop();
            // builder.push("test").comment("Please Ignore");
            //TEST = builder.comment("Test: Please Ignore")
            //        .translation(MODID + ".config.test")
            //        .define("TEST", 1);
            //TEST_LIST = builder.comment("Test List: Please Ignore")
            //        .translation(MODID + ".config.test_list")
            //        .define("_boolean", asList(true, true, false, true));
            //TEST_INT_LIST = builder.comment("Test List: Please Ignore")
            //        .translation(MODID + ".config.test_int_list")
            //        .define("_int", asList(1, 2, 1, 3));
            //ENUM_TEST = builder.comment("Enum Test: Please Ignore")
            //        .translation(MODID + ".config.test_enum")
            //        .defineEnum("_enum", Buff.NONE);
            //ENUM_LIST_TEST = builder.comment("Enum List Test: Please Ignore", "Allowed Values: ",
            //        Arrays.stream(DeBuff.values()).map(Enum::name).collect(Collectors.joining(", ")))
            //        .translation(MODID + ".config.test_enum_list")
            //        .define("_enum_list", asList(DeBuff.NONE, DeBuff.IGNITE));

            builder.pop();
        }

        public ConfigValue<List<Boolean>> withDefaultEnabled(Builder builder, String category) {
            return this.withValue(builder, category, "enable", asList(true, true, true),
                    "Enable/Disable their world generation"
            );
        }

        public ConfigValue<List<Integer>> withDefaultVeinSize(Builder builder, String category) {
            return this.withValue(builder, category, "vein_size", asList(5, 4, 3),
                    "Set the vein size"
            );
        }

        public ConfigValue<List<Integer>> withDefaultOffset(Builder builder, String category) {
            return this.withValue(builder, category, "offset", asList(12, 6, 0),
                    "Set the Y offset, Example: ", "Offset: 4 - Sets the minimum Y height to 4."
            );
        }

        public ConfigValue<List<Integer>> withDefaultRange(Builder builder, String category) {
            return this.withValue(builder, category, "range", asList(20, 10, 4),
                    "Set the vein size, Example: ", "Range: 8 - Sets the maximum Y height to 8."
            );
        }

        public <T> ConfigValue<T> withValue(Builder builder, String category, String name, T defaultValues, String... comments) {
            return builder.comment(comments).translation(MODID + ".config." + category + "_" + name).define(name, defaultValues);
        }
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
                .writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}