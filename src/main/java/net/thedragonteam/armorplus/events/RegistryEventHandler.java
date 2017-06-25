package net.thedragonteam.armorplus.events;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
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

import static net.thedragonteam.armorplus.proxy.CommonProxy.registerTileEntities;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.Utils.setRL;

public class RegistryEventHandler {

    private void registerEntities(Register<EntityEntry> event, Class<? extends Entity> entityClass, String registryName, boolean hasEgg, int primaryColor, int secondaryColor) {
        EntityEntry entityEntry = new EntityEntry(entityClass, registryName);
        ResourceLocation resourceLocation = setRL(registryName);
        entityEntry.setRegistryName(resourceLocation);
        if (hasEgg) entityEntry.setEgg(new EntityEggInfo(resourceLocation, primaryColor, secondaryColor));
        event.getRegistry().register(entityEntry);
    }

    private void registerEntities(Register<EntityEntry> event, Class<? extends Entity> entityClass, String registryName, int primaryColor, int secondaryColor) {
        this.registerEntities(event, entityClass, registryName, true, primaryColor, secondaryColor);
    }

    private void registerEntities(Register<EntityEntry> event, Class<? extends Entity> entityClass, String registryName) {
        this.registerEntities(event, entityClass, registryName, false, 0, 0);
    }

    @SubscribeEvent
    public void registerEntities(Register<EntityEntry> event) {
        // Mobs
        registerEntities(event, EntityEnderDragonZombie.class, "ender_dragon_zombie", 0x721164, 0x00ff00);
        registerEntities(event, EntityIceGolem.class, "ice_golem", 0xffffff, 0x00ff00);
        // Arrows
        registerEntities(event, EntityCoalArrow.class, "coal_arrow");
        registerEntities(event, EntityLapisArrow.class, "lapis_arrow");
        registerEntities(event, EntityRedstoneArrow.class, "redstone_arrow");
        registerEntities(event, EntityLavaArrow.class, "lava_arrow");
        registerEntities(event, EntityEnderDragonArrow.class, "ender_dragon_arrow");
        // Bosses
        registerEntities(event, EntityGuardianOverlord.class, "overlord_of_the_guardians", 0x7ae4ff, 0x79a6ff);
        registerEntities(event, EntitySkeletalKing.class, "skeletal_king", 0x665b52, 0x845833);
        // registerAPEntity("overlord_of_the_guardians", EntityGuardianOverlord.class, OVERLORD_OF_THE_GUARDIANS, 80, 0x7ae4ff, 0x79a6ff);
        // registerAPEntity("skeletal_king", EntitySkeletalKing.class, SKELETAL_KING, 80, 0x665b52, 0x845833);
        // // Boss Projectiles
        registerEntities(event, EntityFreezeBomb.class, "freeze_bomb");
        registerEntities(event, EntityWitherMinion.class, "wither_minion");
        // registerAPEntity("freeze_bomb", EntityFreezeBomb.class, FREEZE_BOMB);
        // registerAPEntity("wither_minion", EntityWitherMinion.class, WITHER_MINION);
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
        registerTileEntities();
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
