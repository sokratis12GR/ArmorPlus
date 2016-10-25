/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.benches.HighTechBench;
import net.thedragonteam.armorplus.blocks.benches.UltiTechBench;
import net.thedragonteam.armorplus.blocks.benches.Workbench;
import net.thedragonteam.armorplus.blocks.castle.EnumStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.base.BaseStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.base.BaseStoneBrickCorner;
import net.thedragonteam.armorplus.blocks.castle.base.BaseStoneBrickTower;
import net.thedragonteam.armorplus.blocks.normal.BlockLavaCrystal;
import net.thedragonteam.armorplus.blocks.normal.CompressedObsidian;
import net.thedragonteam.armorplus.blocks.normal.LavaCactus;
import net.thedragonteam.armorplus.blocks.normal.LavaNetherBrick;
import net.thedragonteam.armorplus.blocks.spawners.SpawnerEnderDragonZombie;
import net.thedragonteam.armorplus.blocks.spawners.SpawnerGuardian;
import net.thedragonteam.armorplus.blocks.v2.BaseMetalBlock;
import net.thedragonteam.armorplus.blocks.v2.EnumMetalBlock;
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
    public static SpawnerGuardian spawnerGuardian;
    public static SpawnerEnderDragonZombie spawnerEnderDragonZombie;
    public static Workbench arpWorkbench;
    public static HighTechBench arpHighTechBench;
    public static UltiTechBench arpUltiTechBench;

    public static void init() {
        blockLavaCrystal = new BlockLavaCrystal();
        compressedObsidian = new CompressedObsidian();
        steelBlock = new BaseMetalBlock(EnumMetalBlock.STEEL);
        electricalBlock = new BaseMetalBlock(EnumMetalBlock.ELECTRICAL);
        lavaCactus = new LavaCactus();
        lavaNetherBrick = new LavaNetherBrick();
        whiteStoneBrick = new BaseStoneBrick(EnumStoneBrick.WHITE);
        whiteStoneBrickTower = new BaseStoneBrickTower(EnumStoneBrick.WHITE);
        whiteStoneBrickCorner = new BaseStoneBrickCorner(EnumStoneBrick.WHITE);
        redStoneBrick = new BaseStoneBrick(EnumStoneBrick.RED);
        redStoneBrickTower = new BaseStoneBrickTower(EnumStoneBrick.RED);
        redStoneBrickCorner = new BaseStoneBrickCorner(EnumStoneBrick.RED);
        blackStoneBrick = new BaseStoneBrick(EnumStoneBrick.BLACK);
        blackStoneBrickTower = new BaseStoneBrickTower(EnumStoneBrick.BLACK);
        blackStoneBrickCorner = new BaseStoneBrickCorner(EnumStoneBrick.BLACK);
        blueStoneBrick = new BaseStoneBrick(EnumStoneBrick.BLUE);
        blueStoneBrickTower = new BaseStoneBrickTower(EnumStoneBrick.BLUE);
        blueStoneBrickCorner = new BaseStoneBrickCorner(EnumStoneBrick.BLUE);
        greenStoneBrick = new BaseStoneBrick(EnumStoneBrick.GREEN);
        greenStoneBrickTower = new BaseStoneBrickTower(EnumStoneBrick.GREEN);
        greenStoneBrickCorner = new BaseStoneBrickCorner(EnumStoneBrick.GREEN);
        yellowStoneBrick = new BaseStoneBrick(EnumStoneBrick.YELLOW);
        yellowStoneBrickTower = new BaseStoneBrickTower(EnumStoneBrick.YELLOW);
        yellowStoneBrickCorner = new BaseStoneBrickCorner(EnumStoneBrick.YELLOW);
        purpleStoneBrick = new BaseStoneBrick(EnumStoneBrick.PURPLE);
        purpleStoneBrickTower = new BaseStoneBrickTower(EnumStoneBrick.PURPLE);
        purpleStoneBrickCorner = new BaseStoneBrickCorner(EnumStoneBrick.PURPLE);
        spawnerGuardian = new SpawnerGuardian();
        spawnerEnderDragonZombie = new SpawnerEnderDragonZombie();
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