package com.sofodev.armorplus;

import com.mojang.brigadier.CommandDispatcher;
import com.sofodev.armorplus.client.ClientLifecycleHandler;
import com.sofodev.armorplus.common.caps.abilities.CapabilityAbility;
import com.sofodev.armorplus.common.caps.abilities.data.AbilityData;
import com.sofodev.armorplus.common.commands.AbilityArgument;
import com.sofodev.armorplus.common.commands.CommandAbilities;
import com.sofodev.armorplus.common.commands.CommandArmorPlus;
import com.sofodev.armorplus.common.registry.ModTileEntities;
import com.sofodev.armorplus.common.registry.blocks.castle.BrickColor;
import com.sofodev.armorplus.common.registry.blocks.castle.base.BlockStoneBrick;
import com.sofodev.armorplus.common.registry.blocks.castle.base.BlockStoneBrickCorner;
import com.sofodev.armorplus.common.registry.blocks.castle.base.BlockStoneBrickTower;
import com.sofodev.armorplus.common.registry.blocks.castle.base.BlockStoneBrickWall;
import com.sofodev.armorplus.common.registry.blocks.dungeon.BlockDungeonEnder;
import com.sofodev.armorplus.common.registry.blocks.dungeon.EnderType;
import com.sofodev.armorplus.common.registry.blocks.lava.*;
import com.sofodev.armorplus.common.registry.blocks.normal.BlockCompressedObsidian;
import com.sofodev.armorplus.common.registry.blocks.special.BlockTrophy;
import com.sofodev.armorplus.common.registry.blocks.special.Trophy;
import com.sofodev.armorplus.common.registry.blocks.v2.BlockMetal;
import com.sofodev.armorplus.common.registry.entity.dungeon.guardianoverlord.EntityGuardianOverlord;
import com.sofodev.armorplus.common.registry.entity.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.common.registry.entity.dungeon.skeletalking.projectile.EntityWitherMinion;
import com.sofodev.armorplus.common.registry.entity.entityarrow.*;
import com.sofodev.armorplus.common.registry.entity.mobs.EntityEnderDragonZombie;
import com.sofodev.armorplus.common.registry.entity.mobs.EntityIceGolem;
import com.sofodev.armorplus.common.registry.items.arrows.ArrowType;
import com.sofodev.armorplus.common.registry.items.arrows.ItemSpecialArrow;
import com.sofodev.armorplus.common.registry.items.base.ItemBase;
import com.sofodev.armorplus.common.registry.items.base.ItemCosmetic;
import com.sofodev.armorplus.common.registry.items.books.ItemAPBook;
import com.sofodev.armorplus.common.registry.items.consumables.ItemRedstoneApple;
import com.sofodev.armorplus.common.registry.items.consumables.ItemTGOTG;
import com.sofodev.armorplus.common.registry.items.materials.ItemLavaCrystal;
import com.sofodev.armorplus.common.registry.items.materials.ItemMaterial;
import com.sofodev.armorplus.common.registry.items.materials.ItemMaterial.Ingredient;
import com.sofodev.armorplus.common.registry.potions.PotionEmpty;
import net.minecraft.block.Block;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.ArgumentSerializer;
import net.minecraft.command.arguments.ArgumentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Arrays;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.common.caps.abilities.ImplementedAbilities.NONE;
import static com.sofodev.armorplus.common.caps.abilities.ImplementedAbilities.*;
import static com.sofodev.armorplus.common.caps.abilities.data.MaterialType.*;
import static com.sofodev.armorplus.common.config.ModConfig.MainConfig.tgotg;
import static com.sofodev.armorplus.common.registry.ModRegistryUtils.registerItems;
import static com.sofodev.armorplus.common.registry.blocks.lava.BlockLavaMaterial.LavaMaterial.*;
import static com.sofodev.armorplus.common.registry.blocks.special.Trophy.ANY;
import static com.sofodev.armorplus.common.registry.blocks.v2.Metals.ELECTRICAL;
import static com.sofodev.armorplus.common.registry.blocks.v2.Metals.STEEL;
import static com.sofodev.armorplus.common.registry.items.enums.Cosmetics.*;
import static com.sofodev.armorplus.common.registry.items.materials.ItemLavaCrystal.LavaType.INFUSED;
import static com.sofodev.armorplus.common.registry.items.materials.ItemLavaCrystal.LavaType.NORMAL;
import static com.sofodev.armorplus.common.util.Utils.setName;
import static com.sofodev.armorplus.common.util.Utils.setRL;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

@Mod("armorplus")
@Mod.EventBusSubscriber(bus = MOD, modid = MODID)
public class ArmorPlus {

    public static final String MODID = "armorplus1";
    public static final String MODNAME = "ArmorPlus";
    public static final String VERSION = "1.13.2-12.0.0.2";

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

    public static ArmorPlus instance;
    // public static GuiHandler guiHandler = new GuiHandler();

