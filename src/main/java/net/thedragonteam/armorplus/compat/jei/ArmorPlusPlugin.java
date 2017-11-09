/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional.Interface;
import net.thedragonteam.armorplus.api.crafting.championbench.*;
import net.thedragonteam.armorplus.api.crafting.hightechbench.*;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.*;
import net.thedragonteam.armorplus.api.crafting.workbench.*;
import net.thedragonteam.armorplus.client.gui.GuiChampionBench;
import net.thedragonteam.armorplus.client.gui.GuiHighTechBench;
import net.thedragonteam.armorplus.client.gui.GuiUltiTechBench;
import net.thedragonteam.armorplus.client.gui.GuiWorkbench;
import net.thedragonteam.armorplus.compat.jei.base.*;
import net.thedragonteam.armorplus.container.ContainerChampionBench;
import net.thedragonteam.armorplus.container.ContainerHighTechBench;
import net.thedragonteam.armorplus.container.ContainerUltiTechBench;
import net.thedragonteam.armorplus.container.ContainerWorkbench;
import net.thedragonteam.armorplus.registry.ModBlocks;

import java.util.Arrays;

import static net.thedragonteam.armorplus.APConfig.enableJEIIntegration;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.lavaInfuser;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;
import static net.thedragonteam.armorplus.util.Utils.setLocation;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@Interface(iface = "mezz.jei.api.ingredients.IIngredientBlacklist", modid = "jei", striprefs = true)
@JEIPlugin
public class ArmorPlusPlugin implements IModPlugin {

    public static IJeiHelpers jeiHelper;

    private static final String JEI_CATEGORY_3X3 = setLocation("workbench");
    private static final String JEI_CATEGORY_5X5 = setLocation("high_tech_bench");
    private static final String JEI_CATEGORY_7X7 = setLocation("ulti_tech_bench");
    private static final String JEI_CATEGORY_9X9 = setLocation("champion_bench");

