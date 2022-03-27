package com.sofodev.armorplus.config;

import com.sofodev.armorplus.config.ConfigHelper.ConfigValueListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODNAME;
import static java.util.Arrays.asList;

public class ArmorPlusConfig {

    public static MaterialConfig coalMaterial;
    public static MaterialConfig lapisMaterial;
    public static MaterialConfig redstoneMaterial;
    public static MaterialConfig chickenMaterial;
    public static MaterialConfig slimeMaterial;
    public static MaterialConfig emeraldMaterial;
    public static MaterialConfig obsidianMaterial;
    public static MaterialConfig infusedLavaMaterial;
    public static MaterialConfig frostMaterial;
    public static MaterialConfig frostLavaMaterial;
    public static MaterialConfig guardianMaterial;
    public static MaterialConfig superStarMaterial;
    public static MaterialConfig enderDragonMaterial;
    public static MaterialConfig slayerMaterial;
    public static MaterialConfig enhancedMaterial;
    //Advancements
    public static ConfigValueListener<Boolean> enableThankYouAdvancement;
    public static ConfigValueListener<List<? extends String>> autoSmeltingInput;
    public static ConfigValueListener<List<? extends String>> autoSmeltingOutput;

    public static OreConfig oreLavaCrystalStone;
    public static OreConfig oreLavaCrystalObsidian;
    public static OreConfig oreLavaCrystalCompressed;
    public static OreConfig oreFrostCrystalStone;
    public static OreConfig oreFrostCrystalObsidian;
    public static OreConfig oreFrostCrystalCompressed;

    public static ConfigValueListener<List<? extends String>> enchantsThatWontWorkWithSoulHarden;
    public static BossDropConfig witherBossDrops;
    public static BossDropConfig enderDragonDrops;
    public static BossDropConfig elderGuardianDrops;
    public static DropConfig witherSkeletonDrops;
    public static DropConfig guardianDrops;
    public static DropConfig endermanDrops;
    public static DropConfig blazeDrops;

