package sokratis12GR.ArmorPlus.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import sokratis12GR.ArmorPlus.resources.ConfigHandler;

/**
 * sokratis12GR.ArmorPlus.registry
 * ArmorPlus created by sokratis12GR on 6/20/2016 6:44 PM.
 */
public class ModRecipes {


    public static void init() {
        addShapedRecipes();
        addShapelessRecipes();
        addFurnaceRecipes();
    }

    public static void addShapedRecipes() {
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(ModItems.CHAINMAIL, 12), "SSX", "SXS", "XSS", 'S', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN, 1), "OOO", "OOO", "OOO", 'O', "obsidian"));
        GameRegistry.addRecipe(new ItemStack(Item.getByNameOrId("minecraft:dragon_egg"), 1), "EEE", "EGE", "EEE", 'E', new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), 'G', new ItemStack(Items.EGG, 1));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.ARMOR_FORGE), "LCL", "OTO", "OXO", 'T', new ItemStack(Blocks.CRAFTING_TABLE), 'O', new ItemStack(Blocks.COAL_BLOCK), 'L', new ItemStack(Blocks.LAPIS_BLOCK), 'C', new ItemStack(ModItems.LAVA_CRYSTAL));
        if (ConfigHandler.enableRedstoneAppleRecipes) {
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_APPLE, 1), "RRR", "RAR", "RRR", 'R', new ItemStack(Items.REDSTONE), 'A', new ItemStack(Items.APPLE));
            GameRegistry.addRecipe(new ItemStack(ModItems.REDSTONE_APPLE, 1, 1), "BBB", "BAB", "BBB", 'B', new ItemStack(Blocks.REDSTONE_BLOCK), 'A', new ItemStack(Items.APPLE));
        }
    }

    public static void addShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN, 9), new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ARMORPLUS_INFO_BOOK, 1), new ItemStack(Items.BOOK), new ItemStack(Items.COAL));
    }

    public static void addFurnaceRecipes() {
        GameRegistry.addSmelting(new ItemStack(ModBlocks.METAL_ORE), new ItemStack(ModItems.METAL_INGOT), 0.5F);
    }
}
