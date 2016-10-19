/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.api.crafting.workbench.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.workbench.ShapedOreRecipe;
import net.thedragonteam.armorplus.registry.ModItems;

public class ModOriginRecipes {

    public void addRecipes(WorkbenchCraftingManager manager) {
        /* Coal Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableCoalArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "XXX", "CCC", "CXC", 'C', "itemCoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "CCC", "CXC", "XXX", 'C', "itemCoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalChestplate, 1), "CXC", "CCC", "CCC", 'C', "itemCoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalLeggings, 1), "CCC", "CXC", "CXC", 'C', "itemCoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "XXX", "CXC", "CXC", 'C', "itemCoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "CXC", "CXC", "XXX", 'C', "itemCoal"));
        }
        if (ARPConfig.recipes == 0 && ARPConfig.enableCharcoalCoalArmorRecipe) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "XXX", "CCC", "CXC", 'C', "itemCharcoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "CCC", "CXC", "XXX", 'C', "itemCharcoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalChestplate, 1), "CXC", "CCC", "CCC", 'C', "itemCharcoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalLeggings, 1), "CCC", "CXC", "CXC", 'C', "itemCharcoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "XXX", "CXC", "CXC", 'C', "itemCharcoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "CXC", "CXC", "XXX", 'C', "itemCharcoal"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableCoalArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "XXX", "CCC", "CXC", 'C', "blockCoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalHelmet, 1), "CCC", "CXC", "XXX", 'C', "blockCoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalChestplate, 1), "CXC", "CCC", "CCC", 'C', "blockCoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalLeggings, 1), "CCC", "CXC", "CXC", 'C', "blockCoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "CXC", "CXC", 'C', "blockCoal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.coalBoots, 1), "CXC", "CXC", "XXX", 'C', "blockCoal"));
        }
        /* Emerald Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableEmeraldArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldHelmet, 1), "XXX", "EEE", "EXE", 'E', "gemEmerald"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldHelmet, 1), "EEE", "EXE", "XXX", 'E', "gemEmerald"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldChestplate, 1), "EXE", "EEE", "EEE", 'E', "gemEmerald"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldLeggings, 1), "EEE", "EXE", "EXE", 'E', "gemEmerald"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBoots, 1), "XXX", "EXE", "EXE", 'E', "gemEmerald"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBoots, 1), "EXE", "EXE", "XXX", 'E', "gemEmerald"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableEmeraldArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldHelmet, 1), "XXX", "EEE", "EXE", 'E', "blockEmerald"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldHelmet, 1), "EEE", "EXE", "XXX", 'E', "blockEmerald"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldChestplate, 1), "EXE", "EEE", "EEE", 'E', "blockEmerald"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldLeggings, 1), "EEE", "EXE", "EXE", 'E', "blockEmerald"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBoots, 1), "XXX", "EXE", "EXE", 'E', "blockEmerald"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.emeraldBoots, 1), "EXE", "EXE", "XXX", 'E', "blockEmerald"));
        }
        /* Lapis Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableLapisArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisHelmet, 1), "XXX", "LLL", "LXL", 'L', "gemLapis"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisHelmet, 1), "LLL", "LXL", "XXX", 'L', "gemLapis"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisChestplate, 1), "LXL", "LLL", "LLL", 'L', "gemLapis"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisLeggings, 1), "LLL", "LXL", "LXL", 'L', "gemLapis"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBoots, 1), "XXX", "LXL", "LXL", 'L', "gemLapis"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBoots, 1), "LXL", "LXL", "XXX", 'L', "gemLapis"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableLapisArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisHelmet, 1), "XXX", "LLL", "LXL", 'L', "blockLapis"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisHelmet, 1), "LLL", "LXL", "XXX", 'L', "blockLapis"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisChestplate, 1), "LXL", "LLL", "LLL", 'L', "blockLapis"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisLeggings, 1), "LLL", "LXL", "LXL", 'L', "blockLapis"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBoots, 1), "XXX", "LXL", "LXL", 'L', "blockLapis"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lapisBoots, 1), "LXL", "LXL", "XXX", 'L', "blockLapis"));
        }
        /* Lava Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableLavaArmorRecipes && ARPConfig.enableOldLavaArmorRecipes) {
            manager.addShapelessRecipe(new ItemStack(ModItems.lavaHelmet, 1), ModItems.obsidianHelmet, 1, Items.LAVA_BUCKET, ModItems.obsidianHelmet, 1);
            manager.addShapelessRecipe(new ItemStack(ModItems.lavaChestplate, 1), ModItems.obsidianChestplate, Items.LAVA_BUCKET, ModItems.obsidianChestplate);
            manager.addShapelessRecipe(new ItemStack(ModItems.lavaLeggings, 1), ModItems.obsidianLeggings, Items.LAVA_BUCKET, ModItems.obsidianLeggings);
            manager.addShapelessRecipe(new ItemStack(ModItems.lavaBoots, 1), ModItems.obsidianBoots, Items.LAVA_BUCKET, ModItems.obsidianBoots);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableLavaArmorRecipes && ARPConfig.enableOldLavaArmorRecipes) {
            manager.addShapelessRecipe(new ItemStack(ModItems.lavaHelmet, 1), ModItems.obsidianHelmet, 1, Items.LAVA_BUCKET, ModItems.obsidianHelmet, 1,
                    Items.LAVA_BUCKET);
            manager.addShapelessRecipe(new ItemStack(ModItems.lavaChestplate, 1), ModItems.obsidianChestplate, Items.LAVA_BUCKET, ModItems.obsidianChestplate,
                    Items.LAVA_BUCKET);
            manager.addShapelessRecipe(new ItemStack(ModItems.lavaLeggings, 1), ModItems.obsidianLeggings, Items.LAVA_BUCKET, ModItems.obsidianLeggings,
                    Items.LAVA_BUCKET);
            manager.addShapelessRecipe(new ItemStack(ModItems.lavaBoots, 1), ModItems.obsidianBoots, Items.LAVA_BUCKET, ModItems.obsidianBoots,
                    Items.LAVA_BUCKET);
        }
        if (ARPConfig.recipes == 0 && ARPConfig.enableLavaArmorRecipes && !ARPConfig.enableOldLavaArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaHelmet, 1), "XXX", "CCC", "CXC", 'C', "gemLavaCrystal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaHelmet, 1), "CCC", "CXC", "XXX", 'C', "gemLavaCrystal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaChestplate, 1), "CXC", "CCC", "CCC", 'C', "gemLavaCrystal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaLeggings, 1), "CCC", "CXC", "CXC", 'C', "gemLavaCrystal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBoots, 1), "XXX", "CXC", "CXC", 'C', "gemLavaCrystal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBoots, 1), "CXC", "CXC", "XXX", 'C', "gemLavaCrystal"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableLavaArmorRecipes && !ARPConfig.enableOldLavaArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaHelmet, 1), "XXX", "CCC", "CXC", 'C', "gemChargedLavaCrystal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaHelmet, 1), "CCC", "CXC", "XXX", 'C', "gemChargedLavaCrystal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaChestplate, 1), "CXC", "CCC", "CCC", 'C', "gemChargedLavaCrystal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaLeggings, 1), "CCC", "CXC", "CXC", 'C', "gemChargedLavaCrystal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBoots, 1), "XXX", "CXC", "CXC", 'C', "gemChargedLavaCrystal"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.lavaBoots, 1), "CXC", "CXC", "XXX", 'C', "gemChargedLavaCrystal"));
        }

        /* Obsidian Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableObsidianArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianHelmet, 1), "XXX", "OOO", "OXO", 'O', "obsidian"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianHelmet, 1), "OOO", "OXO", "XXX", 'O', "obsidian"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianChestplate, 1), "OXO", "OOO", "OOO", 'O', "obsidian"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianLeggings, 1), "OOO", "OXO", "OXO", 'O', "obsidian"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBoots, 1), "XXX", "OXO", "OXO", 'O', "obsidian"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBoots, 1), "OXO", "OXO", "XXX", 'O', "obsidian"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableObsidianArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianHelmet, 1), "XXX", "OOO", "OXO", 'O', "blockCompressedObsidian"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianHelmet, 1), "OOO", "OXO", "XXX", 'O', "blockCompressedObsidian"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianChestplate, 1), "OXO", "OOO", "OOO", 'O', "blockCompressedObsidian"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianLeggings, 1), "OOO", "OXO", "OXO", 'O', "blockCompressedObsidian"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBoots, 1), "XXX", "OXO", "OXO", 'O', "blockCompressedObsidian"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianBoots, 1), "OXO", "OXO", "XXX", 'O', "blockCompressedObsidian"));
        }
        /* Redstone Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableRedstoneArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneHelmet, 1), "XXX", "RRR", "RXR", 'R', "dustRedstone"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneHelmet, 1), "RRR", "RXR", "XXX", 'R', "dustRedstone"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneChestplate, 1), "RXR", "RRR", "RRR", 'R', "dustRedstone"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneLeggings, 1), "RRR", "RXR", "RXR", 'R', "dustRedstone"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBoots, 1), "XXX", "RXR", "RXR", 'R', "dustRedstone"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBoots, 1), "RXR", "RXR", "XXX", 'R', "dustRedstone"));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableRedstoneArmorRecipes) {
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneHelmet, 1), "XXX", "RRR", "RXR", 'R', "blockRedstone"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneHelmet, 1), "RRR", "RXR", "XXX", 'R', "blockRedstone"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneChestplate, 1), "RXR", "RRR", "RRR", 'R', "blockRedstone"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneLeggings, 1), "RRR", "RXR", "RXR", 'R', "blockRedstone"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBoots, 1), "XXX", "RXR", "RXR", 'R', "blockRedstone"));
            manager.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redstoneBoots, 1), "RXR", "RXR", "XXX", 'R', "blockRedstone"));
        }
    }
}