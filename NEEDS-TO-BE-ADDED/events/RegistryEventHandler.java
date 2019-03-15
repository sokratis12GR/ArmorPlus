/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
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
import com.sofodev.armorplus.caps.abilities.AbilityData;
import com.sofodev.armorplus.caps.abilities.MaterialType;
import com.sofodev.armorplus.enchantments.FuriousEnchantment;
import com.sofodev.armorplus.enchantments.LifeStealEnchantment;
import com.sofodev.armorplus.entity.dungeon.guardianoverlord.EntityGuardianOverlord;
import com.sofodev.armorplus.entity.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import com.sofodev.armorplus.entity.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.entity.dungeon.skeletalking.projectile.EntityWitherMinion;
import com.sofodev.armorplus.entity.entityarrow.*;
import com.sofodev.armorplus.entity.mobs.EntityEnderDragonZombie;
import com.sofodev.armorplus.entity.mobs.EntityIceGolem;
import com.sofodev.armorplus.items.ItemUltimatePart;
import com.sofodev.armorplus.items.armors.APArmorMaterial;
import com.sofodev.armorplus.items.armors.base.ItemArmorBase;
import com.sofodev.armorplus.items.armors.base.ItemArmorV2;
import com.sofodev.armorplus.items.armors.base.ItemUltimateArmor;
import com.sofodev.armorplus.items.armors.horse.ItemBaseHorseArmor;
import com.sofodev.armorplus.items.arrows.ArrowType;
import com.sofodev.armorplus.items.arrows.ItemSpecialArrow;
import com.sofodev.armorplus.items.base.*;
import com.sofodev.armorplus.items.books.ItemAPBook;
import com.sofodev.armorplus.items.consumables.ItemRedstoneApple;
import com.sofodev.armorplus.items.consumables.ItemTGOTG;
import com.sofodev.armorplus.items.dev.ItemDevTool;
import com.sofodev.armorplus.items.dev.ItemSpawnStructure;
import com.sofodev.armorplus.items.enums.MetalItems;
import com.sofodev.armorplus.items.materials.ItemLavaCrystal;
import com.sofodev.armorplus.items.materials.ItemMaterial;
import com.sofodev.armorplus.items.weapons.BattleAxes;
import com.sofodev.armorplus.items.weapons.Bows;
import com.sofodev.armorplus.items.weapons.Swords;
import com.sofodev.armorplus.potions.PotionEmpty;
import com.sofodev.armorplus.registry.APItems;
import com.sofodev.armorplus.sounds.SoundTrapTriggered;
import com.sofodev.armorplus.util.Utils;
import com.sofodev.armorplus.worldgen.nbt.StructureGenNBT;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.ArmorPlus.MODID;
import static com.sofodev.armorplus.blocks.castle.BrickColor.*;
import static com.sofodev.armorplus.blocks.dungeon.EnderType.*;
import static com.sofodev.armorplus.blocks.lava.BlockLavaMaterial.LavaMaterial.*;
import static com.sofodev.armorplus.blocks.special.Trophy.*;
import static com.sofodev.armorplus.blocks.v2.Metals.ELECTRICAL;
import static com.sofodev.armorplus.blocks.v2.Metals.STEEL;
import static com.sofodev.armorplus.caps.abilities.ImplementedAbilities.*;
import static com.sofodev.armorplus.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.global_registry;
import static com.sofodev.armorplus.items.enums.Cosmetics.*;
import static com.sofodev.armorplus.items.materials.ItemLavaCrystal.LavaType.INFUSED;
import static com.sofodev.armorplus.items.materials.ItemLavaCrystal.LavaType.NORMAL;
import static com.sofodev.armorplus.items.materials.ItemMaterial.Ingredient.*;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.registry.ModRegistryUtils.register;
import static com.sofodev.armorplus.registry.ModRegistryUtils.registerParts;
import static com.sofodev.armorplus.util.Utils.setRL;
import static java.util.Arrays.setAll;
import static net.minecraft.entity.EntityType.Builder.createNothing;

