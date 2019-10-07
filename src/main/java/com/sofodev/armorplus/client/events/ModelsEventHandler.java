/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.client.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.client.entities.renders.*;
import com.sofodev.armorplus.common.registry.entities.entityarrow.*;
import com.sofodev.armorplus.common.registry.entities.mobs.EntityEnderDragonZombie;
import com.sofodev.armorplus.common.registry.entities.mobs.EntityIceGolem;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.demonicdragon.EntityDemonicDragon;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.guardianoverlord.EntityGuardianOverlord;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.EntitySkeletalKing;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.projectile.EntityWitherMinion;
import com.sofodev.armorplus.common.registry.entities.mobs.dungeon.skeletalking.projectile.EntityWitherling;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.client.utils.ModModelUtils.register;
import static com.sofodev.armorplus.common.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.common.registry.ModBlocks.*;
import static com.sofodev.armorplus.common.registry.ModItems.*;
import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;

/**
 * @author Sokratis Fotkatzikis
 **/
@EventBusSubscriber(value = Side.CLIENT, modid = ArmorPlus.MODID)
@SideOnly(Side.CLIENT)
public class ModelsEventHandler {

    @SubscribeEvent
    public static void registerBlockModels(ModelRegistryEvent event) {
        register(benches);
        register(blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal, blockLavaInfusedObsidian,
            oreLavaCrystal, blockCompressedObsidian, steelBlock, electricalBlock, lavaInfuser, lavaInfuserInfusing, lavaCactus, blockLavaNetherBrick
        );
        register(stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        //   register(blockBTMMoon);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        register(enderBlocks);
        register(trophies, blockSwordDisplays);
        register(blockEmptyDisplay);
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        register(twitchItem, beamItem, theDragonTeamItem, moddedCityItem, jonBamsItem, btmMoon, m1Jordan, teamRapture);
        register(materials, itemLavaCrystal, itemTGOTG, bookInfo, bookLore, steelIngot, electricalIngot, itemRedstoneApple, theUltimateParts, itemDevTool,
            itemCoalArrow, itemLapisArrow, itemRedstoneArrow, itemEmeraldArrow, itemObsidianArrow, itemLavaArrow, itemGuardianArrow, itemSuperStarArrow, itemEnderDragonArrow
        );
        register(theUltimate);
        register(coal, emerald, lapis, lava, obsidian, redstone, chicken, slime, guardian, superStar, enderDragon, ardite, cobalt, manyullyn, pigIron, knightSlime);
        register(towerSpawnItem, enderDungeonFloor1SpawnItem);
        register(chainmail, guardianScale, witherBone, enderDragonScale, theUltimateMaterial);
        register(horseArmors);
        register(sword, battleAxe, bow);

        //Prototype
        if (enableExperimentalMode) {
            register(
                coalExp, emeraldExp, lapisExp, lavaExp, obsidianExp, redstoneExp, chickenExp, slimeExp, guardianExp, superStarExp, enderDragonExp,
                arditeExp, cobaltExp, manyullynExp, pigIronExp, knightSlimeExp, ultimateExp
            );
        }
        register(chain, iron, gold, diamond);
        register(pickaxe, fragments, maps);
    }

    @SubscribeEvent
    public static void registerEntityModels(ModelRegistryEvent event) {
        //Mobs
        registerEntityRenderingHandler(EntityEnderDragonZombie.class, RenderEnderDragonZombie.FACTORY);
        registerEntityRenderingHandler(EntityIceGolem.class, RenderIceGolem::new);
        //Arrows
        registerRenderingHandler(EntityCoalArrow.class, "coal");
        registerRenderingHandler(EntityLapisArrow.class, "lapis");
        registerRenderingHandler(EntityRedstoneArrow.class, "redstone");
        registerRenderingHandler(EntityEmeraldArrow.class, "emerald");
        registerRenderingHandler(EntityObsidianArrow.class, "obsidian");
        registerRenderingHandler(EntityInfusedLavaArrow.class, "lava");
        registerRenderingHandler(EntityGuardianArrow.class, "guardian");
        registerRenderingHandler(EntitySuperStarArrow.class, "super_star");
        registerRenderingHandler(EntityEnderDragonArrow.class, "ender_dragon");
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        //Bosses
        registerEntityRenderingHandler(EntityGuardianOverlord.class, RenderGuardianOverlord::new);
        registerEntityRenderingHandler(EntitySkeletalKing.class, RenderSkeletalKing::new);
        registerEntityRenderingHandler(EntityDemonicDragon.class, RenderDemonicDragon::new);
        //Mini-bosses
        registerEntityRenderingHandler(EntityWitherling.class, RenderWitherling::new);

        //Boss Projectiles
        registerEntityRenderingHandler(EntityFreezeBomb.class, RenderFreezeBomb::new);
        registerEntityRenderingHandler(EntityWitherMinion.class, RenderWitherMinion::new);
    }

    private static void registerRenderingHandler(Class<? extends EntityArrow> entityClass, String name) {
        registerEntityRenderingHandler(entityClass, rm -> new RenderModdedArrow<>(rm, name));
    }
}
