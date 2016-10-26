/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.benches.HighTechBench;
import net.thedragonteam.armorplus.blocks.benches.UltiTechBench;
import net.thedragonteam.armorplus.blocks.benches.Workbench;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;
import net.thedragonteam.armorplus.blocks.castle.base.BaseStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.base.BaseStoneBrickCorner;
import net.thedragonteam.armorplus.blocks.castle.base.BaseStoneBrickTower;
import net.thedragonteam.armorplus.blocks.normal.BlockLavaCrystal;
import net.thedragonteam.armorplus.blocks.normal.CompressedObsidian;
import net.thedragonteam.armorplus.blocks.normal.LavaCactus;
import net.thedragonteam.armorplus.blocks.normal.LavaNetherBrick;
import net.thedragonteam.armorplus.blocks.spawners.Spawners;
import net.thedragonteam.armorplus.blocks.spawners.base.BaseSpawner;
import net.thedragonteam.armorplus.blocks.v2.BaseMetalBlock;
import net.thedragonteam.armorplus.blocks.v2.Metals;
import net.thedragonteam.armorplus.tileentity.TileEntityHighTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityUltiTechBench;
import net.thedragonteam.armorplus.tileentity.TileEntityWorkbench;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:39 PM.
 * - TheDragonTeam
 */
public class ModBlocks {

    public static BlockLavaCrystal blockLavaCrystal;
    public static CompressedObsidian compressedObsidian;
    public static BaseMetalBlock steelBlock;
    public static BaseMetalBlock electricalBlock;
    public static LavaNetherBrick lavaNetherBrick;
    public static BaseStoneBrick whiteStoneBrick;
    public static BaseStoneBrickTower whiteStoneBrickTower;
    public static BaseStoneBrickCorner whiteStoneBrickCorner;
    public static BaseStoneBrick redStoneBrick;
    public static BaseStoneBrickTower redStoneBrickTower;
    public static BaseStoneBrickCorner redStoneBrickCorner;
    public static BaseStoneBrick blackStoneBrick;
    public static BaseStoneBrickTower blackStoneBrickTower;
    public static BaseStoneBrickCorner blackStoneBrickCorner;
    public static BaseStoneBrick blueStoneBrick;
    public static BaseStoneBrickTower blueStoneBrickTower;
    public static BaseStoneBrickCorner blueStoneBrickCorner;
    public static BaseStoneBrick yellowStoneBrick;
    public static BaseStoneBrickTower yellowStoneBrickTower;
    public static BaseStoneBrickCorner yellowStoneBrickCorner;
    public static BaseStoneBrick greenStoneBrick;
    public static BaseStoneBrickTower greenStoneBrickTower;
    public static BaseStoneBrickCorner greenStoneBrickCorner;
    public static BaseStoneBrick purpleStoneBrick;
    public static BaseStoneBrickTower purpleStoneBrickTower;
    public static BaseStoneBrickCorner purpleStoneBrickCorner;
    public static LavaCactus lavaCactus;
    public static BaseSpawner spawnerGuardian;
    public static BaseSpawner spawnerEnderDragonZombie;
    public static Workbench arpWorkbench;
    public static HighTechBench arpHighTechBench;
    public static UltiTechBench arpUltiTechBench;

    public static void init() {
        blockLavaCrystal = new BlockLavaCrystal();
        compressedObsidian = new CompressedObsidian();
        steelBlock = new BaseMetalBlock(Metals.STEEL);
        electricalBlock = new BaseMetalBlock(Metals.ELECTRICAL);
        lavaCactus = new LavaCactus();
        lavaNetherBrick = new LavaNetherBrick();
        whiteStoneBrick = new BaseStoneBrick(StoneBricks.WHITE);
        whiteStoneBrickTower = new BaseStoneBrickTower(StoneBricks.WHITE);
        whiteStoneBrickCorner = new BaseStoneBrickCorner(StoneBricks.WHITE);
        redStoneBrick = new BaseStoneBrick(StoneBricks.RED);
        redStoneBrickTower = new BaseStoneBrickTower(StoneBricks.RED);
        redStoneBrickCorner = new BaseStoneBrickCorner(StoneBricks.RED);
        blackStoneBrick = new BaseStoneBrick(StoneBricks.BLACK);
        blackStoneBrickTower = new BaseStoneBrickTower(StoneBricks.BLACK);
        blackStoneBrickCorner = new BaseStoneBrickCorner(StoneBricks.BLACK);
        blueStoneBrick = new BaseStoneBrick(StoneBricks.BLUE);
        blueStoneBrickTower = new BaseStoneBrickTower(StoneBricks.BLUE);
        blueStoneBrickCorner = new BaseStoneBrickCorner(StoneBricks.BLUE);
        greenStoneBrick = new BaseStoneBrick(StoneBricks.GREEN);
        greenStoneBrickTower = new BaseStoneBrickTower(StoneBricks.GREEN);
        greenStoneBrickCorner = new BaseStoneBrickCorner(StoneBricks.GREEN);
        yellowStoneBrick = new BaseStoneBrick(StoneBricks.YELLOW);
        yellowStoneBrickTower = new BaseStoneBrickTower(StoneBricks.YELLOW);
        yellowStoneBrickCorner = new BaseStoneBrickCorner(StoneBricks.YELLOW);
        purpleStoneBrick = new BaseStoneBrick(StoneBricks.PURPLE);
        purpleStoneBrickTower = new BaseStoneBrickTower(StoneBricks.PURPLE);
        purpleStoneBrickCorner = new BaseStoneBrickCorner(StoneBricks.PURPLE);
        spawnerGuardian = new BaseSpawner(Spawners.GUARDIAN);
        spawnerEnderDragonZombie = new BaseSpawner(Spawners.ENDER_DRAGON_ZOMBIE);
        arpWorkbench = new Workbench(new TileEntityWorkbench());
        arpHighTechBench = new HighTechBench(new TileEntityHighTechBench());
        arpUltiTechBench = new UltiTechBench(new TileEntityUltiTechBench());
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockLavaCrystal.initModel();
        compressedObsidian.initModel();
        steelBlock.initModel();
        electricalBlock.initModel();
        lavaCactus.initModel();
        lavaNetherBrick.initModel();
        whiteStoneBrick.initModel();
        whiteStoneBrickTower.initModel();
        whiteStoneBrickCorner.initModel();
        redStoneBrick.initModel();
        redStoneBrickTower.initModel();
        redStoneBrickCorner.initModel();
        blackStoneBrick.initModel();
        blackStoneBrickTower.initModel();
        blackStoneBrickCorner.initModel();
        blueStoneBrick.initModel();
        blueStoneBrickTower.initModel();
        blueStoneBrickCorner.initModel();
        greenStoneBrick.initModel();
        greenStoneBrickTower.initModel();
        greenStoneBrickCorner.initModel();
        yellowStoneBrick.initModel();
        yellowStoneBrickTower.initModel();
        yellowStoneBrickCorner.initModel();
        purpleStoneBrick.initModel();
        purpleStoneBrickTower.initModel();
        purpleStoneBrickCorner.initModel();
        spawnerGuardian.initModel();
        spawnerEnderDragonZombie.initModel();
        arpWorkbench.initModel();
        arpHighTechBench.initModel();
        arpUltiTechBench.initModel();
    }

}