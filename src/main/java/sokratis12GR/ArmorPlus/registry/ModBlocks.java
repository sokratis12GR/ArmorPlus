package sokratis12GR.ArmorPlus.registry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.blocks.ArmorForge;
import sokratis12GR.ArmorPlus.blocks.BlockLavaCrystal;
import sokratis12GR.ArmorPlus.blocks.CompressedObsidian;
import sokratis12GR.ArmorPlus.blocks.MetalOre;

import static net.minecraftforge.oredict.OreDictionary.registerOre;

/**
 * sokratis12GR.ArmorPlus.registry
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:39 PM.
 */
public class ModBlocks {

    public static Block BLOCK_LAVA_CRYSTAL,
            COMPRESSED_OBSIDIAN,
            ARMOR_FORGE, METAL_ORE;

    public static void init() {
        BLOCK_LAVA_CRYSTAL = new BlockLavaCrystal().setRegistryName("block_lava_crystal");
        COMPRESSED_OBSIDIAN = new CompressedObsidian().setRegistryName("compressed_obsidian");
        ARMOR_FORGE = new ArmorForge().setRegistryName("armor_forge");
        METAL_ORE = new MetalOre().setRegistryName("metal_ore");
    }

    public static void register() {
        registerBlock(BLOCK_LAVA_CRYSTAL);
        registerBlock(COMPRESSED_OBSIDIAN);
        registerBlock(ARMOR_FORGE);
        registerBlock(METAL_ORE);
    }

    public static void registerRenders() {
        registerRender(BLOCK_LAVA_CRYSTAL);
        registerRender(COMPRESSED_OBSIDIAN);
        registerRender(ARMOR_FORGE);
        registerRender(METAL_ORE);
    }

    public static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getRegistryName().toString());
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(ArmorPlus.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}

