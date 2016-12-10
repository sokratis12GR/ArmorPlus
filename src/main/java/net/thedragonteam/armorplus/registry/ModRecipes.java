/*
 * Copyright (c) TheDragonTeam 2016.
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
import static net.minecraftforge.fml.common.registry.GameRegistry.addRecipe;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapelessRecipe;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.ItemStackUtils.getItemStack;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus created by sokratis12GR on 6/20/2016 6:44 PM.
 * - TheDragonTeam
 */
public class ModRecipes {

    public static void init() {
        addShapedRecipes();
        addShapelessRecipes();
        if (Loader.isModLoaded("theoneprobe"))
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
        ItemStack enderDragonHelmet = new ItemStack(ModItems.enderDragonHelmet, 1);
        enderDragonHelmet.setTagCompound(nbttagc);
        ItemStack guardianHelmet = new ItemStack(ModItems.guardianHelmet, 1);
        guardianHelmet.setTagCompound(nbttagc);
        ItemStack superStarHelmet = new ItemStack(ModItems.superStarHelmet, 1);
        superStarHelmet.setTagCompound(nbttagc);
        ItemStack theUltimateHelmet = new ItemStack(ModItems.theUltimateHelmet, 1);
        theUltimateHelmet.setTagCompound(nbttagc);

        if (enableEnderDragonArmor)
            addShapelessRecipe(enderDragonHelmet, ModItems.enderDragonHelmet, getItemStack("theoneprobe", "probe"));
        if (enableGuardianArmor)
            addShapelessRecipe(guardianHelmet, ModItems.guardianHelmet, getItemStack("theoneprobe", "probe"));
        if (enableSuperStarArmor)
            addShapelessRecipe(superStarHelmet, ModItems.superStarHelmet, getItemStack("theoneprobe", "probe"));
        if (enableTheUltimateArmor)
            addShapelessRecipe(theUltimateHelmet, ModItems.theUltimateHelmet, getItemStack("theoneprobe", "probe"));
        if (enableCoalArmor)
            addShapelessRecipe(coalHelmet, ModItems.coalHelmet, getItemStack("theoneprobe", "probe"));
        if (enableEmeraldArmor)
            addShapelessRecipe(emeraldHelmet, ModItems.emeraldHelmet, getItemStack("theoneprobe", "probe"));
        if (enableLapisArmor)
            addShapelessRecipe(lapisHelmet, ModItems.lapisHelmet, getItemStack("theoneprobe", "probe"));
        if (enableLavaArmor)
            addShapelessRecipe(lavaHelmet, ModItems.lavaHelmet, getItemStack("theoneprobe", "probe"));
        if (enableObsidianArmor)
            addShapelessRecipe(obsidianHelmet, ModItems.obsidianHelmet, getItemStack("theoneprobe", "probe"));
        if (enableRedstoneArmor)
            addShapelessRecipe(redstoneHelmet, ModItems.redstoneHelmet, getItemStack("theoneprobe", "probe"));
        if (enableChickenArmor)
            addShapelessRecipe(chickenHelmet, ModItems.chickenHelmet, getItemStack("theoneprobe", "probe"));
        if (enableSlimeArmor)
            addShapelessRecipe(slimeHelmet, ModItems.slimeHelmet, getItemStack("theoneprobe", "probe"));
        if (enableArditeArmor)
            addShapelessRecipe(arditeHelmet, ModItems.arditeHelmet, getItemStack("theoneprobe", "probe"));
        if (enableCobaltArmor)
            addShapelessRecipe(cobaltHelmet, ModItems.cobaltHelmet, getItemStack("theoneprobe", "probe"));
        if (enableManyullynArmor)
            addShapelessRecipe(manyullynHelmet, ModItems.manyullynHelmet, getItemStack("theoneprobe", "probe"));
        if (enablePigIronArmor)
            addShapelessRecipe(pigIronHelmet, ModItems.pigIronHelmet, getItemStack("theoneprobe", "probe"));
        if (enableKnightSlimeArmor)
            addShapelessRecipe(knightSlimeHelmet, ModItems.knightSlimeHelmet, getItemStack("theoneprobe", "probe"));
    }

    public static void addShapedRecipes() {
        addRecipe(new ItemStack(lavaNetherBrick, 4),
                " N ",
                "NLN",
                " N ",
                'L', LAVA_BUCKET,
                'N', Blocks.NETHER_BRICK);
        addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.arpWorkbench),
                "LCL",
                "OTO",
                "O O",
                'T', "workbench",
                'O', "blockCoal",
                'L', "gemLapis",
                'C', "gemLavaCrystal"));
        addRecipeCastle(whiteStoneBrick, "White");
        addRecipeCastle(redStoneBrick, "Red");
        addRecipeCastle(blackStoneBrick, "Black");
        addRecipeCastle(blueStoneBrick, "Blue");
        addRecipeCastle(greenStoneBrick, "Green");
        addRecipeCastle(yellowStoneBrick, "Yellow");
        addRecipeCastle(purpleStoneBrick, "Purple");
        addRecipeCastleCorner(whiteStoneBrickCorner, "White");
        addRecipeCastleCorner(redStoneBrickCorner, "Red");
        addRecipeCastleCorner(blackStoneBrickCorner, "Black");
        addRecipeCastleCorner(blueStoneBrickCorner, "Blue");
        addRecipeCastleCorner(greenStoneBrickCorner, "Green");
        addRecipeCastleCorner(yellowStoneBrickCorner, "Yellow");
        addRecipeCastleCorner(purpleStoneBrickCorner, "Purple");
        addRecipeCastleTower(whiteStoneBrickTower, "White");
        addRecipeCastleTower(redStoneBrickTower, "Red");
        addRecipeCastleTower(blackStoneBrickTower, "Black");
        addRecipeCastleTower(blueStoneBrickTower, "Blue");
        addRecipeCastleTower(greenStoneBrickTower, "Green");
        addRecipeCastleTower(yellowStoneBrickTower, "Yellow");
        addRecipeCastleTower(purpleStoneBrickTower, "Purple");
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

    public static void addRecipeCastle(Block block, String color) {
        addRecipe(new ShapelessOreRecipe(new ItemStack(block, 3), "stonebrick" + color + "Corner"));
        addRecipe(new ShapelessOreRecipe(new ItemStack(block, 5), "stonebrick" + color + "Tower"));
        addRecipe(new ShapedOreRecipe(new ItemStack(block, 1), " S ", "SCS", " S ", 'S', "stonebrick", 'C', "dye" + color));
    }
}

