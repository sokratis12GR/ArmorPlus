/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.compat.jei.base;

//public class CategoryBase implements IRecipeCategory {
//
//    private static final int OUTPUT_SLOT = 0;
//    private static final int INPUT_SLOT = 1;
//    private final IDrawable background;
//    private final String localizedName;
//    private final ICraftingGridHelper craftingGridHelper;
//    private final int xPos;
//    private final int yPos;
//    private final int xy;
//    private final String category;
//
//    public CategoryBase(String name, UVData uvData, OutputSlot oSlot, int xy, String category) {
//        this(name, uvData.getU(), uvData.getV(), uvData.getWidthU(), uvData.getHeightV(), oSlot.getX(), oSlot.getY(), xy, category);
//    }
//
//    public CategoryBase(String name, int u, int v, int widthU, int heightV, int outputXPos, int outputYPos, int xy, String category) {
//        this.xPos = outputXPos;
//        this.yPos = outputYPos;
//        this.xy = xy;
//        this.category = category;
//        ResourceLocation location = Utils.setRL(format("textures/gui/container/jei/%s_jei.png", name));
//        background = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(location, u, v, widthU, heightV);
//        localizedName = new TextComponentTranslation("gui.jei.category." + name).getFormattedText();
//        craftingGridHelper = ArmorPlusPlugin.jeiHelper.getGuiHelper().createCraftingGridHelper(INPUT_SLOT, OUTPUT_SLOT);
//    }
//
//    @Nonnull
//    @Override
//    public String getUid() {
//        return category;
//    }
//
//    @Nonnull
//    @Override
//    public String getTitle() {
//        return localizedName;
//    }
//
//    /**
//     * Return the name of the mod associated with this recipe category.
//     * Used for the recipe category tab's tooltip.
//     *
//     * @since JEI 4.5.0
//     */
//    @Override
//    public String getModName() {
//        return ArmorPlus.MODNAME;
//    }
//
//    @Nonnull
//    @Override
//    public IDrawable getBackground() {
//        return background;
//    }
//
//    @Override
//    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
//        JEIUtils.setRecipe(recipeLayout, recipeWrapper, ingredients, craftingGridHelper, new JEIUtils.XYProperties(xPos, yPos, xy), INPUT_SLOT, OUTPUT_SLOT);
//    }
//}
//