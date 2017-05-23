/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei

import mezz.jei.api.*
import mezz.jei.api.ingredients.IIngredientRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.api.Constants.Compat.JEI_CATEGORY_HIGH_TECH_BENCH
import net.thedragonteam.armorplus.api.Constants.Compat.JEI_CATEGORY_LAVA_INFUSER
import net.thedragonteam.armorplus.api.Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH
import net.thedragonteam.armorplus.api.Constants.Compat.JEI_CATEGORY_WORKBENCH
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager
import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserManager
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager
import net.thedragonteam.armorplus.api.crafting.workbench.*
import net.thedragonteam.armorplus.client.gui.GuiHighTechBench
import net.thedragonteam.armorplus.client.gui.GuiLavaInfuser
import net.thedragonteam.armorplus.client.gui.GuiUltiTechBench
import net.thedragonteam.armorplus.client.gui.GuiWorkbench
import net.thedragonteam.armorplus.compat.jei.JEIClientUtils.Companion.addDescription
import net.thedragonteam.armorplus.compat.jei.hightechbench.*
import net.thedragonteam.armorplus.compat.jei.lavainfuser.InfuserRecipeMaker
import net.thedragonteam.armorplus.compat.jei.lavainfuser.LavaInfuserCategory
import net.thedragonteam.armorplus.compat.jei.lavainfuser.LavaInfuserRecipeWrapper
import net.thedragonteam.armorplus.compat.jei.ultitechbench.*
import net.thedragonteam.armorplus.compat.jei.workbench.*
import net.thedragonteam.armorplus.compat.minetweaker.lavainfuser.LavaInfuserRecipe
import net.thedragonteam.armorplus.container.ContainerHighTechBench
import net.thedragonteam.armorplus.container.ContainerLavaInfuser
import net.thedragonteam.armorplus.container.ContainerUltiTechBench
import net.thedragonteam.armorplus.container.ContainerWorkbench
import net.thedragonteam.armorplus.registry.APBlocks
import net.thedragonteam.armorplus.registry.APItems
import net.thedragonteam.armorplus.registry.ModBlocks
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack
import net.thedragonteam.thedragonlib.util.TextUtils.formattedText

@JEIPlugin
class ArmorPlusPlugin : BlankModPlugin() {

    override fun register(registry: IModRegistry?) {
        Companion.jeiHelper = registry!!.jeiHelpers
        Companion.ingredientRegistry = registry.ingredientRegistry
        Companion.guiHelper = jeiHelper.guiHelper

        registry.addRecipeCategories(
                WBCategory(),
                HTBCategory(),
                UTBCategory(),
                LavaInfuserCategory()
        )
        registerRecipeHandlers(registry)

        registry.addRecipeClickArea(GuiWorkbench::class.java, 88, 32, 28, 23, JEI_CATEGORY_WORKBENCH)
        registry.addRecipeClickArea(GuiHighTechBench::class.java, 88, 40, 28, 27, JEI_CATEGORY_HIGH_TECH_BENCH)
        registry.addRecipeClickArea(GuiUltiTechBench::class.java, 112, 50, 28, 27, JEI_CATEGORY_ULTI_TECH_BENCH)
        registry.addRecipeClickArea(GuiLavaInfuser::class.java, 92, 34, 28, 27, JEI_CATEGORY_LAVA_INFUSER)

        val recipeTransferRegistry = registry.recipeTransferRegistry

        recipeTransferRegistry.addRecipeTransferHandler(ContainerWorkbench::class.java, JEI_CATEGORY_WORKBENCH, 1, 9, 10, 36)
        recipeTransferRegistry.addRecipeTransferHandler(ContainerHighTechBench::class.java, JEI_CATEGORY_HIGH_TECH_BENCH, 1, 16, 17, 36)
        recipeTransferRegistry.addRecipeTransferHandler(ContainerUltiTechBench::class.java, JEI_CATEGORY_ULTI_TECH_BENCH, 1, 25, 26, 36)
        recipeTransferRegistry.addRecipeTransferHandler(ContainerLavaInfuser::class.java, JEI_CATEGORY_LAVA_INFUSER, 0, 1, 3, 36)

        registry.addRecipeCategoryCraftingItem(getItemStack(APBlocks.workbench), JEI_CATEGORY_WORKBENCH)
        registry.addRecipeCategoryCraftingItem(getItemStack(APBlocks.highTechBench), JEI_CATEGORY_HIGH_TECH_BENCH)
        registry.addRecipeCategoryCraftingItem(getItemStack(APBlocks.ultiTechBench), JEI_CATEGORY_ULTI_TECH_BENCH)
        registry.addRecipeCategoryCraftingItem(getItemStack(ModBlocks.lavaInfuser), JEI_CATEGORY_LAVA_INFUSER)

        registry.addRecipes(WorkbenchCraftingManager.getInstance().recipeList, JEI_CATEGORY_WORKBENCH)
        registry.addRecipes(HighTechBenchCraftingManager.getInstance().recipeList, JEI_CATEGORY_HIGH_TECH_BENCH)
        registry.addRecipes(UltiTechBenchCraftingManager.getInstance().recipeList, JEI_CATEGORY_ULTI_TECH_BENCH)
        registry.addRecipes(LavaInfuserManager.getInstance().recipeList, JEI_CATEGORY_LAVA_INFUSER)
        registry.addRecipes(InfuserRecipeMaker.getInfuserRecipes(), JEI_CATEGORY_LAVA_INFUSER)

        registerDescriptions(registry)

        super.register(registry)
    }

