/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager;
import net.thedragonteam.armorplus.compat.jei.base.CategoryBase;
import net.thedragonteam.armorplus.registry.APItems;

import static net.thedragonteam.armorplus.api.Constants.Compat.*;
import static net.thedragonteam.armorplus.util.TextUtils.formattedText;

@JEIPlugin
public class ArmorPlusPlugin extends BlankModPlugin {

    public static IJeiHelpers jeiHelper;

    @Override
    public void register(IModRegistry registry) {
        jeiHelper = registry.getJeiHelpers();
        IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
        IGuiHelper guiHelper = jeiHelper.getGuiHelper();
        IIngredientBlacklist blacklist = jeiHelper.getIngredientBlacklist();

  //      registry.handleRecipes(LavaInfuserRecipe.class, LavaInfuserRecipeWrapper::new, JEI_CATEGORY_LAVA_INFUSER);

//        registry.handleRecipes(ShapedRecipes.class,  recipe -> new ShapedRecipeWrapper(recipe, recipe.input, recipe.getWidth(), recipe.getHeight(), JEI_CATEGORY_WORKBENCH);
//        registry.handleRecipes(ShapelessRecipes.class, ShapelessRecipeWrapper::new, JEI_CATEGORY_WORKBENCH);
//        registry.handleRecipes(ShapelessOreRecipe.class, {recipe -> new WBShapelessOreRecipeWrapper(jeiHelper, recipe)}, JEI_CATEGORY_WORKBENCH);
//        registry.handleRecipes(ShapedOreRecipe.class, {recipe -> new WBShapedOreRecipeWrapper(jeiHelper, recipe)}, JEI_CATEGORY_WORKBENCH);
//
//        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedRecipes.class,::HTBShapedRecipeWrapper, JEI_CATEGORY_HIGH_TECH_BENCH);
//        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.hightechbench.ShapelessRecipes.class,::HTBShapelessRecipeWrapper, JEI_CATEGORY_HIGH_TECH_BENCH);
//        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.hightechbench.ShapelessOreRecipe.class, {recipe -> new HTBShapelessOreRecipeWrapper(jeiHelper, recipe)}, JEI_CATEGORY_HIGH_TECH_BENCH);
//        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe.class, {recipe -> new HTBShapedOreRecipeWrapper(jeiHelper, recipe)}, JEI_CATEGORY_HIGH_TECH_BENCH);
//
//        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedRecipes.class,::UTBShapedRecipeWrapper, JEI_CATEGORY_ULTI_TECH_BENCH);
//        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapelessRecipes.class,::UTBShapelessRecipeWrapper, JEI_CATEGORY_ULTI_TECH_BENCH);
//        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapelessOreRecipe.class, {recipe -> new UTBShapelessOreRecipeWrapper(jeiHelper, recipe)}, JEI_CATEGORY_ULTI_TECH_BENCH);
//        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedOreRecipe.class, {recipe -> new UTBShapedOreRecipeWrapper(jeiHelper, recipe)}, JEI_CATEGORY_ULTI_TECH_BENCH);

//        registry.addRecipeClickArea(GuiWorkbench.class, 88, 32, 28, 23, JEI_CATEGORY_WORKBENCH);
//        registry.addRecipeClickArea(GuiHighTechBench.class, 88, 40, 28, 27, JEI_CATEGORY_HIGH_TECH_BENCH);
//        registry.addRecipeClickArea(GuiUltiTechBench.class, 112, 50, 28, 27, JEI_CATEGORY_ULTI_TECH_BENCH);
//        registry.addRecipeClickArea(GuiLavaInfuser.class, 92, 34, 28, 27, JEI_CATEGORY_LAVA_INFUSER);
//
        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

//       recipeTransferRegistry.addRecipeTransferHandler(ContainerWorkbench.class, JEI_CATEGORY_WORKBENCH, 1, 9, 10, 36);
//       recipeTransferRegistry.addRecipeTransferHandler(ContainerHighTechBench.class, JEI_CATEGORY_HIGH_TECH_BENCH, 1, 16, 17, 36);
//       recipeTransferRegistry.addRecipeTransferHandler(ContainerUltiTechBench.class, JEI_CATEGORY_ULTI_TECH_BENCH, 1, 25, 26, 36);
//       recipeTransferRegistry.addRecipeTransferHandler(ContainerLavaInfuser.class, JEI_CATEGORY_LAVA_INFUSER, 0, 1, 3, 36);

   //     registry.addRecipeCatalyst(getItemStack(APBlocks.workbench), JEI_CATEGORY_WORKBENCH);
   //     registry.addRecipeCatalyst(getItemStack(APBlocks.highTechBench), JEI_CATEGORY_HIGH_TECH_BENCH);
   //     registry.addRecipeCatalyst(getItemStack(APBlocks.ultiTechBench), JEI_CATEGORY_ULTI_TECH_BENCH);
   //     registry.addRecipeCatalyst(getItemStack(ModBlocks.lavaInfuser), JEI_CATEGORY_LAVA_INFUSER);
//
        registry.addRecipes(WorkbenchCraftingManager.getInstance().getRecipeList(), JEI_CATEGORY_WORKBENCH);
        registry.addRecipes(HighTechBenchCraftingManager.getInstance().getRecipeList(), JEI_CATEGORY_HIGH_TECH_BENCH);
        registry.addRecipes(UltiTechBenchCraftingManager.getInstance().getRecipeList(), JEI_CATEGORY_ULTI_TECH_BENCH);
  //      registry.addRecipes(LavaInfuserManager.getInstance().getRecipeList(), JEI_CATEGORY_LAVA_INFUSER);
  //      registry.addRecipes(InfuserRecipeMaker.getInfuserRecipes(), JEI_CATEGORY_LAVA_INFUSER);

        registerDescriptions(registry);

   //     blacklist.addIngredientToBlacklist(getItemStack(ModItems.moddedCityItem));
   //     blacklist.addIngredientToBlacklist(getItemStack(ModItems.jonBamsItem));
   //     blacklist.addIngredientToBlacklist(getItemStack(ModItems.jonBamsItem, 1));
   //     blacklist.addIngredientToBlacklist(getItemStack(ModItems.theDragonTeamItem));
   //     blacklist.addIngredientToBlacklist(getItemStack(ModItems.twitchItem));
   //     blacklist.addIngredientToBlacklist(getItemStack(ModItems.beamItem));
   //     blacklist.addIngredientToBlacklist(getItemStack(ModItems.nbtItem));
//
   //     for (Item item : ModItems.templates)
   //         blacklist.addIngredientToBlacklist(getItemStack(item));
//
   //     for (Block block : ModBlocks.enderBlocks)
   //         blacklist.addIngredientToBlacklist(getItemStack(block));

        super.register(registry);
    }

    private void registerDescriptions(IModRegistry registry) {
  //      registry.addDescription(APItems.witherBone, formattedText("armorplus.jei.guardian_scale.desc"));
        registry.addIngredientInfo(APItems.witherBone, ItemStack.class, formattedText("armorplus.jei.guardian_scale.desc"));
  //      registry.addDescription(APItems.witherBone, formattedText("armorplus.jei.wither_bone.desc"));
 //       registry.addDescription(APItems.enderDragonScale, formattedText("armorplus.jei.ender_dragon_scale.desc"));
   //     registry.addDescription(getItemStack(APBlocks.lavaInfuser), formattedText("armorplus.jei.lava_infuser.desc"));
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(
                new CategoryBase(29, 16, 116, 54, 94, 18, 3, JEI_CATEGORY_WORKBENCH),
                new CategoryBase(11, 16, 136, 70, 113, 26, 4, JEI_CATEGORY_HIGH_TECH_BENCH),
                new CategoryBase(11, 16, 156, 93, 136, 36, 5, JEI_CATEGORY_ULTI_TECH_BENCH));
                //       new WBCategory(),
                //  new HTBCategory(),
                //  new UTBCategory(),
             //   new LavaInfuserCategory());
    }
}
