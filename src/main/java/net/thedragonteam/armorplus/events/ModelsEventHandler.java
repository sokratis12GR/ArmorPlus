package net.thedragonteam.armorplus.events;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickCorner;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickTower;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStonebrickWall;
import net.thedragonteam.armorplus.entity.dungeon.guardian.EntityGuardianOverlord;
import net.thedragonteam.armorplus.entity.dungeon.guardian.RenderGuardianOverlord;
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.EntityFreezeBomb;
import net.thedragonteam.armorplus.entity.dungeon.guardian.projectile.RenderFreezeBomb;
import net.thedragonteam.armorplus.entity.dungeon.wither.EntitySkeletalKing;
import net.thedragonteam.armorplus.entity.dungeon.wither.RenderSkeletalKing;
import net.thedragonteam.armorplus.entity.dungeon.wither.projectile.EntityWitherMinion;
import net.thedragonteam.armorplus.entity.dungeon.wither.projectile.RenderWitherMinion;
import net.thedragonteam.armorplus.entity.entityarrow.*;
import net.thedragonteam.armorplus.entity.entityzombie.EntityEnderDragonZombie;
import net.thedragonteam.armorplus.entity.render.*;

import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.registry.ModRegistryUtils.*;

@SideOnly(Side.CLIENT)
public class ModelsEventHandler {

    @SubscribeEvent
    public void registerBlockModels(ModelRegistryEvent event) {
        lava_infuser.initModel();
        lava_infuser_infusing.initModel();
        blockLavaInfusedObsidian.initModel();
        oreLavaCrystal.initModel();
        compressedObsidian.initModel();
        steelBlock.initModel();
        electricalBlock.initModel();
        lavaCactus.initModel();
        lavaNetherBrick.initModel();
        for (BlockStoneBrick stoneBrick : stoneBricks) stoneBrick.initModel();
        for (BlockStoneBrickTower stoneBrickTower : stoneBrickTowers) stoneBrickTower.initModel();
        for (BlockStoneBrickCorner stoneBrickCorner : stoneBrickCorners) stoneBrickCorner.initModel();
        for (BlockStonebrickWall stonebrickWall : stonebrickWalls) stonebrickWall.initModel();
        //       ritualAltar.initModel();
        blockLavaCrystal.initModel();
        blockInfusedLavaCrystal.initModel();
        blockCompressedLavaCrystal.initModel();
        blockCompressedInfusedLavaCrystal.initModel();
        //    for (BlockDungeonEnder enderBlock : enderBlocks) enderBlock.initModel();
    }

    @SubscribeEvent
    public void registerItemModels(ModelRegistryEvent event) {
        twitchItem.initModel();
        beamItem.initModel();
        theDragonTeamItem.initModel();
        moddedCityItem.initModel();
        jonBamsItem.initModel();
        materials.initModel();
        lavaCrystal.initModel();
        theGiftOfTheGods.initModel();
        bookInfo.initModel();
        steelIngot.initModel();
        electricalIngot.initModel();
        redstoneApple.initModel();
        theUltimateParts.initModel();
        registerArmorModel(enableCoalArmor, coal);
        registerArmorModel(enableEmeraldArmor, emerald);
        registerArmorModel(enableLapisArmor, lapis);
        registerArmorModel(enableLavaArmor, lava);
        registerArmorModel(enableObsidianArmor, obsidian);
        registerArmorModel(enableRedstoneArmor, redstone);
        registerArmorModel(enableChickenArmor, chicken);
        registerArmorModel(enableSlimeArmor, slime);
        registerArmorModel(enableEnderDragonArmor, enderDragon);
        registerArmorModel(enableGuardianArmor, guardian);
        registerArmorModel(enableSuperStarArmor, superStar);
        registerArmorModel(enableArditeArmor, ardite);
        registerArmorModel(enableCobaltArmor, cobalt);
        registerArmorModel(enableManyullynArmor, manyullyn);
        registerArmorModel(enablePigIronArmor, pigIron);
        registerArmorModel(enableKnightSlimeArmor, knightSlime);
        registerArmorModel(enableTheUltimateArmor, theUltimate);
        registerSwordModel(isSwordEnabled, sword);
        registerBattleAxeModel(isBattleAxeEnabled, battleAxe);
        registerBowModel(isBowEnabled, bow);
        devTool.initModel();
        itemCoalArrow.initModel();
        itemLapisArrow.initModel();
        itemRedstoneArrow.initModel();
        itemLavaArrow.initModel();
        itemEnderDragonArrow.initModel();
        //  for (BaseItem template : templates) template.initModel();
    }

    @SubscribeEvent
    public void registerEntityModels(ModelRegistryEvent event) {
        //Mobs
        registerEntityRenderingHandler(EntityEnderDragonZombie.class, RenderEnderDragonZombie::new);
        //Arrows
        registerEntityRenderingHandler(EntityCoalArrow.class, RenderCoalArrow::new);
        registerEntityRenderingHandler(EntityLapisArrow.class, RenderLapisArrow::new);
        registerEntityRenderingHandler(EntityRedstoneArrow.class, RenderRedstoneArrow::new);
        registerEntityRenderingHandler(EntityLavaArrow.class, RenderLavaArrow::new);
        registerEntityRenderingHandler(EntityEnderDragonArrow.class, RenderEnderDragonArrow::new);
        //Bosses
        registerEntityRenderingHandler(EntityGuardianOverlord.class, RenderGuardianOverlord::new);
        registerEntityRenderingHandler(EntitySkeletalKing.class, RenderSkeletalKing::new);
        //Boss Projectiles
        registerEntityRenderingHandler(EntityFreezeBomb.class, RenderFreezeBomb::new);
        registerEntityRenderingHandler(EntityWitherMinion.class, RenderWitherMinion::new);
    }
}
