package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.thedragonteam.armorplus.ModConfig.RegistryConfig.GlobalRegistry;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapedOreRecipe;
import net.thedragonteam.armorplus.api.crafting.base.BaseShapelessOreRecipe;
import net.thedragonteam.armorplus.util.LoaderUtils;

import java.util.stream.IntStream;

import static net.minecraft.init.Blocks.CACTUS;
import static net.minecraft.init.Items.*;
import static net.minecraft.item.crafting.Ingredient.*;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapedRecipe;
import static net.minecraftforge.fml.common.registry.GameRegistry.addShapelessRecipe;
import static net.thedragonteam.armorplus.ModConfig.MainConfig.global;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.global_registry;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.recipes;
import static net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchRegistry.addRecipe;
import static net.thedragonteam.armorplus.registry.ModBlocks.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.registry.ModOreDicts.colors;
import static net.thedragonteam.armorplus.util.Utils.setRL;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

public class ModRecipes {

    private static GlobalRegistry gr = global_registry;

    public static void init() {
        addShapedRecipes();
        addShapelessRecipes();
        if (LoaderUtils.isTOPIntegrationEnabled()) {
            addIntegrationShapelessRecipes();
        }
    }

    private static void addIntegrationShapelessRecipes() {
        /* NBT-Tag-Compound */
        NBTTagCompound nbttagc = new NBTTagCompound();
        nbttagc.setInteger("theoneprobe", 1);

        /* Set Helmets' NBT-Tags */
        ItemStack coalHelmet = getItemStack(APItems.coalHelmet);
        coalHelmet.setTagCompound(nbttagc);
        ItemStack emeraldHelmet = getItemStack(APItems.emeraldHelmet);
        emeraldHelmet.setTagCompound(nbttagc);
        ItemStack lapisHelmet = getItemStack(APItems.lapisHelmet);
        lapisHelmet.setTagCompound(nbttagc);
        ItemStack lavaHelmet = getItemStack(APItems.lavaHelmet);
        lavaHelmet.setTagCompound(nbttagc);
        ItemStack obsidianHelmet = getItemStack(APItems.obsidianHelmet);
        obsidianHelmet.setTagCompound(nbttagc);
        ItemStack redstoneHelmet = getItemStack(APItems.redstoneHelmet);
        redstoneHelmet.setTagCompound(nbttagc);
        ItemStack chickenHelmet = getItemStack(APItems.chickenHelmet);
        chickenHelmet.setTagCompound(nbttagc);
        ItemStack slimeHelmet = getItemStack(APItems.slimeHelmet);
        slimeHelmet.setTagCompound(nbttagc);
        ItemStack arditeHelmet = getItemStack(APItems.arditeHelmet);
        arditeHelmet.setTagCompound(nbttagc);
        ItemStack cobaltHelmet = getItemStack(APItems.cobaltHelmet);
        cobaltHelmet.setTagCompound(nbttagc);
        ItemStack manyullynHelmet = getItemStack(APItems.manyullynHelmet);
        manyullynHelmet.setTagCompound(nbttagc);
        ItemStack pigIronHelmet = getItemStack(APItems.pigIronHelmet);
        pigIronHelmet.setTagCompound(nbttagc);
        ItemStack knightSlimeHelmet = getItemStack(APItems.knightSlimeHelmet);
        knightSlimeHelmet.setTagCompound(nbttagc);
        ItemStack enderDragonHelmet = getItemStack(APItems.enderDragonHelmet);
        enderDragonHelmet.setTagCompound(nbttagc);
        ItemStack guardianHelmet = getItemStack(APItems.guardianHelmet);
        guardianHelmet.setTagCompound(nbttagc);
        ItemStack superStarHelmet = getItemStack(APItems.superStarHelmet);
        superStarHelmet.setTagCompound(nbttagc);
        ItemStack theUltimateHelmet = getItemStack(APItems.theUltimateHelmet);
        theUltimateHelmet.setTagCompound(nbttagc);


        if (gr.enableEnderDragonArmor) {
            addShapelessRecipe(setRL("ender_dragon__helmet"), setRL("top"), enderDragonHelmet, fromItem(APItems.enderDragonHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableGuardianArmor) {
            addShapelessRecipe(setRL("guardian_helmet"), setRL("top"), guardianHelmet, fromItem(APItems.guardianHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableSuperStarArmor) {
            addShapelessRecipe(setRL("super_star_helmet"), setRL("top"), superStarHelmet, fromItem(APItems.superStarHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableTheUltimateArmor) {
            addShapelessRecipe(setRL("the_ultimate_helmet"), setRL("top"), theUltimateHelmet, fromItem(APItems.theUltimateHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableCoalArmor) {
            addShapelessRecipe(setRL("coal_helmet"), setRL("top"), coalHelmet, fromItem(APItems.coalHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableEmeraldArmor) {
            addShapelessRecipe(setRL("emerald_helmet"), setRL("top"), emeraldHelmet, fromItem(APItems.emeraldHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableLapisArmor) {
            addShapelessRecipe(setRL("lapis_helmet"), setRL("top"), lapisHelmet, fromItem(APItems.lapisHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableLavaArmor) {
            addShapelessRecipe(setRL("lava_helmet"), setRL("top"), lavaHelmet, fromItem(APItems.lavaHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableObsidianArmor) {
            addShapelessRecipe(setRL("obsidian_helmet"), setRL("top"), obsidianHelmet, fromItem(APItems.obsidianHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableRedstoneArmor) {
            addShapelessRecipe(setRL("redstone_helmet"), setRL("top"), redstoneHelmet, fromItem(APItems.redstoneHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableChickenArmor) {
            addShapelessRecipe(setRL("chicken_helmet"), setRL("top"), chickenHelmet, fromItem(APItems.chickenHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableSlimeArmor) {
            addShapelessRecipe(setRL("slime_helmet"), setRL("top"), slimeHelmet, fromItem(APItems.slimeHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableArditeArmor) {
            addShapelessRecipe(setRL("ardite_helmet"), setRL("top"), arditeHelmet, fromItem(APItems.arditeHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableCobaltArmor) {
            addShapelessRecipe(setRL("cobalt_helmet"), setRL("top"), cobaltHelmet, fromItem(APItems.cobaltHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableManyullynArmor) {
            addShapelessRecipe(setRL("manyullyn_helmet"), setRL("top"), manyullynHelmet, fromItem(APItems.manyullynHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enablePigIronArmor) {
            addShapelessRecipe(setRL("pig_iron_helmet"), setRL("top"), pigIronHelmet, fromItem(APItems.pigIronHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
        if (gr.enableKnightSlimeArmor) {
            addShapelessRecipe(setRL("knight_slime_helmet"), setRL("top"), knightSlimeHelmet, fromItem(APItems.knightSlimeHelmet), fromStacks(getItemStack("theoneprobe", "probe")));
        }
    }

    private static void addShapedRecipes() {
        if (!global.useJsonRecipes) {
            addShapedRecipe(setRL("block_lava_crystal"), setRL("lava_block"), getItemStack(blockLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModItems.lavaCrystal, 0));
            addShapedRecipe(setRL("block_infused_lava_crystal"), setRL("infused_lava_block"), getItemStack(blockInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModItems.lavaCrystal, 1));
            addShapedRecipe(setRL("block_compressed_lava_crystal"), setRL("compressed_lava_block"), getItemStack(blockCompressedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(blockLavaCrystal));
            addShapedRecipe(setRL("block_compressed_infused_lava_crystal"), setRL("compressed_infused_lava_block"), getItemStack(blockCompressedInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(blockInfusedLavaCrystal));
            addShapedRecipe(setRL("workbench"), setRL("benches"),
                getItemStack(benches[0]),
                "LCL",
                "OTO",
                "O O",
                'T', Blocks.CRAFTING_TABLE,
                'O', Blocks.COAL_BLOCK,
                'L', getItemStack(Items.DYE, 4),
                'C', ModItems.lavaCrystal
            );
            addRecipe(new BaseShapedOreRecipe(3, new ItemStack(benches[0]),
                "LCL",
                "OTO",
                "O O",
                'T', Blocks.CRAFTING_TABLE,
                'O', Blocks.COAL_BLOCK,
                'L', getItemStack(Items.DYE, 4),
                'C', ModItems.lavaCrystal)
            );
            if (recipes.enableElytraRecipe) {
                addRecipe(new BaseShapedOreRecipe(3, new ItemStack(ELYTRA, 1),
                    "ESE", "SNS", "EEE", 'E', new ItemStack(materials, 1, 3), 'S', STRING, 'N', NETHER_STAR));
            }
            if (recipes.enableChainArmorRecipes) {
                addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_HELMET, 1), "   ", "CCC", "C C", 'C', "chainmail"));
                addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_HELMET, 1), "CCC", "C C", "   ", 'C', "chainmail"));
                addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_CHESTPLATE, 1), "C C", "CCC", "CCC", 'C', "chainmail"));
                addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_LEGGINGS, 1), "CCC", "C C", "C C", 'C', "chainmail"));
                addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_BOOTS, 1), "   ", "C C", "C C", 'C', "chainmail"));
                addRecipe(new BaseShapedOreRecipe(3, new ItemStack(CHAINMAIL_BOOTS, 1), "C C", "C C", "   ", 'C', "chainmail"));
            }
            addRecipe(new BaseShapedOreRecipe(3, getItemStack(materials, 12, 0), "SS ", "S S", " SS", 'S', "ingotIron"));
            addRecipe(new BaseShapedOreRecipe(3, getItemStack(compressedObsidian), "OOO", "OOO", "OOO", 'O', "obsidian"));
            if (recipes.enableRedstoneAppleRecipes) {
                addRecipe(new BaseShapedOreRecipe(3, getItemStack(redstoneApple), "RRR", "RAR", "RRR", 'R', "dustRedstone", 'A', APPLE));
                addRecipe(new BaseShapedOreRecipe(3, getItemStack(redstoneApple, 1), "BBB", "BAB", "BBB", 'B', "blockRedstone", 'A', APPLE));
            }
            addRecipe(new BaseShapedOreRecipe(3, new ItemStack(lavaNetherBrick, 4),
                " N ",
                "NLN",
                " N ",
                'L', LAVA_BUCKET,
                'N', Blocks.NETHER_BRICK));
            IntStream.range(0, colors.length).forEachOrdered(i -> {
                addRecipeCastle(stoneBricks[i], colors[i]);
                addRecipeCastleCorner(stoneBrickCorners[i], colors[i]);
                addRecipeCastleTower(stoneBrickTowers[i], colors[i]);
                addRecipeCastleWall(stonebrickWalls[i], colors[i]);
                addRecipeStoneBrick(Blocks.STONEBRICK, colors[i]);
            });
        }
    }

    private static void addShapelessRecipes() {
        if (!global.useJsonRecipes) {
            addShapelessRecipe(setRL("lava_crystal_from_block"), setRL("lava_crystal"), getItemStack(ModItems.lavaCrystal, 9, 0), fromStacks(getItemStack(blockLavaCrystal)));
            addShapelessRecipe(setRL("infused_lava_crystal_from_block"), setRL("infused_lava_crystal"), getItemStack(ModItems.lavaCrystal, 9, 1), fromStacks(getItemStack(blockInfusedLavaCrystal)));
            addShapelessRecipe(setRL("block_lava_crystal_from_compression"), setRL("lava_block"), getItemStack(ModBlocks.blockLavaCrystal, 9, 0), fromStacks(getItemStack(blockCompressedLavaCrystal)));
            addShapelessRecipe(setRL("block_infused_lava_crystal_from_compression"), setRL("infused_lava_block"), getItemStack(ModBlocks.blockInfusedLavaCrystal, 9, 0), fromStacks(getItemStack(blockCompressedInfusedLavaCrystal)));
            addShapelessRecipe(setRL("obsidian_from_compression"), setRL("obsidian"), getItemStack(Blocks.OBSIDIAN, 9, 0), fromStacks(getItemStack(compressedObsidian)));
        }
        addShapelessRecipe(setRL("info_book"), setRL("item_book"), getItemStack(bookInfo), fromItems(BOOK), fromItems(COAL));
        addShapelessRecipe(setRL("lava_cactus"), setRL("lava_cactus"), getItemStack(lavaCactus, 1, 0), fromStacks(getItemStack(CACTUS)), fromStacks(getItemStack(lavaCrystal, 1)));
    }

    private static void addRecipeCastleCorner(Block block, String color) {
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 1), "   ", "  S", " SS", 'S', "stonebrick" + color));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 1), "   ", "S  ", "SS ", 'S', "stonebrick" + color));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 1), "  S", " SS", "   ", 'S', "stonebrick" + color));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 1), "S  ", "SS ", "   ", 'S', "stonebrick" + color));
    }

    private static void addRecipeCastleTower(Block block, String color) {
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 1), "   ", "S S", "SSS", 'S', "stonebrick" + color));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 1), "S S", "SSS", "   ", 'S', "stonebrick" + color));
    }

    private static void addRecipeCastleWall(Block block, String color) {
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 6), "   ", "SSS", "SSS", 'S', "stonebrick" + color));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 6), "SSS", "SSS", "   ", 'S', "stonebrick" + color));
    }

    private static void addRecipeCastle(Block block, String color) {
        addRecipe(new BaseShapelessOreRecipe(new ItemStack(block, 3), "stonebrick" + color + "Corner"));
        addRecipe(new BaseShapelessOreRecipe(new ItemStack(block, 5), "stonebrick" + color + "Tower"));
        addRecipe(new BaseShapelessOreRecipe(new ItemStack(block, 1), "stonebrick" + color + "Wall"));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 1), " S ", "SCS", " S ", 'S', "stonebrick", 'C', "dye" + color));
    }

    private static void addRecipeStoneBrick(Block block, String color) {
        addRecipe(new BaseShapelessOreRecipe(new ItemStack(block, 1), "stonebrick" + color));
    }
}
