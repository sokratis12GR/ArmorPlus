package sokratis12GR.ArmorPlus.resources;


import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.blocks.ArmorWorkshop;

public class ArmorPlusBlocks {


    public static ArmorWorkshop ArmorWorkshop;

    public static void init() {

        ArmorWorkshop = new ArmorWorkshop();
        GameRegistry.register(new ItemBlock(ArmorWorkshop).setRegistryName(ArmorWorkshop.getRegistryName()));
        GameRegistry.addRecipe(new ItemStack(ArmorPlusBlocks.ArmorWorkshop, 1), new Object[]{"012", "345", "678", Character.valueOf('0'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('1'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('2'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('3'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('4'), new ItemStack(Blocks.crafting_table, 1), Character.valueOf('5'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('6'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('7'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('8'), new ItemStack(Items.iron_ingot, 1), Character.valueOf('9'), new ItemStack(Items.iron_ingot, 1),});
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ArmorWorkshop.initModel();
        ArmorWorkshop.getBlockLayer();
    }
}
