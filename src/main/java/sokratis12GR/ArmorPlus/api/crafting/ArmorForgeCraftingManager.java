package sokratis12GR.ArmorPlus.api.crafting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sokratis12GR.ArmorPlus.armors.origin.*;
import sokratis12GR.ArmorPlus.armors.reinforced.RCArmor;
import sokratis12GR.ArmorPlus.armors.reinforced.RDArmor;
import sokratis12GR.ArmorPlus.armors.reinforced.RGArmor;
import sokratis12GR.ArmorPlus.armors.reinforced.RIArmor;
import sokratis12GR.ArmorPlus.armors.special.EnderDragonArmor;
import sokratis12GR.ArmorPlus.armors.special.GuardianArmor;
import sokratis12GR.ArmorPlus.armors.special.SuperStarArmor;
import sokratis12GR.ArmorPlus.armors.special.TheUltimateArmor;
import sokratis12GR.ArmorPlus.armors.special.mob.ChickenArmor;
import sokratis12GR.ArmorPlus.armors.special.mob.SlimeArmor;
import sokratis12GR.ArmorPlus.armors.tconstruct.*;
import sokratis12GR.ArmorPlus.registry.ModBlocks;
import sokratis12GR.ArmorPlus.registry.ModItems;
import sokratis12GR.ArmorPlus.resources.ConfigHandler;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * sokratis12GR.ArmorPlus.api.crafting
 * ArmorPlus created by sokratis12GR on 6/19/2016 12:29PM.
 */
public class ArmorForgeCraftingManager {
    /**
     * The
     * static instance of
     * this class
     */
    private static final ArmorForgeCraftingManager INSTANCE = new ArmorForgeCraftingManager();
    private final List<IRecipe> recipes = Lists.<IRecipe>newArrayList();

    /**
     * Returns the
     * static instance of
     * this class
     */
    public static ArmorForgeCraftingManager getInstance() {
        // The static instance of this class
        return INSTANCE;
    }

