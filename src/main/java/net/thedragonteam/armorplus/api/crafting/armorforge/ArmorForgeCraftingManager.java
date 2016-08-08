package net.thedragonteam.armorplus.api.crafting.armorforge;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.registry.ModItems;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * sokratis12gr.armorplus.api.crafting
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
    
    NBTTagCompound nbttagc = new NBTTagCompound();
    
    ItemStack COAL_HELMET = new ItemStack(ModItems.COAL_HELMET, 1);
    ItemStack EMERALD_HELMET = new ItemStack(ModItems.EMERALD_HELMET, 1);
    ItemStack LAPIS_HELMET = new ItemStack(ModItems.LAPIS_HELMET, 1);
    ItemStack LAVA_HELMET = new ItemStack(ModItems.LAVA_HELMET, 1);
    ItemStack OBSIDIAN_HELMET = new ItemStack(ModItems.OBSIDIAN_HELMET);
    ItemStack REDSTONE_HELMET = new ItemStack(ModItems.REDSTONE_HELMET, 1);
    
    private ArmorForgeCraftingManager() {
    	
    	// ===================================== Set Variables =====================================
    	
    	/** NBT-Tag-Compound */
    	nbttagc.setInteger("theoneprobe", 1);
    	
    	/** Set Helmets' NBT-Tags */
    	COAL_HELMET.setTagCompound(nbttagc);
    	EMERALD_HELMET.setTagCompound(nbttagc);
    	LAPIS_HELMET.setTagCompound(nbttagc);
    	LAVA_HELMET.setTagCompound(nbttagc);
    	OBSIDIAN_HELMET.setTagCompound(nbttagc);
    	REDSTONE_HELMET.setTagCompound(nbttagc);
        
    	// ===================================== Origin Armors =====================================
        /** Coal Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableCoalArmorRecipes) {
            this.addRecipe(COAL_HELMET, "XXX", "CCC", "CXC", 'C', Items.COAL);
            this.addRecipe(COAL_HELMET, "CCC", "CXC", "XXX", 'C', Items.COAL);
            this.addRecipe(new ItemStack(ModItems.COAL_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', Items.COAL);
            this.addRecipe(new ItemStack(ModItems.COAL_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', Items.COAL);
            this.addRecipe(new ItemStack(ModItems.COAL_BOOTS, 1), "XXX", "CXC", "CXC", 'C', Items.COAL);
            this.addRecipe(new ItemStack(ModItems.COAL_BOOTS, 1), "CXC", "CXC", "XXX", 'C', Items.COAL);
        }
        if (ARPConfig.recipes == 0 && ARPConfig.enableCharcoalCoalArmorRecipe) {
            this.addRecipe(COAL_HELMET, "XXX", "CCC", "CXC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(COAL_HELMET, "CCC", "CXC", "XXX", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(ModItems.COAL_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(ModItems.COAL_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(ModItems.COAL_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Items.COAL, 1, 1));
            this.addRecipe(new ItemStack(ModItems.COAL_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Items.COAL, 1, 1));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableCoalArmorRecipes) {
            this.addRecipe(COAL_HELMET, "XXX", "CCC", "CXC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(COAL_HELMET, "CCC", "CXC", "XXX", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.COAL_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.COAL_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.COAL_BOOTS, 1), "CXC", "CXC", 'C', Blocks.COAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.COAL_BOOTS, 1), "CXC", "CXC", "XXX", 'C', Blocks.COAL_BLOCK);
        }
        /** Emerald Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableEmeraldArmorRecipes) {
            this.addRecipe(EMERALD_HELMET, "XXX", "EEE", "EXE", 'E', Items.EMERALD);
            this.addRecipe(EMERALD_HELMET, "EEE", "EXE", "XXX", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(ModItems.EMERALD_CHESTPLATE, 1), "EXE", "EEE", "EEE", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(ModItems.EMERALD_LEGGINGS, 1), "EEE", "EXE", "EXE", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(ModItems.EMERALD_BOOTS, 1), "XXX", "EXE", "EXE", 'E', Items.EMERALD);
            this.addRecipe(new ItemStack(ModItems.EMERALD_BOOTS, 1), "EXE", "EXE", "XXX", 'E', Items.EMERALD);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableEmeraldArmorRecipes) {
            this.addRecipe(EMERALD_HELMET, "XXX", "EEE", "EXE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(EMERALD_HELMET, "EEE", "EXE", "XXX", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(ModItems.EMERALD_CHESTPLATE, 1), "EXE", "EEE", "EEE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(ModItems.EMERALD_LEGGINGS, 1), "EEE", "EXE", "EXE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(ModItems.EMERALD_BOOTS, 1), "XXX", "EXE", "EXE", 'E', Blocks.EMERALD_BLOCK);
            this.addRecipe(new ItemStack(ModItems.EMERALD_BOOTS, 1), "EXE", "EXE", "XXX", 'E', Blocks.EMERALD_BLOCK);
        }
        /** Lapis Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableLapisArmorRecipes) {
            this.addRecipe(LAPIS_HELMET, "XXX", "LLL", "LXL", 'L', new ItemStack(Items.DYE, 1, 4));
            this.addRecipe(LAPIS_HELMET, "LLL", "LXL", "XXX", 'L', new ItemStack(Items.DYE, 1, 4));
            this.addRecipe(new ItemStack(ModItems.LAPIS_CHESTPLATE, 1), "LXL", "LLL", "LLL", 'L', new ItemStack(Items.DYE, 1, 4));
            this.addRecipe(new ItemStack(ModItems.LAPIS_LEGGINGS, 1), "LLL", "LXL", "LXL", 'L', new ItemStack(Items.DYE, 1, 4));
            this.addRecipe(new ItemStack(ModItems.LAPIS_BOOTS, 1), "XXX", "LXL", "LXL", 'L', new ItemStack(Items.DYE, 1, 4));
            this.addRecipe(new ItemStack(ModItems.LAPIS_BOOTS, 1), "LXL", "LXL", "XXX", 'L', new ItemStack(Items.DYE, 1, 4));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableLapisArmorRecipes) {
            this.addRecipe(LAPIS_HELMET, "XXX", "LLL", "LXL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(LAPIS_HELMET, "LLL", "LXL", "XXX", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(ModItems.LAPIS_CHESTPLATE, 1), "LXL", "LLL", "LLL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(ModItems.LAPIS_LEGGINGS, 1), "LLL", "LXL", "LXL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(ModItems.LAPIS_BOOTS, 1), "XXX", "LXL", "LXL", 'L', Blocks.LAPIS_BLOCK);
            this.addRecipe(new ItemStack(ModItems.LAPIS_BOOTS, 1), "LXL", "LXL", "XXX", 'L', Blocks.LAPIS_BLOCK);
        }
        /** Lava Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableLavaArmorRecipes && ARPConfig.enableOldLavaArmorRecipes) {
            this.addShapelessRecipe(LAVA_HELMET, ModItems.OBSIDIAN_HELMET, Items.LAVA_BUCKET, ModItems.OBSIDIAN_HELMET);
            this.addShapelessRecipe(new ItemStack(ModItems.LAVA_CHESTPLATE, 1), ModItems.OBSIDIAN_CHESTPLATE, Items.LAVA_BUCKET, ModItems.OBSIDIAN_CHESTPLATE);
            this.addShapelessRecipe(new ItemStack(ModItems.LAVA_LEGGINGS, 1), ModItems.OBSIDIAN_LEGGINGS, Items.LAVA_BUCKET, ModItems.OBSIDIAN_LEGGINGS);
            this.addShapelessRecipe(new ItemStack(ModItems.LAVA_BOOTS, 1), ModItems.OBSIDIAN_BOOTS, Items.LAVA_BUCKET, ModItems.OBSIDIAN_BOOTS);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableLavaArmorRecipes && ARPConfig.enableOldLavaArmorRecipes) {
            this.addShapelessRecipe(LAVA_HELMET, ModItems.OBSIDIAN_HELMET, Items.LAVA_BUCKET, ModItems.OBSIDIAN_HELMET,
                    Items.LAVA_BUCKET);
            this.addShapelessRecipe(new ItemStack(ModItems.LAVA_CHESTPLATE, 1), ModItems.OBSIDIAN_CHESTPLATE, Items.LAVA_BUCKET, ModItems.OBSIDIAN_CHESTPLATE,
                    Items.LAVA_BUCKET);
            this.addShapelessRecipe(new ItemStack(ModItems.LAVA_LEGGINGS, 1), ModItems.OBSIDIAN_LEGGINGS, Items.LAVA_BUCKET, ModItems.OBSIDIAN_LEGGINGS,
                    Items.LAVA_BUCKET);
            this.addShapelessRecipe(new ItemStack(ModItems.LAVA_BOOTS, 1), ModItems.OBSIDIAN_BOOTS, Items.LAVA_BUCKET, ModItems.OBSIDIAN_BOOTS,
                    Items.LAVA_BUCKET);
        }
        if (ARPConfig.enableLavaArmorRecipes && !ARPConfig.enableOldLavaArmorRecipes) {
            this.addRecipe(LAVA_HELMET, "XXX", "CCC", "CXC", 'C', ModItems.LAVA_CRYSTAL);
            this.addRecipe(LAVA_HELMET, "CCC", "CXC", "XXX", 'C', ModItems.LAVA_CRYSTAL);
            this.addRecipe(new ItemStack(ModItems.LAVA_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', ModItems.LAVA_CRYSTAL);
            this.addRecipe(new ItemStack(ModItems.LAVA_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', ModItems.LAVA_CRYSTAL);
            this.addRecipe(new ItemStack(ModItems.LAVA_BOOTS, 1), "XXX", "CXC", "CXC", 'C', ModItems.LAVA_CRYSTAL);
            this.addRecipe(new ItemStack(ModItems.LAVA_BOOTS, 1), "CXC", "CXC", "XXX", 'C', ModItems.LAVA_CRYSTAL);
        }
        /** Obsidian Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableObsidianArmorRecipes) {
            this.addRecipe(OBSIDIAN_HELMET, "XXX", "OOO", "OXO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(OBSIDIAN_HELMET, "OOO", "OXO", "XXX", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.OBSIDIAN_CHESTPLATE, 1), "OXO", "OOO", "OOO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.OBSIDIAN_LEGGINGS, 1), "OOO", "OXO", "OXO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOOTS, 1), "XXX", "OXO", "OXO", 'O', Blocks.OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOOTS, 1), "OXO", "OXO", "XXX", 'O', Blocks.OBSIDIAN);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableObsidianArmorRecipes) {
            this.addRecipe(OBSIDIAN_HELMET, "XXX", "OOO", "OXO", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
            this.addRecipe(OBSIDIAN_HELMET, "OOO", "OXO", "XXX", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.OBSIDIAN_CHESTPLATE, 1), "OXO", "OOO", "OOO", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.OBSIDIAN_LEGGINGS, 1), "OOO", "OXO", "OXO", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOOTS, 1), "XXX", "OXO", "OXO", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
            this.addRecipe(new ItemStack(ModItems.OBSIDIAN_BOOTS, 1), "OXO", "OXO", "XXX", 'O', ModBlocks.COMPRESSED_OBSIDIAN);
        }
        /** Redstone Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableRedstoneArmorRecipes) {
            this.addRecipe(REDSTONE_HELMET, "XXX", "RRR", "RXR", 'R', Items.REDSTONE);
            this.addRecipe(REDSTONE_HELMET, "RRR", "RXR", "XXX", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(ModItems.REDSTONE_CHESTPLATE, 1), "RXR", "RRR", "RRR", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(ModItems.REDSTONE_LEGGINGS, 1), "RRR", "RXR", "RXR", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(ModItems.REDSTONE_BOOTS, 1), "XXX", "RXR", "RXR", 'R', Items.REDSTONE);
            this.addRecipe(new ItemStack(ModItems.REDSTONE_BOOTS, 1), "RXR", "RXR", "XXX", 'R', Items.REDSTONE);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableRedstoneArmorRecipes) {
            this.addRecipe(REDSTONE_HELMET, "XXX", "RRR", "RXR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(REDSTONE_HELMET, "RRR", "RXR", "XXX", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(ModItems.REDSTONE_CHESTPLATE, 1), "RXR", "RRR", "RRR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(ModItems.REDSTONE_LEGGINGS, 1), "RRR", "RXR", "RXR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(ModItems.REDSTONE_BOOTS, 1), "XXX", "RXR", "RXR", 'R', Blocks.REDSTONE_BLOCK);
            this.addRecipe(new ItemStack(ModItems.REDSTONE_BOOTS, 1), "RXR", "RXR", "XXX", 'R', Blocks.REDSTONE_BLOCK);
        }
        // ===================================== Reinforced Armors =====================================
        /**  Reinforced Chain Armor */
        if (ARPConfig.enableReinforcedArmorsRecipes) {
            this.addRecipe(new ItemStack(ModItems.RC_HELMET, 1), "XXX", "RRR", "RIR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_HELMET);
            this.addRecipe(new ItemStack(ModItems.RC_HELMET, 1), "RRR", "RIR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_HELMET);
            this.addRecipe(new ItemStack(ModItems.RC_CHESTPLATE, 1), "RIR", "RRR", "RRR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_CHESTPLATE);
            this.addRecipe(new ItemStack(ModItems.RC_LEGGINGS, 1), "RRR", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_LEGGINGS);
            this.addRecipe(new ItemStack(ModItems.RC_BOOTS, 1), "XXX", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_BOOTS);
            this.addRecipe(new ItemStack(ModItems.RC_BOOTS, 1), "RIR", "RXR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.CHAINMAIL_BOOTS);
        }
        /**  Reinforced Diamond Armor */
        if (ARPConfig.enableReinforcedArmorsRecipes) {
            this.addRecipe(new ItemStack(ModItems.RD_HELMET, 1), "XXX", "RRR", "RIR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_HELMET);
            this.addRecipe(new ItemStack(ModItems.RD_HELMET, 1), "RRR", "RIR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_HELMET);
            this.addRecipe(new ItemStack(ModItems.RD_CHESTPLATE, 1), "RIR", "RRR", "RRR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_CHESTPLATE);
            this.addRecipe(new ItemStack(ModItems.RD_LEGGINGS, 1), "RRR", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_LEGGINGS);
            this.addRecipe(new ItemStack(ModItems.RD_BOOTS, 1), "XXX", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_BOOTS);
            this.addRecipe(new ItemStack(ModItems.RD_BOOTS, 1), "RIR", "RXR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.DIAMOND_BOOTS);
        }
        /**  Reinforced Golden Armor */
        if (ARPConfig.enableReinforcedArmorsRecipes) {
            this.addRecipe(new ItemStack(ModItems.RG_HELMET, 1), "XXX", "RRR", "RIR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_HELMET);
            this.addRecipe(new ItemStack(ModItems.RG_HELMET, 1), "RRR", "RIR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_HELMET);
            this.addRecipe(new ItemStack(ModItems.RG_CHESTPLATE, 1), "RIR", "RRR", "RRR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_CHESTPLATE);
            this.addRecipe(new ItemStack(ModItems.RG_LEGGINGS, 1), "RRR", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_LEGGINGS);
            this.addRecipe(new ItemStack(ModItems.RG_BOOTS, 1), "XXX", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_BOOTS);
            this.addRecipe(new ItemStack(ModItems.RG_BOOTS, 1), "RIR", "RXR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.GOLDEN_BOOTS);
        }
        /**  Reinforced Iron Armor */
        if (ARPConfig.enableReinforcedArmorsRecipes) {
            this.addRecipe(new ItemStack(ModItems.RI_HELMET, 1), "XXX", "RRR", "RIR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_HELMET);
            this.addRecipe(new ItemStack(ModItems.RI_HELMET, 1), "RRR", "RIR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_HELMET);
            this.addRecipe(new ItemStack(ModItems.RI_CHESTPLATE, 1), "RIR", "RRR", "RRR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_CHESTPLATE);
            this.addRecipe(new ItemStack(ModItems.RI_LEGGINGS, 1), "RRR", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_LEGGINGS);
            this.addRecipe(new ItemStack(ModItems.RI_BOOTS, 1), "XXX", "RIR", "RXR", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_BOOTS);
            this.addRecipe(new ItemStack(ModItems.RI_BOOTS, 1), "RIR", "RXR", "XXX", 'R', ModItems.REINFORCING_MATERIAL, 'I', Items.IRON_BOOTS);
        }
        // ===================================== Special Mob Armors =====================================
        /** Chicken Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableChickenArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.CHICKEN_HELMET, 1), "XXX", "FFF", "FXF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ModItems.CHICKEN_HELMET, 1), "FFF", "FXF", "XXX", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ModItems.CHICKEN_CHESTPLATE, 1), "FXF", "FFF", "FFF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ModItems.CHICKEN_LEGGINGS, 1), "FFF", "FXF", "FXF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ModItems.CHICKEN_BOOTS, 1), "XXX", "FXF", "FXF", 'F', Items.FEATHER);
            this.addRecipe(new ItemStack(ModItems.CHICKEN_BOOTS, 1), "FXF", "FXF", "XXX", 'F', Items.FEATHER);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableChickenArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.CHICKEN_HELMET, 1), "XXX", "FFF", "EXE", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ModItems.CHICKEN_HELMET, 1), "FFF", "EXE", "XXX", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ModItems.CHICKEN_CHESTPLATE, 1), "EXE", "FEF", "FFF", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ModItems.CHICKEN_LEGGINGS, 1), "EFE", "FXF", "FXF", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ModItems.CHICKEN_BOOTS, 1), "XXX", "FXF", "EXE", 'F', Items.FEATHER, 'E', Items.EGG);
            this.addRecipe(new ItemStack(ModItems.CHICKEN_BOOTS, 1), "FXF", "EXE", "XXX", 'F', Items.FEATHER, 'E', Items.EGG);
        }
        /** Slime Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableSlimeArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.SLIME_HELMET, 1), "XXX", "SSS", "SXS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(ModItems.SLIME_HELMET, 1), "SSS", "SXS", "XXX", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(ModItems.SLIME_CHESTPLATE, 1), "SXS", "SSS", "SSS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(ModItems.SLIME_LEGGINGS, 1), "SSS", "SXS", "SXS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(ModItems.SLIME_BOOTS, 1), "XXX", "SXS", "SXS", 'S', Items.SLIME_BALL);
            this.addRecipe(new ItemStack(ModItems.SLIME_BOOTS, 1), "SXS", "SXS", "XXX", 'S', Items.SLIME_BALL);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableSlimeArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.SLIME_HELMET, 1), "XXX", "SSS", "SXS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(ModItems.SLIME_HELMET, 1), "SSS", "SXS", "XXX", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(ModItems.SLIME_CHESTPLATE, 1), "SXS", "SSS", "SSS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(ModItems.SLIME_LEGGINGS, 1), "SSS", "SXS", "SXS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(ModItems.SLIME_BOOTS, 1), "XXX", "SXS", "SXS", 'S', Blocks.SLIME_BLOCK);
            this.addRecipe(new ItemStack(ModItems.SLIME_BOOTS, 1), "SXS", "SXS", "XXX", 'S', Blocks.SLIME_BLOCK);
        }
        // ===================================== Special Armors =====================================
        /** Ender Dragon Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableEnderDragonArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_HELMET, 1), "XXX", "EEE", "EXE", 'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_HELMET, 1), "EEE", "EXE", "XXX", 'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_CHESTPLATE, 1), "EXE", "EEE", "EEE", 'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_LEGGINGS, 1), "EEE", "EXE", "EXE", 'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOOTS, 1), "XXX", "EXE", "EXE", 'E', ModItems.ENDER_DRAGON_SCALE);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOOTS, 1), "EXE", "EXE", "XXX", 'E', ModItems.ENDER_DRAGON_SCALE);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableEnderDragonArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_HELMET, 1), "XXX", "EEE", "ESE", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', new ItemStack(Items.SKULL, 1, 5));
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_HELMET, 1), "EEE", "ESE", "XXX", 'E', ModItems.ENDER_DRAGON_SCALE, 'S', new ItemStack(Items.SKULL, 1, 5));
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_CHESTPLATE, 1), "ELE", "EEE", "EEE", 'E', ModItems.ENDER_DRAGON_SCALE, 'L', Items.ELYTRA);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_LEGGINGS, 1), "DED", "EXE", "EXE", 'E', ModItems.ENDER_DRAGON_SCALE, 'D', new ItemStack(Item.getByNameOrId("minecraft:dragon_egg")));
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOOTS, 1), "XXX", "EXE", "ECE", 'E', ModItems.ENDER_DRAGON_SCALE, 'C', Items.END_CRYSTAL);
            this.addRecipe(new ItemStack(ModItems.ENDER_DRAGON_BOOTS, 1), "EXE", "ECE", "XXX", 'E', ModItems.ENDER_DRAGON_SCALE, 'C', Items.END_CRYSTAL);
        }
        /** Guardian Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableGuardianArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_HELMET, 1), "XXX", "GGG", "GXG", 'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_HELMET, 1), "GGG", "GXG", "XXX", 'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_CHESTPLATE, 1), "GXG", "GGG", "GGG", 'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_LEGGINGS, 1), "GGG", "GXG", "GXG", 'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_BOOTS, 1), "XXX", "GXG", "GXG", 'G', ModItems.GUARDIAN_SCALE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_BOOTS, 1), "GXG", "GXG", "XXX", 'G', ModItems.GUARDIAN_SCALE);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableGuardianArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_HELMET, 1), "XXX", "GLG", "GXG", 'G', ModItems.GUARDIAN_SCALE, 'L', Blocks.SEA_LANTERN);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_HELMET, 1), "GLG", "GXG", "XXX", 'G', ModItems.GUARDIAN_SCALE, 'L', Blocks.SEA_LANTERN);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_CHESTPLATE, 1), "SXS", "GLG", "CGC", 'G', ModItems.GUARDIAN_SCALE, 'C', Items.PRISMARINE_CRYSTALS, 'L', Blocks.SEA_LANTERN, 'S', Blocks.SPONGE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_LEGGINGS, 1), "CGC", "GXG", "PXP", 'G', ModItems.GUARDIAN_SCALE, 'C', Items.PRISMARINE_CRYSTALS, 'P', Items.PRISMARINE_SHARD);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_BOOTS, 1), "XXX", "SXS", "GXG", 'G', ModItems.GUARDIAN_SCALE, 'S', Blocks.SPONGE);
            this.addRecipe(new ItemStack(ModItems.GUARDIAN_BOOTS, 1), "SXS", "GXG", "XXX", 'G', ModItems.GUARDIAN_SCALE, 'S', Blocks.SPONGE);
        }
        /** Super Star Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableSuperStarArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_HELMET, 1), "XXX", "WWW", "WNW", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_HELMET, 1), "WWW", "WNW", "XXX", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_CHESTPLATE, 1), "WNW", "WWW", "WWW", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_LEGGINGS, 1), "WWW", "WNW", "WXW", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOOTS, 1), "XXX", "WNW", "WXW", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOOTS, 1), "WNW", "WXW", "XXX", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableSuperStarArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_HELMET, 1), "WWW", "WSW", "NXN", 'W', ModItems.WITHER_BONE, 'S', new ItemStack(Items.SKULL, 1, 1), 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_CHESTPLATE, 1), "SXS", "NNN", "WWW", 'W', ModItems.WITHER_BONE, 'S', new ItemStack(Items.SKULL, 1, 1), 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_LEGGINGS, 1), "SWS", "WXW", "NXN", 'W', ModItems.WITHER_BONE, 'S', new ItemStack(Items.SKULL, 1, 1), 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOOTS, 1), "XXX", "WXW", "NXN", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
            this.addRecipe(new ItemStack(ModItems.SUPER_STAR_BOOTS, 1), "WXW", "NXN", "XXX", 'W', ModItems.WITHER_BONE, 'N', Items.NETHER_STAR);
        }
        /** The Ultimate Armor */
        if (ARPConfig.enableTheUltimateArmorRecipes) {
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_HELMET, 1), new ItemStack(ModItems.SUPER_STAR_HELMET, 1), new ItemStack(ModItems.ENDER_DRAGON_HELMET, 1), new ItemStack(ModItems.GUARDIAN_HELMET, 1), ModItems.THE_ULTIMATE_MATERIAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_CHESTPLATE, 1), new ItemStack(ModItems.SUPER_STAR_CHESTPLATE, 1), new ItemStack(ModItems.ENDER_DRAGON_CHESTPLATE, 1), new ItemStack(ModItems.GUARDIAN_CHESTPLATE, 1), ModItems.THE_ULTIMATE_MATERIAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_LEGGINGS, 1), new ItemStack(ModItems.SUPER_STAR_LEGGINGS, 1), new ItemStack(ModItems.ENDER_DRAGON_LEGGINGS, 1), new ItemStack(ModItems.GUARDIAN_LEGGINGS, 1), ModItems.THE_ULTIMATE_MATERIAL);
            this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_BOOTS, 1), new ItemStack(ModItems.SUPER_STAR_BOOTS, 1), new ItemStack(ModItems.ENDER_DRAGON_BOOTS, 1), new ItemStack(ModItems.GUARDIAN_BOOTS, 1), ModItems.THE_ULTIMATE_MATERIAL);
        }
        // ===================================== Tinkers' Construct Armors =====================================
        /** Ardite Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableArditeArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.ARDITE_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
            this.addRecipe(new ItemStack(ModItems.ARDITE_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
            this.addRecipe(new ItemStack(ModItems.ARDITE_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
            this.addRecipe(new ItemStack(ModItems.ARDITE_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
            this.addRecipe(new ItemStack(ModItems.ARDITE_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
            this.addRecipe(new ItemStack(ModItems.ARDITE_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 1));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableArditeArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.ARDITE_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
            this.addRecipe(new ItemStack(ModItems.ARDITE_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
            this.addRecipe(new ItemStack(ModItems.ARDITE_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
            this.addRecipe(new ItemStack(ModItems.ARDITE_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
            this.addRecipe(new ItemStack(ModItems.ARDITE_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
            this.addRecipe(new ItemStack(ModItems.ARDITE_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 1));
        }
        /** Cobalt Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableCobaltArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.COBALT_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            this.addRecipe(new ItemStack(ModItems.COBALT_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            this.addRecipe(new ItemStack(ModItems.COBALT_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            this.addRecipe(new ItemStack(ModItems.COBALT_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            this.addRecipe(new ItemStack(ModItems.COBALT_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
            this.addRecipe(new ItemStack(ModItems.COBALT_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 0));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableCobaltArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.COBALT_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            this.addRecipe(new ItemStack(ModItems.COBALT_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            this.addRecipe(new ItemStack(ModItems.COBALT_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            this.addRecipe(new ItemStack(ModItems.COBALT_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            this.addRecipe(new ItemStack(ModItems.COBALT_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
            this.addRecipe(new ItemStack(ModItems.COBALT_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 0));
        }
        /** Knight Slime Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableKnightSlimeArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_LEGGINGS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 3));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableKnightSlimeArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_LEGGINGS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
            this.addRecipe(new ItemStack(ModItems.KNIGHT_SLIME_LEGGINGS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 3));
        }
        /** Manyullyn Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enableManyullynArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 2));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enableManyullynArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
            this.addRecipe(new ItemStack(ModItems.MANYULLYN_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 2));
        }
        /** Pig Iron Armor */
        if (ARPConfig.recipes == 0 && ARPConfig.enablePigIronArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:ingots"), 1, 4));
        }
        if (ARPConfig.recipes == 1 && ARPConfig.enablePigIronArmorRecipes) {
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_HELMET, 1), "XXX", "CCC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_HELMET, 1), "CCC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_CHESTPLATE, 1), "CXC", "CCC", "CCC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_LEGGINGS, 1), "CCC", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_BOOTS, 1), "XXX", "CXC", "CXC", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
            this.addRecipe(new ItemStack(ModItems.PIG_IRON_BOOTS, 1), "CXC", "CXC", "XXX", 'C', new ItemStack(Item.getByNameOrId("tconstruct:metal"), 1, 4));
        }
        // ===================================== v2 Armors =====================================
        this.addShapelessRecipe(new ItemStack(ModItems.ELECTRICAL_INGOT, 1), ModItems.STEEL_INGOT, Items.REDSTONE, Items.GLOWSTONE_DUST);
        /** Metal Armor */
        if (ARPConfig.recipes == 0) {
            this.addRecipe(new ItemStack(ModItems.STEEL_HELMET, 1), "XXX", "MMM", "MXM", 'M', ModItems.STEEL_INGOT);
            this.addRecipe(new ItemStack(ModItems.STEEL_HELMET, 1), "MMM", "MXM", "XXX", 'M', ModItems.STEEL_INGOT);
            this.addRecipe(new ItemStack(ModItems.STEEL_CHESTPLATE, 1), "MXM", "MMM", "MMM", 'M', ModItems.STEEL_INGOT);
            this.addRecipe(new ItemStack(ModItems.STEEL_LEGGINGS, 1), "MMM", "MXM", "MXM", 'M', ModItems.STEEL_INGOT);
            this.addRecipe(new ItemStack(ModItems.STEEL_BOOTS, 1), "XXX", "MXM", "MXM", 'M', ModItems.STEEL_INGOT);
            this.addRecipe(new ItemStack(ModItems.STEEL_BOOTS, 1), "MXM", "MXM", "XXX", 'M', ModItems.STEEL_INGOT);
        }
        if (ARPConfig.recipes == 1) {
            this.addRecipe(new ItemStack(ModItems.STEEL_HELMET, 1), "XXX", "MMM", "MXM", 'M', ModBlocks.STEEL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.STEEL_HELMET, 1), "MMM", "MXM", "XXX", 'M', ModBlocks.STEEL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.STEEL_CHESTPLATE, 1), "MXM", "MMM", "MMM", 'M', ModBlocks.STEEL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.STEEL_LEGGINGS, 1), "MMM", "MXM", "MXM", 'M', ModBlocks.STEEL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.STEEL_BOOTS, 1), "XXX", "MXM", "MXM", 'M', ModBlocks.STEEL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.STEEL_BOOTS, 1), "MXM", "MXM", "XXX", 'M', ModBlocks.STEEL_BLOCK);
        }
        /** Electrical Armor */
        if (ARPConfig.recipes == 0) {
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_HELMET, 1), "XXX", "EEE", "EXE", 'E', ModItems.ELECTRICAL_INGOT);
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_HELMET, 1), "EEE", "EXE", "XXX", 'E', ModItems.ELECTRICAL_INGOT);
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_CHESTPLATE, 1), "EXE", "EEE", "EEE", 'E', ModItems.ELECTRICAL_INGOT);
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_LEGGINGS, 1), "EEE", "EXE", "EXE", 'E', ModItems.ELECTRICAL_INGOT);
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_BOOTS, 1), "XXX", "EXE", "EXE", 'E', ModItems.ELECTRICAL_INGOT);
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_BOOTS, 1), "EXE", "EXE", "XXX", 'E', ModItems.ELECTRICAL_INGOT);
        }
        if (ARPConfig.recipes == 1) {
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_HELMET, 1), "XXX", "EEE", "EXE", 'E', ModBlocks.ELECTRICAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_HELMET, 1), "EEE", "EXE", "XXX", 'E', ModBlocks.ELECTRICAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_CHESTPLATE, 1), "EXE", "EEE", "EEE", 'E', ModBlocks.ELECTRICAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_LEGGINGS, 1), "EEE", "EXE", "EXE", 'E', ModBlocks.ELECTRICAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_BOOTS, 1), "XXX", "EXE", "EXE", 'E', ModBlocks.ELECTRICAL_BLOCK);
            this.addRecipe(new ItemStack(ModItems.ELECTRICAL_BOOTS, 1), "EXE", "EXE", "XXX", 'E', ModBlocks.ELECTRICAL_BLOCK);
        }

        // ===================================== Items =====================================
        this.addRecipe(new ItemStack(ModItems.REINFORCING_MATERIAL, 4), "XSX", "SBS", "XSX", 'S', Items.STRING, 'B', Items.SLIME_BALL);
        this.addShapelessRecipe(new ItemStack(ModItems.THE_ULTIMATE_MATERIAL, 1), ModItems.ENDER_DRAGON_SCALE, ModItems.GUARDIAN_SCALE, ModItems.WITHER_BONE);
        this.addRecipe(new ItemStack(ModItems.THE_GIFT_OF_THE_GODS, 1), "SOS", "OLO", "SOS", 'S', new ItemStack(Items.NETHER_STAR, 1), 'O', new ItemStack(Blocks.OBSIDIAN), 'L', new ItemStack(ModItems.LAVA_CRYSTAL));
        //this.addRecipe(new ItemStack(Blocks.TNT, 1), new Object[]{"X#X", "#X#", "X#X", 'X', Items.GUNPOWDER, '#', Blocks.SAND});
        Collections.sort(this.recipes, new Comparator<IRecipe>() {
            public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_) {
                return p_compare_1_ instanceof ShapelessRecipes && p_compare_2_ instanceof ShapedRecipes ? 1 : (p_compare_2_ instanceof ShapelessRecipes && p_compare_1_ instanceof ShapedRecipes ? -1 : (p_compare_2_.getRecipeSize() < p_compare_1_.getRecipeSize() ? -1 : (p_compare_2_.getRecipeSize() > p_compare_1_.getRecipeSize() ? 1 : 0)));
            }
        });
    }

    /**
     * Returns the
     * static instance of
     * this class
     */
    public static ArmorForgeCraftingManager getInstance() {
        // The static instance of this class
        return INSTANCE;
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