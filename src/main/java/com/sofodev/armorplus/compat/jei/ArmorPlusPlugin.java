/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.jei;

import com.sofodev.armorplus.api.crafting.base.*;
import com.sofodev.armorplus.client.gui.*;
import com.sofodev.armorplus.compat.crafttweaker.lavainfuser.LavaInfuserRecipe;
import com.sofodev.armorplus.compat.jei.base.*;
import com.sofodev.armorplus.compat.jei.lavainfuser.InfuserRecipeMaker;
import com.sofodev.armorplus.compat.jei.lavainfuser.LavaInfuserCategory;
import com.sofodev.armorplus.compat.jei.lavainfuser.LavaInfuserRecipeWrapper;
import com.sofodev.armorplus.compat.jei.misc.OutputSlot;
import com.sofodev.armorplus.compat.jei.misc.UVData;
import com.sofodev.armorplus.config.ModConfig;
import com.sofodev.armorplus.container.*;
import com.sofodev.armorplus.registry.APItems;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.util.TextUtils;
import com.sofodev.armorplus.util.Utils;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional.Interface;
import net.thedragonteam.thedragonlib.util.ItemStackUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis
 */
@Interface(iface = "mezz.jei.api.ingredients.IIngredientBlacklist", modid = "jei", striprefs = true)
@JEIPlugin
public class ArmorPlusPlugin implements IModPlugin {

    public static final String JEI_CATEGORY_WORKBENCH = Utils.setLocation("workbench");
    public static final String JEI_CATEGORY_HIGH_TECH_BENCH = Utils.setLocation("high_tech_bench");
    public static final String JEI_CATEGORY_ULTI_TECH_BENCH = Utils.setLocation("ulti_tech_bench");
    public static final String JEI_CATEGORY_CHAMPION_BENCH = Utils.setLocation("champion_bench");
    public static final String JEI_CATEGORY_LAVA_INFUSER = Utils.setLocation("lava_infuser_infusing");
    public static final String[] JEI_CATEGORIES = new String[]{JEI_CATEGORY_WORKBENCH, JEI_CATEGORY_HIGH_TECH_BENCH, JEI_CATEGORY_ULTI_TECH_BENCH, JEI_CATEGORY_CHAMPION_BENCH};
    public static IJeiHelpers jeiHelper;

    public static void setJeiHelper(IJeiHelpers jeiHelper) {
        ArmorPlusPlugin.jeiHelper = jeiHelper;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        setJeiHelper(registry.getJeiHelpers());
        registry.addRecipeCategories(
            new CategoryBase("workbench", new UVData(0, 0, 116, 54), new OutputSlot(94, 18), 3, JEI_CATEGORY_WORKBENCH),
            new CategoryBase("high_tech_bench", new UVData(0, 0, 144, 90), new OutputSlot(122, 37), 5, JEI_CATEGORY_HIGH_TECH_BENCH),
            new CategoryBaseAdvanced("ulti_tech_bench", new UVData(0, 0, 180, 126), new OutputSlot(158, 55), 7, JEI_CATEGORY_ULTI_TECH_BENCH, 160, 85),
            new CategoryBaseAdvanced("champion_bench", new UVData(0, 0, 162, 197), new OutputSlot(72, 179), 9, JEI_CATEGORY_CHAMPION_BENCH, 100, 182),
            new LavaInfuserCategory()
        );
    }

