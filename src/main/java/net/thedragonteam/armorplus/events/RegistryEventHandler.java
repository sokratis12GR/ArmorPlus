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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.enchantments.FuriousEnchantment;
import net.thedragonteam.armorplus.enchantments.LifeStealEnchantment;
import net.thedragonteam.armorplus.entity.dungeon.guardian.EntityGuardianOverlord;
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.EntityFreezeBomb;
import net.thedragonteam.armorplus.entity.dungeon.wither.EntitySkeletalKing;
import net.thedragonteam.armorplus.entity.dungeon.wither.projectile.EntityWitherMinion;
import net.thedragonteam.armorplus.entity.entityarrow.*;
import net.thedragonteam.armorplus.entity.entitygolem.EntityIceGolem;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;
import net.thedragonteam.armorplus.potions.PotionEmpty;
import net.thedragonteam.armorplus.sounds.SoundTrapTriggered;
import net.thedragonteam.armorplus.tileentity.TileEntityLavaInfuser;

import static net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity;
import static net.thedragonteam.armorplus.ArmorPlus.instance;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.Utils.setRL;

public class RegistryEventHandler {

    //private void registerEntities(Register<EntityEntry> event, Class<? extends Entity> entityClass, String registryName, boolean hasEgg, int primaryColor, int secondaryColor) {
    //    EntityEntry entityEntry = new EntityEntry(entityClass, registryName);
    //    ResourceLocation resourceLocation = setRL(registryName);
    //    entityEntry.setRegistryName(resourceLocation);
    //    if (hasEgg) entityEntry.setEgg(new EntityList.EntityEggInfo(resourceLocation, primaryColor, secondaryColor));
    //    event.getRegistry().register(entityEntry);
    //}

