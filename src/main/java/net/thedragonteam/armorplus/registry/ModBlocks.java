/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BaseBenchBlock;
import net.thedragonteam.armorplus.blocks.castle.base.*;
import net.thedragonteam.armorplus.blocks.normal.BlockLavaCrystal;
import net.thedragonteam.armorplus.blocks.normal.CompressedObsidian;
import net.thedragonteam.armorplus.blocks.normal.LavaCactus;
import net.thedragonteam.armorplus.blocks.normal.LavaNetherBrick;
import net.thedragonteam.armorplus.blocks.spawners.base.BaseSpawner;
import net.thedragonteam.armorplus.blocks.v2.BaseMetalBlock;

import static net.thedragonteam.armorplus.blocks.benches.Benches.*;
import static net.thedragonteam.armorplus.blocks.castle.StoneBricks.*;
import static net.thedragonteam.armorplus.blocks.spawners.Spawners.ENDER_DRAGON_ZOMBIE;
import static net.thedragonteam.armorplus.blocks.spawners.Spawners.GUARDIAN;
import static net.thedragonteam.armorplus.blocks.v2.Metals.ELECTRICAL;
import static net.thedragonteam.armorplus.blocks.v2.Metals.STEEL;

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
    public static BaseStoneBrick whiteStoneBrick, redStoneBrick, blackStoneBrick,
            blueStoneBrick, yellowStoneBrick, greenStoneBrick, purpleStoneBrick;
    public static BaseStoneBrickTower whiteStoneBrickTower, redStoneBrickTower,
            blackStoneBrickTower, blueStoneBrickTower, yellowStoneBrickTower,
            greenStoneBrickTower, purpleStoneBrickTower;
    public static BaseStoneBrickCorner whiteStoneBrickCorner;
    public static BaseStoneBrickCorner redStoneBrickCorner;
    public static BaseStoneBrickCorner blackStoneBrickCorner;
    public static BaseStoneBrickCorner blueStoneBrickCorner;
    public static BaseStoneBrickCorner yellowStoneBrickCorner;
    public static BaseStoneBrickCorner greenStoneBrickCorner;
    public static BaseStoneBrickCorner purpleStoneBrickCorner;
    public static LavaCactus lavaCactus;
    public static BaseSpawner spawnerGuardian;
    public static BaseSpawner spawnerEnderDragonZombie;
    public static BaseBenchBlock arpWorkbench;
    public static BaseBenchBlock arpHighTechBench;
    public static BaseBenchBlock arpUltiTechBench;

    public static void init() {
        blockLavaCrystal = new BlockLavaCrystal();
        compressedObsidian = new CompressedObsidian();
        steelBlock = new BaseMetalBlock(STEEL);
        electricalBlock = new BaseMetalBlock(ELECTRICAL);
        lavaCactus = new LavaCactus();
        lavaNetherBrick = new LavaNetherBrick();
        whiteStoneBrick = new BaseStoneBrick(WHITE);
        whiteStoneBrickTower = new BaseStoneBrickTower(WHITE);
        whiteStoneBrickCorner = new BaseStoneBrickCorner(WHITE, whiteStoneBrick.getDefaultState());
        redStoneBrick = new BaseStoneBrick(RED);
        redStoneBrickTower = new BaseStoneBrickTower(RED);
        redStoneBrickCorner = new BaseStoneBrickCorner(RED, redStoneBrick.getDefaultState());
        blackStoneBrick = new BaseStoneBrick(BLACK);
        blackStoneBrickTower = new BaseStoneBrickTower(BLACK);
        blackStoneBrickCorner = new BaseStoneBrickCorner(BLACK, blackStoneBrick.getDefaultState());
        blueStoneBrick = new BaseStoneBrick(BLUE);
        blueStoneBrickTower = new BaseStoneBrickTower(BLUE);
        blueStoneBrickCorner = new BaseStoneBrickCorner(BLUE, blueStoneBrick.getDefaultState());
        greenStoneBrick = new BaseStoneBrick(GREEN);
        greenStoneBrickTower = new BaseStoneBrickTower(GREEN);
        greenStoneBrickCorner = new BaseStoneBrickCorner(GREEN, greenStoneBrick.getDefaultState());
        yellowStoneBrick = new BaseStoneBrick(YELLOW);
        yellowStoneBrickTower = new BaseStoneBrickTower(YELLOW);
        yellowStoneBrickCorner = new BaseStoneBrickCorner(YELLOW, yellowStoneBrick.getDefaultState());
        purpleStoneBrick = new BaseStoneBrick(PURPLE);
        purpleStoneBrickTower = new BaseStoneBrickTower(PURPLE);
        purpleStoneBrickCorner = new BaseStoneBrickCorner(PURPLE, purpleStoneBrick.getDefaultState());
        spawnerGuardian = new BaseSpawner(GUARDIAN);
        spawnerEnderDragonZombie = new BaseSpawner(ENDER_DRAGON_ZOMBIE);
        arpWorkbench = new BaseBenchBlock(WORKBENCH);
        arpHighTechBench = new BaseBenchBlock(HIGH_TECH);
        arpUltiTechBench = new BaseBenchBlock(ULIT_TECH);
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