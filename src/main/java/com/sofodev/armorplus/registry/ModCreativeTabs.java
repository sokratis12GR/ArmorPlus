package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.registry.blocks.castle.BrickColor;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.tool.APToolProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackLinkedSet;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.AP_STONE_BRICKS_LENGTH;
import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.registry.blocks.castle.BrickColor.values;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.getAPItemStack;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> AP_CORE_GROUP = register("core", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(getAPItem("infused_lava_chestplate")))
            .title(Component.translatable("tabs.armorplus.core"))
            .withLabelColor(0xFFFFFF)
            .withSearchBar(40)
            .withBackgroundLocation(new ResourceLocation(MODID, "textures/gui/container/creative_inventory/tab_armorplus_small_search.png"))
            .displayItems((featureFlags, output) -> {
                addEquipmentToOutput(output, "coal");
                addEquipmentToOutput(output, "redstone");
                addEquipmentToOutput(output, "lapis");
                addEquipmentToOutput(output, "emerald");
                addEquipmentToOutput(output, "obsidian");
                addEquipmentToOutput(output, "infused_lava");
                addEquipmentToOutput(output, "guardian");
                addEquipmentToOutput(output, "super_star");
                addEquipmentToOutput(output, "ender_dragon");
                addEquipmentToOutput(output, "slayer");
            }).build());
    public static final RegistryObject<CreativeModeTab> AP_EXTRA_GROUP = register("extra", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(getAPItem("cobalt_chestplate")))
            .title(Component.translatable("tabs.armorplus.extra"))
            .withLabelColor(0xFFFFFF)
            .withBackgroundLocation(new ResourceLocation(MODID, "textures/gui/container/creative_inventory/tab_armorplus_small_search.png"))
            .withSearchBar(40)
            .displayItems((featureFlags, output) -> {
                addSetToOutput(output, "chainmail");
                output.accept(ENHANCED_CHAINMAIL.get());
                addSetToOutput(output, "chicken");
                addSetToOutput(output, "golden");
                output.accept(ENHANCED_GOLD.get());
                addSetToOutput(output, "slime");
                addSetToOutput(output, "iron");
                output.accept(ENHANCED_IRON.get());
                addSetToOutput(output, "ardite");
                addSetToOutput(output, "diamond");
                output.accept(ENHANCED_DIAMOND.get());
                addSetToOutput(output, "cobalt");
                addSetToOutput(output, "netherite");
                output.accept(ENHANCED_NETHERITE.get());
                addSetToOutput(output, "knight_slime");
                addSetToOutput(output, "frost");
                output.accept(FROST_SHARD.get());
                addSetToOutput(output, "pig_iron");
                addSetToOutput(output, "frost_lava");
                output.accept(LAVA_SHARD.get());
                addSetToOutput(output, "manyullyn");
            }).build());
    public static final RegistryObject<CreativeModeTab> AP_ITEM_GROUP = register("items", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.INFUSED_LAVA_CRYSTAL.get()))
            .title(Component.translatable("tabs.armorplus.items"))
            .withBackgroundLocation(new ResourceLocation(MODID, "textures/gui/container/creative_inventory/tab_armorplus.png"))
            .withSearchBar()
            .withLabelColor(0xFFFFFF)
            .displayItems((featureFlags, output) -> {
                //materials
                output.accept(ModItems.LAVA_CRYSTAL.get());
                output.accept(ModItems.INFUSED_LAVA_CRYSTAL.get());
                output.accept(ModItems.FROST_CRYSTAL.get());
                output.accept(ModItems.INFUSED_FROST_CRYSTAL.get());
                output.accept(ModItems.INFUSED_FROST_LAVA_CRYSTAL.get());
                output.accept(ModItems.GUARDIAN_SCALE.get());
                output.accept(ModItems.WITHER_BONE.get());
                output.accept(ModItems.ENDER_DRAGON_SCALE.get());
                output.accept(ModItems.THE_ULTIMATE_MATERIAL.get());
                //arrows
                output.accept(ModItems.ITEM_COAL_ARROW.get());
                output.accept(ModItems.ITEM_REDSTONE_ARROW.get());
                output.accept(ModItems.ITEM_LAPIS_ARROW.get());
                output.accept(ModItems.ITEM_EMERALD_ARROW.get());
                output.accept(ModItems.ITEM_OBSIDIAN_ARROW.get());
                output.accept(ModItems.ITEM_INFUSED_LAVA_ARROW.get());
                output.accept(ModItems.ITEM_GUARDIAN_ARROW.get());
                output.accept(ModItems.ITEM_SUPER_STAR_ARROW.get());
                output.accept(ModItems.ITEM_ENDER_DRAGON_ARROW.get());
                //bases
                output.accept(ModItems.ELDER_GUARDIAN_SOUL.get());
                addBasesToOutput(output, "guardian");
                output.accept(ModItems.WITHER_BOSS_SOUL.get());
                addBasesToOutput(output, "super_star");
                output.accept(ModItems.ENDER_DRAGON_SOUL.get());
                addBasesToOutput(output, "ender_dragon");
                output.accept(ModItems.SLAYER_SOUL.get());
                addBasesToOutput(output, "slayer");
                //advanced materials
                output.accept(ModItems.WITHER_SKELETON_SOUL.get());
                output.accept(ModItems.GUARDIAN_SOUL.get());
                output.accept(ModItems.ENDERMAN_SOUL.get());
                output.accept(ModItems.BLAZE_SOUL.get());
                //enhanced materials
                output.accept(ModItems.ENHANCED_CHAINMAIL.get());
                output.accept(ModItems.ENHANCED_IRON.get());
                output.accept(ModItems.ENHANCED_GOLD.get());
                output.accept(ModItems.ENHANCED_DIAMOND.get());
                output.accept(ModItems.ENHANCED_NETHERITE.get());
                //misc
                output.accept(ModItems.CHAINMAIL.get());
                output.accept(ModItems.OBSIDIAN_STICK.get());
                output.accept(ModItems.WOODEN_ROD.get());
                output.accept(ModItems.LAVA_SHARD.get());
                output.accept(ModItems.FROST_SHARD.get());
                output.accept(ModItems.THANK_YOU_6M.get());

            }).build());
    public static final RegistryObject<CreativeModeTab> AP_BLOCK_GROUP = register("blocks", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.INFUSED_LAVA_CRYSTAL.get()))
            .title(Component.translatable("tabs.armorplus.blocks"))
            .withBackgroundLocation(new ResourceLocation(MODID, "textures/gui/container/creative_inventory/tab_armorplus.png"))
            .withLabelColor(0xFFFFFF)
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
        Set<ItemStack> set = ItemStackLinkedSet.createTypeAndTagSet();

        set.add(getAPItemStack(material + "_helmet_base"));
        set.add(getAPItemStack(material + "_chestplate_base"));
        set.add(getAPItemStack(material + "_leggings_base"));
        set.add(getAPItemStack(material + "_boots_base"));
        set.add(getAPItemStack(material + "_sword_base"));
        set.add(getAPItemStack(material + "_battle_axe_base"));
        set.add(getAPItemStack(material + "_pickaxe_base"));
        set.add(getAPItemStack(material + "_bow_base"));

        output.acceptAll(set);
    }

    private static void addEquipmentToOutput(CreativeModeTab.Output output, String material) {
        Set<ItemStack> set = ItemStackLinkedSet.createTypeAndTagSet();
        addSetToOutput(output, material);
        set.add(getAPItemStack(material + "_sword"));
        set.add(getAPItemStack(material + "_battle_axe"));
        set.add(getAPItemStack(material + "_pickaxe"));
        set.add(getAPItemStack(material + "_mace"));
        set.add(getAPItemStack(material + "_bow"));
        output.acceptAll(set);
    }

    private static void addSetToOutput(CreativeModeTab.Output output, String material) {
        Set<ItemStack> set = ItemStackLinkedSet.createTypeAndTagSet();
        set.add(getAPItemStack(material + "_helmet"));
        set.add(getAPItemStack(material + "_chestplate"));
        set.add(getAPItemStack(material + "_leggings"));
        set.add(getAPItemStack(material + "_boots"));
        output.acceptAll(set);
    }


    public static RegistryObject<CreativeModeTab> register(String name, Supplier<? extends CreativeModeTab> sup) {
        return CREATIVE_MODE_TABS.register(name, sup);
    }
}