    fun registerRecipeHandlers(registry: IModRegistry) {
        registerWBRecipesHandlers(registry)
        registerHTBRecipesHandlers(registry)
        registerUTBRecipesHandlers(registry)
        registry.handleRecipes(LavaInfuserRecipe::class.java, ::LavaInfuserRecipeWrapper, JEI_CATEGORY_LAVA_INFUSER)
    }

    fun registerWBRecipesHandlers(registry: IModRegistry) {
        registry.handleRecipes(ShapedRecipes::class.java, ::WBShapedRecipeWrapper, JEI_CATEGORY_WORKBENCH)
        registry.handleRecipes(ShapelessRecipes::class.java, ::WBShapelessRecipeWrapper, JEI_CATEGORY_WORKBENCH)
        registry.handleRecipes(ShapelessOreRecipe::class.java, { recipe -> WBShapelessOreRecipeWrapper(Companion.jeiHelper, recipe) }, JEI_CATEGORY_WORKBENCH)
        registry.handleRecipes(ShapedOreRecipe::class.java, { recipe -> WBShapedOreRecipeWrapper(Companion.jeiHelper, recipe) }, JEI_CATEGORY_WORKBENCH)
    }

    fun registerHTBRecipesHandlers(registry: IModRegistry) {
        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedRecipes::class.java, ::HTBShapedRecipeWrapper, JEI_CATEGORY_HIGH_TECH_BENCH)
        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.hightechbench.ShapelessRecipes::class.java, ::HTBShapelessRecipeWrapper, JEI_CATEGORY_HIGH_TECH_BENCH)
        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.hightechbench.ShapelessOreRecipe::class.java, { recipe -> HTBShapelessOreRecipeWrapper(Companion.jeiHelper, recipe) }, JEI_CATEGORY_HIGH_TECH_BENCH)
        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.hightechbench.ShapedOreRecipe::class.java, { recipe -> HTBShapedOreRecipeWrapper(Companion.jeiHelper, recipe) }, JEI_CATEGORY_HIGH_TECH_BENCH)
    }

    fun registerUTBRecipesHandlers(registry: IModRegistry) {
        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedRecipes::class.java, ::UTBShapedRecipeWrapper, JEI_CATEGORY_ULTI_TECH_BENCH)
        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapelessRecipes::class.java, ::UTBShapelessRecipeWrapper, JEI_CATEGORY_ULTI_TECH_BENCH)
        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapelessOreRecipe::class.java, { recipe -> UTBShapelessOreRecipeWrapper(Companion.jeiHelper, recipe) }, JEI_CATEGORY_ULTI_TECH_BENCH)
        registry.handleRecipes(net.thedragonteam.armorplus.api.crafting.ultitechbench.ShapedOreRecipe::class.java, { recipe -> UTBShapedOreRecipeWrapper(Companion.jeiHelper, recipe) }, JEI_CATEGORY_ULTI_TECH_BENCH)
    }

    @SideOnly(Side.CLIENT)
    private fun registerDescriptions(registry: IModRegistry) {
        addDescription(registry, APItems.guardianScale, formattedText("armorplus.jei.guardian_scale.desc"))
        addDescription(registry, APItems.witherBone, formattedText("armorplus.jei.wither_bone.desc"))
        addDescription(registry, APItems.enderDragonScale, formattedText("armorplus.jei.ender_dragon_scale.desc"))
        addDescription(registry, APBlocks.lavaInfuser, formattedText("armorplus.jei.lava_infuser.desc"))
    }

    companion object {
        lateinit var jeiHelper: IJeiHelpers
        lateinit var ingredientRegistry: IIngredientRegistry
        lateinit var guiHelper: IGuiHelper
    }
}
