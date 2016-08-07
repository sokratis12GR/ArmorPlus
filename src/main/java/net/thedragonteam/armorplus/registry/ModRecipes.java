package net.thedragonteam.armorplus.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.thedragonteam.armorplus.ARPConfig;

/**
 * sokratis12GR.ArmorPlus.registry
 * ArmorPlus created by sokratis12GR on 6/20/2016 6:44 PM.
 */
public class ModRecipes {


    public static void init() {
        addShapedRecipes();
        addShapelessRecipes();
    }

    public static void addShapedRecipes() {
        if (ARPConfig.enableChainArmorRecipes) {
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

        if (ARPConfig.enableRedstoneAppleRecipes) {
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
}
