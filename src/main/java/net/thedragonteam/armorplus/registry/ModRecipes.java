/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
        ItemStack COAL_HELMET = new ItemStack(ModItems.COAL_HELMET, 1);
        COAL_HELMET.setTagCompound(nbttagc);
        ItemStack EMERALD_HELMET = new ItemStack(ModItems.EMERALD_HELMET, 1);
        EMERALD_HELMET.setTagCompound(nbttagc);
        ItemStack LAPIS_HELMET = new ItemStack(ModItems.LAPIS_HELMET, 1);
        LAPIS_HELMET.setTagCompound(nbttagc);
        ItemStack LAVA_HELMET = new ItemStack(ModItems.LAVA_HELMET, 1);
        LAVA_HELMET.setTagCompound(nbttagc);
        ItemStack OBSIDIAN_HELMET = new ItemStack(ModItems.OBSIDIAN_HELMET);
        OBSIDIAN_HELMET.setTagCompound(nbttagc);
        ItemStack REDSTONE_HELMET = new ItemStack(ModItems.REDSTONE_HELMET, 1);
        REDSTONE_HELMET.setTagCompound(nbttagc);
        ItemStack RC_HELMET = new ItemStack(ModItems.RC_HELMET, 1);
        RC_HELMET.setTagCompound(nbttagc);
        ItemStack RD_HELMET = new ItemStack(ModItems.RD_HELMET, 1);
        RD_HELMET.setTagCompound(nbttagc);
        ItemStack RG_HELMET = new ItemStack(ModItems.RG_HELMET, 1);
        RG_HELMET.setTagCompound(nbttagc);
        ItemStack RI_HELMET = new ItemStack(ModItems.RI_HELMET, 1);
        RI_HELMET.setTagCompound(nbttagc);
        ItemStack CHICKEN_HELMET = new ItemStack(ModItems.CHICKEN_HELMET, 1);
        CHICKEN_HELMET.setTagCompound(nbttagc);
        ItemStack SLIME_HELMET = new ItemStack(ModItems.SLIME_HELMET, 1);
        SLIME_HELMET.setTagCompound(nbttagc);
        ItemStack ARDITE_HELMET = new ItemStack(ModItems.ARDITE_HELMET, 1);
        ARDITE_HELMET.setTagCompound(nbttagc);
        ItemStack COBALT_HELMET = new ItemStack(ModItems.COBALT_HELMET, 1);
        COBALT_HELMET.setTagCompound(nbttagc);
        ItemStack MANYULLYN_HELMET = new ItemStack(ModItems.MANYULLYN_HELMET, 1);
        MANYULLYN_HELMET.setTagCompound(nbttagc);
        ItemStack PIG_IRON_HELMET = new ItemStack(ModItems.PIG_IRON_HELMET, 1);
        PIG_IRON_HELMET.setTagCompound(nbttagc);
        ItemStack KNIGHT_SLIME_HELMET = new ItemStack(ModItems.KNIGHT_SLIME_HELMET, 1);
        KNIGHT_SLIME_HELMET.setTagCompound(nbttagc);
        ItemStack STEEL_HELMET = new ItemStack(ModItems.STEEL_HELMET, 1);
        STEEL_HELMET.setTagCompound(nbttagc);
        ItemStack ELECTRICAL_HELMET = new ItemStack(ModItems.ELECTRICAL_HELMET, 1);
        ELECTRICAL_HELMET.setTagCompound(nbttagc);
        ItemStack ENDER_DRAGON_HELMET = new ItemStack(ModItems.ENDER_DRAGON_HELMET, 1);
        ENDER_DRAGON_HELMET.setTagCompound(nbttagc);
        ItemStack GUARDIAN_HELMET = new ItemStack(ModItems.GUARDIAN_HELMET, 1);
        GUARDIAN_HELMET.setTagCompound(nbttagc);
        ItemStack SUPER_STAR_HELMET = new ItemStack(ModItems.SUPER_STAR_HELMET, 1);
        SUPER_STAR_HELMET.setTagCompound(nbttagc);
        ItemStack THE_ULTIMATE_HELMET = new ItemStack(ModItems.THE_ULTIMATE_HELMET, 1);
        THE_ULTIMATE_HELMET.setTagCompound(nbttagc);

        GameRegistry.addShapelessRecipe(ENDER_DRAGON_HELMET, ModItems.ENDER_DRAGON_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(GUARDIAN_HELMET, ModItems.GUARDIAN_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(SUPER_STAR_HELMET, ModItems.SUPER_STAR_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(THE_ULTIMATE_HELMET, ModItems.THE_ULTIMATE_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(COAL_HELMET, ModItems.COAL_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(EMERALD_HELMET, ModItems.EMERALD_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(LAPIS_HELMET, ModItems.LAPIS_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(LAVA_HELMET, ModItems.LAVA_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(OBSIDIAN_HELMET, ModItems.OBSIDIAN_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(REDSTONE_HELMET, ModItems.REDSTONE_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(RC_HELMET, ModItems.RC_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(RD_HELMET, ModItems.RD_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(RG_HELMET, ModItems.RG_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(RI_HELMET, ModItems.RI_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(CHICKEN_HELMET, ModItems.CHICKEN_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(SLIME_HELMET, ModItems.SLIME_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(ARDITE_HELMET, ModItems.ARDITE_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(COBALT_HELMET, ModItems.COBALT_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(MANYULLYN_HELMET, ModItems.MANYULLYN_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(PIG_IRON_HELMET, ModItems.PIG_IRON_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(KNIGHT_SLIME_HELMET, ModItems.KNIGHT_SLIME_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(STEEL_HELMET, ModItems.STEEL_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));
        GameRegistry.addShapelessRecipe(ELECTRICAL_HELMET, ModItems.ELECTRICAL_HELMET, new ItemStack(Item.getByNameOrId("theoneprobe:probe"), 1));

    }

    public static void addShapedRecipes() {

        ItemStack LAPIS_LAZULI = new ItemStack(Items.DYE, 1, 4);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.ADVANCED_ARMOR_FORGE), "LLL", "CAC", "CCC", 'C', ModItems.LAVA_CRYSTAL, 'L', Blocks.REDSTONE_BLOCK, 'A', ModBlocks.ARMOR_FORGE);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.LAVA_NETHER_BRICK, 4), "XNX", "NLN", "XNX", 'L', Items.LAVA_BUCKET, 'N', Blocks.NETHER_BRICK);

        if (recipes == 0 && enableArrowRecipes) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_ARROW, 2), "CCC", "CAC", "CCC", 'C', Items.COAL, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_ARROW, 2), "LLL", "LAL", "LLL", 'L', LAPIS_LAZULI, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_ARROW, 2), "RRR", "RAR", "RRR", 'R', Items.REDSTONE, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_ARROW, 2), "LLL", "LAL", "LLL", 'L', ModItems.LAVA_CRYSTAL, 'A', Items.ARROW);
        }
        if (recipes == 1 && enableArrowRecipes) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_ARROW, 2), "CCC", "CAC", "CCC", 'C', Blocks.COAL_BLOCK, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_ARROW, 2), "LLL", "LAL", "LLL", 'L', Blocks.LAPIS_BLOCK, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_ARROW, 2), "RRR", "RAR", "RRR", 'R', Blocks.REDSTONE_BLOCK, 'A', Items.ARROW);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_ARROW, 2), "LLL", "LAL", "LLL", 'L', ModItems.LAVA_CRYSTAL, 'A', Items.ARROW);
        }
        if (enableElytraRecipe)
            GameRegistry.addRecipe(new ItemStack(Items.ELYTRA, 1), "ESE", "SNS", "EEE", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', Items.STRING, 'N', Items.NETHER_STAR);
        if (enableChainArmorRecipes) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "XXX", "CCC", "CXC", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "CCC", "CXC", "XXX", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "XXX", "CXC", "CXC", 'C', "chainmail"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "CXC", "CXC", "XXX", 'C', "chainmail"));
        }
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.CHAINMAIL, 12), "SSX", "SXS", "XSS", 'S', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN, 1), "OOO", "OOO", "OOO", 'O', "obsidian"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.ARMOR_FORGE), "LCL", "OTO", "OXO", 'T', "workbench", 'O', "blockCoal", 'L', "blockLapis", 'C', "gemLavaCrystal"));

        if (enableRedstoneAppleRecipes) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.REDSTONE_APPLE, 1), "RRR", "RAR", "RRR", 'R', "dustRedstone", 'A', Items.APPLE));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.REDSTONE_APPLE, 1, 1), "BBB", "BAB", "BBB", 'B', "blockRedstone", 'A', Items.APPLE));
        }
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.STEEL_BLOCK, 1), "OOO", "OOO", "OOO", 'O', "ingotSteel"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.ELECTRICAL_BLOCK, 1), "OOO", "OOO", "OOO", 'O', "ingotElectrical"));
    }

    public static void addShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN, 9), ModBlocks.COMPRESSED_OBSIDIAN);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.STEEL_INGOT, 9), ModBlocks.STEEL_BLOCK);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ELECTRICAL_INGOT, 9), ModBlocks.ELECTRICAL_BLOCK);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ARMORPLUS_INFO_BOOK, 1), Items.BOOK, Items.COAL);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.STEEL_INGOT, 1), new ItemStack(Items.COAL, 1, 1), Items.IRON_INGOT, ModItems.LAVA_CRYSTAL);
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.LAVA_CACTUS, 1), Blocks.CACTUS, ModItems.LAVA_CRYSTAL);
    }

    public static void addEasyWeaponsRecipes() {
        ItemStack LAPIS_LAZULI = new ItemStack(Items.DYE, 1, 4);
        if (enableSwordsRecipes && recipes == 0) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_SWORD, 1), "XCX", "XCX", "XSX", 'C', Items.COAL, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_SWORD, 1), "XLX", "XLX", "XSX", 'L', LAPIS_LAZULI, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_SWORD, 1), "XRX", "XRX", "XSX", 'R', Items.REDSTONE, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_SWORD, 1), "XEX", "XEX", "XSX", 'E', Items.EMERALD, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_SWORD, 1), "XOX", "XOX", "XSX", 'O', Blocks.OBSIDIAN, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_SWORD, 1), "XGX", "XGX", "XSX", 'G', ModItems.GUARDIAN_SCALE, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_SWORD, 1), "XEX", "XEX", "XSX", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_SWORD, 1), "XWX", "XWX", "XSX", 'W', ModItems.WITHER_BONE, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_SWORD, 1), "XLX", "XLX", "XSX", 'L', ModItems.LAVA_CRYSTAL, 'S', Items.STICK);
        }
        if (enableBattleAxesRecipes && recipes == 0) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BATTLE_AXE, 1), "CXC", "CSC", "XSX", 'C', Items.COAL, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BATTLE_AXE, 1), "LXL", "LSL", "XSX", 'L', LAPIS_LAZULI, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BATTLE_AXE, 1), "RXR", "RSR", "XSX", 'R', Items.REDSTONE, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BATTLE_AXE, 1), "EXE", "ESE", "XSX", 'E', Items.EMERALD, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BATTLE_AXE, 1), "OXO", "OSO", "XSX", 'O', Blocks.OBSIDIAN, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BATTLE_AXE, 1), "GXG", "GSG", "XSX", 'G', ModItems.GUARDIAN_SCALE, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BATTLE_AXE, 1), "EXE", "ESE", "XSX", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BATTLE_AXE, 1), "WXW", "WSW", "XSX", 'W', ModItems.WITHER_BONE, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BATTLE_AXE, 1), "LXL", "LSL", "XSX", 'L', ModItems.LAVA_CRYSTAL, 'S', Items.STICK);
        }
        if (enableBowsRecipes && recipes == 0) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BOW, 1), "XCS", "CXS", "XCS", 'C', Items.COAL, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BOW, 1), "SCX", "SXC", "SCX", 'C', Items.COAL, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BOW, 1), "XLS", "LXS", "XLS", 'L', LAPIS_LAZULI, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BOW, 1), "SLX", "SXL", "SLX", 'L', LAPIS_LAZULI, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BOW, 1), "XRS", "RXS", "XRS", 'R', Items.REDSTONE, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BOW, 1), "SRX", "SXR", "SRX", 'R', Items.REDSTONE, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BOW, 1), "XES", "EXS", "XES", 'E', Items.EMERALD, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BOW, 1), "SEX", "SXE", "SEX", 'E', Items.EMERALD, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOW, 1), "XOS", "OXS", "XOS", 'O', Blocks.OBSIDIAN, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOW, 1), "SOX", "SXO", "SOX", 'O', Blocks.OBSIDIAN, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BOW, 1), "XGS", "GXS", "XGS", 'G', ModItems.GUARDIAN_SCALE, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BOW, 1), "SGX", "SXG", "SGX", 'G', ModItems.GUARDIAN_SCALE, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOW, 1), "XES", "EXS", "XES", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOW, 1), "SEX", "SXE", "SEX", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOW, 1), "XWS", "WXS", "XWS", 'W', ModItems.WITHER_BONE, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOW, 1), "SWX", "SXW", "SWX", 'W', ModItems.WITHER_BONE, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BOW, 1), "SLX", "SXL", "SLX", 'L', ModItems.LAVA_CRYSTAL, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BOW, 1), "XLS", "LXS", "XLS", 'L', ModItems.LAVA_CRYSTAL, 'S', Items.STRING);

        }
    }

    public static void addExpertWeaponsRecipes() {
        if (enableSwordsRecipes && recipes == 1) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_SWORD, 1), "XCX", "XCX", "XSX", 'C', Blocks.COAL_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_SWORD, 1), "XLX", "XLX", "XSX", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_SWORD, 1), "XRX", "XRX", "XSX", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_SWORD, 1), "XEX", "XEX", "XSX", 'E', Blocks.EMERALD_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_SWORD, 1), "XOX", "XOX", "XSX", 'O', ModBlocks.COMPRESSED_OBSIDIAN, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_SWORD, 1), "XGX", "XGX", "XSX", 'G', ModItems.GUARDIAN_SCALE, 'S', Items.PRISMARINE_SHARD);
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_SWORD, 1), "XEX", "XEX", "XSX", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', Items.DRAGON_BREATH);
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_SWORD, 1), "XWX", "XWX", "XSX", 'W', ModItems.WITHER_BONE, 'S', Items.NETHER_STAR);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_SWORD, 1), "XLX", "XLX", "XSX", 'L', ModItems.LAVA_CRYSTAL, 'S', Items.STICK);
        }
        if (enableBattleAxesRecipes && recipes == 1) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BATTLE_AXE, 1), "CXC", "CSC", "XSX", 'C', Blocks.COAL_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BATTLE_AXE, 1), "LXL", "LSL", "XSX", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BATTLE_AXE, 1), "RXR", "RSR", "XSX", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BATTLE_AXE, 1), "EXE", "ESE", "XSX", 'E', Blocks.EMERALD_BLOCK, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BATTLE_AXE, 1), "OXO", "OSO", "XSX", 'O', ModBlocks.COMPRESSED_OBSIDIAN, 'S', Items.STICK);
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BATTLE_AXE, 1), "GXG", "GSG", "XSX", 'G', ModItems.GUARDIAN_SCALE, 'S', Items.PRISMARINE_SHARD);
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BATTLE_AXE, 1), "EXE", "ESE", "XSX", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', Items.DRAGON_BREATH);
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BATTLE_AXE, 1), "WXW", "WSW", "XSX", 'W', ModItems.WITHER_BONE, 'S', Items.NETHER_STAR);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BATTLE_AXE, 1), "LXL", "LSL", "XSX", 'L', ModItems.LAVA_CRYSTAL, 'S', Items.STICK);
        }
        if (enableBowsRecipes && recipes == 1) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BOW, 1), "XCS", "CXS", "XCS", 'C', Blocks.COAL_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BOW, 1), "SCX", "SXC", "SCX", 'C', Blocks.COAL_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BOW, 1), "XLS", "LXS", "XLS", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BOW, 1), "SLX", "SXL", "SLX", 'L', Blocks.LAPIS_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BOW, 1), "XRS", "RXS", "XRS", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BOW, 1), "SRX", "SXR", "SRX", 'R', Blocks.REDSTONE_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BOW, 1), "XES", "EXS", "XES", 'E', Blocks.EMERALD_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BOW, 1), "SEX", "SXE", "SEX", 'E', Blocks.EMERALD_BLOCK, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOW, 1), "XOS", "OXS", "XOS", 'O', ModBlocks.COMPRESSED_OBSIDIAN, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOW, 1), "SOX", "SXO", "SOX", 'O', ModBlocks.COMPRESSED_OBSIDIAN, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BOW, 1), "XGS", "GXS", "XGS", 'G', ModItems.GUARDIAN_SCALE, 'S', Items.PRISMARINE_SHARD);
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BOW, 1), "SGX", "SXG", "SGX", 'G', ModItems.GUARDIAN_SCALE, 'S', Items.PRISMARINE_SHARD);
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOW, 1), "XES", "DXS", "XES", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', Items.STRING, 'D', Items.DRAGON_BREATH);
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOW, 1), "SEX", "SXD", "SEX", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', Items.STRING, 'D', Items.DRAGON_BREATH);
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOW, 1), "XWS", "NXS", "XWS", 'W', ModItems.WITHER_BONE, 'S', Items.STRING, 'N', new ItemStack(Items.SKULL, 1, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOW, 1), "SWX", "SXN", "SWX", 'W', ModItems.WITHER_BONE, 'S', Items.STRING, 'N', new ItemStack(Items.SKULL, 1, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BOW, 1), "SLX", "SXL", "SLX", 'L', ModItems.LAVA_CRYSTAL, 'S', Items.STRING);
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BOW, 1), "XLS", "LXS", "XLS", 'L', ModItems.LAVA_CRYSTAL, 'S', Items.STRING);
        }
    }
}
