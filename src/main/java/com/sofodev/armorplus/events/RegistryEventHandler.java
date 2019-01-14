/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.blocks.base.BlockBase;
import com.sofodev.armorplus.blocks.benches.BlockBench;
import com.sofodev.armorplus.blocks.benches.ItemBlockBench;
import com.sofodev.armorplus.blocks.dungeon.ItemDungeonBlock;
import com.sofodev.armorplus.enchantments.FuriousEnchantment;
import com.sofodev.armorplus.enchantments.LifeStealEnchantment;
import com.sofodev.armorplus.entity.dungeon.guardianoverlord.EntityGuardianOverlord;
import com.sofodev.armorplus.entity.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import com.sofodev.armorplus.entity.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.entity.dungeon.skeletalking.projectile.EntityWitherMinion;
import com.sofodev.armorplus.entity.entityarrow.*;
import com.sofodev.armorplus.entity.mobs.EntityEnderDragonZombie;
import com.sofodev.armorplus.entity.mobs.EntityIceGolem;
import com.sofodev.armorplus.items.materials.ItemRename;
import com.sofodev.armorplus.potions.PotionEmpty;
import com.sofodev.armorplus.sounds.SoundTrapTriggered;
import com.sofodev.armorplus.tileentity.*;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraftforge.common.util.CompoundDataFixer;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.event.RegistryEvent.MissingMappings;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.Arrays;

import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.tileentity.TileCB.*;
import static com.sofodev.armorplus.tileentity.TileHTB.*;
import static com.sofodev.armorplus.tileentity.TileLavaInfuser.*;
import static com.sofodev.armorplus.tileentity.TileTrophy.*;
import static com.sofodev.armorplus.tileentity.TileUTB.*;
import static com.sofodev.armorplus.tileentity.TileWB.*;
import static net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity;
import static net.minecraftforge.fml.common.registry.GameRegistry.registerTileEntity;

