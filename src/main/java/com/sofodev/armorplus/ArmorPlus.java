package com.sofodev.armorplus;

import com.sofodev.armorplus.blocks.benches.Benches;
import com.sofodev.armorplus.blocks.benches.BlockBench;
import com.sofodev.armorplus.blocks.castle.BrickColor;
import com.sofodev.armorplus.blocks.castle.base.BlockStoneBrick;
import com.sofodev.armorplus.blocks.castle.base.BlockStoneBrickCorner;
import com.sofodev.armorplus.blocks.castle.base.BlockStoneBrickTower;
import com.sofodev.armorplus.blocks.castle.base.BlockStoneBrickWall;
import com.sofodev.armorplus.blocks.dungeon.BlockDungeonEnder;
import com.sofodev.armorplus.blocks.dungeon.EnderType;
import com.sofodev.armorplus.blocks.lava.*;
import com.sofodev.armorplus.blocks.normal.BlockCompressedObsidian;
import com.sofodev.armorplus.blocks.special.BlockTrophy;
import com.sofodev.armorplus.blocks.special.Trophy;
import com.sofodev.armorplus.blocks.v2.BlockMetal;
import com.sofodev.armorplus.caps.abilities.AbilityDataHandler;
import com.sofodev.armorplus.client.GuiHandler;
import com.sofodev.armorplus.entity.dungeon.guardianoverlord.EntityGuardianOverlord;
import com.sofodev.armorplus.entity.dungeon.guardianoverlord.RenderGuardianOverlord;
import com.sofodev.armorplus.entity.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import com.sofodev.armorplus.entity.dungeon.guardianoverlord.projectile.RenderFreezeBomb;
import com.sofodev.armorplus.entity.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.entity.dungeon.skeletalking.RenderSkeletalKing;
import com.sofodev.armorplus.entity.dungeon.skeletalking.projectile.EntityWitherMinion;
import com.sofodev.armorplus.entity.dungeon.skeletalking.projectile.RenderWitherMinion;
import com.sofodev.armorplus.entity.entityarrow.*;
import com.sofodev.armorplus.entity.mobs.EntityEnderDragonZombie;
import com.sofodev.armorplus.entity.mobs.EntityIceGolem;
import com.sofodev.armorplus.entity.render.RenderEnderDragonZombie;
import com.sofodev.armorplus.entity.render.RenderIceGolem;
import com.sofodev.armorplus.entity.render.RenderModdedArrow;
import com.sofodev.armorplus.items.arrows.ArrowType;
import com.sofodev.armorplus.items.arrows.ItemSpecialArrow;
import com.sofodev.armorplus.items.base.ItemBase;
import com.sofodev.armorplus.items.base.ItemCosmetic;
import com.sofodev.armorplus.items.books.ItemAPBook;
import com.sofodev.armorplus.items.consumables.ItemRedstoneApple;
import com.sofodev.armorplus.items.consumables.ItemTGOTG;
import com.sofodev.armorplus.items.materials.ItemLavaCrystal;
import com.sofodev.armorplus.items.materials.ItemMaterial;
import com.sofodev.armorplus.items.materials.ItemMaterial.Ingredient;
import com.sofodev.armorplus.potions.PotionEmpty;
import com.sofodev.armorplus.registry.ModRegistryUtils;
import com.sofodev.armorplus.registry.ModTileEntities;
import com.sofodev.armorplus.tileentity.TESRTrophy;
import com.sofodev.armorplus.tileentity.TileTrophy;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Arrays;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.blocks.lava.BlockLavaMaterial.LavaMaterial.*;
import static com.sofodev.armorplus.blocks.special.Trophy.ANY;
import static com.sofodev.armorplus.blocks.v2.Metals.ELECTRICAL;
import static com.sofodev.armorplus.blocks.v2.Metals.STEEL;
import static com.sofodev.armorplus.client.GuiHandler.Client.getClientGuiElement;
import static com.sofodev.armorplus.config.ModConfig.MainConfig.tgotg;
import static com.sofodev.armorplus.items.enums.Cosmetics.*;
import static com.sofodev.armorplus.items.materials.ItemLavaCrystal.LavaType.INFUSED;
import static com.sofodev.armorplus.items.materials.ItemLavaCrystal.LavaType.NORMAL;
import static com.sofodev.armorplus.util.Utils.setName;
import static com.sofodev.armorplus.util.Utils.setRL;
import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod(MODID)
@Mod.EventBusSubscriber(bus = MOD)
public class ArmorPlus {
    public static final String MODID = "armorplus";

