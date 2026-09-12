package com.sofodev.armorplus;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ConfigHelper;
import com.sofodev.armorplus.registry.ModEnchantmentEffects;
import com.sofodev.armorplus.registry.block.castle.BrickColor;
import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.APArrowRenderer;
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
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
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

        ArmorPlus.config = ConfigHelper.register(
                modContainer,
                ModConfig.Type.COMMON,
                ArmorPlusConfig::create
        );

        MATERIALS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        EFFECTS.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);

        PROFESSIONS.register(modEventBus);
        POI_TYPES.register(modEventBus);
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