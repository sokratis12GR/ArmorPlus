package sokratis12GR.ArmorPlus.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * sokratis12GR.ArmorPlus.registry
 * ArmorPlus created by sokratis12GR on 6/20/2016 6:44 PM.
 */
public class ModRecipes {


    public static void init() {
        // ===================================== Vanilla Crafted Items =====================================
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(Items.CHAINMAIL_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(ModItems.CHAINMAIL, 1));
        GameRegistry.addRecipe(new ItemStack(ModItems.CHAINMAIL, 12), "SSX", "SXS", "XSS", 'S', Items.IRON_INGOT);
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN, 1), "OOO", "OOO", "OOO", 'O', "obsidian"));
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN, 9), new ItemStack(ModBlocks.COMPRESSED_OBSIDIAN));
        GameRegistry.addRecipe(new ItemStack(Item.getByNameOrId("minecraft:dragon_egg"), 1), "EEE", "EGE", "EEE", 'E', new ItemStack(ModItems.ENDER_DRAGON_SCALE, 1), 'G', new ItemStack(Items.EGG, 1));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.ARMOR_FORGE), "LCL", "OWO", "OXO", 'W', "Workbench", 'O', new ItemStack(Blocks.COAL_BLOCK), 'L', new ItemStack(Blocks.LAPIS_BLOCK), 'C', new ItemStack(ModItems.LAVA_CRYSTAL)));
        //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.COMPRESSED_OBSIDIAN, 1, 0), "AGA", "GCG", "AAA", 'A', alloy1, 'G', "blockGlass", 'C', active_core1)

    }
}