/**
 * @author Sokratis Fotkatzikis
 **/
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class RegistryEventHandler {

    public static final int DATA_FIXER_VERSION = 132;

    @SubscribeEvent
    public static void registerMappings(MissingMappings<Item> e) {
        registerItemFixes();
    }

    @SuppressWarnings("SameParameterValue")
    private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, boolean hasEgg, int primaryColor, int secondaryColor) {
        ResourceLocation resourceLocation = Utils.setRL(registryName);
        if (hasEgg) {
            registerModEntity(resourceLocation, entityClass, registryName, id, ArmorPlus.instance, trackingRange, updateFrequency, sendVelocityUpdates, primaryColor, secondaryColor);
        } else {
            registerModEntity(resourceLocation, entityClass, registryName, id, ArmorPlus.instance, trackingRange, updateFrequency, sendVelocityUpdates);
        }
    }

    private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int primaryColor, int secondaryColor) {
        registerEntities(entityClass, registryName, id, 64, 1, true, true, primaryColor, secondaryColor);
    }

    private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id) {
        registerEntities(entityClass, registryName, id, 64, 1, true, false, 0, 0);
    }

    @SubscribeEvent
    public static void registerEntities(Register<EntityEntry> event) {
        int id = 0;
        registerEntities(EntityCoalArrow.class, "coal_arrow", ++id);
        registerEntities(EntityLapisArrow.class, "lapis_arrow", ++id);
        registerEntities(EntityRedstoneArrow.class, "redstone_arrow", ++id);
        registerEntities(EntityLavaArrow.class, "lava_arrow", ++id);
        registerEntities(EntityEnderDragonArrow.class, "ender_dragon_arrow", ++id);
        //
        registerEntities(EntityFreezeBomb.class, "freeze_bomb", ++id);
        registerEntities(EntityWitherMinion.class, "wither_minion", ++id);
        registerEntities(EntityEnderDragonZombie.class, "ender_dragon_zombie", ++id,
            0x721164, 0x00ff00);
        registerEntities(EntityIceGolem.class, "ice_golem", ++id,
            0xffffff, 0x00ff00);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        registerEntities(EntityGuardianOverlord.class, "overlord_of_the_guardians", ++id,
            0x7ae4ff, 0x79a6ff);
        registerEntities(EntitySkeletalKing.class, "skeletal_king", ++id,
            0x665b52, 0x845833);
    }

    private static void registerAllBlocks(Register<Block> event, Block[]... blocksArray) {
        Arrays.stream(blocksArray).forEachOrdered(blockList -> registerAllBlocks(event, blockList));
    }

    private static void registerAllBlocks(Register<Block> event, Block... blockList) {
        Arrays.stream(blockList).filter(Utils::isNotNull).forEachOrdered(block -> event.getRegistry().register(block));
    }

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        registerAllBlocks(event, benches);
        registerAllBlocks(event,
            blockCrystalOre, blockCompressedObsidian, steelBlock, electricalBlock, blockLavaNetherBrick, blockLavaCactus, lavaInfuser, lavaInfuserInfusing,
            blockLavaInfusedObsidian, blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal
        );
        registerAllBlocks(event, stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        //registerAllBlocks(event, blockBTMMoon);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        registerAllBlocks(event, enderBlocks);
        registerAllBlocks(event, trophies);
        registerTileEntities();
        registerTEFixes();
    }

    private static void registerTEFixes() {
        DataFixer dataFixer = FMLCommonHandler.instance().getDataFixer();
        registerFixesLavaInfuser(dataFixer);
        registerWBFixes(dataFixer);
        registerHTBFixes(dataFixer);
        registerUTBFixes(dataFixer);
        registerCBFixes(dataFixer);
        registerTrophyFixes(dataFixer);
    }

    private static void registerTileEntities() {
        registerTileEntity(TileLavaInfuser.class, Utils.setRL("lava_infuser_tile_entity"));
        registerTileEntity(TileWB.class, Utils.setRL("workbench_tile_entity"));
        registerTileEntity(TileHTB.class, Utils.setRL("high_tech_bench_tile_entity"));
        registerTileEntity(TileUTB.class, Utils.setRL("ulti_tech_tile_entity"));
        registerTileEntity(TileCB.class, Utils.setRL("champion_tile_entity"));
        registerTileEntity(TileTrophy.class, Utils.setRL("trophy_tile_entity"));
    }

    private static void registerItemBlock(Register<Item> event, Block... blocks) {
        Arrays.stream(blocks).forEachOrdered(block -> {
            if (Utils.areNotNull(block, block.getRegistryName())) {
                ItemBlock itemBlock = new ItemBlock(block);
                itemBlock.setRegistryName(block.getRegistryName());
                event.getRegistry().register(itemBlock);
            }
        });
    }

    private static void registerDungeonBlock(Register<Item> event, BlockBase... blocks) {
        Arrays.stream(blocks).forEachOrdered(block -> {
            if (Utils.areNotNull(block, block.getRegistryName())) {
                ItemBlock itemBlock = new ItemDungeonBlock(block);
                event.getRegistry().register(itemBlock);
            }
        });
    }

    private static void registerBenchBlocks(Register<Item> event, BlockBench... blocks) {
        Arrays.stream(blocks).forEachOrdered(block -> {
            if (Utils.areNotNull(block, block.getRegistryName())) {
                ItemBlock itemBlock = new ItemBlockBench(block);
                event.getRegistry().register(itemBlock);
            }
        });
    }

    private static void registerAllItemBlocks(Register<Item> event, Block[]... blockArray) {
        Arrays.stream(blockArray).forEachOrdered(blockList -> registerItemBlock(event, blockList));
    }

    private static void registerAllItems(Register<Item> event, Item[]... itemsArray) {
        Arrays.stream(itemsArray).forEachOrdered(itemList -> registerAllItems(event, itemList));
    }

    private static void registerAllItems(Register<Item> event, Item... itemsArray) {
        Arrays.stream(itemsArray).filter(Utils::isNotNull).forEachOrdered(anItemsArray -> event.getRegistry().register(anItemsArray));
    }

    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        // ==== BLOCKS ==== \\
        registerBenchBlocks(event, benches);
        //registerItemBlock(event, blockBTMMoon);
        registerItemBlock(event,
            blockCrystalOre, blockCompressedObsidian, steelBlock, electricalBlock, blockLavaNetherBrick, blockLavaCactus, lavaInfuser, lavaInfuserInfusing,
            blockLavaInfusedObsidian, blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal
        );
        // ==== DUNGEON BLOCKS ==== \\
        registerAllItemBlocks(event, stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        registerDungeonBlock(event, enderBlocks);
        registerAllItemBlocks(event, trophies);
        // ==== ITEMS ==== \\
        registerAllItems(event,
            bookInfo, steelIngot, electricalIngot, itemRedstoneApple, itemLavaCrystal, itemTGOTG, itemDevTool, theUltimateParts,
            itemCoalArrow, itemLapisArrow, itemRedstoneArrow, itemLavaArrow, itemEnderDragonArrow
        );
        registerAllItems(event, materials);
        //  registerAllItems(event, chainmail, guardianScale, witherBone, enderDragonScale, theUltimateMaterial);
        // ==== SPECIAL ITEMS ===\\
        registerAllItems(event, towerSpawnItem);
        registerItemFixes();
        // ==== COSMETICS ==== \\
        registerAllItems(event, twitchItem, beamItem, theDragonTeamItem, moddedCityItem, jonBamsItem, btmMoon, m1Jordan, teamRapture);
        // ==== GEAR ==== \\
        registerAllItems(event,
            coal, emerald, lapis, lava, obsidian, redstone, chicken, slime, guardian, superStar, enderDragon, theUltimate, ardite, cobalt, manyullyn, pigIron, knightSlime
        );
        registerAllItems(event, horseArmors);
        registerAllItems(event, sword, battleAxe, bow);
    }

    public static void registerItemFixes() {
        ModFixs modFixs = new CompoundDataFixer(FMLCommonHandler.instance().getDataFixer()).init(ArmorPlus.MODID, DATA_FIXER_VERSION);
        modFixs.registerFix(FixTypes.ITEM_INSTANCE, new ItemRename());
    }

    @SubscribeEvent
    public static void registerEnchantments(Register<Enchantment> event) {
        event.getRegistry().registerAll(new FuriousEnchantment(), new LifeStealEnchantment());
    }

    @SubscribeEvent
    public static void registerPotions(Register<Potion> event) {
        event.getRegistry().register(new PotionEmpty());
    }

    @SubscribeEvent
    public static void registerSounds(Register<SoundEvent> event) {
        event.getRegistry().register(new SoundTrapTriggered());
    }
}