    private ArmorForgeCraftingManager() {
        // ===================================== Origin Armors =====================================
        /** Coal Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableCoalArmorRecipes) {
            this.addRecipe(new ItemStack(CoalArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', Items.COAL);
            this.addRecipe(new ItemStack(CoalArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', Items.COAL);
            this.addRecipe(new ItemStack(CoalArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', Items.COAL);
            this.addRecipe(new ItemStack(CoalArmor.legs, 1), "CCC", "CXC", "CXC", 'C', Items.COAL);
            this.addRecipe(new ItemStack(CoalArmor.boots, 1), "XXX", "CXC", "CXC", 'C', Items.COAL);
            this.addRecipe(new ItemStack(CoalArmor.boots, 1), "CXC", "CXC", "XXX", 'C', Items.COAL);
        }
        if (ConfigHandler.easyMode && ConfigHandler.enableCharcoalCoalArmorRecipe) {
            this.addRecipe(new ItemStack(CoalArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(CoalArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(CoalArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(CoalArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(CoalArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(CoalArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Items.COAL, 1, 1));
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableCoalArmorRecipes) {
            this.addRecipe(new ItemStack(CoalArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(CoalArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(CoalArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(CoalArmor.legs, 1), "CCC", "CXC", "CXC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(CoalArmor.boots, 1), "CXC", "CXC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(CoalArmor.boots, 1), "CXC", "CXC", "XXX", 'C', Blocks.COAL_BLOCK);
        }
        /** Emerald Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableEmeraldArmorRecipes) {
            this.addRecipe(new ItemStack(EmeraldArmor.helmet, 1), "XXX", "EEE", "EXE", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(EmeraldArmor.helmet, 1), "EEE", "EXE", "XXX", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(EmeraldArmor.chestplate, 1), "EXE", "EEE", "EEE", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(EmeraldArmor.legs, 1), "EEE", "EXE", "EXE", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(EmeraldArmor.boots, 1), "XXX", "EXE", "EXE", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(EmeraldArmor.boots, 1), "EXE", "EXE", "XXX", 'E', Items.EMERALD);
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableEmeraldArmorRecipes) {
            this.addRecipe(new ItemStack(EmeraldArmor.helmet, 1), "XXX", "EEE", "EXE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(EmeraldArmor.helmet, 1), "EEE", "EXE", "XXX", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(EmeraldArmor.chestplate, 1), "EXE", "EEE", "EEE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(EmeraldArmor.legs, 1), "EEE", "EXE", "EXE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(EmeraldArmor.boots, 1), "XXX", "EXE", "EXE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(EmeraldArmor.boots, 1), "EXE", "EXE", "XXX", 'E', Blocks.EMERALD_BLOCK);
        }
        /** Lapis Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableLapisArmorRecipes) {
            this.addRecipe(new ItemStack(LapisArmor.helmet, 1), "XXX", "LLL", "LXL", 'L', new ItemStack(Items.DYE, 1, 4));
            this.addRecipe(new ItemStack(LapisArmor.helmet, 1), "LLL", "LXL", "XXX", 'L', new ItemStack(Items.DYE, 1, 4));
            this.addRecipe(new ItemStack(LapisArmor.chestplate, 1), "LXL", "LLL", "LLL", 'L', new ItemStack(Items.DYE, 1, 4));
            this.addRecipe(new ItemStack(LapisArmor.legs, 1), "LLL", "LXL", "LXL", 'L', new ItemStack(Items.DYE, 1, 4));
            this.addRecipe(new ItemStack(LapisArmor.boots, 1), "XXX", "LXL", "LXL", 'L', new ItemStack(Items.DYE, 1, 4));
            this.addRecipe(new ItemStack(LapisArmor.boots, 1), "LXL", "LXL", "XXX", 'L', new ItemStack(Items.DYE, 1, 4));
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableLapisArmorRecipes) {
            this.addRecipe(new ItemStack(LapisArmor.helmet, 1), "XXX", "LLL", "LXL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(LapisArmor.helmet, 1), "LLL", "LXL", "XXX", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(LapisArmor.chestplate, 1), "LXL", "LLL", "LLL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(LapisArmor.legs, 1), "LLL", "LXL", "LXL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(LapisArmor.boots, 1), "XXX", "LXL", "LXL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(LapisArmor.boots, 1), "LXL", "LXL", "XXX", 'L', Blocks.LAPIS_BLOCK);
        }
        /** Lava Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableLavaArmorRecipes && ConfigHandler.enableOldLavaArmorRecipes) {
            this.addShapelessRecipe(new ItemStack(LavaArmor.helmet, 1), ObsidianArmor.helmet, Items.LAVA_BUCKET, ObsidianArmor.helmet);
            this.addShapelessRecipe(new ItemStack(LavaArmor.chestplate, 1), ObsidianArmor.chestplate, Items.LAVA_BUCKET, ObsidianArmor.chestplate);
            this.addShapelessRecipe(new ItemStack(LavaArmor.legs, 1), ObsidianArmor.legs, Items.LAVA_BUCKET, ObsidianArmor.legs);
            this.addShapelessRecipe(new ItemStack(LavaArmor.boots, 1), ObsidianArmor.boots, Items.LAVA_BUCKET, ObsidianArmor.boots);
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableLavaArmorRecipes && ConfigHandler.enableOldLavaArmorRecipes) {
            this.addShapelessRecipe(new ItemStack(LavaArmor.helmet, 1), ObsidianArmor.helmet, Items.LAVA_BUCKET, ObsidianArmor.helmet,
                    Items.LAVA_BUCKET);
            this.addShapelessRecipe(new ItemStack(LavaArmor.chestplate, 1), ObsidianArmor.chestplate, Items.LAVA_BUCKET, ObsidianArmor.chestplate,
                    Items.LAVA_BUCKET);
            this.addShapelessRecipe(new ItemStack(LavaArmor.legs, 1), ObsidianArmor.legs, Items.LAVA_BUCKET, ObsidianArmor.legs,
                    Items.LAVA_BUCKET);
            this.addShapelessRecipe(new ItemStack(LavaArmor.boots, 1), ObsidianArmor.boots, Items.LAVA_BUCKET, ObsidianArmor.boots,
                    Items.LAVA_BUCKET);
        }
        if (ConfigHandler.enableLavaArmorRecipes && !ConfigHandler.enableOldLavaArmorRecipes) {
            this.addRecipe(new ItemStack(LavaArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', ModItems.LAVA_CRYSTAL);
            this.addRecipe(new ItemStack(LavaArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', ModItems.LAVA_CRYSTAL);
            this.addRecipe(new ItemStack(LavaArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', ModItems.LAVA_CRYSTAL);
            this.addRecipe(new ItemStack(LavaArmor.legs, 1), "CCC", "CXC", "CXC", 'C', ModItems.LAVA_CRYSTAL);
            this.addRecipe(new ItemStack(LavaArmor.boots, 1), "XXX", "CXC", "CXC", 'C', ModItems.LAVA_CRYSTAL);
            this.addRecipe(new ItemStack(LavaArmor.boots, 1), "CXC", "CXC", "XXX", 'C', ModItems.LAVA_CRYSTAL);
        }
        /** Obsidian Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableObsidianArmorRecipes) {
            this.addRecipe(new ItemStack(ObsidianArmor.helmet, 1), "XXX", "OOO", "OXO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ObsidianArmor.helmet, 1), "OOO", "OXO", "XXX", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ObsidianArmor.chestplate, 1), "OXO", "OOO", "OOO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ObsidianArmor.legs, 1), "OOO", "OXO", "OXO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ObsidianArmor.boots, 1), "XXX", "OXO", "OXO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ObsidianArmor.boots, 1), "OXO", "OXO", "XXX", 'O', Blocks.OBSIDIAN);
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableObsidianArmorRecipes) {
            this.addRecipe(new ItemStack(ObsidianArmor.helmet, 1), "XXX", "OOO", "OXO", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
            this.addRecipe(new ItemStack(ObsidianArmor.helmet, 1), "OOO", "OXO", "XXX", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
            this.addRecipe(new ItemStack(ObsidianArmor.chestplate, 1), "OXO", "OOO", "OOO", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
            this.addRecipe(new ItemStack(ObsidianArmor.legs, 1), "OOO", "OXO", "OXO", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
            this.addRecipe(new ItemStack(ObsidianArmor.boots, 1), "XXX", "OXO", "OXO", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
            this.addRecipe(new ItemStack(ObsidianArmor.boots, 1), "OXO", "OXO", "XXX", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
        }
        /** Redstone Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableRedstoneArmorRecipes) {
            this.addRecipe(new ItemStack(RedstoneArmor.helmet, 1), "XXX", "RRR", "RXR", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(RedstoneArmor.helmet, 1), "RRR", "RXR", "XXX", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(RedstoneArmor.chestplate, 1), "RXR", "RRR", "RRR", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(RedstoneArmor.legs, 1), "RRR", "RXR", "RXR", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(RedstoneArmor.boots, 1), "XXX", "RXR", "RXR", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(RedstoneArmor.boots, 1), "RXR", "RXR", "XXX", 'R', Items.REDSTONE);
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableRedstoneArmorRecipes) {
            this.addRecipe(new ItemStack(RedstoneArmor.helmet, 1), "XXX", "RRR", "RXR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(RedstoneArmor.helmet, 1), "RRR", "RXR", "XXX", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(RedstoneArmor.chestplate, 1), "RXR", "RRR", "RRR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(RedstoneArmor.legs, 1), "RRR", "RXR", "RXR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(RedstoneArmor.boots, 1), "XXX", "RXR", "RXR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(RedstoneArmor.boots, 1), "RXR", "RXR", "XXX", 'R', Blocks.REDSTONE_BLOCK);
        }
        // ===================================== Reinforced Armors =====================================
        /**  Reinforced Chain Armor */
        if (ConfigHandler.enableReinforcedArmorsRecipes) {
            this.addRecipe(new ItemStack(RCArmor.helmet, 1), "XXX", "RRR", "RIR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_HELMET);
            this.addRecipe(new ItemStack(RCArmor.helmet, 1), "RRR", "RIR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_HELMET);
            this.addRecipe(new ItemStack(RCArmor.chestplate, 1), "RIR", "RRR", "RRR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_CHESTPLATE);
            this.addRecipe(new ItemStack(RCArmor.legs, 1), "RRR", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_LEGGINGS);
            this.addRecipe(new ItemStack(RCArmor.boots, 1), "XXX", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_BOOTS);
            this.addRecipe(new ItemStack(RCArmor.boots, 1), "RIR", "RXR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_BOOTS);
        }
        /**  Reinforced Diamond Armor */
        if (ConfigHandler.enableReinforcedArmorsRecipes) {
            this.addRecipe(new ItemStack(RDArmor.helmet, 1), "XXX", "RRR", "RIR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_HELMET);
            this.addRecipe(new ItemStack(RDArmor.helmet, 1), "RRR", "RIR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_HELMET);
            this.addRecipe(new ItemStack(RDArmor.chestplate, 1), "RIR", "RRR", "RRR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_CHESTPLATE);
            this.addRecipe(new ItemStack(RDArmor.legs, 1), "RRR", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_LEGGINGS);
            this.addRecipe(new ItemStack(RDArmor.boots, 1), "XXX", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_BOOTS);
            this.addRecipe(new ItemStack(RDArmor.boots, 1), "RIR", "RXR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_BOOTS);
        }
        /**  Reinforced Golden Armor */
        if (ConfigHandler.enableReinforcedArmorsRecipes) {
            this.addRecipe(new ItemStack(RGArmor.helmet, 1), "XXX", "RRR", "RIR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_HELMET);
            this.addRecipe(new ItemStack(RGArmor.helmet, 1), "RRR", "RIR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_HELMET);
            this.addRecipe(new ItemStack(RGArmor.chestplate, 1), "RIR", "RRR", "RRR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_CHESTPLATE);
            this.addRecipe(new ItemStack(RGArmor.legs, 1), "RRR", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_LEGGINGS);
            this.addRecipe(new ItemStack(RGArmor.boots, 1), "XXX", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_BOOTS);
            this.addRecipe(new ItemStack(RGArmor.boots, 1), "RIR", "RXR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_BOOTS);
        }
        /**  Reinforced Iron Armor */
        if (ConfigHandler.enableReinforcedArmorsRecipes) {
            this.addRecipe(new ItemStack(RIArmor.helmet, 1), "XXX", "RRR", "RIR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_HELMET);
            this.addRecipe(new ItemStack(RIArmor.helmet, 1), "RRR", "RIR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_HELMET);
            this.addRecipe(new ItemStack(RIArmor.chestplate, 1), "RIR", "RRR", "RRR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_CHESTPLATE);
            this.addRecipe(new ItemStack(RIArmor.legs, 1), "RRR", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_LEGGINGS);
            this.addRecipe(new ItemStack(RIArmor.boots, 1), "XXX", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_BOOTS);
            this.addRecipe(new ItemStack(RIArmor.boots, 1), "RIR", "RXR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_BOOTS);
        }
        // ===================================== Special Mob Armors =====================================
        /** Chicken Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableChickenArmorRecipes) {
            this.addRecipe(new ItemStack(ChickenArmor.helmet, 1), "XXX", "FFF", "FXF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ChickenArmor.helmet, 1), "FFF", "FXF", "XXX", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ChickenArmor.chestplate, 1), "FXF", "FFF", "FFF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ChickenArmor.legs, 1), "FFF", "FXF", "FXF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ChickenArmor.boots, 1), "XXX", "FXF", "FXF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ChickenArmor.boots, 1), "FXF", "FXF", "XXX", 'F', Items.FEATHER);
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableChickenArmorRecipes) {
            this.addRecipe(new ItemStack(ChickenArmor.helmet, 1), "XXX", "FFF", "EXE", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ChickenArmor.helmet, 1), "FFF", "EXE", "XXX", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ChickenArmor.chestplate, 1), "EXE", "FEF", "FFF", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ChickenArmor.legs, 1), "EFE", "FXF", "FXF", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ChickenArmor.boots, 1), "XXX", "FXF", "EXE", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ChickenArmor.boots, 1), "FXF", "EXE", "XXX", 'F', Items.FEATHER, 'E', Items.EGG);
        }
        /** Slime Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableSlimeArmorRecipes) {
            this.addRecipe(new ItemStack(SlimeArmor.helmet, 1), "XXX", "SSS", "SXS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(SlimeArmor.helmet, 1), "SSS", "SXS", "XXX", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(SlimeArmor.chestplate, 1), "SXS", "SSS", "SSS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(SlimeArmor.legs, 1), "SSS", "SXS", "SXS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(SlimeArmor.boots, 1), "XXX", "SXS", "SXS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(SlimeArmor.boots, 1), "SXS", "SXS", "XXX", 'S', Items.SLIME_BALL);
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableSlimeArmorRecipes) {
            this.addRecipe(new ItemStack(SlimeArmor.helmet, 1), "XXX", "SSS", "SXS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(SlimeArmor.helmet, 1), "SSS", "SXS", "XXX", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(SlimeArmor.chestplate, 1), "SXS", "SSS", "SSS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(SlimeArmor.legs, 1), "SSS", "SXS", "SXS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(SlimeArmor.boots, 1), "XXX", "SXS", "SXS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(SlimeArmor.boots, 1), "SXS", "SXS", "XXX", 'S', Blocks.SLIME_BLOCK);
        }
        // ===================================== Special Armors =====================================
        /** Ender Dragon Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableEnderDragonArmorRecipes) {
            this.addRecipe(new ItemStack(EnderDragonArmor.helmet, 1), "XXX", "EEE", "EXE", 'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(EnderDragonArmor.helmet, 1), "EEE", "EXE", "XXX", 'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(EnderDragonArmor.chestplate, 1), "EXE", "EEE", "EEE", 'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(EnderDragonArmor.legs, 1), "EEE", "EXE", "EXE", 'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(EnderDragonArmor.boots, 1), "XXX", "EXE", "EXE", 'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(EnderDragonArmor.boots, 1), "EXE", "EXE", "XXX", 'E', ModItems.ENDER_DRAGON_SCALE);
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableEnderDragonArmorRecipes) {
            this.addRecipe(new ItemStack(EnderDragonArmor.helmet, 1), "XXX", "EEE", "ESE", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', new ItemStack(Items.SKULL, 1, 5));
            this.addRecipe(new ItemStack(EnderDragonArmor.helmet, 1), "EEE", "ESE", "XXX", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', new ItemStack(Items.SKULL, 1, 5));
            this.addRecipe(new ItemStack(EnderDragonArmor.chestplate, 1), "ELE", "EEE", "EEE", 'E', ModItems.ENDER_DRAGON_SCALE, 'L', Items.ELYTRA);
            this.addRecipe(new ItemStack(EnderDragonArmor.legs, 1), "DED", "EXE", "EXE", 'E', ModItems.ENDER_DRAGON_SCALE, 'D', new ItemStack(Item.getByNameOrId("minecraft:dragon_egg")));
            this.addRecipe(new ItemStack(EnderDragonArmor.boots, 1), "XXX", "EXE", "ECE", 'E', ModItems.ENDER_DRAGON_SCALE, 'C', Items.END_CRYSTAL);
            this.addRecipe(new ItemStack(EnderDragonArmor.boots, 1), "EXE", "ECE", "XXX", 'E', ModItems.ENDER_DRAGON_SCALE, 'C', Items.END_CRYSTAL);
        }
        /** Guardian Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableGuardianArmorRecipes) {
            this.addRecipe(new ItemStack(GuardianArmor.helmet, 1), "XXX", "GGG", "GXG", 'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(GuardianArmor.helmet, 1), "GGG", "GXG", "XXX", 'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(GuardianArmor.chestplate, 1), "GXG", "GGG", "GGG", 'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(GuardianArmor.legs, 1), "GGG", "GXG", "GXG", 'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(GuardianArmor.boots, 1), "XXX", "GXG", "GXG", 'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(GuardianArmor.boots, 1), "GXG", "GXG", "XXX", 'G', ModItems.GUARDIAN_SCALE);
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableGuardianArmorRecipes) {
            this.addRecipe(new ItemStack(GuardianArmor.helmet, 1), "XXX", "GLG", "GXG", 'G', ModItems.GUARDIAN_SCALE, 'L', Blocks.SEA_LANTERN);
            this.addRecipe(new ItemStack(GuardianArmor.helmet, 1), "GLG", "GXG", "XXX", 'G', ModItems.GUARDIAN_SCALE, 'L', Blocks.SEA_LANTERN);
            this.addRecipe(new ItemStack(GuardianArmor.chestplate, 1), "SXS", "GLG", "CGC", 'G', ModItems.GUARDIAN_SCALE, 'C', Items.PRISMARINE_CRYSTALS, 'L', Blocks.SEA_LANTERN, 'S', Blocks.SPONGE);
            this.addRecipe(new ItemStack(GuardianArmor.legs, 1), "CGC", "GXG", "PXP", 'G', ModItems.GUARDIAN_SCALE, 'C', Items.PRISMARINE_CRYSTALS, 'P', Items.PRISMARINE_SHARD);
            this.addRecipe(new ItemStack(GuardianArmor.boots, 1), "XXX", "SXS", "GXG", 'G', ModItems.GUARDIAN_SCALE, 'S', Blocks.SPONGE);
            this.addRecipe(new ItemStack(GuardianArmor.boots, 1), "SXS", "GXG", "XXX", 'G', ModItems.GUARDIAN_SCALE, 'S', Blocks.SPONGE);
        }
        /** Super Star Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableSuperStarArmorRecipes) {
            this.addRecipe(new ItemStack(SuperStarArmor.helmet, 1), "XXX", "WWW", "WNW", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(SuperStarArmor.helmet, 1), "WWW", "WNW", "XXX", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(SuperStarArmor.chestplate, 1), "WNW", "WWW", "WWW", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(SuperStarArmor.legs, 1), "WWW", "WNW", "WXW", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(SuperStarArmor.boots, 1), "XXX", "WNW", "WXW", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(SuperStarArmor.boots, 1), "WNW", "WXW", "XXX", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableSuperStarArmorRecipes) {
            this.addRecipe(new ItemStack(SuperStarArmor.helmet, 1), "WWW", "WSW", "NXN", 'W', ModItems.WITHER_BONE, 'S', new ItemStack(Items.SKULL, 1, 1), 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(SuperStarArmor.chestplate, 1), "SXS", "NNN", "WWW", 'W', ModItems.WITHER_BONE, 'S', new ItemStack(Items.SKULL, 1, 1), 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(SuperStarArmor.legs, 1), "SWS", "WXW", "NXN", 'W', ModItems.WITHER_BONE, 'S', new ItemStack(Items.SKULL, 1, 1), 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(SuperStarArmor.boots, 1), "XXX", "WXW", "NXN", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(SuperStarArmor.boots, 1), "WXW", "NXN", "XXX", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
        }
        /** The Ultimate Armor */
        if (ConfigHandler.enableTheUltimateArmorRecipes) {
            this.addShapelessRecipe(new ItemStack(TheUltimateArmor.helmet, 1), new ItemStack(SuperStarArmor.helmet, 1), new ItemStack(EnderDragonArmor.helmet, 1), new ItemStack(GuardianArmor.helmet, 1), ModItems.THE_ULTIMATE_MATERIAL);
            this.addShapelessRecipe(new ItemStack(TheUltimateArmor.chestplate, 1), new ItemStack(SuperStarArmor.chestplate, 1), new ItemStack(EnderDragonArmor.chestplate, 1), new ItemStack(GuardianArmor.chestplate, 1), ModItems.THE_ULTIMATE_MATERIAL);
            this.addShapelessRecipe(new ItemStack(TheUltimateArmor.legs, 1), new ItemStack(SuperStarArmor.legs, 1), new ItemStack(EnderDragonArmor.legs, 1), new ItemStack(GuardianArmor.legs, 1), ModItems.THE_ULTIMATE_MATERIAL);
            this.addShapelessRecipe(new ItemStack(TheUltimateArmor.boots, 1), new ItemStack(SuperStarArmor.boots, 1), new ItemStack(EnderDragonArmor.boots, 1), new ItemStack(GuardianArmor.boots, 1), ModItems.THE_ULTIMATE_MATERIAL);
        }
        // ===================================== Tinkers' Construct Armors =====================================
        /** Ardite Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableArditeArmorRecipes) {
            this.addRecipe(new ItemStack(ArditeArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
            this.addRecipe(new ItemStack(ArditeArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
            this.addRecipe(new ItemStack(ArditeArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
            this.addRecipe(new ItemStack(ArditeArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
            this.addRecipe(new ItemStack(ArditeArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
            this.addRecipe(new ItemStack(ArditeArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableArditeArmorRecipes) {
            this.addRecipe(new ItemStack(ArditeArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
            this.addRecipe(new ItemStack(ArditeArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
            this.addRecipe(new ItemStack(ArditeArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
            this.addRecipe(new ItemStack(ArditeArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
            this.addRecipe(new ItemStack(ArditeArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
            this.addRecipe(new ItemStack(ArditeArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
        }
        /** Cobalt Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableCobaltArmorRecipes) {
            this.addRecipe(new ItemStack(CobaltArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            this.addRecipe(new ItemStack(CobaltArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            this.addRecipe(new ItemStack(CobaltArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            this.addRecipe(new ItemStack(CobaltArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            this.addRecipe(new ItemStack(CobaltArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            this.addRecipe(new ItemStack(CobaltArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableCobaltArmorRecipes) {
            this.addRecipe(new ItemStack(CobaltArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            this.addRecipe(new ItemStack(CobaltArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            this.addRecipe(new ItemStack(CobaltArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            this.addRecipe(new ItemStack(CobaltArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            this.addRecipe(new ItemStack(CobaltArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            this.addRecipe(new ItemStack(CobaltArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
        }
        /** Knight Slime Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableKnightSlimeArmorRecipes) {
            this.addRecipe(new ItemStack(KnightSlimeArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
            this.addRecipe(new ItemStack(KnightSlimeArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
            this.addRecipe(new ItemStack(KnightSlimeArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
            this.addRecipe(new ItemStack(KnightSlimeArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
            this.addRecipe(new ItemStack(KnightSlimeArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
            this.addRecipe(new ItemStack(KnightSlimeArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableKnightSlimeArmorRecipes) {
            this.addRecipe(new ItemStack(KnightSlimeArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
            this.addRecipe(new ItemStack(KnightSlimeArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
            this.addRecipe(new ItemStack(KnightSlimeArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
            this.addRecipe(new ItemStack(KnightSlimeArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
            this.addRecipe(new ItemStack(KnightSlimeArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
            this.addRecipe(new ItemStack(KnightSlimeArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
        }
        /** Manyullyn Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enableManyullynArmorRecipes) {
            this.addRecipe(new ItemStack(ManyullynArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
            this.addRecipe(new ItemStack(ManyullynArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
            this.addRecipe(new ItemStack(ManyullynArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
            this.addRecipe(new ItemStack(ManyullynArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
            this.addRecipe(new ItemStack(ManyullynArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
            this.addRecipe(new ItemStack(ManyullynArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
        }
        if (ConfigHandler.expertMode && ConfigHandler.enableManyullynArmorRecipes) {
            this.addRecipe(new ItemStack(ManyullynArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
            this.addRecipe(new ItemStack(ManyullynArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
            this.addRecipe(new ItemStack(ManyullynArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
            this.addRecipe(new ItemStack(ManyullynArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
            this.addRecipe(new ItemStack(ManyullynArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
            this.addRecipe(new ItemStack(ManyullynArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
        }
        /** Pig Iron Armor */
        if (ConfigHandler.easyMode && ConfigHandler.enablePigIronArmorRecipes) {
            this.addRecipe(new ItemStack(PigIronArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
            this.addRecipe(new ItemStack(PigIronArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
            this.addRecipe(new ItemStack(PigIronArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
            this.addRecipe(new ItemStack(PigIronArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
            this.addRecipe(new ItemStack(PigIronArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
            this.addRecipe(new ItemStack(PigIronArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
        }
        if (ConfigHandler.expertMode && ConfigHandler.enablePigIronArmorRecipes) {
            this.addRecipe(new ItemStack(PigIronArmor.helmet, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
            this.addRecipe(new ItemStack(PigIronArmor.helmet, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
            this.addRecipe(new ItemStack(PigIronArmor.chestplate, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
            this.addRecipe(new ItemStack(PigIronArmor.legs, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
            this.addRecipe(new ItemStack(PigIronArmor.boots, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
            this.addRecipe(new ItemStack(PigIronArmor.boots, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
        }
        // ===================================== Items =====================================
        this.addRecipe(new ItemStack(ModItems.REINFORCING_MATERIAL, 4), new Object[]{"XSX", "SBS", "XSX", 'S', Items.STRING, 'B', Items.SLIME_BALL});
        this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_MATERIAL, 1), ModItems.ENDER_DRAGON_SCALE, ModItems.GUARDIAN_SCALE, ModItems.WITHER_BONE);
        //this.addRecipe(new ItemStack(Blocks.TNT, 1), new Object[]{"X#X", "#X#", "X#X", 'X', Items.GUNPOWDER, '#', Blocks.SAND});
        Collections.sort(this.recipes, new Comparator<IRecipe>() {
            public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_) {
                return p_compare_1_ instanceof ShapelessRecipes && p_compare_2_ instanceof ShapedRecipes ? 1 : (p_compare_2_ instanceof ShapelessRecipes && p_compare_1_ instanceof ShapedRecipes ? -1 : (p_compare_2_.getRecipeSize() < p_compare_1_.getRecipeSize() ? -1 : (p_compare_2_.getRecipeSize() > p_compare_1_.getRecipeSize() ? 1 : 0)));
            }
        });
    }

    /**
     * Adds a shaped recipe to the games recipe list.
     */
    public ShapedRecipes addRecipe(ItemStack stack, Object... recipeComponents) {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeComponents[i] instanceof String[]) {
            String[] astring = (String[]) ((String[]) recipeComponents[i++]);

            for (int l = 0; l < astring.length; ++l) {
                String s2 = astring[l];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        } else {
            while (recipeComponents[i] instanceof String) {
                String s1 = (String) recipeComponents[i++];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }

        Map<Character, ItemStack> map;

        for (map = Maps.<Character, ItemStack>newHashMap(); i < recipeComponents.length; i += 2) {
            Character character = (Character) recipeComponents[i];
            ItemStack itemstack = null;

            if (recipeComponents[i + 1] instanceof Item) {
                itemstack = new ItemStack((Item) recipeComponents[i + 1]);
            } else if (recipeComponents[i + 1] instanceof Block) {
                itemstack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
            } else if (recipeComponents[i + 1] instanceof ItemStack) {
                itemstack = (ItemStack) recipeComponents[i + 1];
            }

            map.put(character, itemstack);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1) {
            char c0 = s.charAt(i1);

            if (map.containsKey(Character.valueOf(c0))) {
                aitemstack[i1] = ((ItemStack) map.get(Character.valueOf(c0))).copy();
            } else {
                aitemstack[i1] = null;
            }
        }

        ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    /**
     * Adds a shapeless crafting recipe to the the game.
     */

    public void addShapelessRecipe(ItemStack stack, Object... recipeComponents) {
        List<ItemStack> list = Lists.<ItemStack>newArrayList();

        for (Object object : recipeComponents) {
            if (object instanceof ItemStack) {
                list.add(((ItemStack) object).copy());
            } else if (object instanceof Item) {
                list.add(new ItemStack((Item) object));
            } else {
                if (!(object instanceof Block)) {
                    throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!");
                }

                list.add(new ItemStack((Block) object));
            }
        }

        this.recipes.add(new ShapelessRecipes(stack, list));
    }

    /**
     * Adds an IRecipe to the list of crafting recipes.
     */
    public void addRecipe(IRecipe recipe) {
        this.recipes.add(recipe);
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     */
    @Nullable
    public ItemStack findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getCraftingResult(craftMatrix);
            }
        }

        return null;
    }

    public ItemStack[] getRemainingItems(InventoryCrafting craftMatrix, World worldIn) {
        for (IRecipe irecipe : this.recipes) {
            if (irecipe.matches(craftMatrix, worldIn)) {
                return irecipe.getRemainingItems(craftMatrix);
            }
        }

        ItemStack[] aitemstack = new ItemStack[craftMatrix.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i) {
            aitemstack[i] = craftMatrix.getStackInSlot(i);
        }

        return aitemstack;
    }

    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }
}