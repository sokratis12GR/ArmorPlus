/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

//package net.thedragonteam.armorplus.compat.jei.lavainfuser;

//public class LavaInfuserCategory extends LavaInfuserRecipeCategory<LavaInfuserRecipeWrapper> {
//
//    @Override
//    public String getModName() {
//        return ArmorPlus.MODNAME;
//    }
//
//    private IDrawable background;
//    private String localizedName  = formattedText("gui.jei.category.armorplus.infusing");
//
//    public LavaInfuserCategory() {
//        ResourceLocation location = setResourceLocation( "textures/gui/container/gui_lava_infuser.png");
//        background = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(location, 7, 20, 138, 46);
//    }
//
//    @Override
//    public IDrawable getBackground() {
//        return background;
//    }
//
//    @Override
//    public void drawExtras(Minecraft minecraft) {
//        fusion.draw(minecraft, 1, 1);
//        arrow.draw(minecraft, 84, 15);
//        lavaBucket.draw(minecraft, 26, 14);
//    }
//
//    @Override
//    public String getTitle() {
//        return localizedName;
//    }
//
//    @Override
//    public String getUid() {
//        return JEI_CATEGORY_LAVA_INFUSER;
//    }
//
//    @Override
//    public void setRecipe(IRecipeLayout recipeLayout, LavaInfuserRecipeWrapper recipeWrapper, IIngredients ingredients) {
//        recipeLayout.getItemStacks().init(inputSlot, true, 61, 14);
//        recipeLayout.getItemStacks().set(inputSlot, recipeWrapper.getRecipe().input);
//
//        recipeLayout.getItemStacks().init(outputSlot, false, 115, 14);
//        recipeLayout.getItemStacks().set(outputSlot, recipeWrapper.getRecipe().output);
//    }
//}