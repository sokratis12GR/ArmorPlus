/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.api.crafting.hightechbench.recipes;


import com.sofodev.armorplus.api.crafting.base.BaseCraftingManager;
import com.sofodev.armorplus.common.util.LoaderUtils;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createEasyArmorSetRecipes;
import static com.sofodev.armorplus.api.crafting.hightechbench.recipes.HTBRecipesHelper.createExpertArmorSetRecipes;
import static com.sofodev.armorplus.common.config.ModConfig.RegistryConfig.recipes;
import static com.sofodev.armorplus.common.config.ModConfig.getRD;
import static com.sofodev.armorplus.common.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis
 */
public class ModTinkersConstructRecipes {

    public void addRecipes(BaseCraftingManager manager) {
        if (LoaderUtils.isTiCLoaded()) {
            switch (getRD()) {
                case EASY:
                    createEasyArmorSetRecipes(recipes.enableArditeArmorRecipes, manager, "ingotArdite", getItem("ardite_helmet"), getItem("ardite_chestplate"), getItem("ardite_leggings"), getItem("ardite_boots"));
                    createEasyArmorSetRecipes(recipes.enableCobaltArmorRecipes, manager, "ingotCobalt", getItem("cobalt_helmet"), getItem("cobalt_chestplate"), getItem("cobalt_leggings"), getItem("cobalt_boots"));
                    createEasyArmorSetRecipes(recipes.enableKnightSlimeArmorRecipes, manager, "ingotKnightslime", getItem("knight_slime_helmet"), getItem("knight_slime_chestplate"), getItem("knight_slime_leggings"), getItem("knight_slime_boots"));
                    createEasyArmorSetRecipes(recipes.enableManyullynArmorRecipes, manager, "ingotManyullyn", getItem("manyullyn_helmet"), getItem("manyullyn_chestplate"), getItem("manyullyn_leggings"), getItem("manyullyn_boots"));
                    createEasyArmorSetRecipes(recipes.enablePigIronArmorRecipes, manager, "ingotPigiron", getItem("pig_iron_helmet"), getItem("pig_iron_chestplate"), getItem("pig_iron_leggings"), getItem("pig_iron_boots"));
                    break;
                case EXPERT:
                case HELLISH:
                    createExpertArmorSetRecipes(recipes.enableArditeArmorRecipes, manager, "blockArdite", "ingotArdite", getItem("ardite_helmet"), getItem("ardite_chestplate"), getItem("ardite_leggings"), getItem("ardite_boots"));
                    createExpertArmorSetRecipes(recipes.enableCobaltArmorRecipes, manager, "blockCobalt", "ingotCobalt", getItem("cobalt_helmet"), getItem("cobalt_chestplate"), getItem("cobalt_leggings"), getItem("cobalt_boots"));
                    createExpertArmorSetRecipes(recipes.enableKnightSlimeArmorRecipes, manager, "blockKnightslime", "ingotKnightslime", getItem("knight_slime_helmet"), getItem("knight_slime_chestplate"), getItem("knight_slime_leggings"), getItem("knight_slime_boots"));
                    createExpertArmorSetRecipes(recipes.enableManyullynArmorRecipes, manager, "blockManyullyn", "ingotManyullyn", getItem("manyullyn_helmet"), getItem("manyullyn_chestplate"), getItem("manyullyn_leggings"), getItem("manyullyn_boots"));
                    createExpertArmorSetRecipes(recipes.enablePigIronArmorRecipes, manager, "blockPigiron", "ingotPigiron", getItem("pig_iron_helmet"), getItem("pig_iron_chestplate"), getItem("pig_iron_leggings"), getItem("pig_iron_boots"));
                    break;
            }
        }
    }

    private Item getItem(String value) {
        return ForgeRegistries.ITEMS.getValue(setRL(value));
    }
}