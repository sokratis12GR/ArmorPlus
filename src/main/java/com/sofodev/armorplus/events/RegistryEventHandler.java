/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.caps.abilities.AbilityData;
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
import com.sofodev.armorplus.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.FixTypes;
import net.minecraftforge.common.util.CompoundDataFixer;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.event.RegistryEvent.MissingMappings;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Arrays;

import static com.sofodev.armorplus.caps.abilities.ImplementedAbilities.*;
import static com.sofodev.armorplus.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.util.Utils.setRL;
import static net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity;

/**
 * @author Sokratis Fotkatzikis
 **/
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class RegistryEventHandler {

    public static final int DATA_FIXER_VERSION = 132;

    /**
     * Mappings
     */
    @SubscribeEvent
    public static void registerMappings(MissingMappings<Item> e) {
        registerItemFixes();
    }

    /**
     * Entities
     */
    @SuppressWarnings("SameParameterValue")
    private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, boolean hasEgg, int primaryColor, int secondaryColor) {
        ResourceLocation resourceLocation = setRL(registryName);
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

    private static void registerItemBlock(Register<Item> event, ResourceLocation... registryNames) {
        Arrays.stream(registryNames).forEachOrdered(regName -> {
            Block block = ForgeRegistries.BLOCKS.getValue(regName);
            if (Utils.areNotNull(block, block.getRegistryName())) {
                ItemBlock itemBlock = new ItemBlock(block);
                itemBlock.setRegistryName(block.getRegistryName());
                event.getRegistry().register(itemBlock);
            }
        });
    }

    private static void registerItemBlock(Register<Item> event, String... locations) {
        Arrays.stream(locations).forEach(name -> registerItemBlock(event, setRL(name)));
    }

    private static void registerItemBlock(Register<Item> event, Block... blocks) {
        Arrays.stream(blocks).forEach(block -> registerItemBlock(event, block.getRegistryName()));
    }

    /**
     * Items
     */
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

    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        // ==== BLOCKS ==== \\
        registerItemBlock(event, benches);
        //registerItemBlock(event, blockBTMMoon);
        registerItemBlock(event,
            oreLavaCrystal, blockCompressedObsidian, steelBlock, electricalBlock, blockLavaNetherBrick, lavaCactus, lavaInfuser, lavaInfuserInfusing,
            blockLavaInfusedObsidian, blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal, blockMeltingObsidian
        );
        // ==== DUNGEON BLOCKS ==== \\
        registerAllItemBlocks(event, stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        registerItemBlock(event, enderBlocks);
        registerAllItemBlocks(event, trophies);
        // ==== ITEMS ==== \\
        registerAllItems(event,
            bookInfo, steelIngot, electricalIngot, itemRedstoneApple, itemLavaCrystal, itemTGOTG, itemDevTool, theUltimateParts,
            itemCoalArrow, itemLapisArrow, itemRedstoneArrow, itemLavaArrow, itemEnderDragonArrow
        );
        registerAllItems(event, materials);
        //  registerAllItems(event, chainmail, guardianScale, witherBone, enderDragonScale, theUltimateMaterial);
        // ==== SPECIAL ITEMS ===\\
        registerAllItems(event, towerSpawnItem, enderDungeonFloor1SpawnItem);
        registerItemFixes();
        // ==== COSMETICS ==== \\
        registerAllItems(event, twitchItem, beamItem, theDragonTeamItem, moddedCityItem, jonBamsItem, btmMoon, m1Jordan, teamRapture);
        // ==== GEAR ==== \\
        registerAllItems(event,
            coal, lapis, redstone, emerald, obsidian, lava, chicken, slime, guardian, superStar, enderDragon, theUltimate, ardite, cobalt, manyullyn, pigIron, knightSlime
        );
        if (enableExperimentalMode) {
            registerAllItems(event,
                coalExp, lapisExp, redstoneExp, emeraldExp, obsidianExp, lavaExp,
                chickenExp, slimeExp, guardianExp, superStarExp, enderDragonExp, ultimateExp,
                arditeExp, cobaltExp, manyullynExp, pigIronExp, knightSlimeExp
            );
        }
        registerAllItems(event, horseArmors);
        registerAllItems(event, sword, battleAxe, bow);
        //   if (enableExperimentalMode) {
        //       registerAllItems(event, prototype);
        //   }
    }

    public static void registerItemFixes() {
        ModFixs modFixs = new CompoundDataFixer(FMLCommonHandler.instance().getDataFixer()).init(ArmorPlus.MODID, DATA_FIXER_VERSION);
        modFixs.registerFix(FixTypes.ITEM_INSTANCE, new ItemRename());
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
