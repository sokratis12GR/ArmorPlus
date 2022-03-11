package com.sofodev.armorplus;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ConfigHelper;
import com.sofodev.armorplus.events.WorldGenEvents;
import com.sofodev.armorplus.network.PacketHandler;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModConfiguredFeatures;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.blocks.castle.BrickColor;
import com.sofodev.armorplus.registry.blocks.special.TrophyTileEntityRenderer;
import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import com.sofodev.armorplus.registry.entities.arrows.APArrowRenderer;
import com.sofodev.armorplus.registry.entities.bosses.DemonicDragonRenderer;
import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingRenderer;
import com.sofodev.armorplus.registry.entities.normal.BoreasRenderer;
import com.sofodev.armorplus.registry.entities.normal.FrostWolfAlphaRenderer;
import com.sofodev.armorplus.registry.entities.normal.FrostWolfRenderer;
import com.sofodev.armorplus.registry.entities.normal.WitherlingRenderer;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.tool.APToolProperties;
import com.sofodev.armorplus.utils.GlobalVars;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

import static com.sofodev.armorplus.registry.ModAttributes.ATTRIBUTES;
import static com.sofodev.armorplus.registry.ModBiomes.*;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModEnchantments.ENCHANTMENTS;
import static com.sofodev.armorplus.registry.ModEntities.*;
import static com.sofodev.armorplus.registry.ModFeatures.FEATURES;
import static com.sofodev.armorplus.registry.ModFeatures.SURFACE_BUILDERS;
import static com.sofodev.armorplus.registry.ModItems.BOWS;
import static com.sofodev.armorplus.registry.ModItems.ITEMS;
import static com.sofodev.armorplus.registry.ModPotions.EFFECTS;
import static com.sofodev.armorplus.registry.ModVillagers.POI_TYPES;
import static com.sofodev.armorplus.registry.ModVillagers.PROFESSIONS;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setName;
import static java.util.Arrays.asList;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;
import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;
import static net.minecraftforge.fml.loading.FMLEnvironment.dist;

@Mod("armorplus")
@Mod.EventBusSubscriber(bus = MOD, modid = ArmorPlus.MODID)
public class ArmorPlus {

    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";
    public static final String VERSION = "1.16.5-16.5.2";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final PacketHandler PACKET_HANDLER = new PacketHandler();
    /**
     * Used as an "upper ground" variable, which sets the limit for the sets which use these materials.
     */
    public static final int AP_ARMOR_MATERIAL_LENGTH = APArmorMaterial.values().length;
    public static final int AP_TOOL_MATERIAL_LENGTH = APToolProperties.values().length;
    public static final int AP_STONE_BRICKS_LENGTH = BrickColor.values().length;
    public static final int AP_MACE_MAT_LENGTH = APMaceMaterial.values().length;
    public static final ItemGroup AP_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("armors")) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(getAPItem("infused_lava_chestplate"));
        }
    };
    public static final ItemGroup AP_ITEM_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("items")) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.INFUSED_LAVA_CRYSTAL.get());
        }
    };
    public static final ItemGroup AP_BLOCK_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("blocks")) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(INFUSED_LAVA_CRYSTAL.get());
        }
    };
    public static final ItemGroup AP_WEAPON_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("weapons")) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(getAPItem("ender_dragon_battle_axe"));
        }
    };
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
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        EFFECTS.register(modEventBus);
        SURFACE_BUILDERS.register(modEventBus);
        FEATURES.register(modEventBus);
        ATTRIBUTES.register(modEventBus);
        PROFESSIONS.register(modEventBus);
        POI_TYPES.register(modEventBus);
        BIOMES.register(modEventBus);

        modEventBus.addListener(this::onCommonSetup);
        forgeBus.addListener(EventPriority.HIGH, WorldGenEvents::onBiomeLoad);
        if (dist == CLIENT) {
            modEventBus.addListener(this::onClientSetup);
        }
        //  modEventBus.addListener(ModAttributes::registerAttributes);
    }

    @OnlyIn(CLIENT)
    private static void registerRenderingHandler(EntityType<? extends APArrowEntity> entityClass, String name) {
        registerEntityRenderingHandler(entityClass, rm -> new APArrowRenderer<>(rm, name));
    }

    @OnlyIn(CLIENT)
    private static void registerBowOverrides() {
        Arrays.stream(BOWS).forEach(bow -> {
            ItemModelsProperties.register(bow.get(), new ResourceLocation("pull"), (stack, world, player) -> {
                if (player == null) {
                    return 0.0F;
                } else {
                    return player.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - player.getUseItemRemainingTicks()) / 20.0F;
                }
            });
            ItemModelsProperties.register(bow.get(), new ResourceLocation("pulling"), (stack, world, player) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;
            });
        });
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Nullable
    public static <T> Registry<T> findRegistryByKey(RegistryKey<Registry<T>> key) {
        return Registry.REGISTRY.get((RegistryKey) key);
    }

    public static ArmorPlus getInstance() {
        return instance;
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(this::afterSetup);
    }

    private void afterSetup() {
        GlobalVars.registerAfterEverything();
        ModConfiguredFeatures.registerConfiguredFeatures();
        ModConfiguredFeatures.registerConfiguredFeatures();
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(RegistryKey.create(Registry.BIOME_REGISTRY, FROZEN_PLAINS.getId()), 5));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(RegistryKey.create(Registry.BIOME_REGISTRY, VALLEY_OF_SOULS.getId()), 5));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(RegistryKey.create(Registry.BIOME_REGISTRY, POSSESSED_GROUNDS.getId()), 5));
        PACKET_HANDLER.initialize();
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
        registerEntityRenderingHandler(SKELETAL_KING.get(), SkeletalKingRenderer::new);
        registerEntityRenderingHandler(WITHERLING.get(), WitherlingRenderer::new);
        registerEntityRenderingHandler(DEMONIC_DRAGON.get(), DemonicDragonRenderer::new);
        registerEntityRenderingHandler(FROST_WOLF.get(), FrostWolfRenderer::new);
        registerEntityRenderingHandler(FROST_WOLF_ALPHA.get(), FrostWolfAlphaRenderer::new);
        registerEntityRenderingHandler(BOREAS.get(), BoreasRenderer::new);
        this.setRenderLayer(asList(ModBlocks.LAVA_INFUSER, ModBlocks.TROPHY));
        ClientRegistry.bindTileEntityRenderer(TROPHY_TYPE.get(), TrophyTileEntityRenderer::new);
        registerBowOverrides();
    }

    private void setRenderLayer(List<RegistryObject<Block>> blocks) {
        blocks.forEach(block -> RenderTypeLookup.setRenderLayer(block.get(), RenderType.cutout()));
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T findObjectByKey(RegistryKey<T> key) {
        Registry<T> registry = (Registry<T>) Registry.REGISTRY.get(key.getRegistryName());
        if (registry == null)
            return null;
        return registry.get(key.getRegistryName());
    }
}