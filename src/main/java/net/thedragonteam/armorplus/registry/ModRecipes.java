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
import static net.minecraftforge.fml.common.registry.GameRegistry.addRecipe;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapelessRecipe;
import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.registry.ModOreDicts.colors;
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
        ItemStack coalHelmet = new ItemStack(ModItems.coal[0], 1);
        coalHelmet.setTagCompound(nbttagc);
        ItemStack emeraldHelmet = new ItemStack(ModItems.emerald[0], 1);
        emeraldHelmet.setTagCompound(nbttagc);
        ItemStack lapisHelmet = new ItemStack(ModItems.lapis[0], 1);
        lapisHelmet.setTagCompound(nbttagc);
        ItemStack lavaHelmet = new ItemStack(ModItems.lava[0], 1);
        lavaHelmet.setTagCompound(nbttagc);
        ItemStack obsidianHelmet = new ItemStack(ModItems.obsidian[0], 1);
        obsidianHelmet.setTagCompound(nbttagc);
        ItemStack redstoneHelmet = new ItemStack(ModItems.redstone[0], 1);
        redstoneHelmet.setTagCompound(nbttagc);
        ItemStack chickenHelmet = new ItemStack(ModItems.chicken[0], 1);
        chickenHelmet.setTagCompound(nbttagc);
        ItemStack slimeHelmet = new ItemStack(ModItems.slime[0], 1);
        slimeHelmet.setTagCompound(nbttagc);
        ItemStack arditeHelmet = new ItemStack(ModItems.ardite[0], 1);
        arditeHelmet.setTagCompound(nbttagc);
        ItemStack cobaltHelmet = new ItemStack(ModItems.cobalt[0], 1);
        cobaltHelmet.setTagCompound(nbttagc);
        ItemStack manyullynHelmet = new ItemStack(ModItems.manyullyn[0], 1);
        manyullynHelmet.setTagCompound(nbttagc);
        ItemStack pigIronHelmet = new ItemStack(ModItems.pigIron[0], 1);
        pigIronHelmet.setTagCompound(nbttagc);
        ItemStack knightSlimeHelmet = new ItemStack(ModItems.knightSlime[0], 1);
        knightSlimeHelmet.setTagCompound(nbttagc);
        ItemStack enderDragonHelmet = new ItemStack(ModItems.enderDragon[0], 1);
        enderDragonHelmet.setTagCompound(nbttagc);
        ItemStack guardianHelmet = new ItemStack(ModItems.guardian[0], 1);
        guardianHelmet.setTagCompound(nbttagc);
        ItemStack superStarHelmet = new ItemStack(ModItems.superStar[0], 1);
        superStarHelmet.setTagCompound(nbttagc);
        ItemStack theUltimateHelmet = new ItemStack(ModItems.theUltimate[0], 1);
        theUltimateHelmet.setTagCompound(nbttagc);

        if (enableEnderDragonArmor)
            addShapelessRecipe(enderDragonHelmet, ModItems.enderDragon[0], getItemStack("theoneprobe", "probe"));
        if (enableGuardianArmor)
            addShapelessRecipe(guardianHelmet, ModItems.guardian[0], getItemStack("theoneprobe", "probe"));
        if (enableSuperStarArmor)
            addShapelessRecipe(superStarHelmet, ModItems.superStar[0], getItemStack("theoneprobe", "probe"));
        if (enableTheUltimateArmor)
            addShapelessRecipe(theUltimateHelmet, ModItems.theUltimate[0], getItemStack("theoneprobe", "probe"));
        if (enableCoalArmor)
            addShapelessRecipe(coalHelmet, ModItems.coal[0], getItemStack("theoneprobe", "probe"));
        if (enableEmeraldArmor)
            addShapelessRecipe(emeraldHelmet, ModItems.emerald[0], getItemStack("theoneprobe", "probe"));
        if (enableLapisArmor)
            addShapelessRecipe(lapisHelmet, ModItems.lapis[0], getItemStack("theoneprobe", "probe"));
        if (enableLavaArmor)
            addShapelessRecipe(lavaHelmet, ModItems.lava[0], getItemStack("theoneprobe", "probe"));
        if (enableObsidianArmor)
            addShapelessRecipe(obsidianHelmet, ModItems.obsidian[0], getItemStack("theoneprobe", "probe"));
        if (enableRedstoneArmor)
            addShapelessRecipe(redstoneHelmet, ModItems.redstone[0], getItemStack("theoneprobe", "probe"));
        if (enableChickenArmor)
            addShapelessRecipe(chickenHelmet, ModItems.chicken[0], getItemStack("theoneprobe", "probe"));
        if (enableSlimeArmor)
            addShapelessRecipe(slimeHelmet, ModItems.slime[0], getItemStack("theoneprobe", "probe"));
        if (enableArditeArmor)
            addShapelessRecipe(arditeHelmet, ModItems.ardite[0], getItemStack("theoneprobe", "probe"));
        if (enableCobaltArmor)
            addShapelessRecipe(cobaltHelmet, ModItems.cobalt[0], getItemStack("theoneprobe", "probe"));
        if (enableManyullynArmor)
            addShapelessRecipe(manyullynHelmet, ModItems.manyullyn[0], getItemStack("theoneprobe", "probe"));
        if (enablePigIronArmor)
            addShapelessRecipe(pigIronHelmet, ModItems.pigIron[0], getItemStack("theoneprobe", "probe"));
        if (enableKnightSlimeArmor)
            addShapelessRecipe(knightSlimeHelmet, ModItems.knightSlime[0], getItemStack("theoneprobe", "probe"));
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
        for (int i = 0; i <= 6; i++) {
            addRecipeCastle(stoneBricks[i], colors[i]);
            addRecipeCastleCorner(stoneBrickCorners[i], colors[i]);
            addRecipeCastleTower(stoneBrickTowers[i], colors[i]);
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

