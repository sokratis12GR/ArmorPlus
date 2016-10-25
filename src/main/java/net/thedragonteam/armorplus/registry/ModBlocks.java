/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.benches.HighTechBench;
import net.thedragonteam.armorplus.blocks.benches.UltiTechBench;
import net.thedragonteam.armorplus.blocks.benches.Workbench;
import net.thedragonteam.armorplus.blocks.castle.*;
import net.thedragonteam.armorplus.blocks.normal.BlockLavaCrystal;
import net.thedragonteam.armorplus.blocks.normal.CompressedObsidian;
import net.thedragonteam.armorplus.blocks.normal.LavaCactus;
import net.thedragonteam.armorplus.blocks.normal.LavaNetherBrick;
import net.thedragonteam.armorplus.blocks.spawners.SpawnerEnderDragonZombie;
import net.thedragonteam.armorplus.blocks.spawners.SpawnerGuardian;
import net.thedragonteam.armorplus.blocks.v2.ElectricalBlock;
import net.thedragonteam.armorplus.blocks.v2.SteelBlock;
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
    public static SteelBlock steelBlock;
    public static ElectricalBlock electricalBlock;
    public static LavaNetherBrick lavaNetherBrick;
    public static BlockStoneBricks whiteStoneBrick;
    public static BlockStoneBricksTower whiteStoneBrickTower;
    public static BlockStoneBricksCorner whiteStoneBrickCorner;
    public static BlockStoneBricks redStoneBrick;
    public static BlockStoneBricksTower redStoneBrickTower;
    public static BlockStoneBricksCorner redStoneBrickCorner;
    public static BlockStoneBricks blackStoneBrick;
    public static BlockStoneBricksTower blackStoneBrickTower;
    public static BlockStoneBricksCorner blackStoneBrickCorner;
    public static BlockStoneBricks blueStoneBrick;
    public static BlockStoneBricksTower blueStoneBrickTower;
    public static BlockStoneBricksCorner blueStoneBrickCorner;
    public static BlockStoneBricks yellowStoneBrick;
    public static BlockStoneBricksTower yellowStoneBrickTower;
    public static BlockStoneBricksCorner yellowStoneBrickCorner;
    public static BlockStoneBricks greenStoneBrick;
    public static BlockStoneBricksTower greenStoneBrickTower;
    public static BlockStoneBricksCorner greenStoneBrickCorner;
    public static BlockStoneBricks purpleStoneBrick;
    public static BlockStoneBricksTower purpleStoneBrickTower;
    public static BlockStoneBricksCorner purpleStoneBrickCorner;
    public static LavaCactus lavaCactus;
    public static SpawnerGuardian spawnerGuardian;
    public static SpawnerEnderDragonZombie spawnerEnderDragonZombie;
    public static Workbench arpWorkbench;
    public static HighTechBench arpHighTechBench;
    public static UltiTechBench arpUltiTechBench;

    public static void init() {
        blockLavaCrystal = new BlockLavaCrystal();
        compressedObsidian = new CompressedObsidian();
        steelBlock = new SteelBlock();
        electricalBlock = new ElectricalBlock();
        lavaCactus = new LavaCactus();
        lavaNetherBrick = new LavaNetherBrick();
        whiteStoneBrick = new BlockStoneBricks(EnumStoneBrick.WHITE);
        whiteStoneBrickTower = new BlockStoneBricksTower(EnumStoneBrick.WHITE);
        whiteStoneBrickCorner = new BlockStoneBricksCorner(EnumStoneBrick.WHITE);
        redStoneBrick = new BlockStoneBricks(EnumStoneBrick.RED);
        redStoneBrickTower = new BlockStoneBricksTower(EnumStoneBrick.RED);
        redStoneBrickCorner = new BlockStoneBricksCorner(EnumStoneBrick.RED);
        blackStoneBrick = new BlockStoneBricks(EnumStoneBrick.BLACK);
        blackStoneBrickTower = new BlockStoneBricksTower(EnumStoneBrick.BLACK);
        blackStoneBrickCorner = new BlockStoneBricksCorner(EnumStoneBrick.BLACK);
        blueStoneBrick = new BlockStoneBricks(EnumStoneBrick.BLUE);
        blueStoneBrickTower = new BlockStoneBricksTower(EnumStoneBrick.BLUE);
        blueStoneBrickCorner = new BlockStoneBricksCorner(EnumStoneBrick.BLUE);
        greenStoneBrick = new BlockStoneBricks(EnumStoneBrick.GREEN);
        greenStoneBrickTower = new BlockStoneBricksTower(EnumStoneBrick.GREEN);
        greenStoneBrickCorner = new BlockStoneBricksCorner(EnumStoneBrick.GREEN);
        yellowStoneBrick = new BlockStoneBricks(EnumStoneBrick.YELLOW);
        yellowStoneBrickTower = new BlockStoneBricksTower(EnumStoneBrick.YELLOW);
        yellowStoneBrickCorner = new BlockStoneBricksCorner(EnumStoneBrick.YELLOW);
        purpleStoneBrick = new BlockStoneBricks(EnumStoneBrick.PURPLE);
        purpleStoneBrickTower = new BlockStoneBricksTower(EnumStoneBrick.PURPLE);
        purpleStoneBrickCorner = new BlockStoneBricksCorner(EnumStoneBrick.PURPLE);
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