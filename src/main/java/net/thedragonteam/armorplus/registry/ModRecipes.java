/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import static net.minecraft.init.Items.*;
import static net.minecraft.item.Item.getByNameOrId;
import static net.minecraftforge.fml.common.registry.GameRegistry.addRecipe;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapelessRecipe;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.registry.ModOreDicts.colors;

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

        if (enableEnderDragonArmor)
            addShapelessRecipe(enderDragonHelmet, ModItems.enderDragonHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableGuardianArmor)
            addShapelessRecipe(guardianHelmet, ModItems.guardianHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableSuperStarArmor)
            addShapelessRecipe(superStarHelmet, ModItems.superStarHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableTheUltimateArmor)
            addShapelessRecipe(theUltimateHelmet, ModItems.theUltimateHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableCoalArmor)
            addShapelessRecipe(coalHelmet, ModItems.coalHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableEmeraldArmor)
            addShapelessRecipe(emeraldHelmet, ModItems.emeraldHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableLapisArmor)
            addShapelessRecipe(lapisHelmet, ModItems.lapisHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableLavaArmor)
            addShapelessRecipe(lavaHelmet, ModItems.lavaHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableObsidianArmor)
            addShapelessRecipe(obsidianHelmet, ModItems.obsidianHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableRedstoneArmor)
            addShapelessRecipe(redstoneHelmet, ModItems.redstoneHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableChickenArmor)
            addShapelessRecipe(chickenHelmet, ModItems.chickenHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableSlimeArmor)
            addShapelessRecipe(slimeHelmet, ModItems.slimeHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableArditeArmor)
            addShapelessRecipe(arditeHelmet, ModItems.arditeHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableCobaltArmor)
            addShapelessRecipe(cobaltHelmet, ModItems.cobaltHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableManyullynArmor)
            addShapelessRecipe(manyullynHelmet, ModItems.manyullynHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enablePigIronArmor)
            addShapelessRecipe(pigIronHelmet, ModItems.pigIronHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableKnightSlimeArmor)
            addShapelessRecipe(knightSlimeHelmet, ModItems.knightSlimeHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableSteelArmor)
            addShapelessRecipe(steelHelmet, ModItems.steelHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
        if (enableElectricalArmor)
            addShapelessRecipe(electricalHelmet, ModItems.electricalHelmet, new ItemStack(getByNameOrId("theoneprobe:probe"), 1));
    }

    public static void addShapedRecipes() {
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
            addRecipeStoneBrick(Blocks.STONEBRICK, colors[i]);
        }
        if (enableElytraRecipe)
            addRecipe(new ItemStack(ELYTRA, 1), "ESE", "SNS", "EEE", 'E', enderDragonScale, 'S', STRING, 'N', NETHER_STAR);
        if (enableChainArmorRecipes) {
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_HELMET, 1), "   ", "CCC", "C C", 'C', "chainmail"));
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_HELMET, 1), "CCC", "C C", "   ", 'C', "chainmail"));
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_CHESTPLATE, 1), "C C", "CCC", "CCC", 'C', "chainmail"));
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_LEGGINGS, 1), "CCC", "C C", "C C", 'C', "chainmail"));
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_BOOTS, 1), "   ", "C C", "C C", 'C', "chainmail"));
            addRecipe(new ShapedOreRecipe(new ItemStack(CHAINMAIL_BOOTS, 1), "C C", "C C", "   ", 'C', "chainmail"));
        }
        addRecipe(new ShapedOreRecipe(new ItemStack(chainmail), "SS ", "S S", " SS", 'S', "ingotIron"));
        addRecipe(new ShapedOreRecipe(new ItemStack(compressedObsidian, 1), "OOO", "OOO", "OOO", 'O', "obsidian"));
        if (enableRedstoneAppleRecipes) {
            addRecipe(new ShapedOreRecipe(new ItemStack(redstoneApple, 1), "RRR", "RAR", "RRR", 'R', "dustRedstone", 'A', APPLE));
            addRecipe(new ShapedOreRecipe(new ItemStack(redstoneApple, 1, 1), "BBB", "BAB", "BBB", 'B', "blockRedstone", 'A', APPLE));
        }
    }

    public static void addShapelessRecipes() {
        addShapelessRecipe(new ItemStack(nbtItem, 1), STICK, GLOWSTONE_DUST);
        addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN, 9), compressedObsidian);
        addShapelessRecipe(new ItemStack(armorPlusInfoBook), BOOK, COAL);
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

