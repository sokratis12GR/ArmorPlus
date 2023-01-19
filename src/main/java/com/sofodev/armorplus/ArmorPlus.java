package com.sofodev.armorplus;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ConfigHelper;
import com.sofodev.armorplus.network.PacketHandler;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.blocks.castle.BrickColor;
import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import com.sofodev.armorplus.registry.entities.arrows.APArrowRenderer;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.tool.APToolProperties;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.config.ArmorPlusConfig.autoSmeltingInput;
import static com.sofodev.armorplus.config.ArmorPlusConfig.autoSmeltingOutput;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModEnchantments.ENCHANTMENTS;
import static com.sofodev.armorplus.registry.ModEntities.*;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.registry.ModPoI.POI_TYPES;
import static com.sofodev.armorplus.registry.ModPotions.EFFECTS;
import static com.sofodev.armorplus.registry.ModVillagerProfessions.PROFESSIONS;
import static com.sofodev.armorplus.registry.blocks.castle.BrickColor.values;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.getItemByName;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;
import static net.minecraftforge.fml.loading.FMLEnvironment.dist;

@Mod("armorplus")
@Mod.EventBusSubscriber(bus = MOD, modid = ArmorPlus.MODID)
public class ArmorPlus {

    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";
    public static final String VERSION = "1.19.3-19.2.0";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final PacketHandler PACKET_HANDLER = new PacketHandler();
    /**
     * Used as an "upper ground" variable, which sets the limit for the sets which use these materials.
     */
    public static final int AP_ARMOR_MATERIAL_LENGTH = APArmorMaterial.values().length;
    public static final int AP_TOOL_MATERIAL_LENGTH = APToolProperties.values().length;
    public static final int AP_STONE_BRICKS_LENGTH = BrickColor.values().length;
    public static final int AP_MACE_MAT_LENGTH = APMaceMaterial.values().length;

    public static CreativeModeTab AP_CORE_GROUP;
    public static CreativeModeTab AP_EXTRA_GROUP;
    public static CreativeModeTab AP_ITEM_GROUP;
    public static CreativeModeTab AP_BLOCK_GROUP;

    @SubscribeEvent
    public static void registerTabs(CreativeModeTabEvent.Register event) {
        AP_CORE_GROUP = event.registerCreativeModeTab(new ResourceLocation(MODID, "core"), builder -> builder.icon(() -> new ItemStack(getAPItem("infused_lava_chestplate")))
                .title(Component.translatable("tabs.armorplus.core"))
                .withLabelColor(0xFFFFFF)
                .withSearchBar(40)
                .withBackgroundLocation(new ResourceLocation(MODID, "textures/gui/container/creative_inventory/tab_armorplus_small_search.png"))
                .displayItems((featureFlags, output, hasOp) -> {
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
                }));
        AP_EXTRA_GROUP = event.registerCreativeModeTab(new ResourceLocation(MODID, "extra"), builder -> builder.icon(() -> new ItemStack(getAPItem("cobalt_chestplate")))
                .title(Component.translatable("tabs.armorplus.extra"))
                .withLabelColor(0xFFFFFF)
                .withBackgroundLocation(new ResourceLocation(MODID, "textures/gui/container/creative_inventory/tab_armorplus_small_search.png"))
                .withSearchBar(40)
                .displayItems((featureFlags, output, hasOp) -> {
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
                }));
        AP_ITEM_GROUP = event.registerCreativeModeTab(new ResourceLocation(MODID, "items"), builder -> builder.icon(() -> new ItemStack(ModItems.INFUSED_LAVA_CRYSTAL.get()))
                .title(Component.translatable("tabs.armorplus.items"))
                .withBackgroundLocation(new ResourceLocation(MODID, "textures/gui/container/creative_inventory/tab_armorplus.png"))
                .withSearchBar()
                .withLabelColor(0xFFFFFF)
                .displayItems((featureFlags, output, hasOp) -> {
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

                }));
        AP_BLOCK_GROUP = event.registerCreativeModeTab(new ResourceLocation(MODID, "blocks"), builder -> builder.icon(() -> new ItemStack(ModBlocks.INFUSED_LAVA_CRYSTAL.get()))
                .title(Component.translatable("tabs.armorplus.blocks"))
                .withBackgroundLocation(new ResourceLocation(MODID, "textures/gui/container/creative_inventory/tab_armorplus.png"))
                .withLabelColor(0xFFFFFF)
                .withSearchBar()
                .displayItems((featureFlags, output, hasOp) -> {
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
                }));
    }
    private static void addBasesToOutput(CreativeModeTab.Output output, String material) {
        output.accept(getAPItem(material + "_helmet_base"));
        output.accept(getAPItem(material + "_chestplate_base"));
        output.accept(getAPItem(material + "_leggings_base"));
        output.accept(getAPItem(material + "_boots_base"));
        output.accept(getAPItem(material + "_sword_base"));
        output.accept(getAPItem(material + "_battle_axe_base"));
        output.accept(getAPItem(material + "_pickaxe_base"));
        output.accept(getAPItem(material + "_bow_base"));
    }

    private static void addEquipmentToOutput(CreativeModeTab.Output output, String material) {
        addSetToOutput(output, material);
        output.accept(getAPItem(material + "_sword"));
        output.accept(getAPItem(material + "_battle_axe"));
        output.accept(getAPItem(material + "_pickaxe"));
        output.accept(getAPItem(material + "_mace"));
        output.accept(getAPItem(material + "_bow"));
    }

