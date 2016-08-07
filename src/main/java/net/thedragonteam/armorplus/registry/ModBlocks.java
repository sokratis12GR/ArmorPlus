package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.blocks.*;

/**
 * sokratis12GR.ArmorPlus.registry
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:39 PM.
 */
public class ModBlocks {

    public static Block BLOCK_LAVA_CRYSTAL,
            COMPRESSED_OBSIDIAN,
            ARMOR_FORGE, STEEL_BLOCK, ELECTRICAL_BLOCK;

    public static void init() {
        BLOCK_LAVA_CRYSTAL = new BlockLavaCrystal().setRegistryName("block_lava_crystal");
        COMPRESSED_OBSIDIAN = new CompressedObsidian().setRegistryName("compressed_obsidian");
        ARMOR_FORGE = new ArmorForge().setRegistryName("armor_forge");
        STEEL_BLOCK = new SteelBlock().setRegistryName("steel_block");
        ELECTRICAL_BLOCK = new ElectricalBlock().setRegistryName("electrical_block");
    }

    public static void register() {
        registerBlock(BLOCK_LAVA_CRYSTAL);
        registerBlock(COMPRESSED_OBSIDIAN);
        registerBlock(ARMOR_FORGE);
        registerBlock(STEEL_BLOCK);
        registerBlock(ELECTRICAL_BLOCK);
    }

    public static void registerRenders() {
        registerRender(BLOCK_LAVA_CRYSTAL);
        registerRender(COMPRESSED_OBSIDIAN);
        registerRender(ARMOR_FORGE);
        registerRender(STEEL_BLOCK);
        registerRender(ELECTRICAL_BLOCK);
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