    public static ItemGroup tabArmorplus = new ItemGroup(setName("armors")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.LEATHER_CHESTPLATE);
        }
    };
    public static ItemGroup tabArmorplusItems = new ItemGroup(setName("items")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.STICK);
        }
    };
    public static ItemGroup tabArmorplusBlocks = new ItemGroup(setName("block")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Blocks.OBSIDIAN);
        }
    };
    public static ItemGroup tabArmorplusWeapons = new ItemGroup(setName("weapons")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.GOLDEN_AXE);
        }
    };

    // public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(),
    // () -> () -> new ServerProxy());

    public static ArmorPlus instance;
    // public static GuiHandler guiHandler = new GuiHandler();

    public ArmorPlus() {
        instance = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModTileEntities::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

       // ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> message -> getClientGuiElement(message));

    }

    private void setup(final FMLCommonSetupEvent event) {
        AbilityDataHandler.register();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        //      OBJLoader.INSTANCE.addDomain(MODID);
        ClientRegistry.bindTileEntitySpecialRenderer(TileTrophy.class, new TESRTrophy());
        //      new CosmeticsRenderInit();
        //      //TConstruct
        //      //  if (LoaderUtils.isTiCIntegrationEnabled()) TiCMaterials.registerMaterialRendering();
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, bus = MOD)
    public static class RenderingEvents {
        @SubscribeEvent
        public static void registerEntityModels(ModelRegistryEvent event) {
            //Mobs
            registerEntityRenderingHandler(EntityEnderDragonZombie.class, RenderEnderDragonZombie.FACTORY);
            registerEntityRenderingHandler(EntityIceGolem.class, RenderIceGolem::new);
            //Arrows
            registerRenderingHandler(EntityCoalArrow.class, "coal");
            registerRenderingHandler(EntityLapisArrow.class, "lapis");
            registerRenderingHandler(EntityRedstoneArrow.class, "redstone");
            registerRenderingHandler(EntityLavaArrow.class, "lava");
            registerRenderingHandler(EntityEnderDragonArrow.class, "ender_dragon");
            //Bosses
            registerEntityRenderingHandler(EntityGuardianOverlord.class, RenderGuardianOverlord::new);
            registerEntityRenderingHandler(EntitySkeletalKing.class, RenderSkeletalKing::new);
            //Boss Projectiles
            registerEntityRenderingHandler(EntityFreezeBomb.class, RenderFreezeBomb::new);
            registerEntityRenderingHandler(EntityWitherMinion.class, RenderWitherMinion::new);
        }

        private static void registerRenderingHandler(Class<? extends EntityArrow> entityClass, String name) {
            registerEntityRenderingHandler(entityClass, rm -> new RenderModdedArrow<>(rm, name));
        }
    }

    @Mod.EventBusSubscriber(bus = MOD)
    public static class RegistryEvents {
        @ObjectHolder(MODID + ":ender_dragon_zombie")
        public static EntityType<?> ENTITY_ENDER_DRAGON_ZOMBIE;
        @ObjectHolder(MODID + ":ice_golem")
        public static EntityType<?> ENTITY_ICE_GOLEM;
        @ObjectHolder(MODID + ":guardian_overlord")
        public static EntityType<?> ENTITY_GUARDIAN_OVERLORD;
        @ObjectHolder(MODID + ":skeletal_king")
        public static EntityType<?> ENTITY_SKELETAL_KING;
        @ObjectHolder(MODID + ":wither_minion")
        public static EntityType<?> ENTITY_WITHER_MINION;
        @ObjectHolder(MODID + ":freeze_bomb")
        public static EntityType<?> ENTITY_FREEZE_BOMB;
        @ObjectHolder(MODID + ":coal_arrow")
        public static EntityType<?> ENTITY_COAL_ARROW;
        @ObjectHolder(MODID + ":lapis_arrow")
        public static EntityType<?> ENTITY_LAPIS_ARROW;
        @ObjectHolder(MODID + ":redstone_arrow")
        public static EntityType<?> ENTITY_REDSTONE_ARROW;
        @ObjectHolder(MODID + ":infused_lava_arrow")
        public static EntityType<?> ENTITY_INFUSED_LAVA_ARROW;
        @ObjectHolder(MODID + ":ender_dragon_arrow")
        public static EntityType<?> ENTITY_ENDER_DRAGON_ARROW;
        @ObjectHolder(MODID + ":empty_potion")
        public static Potion POTION_EMPTY;
        @ObjectHolder(MODID + ":empty_potion_type")
        public static PotionType POTION_TYPE_EMPTY;

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            IForgeRegistry<Block> registry = event.getRegistry();

            registry.registerAll(
                new BlockCrystalOre().setRegistryName(setRL("ore_lava_crystal")),
                new BlockCompressedObsidian().setRegistryName(setRL("compressed_obsidian")),
                new BlockLavaCactus().setRegistryName(setRL("lava_cactus")),
                new BlockMetal(STEEL).setRegistryName(setRL("block_steel")),
                new BlockMetal(ELECTRICAL).setRegistryName(setRL("block_electrical")),
                new BlockLavaNetherBrick().setRegistryName(setRL("block_lava_nether_brick")),
                new BlockLavaInfuser(false).setRegistryName(setRL("lava_infuser")),
                new BlockLavaInfuser(true).setRegistryName(setRL("lava_infuser_infusing")),
                new BlockLavaMaterial(LAVA_CRYSTAL).setRegistryName(setRL("block_lava_crystal")),
                new BlockLavaMaterial(INFUSED_LAVA_CRYSTAL).setRegistryName(setRL("block_infused_lava_crystal")),
                new BlockLavaMaterial(COMPRESSED_LAVA_CRYSTAL).setRegistryName(setRL("block_compressed_lava_crystal")),
                new BlockLavaMaterial(COMPRESSED_INFUSED_LAVA_CRYSTAL).setRegistryName(setRL("block_compressed_infused_lava_crystal")),
                new BlockLavaMaterial(LAVA_INFUSED_OBSIDIAN).setRegistryName(setRL("block_infused_obsidian")),
                new BlockMeltingObsidian().setRegistryName(setRL("block_melting_obsidian"))
            );
            for (Benches bench : Benches.values()) {
                registry.register(new BlockBench(bench).setRegistryName(setRL(bench.getName())));
            }
            for (BrickColor color : BrickColor.values()) {
                String name = color.getName() + "_stone_brick";
                BlockStoneBrick brick = new BlockStoneBrick(color);
                brick.setRegistryName(setRL(name));
                registry.register(brick);
                registry.register(new BlockStoneBrickTower(color).setRegistryName(setRL(name + "_tower")));
                registry.register(new BlockStoneBrickCorner(color, brick.getDefaultState()).setRegistryName(name + "_corner"));
                registry.register(new BlockStoneBrickWall(brick).setRegistryName(name + "_wall"));
            }
            for (EnderType type : EnderType.values()) {
                registry.register(new BlockDungeonEnder(type).setRegistryName(setRL(type.getName())));
            }
            for (Trophy type : Trophy.values()) {
                registry.register(new BlockTrophy(type).setRegistryName(setRL(type == ANY ? "trophy" : type.getName() + "_trophy")));
            }
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegister) {
            itemRegister.getRegistry().registerAll(
                new ItemRedstoneApple(new Item.Properties().group(ItemGroup.SEARCH).group(tabArmorplusItems).defaultMaxDamage(0).maxStackSize(64)).setRegistryName(setRL("redstone_apple")),
                new ItemLavaCrystal(NORMAL, new Item.Properties().group(ItemGroup.SEARCH).group(tabArmorplusItems).defaultMaxDamage(0).maxStackSize(64)).setRegistryName(setRL(NORMAL.getName())),
                new ItemLavaCrystal(INFUSED, new Item.Properties().group(ItemGroup.SEARCH).group(tabArmorplusItems).defaultMaxDamage(0).maxStackSize(64)).setRegistryName(setRL(INFUSED.getName())),
                new ItemTGOTG(new Item.Properties().defaultMaxDamage(tgotg.maxUses).group(ArmorPlus.tabArmorplusItems)).setRegistryName(setRL("the_gift_of_the_gods")),
                //   new ItemDevTool().setRegistryName(setRL("dev_tool")),
                new ItemCosmetic(TWITCH).setRegistryName(setRL("twitch")),
                new ItemCosmetic(BEAM).setRegistryName(setRL("beam")),
                new ItemCosmetic(THE_DRAGON_TEAM).setRegistryName(setRL("dragon")),
                new ItemCosmetic(MODDED_CITY).setRegistryName(setRL("modded_city")),
                new ItemCosmetic(BTM_MOON).setRegistryName(setRL("btm_moon")),
                new ItemCosmetic(M1JORDAN).setRegistryName(setRL("m1jordan")),
                new ItemCosmetic(JON_BAMS).setRegistryName(setRL("jon_bams")),
                new ItemCosmetic(TEAM_RAPTURE).setRegistryName(setRL("team_rapture")),
                new ItemSpecialArrow(ArrowType.COAL, new Item.Properties().group(tabArmorplusWeapons)).setRegistryName(setRL("coal_arrow")),
                new ItemSpecialArrow(ArrowType.LAPIS, new Item.Properties().group(tabArmorplusWeapons)).setRegistryName(setRL("lapis_arrow")),
                new ItemSpecialArrow(ArrowType.REDSTONE, new Item.Properties().group(tabArmorplusWeapons)).setRegistryName(setRL("redstone_arrow")),
                new ItemSpecialArrow(ArrowType.INFUSED_LAVA, new Item.Properties().group(tabArmorplusWeapons)).setRegistryName(setRL("infused_lava_arrow")),
                new ItemSpecialArrow(ArrowType.ENDER_DRAGON, new Item.Properties().group(tabArmorplusWeapons)).setRegistryName(setRL("ender_dragon_arrow")),
                //new ItemSpawnStructure("tower_spawn_item", StructureGenNBT.TOWER).setRegistryName(setRL("tower")),
                //new ItemSpawnStructure("ender_dungeon_floor_1_spawn_item", StructureGenNBT.ENDER_DUNGEON_FLOOR_1).setRegistryName(setRL("ender_dungeon")),
                new ItemAPBook(new Item.Properties().group(tabArmorplusItems)).setRegistryName(setRL("book")),
                new ItemBase(new Item.Properties().group(tabArmorplusItems)).setRegistryName(setRL("steel_ingot")),
                new ItemBase(new Item.Properties().group(tabArmorplusItems)).setRegistryName(setRL("electrical_ingot"))
            );
            for (Ingredient material : Ingredient.values()) {
                itemRegister.getRegistry().register(new ItemMaterial(new Item.Properties().group(tabArmorplusItems)).setRegistryName(setRL(material.getName())));
            }
            registerMost(itemRegister.getRegistry());
        }

        private static void registerMost(IForgeRegistry<Item> registry) {
            ModRegistryUtils.registerItems(registry);
            // ==== ITEM BLOCKS ==== \\
            registerItemBlock(registry,
                "ore_lava_crystal",
                "compressed_obsidian",
                "lava_cactus",
                "block_steel",
                "block_electrical",
                "block_lava_nether_brick",
                "lava_infuser",
                "lava_infuser_infusing",
                "block_lava_crystal",
                "block_infused_lava_crystal",
                "block_compressed_lava_crystal",
                "block_compressed_infused_lava_crystal",
                "block_infused_obsidian",
                "block_melting_obsidian");
            Arrays.stream(Benches.values()).forEach(bench -> registerItemBlock(registry, bench.getName()));
            Arrays.stream(BrickColor.values()).forEach(color -> {
                String name = color.getName() + "_stone_brick";
                BlockStoneBrick brick = new BlockStoneBrick(color);
                brick.setRegistryName(setRL(name));
                registerItemBlock(registry, name);
                registerItemBlock(registry, name + "_tower");
                registerItemBlock(registry, name + "_corner");
                registerItemBlock(registry, name + "_wall");
            });
            Arrays.stream(EnderType.values()).forEach(type -> registerItemBlock(registry, type.getName()));
            Arrays.stream(Trophy.values()).forEach(type -> registerItemBlock(registry, type == ANY ? "trophy" : type.getName() + "_trophy"));
        }

        @SubscribeEvent
        public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
            EntityType.register(setRL("ender_dragon_zombie").toString(), EntityType.Builder.create(EntityEnderDragonZombie.class, EntityEnderDragonZombie::new));
            EntityType.register(setRL("ice_golem").toString(), EntityType.Builder.create(EntityIceGolem.class, EntityIceGolem::new));
            EntityType.register(setRL("guardian_overlord").toString(), EntityType.Builder.create(EntityGuardianOverlord.class, EntityGuardianOverlord::new));
            EntityType.register(setRL("skeletal_king").toString(), EntityType.Builder.create(EntitySkeletalKing.class, EntitySkeletalKing::new));
            EntityType.register(setRL("wither_minion").toString(), EntityType.Builder.create(EntityWitherMinion.class, EntityWitherMinion::new));
            EntityType.register(setRL("freeze_bomb").toString(), EntityType.Builder.create(EntityFreezeBomb.class, EntityFreezeBomb::new));
            EntityType.register(setRL("coal_arrow").toString(), EntityType.Builder.create(EntityCoalArrow.class, EntityCoalArrow::new));
            EntityType.register(setRL("lapis_arrow").toString(), EntityType.Builder.create(EntityLapisArrow.class, EntityLapisArrow::new));
            EntityType.register(setRL("redstone_arrow").toString(), EntityType.Builder.create(EntityRedstoneArrow.class, EntityRedstoneArrow::new));
            EntityType.register(setRL("infused_lava_arrow").toString(), EntityType.Builder.create(EntityLavaArrow.class, EntityLavaArrow::new));
            EntityType.register(setRL("ender_dragon_arrow").toString(), EntityType.Builder.create(EntityEnderDragonArrow.class, EntityEnderDragonArrow::new));
        }

        @SubscribeEvent
        public static void registerPotions(RegistryEvent.Register<Potion> event) {
            event.getRegistry().register(new PotionEmpty().setRegistryName(setRL("empty_potion")));
        }

        @SubscribeEvent
        public static void registerPotionTypes(RegistryEvent.Register<PotionType> event) {
            event.getRegistry().register(new PotionType().setRegistryName(setRL("empty_potion_type")));
        }

        /**
         * Items
         */
        private static void registerItemBlock(IForgeRegistry<Item> registry, String... regNames) {
            for (String block : regNames) {
                ResourceLocation location = setRL(block);
                Block theBlock = ForgeRegistries.BLOCKS.getValue(location);
                if (theBlock != null) {
                    registry.register(new ItemBlock(theBlock, new Item.Properties().group(ArmorPlus.tabArmorplusBlocks)).setRegistryName(location));
                }
            }
        }
    }
}