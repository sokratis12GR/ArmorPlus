package com.sofodev.armorplus.compat.jei;

import com.sofodev.armorplus.registry.ModBlocks;
import com.sofodev.armorplus.registry.rune.RuneRitual;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import static com.sofodev.armorplus.ArmorPlus.MODID;

/**
 * The ritual of a Rune Altar with a complete circle: the two inputs on the left, the circle as it stands in the world in the
 * middle with one catalyst on each pedestal that needs one, the result and the experience level cost on the right, and the tab
 * it's done on below them.
 */
public class RitualCategory extends AbstractRecipeCategory<RitualRecipe> {

    public static final IRecipeType<RitualRecipe> TYPE = IRecipeType.create(MODID, "ritual", RitualRecipe.class);

    static final String PRIMARY_SLOT = "primary";
    static final String SECONDARY_SLOT = "secondary";

    private static final int WIDTH = 162;
    private static final int HEIGHT = 66;
    private static final int INPUT_Y = 19;
    private static final int PRIMARY_X = 1;
    private static final int PLUS_X = 19;
    private static final int PLUS_WIDTH = 8;
    private static final int SECONDARY_X = 27;
    private static final int ALTAR_X = 71;
    private static final int ALTAR_Y = 19;
    /** Where each pedestal is around the altar, in the order of {@code RuneRitual.pedestalPositions}: north, east, south, west. */
    private static final int[][] PEDESTALS = {{ALTAR_X, ALTAR_Y - 18}, {ALTAR_X + 18, ALTAR_Y}, {ALTAR_X, ALTAR_Y + 18}, {ALTAR_X - 18, ALTAR_Y}};
    private static final int ARROW_X = 108;
    private static final int OUTPUT_X = 137;
    private static final int COST_Y = 42;
    private static final int COST_WIDTH = 30;
    private static final int LABEL_Y = 56;
    private static final int COST_COLOR = 0xFF80FF20;
    private static final int LABEL_COLOR = 0xFF404040;
    private static final int EMPTY_PEDESTAL_DIMMING = 0xB08B8B8B;

    /** Over the picture of a pedestal without a catalyst, so it can't be taken for an ingredient. */
    private static final IDrawable EMPTY_PEDESTAL_OVERLAY = new IDrawable() {
        @Override
        public int getWidth() {
            return 16;
        }

        @Override
        public int getHeight() {
            return 16;
        }

        @Override
        public void draw(GuiGraphicsExtractor graphics, int xOffset, int yOffset) {
            graphics.nextStratum();
            graphics.fill(xOffset, yOffset, xOffset + 16, yOffset + 16, EMPTY_PEDESTAL_DIMMING);
        }
    };

    /** @return the name of the slot of the catalyst on that pedestal, the first ones hold the recipe's catalysts */
    static String catalystSlot(int pedestal) {
        return "catalyst_" + pedestal;
    }

    public RitualCategory(IGuiHelper guiHelper) {
        super(TYPE, Component.translatable("container.armorplus.ritual_altar"), guiHelper.createDrawableItemLike(ModBlocks.EMPOWERING_PEDESTAL.get()), WIDTH, HEIGHT);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RitualRecipe recipe, IFocusGroup focuses) {
        IRecipeSlotBuilder primary = builder.addInputSlot(PRIMARY_X, INPUT_Y).setSlotName(PRIMARY_SLOT).setStandardSlotBackground().addItemStacks(recipe.primary());
        builder.addInputSlot(SECONDARY_X, INPUT_Y).setSlotName(SECONDARY_SLOT).setStandardSlotBackground().add(recipe.secondary());
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, ALTAR_X, ALTAR_Y).setStandardSlotBackground().add(new ItemStack(ModBlocks.RUNE_ALTAR.get()));
        int catalysts = recipe.catalyst().isEmpty() ? 0 : recipe.catalyst().getCount();
        for (int pedestal = 0; pedestal < RuneRitual.PEDESTAL_COUNT; pedestal++) {
            int x = PEDESTALS[pedestal][0];
            int y = PEDESTALS[pedestal][1];
            if (pedestal < catalysts) {
                builder.addInputSlot(x, y).setSlotName(catalystSlot(pedestal)).setStandardSlotBackground().add(recipe.catalyst().copyWithCount(1))
                        .addRichTooltipCallback((view, tooltip) -> tooltip.add(Component.translatable("jei.armorplus.ritual_catalyst.tooltip").withStyle(ChatFormatting.GRAY)));
            } else {
                builder.addSlot(RecipeIngredientRole.RENDER_ONLY, x, y).setStandardSlotBackground().add(new ItemStack(ModBlocks.EMPOWERING_PEDESTAL.get())).setOverlay(EMPTY_PEDESTAL_OVERLAY, 0, 0);
            }
        }
        IRecipeSlotBuilder output = builder.addOutputSlot(OUTPUT_X, INPUT_Y).setOutputSlotBackground().addItemStacks(recipe.outputs());
        builder.createFocusLink(primary, output);
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RitualRecipe recipe, IFocusGroup focuses) {
        builder.addRecipeArrowWidget().setPosition(ARROW_X, INPUT_Y);
        builder.addText(Component.literal("+"), PLUS_WIDTH, 18)
                .setPosition(PLUS_X, INPUT_Y)
                .setTextAlignment(HorizontalAlignment.CENTER)
                .setTextAlignment(VerticalAlignment.CENTER)
                .setColor(LABEL_COLOR);
        builder.addText(Component.translatable("container.armorplus.rune_altar.cost", recipe.cost()), COST_WIDTH, 10)
                .setPosition(OUTPUT_X + 9 - COST_WIDTH / 2, COST_Y)
                .setTextAlignment(HorizontalAlignment.CENTER)
                .setColor(COST_COLOR)
                .setShadow(true)
                .setTooltip(Component.translatable("container.armorplus.rune_altar.cost_levels", recipe.cost()));
        builder.addText(Component.translatable("container.armorplus.ritual_altar.tab." + recipe.tab().key()), WIDTH, 10)
                .setPosition(1, LABEL_Y)
                .setColor(LABEL_COLOR);
    }

    @Override
    public Identifier getIdentifier(RitualRecipe recipe) {
        return recipe.id();
    }
}
