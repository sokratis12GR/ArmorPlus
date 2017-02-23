/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei

import mezz.jei.api.BlankModPlugin
import mezz.jei.api.IJeiHelpers
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.thedragonteam.armorplus.api.Constants.Compat.JEI_CATEGORY_HIGH_TECH_BENCH
import net.thedragonteam.armorplus.api.Constants.Compat.JEI_CATEGORY_LAVA_INFUSER_INFUSING
import net.thedragonteam.armorplus.api.Constants.Compat.JEI_CATEGORY_ULTI_TECH_BENCH
import net.thedragonteam.armorplus.api.Constants.Compat.JEI_CATEGORY_WORKBENCH
import net.thedragonteam.armorplus.api.crafting.hightechbench.HighTechBenchCraftingManager
import net.thedragonteam.armorplus.api.crafting.ultitechbench.UltiTechBenchCraftingManager
import net.thedragonteam.armorplus.api.crafting.workbench.WorkbenchCraftingManager
import net.thedragonteam.armorplus.client.gui.GuiHighTechBench
import net.thedragonteam.armorplus.client.gui.GuiLavaInfuser
import net.thedragonteam.armorplus.client.gui.GuiUltiTechBench
import net.thedragonteam.armorplus.client.gui.GuiWorkbench
import net.thedragonteam.armorplus.compat.jei.hightechbench.*
import net.thedragonteam.armorplus.compat.jei.lavainfuser.InfuserRecipeMaker
import net.thedragonteam.armorplus.compat.jei.lavainfuser.InfusingRecipeHandler
import net.thedragonteam.armorplus.compat.jei.lavainfuser.LavaInfuserCategory
import net.thedragonteam.armorplus.compat.jei.ultitechbench.*
import net.thedragonteam.armorplus.compat.jei.workbench.*
import net.thedragonteam.armorplus.container.ContainerHighTechBench
import net.thedragonteam.armorplus.container.ContainerLavaInfuser
import net.thedragonteam.armorplus.container.ContainerUltiTechBench
import net.thedragonteam.armorplus.container.ContainerWorkbench
import net.thedragonteam.armorplus.registry.APBlocks
import net.thedragonteam.armorplus.registry.APItems
import net.thedragonteam.armorplus.registry.ModBlocks
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack
import net.thedragonteam.thedragonlib.util.TextHelper.localize

@JEIPlugin
class ArmorPlusPlugin : BlankModPlugin() {

    override fun register(registry: IModRegistry?) {
        jeiHelper = registry!!.jeiHelpers
        val ingredientRegistry = registry.ingredientRegistry

        //IGuiHelper guiHelper = jeiHelper.getGuiHelper();

        registry.addRecipeCategories(
                WBCategory(),
                HTBCategory(),
                UTBCategory(),
                LavaInfuserCategory()
        )
        registry.addRecipeHandlers(
                WBShapedRecipeHandler(),
                WBShapelessRecipeHandler(),
                WBShapedOreRecipeHandler(jeiHelper),
                WBShapelessOreRecipeHandler(jeiHelper),
                HTBShapedRecipeHandler(),
                HTBShapelessRecipeHandler(),
                HTBShapedOreRecipeHandler(jeiHelper),
                HTBShapelessOreRecipeHandler(jeiHelper),
                UTBShapedRecipeHandler(),
                UTBShapelessRecipeHandler(),
                UTBShapedOreRecipeHandler(jeiHelper),
                UTBShapelessOreRecipeHandler(jeiHelper),
                InfusingRecipeHandler()
        )

        registry.addRecipeClickArea(GuiWorkbench::class.java, 88, 32, 28, 23, JEI_CATEGORY_WORKBENCH)
        registry.addRecipeClickArea(GuiHighTechBench::class.java, 88, 40, 28, 27, JEI_CATEGORY_HIGH_TECH_BENCH)
        registry.addRecipeClickArea(GuiUltiTechBench::class.java, 112, 50, 28, 27, JEI_CATEGORY_ULTI_TECH_BENCH)
        registry.addRecipeClickArea(GuiLavaInfuser::class.java, 92, 34, 28, 27, JEI_CATEGORY_LAVA_INFUSER_INFUSING)

        val recipeTransferRegistry = registry.recipeTransferRegistry

        recipeTransferRegistry.addRecipeTransferHandler(ContainerWorkbench::class.java, JEI_CATEGORY_WORKBENCH, 1, 9, 10, 36)
        recipeTransferRegistry.addRecipeTransferHandler(ContainerHighTechBench::class.java, JEI_CATEGORY_HIGH_TECH_BENCH, 1, 16, 17, 36)
        recipeTransferRegistry.addRecipeTransferHandler(ContainerUltiTechBench::class.java, JEI_CATEGORY_ULTI_TECH_BENCH, 1, 25, 26, 36)
        recipeTransferRegistry.addRecipeTransferHandler(ContainerLavaInfuser::class.java, JEI_CATEGORY_LAVA_INFUSER_INFUSING, 0, 1, 3, 36)

        registry.addRecipeCategoryCraftingItem(getItemStack(APBlocks.workbench), JEI_CATEGORY_WORKBENCH)
        registry.addRecipeCategoryCraftingItem(getItemStack(APBlocks.highTechBench), JEI_CATEGORY_HIGH_TECH_BENCH)
        registry.addRecipeCategoryCraftingItem(getItemStack(APBlocks.ultiTechBench), JEI_CATEGORY_ULTI_TECH_BENCH)
        registry.addRecipeCategoryCraftingItem(getItemStack(ModBlocks.lavaInfuser), JEI_CATEGORY_LAVA_INFUSER_INFUSING)

        registry.addRecipes(WorkbenchCraftingManager.getInstance().recipeList)
        registry.addRecipes(HighTechBenchCraftingManager.getInstance().recipeList)
        registry.addRecipes(UltiTechBenchCraftingManager.getInstance().recipeList)
        registry.addRecipes(InfuserRecipeMaker.getFurnaceRecipes(jeiHelper))

        addDescription(registry, APItems.guardianScale, localize("armorplus.jei.guardian_scale.desc"))
        addDescription(registry, APItems.witherBone, localize("armorplus.jei.wither_bone.desc"))
        addDescription(registry, APItems.enderDragonScale, localize("armorplus.jei.ender_dragon_scale.desc"))
        addDescription(registry, APBlocks.lavaInfuser, localize("armorplus.jei.lava_infuser.desc"))
        super.register(registry)
    }

    fun addDescription(registry: IModRegistry, item: ItemStack, description: String) {
        registry.addDescription(item, description)
    }

    fun addDescription(registry: IModRegistry, item: Item, meta: Int, description: String) {
        registry.addDescription(getItemStack(item, meta), description)
    }

    fun addDescription(registry: IModRegistry, item: Item, description: String) {
        registry.addDescription(getItemStack(item), description)
    }

    fun addDescription(registry: IModRegistry, block: Block, meta: Int, description: String) {
        registry.addDescription(getItemStack(block, meta), description)
    }

    fun addDescription(registry: IModRegistry, block: Block, description: String) {
        registry.addDescription(getItemStack(block), description)
    }

    companion object {
        lateinit var jeiHelper: IJeiHelpers
    }
}
