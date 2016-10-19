/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.benches.HighTechBench;
import net.thedragonteam.armorplus.blocks.benches.UltiTechBench;
import net.thedragonteam.armorplus.blocks.benches.Workbench;
import net.thedragonteam.armorplus.blocks.castle.WhiteStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.WhiteStoneBrickCorner;
import net.thedragonteam.armorplus.blocks.castle.WhiteStoneBrickTower;
import net.thedragonteam.armorplus.blocks.normal.BlockLavaCrystal;
import net.thedragonteam.armorplus.blocks.normal.CompressedObsidian;
import net.thedragonteam.armorplus.blocks.normal.LavaCactus;
import net.thedragonteam.armorplus.blocks.normal.LavaNetherBrick;
import net.thedragonteam.armorplus.blocks.spawners.SpawnerEnderDragonZombie;
import net.thedragonteam.armorplus.blocks.spawners.SpawnerGuardian;
import net.thedragonteam.armorplus.blocks.v2.ElectricalBlock;
import net.thedragonteam.armorplus.blocks.v2.SteelBlock;

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
    public static WhiteStoneBrick whiteStoneBrick;
    public static LavaNetherBrick lavaNetherBrick;
    public static WhiteStoneBrickTower whiteStoneBrickTower;
    public static WhiteStoneBrickCorner whiteStoneBrickCorner;
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
        spawnerGuardian = new SpawnerGuardian();
        spawnerEnderDragonZombie = new SpawnerEnderDragonZombie();
        arpWorkbench = new Workbench();
        arpHighTechBench = new HighTechBench();
        arpUltiTechBench = new UltiTechBench();
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
        spawnerGuardian.initModel();
        spawnerEnderDragonZombie.initModel();
        arpWorkbench.initModel();
        arpHighTechBench.initModel();
        arpUltiTechBench.initModel();
    }

}