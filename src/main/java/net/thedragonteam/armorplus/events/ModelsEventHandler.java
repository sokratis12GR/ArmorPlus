package net.thedragonteam.armorplus.events;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.dungeon.wither.EntitySkeletalKing;
import net.thedragonteam.armorplus.entity.dungeon.wither.RenderSkeletalKing;
import net.thedragonteam.armorplus.entity.dungeon.wither.projectile.EntityWitherMinion;
import net.thedragonteam.armorplus.entity.dungeon.wither.projectile.RenderWitherMinion;
import net.thedragonteam.armorplus.entity.entityarrow.*;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;
import net.thedragonteam.armorplus.entity.render.*;

import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;
import static net.thedragonteam.armorplus.APConfig.enableTheUltimateArmor;
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
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        //  registerModels(enderBlocks);
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        registerModels(twitchItem, beamItem, theDragonTeamItem, moddedCityItem, jonBamsItem,
                materials, lavaCrystal, theGiftOfTheGods, bookInfo, steelIngot, electricalIngot, redstoneApple, theUltimateParts,
                devTool, itemCoalArrow, itemLapisArrow, itemRedstoneArrow, itemLavaArrow, itemEnderDragonArrow
        );
        registerModels(enableTheUltimateArmor, theUltimate);
        registerModels(isArmorEnabled,
                coal, emerald, lapis, lava, obsidian, redstone, chicken, slime, guardian, superStar, enderDragon, ardite, cobalt, manyullyn, pigIron, knightSlime
        );
        registerModels(isSwordEnabled, sword);
        registerModels(isBattleAxeEnabled, battleAxe);
        registerModels(isBowEnabled, bow);
    }

    @SubscribeEvent
    public static void registerEntityModels(ModelRegistryEvent event) {
        //Mobs
        registerEntityRenderingHandler(EntityEnderDragonZombie.class, RenderEnderDragonZombie::new);
        //Arrows
        registerEntityRenderingHandler(EntityCoalArrow.class, RenderCoalArrow::new);
        registerEntityRenderingHandler(EntityLapisArrow.class, RenderLapisArrow::new);
        registerEntityRenderingHandler(EntityRedstoneArrow.class, RenderRedstoneArrow::new);
        registerEntityRenderingHandler(EntityLavaArrow.class, RenderLavaArrow::new);
        registerEntityRenderingHandler(EntityEnderDragonArrow.class, RenderEnderDragonArrow::new);
        //TODO: Finish the Dungeons: Blocks, Bosses, Abilities, Mechanics
        //Bosses
        //  registerEntityRenderingHandler(EntityGuardianOverlord.class, RenderGuardianOverlord::new);
          registerEntityRenderingHandler(EntitySkeletalKing.class, RenderSkeletalKing::new);
        //Boss Projectiles
        //  registerEntityRenderingHandler(EntityFreezeBomb.class, RenderFreezeBomb::new);
          registerEntityRenderingHandler(EntityWitherMinion.class, RenderWitherMinion::new);
    }
}
