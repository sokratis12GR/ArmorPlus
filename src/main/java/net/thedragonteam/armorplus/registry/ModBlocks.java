/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.*;
import net.thedragonteam.armorplus.blocks.castle.WhiteStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.WhiteStoneBrickCorner;
import net.thedragonteam.armorplus.blocks.castle.WhiteStoneBrickTower;
import net.thedragonteam.armorplus.blocks.v2.ElectricalBlock;
import net.thedragonteam.armorplus.blocks.v2.SteelBlock;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:39 PM.
 * - TheDragonTeam
 */
public class ModBlocks {

    public static Block blockLavaCrystal;
    public static Block compressedObsidian;
    public static Block steelBlock;
    public static Block electricalBlock;
    public static Block armorForge;
    public static Block advancedArmorForge;
    public static Block whiteStoneBrick;
    public static Block lavaNetherBrick;
    public static Block whiteStoneBrickTower;
    public static Block whiteStoneBrickCorner;
    public static LavaCactus lavaCactus;

    public static void init() {
        blockLavaCrystal = new BlockLavaCrystal().setRegistryName("block_lava_crystal");
        compressedObsidian = new CompressedObsidian().setRegistryName("compressed_obsidian");
        steelBlock = new SteelBlock().setRegistryName("steel_block");
        electricalBlock = new ElectricalBlock().setRegistryName("electrical_block");
        armorForge = new ArmorForge().setRegistryName("armor_forge");
        advancedArmorForge = new AdvancedArmorForge().setRegistryName("advanced_armor_forge");
        lavaCactus = new LavaCactus();
        lavaNetherBrick = new LavaNetherBrick().setRegistryName("lava_nether_brick");
        whiteStoneBrick = new WhiteStoneBrick().setRegistryName("white_stone_brick");
        whiteStoneBrickTower = new WhiteStoneBrickTower().setRegistryName("white_stone_brick_tower");
        whiteStoneBrickCorner = new WhiteStoneBrickCorner().setRegistryName("white_stone_brick_corner");
    }

    public static void register() {
        registerBlock(blockLavaCrystal);
        registerBlock(compressedObsidian);
        registerBlock(steelBlock);
        registerBlock(electricalBlock);
        registerBlock(armorForge);
        registerBlock(advancedArmorForge);
        registerBlock(lavaCactus);
        registerBlock(lavaNetherBrick);
        registerBlock(whiteStoneBrick);
        registerBlock(whiteStoneBrickTower);
        registerBlock(whiteStoneBrickCorner);
    }

    public static void registerRenders() {
        registerRender(blockLavaCrystal);
        registerRender(compressedObsidian);
        registerRender(steelBlock);
        registerRender(electricalBlock);
        registerRender(armorForge);
        registerRender(advancedArmorForge);
        registerRender(lavaCactus);
        registerRender(lavaNetherBrick);
        registerRender(whiteStoneBrick);
        registerRender(whiteStoneBrickTower);
        registerRender(whiteStoneBrickCorner);
    }

    public static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getRegistryName().toString());
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}