package com.sofodev.armorplus.compat.jei;

import com.sofodev.armorplus.client.RitualScreen;
import com.sofodev.armorplus.client.RuneAltarScreen;
import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModDataComponents;
import com.sofodev.armorplus.registry.ModItems;
import com.sofodev.armorplus.registry.rune.Runes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Optional JEI integration, only loaded by JEI itself: Rune Altar extractions, its ritual and the Socket Punch in the Anvil.
 */
@JeiPlugin
public class ArmorPlusJeiPlugin implements IModPlugin {

    private static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(MODID, "jei_plugin");

    private static final ISubtypeInterpreter<ItemStack> RUNE_SUBTYPE = new ISubtypeInterpreter<>() {
        @Override
        public @Nullable Object getSubtypeData(ItemStack stack, UidContext context) {
            return stack.get(ModDataComponents.RUNE.get());
        }

        @Override
        public String getLegacyStringSubtypeInfo(ItemStack stack, UidContext context) {
            return String.valueOf(stack.get(ModDataComponents.RUNE.get()));
        }
    };

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    /** Runes are told apart by their buff, otherwise JEI would merge all of them into one entry. */
    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(ModItems.ARMOR_RUNE.get(), RUNE_SUBTYPE);
        registration.registerSubtypeInterpreter(ModItems.WEAPON_RUNE.get(), RUNE_SUBTYPE);
        registration.registerSubtypeInterpreter(ModItems.TOOL_RUNE.get(), RUNE_SUBTYPE);
        registration.registerSubtypeInterpreter(ModItems.RANGED_RUNE.get(), RUNE_SUBTYPE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new RuneExtractionCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new RitualCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (!Runes.enabled()) return;
        List<ItemStack> gear = RuneRecipeMaker.gearItems();
        List<RuneExtractionRecipe> extractions = RuneRecipeMaker.extractionRecipes(gear);
        registration.addRecipes(RuneExtractionCategory.TYPE, extractions);
        registration.addRecipes(RitualCategory.TYPE, RuneRecipeMaker.ritualRecipes(gear, extractions));
        registration.addRecipes(RecipeTypes.ANVIL, RuneRecipeMaker.socketPunchRecipes(registration.getVanillaRecipeFactory(), gear));
        Component circle = Component.translatable("jei.armorplus.ritual_circle");
        registration.addIngredientInfo(ModBlocks.RUNE_ALTAR.get(), circle);
        registration.addIngredientInfo(ModBlocks.EMPOWERING_PEDESTAL.get(), circle, Component.translatable("jei.armorplus.ritual_pedestal"));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalysts(RuneExtractionCategory.TYPE, ModBlocks.RUNE_ALTAR.get());
        registration.addRecipeCatalysts(RitualCategory.TYPE, ModBlocks.RUNE_ALTAR.get(), ModBlocks.EMPOWERING_PEDESTAL.get());
    }

    /** The transfer button only fills the screen that's open, a recipe of the other form of the altar has none. */
    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(new RuneExtractionTransferHandler(registration.getTransferHelper()), RuneExtractionCategory.TYPE);
        registration.addRecipeTransferHandler(new RitualTransferHandler(registration.getTransferHelper()), RitualCategory.TYPE);
    }

    /** The small arrows in front of the result slots. */
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(RuneAltarScreen.class, 150, 48, 6, 8, RuneExtractionCategory.TYPE);
        registration.addRecipeClickArea(RitualScreen.class, 144, 58, 12, 8, RitualCategory.TYPE);
    }
}