    @Override
    public void register(IModRegistry registry) {
        setJeiHelper(registry.getJeiHelpers());
        if (ModConfig.IntegrationsConfig.enableJEIIntegration) {
            IIngredientBlacklist blacklist = jeiHelper.getIngredientBlacklist();

            registerDescriptions(registry);
            blackListIngredients(blacklist,
                ModItems.moddedCityItem,
                ModItems.jonBamsItem,
                ItemStackUtils.getItemStack(ModItems.jonBamsItem, 1),
                ModItems.theDragonTeamItem,
                ModItems.twitchItem,
                ModItems.beamItem,
                ModItems.btmMoon,
                ModItems.m1Jordan,
                ModItems.teamRapture
            );
            Arrays.stream(ModItems.horseArmors).forEach(horseArmor -> blackListIngredients(blacklist, horseArmor));
            Arrays.stream(ModBlocks.enderBlocks).forEach(enderBlocks -> blackListIngredients(blacklist, enderBlocks));
        }

        registry.handleRecipes(LavaInfuserRecipe.class, LavaInfuserRecipeWrapper::new, JEI_CATEGORY_LAVA_INFUSER);

        this.handleRecipes(registry, JEI_CATEGORY_WORKBENCH);
        this.handleRecipes(registry, JEI_CATEGORY_HIGH_TECH_BENCH);
        this.handleRecipes(registry, JEI_CATEGORY_ULTI_TECH_BENCH);
        this.handleRecipes(registry, JEI_CATEGORY_CHAMPION_BENCH);

        registry.addRecipeClickArea(GuiWorkbench.class, 61, 33, 22, 15, JEI_CATEGORY_WORKBENCH);
        registry.addRecipeClickArea(GuiHighTechBench.class, 100, 33, 22, 15, JEI_CATEGORY_HIGH_TECH_BENCH);
        registry.addRecipeClickArea(GuiUltiTechBench.class, 136, 70, 22, 15, JEI_CATEGORY_ULTI_TECH_BENCH);
        registry.addRecipeClickArea(GuiChampionBench.class, 184, 24, 21, 23, JEI_CATEGORY_CHAMPION_BENCH);
        registry.addRecipeClickArea(GuiLavaInfuser.class, 92, 35, 20, 17, JEI_CATEGORY_LAVA_INFUSER);

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        recipeTransferRegistry.addRecipeTransferHandler(ContainerWorkbench.class, JEI_CATEGORY_WORKBENCH, 1, 9, 10, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerHighTechBench.class, JEI_CATEGORY_HIGH_TECH_BENCH, 1, 25, 26, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerUltiTechBench.class, JEI_CATEGORY_ULTI_TECH_BENCH, 1, 49, 50, 36);
        recipeTransferRegistry.addRecipeTransferHandler(new AdvancedRecipeTransferInfo<>(ContainerChampionBench.class, JEI_CATEGORY_CHAMPION_BENCH, 1, 81, 82, 36));
        recipeTransferRegistry.addRecipeTransferHandler(ContainerLavaInfuser.class, JEI_CATEGORY_LAVA_INFUSER, 0, 1, 3, 36);

        registry.addRecipeCatalyst(getItemStack(ModBlocks.lavaInfuser), JEI_CATEGORY_LAVA_INFUSER);

        IntStream.range(0, JEI_CATEGORIES.length).forEach(i -> registry.addRecipeCatalyst(getItemStack(ModBlocks.benches[i]), JEI_CATEGORIES[i]));

        registry.addRecipes(BaseCraftingManager.getWBInstance().getRecipeList(), JEI_CATEGORY_WORKBENCH);
        registry.addRecipes(BaseCraftingManager.getHTBInstance().getRecipeList(), JEI_CATEGORY_HIGH_TECH_BENCH);
        registry.addRecipes(BaseCraftingManager.getUTBInstance().getRecipeList(), JEI_CATEGORY_ULTI_TECH_BENCH);
        registry.addRecipes(BaseCraftingManager.getCBInstance().getRecipeList(), JEI_CATEGORY_CHAMPION_BENCH);
        registry.addRecipes(InfuserRecipeMaker.getInfuserRecipes(), JEI_CATEGORY_LAVA_INFUSER);

    }

    private void handleRecipes(IModRegistry registry, String category) {
        registry.handleRecipes(BaseShapedRecipe.class, recipe -> new ShapedRecipeWrapper(recipe, recipe.input, recipe.getRecipeWidth(), recipe.getRecipeHeight()), category);
        registry.handleRecipes(BaseShapelessRecipe.class, recipe -> new ShapelessRecipeWrapper(recipe, recipe.input), category);
        registry.handleRecipes(BaseShapelessOreRecipe.class, recipe -> new ShapelessOreRecipeWrapper(jeiHelper, recipe, recipe.getInput()), category);
        registry.handleRecipes(BaseShapedOreRecipe.class, recipe -> new ShapedOreRecipeWrapper(jeiHelper, recipe, recipe.getInput(), recipe.getRecipeWidth(), recipe.getRecipeHeight()), category);
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
            createDesc(APItems.guardianScale, "armorplus.jei.guardian_scale.desc"),
            createDesc(APItems.witherBone, "armorplus.jei.wither_bone.desc"),
            createDesc(APItems.enderDragonScale, "armorplus.jei.ender_dragon_scale.desc"),
            createDesc(getItemStack(ModBlocks.lavaInfuser), "armorplus.jei.lava_infuser.desc")
        );
    }

    private void registerDescriptions(IModRegistry registry, EntryDescription entry) {
        registry.addIngredientInfo(entry.getStack(), VanillaTypes.ITEM, TextUtils.formattedText(entry.getDesc()));
    }

    private void registerDescriptions(IModRegistry registry, EntryDescription... entries) {
        Arrays.stream(entries).forEachOrdered(entry -> registerDescriptions(registry, entry));
    }

    private EntryDescription createDesc(ItemStack stack, String desc) {
        return new EntryDescription(stack, desc);
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
