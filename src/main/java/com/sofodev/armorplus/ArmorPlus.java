package com.sofodev.armorplus;

import com.sofodev.armorplus.registry.APItems;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.blocks.castle.BrickColor;
import com.sofodev.armorplus.registry.entities.arrows.APArrowEntity;
import com.sofodev.armorplus.registry.entities.arrows.impl.*;
import com.sofodev.armorplus.registry.entities.bosses.SkeletalKingRenderer;
import com.sofodev.armorplus.registry.entities.bosses.WitherMinionRenderer;
import com.sofodev.armorplus.registry.entities.bosses.WitherlingRenderer;
import com.sofodev.armorplus.registry.items.armors.APArmorMaterial;
import com.sofodev.armorplus.registry.items.tools.properties.APToolProperties;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;

import static com.sofodev.armorplus.registry.ModEntities.*;
import static com.sofodev.armorplus.utils.Utils.getAPItem;
import static com.sofodev.armorplus.utils.Utils.setName;
import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod("armorplus")
@Mod.EventBusSubscriber(bus = MOD, modid = ArmorPlus.MODID)
public class ArmorPlus {

    public static final String MODID = "armorplus";
    public static final String MODNAME = "ArmorPlus";
    public static final String VERSION = "1.16.3-16.0.0.1";

    private static ArmorPlus instance;

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);

    /**
     * Used as an "upper ground" variable, which sets the limit for the sets which use these materials.
     */
    public static final int AP_ARMOR_MATERIAL_LENGTH = APArmorMaterial.values().length;
    public static final int AP_TOOL_MATERIAL_LENGTH = APToolProperties.values().length;
    public static final int AP_STONE_BRICKS_LENGTH = BrickColor.values().length;

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
        MinecraftForge.EVENT_BUS.register(this);
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientInit);
    }

    private void clientInit(FMLClientSetupEvent event) {
        registerRenderingHandler(CoalArrowEntity.class, "coal");
        registerRenderingHandler(LapisArrowEntity.class, "lapis");
        registerRenderingHandler(RedstoneArrowEntity.class, "redstone");
        registerRenderingHandler(EmeraldArrowEntity.class, "emerald");
        registerRenderingHandler(ObsidianArrowEntity.class, "obsidian");
        registerRenderingHandler(InfusedLavaArrowEntity.class, "lava");
        registerRenderingHandler(GuardianArrowEntity.class, "guardian");
        registerRenderingHandler(SuperStarArrowEntity.class, "super_star");
        registerRenderingHandler(EnderDragonArrowEntity.class, "ender_dragon");
        registerEntityRenderingHandler(SKELETAL_KING.get(), SkeletalKingRenderer::new);
        registerEntityRenderingHandler(WITHER_MINION.get(), WitherMinionRenderer::new);
        registerEntityRenderingHandler(WITHERLING.get(), WitherlingRenderer::new);
        this.setRenderLayer(ModBlocks.LAVA_INFUSER);
    }

    @SafeVarargs
    private final void setRenderLayer(RegistryObject<Block>... blocks) {
        Arrays.stream(blocks).forEach(block -> RenderTypeLookup.setRenderLayer(block.get(), RenderType.getCutout()));
    }

    private static void registerRenderingHandler(Class<? extends APArrowEntity> entityClass, String name) {
        //    registerEntityRenderingHandler(entityClass, rm -> new APArrowRenderer<>(rm, name));
    }

    //      ClientRegistry.bindTileEntitySpecialRenderer(TileTrophy.class, new TESRTrophy());
    //  ClientRegistry.bindTileEntitySpecialRenderer(TilePortal.class, new TESRPortal());

    public static ArmorPlus getInstance() {
        return instance;
    }
}