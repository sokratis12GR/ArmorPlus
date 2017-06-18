/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import static net.minecraft.init.Items.*;
import static net.minecraftforge.fml.common.registry.GameRegistry.*;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.registry.ModOreDicts.colors;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus created by sokratis12GR on 6/20/2016 6:44 PM.
 * - TheDragonTeam
 */
public class ModRecipes {

    public static void init() {

        addShapedRecipes();
        addShapelessRecipes();
        if (Loader.isModLoaded("theoneprobe")) {
            addIntegrationShapelessRecipes();
        }
    }

    public static void addIntegrationShapelessRecipes() {
        /* NBT-Tag-Compound */
        NBTTagCompound nbttagc = new NBTTagCompound();
        nbttagc.setInteger("theoneprobe", 1);

        /* Set Helmets' NBT-Tags */
        ItemStack coalHelmet = new ItemStack(APItems.coalHelmet, 1);
        coalHelmet.setTagCompound(nbttagc);
        ItemStack emeraldHelmet = new ItemStack(APItems.emeraldHelmet, 1);
        emeraldHelmet.setTagCompound(nbttagc);
        ItemStack lapisHelmet = new ItemStack(APItems.lapisHelmet, 1);
        lapisHelmet.setTagCompound(nbttagc);
        ItemStack lavaHelmet = new ItemStack(APItems.lavaHelmet, 1);
        lavaHelmet.setTagCompound(nbttagc);
        ItemStack obsidianHelmet = new ItemStack(APItems.obsidianHelmet, 1);
        obsidianHelmet.setTagCompound(nbttagc);
        ItemStack redstoneHelmet = new ItemStack(APItems.redstoneHelmet, 1);
        redstoneHelmet.setTagCompound(nbttagc);
        ItemStack chickenHelmet = new ItemStack(APItems.chickenHelmet, 1);
        chickenHelmet.setTagCompound(nbttagc);
        ItemStack slimeHelmet = new ItemStack(APItems.slimeHelmet, 1);
        slimeHelmet.setTagCompound(nbttagc);
        ItemStack arditeHelmet = new ItemStack(APItems.arditeHelmet, 1);
        arditeHelmet.setTagCompound(nbttagc);
        ItemStack cobaltHelmet = new ItemStack(APItems.cobaltHelmet, 1);
        cobaltHelmet.setTagCompound(nbttagc);
        ItemStack manyullynHelmet = new ItemStack(APItems.manyullynHelmet, 1);
        manyullynHelmet.setTagCompound(nbttagc);
        ItemStack pigIronHelmet = new ItemStack(APItems.pigIronHelmet, 1);
        pigIronHelmet.setTagCompound(nbttagc);
        ItemStack knightSlimeHelmet = new ItemStack(APItems.knightSlimeHelmet, 1);
        knightSlimeHelmet.setTagCompound(nbttagc);
        ItemStack enderDragonHelmet = new ItemStack(APItems.enderDragonHelmet, 1);
        enderDragonHelmet.setTagCompound(nbttagc);
        ItemStack guardianHelmet = new ItemStack(APItems.guardianHelmet, 1);
        guardianHelmet.setTagCompound(nbttagc);
        ItemStack superStarHelmet = new ItemStack(APItems.superStarHelmet, 1);
        superStarHelmet.setTagCompound(nbttagc);
        ItemStack theUltimateHelmet = new ItemStack(APItems.theUltimateHelmet, 1);
        theUltimateHelmet.setTagCompound(nbttagc);

        if (enableEnderDragonArmor)
            addShapelessRecipe(enderDragonHelmet, APItems.enderDragonHelmet, getItemStack("theoneprobe", "probe"));
        if (enableGuardianArmor)
            addShapelessRecipe(guardianHelmet, APItems.guardianHelmet, getItemStack("theoneprobe", "probe"));
        if (enableSuperStarArmor)
            addShapelessRecipe(superStarHelmet, APItems.superStarHelmet, getItemStack("theoneprobe", "probe"));
        if (enableTheUltimateArmor)
            addShapelessRecipe(theUltimateHelmet, APItems.theUltimateHelmet, getItemStack("theoneprobe", "probe"));
        if (enableCoalArmor)
            addShapelessRecipe(coalHelmet, APItems.coalHelmet, getItemStack("theoneprobe", "probe"));
        if (enableEmeraldArmor)
            addShapelessRecipe(emeraldHelmet, APItems.emeraldHelmet, getItemStack("theoneprobe", "probe"));
        if (enableLapisArmor)
            addShapelessRecipe(lapisHelmet, APItems.lapisHelmet, getItemStack("theoneprobe", "probe"));
        if (enableLavaArmor)
            addShapelessRecipe(lavaHelmet, APItems.lavaHelmet, getItemStack("theoneprobe", "probe"));
        if (enableObsidianArmor)
            addShapelessRecipe(obsidianHelmet, APItems.obsidianHelmet, getItemStack("theoneprobe", "probe"));
        if (enableRedstoneArmor)
            addShapelessRecipe(redstoneHelmet, APItems.redstoneHelmet, getItemStack("theoneprobe", "probe"));
        if (enableChickenArmor)
            addShapelessRecipe(chickenHelmet, APItems.chickenHelmet, getItemStack("theoneprobe", "probe"));
        if (enableSlimeArmor)
            addShapelessRecipe(slimeHelmet, APItems.slimeHelmet, getItemStack("theoneprobe", "probe"));
        if (enableArditeArmor)
            addShapelessRecipe(arditeHelmet, APItems.arditeHelmet, getItemStack("theoneprobe", "probe"));
        if (enableCobaltArmor)
            addShapelessRecipe(cobaltHelmet, APItems.cobaltHelmet, getItemStack("theoneprobe", "probe"));
        if (enableManyullynArmor)
            addShapelessRecipe(manyullynHelmet, APItems.manyullynHelmet, getItemStack("theoneprobe", "probe"));
        if (enablePigIronArmor)
            addShapelessRecipe(pigIronHelmet, APItems.pigIronHelmet, getItemStack("theoneprobe", "probe"));
        if (enableKnightSlimeArmor)
            addShapelessRecipe(knightSlimeHelmet, APItems.knightSlimeHelmet, getItemStack("theoneprobe", "probe"));
    }

