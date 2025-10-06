package com.sofodev.armorplus;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ConfigHelper;
import com.sofodev.armorplus.registry.ModEnchantmentEffects;
import com.sofodev.armorplus.registry.block.castle.BrickColor;
import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.APArrowRenderer;
import com.sofodev.armorplus.registry.item.tool.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.item.tool.properties.tool.APToolProperties;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    /**
     * Used as an "upper ground" variable, which sets the limit for the sets which use these materials.
     */
    public static final int AP_TOOL_MATERIAL_LENGTH = APToolProperties.values().length;
    public static final int AP_STONE_BRICKS_LENGTH = BrickColor.values().length;
    public static final int AP_MACE_MAT_LENGTH = APMaceMaterial.values().length;

    public static Map<Block, ItemLike> SMELTING_MAP = new HashMap<>();
    public static ArmorPlusConfig config;
    public static ArmorPlus instance;

    public ArmorPlus(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        //Config Start
        ArmorPlus.config = ConfigHelper.register(
                ModConfig.Type.COMMON, ArmorPlusConfig::create);
        //Config End
        //Order of registration per https://gist.github.com/pupnewfster/ea38cf3744f23d6b65d67e6f279d5942
        MATERIALS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        //Configured
        //Placed
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

//        ENCHANTMENTS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        EFFECTS.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);

        PROFESSIONS.register(modEventBus);
        POI_TYPES.register(modEventBus);

        modEventBus.addListener(this::onCommonSetup);

    }

    private static Map<Block, ItemLike> registerSmeltingMap() {
        HashMap<Block, ItemLike> map = new HashMap<>();

        List<? extends String> input = config.autoSmeltingInput().get();
        List<? extends String> output = config.autoSmeltingOutput().get();
        if (input.size() != output.size()) {
            throw new IllegalArgumentException("autoSmeltingInput and autoSmeltingOutput in config/ap_config.toml must have the same size!");
        }
        for (int i = 0; i < input.size(); i++) {
            String entryInput = input.get(i);
            String entryOutput = output.get(i);
            Block fromBlock = ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(entryInput));
            Item toItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(entryOutput));
            if (fromBlock != null && toItem != null) {
                map.put(fromBlock, toItem);
            } else {
                ArmorPlus.LOGGER.error("Block with the registry name: {} or Item with the registry name: {} don't exist. Failed to add auto smelt recipe", entryInput, entryOutput);
            }
        }
        return map;
    }

    public static ArmorPlus getInstance() {
        return instance;
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(this::afterSetup);
    }

    private void afterSetup() {
        //        GlobalVars.registerAfterEverything();
        SMELTING_MAP = registerSmeltingMap();
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
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

        private static void registerRenderingHandler(EntityType<? extends APArrowEntity> entityClass, String name) {
            EntityRenderers.register(entityClass, rm -> new APArrowRenderer<>(rm, name));
        }

        private static void registerBowOverrides() {
            Arrays.stream(BOWS).forEach(bow -> {
                ItemProperties.register(bow.map(Item::asItem)
                        .orElse(ItemStack.EMPTY.getItem()), ResourceLocation.withDefaultNamespace("pull"), (stack, level, player, val) -> {
                    if (player == null) {
                        return 0.0F;
                    } else {
                        return player.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration(player) - player.getUseItemRemainingTicks()) / 20.0F;
                    }
                });
                ItemProperties.register(bow.map(Item::asItem)
                        .orElse(ItemStack.EMPTY.getItem()), ResourceLocation.withDefaultNamespace("pulling"), (stack, level, player, val) -> player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F);
            });
        }


        @SuppressWarnings("removal")
        private void setRenderLayer(List<RegistryObject<Block>> blocks) {
            blocks.forEach(block -> ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout()));
        }
    }
}