package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.rune.RuneDefinitions;
import com.sofodev.armorplus.registry.rune.RuneItem;
import com.sofodev.armorplus.registry.rune.Runes;
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
import static com.sofodev.armorplus.registry.ModBlocks.CASTLE_BLOCKS;
import static com.sofodev.armorplus.registry.ModBlocks.CASTLE_BLOCK_CORNERS;
import static com.sofodev.armorplus.registry.ModBlocks.CASTLE_BLOCK_SLABS;
import static com.sofodev.armorplus.registry.ModBlocks.CASTLE_BLOCK_STAIRS;
import static com.sofodev.armorplus.registry.ModBlocks.CASTLE_BLOCK_TOWERS;
import static com.sofodev.armorplus.registry.ModBlocks.CASTLE_BLOCK_WALLS;
import static com.sofodev.armorplus.registry.ModBlocks.COMPRESSED_INFUSED_LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModBlocks.COMPRESSED_LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModBlocks.COMPRESSED_OBSIDIAN;
import static com.sofodev.armorplus.registry.ModBlocks.LAVA_INFUSED_OBSIDIAN;
import static com.sofodev.armorplus.registry.ModBlocks.ORE_FROST_CRYSTAL;
import static com.sofodev.armorplus.registry.ModBlocks.ORE_FROST_CRYSTAL_OBSIDIAN;
import static com.sofodev.armorplus.registry.ModBlocks.ORE_FROST_CRYSTAL_STONE;
import static com.sofodev.armorplus.registry.ModBlocks.ORE_LAVA_CRYSTAL;
import static com.sofodev.armorplus.registry.ModBlocks.ORE_LAVA_CRYSTAL_OBSIDIAN;
import static com.sofodev.armorplus.registry.ModBlocks.ORE_LAVA_CRYSTAL_STONE;
import static com.sofodev.armorplus.registry.ModBlocks.PETRIFIED_SOULS;
import static com.sofodev.armorplus.registry.ModBlocks.EMPOWERING_PEDESTAL;
import static com.sofodev.armorplus.registry.ModBlocks.RUNE_ALTAR;
import static com.sofodev.armorplus.registry.ModBlocks.SNOW_BRICK;
import static com.sofodev.armorplus.registry.ModBlocks.SNOW_BRICK_SLAB;
import static com.sofodev.armorplus.registry.ModBlocks.SNOW_BRICK_STAIRS;
import static com.sofodev.armorplus.registry.ModBlocks.SOUL_BOX;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICKS;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICK_CORNERS;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICK_SLABS;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICK_STAIRS;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICK_TOWERS;
import static com.sofodev.armorplus.registry.ModBlocks.STONE_BRICK_WALLS;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_CHAINMAIL;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_COPPER;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_DIAMOND;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_GOLD;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_IRON;
import static com.sofodev.armorplus.registry.ModItems.ENHANCED_NETHERITE;
import static com.sofodev.armorplus.registry.ModItems.FROST_SHARD;
import static com.sofodev.armorplus.registry.ModItems.LAVA_SHARD;
import static com.sofodev.armorplus.registry.block.castle.BrickColor.values;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.getAPItemStack;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    private static final String[] CORE_MATERIALS = {
            //basic
            "coal", "glowstone", "honey", "chicken", "slime", "redstone", "lapis",
            //intermediate
            "amethyst", "quartz", "emerald", "obsidian",
            //advanced
            "frost", "infused_lava", "frost_lava",
            //legendary
            "guardian", "super_star", "ender_dragon", "warden", "slayer"
    };
    private static final String[] EXTRA_MATERIALS = {
            //enhanced vanilla
            "chainmail", "copper", "iron", "golden", "diamond", "netherite",
            //tconstruct
            "ardite", "cobalt", "knight_slime", "pig_iron", "manyullyn"
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
    /** The rune tools, every rune that can currently be obtained and a rune of every vanilla effect, see Runes#creativeRunes. */
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AP_RUNES_GROUP = register("runes", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.ARMOR_RUNE.get()))
            .title(Component.translatable("tabs.armorplus.runes"))
            .backgroundTexture(Identifier.fromNamespaceAndPath(MODID, "textures/gui/container/creative_inventory/tab_armorplus.png"))
            .withLabelColor(0xC070FF)
            .withSearchBar()
            .displayItems((parameters, output) -> {
                output.accept(RUNE_ALTAR.get());
                output.accept(EMPOWERING_PEDESTAL.get());
                output.accept(ModItems.BLANK_RUNE.get());
                output.accept(ModItems.SOCKET_PUNCH.get());
                output.accept(ModItems.EMBER_SIGIL.get());
                output.accept(ModItems.RIME_SIGIL.get());
                output.accept(ModItems.EQUINOX_SIGIL.get());
                output.accept(ModItems.ASTRAL_SIGIL.get());
                output.accept(ModItems.ARMOR_SIGIL.get());
                output.accept(ModItems.WEAPON_SIGIL.get());
                output.accept(ModItems.TOOL_SIGIL.get());
                output.accept(ModItems.BOW_SIGIL.get());
                output.accept(ModItems.EFFECT_SIGIL.get());
                output.accept(ModItems.INSTANT_SIGIL.get());
                output.accept(ModItems.MODDED_SIGIL.get());
                // the tab can be built before the synced datapack runes were loaded
                RuneDefinitions.update(parameters.holders());
                Runes.creativeRunes().forEach(rune -> output.accept(RuneItem.create(rune.kind(), rune.entry())));
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
                output.accept(RUNE_ALTAR.get());
                output.accept(EMPOWERING_PEDESTAL.get());

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