    public ArmorPlusConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
        //Advancements
        builder.comment("Configure advancements")
                .push("advancements");
        enableThankYouAdvancement = subscriber.subscribe(builder.comment("Enable/Disable the Thank You 6M advancement")
                .define("enableThankYou6M", true));
        builder.pop();
        builder.comment("Configure Items")
                .push("items");
        coalMaterial = new MaterialConfig(builder, subscriber, "coal");
        lapisMaterial = new MaterialConfig(builder, subscriber, "lapis");
        redstoneMaterial = new MaterialConfig(builder, subscriber, "redstone");
        chickenMaterial = new MaterialConfig(builder, subscriber, "chicken");
        slimeMaterial = new MaterialConfig(builder, subscriber, "slime");
        emeraldMaterial = new MaterialConfig(builder, subscriber, "emerald");
        obsidianMaterial = new MaterialConfig(builder, subscriber, "obsidian");
        infusedLavaMaterial = new MaterialConfig(builder, subscriber, "infused_lava");
        builder.comment("Infused Lava Extra ")
                .push("infused_lava.extra");
        autoSmeltingInput = subscriber.subscribe(builder.comment("Infused Lava Tools: Smelting Recipe Input (Blocks)")
                .defineList("smeltingInput", asList("minecraft:iron_ore", "minecraft:gold_ore",
                                "minecraft:sand", "minecraft:sandstone", "minecraft:wet_sponge", "minecraft:clay", "minecraft:stone_bricks", "minecraft:cobblestone", "minecraft:stone",
                                "minecraft:acacia_log", "minecraft:birch_log", "minecraft:dark_oak_log", "minecraft:jungle_log", "minecraft:oak_log", "minecraft:spruce_log",
                                "minecraft:netherrack", "minecraft:ancient_debris", "minecraft:stone_bricks", "armorplus:ore_lava_crystal", "armorplus:ore_frost_crystal"),
                        s -> ResourceLocation.tryParse((String) s) != null));
        autoSmeltingOutput = subscriber.subscribe(builder.comment("Infused Lava Tools: Smelting Recipe Output (Items)")
                .defineList("smeltingOutput", asList("minecraft:iron_ingot", "minecraft:gold_ingot", "minecraft:glass", "minecraft:smooth_sandstone", "minecraft:sponge",
                                "minecraft:terracotta", "minecraft:cracked_stone_bricks", "minecraft:stone", "minecraft:stone",
                                "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal",
                                "minecraft:nether_brick", "minecraft:netherite_scrap", "minecraft:cracked_stone_bricks", "armorplus:infused_lava_crystal", "armorplus:infused_frost_crystal"),
                        s -> ResourceLocation.tryParse((String) s) != null));
        builder.pop(2);
        frostMaterial = new MaterialConfig(builder, subscriber, "frost");
        frostLavaMaterial = new MaterialConfig(builder, subscriber, "frost_lava");
        guardianMaterial = new MaterialConfig(builder, subscriber, "guardian");
        superStarMaterial = new MaterialConfig(builder, subscriber, "super_star");
        enderDragonMaterial = new MaterialConfig(builder, subscriber, "ender_dragon");
        slayerMaterial = new MaterialConfig(builder, subscriber, "slayer");
        enhancedMaterial = new MaterialConfig(builder, subscriber, "enhanced");
        builder.pop();
        builder.push("world_gen");
        oreLavaCrystalStone = new OreConfig(builder, subscriber, "lava_crystal_stone", 5, 0.5, 0, 200);
        oreLavaCrystalObsidian = new OreConfig(builder, subscriber, "lava_crystal_obsidian", 4, 0.3, -30, 0);
        oreLavaCrystalCompressed = new OreConfig(builder, subscriber, "lava_crystal_compressed", 3, 0.1, -60, -30);
        oreFrostCrystalStone = new OreConfig(builder, subscriber, "frost_crystal_stone", 5, 0.5, 50, 200);
        oreFrostCrystalObsidian = new OreConfig(builder, subscriber, "frost_crystal_obsidian", 4, 0.3, -30, 0);
        oreFrostCrystalCompressed = new OreConfig(builder, subscriber, "frost_crystal_compressed", 3, 0.1, -60, -30);
        builder.pop();
        builder.comment("Enchantment Configuration").push("enchantments");
        enchantsThatWontWorkWithSoulHarden = subscriber.subscribe(builder.comment("is a list of registry names that will not work with the enchantment \"Soul Harden\"")
                .defineList("disallowWithSoulHardenList",
                        asList("minecraft:mending", "minecraft:unbreaking", "minecraft:vanishing_curse"),
                        s -> ResourceLocation.tryParse((String) s) != null)
        );
        builder.pop();
        builder.comment("Mob Drops Configuration").push("drops");
        witherBossDrops = new BossDropConfig(builder, subscriber, "wither_boss");
        enderDragonDrops = new BossDropConfig(builder, subscriber, "ender_dragon");
        elderGuardianDrops = new BossDropConfig(builder, subscriber, "elder_guardian");
        witherSkeletonDrops = new DropConfig(builder, subscriber, "wither_skeleton");
        guardianDrops = new DropConfig(builder, subscriber, "guardian");
        endermanDrops = new DropConfig(builder, subscriber, "enderman");
        blazeDrops = new DropConfig(builder, subscriber, "blaze");
        builder.pop();
    }

    public static class OreConfig {
        public ConfigValueListener<Boolean> enabled;
        public ConfigValueListener<Integer> veinSize;
        public ConfigValueListener<Integer> offset;
        public ConfigValueListener<Double> exposure;
        public ConfigValueListener<Integer> minY;
        public ConfigValueListener<Integer> maxY;

        public OreConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String name, int vein, double exposure, int minY, int maxY) {
            builder.comment("Customize the world generation of ore").push(name);
            this.enabled = subscriber.subscribe(builder.comment("enable/disable their world generation")
                    .define("enable", true));
            this.veinSize = subscriber.subscribe(builder.comment("Set the vein size")
                    .defineInRange("vein_size", vein, 0, 254));
            this.exposure = subscriber.subscribe(builder.comment("Set the air exposure %, Example: ", "0.0% will have no air exposed ores generated, 1.0 will make all ores be air exposed")
                    .defineInRange("exposure", exposure, 0.0D, 1.0D));
            this.minY = subscriber.subscribe(builder.comment("Set the min Y, Example: ", "minY: -30 - Sets the minimum Y height to -30.")
                    .defineInRange("minY", minY, -63, 318));
            this.maxY = subscriber.subscribe(builder.comment("Set the max Y, Example: ", "maxY: 280 - Sets the maximum Y height to 200.")
                    .defineInRange("maxY", maxY, -63, 318));

            builder.pop();
        }
    }

    public static class MaterialConfig {
        public ConfigValueListener<Boolean> enableArmorEffects;
        public ConfigValueListener<Boolean> enableWeaponEffects;

        public MaterialConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String name) {
            builder.comment(name + " equipment Configuration")
                    .push(name);
            enableArmorEffects = subscriber.subscribe(builder.comment(name + " armor: enable/disable full set effects")
                    .define("enableArmorEffects", true));
            enableWeaponEffects = subscriber.subscribe(builder.comment(name + " weapons: enable/disable on hit effects")
                    .define("enableWeaponEffects", true));
            builder.pop();
        }
    }

    public static class DropConfig {
        public ConfigValueListener<Boolean> enableRegularDrops;
        public ConfigValueListener<Boolean> enableSoulDrops; //Obtained via Soul Stealer

        public DropConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String name) {
            builder.comment(name + " drop configuration")
                    .push(name);
            enableRegularDrops = subscriber.subscribe(builder.comment(MODNAME + "'s " + name + " regular drops: enable/disable")
                    .define("enableRegularDrops", true));
            enableSoulDrops = subscriber.subscribe(builder.comment(MODNAME + "'s " + name + " soul drops: enable/disable")
                    .define("enableSoulDrops", true));
            builder.pop();
        }
    }

    public static class BossDropConfig {
        public ConfigValueListener<Boolean> enableTrophyDrops;
        public ConfigValueListener<Boolean> enableRegularDrops;
        public ConfigValueListener<Boolean> enableSoulDrops; //Obtained via Soul Stealer

        public BossDropConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String name) {
            builder.comment(name + " drop configuration")
                    .push(name);
            enableTrophyDrops = subscriber.subscribe(builder.comment(MODNAME + "'s " + name + " trophy drops: enable/disable")
                    .define("enableTrophyDrops", true));
            enableRegularDrops = subscriber.subscribe(builder.comment(MODNAME + "'s " + name + " regular drops: enable/disable")
                    .define("enableRegularDrops", true));
            enableSoulDrops = subscriber.subscribe(builder.comment(MODNAME + "'s " + name + " soul drops: enable/disable")
                    .define("enableSoulDrops", true));
            builder.pop();
        }
    }
}