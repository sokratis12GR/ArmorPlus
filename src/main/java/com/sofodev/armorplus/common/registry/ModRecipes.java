/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry;

import com.sofodev.armorplus.api.crafting.base.BaseShapedOreRecipe;
import com.sofodev.armorplus.api.crafting.base.BaseShapelessOreRecipe;
import com.sofodev.armorplus.api.crafting.workbench.WorkbenchRegistry;
import com.sofodev.armorplus.common.config.ModConfig;
import com.sofodev.armorplus.common.util.LoaderUtils;
import com.sofodev.armorplus.common.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.sofodev.armorplus.common.registry.ModBlocks.*;
import static net.minecraft.init.Blocks.CACTUS;
import static net.minecraft.init.Items.*;
import static net.minecraft.item.crafting.Ingredient.*;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapedRecipe;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapelessRecipe;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModRecipes {

    public static void init() {
        addShapedRecipes();
        addShapelessRecipes();
        if (LoaderUtils.isTOPIntegrationEnabled()) {
            addIntegrationShapelessRecipes();
        }
    }

    private static void addIntegrationShapelessRecipes() {
        addTOPRecipes("ardite_helmet", "chicken_helmet", "coal_helmet",
            "cobalt_helmet", "emerald_helmet", "ender_dragon_helmet",
            "guardian_helmet", "knight_slime_helmet", "lapis_helmet",
            "lava_helmet", "manyullyn_helmet", "obsidian_helmet",
            "pig_iron_helmet", "redstone_helmet", "slime_helmet",
            "super_star_helmet", "the_ultimate_helmet"
        );
    }

    private static void addTOPRecipes(String... names) {
        Arrays.stream(names).forEach(ModRecipes::addTOPRecipe);
    }

    private static void addTOPRecipe(String name) {
        /* NBT-Tag-Compound */
        NBTTagCompound nbttagc = new NBTTagCompound();
        nbttagc.setInteger("theoneprobe", 1);
        Item helmet = Item.getByNameOrId("armorplus:" + name);
        if (helmet != null) {
            /* Set Helmets' NBT-Tags */
            ItemStack stack = getItemStack(helmet);
            NBTTagCompound oldNBT = stack.getTagCompound();
            if (oldNBT != null) {
                nbttagc.merge(oldNBT);
            }
            stack.setTagCompound(nbttagc);
            if (!stack.isEmpty()) {
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
                WorkbenchRegistry.addRecipe(
                    new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_HELMET, 1), "   ", "CCC", "C C", 'C', "chainmail"),
                    new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_HELMET, 1), "CCC", "C C", "   ", 'C', "chainmail"),
                    new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_CHESTPLATE, 1), "C C", "CCC", "CCC", 'C', "chainmail"),
                    new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_LEGGINGS, 1), "CCC", "C C", "C C", 'C', "chainmail"),
                    new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_BOOTS, 1), "   ", "C C", "C C", 'C', "chainmail"),
                    new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_BOOTS, 1), "C C", "C C", "   ", 'C', "chainmail")
                );
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
        addShapelessRecipe(Utils.setRL("lava_cactus"), Utils.setRL("lava_cactus"), getItemStack(lavaCactus, 1, 0), fromStacks(getItemStack(CACTUS)), fromStacks(getItemStack(ModItems.itemLavaCrystal, 1)));
    }

    private static String stoneBrick = "stonebrick";

    private static void addRecipeCastleCorner(Block block, String color) {
        WorkbenchRegistry.addRecipe(
            new BaseShapedOreRecipe(3, new ItemStack(block), "   ", "  S", " SS", 'S', stoneBrick + color),
            new BaseShapedOreRecipe(3, new ItemStack(block), "   ", "S  ", "SS ", 'S', stoneBrick + color),
            new BaseShapedOreRecipe(3, new ItemStack(block), "  S", " SS", "   ", 'S', stoneBrick + color),
            new BaseShapedOreRecipe(3, new ItemStack(block), "S  ", "SS ", "   ", 'S', stoneBrick + color)
        );
    }

    private static void addRecipeCastleTower(Block block, String color) {
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "   ", "S S", "SSS", 'S', stoneBrick + color));
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "S S", "SSS", "   ", 'S', stoneBrick + color));
    }

    private static void addRecipeCastleWall(Block block, String color) {
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 6), "   ", "SSS", "SSS", 'S', stoneBrick + color));
        WorkbenchRegistry.addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 6), "SSS", "SSS", "   ", 'S', stoneBrick + color));
    }

    private static void addRecipeCastle(Block block, String color) {
        WorkbenchRegistry.addRecipe(
            new BaseShapelessOreRecipe(new ItemStack(block, 3), stoneBrick + color + "Corner"),
            new BaseShapelessOreRecipe(new ItemStack(block, 5), stoneBrick + color + "Tower"),
            new BaseShapelessOreRecipe(new ItemStack(block), stoneBrick + color + "Wall"),
            new BaseShapedOreRecipe(3, new ItemStack(block), " S ", "SCS", " S ", 'S', stoneBrick, 'C', "dye" + color)
        );
    }

    private static void addRecipeStoneBrick(String color) {
        WorkbenchRegistry.addRecipe(new BaseShapelessOreRecipe(new ItemStack(Blocks.STONEBRICK), stoneBrick + color));
    }
}
