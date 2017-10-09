package net.thedragonteam.armorplus.events;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.enchantments.FuriousEnchantment;
import net.thedragonteam.armorplus.enchantments.LifeStealEnchantment;
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.EntityFreezeBomb;
import net.thedragonteam.armorplus.entity.dungeon.wither.projectile.EntityWitherMinion;
import net.thedragonteam.armorplus.entity.entityarrow.*;
import net.thedragonteam.armorplus.entity.entitygolem.EntityIceGolem;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;
import net.thedragonteam.armorplus.potions.PotionEmpty;
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser;
import net.thedragonteam.armorplus.util.Utils;

import java.util.Arrays;

import static net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity;
import static net.thedragonteam.armorplus.ArmorPlus.instance;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.Utils.areNotNull;
import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
@EventBusSubscriber(modid = ArmorPlus.MODID)
public class RegistryEventHandler {

    @SuppressWarnings("SameParameterValue")
    private static void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, boolean hasEgg, int primaryColor, int secondaryColor) {
        ResourceLocation resourceLocation = setRL(registryName);
        if (hasEgg) {
            registerModEntity(resourceLocation, entityClass, registryName, id, instance, trackingRange, updateFrequency, sendVelocityUpdates, primaryColor, secondaryColor);
        } else {
            registerModEntity(resourceLocation, entityClass, registryName, id, instance, trackingRange, updateFrequency, sendVelocityUpdates);
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
        // Projectiles
        // Arrows
        registerEntities(EntityCoalArrow.class, "coal_arrow", 0);
        registerEntities(EntityLapisArrow.class, "lapis_arrow", 1);
        registerEntities(EntityRedstoneArrow.class, "redstone_arrow", 3);
        registerEntities(EntityLavaArrow.class, "lava_arrow", 4);
        registerEntities(EntityEnderDragonArrow.class, "ender_dragon_arrow", 5);
        // Abilities
        registerEntities(EntityFreezeBomb.class, "freeze_bomb", 6);
        registerEntities(EntityWitherMinion.class, "wither_minion", 7);
        // Mobs
        registerEntities(EntityEnderDragonZombie.class, "ender_dragon_zombie", 20,
            0x721164, 0x00ff00);
        registerEntities(EntityIceGolem.class, "ice_golem", 21,
            0xffffff, 0x00ff00);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        // Bosses
        //  registerEntities(EntityGuardianOverlord.class, "overlord_of_the_guardians", 100,
        //  0x7ae4ff, 0x79a6ff);
        //  registerEntities(EntitySkeletalKing.class, "skeletal_king", 101,
        //  0x665b52, 0x845833);
    }

    private static void registerAllBlocks(Register<Block> event, Block[]... blocksArray) {
        Arrays.stream(blocksArray).forEachOrdered(blockList -> registerAllBlocks(event, blockList));
    }

    private static void registerAllBlocks(Register<Block> event, Block... blockList) {
        Arrays.stream(blockList).filter(Utils::isNotNull).forEachOrdered(block -> event.getRegistry().register(block));
    }

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        registerAllBlocks(event,
            oreLavaCrystal, compressedObsidian, steelBlock, electricalBlock, lavaNetherBrick, lavaCactus, lavaInfuser, lavaInfuserInfusing,
            blockLavaInfusedObsidian, blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal
        );
        registerAllBlocks(event, stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        //  registerAllBlocks(event, enderBlocks);
        registerTileEntities();
        registerTEFixes();
    }

    private static void registerTEFixes() {
        TileEntityLavaInfuser.registerFixesFurnace(DataFixesManager.createFixer());
    }

    private static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityLavaInfuser.class, "LavaInfuserTileEntity");
    }

    private static void registerItemBlock(Register<Item> event, Block... blocks) {
        Arrays.stream(blocks).forEachOrdered(block -> {
            if (areNotNull(block, block.getRegistryName())) {
                ItemBlock itemBlock = new ItemBlock(block);
                itemBlock.setRegistryName(block.getRegistryName());
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
        registerItemBlock(event,
            oreLavaCrystal, compressedObsidian, steelBlock, electricalBlock, lavaNetherBrick, lavaCactus, lavaInfuser, lavaInfuserInfusing,
            blockLavaInfusedObsidian, blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal
        );
        // ==== DUNGEON BLOCKS ==== \\
        registerAllItemBlocks(event, stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        //  registerAllItemBlocks(event, enderBlocks);
        // ==== ITEMS ==== \\
        registerAllItems(event,
            bookInfo, materials, steelIngot, electricalIngot, redstoneApple, lavaCrystal, theGiftOfTheGods, devTool, theUltimateParts,
            itemCoalArrow, itemLapisArrow, itemRedstoneArrow, itemLavaArrow, itemEnderDragonArrow
        );
        // ==== COSMETICS ==== \\
        registerAllItems(event, twitchItem, beamItem, theDragonTeamItem, moddedCityItem, jonBamsItem);
        // ==== GEAR ==== \\
        registerAllItems(event,
            coal, emerald, lapis, lava, obsidian, redstone, chicken, slime, guardian, superStar, enderDragon, theUltimate, ardite, cobalt, manyullyn, pigIron, knightSlime
        );
        registerAllItems(event,
            sword, battleAxe, bow
        );
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
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        //  event.getRegistry().register(new SoundTrapTriggered());
    }
}
