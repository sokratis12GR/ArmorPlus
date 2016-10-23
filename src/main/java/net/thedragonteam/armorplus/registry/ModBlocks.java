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
    public static WhiteStoneBrick whiteStoneBrick;
    public static WhiteStoneBrickTower whiteStoneBrickTower;
    public static WhiteStoneBrickCorner whiteStoneBrickCorner;
    public static RedStoneBrick redStoneBrick;
    public static RedStoneBrickTower redStoneBrickTower;
    public static RedStoneBrickCorner redStoneBrickCorner;
    public static BlackStoneBrick blackStoneBrick;
    public static BlackStoneBrickTower blackStoneBrickTower;
    public static BlackStoneBrickCorner blackStoneBrickCorner;
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
        whiteStoneBrick = new WhiteStoneBrick();
        whiteStoneBrickTower = new WhiteStoneBrickTower();
        whiteStoneBrickCorner = new WhiteStoneBrickCorner();
        redStoneBrick = new RedStoneBrick();
        redStoneBrickTower = new RedStoneBrickTower();
        redStoneBrickCorner = new RedStoneBrickCorner();
        blackStoneBrick = new BlackStoneBrick();
        blackStoneBrickTower = new BlackStoneBrickTower();
        blackStoneBrickCorner = new BlackStoneBrickCorner();
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
        spawnerGuardian.initModel();
        spawnerEnderDragonZombie.initModel();
        arpWorkbench.initModel();
        arpHighTechBench.initModel();
        arpUltiTechBench.initModel();
    }

}