    public static void addShapedRecipes() {
        addShapedRecipe(getItemStack(ModBlocks.blockLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModItems.lavaCrystal, 0));
        addShapedRecipe(getItemStack(ModBlocks.blockInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModItems.lavaCrystal, 1));

        addShapedRecipe(getItemStack(ModBlocks.blockCompressedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModBlocks.blockLavaCrystal));
        addShapedRecipe(getItemStack(ModBlocks.blockCompressedInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModBlocks.blockInfusedLavaCrystal));

        addRecipe(new ItemStack(lavaNetherBrick, 4),
                " N ",
                "NLN",
                " N ",
                'L', LAVA_BUCKET,
                'N', Blocks.NETHER_BRICK);
        addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.benches[0]),
                "LCL",
                "OTO",
                "O O",
                'T', "workbench",
                'O', "blockCoal",
                'L', "gemLapis",
                'C', "gemLavaCrystal"));
        for (int i = 0; i < colors.length; i++) {
            addRecipeCastle(stoneBricks[i], colors[i]);
            addRecipeCastleCorner(stoneBrickCorners[i], colors[i]);
            addRecipeCastleTower(stoneBrickTowers[i], colors[i]);
            addRecipeCastleWall(stonebrickWalls[i], colors[i]);
            addRecipeStoneBrick(Blocks.STONEBRICK, colors[i]);
        }
        if (enableElytraRecipe)
            addRecipe(new ItemStack(ELYTRA, 1), "ESE", "SNS", "EEE", 'E', new ItemStack(materials, 1, 3), 'S', STRING, 'N', NETHER_STAR);
        if (enableChainArmorRecipes) {
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_HELMET, 1), "   ", "CCC", "C C", 'C', "chainmail"));
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_HELMET, 1), "CCC", "C C", "   ", 'C', "chainmail"));
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_CHESTPLATE, 1), "C C", "CCC", "CCC", 'C', "chainmail"));
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_LEGGINGS, 1), "CCC", "C C", "C C", 'C', "chainmail"));
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_BOOTS, 1), "   ", "C C", "C C", 'C', "chainmail"));
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_BOOTS, 1), "C C", "C C", "   ", 'C', "chainmail"));
        }
        addRecipe(new ShapedOreRecipe(new ItemStack(materials, 12, 0), "SS ", "S S", " SS", 'S', "ingotIron"));
        addRecipe(new ShapedOreRecipe(new ItemStack(compressedObsidian, 1), "OOO", "OOO", "OOO", 'O', "obsidian"));
        if (enableRedstoneAppleRecipes) {
            addRecipe(new ShapedOreRecipe(new ItemStack(redstoneApple, 1), "RRR", "RAR", "RRR", 'R', "dustRedstone", 'A', APPLE));
            addRecipe(new ShapedOreRecipe(new ItemStack(redstoneApple, 1, 1), "BBB", "BAB", "BBB", 'B', "blockRedstone", 'A', APPLE));
        }
    }

    public static void addShapelessRecipes() {
        addShapelessRecipe(getItemStack(ModItems.lavaCrystal, 9, 0), ModBlocks.blockLavaCrystal);
        addShapelessRecipe(getItemStack(ModItems.lavaCrystal, 9, 1), ModBlocks.blockInfusedLavaCrystal);
        addShapelessRecipe(new ItemStack(nbtItem, 1), STICK, GLOWSTONE_DUST);
        addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN, 9), compressedObsidian);
        addShapelessRecipe(new ItemStack(bookInfo), BOOK, COAL);
        addShapelessRecipe(new ItemStack(lavaCactus, 1), Blocks.CACTUS, new ItemStack(lavaCrystal, 1, 1));
    }

    public static void addRecipeCastleCorner(Block block, String color) {
        addRecipe(new ShapedOreRecipe(new ItemStack(block, 1), "   ", "  S", " SS", 'S', "stonebrick" + color));
        addRecipe(new ShapedOreRecipe(new ItemStack(block, 1), "   ", "S  ", "SS ", 'S', "stonebrick" + color));
        addRecipe(new ShapedOreRecipe(new ItemStack(block, 1), "  S", " SS", "   ", 'S', "stonebrick" + color));
        addRecipe(new ShapedOreRecipe(new ItemStack(block, 1), "S  ", "SS ", "   ", 'S', "stonebrick" + color));
    }

    public static void addRecipeCastleTower(Block block, String color) {
        addRecipe(new ShapedOreRecipe(new ItemStack(block, 1), "   ", "S S", "SSS", 'S', "stonebrick" + color));
        addRecipe(new ShapedOreRecipe(new ItemStack(block, 1), "S S", "SSS", "   ", 'S', "stonebrick" + color));
    }

    public static void addRecipeCastleWall(Block block, String color) {
        addRecipe(new ShapedOreRecipe(new ItemStack(block, 6), "   ", "SSS", "SSS", 'S', "stonebrick" + color));
        addRecipe(new ShapedOreRecipe(new ItemStack(block, 6), "SSS", "SSS", "   ", 'S', "stonebrick" + color));
    }

    public static void addRecipeCastle(Block block, String color) {
        addRecipe(new ShapelessOreRecipe(new ItemStack(block, 3), "stonebrick" + color + "Corner"));
        addRecipe(new ShapelessOreRecipe(new ItemStack(block, 5), "stonebrick" + color + "Tower"));
        addRecipe(new ShapelessOreRecipe(new ItemStack(block, 1), "stonebrick" + color + "Wall"));
        addRecipe(new ShapedOreRecipe(new ItemStack(block, 1), " S ", "SCS", " S ", 'S', "stonebrick", 'C', "dye" + color));
    }

    public static void addRecipeStoneBrick(Block block, String color) {
        addRecipe(new ShapelessOreRecipe(new ItemStack(block, 1), "stonebrick" + color));
    }
}