    private void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, boolean hasEgg, int primaryColor, int secondaryColor) {
        ResourceLocation resourceLocation = setRL(registryName);
        if (hasEgg) {
            registerModEntity(resourceLocation, entityClass, registryName, id, instance, trackingRange, updateFrequency, sendVelocityUpdates, primaryColor, secondaryColor);
        } else {
            registerModEntity(resourceLocation, entityClass, registryName, id, instance, trackingRange, updateFrequency, sendVelocityUpdates);
        }
    }

    private void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, int primaryColor, int secondaryColor) {
        this.registerEntities(entityClass, registryName, id, trackingRange, updateFrequency, sendVelocityUpdates, true, primaryColor, secondaryColor);
    }

    private void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int trackingRange, int primaryColor, int secondaryColor) {
        this.registerEntities(entityClass, registryName, id, trackingRange, 1, true, true, primaryColor, secondaryColor);
    }

    private void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates) {
        this.registerEntities(entityClass, registryName, id, trackingRange, updateFrequency, sendVelocityUpdates, false, 0, 0);
    }

    private void registerEntities(Class<? extends Entity> entityClass, String registryName, int id, int updateFrequency, boolean sendVelocityUpdates) {
        this.registerEntities(entityClass, registryName, id, 64, updateFrequency, sendVelocityUpdates, false, 0, 0);
    }

    private void registerEntities(Class<? extends Entity> entityClass, String registryName, int id) {
        this.registerEntities(entityClass, registryName, id, 64, 1, true, false, 0, 0);
    }

    @SubscribeEvent
    public void registerEntities(Register<EntityEntry> event) {
        //  // Mobs
        //  registerEntities(event, EntityEnderDragonZombie.class, "ender_dragon_zombie", 0x721164, 0x00ff00);
        //  registerEntities(event, EntityIceGolem.class, "ice_golem", 0xffffff, 0x00ff00);
        //  // Arrows
        //  registerEntities(event, EntityCoalArrow.class, "coal_arrow");
        //  registerEntities(event, EntityLapisArrow.class, "lapis_arrow");
        //  registerEntities(event, EntityRedstoneArrow.class, "redstone_arrow");
        //  registerEntities(event, EntityLavaArrow.class, "lava_arrow");
        //  registerEntities(event, EntityEnderDragonArrow.class, "ender_dragon_arrow");
        //  // Bosses
        //  registerEntities(event, EntityGuardianOverlord.class, "overlord_of_the_guardians", 0x7ae4ff, 0x79a6ff);
        //  registerEntities(event, EntitySkeletalKing.class, "skeletal_king", 0x665b52, 0x845833);
        //  // // Boss Projectiles
        //  registerEntities(event, EntityFreezeBomb.class, "freeze_bomb");
        //  registerEntities(event, EntityWitherMinion.class, "wither_minion");

        // Projectiles
        // Arrows
        this.registerEntities(EntityCoalArrow.class, "coal_arrow", 0);
        this.registerEntities(EntityLapisArrow.class, "lapis_arrow", 1);
        this.registerEntities(EntityRedstoneArrow.class, "redstone_arrow", 3);
        this.registerEntities(EntityLavaArrow.class, "lava_arrow", 4);
        this.registerEntities(EntityEnderDragonArrow.class, "ender_dragon_arrow", 5);
        // Abilities
        this.registerEntities(EntityFreezeBomb.class, "freeze_bomb", 6);
        this.registerEntities(EntityWitherMinion.class, "wither_minion", 7);
        // Mobs
        this.registerEntities(EntityEnderDragonZombie.class, "ender_dragon_zombie", 20, 64,
                0x721164, 0x00ff00);
        this.registerEntities(EntityIceGolem.class, "ice_golem", 21, 64,
                0xffffff, 0x00ff00);
        // Bosses
        this.registerEntities(EntityGuardianOverlord.class, "overlord_of_the_guardians", 100, 64,
                0x7ae4ff, 0x79a6ff);
        this.registerEntities(EntitySkeletalKing.class, "skeletal_king", 101, 64,
                0x665b52, 0x845833);
        this.registerEntityFixes();
    }

    public void registerEntityFixes() {
        EntityGuardianOverlord.registerFixesElderGuardian(DataFixesManager.createFixer());
        EntityFreezeBomb.registerFixesFreezeBomb(DataFixesManager.createFixer());
        EntitySkeletalKing.registerFixesSkeletalKing(DataFixesManager.createFixer());
        EntityWitherMinion.registerFixesWitherMinion(DataFixesManager.createFixer());
    }

    @SubscribeEvent
    public void registerBlocks(Register<Block> event) {
        event.getRegistry().registerAll(
                oreLavaCrystal, compressedObsidian, steelBlock, electricalBlock, lavaNetherBrick, lavaCactus, lava_infuser, lava_infuser_infusing,
                blockLavaInfusedObsidian, blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal
        );
        event.getRegistry().registerAll(stoneBricks);
        event.getRegistry().registerAll(stoneBrickTowers);
        event.getRegistry().registerAll(stoneBrickCorners);
        event.getRegistry().registerAll(stonebrickWalls);
        event.getRegistry().registerAll(enderBlocks);
        this.registerTileEntities();
        this.registerTEFixes();
    }

    public void registerTEFixes() {
        TileEntityLavaInfuser.registerFixesFurnace(DataFixesManager.createFixer());
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityLavaInfuser.class, "LavaInfuserTileEntity");
    }

    private void registerItemBlock(Register<Item> event, Block... blocks) {
        for (Block block : blocks) {
            ItemBlock itemBlock = new ItemBlock(block);
            itemBlock.setRegistryName(block.getRegistryName());
            event.getRegistry().register(itemBlock);
        }
    }

    @SubscribeEvent
    public void registerItems(Register<Item> event) {
        // ==== BLOCKS ==== \\
        registerItemBlock(event,
                oreLavaCrystal, compressedObsidian, steelBlock, electricalBlock, lavaNetherBrick, lavaCactus, lava_infuser, lava_infuser_infusing,
                blockLavaInfusedObsidian, blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal
        );
        registerItemBlock(event, stoneBricks);
        registerItemBlock(event, stoneBrickTowers);
        registerItemBlock(event, stoneBrickCorners);
        registerItemBlock(event, stonebrickWalls);
        registerItemBlock(event, enderBlocks);
        // ==== ITEMS ==== \\
        event.getRegistry().registerAll(
                bookInfo, materials, steelIngot, electricalIngot, redstoneApple, lavaCrystal, theGiftOfTheGods,
                twitchItem, beamItem, theDragonTeamItem, moddedCityItem, jonBamsItem, devTool, theUltimateParts,
                itemCoalArrow, itemLapisArrow, itemRedstoneArrow, itemLavaArrow, itemEnderDragonArrow
        );
        //Armors
        event.getRegistry().registerAll(coal);
        event.getRegistry().registerAll(emerald);
        event.getRegistry().registerAll(lapis);
        event.getRegistry().registerAll(lava);
        event.getRegistry().registerAll(obsidian);
        event.getRegistry().registerAll(redstone);
        event.getRegistry().registerAll(chicken);
        event.getRegistry().registerAll(slime);
        event.getRegistry().registerAll(enderDragon);
        event.getRegistry().registerAll(guardian);
        event.getRegistry().registerAll(superStar);
        event.getRegistry().registerAll(ardite);
        event.getRegistry().registerAll(cobalt);
        event.getRegistry().registerAll(manyullyn);
        event.getRegistry().registerAll(pigIron);
        event.getRegistry().registerAll(knightSlime);
        event.getRegistry().registerAll(theUltimate);
        //Swords
        event.getRegistry().registerAll(sword);
        //BattleAxes
        event.getRegistry().registerAll(battleAxe);
        //Bows
        event.getRegistry().registerAll(bow);
        // Arrays.setAll(templates, i -> (new BaseItem(templateNames[i])));
    }

    @SubscribeEvent
    public void registerEnchantments(Register<Enchantment> event) {
        event.getRegistry().registerAll(new FuriousEnchantment(), new LifeStealEnchantment());
    }

    @SubscribeEvent
    public void registerPotions(Register<Potion> event) {
        event.getRegistry().registerAll(new PotionEmpty());
    }

    @SubscribeEvent
    public void registerSounds(Register<SoundEvent> event) {
        event.getRegistry().register(new SoundTrapTriggered());
    }
}
