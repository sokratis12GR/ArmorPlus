package com.sofodev.armorplus.compat.jei;

import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.ModItems;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * Rune Altar extractions, laid out like the altar: equipment, Blank Rune and catalyst on the left, the rune and its
 * experience level cost on the right.
 */
public class RuneExtractionCategory extends AbstractRecipeCategory<RuneExtractionRecipe> {

    public static final IRecipeType<RuneExtractionRecipe> TYPE = IRecipeType.create(MODID, "rune_extraction", RuneExtractionRecipe.class);

    static final String GEAR_SLOT = "gear";
    static final String BLANK_RUNE_SLOT = "blank_rune";
    static final String CATALYST_SLOT = "catalyst";

    private static final int WIDTH = 130;
    private static final int HEIGHT = 54;
    private static final int COST_COLOR = 0xFF80FF20;

    public RuneExtractionCategory(IGuiHelper guiHelper) {
        super(TYPE, ModBlocks.RUNE_ALTAR.get().getName(), guiHelper.createDrawableItemLike(ModBlocks.RUNE_ALTAR.get()), WIDTH, HEIGHT);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RuneExtractionRecipe recipe, IFocusGroup focuses) {
        builder.addInputSlot(1, 1).setSlotName(GEAR_SLOT).setStandardSlotBackground().addItemStacks(recipe.gear());
        builder.addInputSlot(1, 19).setSlotName(BLANK_RUNE_SLOT).setStandardSlotBackground().add(ModItems.BLANK_RUNE.get());
        IRecipeSlotBuilder catalyst = builder.addInputSlot(1, 37).setSlotName(CATALYST_SLOT).setStandardSlotBackground();
        if (!recipe.catalyst().isEmpty()) {
            catalyst.add(recipe.catalyst()).addRichTooltipCallback((view, tooltip) ->
                    tooltip.add(Component.translatable("jei.armorplus.extraction_catalyst.tooltip").withStyle(ChatFormatting.GRAY)));
        }
        builder.addOutputSlot(64, 19).setOutputSlotBackground().add(recipe.rune());
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RuneExtractionRecipe recipe, IFocusGroup focuses) {
        builder.addRecipeArrowWidget().setPosition(28, 19);
        builder.addText(Component.translatable("container.armorplus.rune_altar.cost", recipe.cost()), 40, 10)
                .setPosition(90, 23)
                .setColor(COST_COLOR)
                .setShadow(true)
                .setTooltip(Component.translatable("container.armorplus.rune_altar.cost_levels", recipe.cost()));
    }

    @Override
    public Identifier getIdentifier(RuneExtractionRecipe recipe) {
        return recipe.id();
    }
}
