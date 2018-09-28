/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei;

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
import net.thedragonteam.armorplus.api.crafting.base.*;
import net.thedragonteam.armorplus.client.gui.*;
import net.thedragonteam.armorplus.compat.crafttweaker.lavainfuser.LavaInfuserRecipe;
import net.thedragonteam.armorplus.compat.jei.base.*;
import net.thedragonteam.armorplus.compat.jei.lavainfuser.InfuserRecipeMaker;
import net.thedragonteam.armorplus.compat.jei.lavainfuser.LavaInfuserCategory;
import net.thedragonteam.armorplus.compat.jei.lavainfuser.LavaInfuserRecipeWrapper;
import net.thedragonteam.armorplus.compat.jei.misc.OutputSlot;
import net.thedragonteam.armorplus.compat.jei.misc.UVData;
import net.thedragonteam.armorplus.container.*;
import net.thedragonteam.armorplus.registry.ModBlocks;

import java.util.Arrays;
import java.util.stream.IntStream;

import static net.thedragonteam.armorplus.ModConfig.IntegrationsConfig.enableJEIIntegration;
import static net.thedragonteam.armorplus.registry.APItems.enderDragonScale;
import static net.thedragonteam.armorplus.registry.APItems.guardianScale;
import static net.thedragonteam.armorplus.registry.APItems.witherBone;
import static net.thedragonteam.armorplus.registry.ModBlocks.enderBlocks;
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

    public static final String JEI_CATEGORY_WORKBENCH = setLocation("workbench");
    public static final String JEI_CATEGORY_HIGH_TECH_BENCH = setLocation("high_tech_bench");
    public static final String JEI_CATEGORY_ULTI_TECH_BENCH = setLocation("ulti_tech_bench");
    public static final String JEI_CATEGORY_CHAMPION_BENCH = setLocation("champion_bench");
    public static final String JEI_CATEGORY_LAVA_INFUSER = setLocation("lava_infuser_infusing");
    public static final String[] JEI_CATEGORIES = new String[]{JEI_CATEGORY_WORKBENCH, JEI_CATEGORY_HIGH_TECH_BENCH, JEI_CATEGORY_ULTI_TECH_BENCH, JEI_CATEGORY_CHAMPION_BENCH};
    public static IJeiHelpers jeiHelper;

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        jeiHelper = registry.getJeiHelpers();
        registry.addRecipeCategories(
            new CategoryBase("workbench", new UVData(29, 16, 116, 54), new OutputSlot(94, 18), 3, JEI_CATEGORY_WORKBENCH),
            new CategoryBase("high_tech_bench", new UVData(11, 16, 156, 93),new OutputSlot( 136, 36), 5, JEI_CATEGORY_HIGH_TECH_BENCH),
            new CategoryBaseAdvanced("ulti_tech_bench", new UVData (11, 16, 178, 126), new OutputSlot(156, 54), 7, JEI_CATEGORY_ULTI_TECH_BENCH, 160, 80),
            new CategoryBaseAdvanced("champion_bench", new UVData(11, 16, 162, 162),new OutputSlot( 72, 168), 9, JEI_CATEGORY_CHAMPION_BENCH, 100, 170),
            new LavaInfuserCategory()
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
                beamItem,
                btmMoon,
                m1Jordan,
                teamRapture
            );
            Arrays.stream(horseArmors).forEach(horseArmor -> blackListIngredients(blacklist, horseArmor));
            Arrays.stream(enderBlocks).forEach(enderBlocks -> blackListIngredients(blacklist, enderBlocks));
        }

        jeiHelper = registry.getJeiHelpers();
        registry.handleRecipes(LavaInfuserRecipe.class, LavaInfuserRecipeWrapper::new, JEI_CATEGORY_LAVA_INFUSER);

        this.handleRecipes(registry, JEI_CATEGORY_WORKBENCH);
        this.handleRecipes(registry, JEI_CATEGORY_HIGH_TECH_BENCH);
        this.handleRecipes(registry, JEI_CATEGORY_ULTI_TECH_BENCH);
        this.handleRecipes(registry, JEI_CATEGORY_CHAMPION_BENCH);

        registry.addRecipeClickArea(GuiWorkbench.class, 88, 32, 28, 23, JEI_CATEGORY_WORKBENCH);
        registry.addRecipeClickArea(GuiHighTechBench.class, 112, 50, 28, 27, JEI_CATEGORY_HIGH_TECH_BENCH);
        registry.addRecipeClickArea(GuiUltiTechBench.class, 138, 70, 24, 17, JEI_CATEGORY_ULTI_TECH_BENCH);
        registry.addRecipeClickArea(GuiChampionBench.class, 185, 23, 24, 17, JEI_CATEGORY_CHAMPION_BENCH);
        registry.addRecipeClickArea(GuiLavaInfuser.class, 92, 34, 28, 27, JEI_CATEGORY_LAVA_INFUSER);

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
            createDesc(guardianScale, "armorplus.jei.guardian_scale.desc"),
            createDesc(witherBone, "armorplus.jei.wither_bone.desc"),
            createDesc(enderDragonScale, "armorplus.jei.ender_dragon_scale.desc"),
            createDesc(getItemStack(lavaInfuser), "armorplus.jei.lava_infuser.desc")
        );
    }

    private void registerDescriptions(IModRegistry registry, EntryDescription entry) {
        registry.addIngredientInfo(entry.getStack(), VanillaTypes.ITEM, formattedText(entry.getDesc()));
    }

    private void registerDescriptions(IModRegistry registry, EntryDescription... entries) {
        Arrays.stream(entries).forEachOrdered(entry -> registerDescriptions(registry, entry));
    }

    private EntryDescription createDesc(ItemStack stack, String desc){
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
