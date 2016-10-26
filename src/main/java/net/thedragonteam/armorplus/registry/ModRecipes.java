/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus created by sokratis12GR on 6/20/2016 6:44 PM.
 * - TheDragonTeam
 */
public class ModRecipes {

    public static void init() {
        addShapedRecipes();
        addShapelessRecipes();
        addIntegrationShapelessRecipes();
    }

    public static void addIntegrationShapelessRecipes() {
        /* NBT-Tag-Compound */
        NBTTagCompound nbttagc = new NBTTagCompound();
        nbttagc.setInteger("theoneprobe", 1);

        /* Set Helmets' NBT-Tags */
        ItemStack coalHelmet = new ItemStack(ModItems.coalHelmet, 1);
        coalHelmet.setTagCompound(nbttagc);
        ItemStack emeraldHelmet = new ItemStack(ModItems.emeraldHelmet, 1);
        emeraldHelmet.setTagCompound(nbttagc);
        ItemStack lapisHelmet = new ItemStack(ModItems.lapisHelmet, 1);
        lapisHelmet.setTagCompound(nbttagc);
        ItemStack lavaHelmet = new ItemStack(ModItems.lavaHelmet, 1);
        lavaHelmet.setTagCompound(nbttagc);
        ItemStack obsidianHelmet = new ItemStack(ModItems.obsidianHelmet);
        obsidianHelmet.setTagCompound(nbttagc);
        ItemStack redstoneHelmet = new ItemStack(ModItems.redstoneHelmet, 1);
        redstoneHelmet.setTagCompound(nbttagc);
        ItemStack chickenHelmet = new ItemStack(ModItems.chickenHelmet, 1);
        chickenHelmet.setTagCompound(nbttagc);
        ItemStack slimeHelmet = new ItemStack(ModItems.slimeHelmet, 1);
        slimeHelmet.setTagCompound(nbttagc);
        ItemStack arditeHelmet = new ItemStack(ModItems.arditeHelmet, 1);
        arditeHelmet.setTagCompound(nbttagc);
        ItemStack cobaltHelmet = new ItemStack(ModItems.cobaltHelmet, 1);
        cobaltHelmet.setTagCompound(nbttagc);
        ItemStack manyullynHelmet = new ItemStack(ModItems.manyullynHelmet, 1);
        manyullynHelmet.setTagCompound(nbttagc);
        ItemStack pigIronHelmet = new ItemStack(ModItems.pigIronHelmet, 1);
        pigIronHelmet.setTagCompound(nbttagc);
        ItemStack knightSlimeHelmet = new ItemStack(ModItems.knightSlimeHelmet, 1);
        knightSlimeHelmet.setTagCompound(nbttagc);
        ItemStack steelHelmet = new ItemStack(ModItems.steelHelmet, 1);
        steelHelmet.setTagCompound(nbttagc);
        ItemStack electricalHelmet = new ItemStack(ModItems.electricalHelmet, 1);
        electricalHelmet.setTagCompound(nbttagc);
        ItemStack enderDragonHelmet = new ItemStack(ModItems.enderDragonHelmet, 1);
        enderDragonHelmet.setTagCompound(nbttagc);
        ItemStack guardianHelmet = new ItemStack(ModItems.guardianHelmet, 1);
        guardianHelmet.setTagCompound(nbttagc);
        ItemStack superStarHelmet = new ItemStack(ModItems.superStarHelmet, 1);
        superStarHelmet.setTagCompound(nbttagc);
        ItemStack theUltimateHelmet = new ItemStack(ModItems.theUltimateHelmet, 1);
        theUltimateHelmet.setTagCompound(nbttagc);

        GameRegistry.addShapelessRecipe(enderDragonHelmet, ModItems.enderDragonHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(guardianHelmet, ModItems.guardianHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(superStarHelmet, ModItems.superStarHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(theUltimateHelmet, ModItems.theUltimateHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(coalHelmet, ModItems.coalHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(emeraldHelmet, ModItems.emeraldHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(lapisHelmet, ModItems.lapisHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(lavaHelmet, ModItems.lavaHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(obsidianHelmet, ModItems.obsidianHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(redstoneHelmet, ModItems.redstoneHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(chickenHelmet, ModItems.chickenHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(slimeHelmet, ModItems.slimeHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(arditeHelmet, ModItems.arditeHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(cobaltHelmet, ModItems.cobaltHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(manyullynHelmet, ModItems.manyullynHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(pigIronHelmet, ModItems.pigIronHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(knightSlimeHelmet, ModItems.knightSlimeHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(steelHelmet, ModItems.steelHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(electricalHelmet, ModItems.electricalHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
    }

    public static void addShapedRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.arpWorkbench), "LCL", "OTO", "OXO", 'T', "workbench", 'O', "blockCoal", 'L', "gemLapis", 'C', "gemLavaCrystal"));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.lavaNetherBrick, 4), " N ", "NLN", " N ", 'L', Items.LAVA_BUCKET, 'N', Blocks.NETHER_BRICK);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.whiteStoneBrick, 4), " S ", "SWS", " S ", 'S', "stonebrick", 'W', "dyeWhite"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.redStoneBrick, 4), " S ", "SWS", " S ", 'S', "stonebrick", 'W', "dyeRed"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blackStoneBrick, 4), " S ", "SWS", " S ", 'S', "stonebrick", 'W', "dyeBlack"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blueStoneBrick, 4), " S ", "SWS", " S ", 'S', "stonebrick", 'W', "dyeBlue"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.greenStoneBrick, 4), " S ", "SWS", " S ", 'S', "stonebrick", 'W', "dyeGreen"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.yellowStoneBrick, 4), " S ", "SWS", " S ", 'S', "stonebrick", 'W', "dyeYellow"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.purpleStoneBrick, 4), " S ", "SWS", " S ", 'S', "stonebrick", 'W', "dyePurple"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.whiteStoneBrick, 5), "stonebrickWhiteTower"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.redStoneBrick, 5), "stonebrickRedTower"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blackStoneBrick, 5), "stonebrickBlackTower"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blueStoneBrick, 5), "stonebrickBlueTower"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.greenStoneBrick, 5), "stonebrickGreenTower"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.yellowStoneBrick, 5), "stonebrickYellowTower"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.purpleStoneBrick, 5), "stonebrickPurpleTower"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.whiteStoneBrickTower, 1), "   ", "S S", "SSS", 'S', "stonebrickWhite"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.redStoneBrickTower, 1), "   ", "S S", "SSS", 'S', "stonebrickRed"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blackStoneBrickTower, 1), "   ", "S S", "SSS", 'S', "stonebrickBlack"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blueStoneBrickTower, 1), "   ", "S S", "SSS", 'S', "stonebrickBlue"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.greenStoneBrickTower, 1), "   ", "S S", "SSS", 'S', "stonebrickGreen"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.yellowStoneBrickTower, 1), "   ", "S S", "SSS", 'S', "stonebrickYellow"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.purpleStoneBrickTower, 1), "   ", "S S", "SSS", 'S', "stonebrickPurple"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.whiteStoneBrick, 3), "stonebrickWhiteCorner"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.redStoneBrick, 3), "stonebrickRedCorner"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blackStoneBrick, 3), "stonebrickBlackCorner"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.blueStoneBrick, 3), "stonebrickBlueCorner"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.greenStoneBrick, 3), "stonebrickGreenCorner"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.yellowStoneBrick, 3), "stonebrickYellowCorner"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.purpleStoneBrick, 3), "stonebrickPurpleCorner"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.whiteStoneBrickCorner, 1), "   ", "  S", " SS", 'S', "stonebrickWhite"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.redStoneBrickCorner, 1), "   ", "  S", " SS", 'S', "stonebrickRed"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blackStoneBrickCorner, 1), "   ", "  S", " SS", 'S', "stonebrickBlack"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.blueStoneBrickCorner, 1), "   ", "  S", " SS", 'S', "stonebrickBlue"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.greenStoneBrickCorner, 1), "   ", "  S", " SS", 'S', "stonebrickGreen"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.yellowStoneBrickCorner, 1), "   ", "  S", " SS", 'S', "stonebrickYellow"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.purpleStoneBrickCorner, 1), "   ", "  S", " SS", 'S', "stonebrickPurple"));
        if (enableElytraRecipe)
            GameRegistry.addRecipe(new ItemStack(Items.ELYTRA, 1), "ESE", "SNS", "EEE", 'E', ModItems.enderDragonScale, 'S', Items.STRING, 'N', Items.NETHER_STAR);
        if (enableChainArmorRecipes) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "   ", "CCC", "C C", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "CCC", "C C", "   ", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1), "C C", "CCC", "CCC", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_LEGGINGS, 1), "CCC", "C C", "C C", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "   ", "C C", "C C", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "C C", "C C", "   ", 'C', "chainmail"));
        }
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chainmail, 12), "SS ", "S S", " SS", 'S', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compressedObsidian, 1), "OOO", "OOO", "OOO", 'O', "obsidian"));
        if (enableRedstoneAppleRecipes) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneApple, 1), "RRR", "RAR", "RRR", 'R', "dustRedstone", 'A', Items.APPLE));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneApple, 1, 1), "BBB", "BAB", "BBB", 'B', "blockRedstone", 'A', Items.APPLE));
        }
    }

    public static void addShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nbtItem, 1), Items.STICK, Items.GLOWSTONE_DUST);
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN, 9), ModBlocks.compressedObsidian);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorPlusInfoBook, 1), Items.BOOK, Items.COAL);
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.lavaCactus, 1), Blocks.CACTUS, new ItemStack(ModItems.lavaCrystal, 1, 1));
    }

}
