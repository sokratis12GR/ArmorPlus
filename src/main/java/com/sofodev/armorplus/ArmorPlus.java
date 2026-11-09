package com.sofodev.armorplus;

import com.sofodev.armorplus.client.config.APConfigSectionScreen;
import com.sofodev.thedragonlib.client.config.ConfigScreenHelper;
import net.neoforged.fml.loading.FMLEnvironment;
import com.sofodev.armorplus.client.RuneMarkerDecorator;
import com.sofodev.armorplus.client.SocketOverlayDecorator;
import com.sofodev.armorplus.registry.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import com.sofodev.armorplus.client.RitualScreen;
import com.sofodev.armorplus.client.RuneAltarScreen;
import com.sofodev.armorplus.client.renderer.EmpoweringPedestalRenderer;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ArmorPlusStatsConfig;
import com.sofodev.armorplus.registry.ModAttachments;
import com.sofodev.armorplus.registry.ModDataComponents;
import com.sofodev.armorplus.registry.ModEnchantmentEffects;
import com.sofodev.armorplus.registry.ModLootModifiers;
import com.sofodev.armorplus.registry.ModMenus;
import com.sofodev.armorplus.registry.rune.RuneEffectTypes;
import com.sofodev.armorplus.registry.block.castle.BrickColor;
import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.APArrowRenderer;
import com.sofodev.armorplus.registry.item.armor.ArmorEffects;
import com.sofodev.armorplus.registry.item.tool.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import com.sofodev.armorplus.registry.item.tool.ToolEffects;
import com.sofodev.thedragonlib.config.ConfigHelper;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.registry.ModArmorMaterials.MATERIALS;
import static com.sofodev.armorplus.registry.ModBlocks.BLOCKS;
import static com.sofodev.armorplus.registry.ModBlocks.TILE_ENTITIES;
import static com.sofodev.armorplus.registry.ModCreativeTabs.CREATIVE_MODE_TABS;
import static com.sofodev.armorplus.registry.ModEntities.*;
import static com.sofodev.armorplus.registry.ModItems.BOWS;
import static com.sofodev.armorplus.registry.ModItems.ITEMS;
import static com.sofodev.armorplus.registry.ModPoI.POI_TYPES;
import static com.sofodev.armorplus.registry.ModPotions.EFFECTS;
import static com.sofodev.armorplus.registry.ModVillagerProfessions.PROFESSIONS;

@Mod(MODID)
public class ArmorPlus {

    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final int AP_TOOL_MATERIAL_LENGTH = APToolProperties.values().length;
    public static final int AP_STONE_BRICKS_LENGTH = BrickColor.values().length;
    public static final int AP_MACE_MAT_LENGTH = APMaceMaterial.values().length;

    public static final Map<Block, ItemLike> SMELTING_MAP = new HashMap<>();

    public static ArmorPlusConfig config;
    public static ArmorPlus instance;

    public ArmorPlus(IEventBus modEventBus, ModContainer modContainer) {
        instance = this;

        ArmorPlus.config = ConfigHelper.buildAndRegister(
                modContainer, ModConfig.Type.COMMON, ArmorPlusConfig.COMMON_FILE, ArmorPlusConfig::create).values();
        // One file per topic, so each stays short (see ArmorPlusConfig)
        ConfigHelper.buildAndRegister(modContainer, ModConfig.Type.COMMON, ArmorPlusConfig.EFFECTS_FILE, ArmorPlusConfig::createEffects);
        ConfigHelper.buildAndRegister(modContainer, ModConfig.Type.COMMON, ArmorPlusConfig.DROPS_FILE, ArmorPlusConfig::createDrops);
        ConfigHelper.buildAndRegister(modContainer, ModConfig.Type.COMMON, ArmorPlusConfig.RUNES_FILE, ArmorPlusConfig::createRunes);
        ConfigHelper.buildAndRegister(modContainer, ModConfig.Type.CLIENT, ArmorPlusConfig.CLIENT_FILE, ArmorPlusConfig::createClient);
        // Equipment stats are baked into the items, so they must be loaded before the items register
        ArmorPlusStatsConfig.register(modContainer);

        if (FMLEnvironment.dist.isClient()) {
            ConfigScreenHelper.registerSectionScreen(modContainer, APConfigSectionScreen::new);
        }

        MATERIALS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        EFFECTS.register(modEventBus);
        ModAttachments.ATTACHMENT_TYPES.register(modEventBus);
        ModDataComponents.DATA_COMPONENTS.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        RuneEffectTypes.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);