    private static void addSetToOutput(CreativeModeTab.Output output, String material) {
        output.accept(getAPItem(material + "_helmet"));
        output.accept(getAPItem(material + "_chestplate"));
        output.accept(getAPItem(material + "_leggings"));
        output.accept(getAPItem(material + "_boots"));
    }

    public static Map<Block, ItemLike> SMELTING_MAP = new HashMap<>();
    public static ArmorPlusConfig config;
    public static ArmorPlus instance;

    public ArmorPlus() {
        instance = this;
        GeckoLib.initialize();
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext modLoadingCTX = ModLoadingContext.get();

        //Config Start
        ArmorPlus.config = ConfigHelper.register(ModLoadingContext.get(), FMLJavaModLoadingContext.get(), ModConfig.Type.COMMON, ArmorPlusConfig::new);
        //Config End
        //Order of registration per https://gist.github.com/pupnewfster/ea38cf3744f23d6b65d67e6f279d5942
        BLOCKS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        //Configured
        //Placed
        ITEMS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        EFFECTS.register(modEventBus);
        PROFESSIONS.register(modEventBus);
        POI_TYPES.register(modEventBus);

        modEventBus.addListener(this::onCommonSetup);
        if (dist == CLIENT) {
            modEventBus.addListener(this::onClientSetup);
        }
    }

    private static Map<Block, ItemLike> registerSmeltingMap() {
        HashMap<Block, ItemLike> map = new HashMap<>();
        //        List<? extends String> input = asList("minecraft:iron_ore", "minecraft:gold_ore",
        //                "minecraft:sand", "minecraft:sandstone", "minecraft:wet_sponge", "minecraft:clay", "minecraft:stone_bricks", "minecraft:cobblestone", "minecraft:stone",
        //                "minecraft:acacia_log", "minecraft:birch_log", "minecraft:dark_oak_log", "minecraft:jungle_log", "minecraft:oak_log", "minecraft:spruce_log",
        //                "minecraft:netherrack", "minecraft:ancient_debris", "minecraft:stone_bricks");
        //        List<? extends String> output = asList("minecraft:iron_ingot", "minecraft:gold_ingot", "minecraft:glass", "minecraft:smooth_sandstone", "minecraft:sponge",
        //                "minecraft:terracotta", "minecraft:cracked_stone_bricks", "minecraft:stone", "minecraft:stone",
        //                "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal", "minecraft:charcoal",
        //                "minecraft:nether_brick", "minecraft:netherite_scrap", "minecraft:cracked_stone_bricks");
        List<? extends String> input = autoSmeltingInput.get();
        List<? extends String> output = autoSmeltingOutput.get();
        if (input.size() != output.size()) {
            throw new IllegalArgumentException("autoSmeltingInput and autoSmeltingOutput in config/ap_config.toml must have the same size!");
        }
        for (int i = 0; i < input.size(); i++) {
            String entryInput = input.get(i);
            String entryOutput = output.get(i);
            Block fromBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(entryInput));
            Item toItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(entryOutput));
            if (fromBlock != null && toItem != null) {
                map.put(fromBlock, toItem);
            } else {
                ArmorPlus.LOGGER.error("Block with the registry name: " + entryInput + " or Item with the registry name: " + entryOutput + " don't exist. Failed to add auto smelt recipe");
            }
        }
        return map;
    }

    @OnlyIn(CLIENT)
    private static void registerRenderingHandler(EntityType<? extends APArrowEntity> entityClass, String name) {
        EntityRenderers.register(entityClass, rm -> new APArrowRenderer<>(rm, name));
    }

    @OnlyIn(CLIENT)
    private static void registerBowOverrides() {
        Arrays.stream(BOWS).forEach(bow -> {
            ItemProperties.register(bow.map(Item::asItem)
                    .orElse(ItemStack.EMPTY.getItem()), new ResourceLocation("pull"), (stack, level, player, val) -> {
                if (player == null) {
                    return 0.0F;
                } else {
                    return player.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - player.getUseItemRemainingTicks()) / 20.0F;
                }
            });
            ItemProperties.register(bow.map(Item::asItem)
                    .orElse(ItemStack.EMPTY.getItem()), new ResourceLocation("pulling"), (stack, level, player, val) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;
            });
        });
    }

    public static ArmorPlus getInstance() {
        return instance;
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(this::afterSetup);
    }

    private void afterSetup() {
        //        GlobalVars.registerAfterEverything();
        PACKET_HANDLER.initialize();
        SMELTING_MAP = registerSmeltingMap();
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        registerRenderingHandler(COAL_ARROW.get(), "coal");
        registerRenderingHandler(LAPIS_ARROW.get(), "lapis");
        registerRenderingHandler(REDSTONE_ARROW.get(), "redstone");
        registerRenderingHandler(EMERALD_ARROW.get(), "emerald");
        registerRenderingHandler(OBSIDIAN_ARROW.get(), "obsidian");
        registerRenderingHandler(INFUSED_LAVA_ARROW.get(), "lava");
        registerRenderingHandler(GUARDIAN_ARROW.get(), "guardian");
        registerRenderingHandler(SUPER_STAR_ARROW.get(), "super_star");
        registerRenderingHandler(ENDER_DRAGON_ARROW.get(), "ender_dragon");
        registerBowOverrides();
    }

    @SuppressWarnings("removal")
    private void setRenderLayer(List<RegistryObject<Block>> blocks) {
        blocks.forEach(block -> ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout()));
    }
}