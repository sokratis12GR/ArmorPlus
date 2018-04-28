package net.thedragonteam.armorplus.events;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.dungeon.guardianoverlord.EntityGuardianOverlord;
import net.thedragonteam.armorplus.entity.dungeon.guardianoverlord.RenderGuardianOverlord;
import net.thedragonteam.armorplus.entity.dungeon.guardianoverlord.projectile.EntityFreezeBomb;
import net.thedragonteam.armorplus.entity.dungeon.guardianoverlord.projectile.RenderFreezeBomb;
import net.thedragonteam.armorplus.entity.dungeon.skeletalking.EntitySkeletalKing;
import net.thedragonteam.armorplus.entity.dungeon.skeletalking.RenderSkeletalKing;
import net.thedragonteam.armorplus.entity.dungeon.skeletalking.projectile.EntityWitherMinion;
import net.thedragonteam.armorplus.entity.dungeon.skeletalking.projectile.RenderWitherMinion;
import net.thedragonteam.armorplus.entity.entityarrow.*;
import net.thedragonteam.armorplus.entity.mobs.EntityIceGolem;
import net.thedragonteam.armorplus.entity.render.RenderIceGolem;
import net.thedragonteam.armorplus.entity.mobs.EntityEnderDragonZombie;
import net.thedragonteam.armorplus.entity.render.RenderEnderDragonZombie;
import net.thedragonteam.armorplus.entity.render.RenderModdedArrow;

import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.global_registry;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.registry.ModModelUtils.registerModels;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
@EventBusSubscriber(value = Side.CLIENT, modid = ArmorPlus.MODID)
@SideOnly(Side.CLIENT)
public class ModelsEventHandler {

    @SubscribeEvent
    public static void registerBlockModels(ModelRegistryEvent event) {
        registerModels(benches);
        registerModels(blockLavaCrystal, blockInfusedLavaCrystal, blockCompressedLavaCrystal, blockCompressedInfusedLavaCrystal, blockLavaInfusedObsidian,
            oreLavaCrystal, compressedObsidian, steelBlock, electricalBlock, lavaInfuser, lavaInfuserInfusing, lavaCactus, lavaNetherBrick
        );
        registerModels(stoneBricks, stoneBrickTowers, stoneBrickCorners, stonebrickWalls);
        //   registerModels(blockBTMMoon);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        registerModels(enderBlocks);
        registerModels(trophies);
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        registerModels(twitchItem, beamItem, theDragonTeamItem, moddedCityItem, jonBamsItem, btmMoon, m1Jordan, teamRapture);
        registerModels(materials, lavaCrystal, theGiftOfTheGods, bookInfo, steelIngot, electricalIngot, redstoneApple, theUltimateParts,
            devTool, itemCoalArrow, itemLapisArrow, itemRedstoneArrow, itemLavaArrow, itemEnderDragonArrow
        );
        registerModels(global_registry.enableTheUltimateArmor, theUltimate);
        registerModels(isArmorEnabled,
            coal, emerald, lapis, lava, obsidian, redstone, chicken, slime, guardian, superStar, enderDragon, ardite, cobalt, manyullyn, pigIron, knightSlime
        );
        registerModels(towerSpawnItem);
        registerModels(chainmail, guardianScale, witherBone, enderDragonScale, theUltimateMaterial);
        registerModels(horseArmors);
        registerModels(isSwordEnabled, sword);
        registerModels(isBattleAxeEnabled, battleAxe);
        registerModels(isBowEnabled, bow);
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