        PROFESSIONS.register(modEventBus);
        POI_TYPES.register(modEventBus);

        // parsed buff lists are cached per player, see ArmorEffects
        modEventBus.addListener(ModConfigEvent.Reloading.class, e -> {
            if (e.getConfig().getModId().equals(MODID)) {
                ArmorEffects.clearCaches();
                ToolEffects.clearCaches();
            }
        });
    }

    public static ArmorPlus getInstance() {
        return instance;
    }

    @EventBusSubscriber(
            modid = MODID,
            value = Dist.CLIENT,
            bus = EventBusSubscriber.Bus.MOD
    )
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(ClientModEvents::registerBowOverrides);
        }

        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            registerRenderingHandler(event, COAL_ARROW.get(), "coal");
            registerRenderingHandler(event, LAPIS_ARROW.get(), "lapis");
            registerRenderingHandler(event, REDSTONE_ARROW.get(), "redstone");
            registerRenderingHandler(event, EMERALD_ARROW.get(), "emerald");
            registerRenderingHandler(event, OBSIDIAN_ARROW.get(), "obsidian");
            registerRenderingHandler(event, INFUSED_LAVA_ARROW.get(), "lava");
            registerRenderingHandler(event, GUARDIAN_ARROW.get(), "guardian");
            registerRenderingHandler(event, SUPER_STAR_ARROW.get(), "super_star");
            registerRenderingHandler(event, ENDER_DRAGON_ARROW.get(), "ender_dragon");
            registerRenderingHandler(event, WARDEN_ARROW.get(), "warden");
            registerRenderingHandler(event, AMETHYST_ARROW.get(), "amethyst");
            registerRenderingHandler(event, QUARTZ_ARROW.get(), "quartz");
            registerRenderingHandler(event, GLOWSTONE_ARROW.get(), "glowstone");
            registerRenderingHandler(event, HONEY_ARROW.get(), "honey");
            event.registerBlockEntityRenderer(ModBlocks.EMPOWERING_PEDESTAL_ENTITY.get(), EmpoweringPedestalRenderer::new);
        }

        @SubscribeEvent
        public static void registerMenuScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenus.RUNE_ALTAR.get(), RuneAltarScreen::new);
            event.register(ModMenus.RITUAL_ALTAR.get(), RitualScreen::new);
        }

        /** No socket symbol in the item models on 1.21.1, the decorator draws it on all equipment, ArmorPlus's included. */
        @SubscribeEvent
        public static void registerItemDecorations(RegisterItemDecorationsEvent event) {
            for (Item item : BuiltInRegistries.ITEM) {
                event.register(item, SocketOverlayDecorator.INSTANCE);
            }
            event.register(ModItems.ARMOR_RUNE.get(), RuneMarkerDecorator.INSTANCE);
            event.register(ModItems.WEAPON_RUNE.get(), RuneMarkerDecorator.INSTANCE);
            event.register(ModItems.TOOL_RUNE.get(), RuneMarkerDecorator.INSTANCE);
            event.register(ModItems.RANGED_RUNE.get(), RuneMarkerDecorator.INSTANCE);
        }

        private static void registerRenderingHandler(
                EntityRenderersEvent.RegisterRenderers event,
                EntityType<? extends APArrowEntity> entityType,
                String name
        ) {
            event.registerEntityRenderer(
                    entityType,
                    context -> new APArrowRenderer<>(context, name)
            );
        }

        private static void registerBowOverrides() {
            Arrays.stream(BOWS).forEach(bow -> {
                Item item = bow.get();

                ItemProperties.register(
                        item,
                        ResourceLocation.withDefaultNamespace("pull"),
                        (stack, level, player, seed) -> {
                            if (player == null) {
                                return 0.0F;
                            }

                            if (player.getUseItem() != stack) {
                                return 0.0F;
                            }

                            return (float) (
                                    stack.getUseDuration(player)
                                            - player.getUseItemRemainingTicks()
                            ) / 20.0F;
                        }
                );

                ItemProperties.register(
                        item,
                        ResourceLocation.withDefaultNamespace("pulling"),
                        (stack, level, player, seed) ->
                                player != null
                                        && player.isUsingItem()
                                        && player.getUseItem() == stack
                                        ? 1.0F
                                        : 0.0F
                );
            });
        }
    }
}