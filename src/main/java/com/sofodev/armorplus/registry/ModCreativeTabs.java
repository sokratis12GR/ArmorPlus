package com.sofodev.armorplus.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackLinkedSet;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.AP_STONE_BRICKS_LENGTH;
import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.registry.block.castle.BrickColor.values;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.getAPItemStack;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    private static final String[] CORE_MATERIALS = {
            "coal", "redstone", "amethyst", "quartz", "glowstone", "honey", "lapis", "emerald",
            "obsidian", "infused_lava", "warden", "guardian", "super_star", "ender_dragon", "slayer"
    };
    private static final String[] EXTRA_MATERIALS = {
            "chainmail", "copper", "iron", "golden", "diamond", "netherite", "chicken", "slime",
            "frost", "frost_lava", "ardite", "cobalt", "knight_slime", "pig_iron", "manyullyn"
    };

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AP_CORE_GROUP = register("core", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(getAPItem("infused_lava_chestplate")))
            .title(Component.translatable("tabs.armorplus.core"))
            .withLabelColor(0xFFAA00)
            .withSearchBar(40)
            .backgroundTexture(Identifier.fromNamespaceAndPath(MODID, "textures/gui/container/creative_inventory/tab_armorplus_small_search.png"))
            .displayItems((featureFlags, output) -> addMaterialsToOutput(output, CORE_MATERIALS)).build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AP_EXTRA_GROUP = register("extra", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(getAPItem("cobalt_chestplate")))
            .title(Component.translatable("tabs.armorplus.extra"))
            .withLabelColor(0x55FFFF)
            .backgroundTexture(Identifier.fromNamespaceAndPath(MODID, "textures/gui/container/creative_inventory/tab_armorplus_small_search.png"))
            .withSearchBar(40)
            .displayItems((featureFlags, output) -> addMaterialsToOutput(output, EXTRA_MATERIALS)).build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AP_ITEM_GROUP = register("items", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.INFUSED_LAVA_CRYSTAL.get()))
            .title(Component.translatable("tabs.armorplus.items"))
            .backgroundTexture(Identifier.fromNamespaceAndPath(MODID, "textures/gui/container/creative_inventory/tab_armorplus.png"))
            .withSearchBar()
            .withLabelColor(0xFF55FF)
            .displayItems((featureFlags, output) -> {
                //bases (9 per material = one row each)
                addBasesToOutput(output, "warden");
                addBasesToOutput(output, "guardian");
                addBasesToOutput(output, "super_star");
                addBasesToOutput(output, "ender_dragon");
                addBasesToOutput(output, "slayer");
                //souls (one row)
                output.accept(ModItems.WARDEN_HEART.get());
                output.accept(ModItems.ELDER_GUARDIAN_SOUL.get());
                output.accept(ModItems.WITHER_BOSS_SOUL.get());
                output.accept(ModItems.ENDER_DRAGON_SOUL.get());
                output.accept(ModItems.SLAYER_SOUL.get());
                output.accept(ModItems.WITHER_SKELETON_SOUL.get());
                output.accept(ModItems.GUARDIAN_SOUL.get());
                output.accept(ModItems.ENDERMAN_SOUL.get());
                output.accept(ModItems.BLAZE_SOUL.get());
                //materials
                output.accept(ModItems.CHAINMAIL.get());
                output.accept(ModItems.OBSIDIAN_STICK.get());
                output.accept(ModItems.WOODEN_ROD.get());
                output.accept(FROST_SHARD.get());
                output.accept(LAVA_SHARD.get());
                output.accept(ModItems.LAVA_CRYSTAL.get());
                output.accept(ModItems.INFUSED_LAVA_CRYSTAL.get());
                output.accept(ModItems.FROST_CRYSTAL.get());
                output.accept(ModItems.INFUSED_FROST_CRYSTAL.get());
                output.accept(ModItems.INFUSED_FROST_LAVA_CRYSTAL.get());
                output.accept(ModItems.GUARDIAN_SCALE.get());
                output.accept(ModItems.WITHER_BONE.get());
                output.accept(ModItems.ENDER_DRAGON_SCALE.get());
                output.accept(ModItems.THE_ULTIMATE_MATERIAL.get());
                output.accept(ModItems.PIG_IRON_INGOT.get());
                output.accept(ModItems.COBALT_INGOT.get());
                output.accept(ModItems.ARDITE_INGOT.get());
                output.accept(ModItems.KNIGHT_SLIME_CRYSTAL.get());
                output.accept(ModItems.MANYULLYN_INGOT.get());
                //enhanced
                output.accept(ENHANCED_CHAINMAIL.get());
                output.accept(ENHANCED_GOLD.get());
                output.accept(ENHANCED_COPPER.get());
                output.accept(ENHANCED_IRON.get());
                output.accept(ENHANCED_DIAMOND.get());
                output.accept(ENHANCED_NETHERITE.get());
                //ammunition
                output.accept(ModItems.ITEM_COAL_ARROW.get());
                output.accept(ModItems.ITEM_REDSTONE_ARROW.get());
                output.accept(ModItems.ITEM_LAPIS_ARROW.get());
                output.accept(ModItems.ITEM_EMERALD_ARROW.get());
                output.accept(ModItems.ITEM_OBSIDIAN_ARROW.get());
                output.accept(ModItems.ITEM_INFUSED_LAVA_ARROW.get());
                output.accept(ModItems.ITEM_GUARDIAN_ARROW.get());
                output.accept(ModItems.ITEM_SUPER_STAR_ARROW.get());
                output.accept(ModItems.ITEM_ENDER_DRAGON_ARROW.get());
                output.accept(ModItems.ITEM_WARDEN_ARROW.get());
                output.accept(ModItems.ITEM_AMETHYST_ARROW.get());
                output.accept(ModItems.ITEM_QUARTZ_ARROW.get());
                output.accept(ModItems.ITEM_GLOWSTONE_ARROW.get());
                output.accept(ModItems.ITEM_HONEY_ARROW.get());
                //misc
                output.accept(ModItems.THANK_YOU.get());
            }).build());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AP_BLOCK_GROUP = register("blocks", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.INFUSED_LAVA_CRYSTAL.get()))
            .title(Component.translatable("tabs.armorplus.blocks"))
            .backgroundTexture(Identifier.fromNamespaceAndPath(MODID, "textures/gui/container/creative_inventory/tab_armorplus.png"))
            .withLabelColor(0x55FF55)
            .withSearchBar()
            .displayItems((featureFlags, output) -> {
                output.accept(ORE_LAVA_CRYSTAL_STONE.get());
                output.accept(ORE_LAVA_CRYSTAL_OBSIDIAN.get());
                output.accept(ORE_LAVA_CRYSTAL.get());
                output.accept(ModBlocks.LAVA_CRYSTAL.get());
                output.accept(ModBlocks.INFUSED_LAVA_CRYSTAL.get());
                output.accept(COMPRESSED_LAVA_CRYSTAL.get());
                output.accept(COMPRESSED_INFUSED_LAVA_CRYSTAL.get());
                output.accept(LAVA_INFUSED_OBSIDIAN.get());
                output.accept(COMPRESSED_OBSIDIAN.get());
                output.accept(ORE_FROST_CRYSTAL_STONE.get());
                output.accept(ORE_FROST_CRYSTAL_OBSIDIAN.get());
                output.accept(ORE_FROST_CRYSTAL.get());
                output.accept(ModBlocks.FROST_CRYSTAL.get());
                output.accept(ModBlocks.INFUSED_FROST_CRYSTAL.get());
                output.accept(SNOW_BRICK.get());
                output.accept(SNOW_BRICK_STAIRS.get());
                output.accept(SNOW_BRICK_SLAB.get());
                output.accept(PETRIFIED_SOULS.get());
                output.accept(SOUL_BOX.get());

                IntStream.range(0, AP_STONE_BRICKS_LENGTH).forEach(index -> {
                    String color = values()[index].getName();
                    output.accept(STONE_BRICKS[index].get());
                    output.accept(STONE_BRICK_CORNERS[index].get());
                    output.accept(STONE_BRICK_TOWERS[index].get());
                    output.accept(STONE_BRICK_WALLS[index].get());
                    output.accept(STONE_BRICK_STAIRS[index].get());
                    output.accept(STONE_BRICK_SLABS[index].get());
                    output.accept(CASTLE_BLOCKS[index].get());
                    output.accept(CASTLE_BLOCK_CORNERS[index].get());
                    output.accept(CASTLE_BLOCK_TOWERS[index].get());
                    output.accept(CASTLE_BLOCK_WALLS[index].get());
                    output.accept(CASTLE_BLOCK_STAIRS[index].get());
                    output.accept(CASTLE_BLOCK_SLABS[index].get());
                });
            })
            .build());

    private static void addBasesToOutput(CreativeModeTab.Output output, String material) {
        Set<ItemStack> set = ItemStackLinkedSet.createTypeAndComponentsSet();

        set.add(getAPItemStack(material + "_helmet_base"));
        set.add(getAPItemStack(material + "_chestplate_base"));
        set.add(getAPItemStack(material + "_leggings_base"));
        set.add(getAPItemStack(material + "_boots_base"));
        set.add(getAPItemStack(material + "_sword_base"));
        set.add(getAPItemStack(material + "_battle_axe_base"));
        set.add(getAPItemStack(material + "_pickaxe_base"));
        set.add(getAPItemStack(material + "_shovel_base"));
        set.add(getAPItemStack(material + "_bow_base"));

        output.acceptAll(set);
    }

    /**
     * The creative grid is 9 slots wide: each material gets exactly one row
     * (helmet, chestplate, leggings, boots, sword, battle axe, pickaxe, shovel, mace),
     * followed by a trailing section holding every material's bow.
     */
    private static void addMaterialsToOutput(CreativeModeTab.Output output, String[] materials) {
        for (String material : materials) {
            addEquipmentToOutput(output, material);
        }
        for (String material : materials) {
            output.accept(getAPItemStack(material + "_bow"));
        }
    }

    private static void addEquipmentToOutput(CreativeModeTab.Output output, String material) {
        Set<ItemStack> set = ItemStackLinkedSet.createTypeAndComponentsSet();
        set.add(getAPItemStack(material + "_helmet"));
        set.add(getAPItemStack(material + "_chestplate"));
        set.add(getAPItemStack(material + "_leggings"));
        set.add(getAPItemStack(material + "_boots"));
        set.add(getAPItemStack(material + "_sword"));
        set.add(getAPItemStack(material + "_battle_axe"));
        set.add(getAPItemStack(material + "_pickaxe"));
        set.add(getAPItemStack(material + "_shovel"));
        set.add(getAPItemStack(material + "_mace"));
        output.acceptAll(set);
    }


    public static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String name, Supplier<? extends CreativeModeTab> sup) {
        return CREATIVE_MODE_TABS.register(name, sup);
    }
}