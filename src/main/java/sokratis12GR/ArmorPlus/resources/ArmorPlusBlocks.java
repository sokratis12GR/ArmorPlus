package sokratis12GR.ArmorPlus.resources;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sokratis12GR.ArmorPlus.ArmorPlus;

public class ArmorPlusBlocks {


    public static Block blockArmorWorkshop;

    public static void init() {

        blockArmorWorkshop = new Block(Material.iron).setUnlocalizedName("ArmorWorkshop").setCreativeTab(ArmorPlus.tabArmorPlus);
        blockArmorWorkshop.setHardness(4.0F).setHarvestLevel("pickaxe", 2);
        GameRegistry.addRecipe(new ItemStack(ArmorPlusBlocks.blockArmorWorkshop, 1), new Object[]{"012", "345", "678", Character.valueOf('0'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('1'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('2'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('3'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('4'), new ItemStack(Blocks.crafting_table, 1), Character.valueOf('5'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('6'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('7'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('8'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('9'), new ItemStack(Items.iron_ingot, 1),});
    }

    public static void register() {
        GameRegistry.registerBlock(blockArmorWorkshop, blockArmorWorkshop.getUnlocalizedName().substring(5));
    }

    public static void registerRenders() {
        registerRender(blockArmorWorkshop);
    }

    public static void registerRender(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ArmorPlusBlocks.blockArmorWorkshop), 0, new ModelResourceLocation("armorplus:ArmorWorkshop", "inventory"));
    }
}
