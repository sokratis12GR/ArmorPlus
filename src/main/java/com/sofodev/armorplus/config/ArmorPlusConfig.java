//package com.sofodev.armorplus.config;
//
//import com.sofodev.armorplus.config.ConfigHelper.ConfigValueListener;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.common.ForgeConfigSpec;
//
//import java.util.List;
//
//import static java.util.Arrays.asList;
//
//public class ArmorPlusConfig {
//
//    public MaterialConfig coalMaterial;
//    public MaterialConfig lapisMaterial;
//    public MaterialConfig redstoneMaterial;
//    public MaterialConfig chickenMaterial;
//    public MaterialConfig slimeMaterial;
//    public MaterialConfig emeraldMaterial;
//    public MaterialConfig obsidianMaterial;
//    public MaterialConfig infusedLavaMaterial;
//    public MaterialConfig frostMaterial;
//    public MaterialConfig frostLavaMaterial;
//    public MaterialConfig guardianMaterial;
//    public MaterialConfig superStarMaterial;
//    public MaterialConfig enderDragonMaterial;
//    public MaterialConfig slayerMaterial;
//    //Advancements
//    public ConfigValueListener<Boolean> enableThankYouAdvancement;
//    public ConfigValueListener<List<? extends String>> autoSmeltingInput;
//    public ConfigValueListener<List<? extends String>> autoSmeltingOutput;
//
//    public ArmorPlusConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber) {
//        //Advancements
//        builder.comment("Configure advancements")
//                .push("advancements");
//        enableThankYouAdvancement = subscriber.subscribe(builder.comment("Enable/Disable the Thank You 6M advancement")
//                .define("enableThankYou6M", true));
//        builder.pop();
//        builder.comment("Configure Items")
//                .push("items");
//        coalMaterial = new MaterialConfig(builder, subscriber, "coal");
//        lapisMaterial = new MaterialConfig(builder, subscriber, "lapis");
//        redstoneMaterial = new MaterialConfig(builder, subscriber, "redstone");
//        chickenMaterial = new MaterialConfig(builder, subscriber, "chicken");
//        slimeMaterial = new MaterialConfig(builder, subscriber, "slime");
//        emeraldMaterial = new MaterialConfig(builder, subscriber, "emerald");
//        obsidianMaterial = new MaterialConfig(builder, subscriber, "obsidian");
//        infusedLavaMaterial = new MaterialConfig(builder, subscriber, "infused_lava");
//        builder.comment("Infused Lava Extra ")
//                .push("infused_lava.extra");
//        autoSmeltingInput = subscriber.subscribe(builder.comment("Infused Lava Tools: Smelting Recipe Input (Blocks)")
//                .defineList("smeltingInput", asList("minecraft:iron_ore", "minecraft:gold_ore",
//                                "minecraft:sand", "minecraft:sandstone", "minecraft:wet_sponge", "minecraft:clay", "minecraft:stone_bricks", "minecraft:cobblestone", "minecraft:stone",
//                                "minecraft:acacia_log", "minecraft:birch_log", "minecraft:dark_oak_log", "minecraft:jungle_log", "minecraft:oak_log", "minecraft:spruce_log",
//                                "minecraft:netherrack", "minecraft:ancient_debris", "minecraft:stone_bricks"),
//                        s -> ResourceLocation.tryParse((String) s) != null));
//        autoSmeltingOutput = subscriber.subscribe(builder.comment("Infused Lava Tools: Smelting Recipe Output (Items)")
//                .defineList("smeltingOutput", asList("minecraft:iron_ingot", "minecraft:gold_ingot", "minecraft:glass", "minecraft:smooth_sandstone", "minecraft:sponge",
//                                "minecraft:terracotta", "minecraft:cracked_stone_bricks", "minecraft:stone", "minecraft:stone",
//                                "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal",
//                                "minecraft:nether_brick", "minecraft:netherite_scrap", "minecraft:cracked_stone_bricks"),
//                        s -> ResourceLocation.tryParse((String) s) != null));
//        builder.pop(2);
//        frostMaterial = new MaterialConfig(builder, subscriber, "frost");
//        frostLavaMaterial = new MaterialConfig(builder, subscriber, "frost_lava");
//        guardianMaterial = new MaterialConfig(builder, subscriber, "guardian");
//        superStarMaterial = new MaterialConfig(builder, subscriber, "super_star");
//        enderDragonMaterial = new MaterialConfig(builder, subscriber, "ender_dragon");
//        slayerMaterial = new MaterialConfig(builder, subscriber, "slayer");
//        builder.pop();
//    }
//
//    public static class MaterialConfig {
//        public ConfigValueListener<Boolean> enableArmorEffects;
//        public ConfigValueListener<Boolean> enableWeaponEffects;
//
//        public MaterialConfig(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber, String name) {
//            builder.comment(name + " equipment Configuration")
//                    .push(name);
//            enableArmorEffects = subscriber.subscribe(builder.comment(name + " armor: enable/disable full set effects")
//                    .define("enableArmorEffects", true));
//            enableWeaponEffects = subscriber.subscribe(builder.comment(name + " weapons: enable/disable on hit effects")
//                    .define("enableWeaponEffects", true));
//            builder.pop();
//        }
//    }
//}