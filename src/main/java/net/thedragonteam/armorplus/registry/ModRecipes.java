package net.thedragonteam.armorplus.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.thedragonteam.armorplus.ARPConfig;

import static net.thedragonteam.armorplus.ARPConfig.*;

/**
 * sokratis12GR.ArmorPlus.registry
 * ArmorPlus created by sokratis12GR on 6/20/2016 6:44 PM.
 */
public class ModRecipes {


    public static void init() {
        addShapedRecipes();
        addShapelessRecipes();
        addEasyWeaponsRecipes();
        addExpertWeaponsRecipes();
    }

    public static void addShapedRecipes() {
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
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.getByNameOrId("minecraft:dragon_egg"), 1), "EEE", "EGE", "EEE", 'E', "scaleEnderDragon", 'G', "egg"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.ARMOR_FORGE), "LCL", "OTO", "OXO", 'T', "workbench", 'O', "blockCoal", 'L', "blockLapis", 'C', "gemLavaCrystal"));

        if (enableRedstoneAppleRecipes) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.REDSTONE_APPLE, 1), "RRR", "RAR", "RRR", 'R', "dustRedstone", 'A', new ItemStack(Items.APPLE)));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.REDSTONE_APPLE, 1, 1), "BBB", "BAB", "BBB", 'B', "blockRedstone", 'A', new ItemStack(Items.APPLE)));
        }
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.STEEL_BLOCK, 1), "OOO", "OOO", "OOO", 'O', "ingotSteel"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.ELECTRICAL_BLOCK, 1), "OOO", "OOO", "OOO", 'O', "ingotElectrical"));
    }

    public static void addShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN, 9), new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.STEEL_INGOT, 9), new ItemStack(ModBlocks.STEEL_BLOCK));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ELECTRICAL_INGOT, 9), new ItemStack(ModBlocks.ELECTRICAL_BLOCK));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ARMORPLUS_INFO_BOOK, 1), new ItemStack(Items.BOOK), new ItemStack(Items.COAL));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.STEEL_INGOT, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.LAVA_CRYSTAL));
    }

    public static void addEasyWeaponsRecipes() {
        if (enableSwordsRecipes && recipes == 0) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_SWORD, 1), "XCX", "XCX", "XSX", 'C', new ItemStack(Items.COAL, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_SWORD, 1), "XLX", "XLX", "XSX", 'L', new ItemStack(Items.DYE, 1, 4), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_SWORD, 1), "XRX", "XRX", "XSX", 'R', new ItemStack(Items.REDSTONE, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_SWORD, 1), "XEX", "XEX", "XSX", 'E', new ItemStack(Items.EMERALD, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_SWORD, 1), "XOX", "XOX", "XSX", 'O', new ItemStack(Blocks.OBSIDIAN, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_SWORD, 1), "XGX", "XGX", "XSX", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_SWORD, 1), "XEX", "XEX", "XSX", 'E', new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_SWORD, 1), "XWX", "XWX", "XSX", 'W', new ItemStack(ModItems.WITHER_BONE, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_SWORD, 1), "XLX", "XLX", "XSX", 'L', new ItemStack(ModItems.LAVA_CRYSTAL, 1), 'S', new ItemStack(Items.STICK, 1));
        }
        if (enableBattleAxesRecipes && recipes == 0) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BATTLE_AXE, 1), "CXC", "CSC", "XSX", 'C', new ItemStack(Items.COAL, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BATTLE_AXE, 1), "LXL", "LSL", "XSX", 'L', new ItemStack(Items.DYE, 1, 4), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BATTLE_AXE, 1), "RXR", "RSR", "XSX", 'R', new ItemStack(Items.REDSTONE, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BATTLE_AXE, 1), "EXE", "ESE", "XSX", 'E', new ItemStack(Items.EMERALD, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BATTLE_AXE, 1), "OXO", "OSO", "XSX", 'O', new ItemStack(Blocks.OBSIDIAN, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BATTLE_AXE, 1), "GXG", "GSG", "XSX", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BATTLE_AXE, 1), "EXE", "ESE", "XSX", 'E', new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BATTLE_AXE, 1), "WXW", "WSW", "XSX", 'W', new ItemStack(ModItems.WITHER_BONE, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BATTLE_AXE, 1), "LXL", "LSL", "XSX", 'L', new ItemStack(ModItems.LAVA_CRYSTAL, 1), 'S', new ItemStack(Items.STICK, 1));
        }
        if (enableBowsRecipes && recipes == 0) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BOW, 1), "XCS", "CXS", "XCS", 'C', new ItemStack(Items.COAL, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BOW, 1), "SCX", "SXC", "SCX", 'C', new ItemStack(Items.COAL, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BOW, 1), "XLS", "LXS", "XLS", 'L', new ItemStack(Items.DYE, 1, 4), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BOW, 1), "SLX", "SXL", "SLX", 'L', new ItemStack(Items.DYE, 1, 4), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BOW, 1), "XRS", "RXS", "XRS", 'R', new ItemStack(Items.REDSTONE, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BOW, 1), "SRX", "SXR", "SRX", 'R', new ItemStack(Items.REDSTONE, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BOW, 1), "XES", "EXS", "XES", 'E', new ItemStack(Items.EMERALD, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BOW, 1), "SEX", "SXE", "SEX", 'E', new ItemStack(Items.EMERALD, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOW, 1), "XOS", "OXS", "XOS", 'O', new ItemStack(Blocks.OBSIDIAN, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOW, 1), "SOX", "SXO", "SOX", 'O', new ItemStack(Blocks.OBSIDIAN, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BOW, 1), "XGS", "GXS", "XGS", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BOW, 1), "SGX", "SXG", "SGX", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOW, 1), "XES", "EXS", "XES", 'E', new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOW, 1), "SEX", "SXE", "SEX", 'E', new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOW, 1), "XWS", "WXS", "XWS", 'W', new ItemStack(ModItems.WITHER_BONE, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOW, 1), "SWX", "SXW", "SWX", 'W', new ItemStack(ModItems.WITHER_BONE, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BOW, 1), "SLX", "SXL", "SLX", 'L', new ItemStack(ModItems.LAVA_CRYSTAL, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BOW, 1), "XLS", "LXS", "XLS", 'L', new ItemStack(ModItems.LAVA_CRYSTAL, 1), 'S', new ItemStack(Items.STRING, 1));

        }
    }

    public static void addExpertWeaponsRecipes() {
        if (enableSwordsRecipes && recipes == 1) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_SWORD, 1), "XCX", "XCX", "XSX", 'C', new ItemStack(Blocks.COAL_BLOCK, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_SWORD, 1), "XLX", "XLX", "XSX", 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_SWORD, 1), "XRX", "XRX", "XSX", 'R', new ItemStack(Blocks.REDSTONE_BLOCK, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_SWORD, 1), "XEX", "XEX", "XSX", 'E', new ItemStack(Blocks.EMERALD_BLOCK, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_SWORD, 1), "XOX", "XOX", "XSX", 'O', new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_SWORD, 1), "XGX", "XGX", "XSX", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'S', new ItemStack(Items.PRISMARINE_SHARD, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_SWORD, 1), "XEX", "XEX", "XSX", 'E', new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), 'S', new ItemStack(Items.DRAGON_BREATH, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_SWORD, 1), "XWX", "XWX", "XSX", 'W', new ItemStack(ModItems.WITHER_BONE, 1), 'S', new ItemStack(Items.NETHER_STAR, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_SWORD, 1), "XLX", "XLX", "XSX", 'L', new ItemStack(ModItems.LAVA_CRYSTAL, 1), 'S', new ItemStack(Items.STICK, 1));
        }
        if (enableBattleAxesRecipes && recipes == 1) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BATTLE_AXE, 1), "CXC", "CSC", "XSX", 'C', new ItemStack(Blocks.COAL_BLOCK, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BATTLE_AXE, 1), "LXL", "LSL", "XSX", 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BATTLE_AXE, 1), "RXR", "RSR", "XSX", 'R', new ItemStack(Blocks.REDSTONE_BLOCK, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BATTLE_AXE, 1), "EXE", "ESE", "XSX", 'E', new ItemStack(Blocks.EMERALD_BLOCK, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BATTLE_AXE, 1), "OXO", "OSO", "XSX", 'O', new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN, 1), 'S', new ItemStack(Items.STICK, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BATTLE_AXE, 1), "GXG", "GSG", "XSX", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'S', new ItemStack(Items.PRISMARINE_SHARD, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BATTLE_AXE, 1), "EXE", "ESE", "XSX", 'E', new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), 'S', new ItemStack(Items.DRAGON_BREATH, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BATTLE_AXE, 1), "WXW", "WSW", "XSX", 'W', new ItemStack(ModItems.WITHER_BONE, 1), 'S', new ItemStack(Items.NETHER_STAR, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BATTLE_AXE, 1), "LXL", "LSL", "XSX", 'L', new ItemStack(ModItems.LAVA_CRYSTAL, 1), 'S', new ItemStack(Items.STICK, 1));
        }
        if (enableBowsRecipes && recipes == 1) {
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BOW, 1), "XCS", "CXS", "XCS", 'C', new ItemStack(Blocks.COAL_BLOCK, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.COAL_BOW, 1), "SCX", "SXC", "SCX", 'C', new ItemStack(Blocks.COAL_BLOCK, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BOW, 1), "XLS", "LXS", "XLS", 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAPIS_BOW, 1), "SLX", "SXL", "SLX", 'L', new ItemStack(Blocks.LAPIS_BLOCK, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BOW, 1), "XRS", "RXS", "XRS", 'R', new ItemStack(Blocks.REDSTONE_BLOCK, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_BOW, 1), "SRX", "SXR", "SRX", 'R', new ItemStack(Blocks.REDSTONE_BLOCK, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BOW, 1), "XES", "EXS", "XES", 'E', new ItemStack(Blocks.EMERALD_BLOCK, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.EMERALD_BOW, 1), "SEX", "SXE", "SEX", 'E', new ItemStack(Blocks.EMERALD_BLOCK, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOW, 1), "XOS", "OXS", "XOS", 'O', new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOW, 1), "SOX", "SXO", "SOX", 'O', new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BOW, 1), "XGS", "GXS", "XGS", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'S', new ItemStack(Items.PRISMARINE_SHARD, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.GUARDIAN_BOW, 1), "SGX", "SXG", "SGX", 'G', new ItemStack(ModItems.GUARDIAN_SCALE, 1), 'S', new ItemStack(Items.PRISMARINE_SHARD, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOW, 1), "XES", "DXS", "XES", 'E', new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), 'S', new ItemStack(Items.STRING, 1), 'D', new ItemStack(Items.DRAGON_BREATH, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOW, 1), "SEX", "SXD", "SEX", 'E', new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), 'S', new ItemStack(Items.STRING, 1), 'D', new ItemStack(Items.DRAGON_BREATH, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOW, 1), "XWS", "NXS", "XWS", 'W', new ItemStack(ModItems.WITHER_BONE, 1), 'S', new ItemStack(Items.STRING, 1), 'N', new ItemStack(Items.SKULL, 1, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOW, 1), "SWX", "SXN", "SWX", 'W', new ItemStack(ModItems.WITHER_BONE, 1), 'S', new ItemStack(Items.STRING, 1), 'N', new ItemStack(Items.SKULL, 1, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BOW, 1), "SLX", "SXL", "SLX", 'L', new ItemStack(ModItems.LAVA_CRYSTAL, 1), 'S', new ItemStack(Items.STRING, 1));
            GameRegistry.addRecipe(new ItemStack(ModItems.LAVA_BOW, 1), "XLS", "LXS", "XLS", 'L', new ItemStack(ModItems.LAVA_CRYSTAL, 1), 'S', new ItemStack(Items.STRING, 1));
        }
    }
}