    public ArmorPlus() {
        instance = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModTileEntities::registerAll);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().<FMLClientSetupEvent>addListener(e -> new ClientLifecycleHandler().clientSetup(e));
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        CapabilityAbility.register();
    }

    @SubscribeEvent
    public void serverStarting(FMLServerStartingEvent evt) {
        final CommandDispatcher<CommandSource> dispatcher = evt.getCommandDispatcher();
        ArgumentTypes.register(new ResourceLocation("armorplus:abilities"), AbilityArgument.class, new ArgumentSerializer<>(AbilityArgument::ability));
        CommandArmorPlus.register(dispatcher);
        CommandAbilities.register(dispatcher);
    }

    @Mod.EventBusSubscriber(bus = MOD, modid = MODID)
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
            for (BrickColor color : BrickColor.values()) {
                String name = color.getName() + "_stone_brick";
                BlockStoneBrick brick = new BlockStoneBrick(color);
                brick.setRegistryName(setRL(name));
                registry.registerAll(brick,
                    new BlockStoneBrickTower(color).setRegistryName(setRL(name + "_tower")),
                    new BlockStoneBrickCorner(color, brick.getDefaultState()).setRegistryName(name + "_corner"),
                    new BlockStoneBrickWall(brick).setRegistryName(name + "_wall")
                );
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
            registerMostItems(itemRegister.getRegistry());
        }

        private static void registerMostItems(IForgeRegistry<Item> registry) {
            registerItems(registry);
            registerItems(registry,
                CHICKEN, SLIME, COAL, REDSTONE, LAPIS, EMERALD, OBSIDIAN, INFUSED_LAVA, GUARDIAN, SUPER_STAR, ENDER_DRAGON, ARDITE, COBALT, MANYULLYN, PIG_IRON, KNIGHT_SLIME, ULTIMATE
            );
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
            //   createSpawnEgg(registry, ENTITY_ENDER_DRAGON_ZOMBIE, 0x721164, 0x00ff00, EntityEnderDragonZombie.class);
            //   createSpawnEgg(registry, ENTITY_GUARDIAN_OVERLORD, 0x7ae4ff, 0x79a6ff, EntityGuardianOverlord.class);
            //   createSpawnEgg(registry, ENTITY_SKELETAL_KING, 0x665b52, 0x845833, EntitySkeletalKing.class);
        }

        // private static void createSpawnEgg(IForgeRegistry<Item> registry, EntityType<?> type, int colorA, int colorB, Class<? extends Entity> entity) {
        //     ItemModdedSpawnEgg egg = new ItemModdedSpawnEgg(type, colorA, colorB);
        //     egg.setRegistryName(entity.getName() + "_spawn_egg");
        //     registry.register(egg);
        //     eggs.put(egg, entity);
        // }

        @SubscribeEvent
        public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
            registerEntity(setRL("ender_dragon_zombie"), EntityType.Builder.create(EntityEnderDragonZombie.class, EntityEnderDragonZombie::new));
            registerEntity(setRL("ice_golem"), EntityType.Builder.create(EntityIceGolem.class, EntityIceGolem::new));
            registerEntity(setRL("guardian_overlord"), EntityType.Builder.create(EntityGuardianOverlord.class, EntityGuardianOverlord::new));
            registerEntity(setRL("skeletal_king"), EntityType.Builder.create(EntitySkeletalKing.class, EntitySkeletalKing::new));
            registerEntity(setRL("wither_minion"), EntityType.Builder.create(EntityWitherMinion.class, EntityWitherMinion::new));
            registerEntity(setRL("freeze_bomb"), EntityType.Builder.create(EntityFreezeBomb.class, EntityFreezeBomb::new));
            registerEntity(setRL("coal_arrow"), EntityType.Builder.create(EntityCoalArrow.class, EntityCoalArrow::new));
            registerEntity(setRL("lapis_arrow"), EntityType.Builder.create(EntityLapisArrow.class, EntityLapisArrow::new));
            registerEntity(setRL("redstone_arrow"), EntityType.Builder.create(EntityRedstoneArrow.class, EntityRedstoneArrow::new));
            registerEntity(setRL("infused_lava_arrow"), EntityType.Builder.create(EntityLavaArrow.class, EntityLavaArrow::new));
            registerEntity(setRL("ender_dragon_arrow"), EntityType.Builder.create(EntityEnderDragonArrow.class, EntityEnderDragonArrow::new));
        }

        private static void registerEntity(ResourceLocation loc, EntityType.Builder<?> type) {
            EntityType.register(loc.toString(), type
                .tracker(64, 1, true)
            );
        }

        @SubscribeEvent
        public static void registerPotions(RegistryEvent.Register<Potion> event) {
            event.getRegistry().register(new PotionEmpty().setRegistryName(setRL("empty_potion")));
        }

        @SubscribeEvent
        public static void registerPotionTypes(RegistryEvent.Register<PotionType> event) {
            event.getRegistry().register(new PotionType().setRegistryName(setRL("empty_potion_type")));
        }

        @SubscribeEvent
        public static void registerCapability(RegistryEvent.Register.NewRegistry event) {
            if (ARMOR_ABILITY_REGISTRY == null) {
                ResourceLocation registryName = setRL("armor_abilities");
                ARMOR_ABILITY_REGISTRY = new RegistryBuilder<AbilityData>().setType(AbilityData.class).setName(registryName).create();
            }
            //  if (WEAPON_ABILITY_REGISTRY == null) {
            //      ResourceLocation registryName = setRL("weapon_abilities");
            //      WEAPON_ABILITY_REGISTRY = new RegistryBuilder<AbilityData>().setType(AbilityData.class).setName(registryName).create();
            //  }
        }

        @SubscribeEvent
        public static void registerAbilities(RegistryEvent.Register<AbilityData> event) {
            registerAbility(event,
                NONE, NIGHT_VISION, WATER_BREATHING, RESISTANCE, FIRE_RESISTANCE, HASTE, SPEED, JUMP_BOOST, REGENERATION, STRENGTH, INVISIBILITY, ABSORPTION,
                WITHER_PROOF, FLIGHT, STEP_ASSIST, BONUS_XP_ON_KILL, WALK_ON_LAVA, SWIMMING_SPEED
            );
        }

        private static void registerAbility(RegistryEvent.Register<AbilityData> event, AbilityData... dataList) {
            for (AbilityData data : dataList) {
                IForgeRegistry<AbilityData> registry = event.getRegistry();
                if (!registry.containsValue(data)) {
                    event.getRegistry().register(data);
                }
            }
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