    private static final String[] JEI_CATEGORIES = new String[]{JEI_CATEGORY_3X3, JEI_CATEGORY_5X5, JEI_CATEGORY_7X7, JEI_CATEGORY_9X9};

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        jeiHelper = registry.getJeiHelpers();
        registry.addRecipeCategories(
            new CategoryBase("workbench", 29, 16, 116, 54, 94, 18, 3, JEI_CATEGORY_3X3),
            new CategoryBase("high_tech_bench", 11, 16, 156, 93, 136, 36, 5, JEI_CATEGORY_5X5),
            new CategoryBaseAdvanced("ulti_tech_bench", 11, 16, 178, 126, 156, 54, 7, JEI_CATEGORY_7X7, 160, 180),
            new CategoryBaseAdvanced("champion_bench", 11, 16, 200, 162, 178, 35, 9, JEI_CATEGORY_9X9, 185, 60)
        );
    }

    @Override
    public void register(IModRegistry registry) {
        if (enableJEIIntegration) {
            jeiHelper = registry.getJeiHelpers();
            IIngredientBlacklist blacklist = jeiHelper.getIngredientBlacklist();

            registerDescriptions(registry);
            blackListIngredients(blacklist,
                moddedCityItem,
                jonBamsItem,
                getItemStack(jonBamsItem, 1),
                theDragonTeamItem,
                twitchItem,
                beamItem
            );
        }

        jeiHelper = registry.getJeiHelpers();

        registry.handleRecipes(WBShapedRecipes.class, recipe -> new ShapedRecipeWrapper(recipe, recipe.input, recipe.getWidth(), recipe.getHeight()), JEI_CATEGORY_3X3);
        registry.handleRecipes(WBShapelessRecipes.class, recipe -> new ShapelessRecipeWrapper(recipe, recipe.input), JEI_CATEGORY_3X3);
        registry.handleRecipes(WBShapelessOreRecipe.class, recipe -> new ShapelessOreRecipeWrapper(jeiHelper, recipe, recipe.getInput()), JEI_CATEGORY_3X3);
        registry.handleRecipes(WBShapedOreRecipe.class, recipe -> new ShapedOreRecipeWrapper(jeiHelper, recipe, recipe.getInput(), recipe.getWidth(), recipe.getHeight()), JEI_CATEGORY_3X3);

        registry.handleRecipes(HTBShapedRecipes.class, recipe -> new ShapedRecipeWrapper(recipe, recipe.input, recipe.getWidth(), recipe.getHeight()), JEI_CATEGORY_5X5);
        registry.handleRecipes(HTBShapelessRecipes.class, recipe -> new ShapelessRecipeWrapper(recipe, recipe.input), JEI_CATEGORY_5X5);
        registry.handleRecipes(HTBShapelessOreRecipe.class, recipe -> new ShapelessOreRecipeWrapper(jeiHelper, recipe, recipe.getInput()), JEI_CATEGORY_5X5);
        registry.handleRecipes(HTBShapedOreRecipe.class, recipe -> new ShapedOreRecipeWrapper(jeiHelper, recipe, recipe.getInput(), recipe.getWidth(), recipe.getHeight()), JEI_CATEGORY_5X5);

        registry.handleRecipes(UTBShapedRecipes.class, recipe -> new ShapedRecipeWrapper(recipe, recipe.input, recipe.getWidth(), recipe.getHeight()), JEI_CATEGORY_7X7);
        registry.handleRecipes(UTBShapelessRecipes.class, recipe -> new ShapelessRecipeWrapper(recipe, recipe.input), JEI_CATEGORY_7X7);
        registry.handleRecipes(UTBShapelessOreRecipe.class, recipe -> new ShapelessOreRecipeWrapper(jeiHelper, recipe, recipe.getInput()), JEI_CATEGORY_7X7);
        registry.handleRecipes(UTBShapedOreRecipe.class, recipe -> new ShapedOreRecipeWrapper(jeiHelper, recipe, recipe.getInput(), recipe.getWidth(), recipe.getHeight()), JEI_CATEGORY_7X7);

        registry.handleRecipes(CBShapedRecipe.class, recipe -> new ShapedRecipeWrapper(recipe, recipe.input, recipe.getWidth(), recipe.getHeight()), JEI_CATEGORY_9X9);
        registry.handleRecipes(CBShapelessRecipe.class, recipe -> new ShapelessRecipeWrapper(recipe, recipe.input), JEI_CATEGORY_9X9);
        registry.handleRecipes(CBShapelessOreRecipe.class, recipe -> new ShapelessOreRecipeWrapper(jeiHelper, recipe, recipe.getInput()), JEI_CATEGORY_9X9);
        registry.handleRecipes(CBShapedOreRecipe.class, recipe -> new ShapedOreRecipeWrapper(jeiHelper, recipe, recipe.getInput(), recipe.getWidth(), recipe.getHeight()), JEI_CATEGORY_9X9);

        registry.addRecipeClickArea(GuiWorkbench.class, 88, 32, 28, 23, JEI_CATEGORY_3X3);
        registry.addRecipeClickArea(GuiHighTechBench.class, 112, 50, 28, 27, JEI_CATEGORY_5X5);
        registry.addRecipeClickArea(GuiUltiTechBench.class, 138, 70, 24, 17, JEI_CATEGORY_7X7);
        registry.addRecipeClickArea(GuiChampionBench.class, 185, 23, 24, 17, JEI_CATEGORY_9X9);

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        recipeTransferRegistry.addRecipeTransferHandler(ContainerWorkbench.class, JEI_CATEGORY_3X3, 1, 9, 10, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerHighTechBench.class, JEI_CATEGORY_5X5, 1, 25, 26, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerUltiTechBench.class, JEI_CATEGORY_7X7, 1, 49, 50, 36);
        recipeTransferRegistry.addRecipeTransferHandler(new AdvancedRecipeTransferInfo<>(ContainerChampionBench.class, JEI_CATEGORY_9X9, 1, 81, 82, 36));

        int bound = JEI_CATEGORIES.length;
        for (int i = 0; i < bound; i++) {
            registry.addRecipeCatalyst(getItemStack(ModBlocks.benches[i]), JEI_CATEGORIES[i]);
        }

        registry.addRecipes(WorkbenchCraftingManager.getInstance().getRecipeList(), JEI_CATEGORY_3X3);
        registry.addRecipes(HighTechBenchCraftingManager.getInstance().getRecipeList(), JEI_CATEGORY_5X5);
        registry.addRecipes(UltiTechBenchCraftingManager.getInstance().getRecipeList(), JEI_CATEGORY_7X7);
        registry.addRecipes(ChampionBenchCraftingManager.getInstance().getRecipeList(), JEI_CATEGORY_9X9);

    }

    private void blackListIngredients(IIngredientBlacklist blacklist, Object... stacks) {
        Arrays.stream(stacks).forEachOrdered(stack -> {
            if (stack instanceof ItemStack || stack instanceof Block || stack instanceof Item) {
                blacklist.addIngredientToBlacklist(getItemStack(stack));
            }
        });
    }

    private void registerDescriptions(IModRegistry registry) {
        this.registerDescriptions(registry,
            new EntryDescription(guardianScale, "armorplus.jei.guardian_scale.desc"),
            new EntryDescription(witherBone, "armorplus.jei.wither_bone.desc"),
            new EntryDescription(enderDragonScale, "armorplus.jei.ender_dragon_scale.desc"),
            new EntryDescription(getItemStack(lavaInfuser), "armorplus.jei.lava_infuser.desc")
        );
    }

    private void registerDescriptions(IModRegistry registry, EntryDescription entryDescription) {
        registry.addIngredientInfo(entryDescription.getStack(), ItemStack.class, formattedText(entryDescription.getDesc()));
    }

    private void registerDescriptions(IModRegistry registry, EntryDescription... entryDescriptions) {
        Arrays.stream(entryDescriptions).forEachOrdered(entryDescription -> registerDescriptions(registry, entryDescription));
    }

    private class EntryDescription {
        private final ItemStack stack;
        private final String desc;

        public EntryDescription(ItemStack stack, String desc) {
            this.stack = stack;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public ItemStack getStack() {
            return stack;
        }
    }
}
