package com.sofodev.armorplus;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ConfigHelper;
import com.sofodev.armorplus.network.PacketHandler;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.blocks.castle.BrickColor;
import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import com.sofodev.armorplus.registry.entities.arrows.APArrowRenderer;
import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingRenderer;
import com.sofodev.armorplus.registry.entities.normal.WitherlingRenderer;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.tool.APToolProperties;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
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
import software.bernie.geckolib3.GeckoLib;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sofodev.armorplus.config.ArmorPlusConfig.autoSmeltingInput;
import static com.sofodev.armorplus.config.ArmorPlusConfig.autoSmeltingOutput;
import static com.sofodev.armorplus.registry.ModAttributes.ATTRIBUTES;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModEnchantments.ENCHANTMENTS;
import static com.sofodev.armorplus.registry.ModEntities.*;
import static com.sofodev.armorplus.registry.ModItems.BOWS;
import static com.sofodev.armorplus.registry.ModItems.ITEMS;
import static com.sofodev.armorplus.registry.ModPoI.POI_TYPES;
import static com.sofodev.armorplus.registry.ModPotions.EFFECTS;
import static com.sofodev.armorplus.registry.ModVillagerProfessions.PROFESSIONS;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setName;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;
import static net.minecraftforge.fml.loading.FMLEnvironment.dist;

@Mod("armorplus")
@Mod.EventBusSubscriber(bus = MOD, modid = ArmorPlus.MODID)
public class ArmorPlus {

    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";
    public static final String VERSION = "1.19.2-19.0.0";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final PacketHandler PACKET_HANDLER = new PacketHandler();
    /**
     * Used as an "upper ground" variable, which sets the limit for the sets which use these materials.
     */
    public static final int AP_ARMOR_MATERIAL_LENGTH = APArmorMaterial.values().length;
    public static final int AP_TOOL_MATERIAL_LENGTH = APToolProperties.values().length;
    public static final int AP_STONE_BRICKS_LENGTH = BrickColor.values().length;
    public static final int AP_MACE_MAT_LENGTH = APMaceMaterial.values().length;
    public static final CreativeModeTab AP_GROUP = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), setName("armors")) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(getAPItem("infused_lava_chestplate"));
        }
    };
    public static final CreativeModeTab AP_ITEM_GROUP = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), setName("items")) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.INFUSED_LAVA_CRYSTAL.get());
        }
    };
    public static final CreativeModeTab AP_BLOCK_GROUP = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), setName("blocks")) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(INFUSED_LAVA_CRYSTAL.get());
        }
    };
    public static final CreativeModeTab AP_WEAPON_GROUP = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), setName("weapons")) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(getAPItem("ender_dragon_battle_axe"));
        }
    };
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
        ArmorPlus.config = ConfigHelper.register(
                ModLoadingContext.get(), FMLJavaModLoadingContext.get(),
                ModConfig.Type.COMMON, ArmorPlusConfig::new);
        //Config End
        //Order of registration per https://gist.github.com/pupnewfster/ea38cf3744f23d6b65d67e6f279d5942
        BLOCKS.register(modEventBus);
        ATTRIBUTES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        EFFECTS.register(modEventBus);
        //        SURFACE_BUILDERS.register(modEventBus); - Removed in 1.18
        //        FEATURES.register(modEventBus); - Not needed yet.
        PROFESSIONS.register(modEventBus);
        POI_TYPES.register(modEventBus);
        //        BIOMES.register(modEventBus); - Not needed yet.

        modEventBus.addListener(this::onCommonSetup);
        //        MinecraftForge.EVENT_BUS.addListener(WorldGenEvents::onBiomeLoad);
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

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Nullable
    public static <T> Registry<T> findRegistryByKey(ResourceKey<Registry<T>> key) {
        return Registry.REGISTRY.get((ResourceKey) key);
    }

    public static ArmorPlus getInstance() {
        return instance;
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(this::afterSetup);
    }

    private void afterSetup() {
        //        GlobalVars.registerAfterEverything();
        //        ModConfiguredFeatures.registerConfiguredFeatures();
        //        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(ResourceKey.create(Registry.BIOME_REGISTRY, FROZEN_PLAINS.getId()), 5)); // Not needed yet.
        //        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(ResourceKey.create(Registry.BIOME_REGISTRY, VALLEY_OF_SOULS.getId()), 5)); // Not needed yet.
        //        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(ResourceKey.create(Registry.BIOME_REGISTRY, POSSESSED_GROUNDS.getId()), 5)); // Not needed yet.
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
        EntityRenderers.register(SKELETAL_KING.get(), SkeletalKingRenderer::new);
        EntityRenderers.register(WITHERLING.get(), WitherlingRenderer::new);
        //        register(DEMONIC_DRAGON.get(), DemonicDragonRenderer::new);           // TODO: Fix misc entities.
        //        register(FROST_WOLF.get(), FrostWolfRenderer::new);           // TODO: Fix misc entities.
        //        register(FROST_WOLF_ALPHA.get(), FrostWolfAlphaRenderer::new);            // TODO: Fix misc entities.
        //        register(BOREAS.get(), BoreasRenderer::new);          // TODO: Fix misc entities.
        //        this.setRenderLayer(List.of(TROPHY));
        //        BlockEntityRenderers.register(TROPHY_TYPE.get(), TrophyTileEntityRenderer::new);
        registerBowOverrides();
    }

    @SuppressWarnings("removal")
    private void setRenderLayer(List<RegistryObject<Block>> blocks) {
        blocks.forEach(block -> ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout()));
    }

    //    @SuppressWarnings("unchecked")
    //    @Nullable
    //    public <T> T findObjectByKey(ResourceKey<T> key) {
    //        Registry<T> registry = (Registry<T>) Registry.REGISTRY.get(key.getRegistryName());
    //        if (registry == null)
    //            return null;
    //        return registry.get(key.getRegistryName());
    //    }
}