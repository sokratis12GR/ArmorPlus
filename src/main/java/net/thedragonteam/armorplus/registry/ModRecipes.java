/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
        addEasyWeaponsRecipes();
        addExpertWeaponsRecipes();
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
        if (Loader.isModLoaded("tesla")) {
            GameRegistry.addShapelessRecipe(steelHelmet, ModItems.steelHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
            GameRegistry.addShapelessRecipe(electricalHelmet, ModItems.electricalHelmet, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        }
    }

    public static void addShapedRecipes() {

        ItemStack LAPIS_LAZULI = new ItemStack(Items.DYE, 1, 4);

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.advancedArmorForge), "LLL", "CAC", "CCC", 'C', "gemChargedLavaCrystal", 'L', "blockRedstone", 'A', "armorForge"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.armorForge), "LCL", "OTO", "OXO", 'T', "workbench", 'O', "blockCoal", 'L', "gemLapis", 'C', "gemLavaCrystal"));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.lavaNetherBrick, 4), "XNX", "NLN", "XNX", 'L', Items.LAVA_BUCKET, 'N', Blocks.NETHER_BRICK);

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFRod, 1), "XTX", "TST", "XTX", 'T', "dustRedstone", 'S', Items.STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFAxe, 1), "TTX", "TSX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemRFRod));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFShovel, 1), "XTX", "XSX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemRFRod));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFHoe, 1), "TTX", "XSX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemRFRod));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFHoe, 1), "XTT", "XSX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemRFRod));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFSword, 1), "XTX", "XTX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemRFRod));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRFPickaxe, 1), "TTT", "XSX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemRFRod));

        if (Loader.isModLoaded("tesla")) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaRod, 1), "XTX", "TST", "XTX", 'T', "gemLapis", 'S', Items.STICK));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaShovel, 1), "XTX", "XSX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemTeslaRod));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaHoe, 1), "TTX", "XSX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemTeslaRod));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaHoe, 1), "XTT", "XSX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemTeslaRod));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaSword, 1), "XTX", "XTX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemTeslaRod));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaAxe, 1), "TTX", "TSX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemTeslaRod));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTeslaPickaxe, 1), "TTT", "XSX", "XSX", 'T', "ingotSteel", 'S', ModItems.itemTeslaRod));
        }

        if (recipes == 0 && enableArrowRecipes) {
            GameRegistry.addRecipe(new ItemStack(ModItems.coalArrow, 2), "CCC", "CAC", "CCC", 'C', Items.COAL, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.lapisArrow, 2), "LLL", "LAL", "LLL", 'L', LAPIS_LAZULI, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.redstoneArrow, 2), "RRR", "RAR", "RRR", 'R', Items.REDSTONE, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.lavaArrow, 2), "LLL", "LAL", "LLL", 'L', new ItemStack(ModItems.lavaCrystal, 1), 'A', Items.ARROW);
        }
        if (recipes == 1 && enableArrowRecipes) {
            GameRegistry.addRecipe(new ItemStack(ModItems.coalArrow, 2), "CCC", "CAC", "CCC", 'C', Blocks.COAL_BLOCK, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.lapisArrow, 2), "LLL", "LAL", "LLL", 'L', Blocks.LAPIS_BLOCK, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.redstoneArrow, 2), "RRR", "RAR", "RRR", 'R', Blocks.REDSTONE_BLOCK, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.lavaArrow, 2), "LLL", "LAL", "LLL", 'L', new ItemStack(ModItems.lavaCrystal, 1, 1), 'A', Items.ARROW);
        }
        if (enableElytraRecipe)
            GameRegistry.addRecipe(new ItemStack(Items.ELYTRA, 1), "ESE", "SNS", "EEE", 'E', ModItems.enderDragonScale, 'S', Items.STRING, 'N', Items.NETHER_STAR);
        if (enableChainArmorRecipes) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "XXX", "CCC", "CXC", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "CCC", "CXC", "XXX", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "XXX", "CXC", "CXC", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "CXC", "CXC", "XXX", 'C', "chainmail"));
        }
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chainmail, 12), "SSX", "SXS", "XSS", 'S', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compressedObsidian, 1), "OOO", "OOO", "OOO", 'O', "obsidian"));

        if (enableRedstoneAppleRecipes) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneApple, 1), "RRR", "RAR", "RRR", 'R', "dustRedstone", 'A', Items.APPLE));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneApple, 1, 1), "BBB", "BAB", "BBB", 'B', "blockRedstone", 'A', Items.APPLE));
        }
        if (Loader.isModLoaded("tesla")) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.steelBlock, 1), "OOO", "OOO", "OOO", 'O', "ingotSteel"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.electricalBlock, 1), "OOO", "OOO", "OOO", 'O', "ingotElectrical"));
        }
    }

    public static void addShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.nbtItem, 1), Items.STICK, Items.GLOWSTONE_DUST);
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN, 9), ModBlocks.compressedObsidian);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.armorPlusInfoBook, 1), Items.BOOK, Items.COAL);
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.lavaCactus, 1), Blocks.CACTUS, new ItemStack(ModItems.lavaCrystal, 1, 1));
    }

    public static void addEasyWeaponsRecipes() {
        ItemStack LAPIS_LAZULI = new ItemStack(Items.DYE, 1, 4);
        if (enableSwordsRecipes && recipes == 0) {
            GameRegistry.addRecipe(new ItemStack(ModItems.coalSword, 1), "XCX", "XCX", "XSX", 'C', Items.COAL, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.lapisSword, 1), "XLX", "XLX", "XSX", 'L', LAPIS_LAZULI, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.redstoneSword, 1), "XRX", "XRX", "XSX", 'R', Items.REDSTONE, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.emeraldSword, 1), "XEX", "XEX", "XSX", 'E', Items.EMERALD, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.obsidianSword, 1), "XOX", "XOX", "XSX", 'O', Blocks.OBSIDIAN, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.guardianSword, 1), "XGX", "XGX", "XSX", 'G', ModItems.guardianScale, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.enderDragonSword, 1), "XEX", "XEX", "XSX", 'E', ModItems.enderDragonScale, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.superStarSword, 1), "XWX", "XWX", "XSX", 'W', ModItems.witherBone, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.lavaSword, 1), "XLX", "XLX", "XSX", 'L', new ItemStack(ModItems.lavaCrystal, 1), 'S', Items.STICK);
        }
        if (enableBattleAxesRecipes && recipes == 0) {
            GameRegistry.addRecipe(new ItemStack(ModItems.coalBattleAxe, 1), "CXC", "CSC", "XSX", 'C', Items.COAL, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.lapisBattleAxe, 1), "LXL", "LSL", "XSX", 'L', LAPIS_LAZULI, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.redstoneBattleAxe, 1), "RXR", "RSR", "XSX", 'R', Items.REDSTONE, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.emeraldBattleAxe, 1), "EXE", "ESE", "XSX", 'E', Items.EMERALD, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.obsidianBattleAxe, 1), "OXO", "OSO", "XSX", 'O', Blocks.OBSIDIAN, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.guardianBattleAxe, 1), "GXG", "GSG", "XSX", 'G', ModItems.guardianScale, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.enderDragonBattleAxe, 1), "EXE", "ESE", "XSX", 'E', ModItems.enderDragonScale, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.superStarBattleAxe, 1), "WXW", "WSW", "XSX", 'W', ModItems.witherBone, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.lavaBattleAxe, 1), "LXL", "LSL", "XSX", 'L', new ItemStack(ModItems.lavaCrystal, 1), 'S', Items.STICK);
        }
        if (enableBowsRecipes && recipes == 0) {
            GameRegistry.addRecipe(new ItemStack(ModItems.coalBow, 1), "XCS", "CXS", "XCS", 'C', Items.COAL, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.coalBow, 1), "SCX", "SXC", "SCX", 'C', Items.COAL, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.lapisBow, 1), "XLS", "LXS", "XLS", 'L', LAPIS_LAZULI, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.lapisBow, 1), "SLX", "SXL", "SLX", 'L', LAPIS_LAZULI, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.redstoneBow, 1), "XRS", "RXS", "XRS", 'R', Items.REDSTONE, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.redstoneBow, 1), "SRX", "SXR", "SRX", 'R', Items.REDSTONE, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.emeraldBow, 1), "XES", "EXS", "XES", 'E', Items.EMERALD, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.emeraldBow, 1), "SEX", "SXE", "SEX", 'E', Items.EMERALD, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.obsidianBow, 1), "XOS", "OXS", "XOS", 'O', Blocks.OBSIDIAN, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.obsidianBow, 1), "SOX", "SXO", "SOX", 'O', Blocks.OBSIDIAN, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.guardianBow, 1), "XGS", "GXS", "XGS", 'G', ModItems.guardianScale, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.guardianBow, 1), "SGX", "SXG", "SGX", 'G', ModItems.guardianScale, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.enderDragonBow, 1), "XES", "EXS", "XES", 'E', ModItems.enderDragonScale, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.enderDragonBow, 1), "SEX", "SXE", "SEX", 'E', ModItems.enderDragonScale, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.superStarBow, 1), "XWS", "WXS", "XWS", 'W', ModItems.witherBone, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.superStarBow, 1), "SWX", "SXW", "SWX", 'W', ModItems.witherBone, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.lavaBow, 1), "SLX", "SXL", "SLX", 'L', new ItemStack(ModItems.lavaCrystal, 1), 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.lavaBow, 1), "XLS", "LXS", "XLS", 'L', new ItemStack(ModItems.lavaCrystal, 1), 'S', Items.STRING);

        }
    }

    public static void addExpertWeaponsRecipes() {
        if (enableSwordsRecipes && recipes == 1) {
            GameRegistry.addRecipe(new ItemStack(ModItems.coalSword, 1), "XCX", "XCX", "XSX", 'C', Blocks.COAL_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.lapisSword, 1), "XLX", "XLX", "XSX", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.redstoneSword, 1), "XRX", "XRX", "XSX", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.emeraldSword, 1), "XEX", "XEX", "XSX", 'E', Blocks.EMERALD_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.obsidianSword, 1), "XOX", "XOX", "XSX", 'O', ModBlocks.compressedObsidian, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.guardianSword, 1), "XGX", "XGX", "XSX", 'G', ModItems.guardianScale, 'S', Items.PRISMARINE_SHARD);
            GameRegistry.addRecipe(new ItemStack(ModItems.enderDragonSword, 1), "XEX", "XEX", "XSX", 'E', ModItems.enderDragonScale, 'S', Items.DRAGON_BREATH);
            GameRegistry.addRecipe(new ItemStack(ModItems.superStarSword, 1), "XWX", "XWX", "XSX", 'W', ModItems.witherBone, 'S', Items.NETHER_STAR);
            GameRegistry.addRecipe(new ItemStack(ModItems.lavaSword, 1), "XLX", "XLX", "XSX", 'L', new ItemStack(ModItems.lavaCrystal, 1, 1), 'S', Items.STICK);
        }
        if (enableBattleAxesRecipes && recipes == 1) {
            GameRegistry.addRecipe(new ItemStack(ModItems.coalBattleAxe, 1), "CXC", "CSC", "XSX", 'C', Blocks.COAL_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.lapisBattleAxe, 1), "LXL", "LSL", "XSX", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.redstoneBattleAxe, 1), "RXR", "RSR", "XSX", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.emeraldBattleAxe, 1), "EXE", "ESE", "XSX", 'E', Blocks.EMERALD_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.obsidianBattleAxe, 1), "OXO", "OSO", "XSX", 'O', ModBlocks.compressedObsidian, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.guardianBattleAxe, 1), "GXG", "GSG", "XSX", 'G', ModItems.guardianScale, 'S', Items.PRISMARINE_SHARD);
            GameRegistry.addRecipe(new ItemStack(ModItems.enderDragonBattleAxe, 1), "EXE", "ESE", "XSX", 'E', ModItems.enderDragonScale, 'S', Items.DRAGON_BREATH);
            GameRegistry.addRecipe(new ItemStack(ModItems.superStarBattleAxe, 1), "WXW", "WSW", "XSX", 'W', ModItems.witherBone, 'S', Items.NETHER_STAR);
            GameRegistry.addRecipe(new ItemStack(ModItems.lavaBattleAxe, 1), "LXL", "LSL", "XSX", 'L', new ItemStack(ModItems.lavaCrystal, 1, 1), 'S', Items.STICK);
        }
        if (enableBowsRecipes && recipes == 1) {
            GameRegistry.addRecipe(new ItemStack(ModItems.coalBow, 1), "XCS", "CXS", "XCS", 'C', Blocks.COAL_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.coalBow, 1), "SCX", "SXC", "SCX", 'C', Blocks.COAL_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.lapisBow, 1), "XLS", "LXS", "XLS", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.lapisBow, 1), "SLX", "SXL", "SLX", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.redstoneBow, 1), "XRS", "RXS", "XRS", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.redstoneBow, 1), "SRX", "SXR", "SRX", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.emeraldBow, 1), "XES", "EXS", "XES", 'E', Blocks.EMERALD_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.emeraldBow, 1), "SEX", "SXE", "SEX", 'E', Blocks.EMERALD_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.obsidianBow, 1), "XOS", "OXS", "XOS", 'O', ModBlocks.compressedObsidian, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.obsidianBow, 1), "SOX", "SXO", "SOX", 'O', ModBlocks.compressedObsidian, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.guardianBow, 1), "XGS", "GXS", "XGS", 'G', ModItems.guardianScale, 'S', Items.PRISMARINE_SHARD);
            GameRegistry.addRecipe(new ItemStack(ModItems.guardianBow, 1), "SGX", "SXG", "SGX", 'G', ModItems.guardianScale, 'S', Items.PRISMARINE_SHARD);
            GameRegistry.addRecipe(new ItemStack(ModItems.enderDragonBow, 1), "XES", "DXS", "XES", 'E', ModItems.enderDragonScale, 'S', Items.STRING, 'D', Items.DRAGON_BREATH);
            GameRegistry.addRecipe(new ItemStack(ModItems.enderDragonBow, 1), "SEX", "SXD", "SEX", 'E', ModItems.enderDragonScale, 'S', Items.STRING, 'D', Items.DRAGON_BREATH);
            GameRegistry.addRecipe(new ItemStack(ModItems.superStarBow, 1), "XWS", "NXS", "XWS", 'W', ModItems.witherBone, 'S', Items.STRING, 'N', new ItemStack(Items.SKULL, 1, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.superStarBow, 1), "SWX", "SXN", "SWX", 'W', ModItems.witherBone, 'S', Items.STRING, 'N', new ItemStack(Items.SKULL, 1, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.lavaBow, 1), "SLX", "SXL", "SLX", 'L', new ItemStack(ModItems.lavaCrystal, 1, 1), 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.lavaBow, 1), "XLS", "LXS", "XLS", 'L', new ItemStack(ModItems.lavaCrystal, 1, 1), 'S', Items.STRING);
        }
    }
}
