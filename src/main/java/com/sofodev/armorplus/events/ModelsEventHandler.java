/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.events;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.config.ModConfig;
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
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.ModModelUtils;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        register(ModBlocks.benches);
        register(ModBlocks.blockLavaCrystal, ModBlocks.blockInfusedLavaCrystal, ModBlocks.blockCompressedLavaCrystal, ModBlocks.blockCompressedInfusedLavaCrystal, ModBlocks.blockLavaInfusedObsidian,
            ModBlocks.blockCrystalOre, ModBlocks.blockCompressedObsidian, ModBlocks.steelBlock, ModBlocks.electricalBlock, ModBlocks.lavaInfuser, ModBlocks.lavaInfuserInfusing, ModBlocks.blockLavaCactus, ModBlocks.blockLavaNetherBrick
        );
        register(ModBlocks.stoneBricks, ModBlocks.stoneBrickTowers, ModBlocks.stoneBrickCorners, ModBlocks.stonebrickWalls);
        //   register(blockBTMMoon);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        register(ModBlocks.enderBlocks);
        register(ModBlocks.trophies);
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        ModModelUtils.register(ModItems.twitchItem, ModItems.beamItem, ModItems.theDragonTeamItem, ModItems.moddedCityItem, ModItems.jonBamsItem, ModItems.btmMoon, ModItems.m1Jordan, ModItems.teamRapture);
        register(ModItems.materials, ModItems.itemLavaCrystal, ModItems.itemTGOTG, ModItems.bookInfo, ModItems.steelIngot, ModItems.electricalIngot, ModItems.itemRedstoneApple, ModItems.theUltimateParts,
            ModItems.itemDevTool, ModItems.itemCoalArrow, ModItems.itemLapisArrow, ModItems.itemRedstoneArrow, ModItems.itemLavaArrow, ModItems.itemEnderDragonArrow
        );
        ModModelUtils.register(ModConfig.RegistryConfig.global_registry.enableTheUltimateArmor, ModItems.theUltimate);
        ModModelUtils.register(ModItems.isArmorEnabled,
            ModItems.coal, ModItems.emerald, ModItems.lapis, ModItems.lava, ModItems.obsidian, ModItems.redstone, ModItems.chicken, ModItems.slime, ModItems.guardian, ModItems.superStar, ModItems.enderDragon, ModItems.ardite, ModItems.cobalt, ModItems.manyullyn, ModItems.pigIron, ModItems.knightSlime
        );
        register(ModItems.towerSpawnItem);
        register(ModItems.chainmail, ModItems.guardianScale, ModItems.witherBone, ModItems.enderDragonScale, ModItems.theUltimateMaterial);
        register(ModItems.horseArmors);
        ModModelUtils.register(ModItems.isSwordEnabled, ModItems.sword);
        ModModelUtils.register(ModItems.isBattleAxeEnabled, ModItems.battleAxe);
        ModModelUtils.register(ModItems.isBowEnabled, ModItems.bow);
    }

    @SubscribeEvent
    public static void registerEntityModels(ModelRegistryEvent event) {
        //Mobs
        registerEntityRenderingHandler(EntityEnderDragonZombie.class, RenderEnderDragonZombie::new);
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
