package com.sofodev.armorplus;

import com.sofodev.armorplus.config.APConfig;
import com.sofodev.armorplus.events.WorldGenEvents;
import com.sofodev.armorplus.registry.*;
import com.sofodev.armorplus.registry.blocks.castle.BrickColor;
import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import com.sofodev.armorplus.registry.entities.arrows.APArrowRenderer;
import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingRenderer;
import com.sofodev.armorplus.registry.entities.bosses.WitherlingRenderer;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.mace.APMaceMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.tool.APToolProperties;
import com.sofodev.armorplus.utils.GlobalVars;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.GeckoLib;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.sofodev.armorplus.registry.APItems.registerAPItems;
import static com.sofodev.armorplus.registry.ModBlocks.registerModBlocks;
import static com.sofodev.armorplus.registry.ModEnchantments.registerEnchantments;
import static com.sofodev.armorplus.registry.ModEntities.*;
import static com.sofodev.armorplus.registry.ModFeatures.FEATURES;
import static com.sofodev.armorplus.registry.ModItems.BOWS;
import static com.sofodev.armorplus.registry.ModItems.registerModItems;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setName;
import static net.minecraft.item.ItemModelsProperties.registerProperty;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;
import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;
import static net.minecraftforge.fml.config.ModConfig.Type.SERVER;
import static net.minecraftforge.fml.loading.FMLEnvironment.dist;

@Mod("armorplus")
@Mod.EventBusSubscriber(bus = MOD, modid = ArmorPlus.MODID)
public class ArmorPlus {

    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";

    private static ArmorPlus instance;

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, MODID);

    /**
     * Used as an "upper ground" variable, which sets the limit for the sets which use these materials.
     */
    public static final int AP_ARMOR_MATERIAL_LENGTH = APArmorMaterial.values().length;
    public static final int AP_TOOL_MATERIAL_LENGTH = APToolProperties.values().length;
    public static final int AP_STONE_BRICKS_LENGTH = BrickColor.values().length;
    public static final int AP_MACE_MAT_LENGTH = APMaceMaterial.values().length;

    public static final ItemGroup AP_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("armors")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(getAPItem("infused_lava_chestplate"));
        }
    };
    public static final ItemGroup AP_ITEM_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("items")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(APItems.INFUSED_LAVA_CRYSTAL.get());
        }
    };
    public static final ItemGroup AP_BLOCK_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("blocks")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.LAVA_CRYSTAL.get());
        }
    };
    public static final ItemGroup AP_WEAPON_GROUP = new ItemGroup(ItemGroup.getGroupCountSafe(), setName("weapons")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(getAPItem("ender_dragon_battle_axe"));
        }
    };

    public ArmorPlus() {
        instance = this;
        GeckoLib.initialize();
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext modLoadingCTX = ModLoadingContext.get();

        modLoadingCTX.registerConfig(SERVER, APConfig.SERVER_SPEC, "ap_config.toml");
        APConfig.loadConfig(APConfig.SERVER_SPEC, FMLPaths.CONFIGDIR.get().resolve("ap_config.toml").toString());
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        ENTITIES.register(modEventBus);
        EFFECTS.register(modEventBus);
        FEATURES.register(modEventBus);
        ATTRIBUTES.register(modEventBus);

        modEventBus.addListener(this::onCommonSetup);
        forgeBus.addListener(EventPriority.HIGH, WorldGenEvents::onBiomeLoad);
        if (dist == CLIENT) {
            modEventBus.addListener(this::clientInit);
        }
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        registerModBlocks();
        registerModItems();
        registerAPItems();
        registerEnchantments();
        registerEntities();
        event.enqueueWork(this::afterSetup);
    }

    private void afterSetup() {
        GlobalVars.registerAfterEverything();
        ModAttributes.registerAttributes();
        ModConfiguredFeatures.registerConfiguredFeatures();
    }

    private void clientInit(FMLClientSetupEvent event) {
        registerRenderingHandler(COAL_ARROW.get(), "coal");
        registerRenderingHandler(LAPIS_ARROW.get(), "lapis");
        registerRenderingHandler(REDSTONE_ARROW.get(), "redstone");
        registerRenderingHandler(EMERALD_ARROW.get(), "emerald");
        registerRenderingHandler(OBSIDIAN_ARROW.get(), "obsidian");
        registerRenderingHandler(INFUSED_LAVA_ARROW.get(), "lava");
        registerRenderingHandler(GUARDIAN_ARROW.get(), "guardian");
        registerRenderingHandler(SUPER_STAR_ARROW.get(), "super_star");
        registerRenderingHandler(ENDER_DRAGON_ARROW.get(), "ender_dragon");
        registerEntityRenderingHandler(ModEntities.SKELETAL_KING.get(), SkeletalKingRenderer::new);
        registerEntityRenderingHandler(ModEntities.WITHERLING.get(), WitherlingRenderer::new);
        this.setRenderLayer(Collections.singletonList(ModBlocks.LAVA_INFUSER));
        registerBowOverrides();
    }

    private void setRenderLayer(List<RegistryObject<Block>> blocks) {
        blocks.forEach(block -> RenderTypeLookup.setRenderLayer(block.get(), RenderType.getCutout()));
    }

    @OnlyIn(CLIENT)
    private static void registerRenderingHandler(EntityType<? extends APArrowEntity> entityClass, String name) {
        registerEntityRenderingHandler(entityClass, rm -> new APArrowRenderer<>(rm, name));
    }

    @OnlyIn(CLIENT)
    private static void registerBowOverrides() {
        Arrays.stream(BOWS).forEach(bow -> {
            registerProperty(bow.get(), new ResourceLocation("pull"), (stack, world, player) -> {
                if (player == null) {
                    return 0.0F;
                } else {
                    return player.getActiveItemStack() != stack ? 0.0F : (float) (stack.getUseDuration() - player.getItemInUseCount()) / 20.0F;
                }
            });
            registerProperty(bow.get(), new ResourceLocation("pulling"), (stack, world, player) -> {
                return player != null && player.isHandActive() && player.getActiveItemStack() == stack ? 1.0F : 0.0F;
            });
        });
    }

    //      ClientRegistry.bindTileEntitySpecialRenderer(TileTrophy.class, new TESRTrophy());

    public static ArmorPlus getInstance() {
        return instance;
    }
}