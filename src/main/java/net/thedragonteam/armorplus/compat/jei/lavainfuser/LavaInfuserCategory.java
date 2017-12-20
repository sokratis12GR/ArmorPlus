package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin;
import net.thedragonteam.armorplus.util.TextUtils;

import static net.thedragonteam.armorplus.util.Utils.setRL;

public class LavaInfuserCategory extends LavaInfuserRecipeCategory {

    private IDrawable background;
    private String localizedName = TextUtils.formattedText("gui.jei.category.armorplus.infusing");

    public LavaInfuserCategory() {
        ResourceLocation location = setRL("textures/gui/container/gui_lava_infuser.png");
        background = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(location, 7, 20, 138, 46);
    }

    @Override
    public String getModName() {
        return ArmorPlus.MODNAME;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        fusion.draw(minecraft, 1, 1);
        arrow.draw(minecraft, 84, 15);
        lavaBucket.draw(minecraft, 26, 14);
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public String getUid() {
        return ArmorPlusPlugin.JEI_CATEGORY_LAVA_INFUSER;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(inputSlot, true, 61, 14);
        guiItemStacks.init(outputSlot, false, 115, 14);

        guiItemStacks.set(ingredients);
    }
}