/**
 * @author Sokratis Fotkatzikis
 **/
@EventBusSubscriber(modid = ArmorPlus.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryEventHandler {

    public static final EntityType<?> ENTITY_ENDER_DRAGON_ZOMBIE = createNothing(EntityEnderDragonZombie.class).build(null).setRegistryName(new ResourceLocation(MODID, "ender_dragon_zombie"));
    public static final EntityType<?> ENTITY_ICE_GOLEM = createNothing(EntityIceGolem.class).build(null).setRegistryName(new ResourceLocation(MODID, "ice_golem"));
    public static final EntityType<?> ENTITY_GUARDIAN_OVERLORD = createNothing(EntityGuardianOverlord.class).build(null).setRegistryName(new ResourceLocation(MODID, "guardian_overlord"));
    public static final EntityType<?> ENTITY_SKELETAL_KING = createNothing(EntitySkeletalKing.class).build(null).setRegistryName(new ResourceLocation(MODID, "skeletal_king"));
    public static final EntityType<?> ENTITY_WITHER_MINION = createNothing(EntityWitherMinion.class).build(null).setRegistryName(new ResourceLocation(MODID, "wither_minion"));
    public static final EntityType<?> ENTITY_FREEZE_BOMB = createNothing(EntityFreezeBomb.class).build(null).setRegistryName(new ResourceLocation(MODID, "freeze_bomb"));
    public static final EntityType<?> ENTITY_COAL_ARROW = createNothing(EntityCoalArrow.class).build(null).setRegistryName(new ResourceLocation(MODID, "coal_arrow"));
    public static final EntityType<?> ENTITY_LAPIS_ARROW = createNothing(EntityLapisArrow.class).build(null).setRegistryName(new ResourceLocation(MODID, "lapis_arrow"));
    public static final EntityType<?> ENTITY_REDSTONE_ARROW = createNothing(EntityRedstoneArrow.class).build(null).setRegistryName(new ResourceLocation(MODID, "redstone_arrow"));
    public static final EntityType<?> ENTITY_INFUSED_LAVA_ARROW = createNothing(EntityLavaArrow.class).build(null).setRegistryName(new ResourceLocation(MODID, "infused_lava_arrow"));
    public static final EntityType<?> ENTITY_ENDER_DRAGON_ARROW = createNothing(EntityEnderDragonArrow.class).build(null).setRegistryName(new ResourceLocation(MODID, "ender_dragon_arrow"));

    /**
     * Entities
     */
    //   @SuppressWarnings("SameParameterValue")
    //   private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, boolean hasEgg, int primaryColor, int secondaryColor) {
    //       ResourceLocation resourceLocation = setRL(registryName);
    //       if (hasEgg) {
    //           new EntityType(resourceLocation, entityClass, registryName, id, ArmorPlus.instance, trackingRange, updateFrequency, sendVelocityUpdates, primaryColor, secondaryColor);
    //       } else {
    //           registerModEntity(resourceLocation, entityClass, registryName, id, ArmorPlus.instance, trackingRange, updateFrequency, sendVelocityUpdates);
    //       }
    //   }
//
    //   private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int primaryColor, int secondaryColor) {
    //       registerEntities(entityClass, registryName, id, 64, 1, true, true, primaryColor, secondaryColor);
    //   }
//
    //   private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id) {
    //       registerEntities(entityClass, registryName, id, 64, 1, true, false, 0, 0);
    //   }
    @SubscribeEvent
    public static void registerEntities(Register<EntityType<?>> event) {
        //  int id = 0;
        event.getRegistry().registerAll(ENTITY_ENDER_DRAGON_ZOMBIE, ENTITY_ICE_GOLEM, ENTITY_GUARDIAN_OVERLORD, ENTITY_SKELETAL_KING, ENTITY_COAL_ARROW, ENTITY_LAPIS_ARROW, ENTITY_REDSTONE_ARROW, ENTITY_INFUSED_LAVA_ARROW, ENTITY_ENDER_DRAGON_ARROW
        );
        //  registerEntities(EntityCoalArrow.class, "coal_arrow", ++id);
        //  registerEntities(EntityLapisArrow.class, "lapis_arrow", ++id);
        //  registerEntities(EntityRedstoneArrow.class, "redstone_arrow", ++id);
        //  registerEntities(EntityLavaArrow.class, "lava_arrow", ++id);
        //  registerEntities(EntityEnderDragonArrow.class, "ender_dragon_arrow", ++id);
        //  //
        //  registerEntities(EntityFreezeBomb.class, "freeze_bomb", ++id);
        //  registerEntities(EntityWitherMinion.class, "wither_minion", ++id);
        //  registerEntities(EntityEnderDragonZombie.class, "ender_dragon_zombie", ++id,
        //      0x721164, 0x00ff00);
        //  registerEntities(EntityIceGolem.class, "ice_golem", ++id,
        //      0xffffff, 0x00ff00);
        //  //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        //  registerEntities(EntityGuardianOverlord.class, "overlord_of_the_guardians", ++id,
        //      0x7ae4ff, 0x79a6ff);
        //  registerEntities(EntitySkeletalKing.class, "skeletal_king", ++id,
        //      0x665b52, 0x845833);
    }

    /**
     * Blocks
     */
    private static void registerAllBlocks(Register<Block> event, Block[]... blocksArray) {
        Arrays.stream(blocksArray).forEachOrdered(blockList -> registerAllBlocks(event, blockList));
    }

    private static void registerAllBlocks(Register<Block> event, Block... blockList) {
        Arrays.stream(blockList).filter(Utils::isNotNull).forEachOrdered(block -> event.getRegistry().register(block));
    }

    @SubscribeEvent
    public static void registerTileEntities(Register<TileEntityType<?>> event) {
        //       event.getRegistry().registerAll(LAVA_INFUSER, WORKBENCH, HIGH_TECH_BENCH, ULTI_TECH_BENCH, CHAMPION_BENCH, TROPHY);
    }

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        blockCrystalOre = new BlockCrystalOre();
        blockCompressedObsidian = new BlockCompressedObsidian();
        blockLavaCactus = new BlockLavaCactus();
        steelBlock = new BlockMetal(STEEL);
        electricalBlock = new BlockMetal(ELECTRICAL);
        blockLavaNetherBrick = new BlockLavaNetherBrick();
        benchTypes = new Benches[]{Benches.WORKBENCH, Benches.HIGH_TECH_BENCH, Benches.ULTI_TECH_BENCH, Benches.CHAMPION_BENCH};
        benches = new BlockBench[4];
        stoneBrickTypes = new BrickColor[]{
            WHITE, RED, BLACK, BLUE, GREEN, YELLOW, PURPLE
        };
        stoneBricks = new BlockStoneBrick[7];
        stoneBrickTowers = new BlockStoneBrickTower[7];
        stoneBrickCorners = new BlockStoneBrickCorner[7];
        stonebrickWalls = new BlockStoneBrickWall[7];
        lavaInfuser = new BlockLavaInfuser("lava_infuser", false);
        lavaInfuserInfusing = new BlockLavaInfuser("lava_infuser_infusing", true);
        blockLavaCrystal = new BlockLavaMaterial(LAVA_CRYSTAL);
        blockInfusedLavaCrystal = new BlockLavaMaterial(INFUSED_LAVA_CRYSTAL);
        blockCompressedLavaCrystal = new BlockLavaMaterial(COMPRESSED_LAVA_CRYSTAL);
        blockCompressedInfusedLavaCrystal = new BlockLavaMaterial(COMPRESSED_INFUSED_LAVA_CRYSTAL);
        blockLavaInfusedObsidian = new BlockLavaMaterial(LAVA_INFUSED_OBSIDIAN);
        enderTypes = new EnderType[]{
            ENDER_STONE, ENDER_STONE_BRICKS, ENDER_PILLAR, ENDER_GLOWSTONE, ENDER_FLOOR_1, ENDER_FLOOR_2, ENDER_STONE_TRAP
        };
        enderBlocks = new BlockDungeonEnder[7];
        trophies = new BlockTrophy[8];
        types = new Trophy[]{
            ANY, ELDER_GUARDIAN, WITHER_BOSS, ENDER_DRAGON, SKELETAL_KING, GUARDIAN_OVERLORD, DEMONIC_DRAGON, THE_LORD_OF_EVERYTHING
        };
        blockMeltingObsidian = new BlockMeltingObsidian();
        setAll(benches, type -> new BlockBench(benchTypes[type]));
        setAll(stoneBricks, type -> new BlockStoneBrick(stoneBrickTypes[type]));
        setAll(stoneBrickTowers, type -> new BlockStoneBrickTower(stoneBrickTypes[type]));
        setAll(stoneBrickCorners, type -> new BlockStoneBrickCorner(stoneBrickTypes[type], stoneBricks[type].getDefaultState()));
        setAll(stonebrickWalls, type -> new BlockStoneBrickWall(stoneBricks[type]));
        setAll(enderBlocks, type -> new BlockDungeonEnder(enderTypes[type]));
        setAll(trophies, type -> new BlockTrophy(types[type]));
        registerAllBlocks(event, benches);
        registerAllBlocks(event,
            blockCrystalOre, blockCompressedObsidian, steelBlock, electricalBlock, blockLavaNetherBrick, blockLavaCactus, lavaInfuser, lavaInfuserInfusing,
            blockLavaInfusedObsidian, blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal, blockMeltingObsidian
        );
        registerAllBlocks(event, stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        //registerAllBlocks(event, blockBTMMoon);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        registerAllBlocks(event, enderBlocks);
        registerAllBlocks(event, trophies);
    }

    @SubscribeEvent
    public static void registerCapability(Register.NewRegistry event) {
        if (enableExperimentalMode && ABILITY_REGISTRY == null) {
            ResourceLocation registryName = setRL("abilities");
            ABILITY_REGISTRY = new RegistryBuilder<AbilityData>().setType(AbilityData.class).setName(registryName).create();
        }
    }

    @SubscribeEvent
    public static void registerAbilities(Register<AbilityData> event) {
        if (enableExperimentalMode) {
            registerAbility(event,
                NONE, NIGHT_VISION, WATER_BREATHING, RESISTANCE, FIRE_RESISTANCE, HASTE, SPEED, JUMP_BOOST, REGENERATION, STRENGTH, INVISIBILITY, ABSORPTION,
                WITHER_PROOF, FLIGHT, STEP_ASSIST, BONUS_XP_ON_KILL, WALK_ON_LAVA, SWIMMING_SPEED
            );
        }
    }

    private static void registerAbility(Register<AbilityData> event, AbilityData... dataList) {
        for (AbilityData data : dataList) {
            IForgeRegistry<AbilityData> registry = event.getRegistry();
            if (!registry.containsValue(data)) {
                event.getRegistry().register(data);
            }
        }
    }

    /**
     * Enchantments
     */
    @SubscribeEvent
    public static void registerEnchantments(Register<Enchantment> event) {
        event.getRegistry().registerAll(new FuriousEnchantment(), new LifeStealEnchantment());
    }

    /**
     * Potions
     */
    @SubscribeEvent
    public static void registerPotions(Register<Potion> event) {
        event.getRegistry().register(new PotionEmpty());
    }

    /**
     * Sounds
     */
    @SubscribeEvent
    public static void registerSounds(Register<SoundEvent> event) {
        event.getRegistry().register(new SoundTrapTriggered());
    }
}
