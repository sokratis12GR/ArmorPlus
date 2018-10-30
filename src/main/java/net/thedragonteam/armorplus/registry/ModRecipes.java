package net.thedragonteam.armorplus.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
                addShapelessRecipe(setRL(name), setRL("top"), stack, fromItem(helmet), fromStacks(getItemStack("theoneprobe", "probe")));
            }
        }
    }

    private static void addShapedRecipes() {
        if (!global.useJsonRecipes) {
            addShapedRecipe(setRL("block_lava_crystal"), setRL("lava_block"), getItemStack(blockLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModItems.itemLavaCrystal, 0));
            addShapedRecipe(setRL("block_infused_lava_crystal"), setRL("infused_lava_block"), getItemStack(blockInfusedLavaCrystal), "CCC", "CCC", "CCC", 'C', getItemStack(ModItems.itemLavaCrystal, 1));
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
                'C', ModItems.itemLavaCrystal
            );
            addRecipe(new BaseShapedOreRecipe(3, new ItemStack(benches[0]),
                "LCL",
                "OTO",
                "O O",
                'T', Blocks.CRAFTING_TABLE,
                'O', Blocks.COAL_BLOCK,
                'L', getItemStack(Items.DYE, 4),
                'C', ModItems.itemLavaCrystal)
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
            addRecipe(new BaseShapedOreRecipe(3, getItemStack(blockCompressedObsidian), "OOO", "OOO", "OOO", 'O', "obsidian"));
            if (recipes.enableRedstoneAppleRecipes) {
                addRecipe(new BaseShapedOreRecipe(3, getItemStack(itemRedstoneApple), "RRR", "RAR", "RRR", 'R', "dustRedstone", 'A', APPLE));
                addRecipe(new BaseShapedOreRecipe(3, getItemStack(itemRedstoneApple, 1), "BBB", "BAB", "BBB", 'B', "blockRedstone", 'A', APPLE));
            }
            addRecipe(new BaseShapedOreRecipe(3, new ItemStack(blockLavaNetherBrick, 4),
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
                addRecipeStoneBrick(colors[i]);
            });
        }
    }

    private static void addShapelessRecipes() {
        if (!global.useJsonRecipes) {
            addShapelessRecipe(setRL("lava_crystal_from_block"), setRL("lava_crystal"), getItemStack(ModItems.itemLavaCrystal, 9, 0), fromStacks(getItemStack(blockLavaCrystal)));
            addShapelessRecipe(setRL("infused_lava_crystal_from_block"), setRL("infused_lava_crystal"), getItemStack(ModItems.itemLavaCrystal, 9, 1), fromStacks(getItemStack(blockInfusedLavaCrystal)));
            addShapelessRecipe(setRL("block_lava_crystal_from_compression"), setRL("lava_block"), getItemStack(ModBlocks.blockLavaCrystal, 9, 0), fromStacks(getItemStack(blockCompressedLavaCrystal)));
            addShapelessRecipe(setRL("block_infused_lava_crystal_from_compression"), setRL("infused_lava_block"), getItemStack(ModBlocks.blockInfusedLavaCrystal, 9, 0), fromStacks(getItemStack(blockCompressedInfusedLavaCrystal)));
            addShapelessRecipe(setRL("obsidian_from_compression"), setRL("obsidian"), getItemStack(Blocks.OBSIDIAN, 9, 0), fromStacks(getItemStack(blockCompressedObsidian)));
        }
        addShapelessRecipe(setRL("info_book"), setRL("item_book"), getItemStack(bookInfo), fromItems(BOOK), fromItems(COAL));
        addShapelessRecipe(setRL("lava_cactus"), setRL("lava_cactus"), getItemStack(blockLavaCactus, 1, 0), fromStacks(getItemStack(CACTUS)), fromStacks(getItemStack(itemLavaCrystal, 1)));
    }

    private static void addRecipeCastleCorner(Block block, String color) {
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "   ", "  S", " SS", 'S', "stonebrick" + color));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "   ", "S  ", "SS ", 'S', "stonebrick" + color));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "  S", " SS", "   ", 'S', "stonebrick" + color));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "S  ", "SS ", "   ", 'S', "stonebrick" + color));
    }

    private static void addRecipeCastleTower(Block block, String color) {
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "   ", "S S", "SSS", 'S', "stonebrick" + color));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), "S S", "SSS", "   ", 'S', "stonebrick" + color));
    }

    private static void addRecipeCastleWall(Block block, String color) {
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 6), "   ", "SSS", "SSS", 'S', "stonebrick" + color));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block, 6), "SSS", "SSS", "   ", 'S', "stonebrick" + color));
    }

    private static void addRecipeCastle(Block block, String color) {
        addRecipe(new BaseShapelessOreRecipe(new ItemStack(block, 3), "stonebrick" + color + "Corner"));
        addRecipe(new BaseShapelessOreRecipe(new ItemStack(block, 5), "stonebrick" + color + "Tower"));
        addRecipe(new BaseShapelessOreRecipe(new ItemStack(block), "stonebrick" + color + "Wall"));
        addRecipe(new BaseShapedOreRecipe(3, new ItemStack(block), " S ", "SCS", " S ", 'S', "stonebrick", 'C', "dye" + color));
    }

    private static void addRecipeStoneBrick(String color) {
        addRecipe(new BaseShapelessOreRecipe(new ItemStack(Blocks.STONEBRICK), "stonebrick" + color));
    }
}
