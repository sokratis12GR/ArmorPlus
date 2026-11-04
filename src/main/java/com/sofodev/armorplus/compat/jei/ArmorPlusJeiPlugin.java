package com.sofodev.armorplus.compat.jei;

import com.sofodev.armorplus.client.RuneAltarScreen;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModDataComponents;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.rune.Runes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Optional JEI integration, only loaded by JEI itself: Rune Altar extractions and rune socketing in the Anvil.
 */
@JeiPlugin
public class ArmorPlusJeiPlugin implements IModPlugin {

    private static final Identifier UID = Identifier.fromNamespaceAndPath(MODID, "jei_plugin");

    @Override
    public Identifier getPluginUid() {
        return UID;
    }

    /** Runes are told apart by their buff, otherwise JEI would merge all of them into one entry. */
    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerFromDataComponentTypes(ModItems.ARMOR_RUNE.get(), ModDataComponents.RUNE.get());
        registration.registerFromDataComponentTypes(ModItems.WEAPON_RUNE.get(), ModDataComponents.RUNE.get());
        registration.registerFromDataComponentTypes(ModItems.TOOL_RUNE.get(), ModDataComponents.RUNE.get());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new RuneExtractionCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (!Runes.enabled()) return;
        List<ItemStack> gear = RuneRecipeMaker.gearItems();
        List<RuneExtractionRecipe> extractions = RuneRecipeMaker.extractionRecipes(gear);
        registration.addRecipes(RuneExtractionCategory.TYPE, extractions);
        registration.addRecipes(RecipeTypes.ANVIL, RuneRecipeMaker.socketingRecipes(registration.getVanillaRecipeFactory(), gear, extractions));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(RuneExtractionCategory.TYPE, ModBlocks.RUNE_ALTAR.get());
    }

    /** The small arrow between the buff list and the result slot. */
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(RuneAltarScreen.class, 148, 44, 10, 16, RuneExtractionCategory.TYPE);
    }
}
