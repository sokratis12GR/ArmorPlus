/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.registry;

import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import com.sofodev.armorplus.api.crafting.base.BaseShapelessOreRecipe;
import com.sofodev.armorplus.api.crafting.workbench.WorkbenchRegistry;
import com.sofodev.armorplus.config.ModConfig;
import com.sofodev.armorplus.util.LoaderUtils;
import com.sofodev.armorplus.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.stream.IntStream;

import static com.sofodev.armorplus.registry.ModBlocks.*;
import static net.minecraft.init.Blocks.CACTUS;
import static net.minecraft.init.Items.*;
import static net.minecraft.item.crafting.Ingredient.*;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapedRecipe;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapelessRecipe;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModRecipes {

    private static ModConfig.RegistryConfig.GlobalRegistry gr = ModConfig.RegistryConfig.global_registry;

    public static void init() {
        addShapedRecipes();
        addShapelessRecipes();
        if (LoaderUtils.isTOPIntegrationEnabled()) {
            addIntegrationShapelessRecipes();
        }
    }

    private static void addIntegrationShapelessRecipes() {
        addTOPRecipe(gr.enableEnderDragonArmor, "ender_dragon_helmet");
        addTOPRecipe(gr.enableGuardianArmor, "guardian_helmet");
        addTOPRecipe(gr.enableSuperStarArmor, "super_star_helmet");
        addTOPRecipe(gr.enableTheUltimateArmor, "the_ultimate_helmet");
        addTOPRecipe(gr.enableCoalArmor, "coal_helmet");
        addTOPRecipe(gr.enableEmeraldArmor, "emerald_helmet");
        addTOPRecipe(gr.enableLapisArmor, "lapis_helmet");
        addTOPRecipe(gr.enableLavaArmor, "lava_helmet");
        addTOPRecipe(gr.enableObsidianArmor, "obsidian_helmet");
        addTOPRecipe(gr.enableRedstoneArmor, "redstone_helmet");
        addTOPRecipe(gr.enableChickenArmor, "chicken_helmet");
        addTOPRecipe(gr.enableSlimeArmor, "slime_helmet");
        addTOPRecipe(gr.enableArditeArmor, "ardite_helmet");
        addTOPRecipe(gr.enableCobaltArmor, "cobalt_helmet");
        addTOPRecipe(gr.enableManyullynArmor, "manyullyn_helmet");
        addTOPRecipe(gr.enablePigIronArmor, "pig_iron_helmet");
        addTOPRecipe(gr.enableKnightSlimeArmor, "knight_slime_helmet");
    }

    private static void addTOPRecipe(boolean enabled, String name) {
        /* NBT-Tag-Compound */
        NBTTagCompound nbttagc = new NBTTagCompound();
        nbttagc.setInteger("theoneprobe", 1);
        Item helmet = Item.getByNameOrId("armorplus:" + name);
        if (helmet != null) {
            /* Set Helmets' NBT-Tags */
            ItemStack stack = getItemStack(helmet);
            stack.setTagCompound(nbttagc);
            if (enabled && !stack.isEmpty()) {
                addShapelessRecipe(Utils.setRL(name), Utils.setRL("top"), stack, fromItem(helmet), fromStacks(getItemStack("theoneprobe", "probe")));
            }
        }
    }

    private static void addShapedRecipes() {
        if (!ModConfig.MainConfig.global.useJsonRecipes) {
            addShapedRecipe(Utils.setRL("block_lava_crystal"), Utils.setRL("lava_block"), getItemStack(blockLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModItems.itemLavaCrystal, 0));
            addShapedRecipe(Utils.setRL("block_infused_lava_crystal"), Utils.setRL("infused_lava_block"), getItemStack(blockInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModItems.itemLavaCrystal, 1));
            addShapedRecipe(Utils.setRL("block_compressed_lava_crystal"), Utils.setRL("compressed_lava_block"), getItemStack(blockCompressedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(blockLavaCrystal));
            addShapedRecipe(Utils.setRL("block_compressed_infused_lava_crystal"), Utils.setRL("compressed_infused_lava_block"), getItemStack(blockCompressedInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(blockInfusedLavaCrystal));
            addShapedRecipe(Utils.setRL("workbench"), Utils.setRL("benches"),
                getItemStack(benches[0]),
                "LCL",
                "OTO",
                "O O",
                'T', Blocks.CRAFTING_TABLE,
                'O', Blocks.COAL_BLOCK,
                'L', getItemStack(Items.DYE, 4),
                'C', ModItems.itemLavaCrystal
            );
            WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(benches[0]),
                "LCL",
                "OTO",
                "O O",
                'T', Blocks.CRAFTING_TABLE,
                'O', Blocks.COAL_BLOCK,
                'L', getItemStack(Items.DYE, 4),
                'C', ModItems.itemLavaCrystal)
            );
            if (ModConfig.RegistryConfig.recipes.enableElytraRecipe) {
                WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(ELYTRA, 1),
                    "ESE", "SNS", "EEE", 'E', new ItemStack(ModItems.materials, 1, 3), 'S', STRING, 'N', NETHER_STAR));
            }
            if (ModConfig.RegistryConfig.recipes.enableChainArmorRecipes) {
                WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_HELMET, 1), "   ", "CCC", "C C", 'C', "chainmail"));
                WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_HELMET, 1), "CCC", "C C", "   ", 'C', "chainmail"));
                WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_CHESTPLATE, 1), "C C", "CCC", "CCC", 'C', "chainmail"));
                WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_LEGGINGS, 1), "CCC", "C C", "C C", 'C', "chainmail"));
                WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_BOOTS, 1), "   ", "C C", "C C", 'C', "chainmail"));
                WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_BOOTS, 1), "C C", "C C", "   ", 'C', "chainmail"));
            }
            WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, getItemStack(ModItems.materials, 12, 0), "SS ", "S S", " SS", 'S', "ingotIron"));
            WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, getItemStack(blockCompressedObsidian), "OOO", "OOO", "OOO", 'O', "obsidian"));
            if (ModConfig.RegistryConfig.recipes.enableRedstoneAppleRecipes) {
                WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, getItemStack(ModItems.itemRedstoneApple), "RRR", "RAR", "RRR", 'R', "dustRedstone", 'A', APPLE));
                WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, getItemStack(ModItems.itemRedstoneApple, 1), "BBB", "BAB", "BBB", 'B', "blockRedstone", 'A', APPLE));
            }
            WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(blockLavaNetherBrick, 4),
                " N ",
                "NLN",
                " N ",
                'L', LAVA_BUCKET,
                'N', Blocks.NETHER_BRICK));
            IntStream.range(0, ModOreDicts.colors.length).forEachOrdered(i -> {
                addRecipeCastle(stoneBricks[i], ModOreDicts.colors[i]);
                addRecipeCastleCorner(stoneBrickCorners[i], ModOreDicts.colors[i]);
                addRecipeCastleTower(stoneBrickTowers[i], ModOreDicts.colors[i]);
                addRecipeCastleWall(stonebrickWalls[i], ModOreDicts.colors[i]);
                addRecipeStoneBrick(ModOreDicts.colors[i]);
            });
        }
    }

    private static void addShapelessRecipes() {
        if (!ModConfig.MainConfig.global.useJsonRecipes) {
            GameRegistry.addShapelessRecipe(Utils.setRL("lava_crystal_from_block"), Utils.setRL("lava_crystal"), getItemStack(ModItems.itemLavaCrystal, 9, 0), fromStacks(getItemStack(blockLavaCrystal)));
            GameRegistry.addShapelessRecipe(Utils.setRL("infused_lava_crystal_from_block"), Utils.setRL("infused_lava_crystal"), getItemStack(ModItems.itemLavaCrystal, 9, 1), fromStacks(getItemStack(blockInfusedLavaCrystal)));
            GameRegistry.addShapelessRecipe(Utils.setRL("block_lava_crystal_from_compression"), Utils.setRL("lava_block"), getItemStack(ModBlocks.blockLavaCrystal, 9, 0), fromStacks(getItemStack(blockCompressedLavaCrystal)));
            GameRegistry.addShapelessRecipe(Utils.setRL("block_infused_lava_crystal_from_compression"), Utils.setRL("infused_lava_block"), getItemStack(ModBlocks.blockInfusedLavaCrystal, 9, 0), fromStacks(getItemStack(blockCompressedInfusedLavaCrystal)));
            GameRegistry.addShapelessRecipe(Utils.setRL("obsidian_from_compression"), Utils.setRL("obsidian"), getItemStack(Blocks.OBSIDIAN, 9, 0), fromStacks(getItemStack(blockCompressedObsidian)));
        }
        addShapelessRecipe(Utils.setRL("info_book"), Utils.setRL("item_book"), getItemStack(ModItems.bookInfo), fromItems(BOOK), fromItems(COAL));
        addShapelessRecipe(Utils.setRL("lava_cactus"), Utils.setRL("lava_cactus"), getItemStack(blockLavaCactus, 1, 0), fromStacks(getItemStack(CACTUS)), fromStacks(getItemStack(ModItems.itemLavaCrystal, 1)));
    }

    private static void addRecipeCastleCorner(Block block, String color) {
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "   ", "  S", " SS", 'S', "stonebrick" + color));
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "   ", "S  ", "SS ", 'S', "stonebrick" + color));
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "  S", " SS", "   ", 'S', "stonebrick" + color));
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "S  ", "SS ", "   ", 'S', "stonebrick" + color));
    }

    private static void addRecipeCastleTower(Block block, String color) {
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "   ", "S S", "SSS", 'S', "stonebrick" + color));
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "S S", "SSS", "   ", 'S', "stonebrick" + color));
    }

    private static void addRecipeCastleWall(Block block, String color) {
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 6), "   ", "SSS", "SSS", 'S', "stonebrick" + color));
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 6), "SSS", "SSS", "   ", 'S', "stonebrick" + color));
    }

    private static void addRecipeCastle(Block block, String color) {
        WorkbenchRegistry.addRecipe(new BaseShapelessOreRecipe(new ItemStack(block, 3), "stonebrick" + color + "Corner"));
        WorkbenchRegistry.addRecipe(new BaseShapelessOreRecipe(new ItemStack(block, 5), "stonebrick" + color + "Tower"));
        WorkbenchRegistry.addRecipe(new BaseShapelessOreRecipe(new ItemStack(block), "stonebrick" + color + "Wall"));
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), " S ", "SCS", " S ", 'S', "stonebrick", 'C', "dye" + color));
    }

    private static void addRecipeStoneBrick(String color) {
        WorkbenchRegistry.addRecipe(new BaseShapelessOreRecipe(new ItemStack(Blocks.STONEBRICK), "stonebrick" + color));
    }
}
