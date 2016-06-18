package sokratis12GR.ArmorPlus.registry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.blocks.*;
import sokratis12GR.ArmorPlus.util.TextHelper;

import static sokratis12GR.ArmorPlus.ArmorPlus.logger;

/**
 * sokratis12GR.ArmorPlus.registry
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:39 PM.
 */
public class ModBlocks {

    public static Block BLOCK_LAVA_CRYSTAL;
    public static Block COMPRESSED_OBSIDIAN;
    public static Block ARMOR_FORGE;

    public static void init() {
        BLOCK_LAVA_CRYSTAL = new BlockLavaCrystal();
        COMPRESSED_OBSIDIAN = new CompressedObsidian();
        ARMOR_FORGE = new ArmorForge();
    }

    public static void register() {
        registerBlock(BLOCK_LAVA_CRYSTAL);
        registerBlock(COMPRESSED_OBSIDIAN);
        registerBlock(ARMOR_FORGE);
    }

    public static void registerRenders() {
        registerRender(BLOCK_LAVA_CRYSTAL);
        registerRender(COMPRESSED_OBSIDIAN);
        registerRender(ARMOR_FORGE);
    }

    public static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
        logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.load.blocks"));
        OreDictionary.registerOre("oreLavaCrystal", new ItemStack(BLOCK_LAVA_CRYSTAL, 1, 2));
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(ArmorPlus.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}

