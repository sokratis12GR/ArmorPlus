/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
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
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.sofodev.armorplus.config.ModConfig.Experimental.enableExperimentalMode;
import static com.sofodev.armorplus.config.ModConfig.RegistryConfig.global_registry;
import static com.sofodev.armorplus.registry.ModBlocks.*;
import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.registry.ModModelUtils.register;
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
            blockCrystalOre, blockCompressedObsidian, steelBlock, electricalBlock, lavaInfuser, lavaInfuserInfusing, blockLavaCactus, blockLavaNetherBrick
        );
        register(stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        //   register(blockBTMMoon);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        register(enderBlocks);
        register(trophies);
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        register(twitchItem, beamItem, theDragonTeamItem, moddedCityItem, jonBamsItem, btmMoon, m1Jordan, teamRapture);
        register(materials, itemLavaCrystal, itemTGOTG, bookInfo, steelIngot, electricalIngot, itemRedstoneApple, theUltimateParts,
            itemDevTool, itemCoalArrow, itemLapisArrow, itemRedstoneArrow, itemLavaArrow, itemEnderDragonArrow
        );
        register(global_registry.enableTheUltimateArmor, theUltimate);
        register(isArmorEnabled,
            coal, emerald, lapis, lava, obsidian, redstone, chicken, slime, guardian, superStar, enderDragon, ardite, cobalt, manyullyn, pigIron, knightSlime
        );
        register(towerSpawnItem, enderDungeonFloor1SpawnItem);
        register(chainmail, guardianScale, witherBone, enderDragonScale, theUltimateMaterial);
        register(horseArmors);
        register(isSwordEnabled, sword);
        register(isBattleAxeEnabled, battleAxe);
        register(isBowEnabled, bow);

        //Prototype
        if (enableExperimentalMode) {
            register(
                coalExp, emeraldExp, lapisExp, lavaExp, obsidianExp, redstoneExp, chickenExp, slimeExp, guardianExp, superStarExp, enderDragonExp,
                arditeExp, cobaltExp, manyullynExp, pigIronExp, knightSlimeExp, ultimateExp
            );
        }
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
        registerRenderingHandler(EntityLavaArrow.class, "lava");
        registerRenderingHandler(EntityEnderDragonArrow.class, "ender_dragon");
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
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
