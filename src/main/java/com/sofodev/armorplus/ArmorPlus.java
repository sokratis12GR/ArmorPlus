package com.sofodev.armorplus;

import com.sofodev.armorplus.config.ArmorPlusConfig;
import com.sofodev.armorplus.config.ConfigHelper;
import com.sofodev.armorplus.events.WorldGenEvents;
import com.sofodev.armorplus.network.PacketHandler;
import com.sofodev.armorplus.registry.ModConfiguredFeatures;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.blocks.castle.BrickColor;
import com.sofodev.armorplus.registry.blocks.special.TrophyTileEntityRenderer;
import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import com.sofodev.armorplus.registry.entities.arrows.APArrowRenderer;
import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingRenderer;
import com.sofodev.armorplus.registry.entities.normal.WitherlingRenderer;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.tool.APToolProperties;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

import static com.sofodev.armorplus.registry.ModAttributes.ATTRIBUTES;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModEnchantments.ENCHANTMENTS;
import static com.sofodev.armorplus.registry.ModEntities.*;
import static com.sofodev.armorplus.registry.ModItems.BOWS;
import static com.sofodev.armorplus.registry.ModItems.ITEMS;
import static com.sofodev.armorplus.registry.ModPotions.EFFECTS;
import static com.sofodev.armorplus.registry.ModVillagerPOI.POI_TYPES;
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
    public static final String VERSION = "1.18.2-18.2.1";
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
        //Re-arranged registry order according to: https://gist.github.com/pupnewfster/ea38cf3744f23d6b65d67e6f279d5942
        BLOCKS.register(modEventBus);
        ATTRIBUTES.register(modEventBus);
        EFFECTS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        PROFESSIONS.register(modEventBus);
        POI_TYPES.register(modEventBus);

        modEventBus.addListener(this::onCommonSetup);
        MinecraftForge.EVENT_BUS.addListener(WorldGenEvents::onBiomeLoad);
        if (dist == CLIENT) {
            modEventBus.addListener(this::onClientSetup);
        }
    }

    @OnlyIn(CLIENT)
    private static void registerRenderingHandler(EntityType<? extends APArrowEntity> entityClass, String name) {
        EntityRenderers.register(entityClass, rm -> new APArrowRenderer<>(rm, name));
    }

    @OnlyIn(CLIENT)
    private static void registerBowOverrides() {
        Arrays.stream(BOWS).forEach(bow -> {
            ItemProperties.register(bow.get(), new ResourceLocation("pull"), (stack, level, player, val) -> {
                if (player == null) {
                    return 0.0F;
                } else {
                    return player.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - player.getUseItemRemainingTicks()) / 20.0F;
                }
            });
            ItemProperties.register(bow.get(), new ResourceLocation("pulling"), (stack, level, player, val) -> {
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
        ModConfiguredFeatures.registerConfiguredFeatures();
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
        EntityRenderers.register(SKELETAL_KING.get(), SkeletalKingRenderer::new);
        EntityRenderers.register(WITHERLING.get(), WitherlingRenderer::new);
        this.setRenderLayer(List.of(TROPHY));
        BlockEntityRenderers.register(TROPHY_TYPE.get(), TrophyTileEntityRenderer::new);
        registerBowOverrides();
    }

    private void setRenderLayer(List<RegistryObject<Block>> blocks) {
        blocks.forEach(block -> ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout()));
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T findObjectByKey(ResourceKey<T> key) {
        Registry<T> registry = (Registry<T>) Registry.REGISTRY.get(key.getRegistryName());
        if (registry == null) return null;
        return registry.get(key.getRegistryName());
    }
}