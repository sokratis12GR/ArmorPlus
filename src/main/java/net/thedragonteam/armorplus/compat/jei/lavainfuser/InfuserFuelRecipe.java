/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class InfuserFuelRecipe extends BlankRecipeWrapper {
    private final List<List<ItemStack>> inputs;
    private final String infuseCountString;
    private final String burnTimeString;
    private final IDrawableAnimated infuser;

    public InfuserFuelRecipe(IGuiHelper guiHelper, Collection<ItemStack> input, int burnTime) {
        List<ItemStack> inputList = new ArrayList<>(input);
        this.inputs = Collections.singletonList(inputList);

        if (burnTime == 200) {
            this.infuseCountString = localize("gui.jei.category.armorplus.infusing.fuel.infuseCount.single");
        } else {
            NumberFormat numberInstance = NumberFormat.getNumberInstance();
            numberInstance.setMaximumFractionDigits(2);
            String infuseCount = numberInstance.format(burnTime / 200f);
            this.infuseCountString = localize("gui.jei.category.armorplus.infusing.fuel.infuseCount", infuseCount);
        }

        this.burnTimeString = localize("gui.jei.category.armorplus.infusing.fuel.burnTime", burnTime);

        ResourceLocation furnaceBackgroundLocation = new ResourceLocation("armorplus", "textures/gui/container/gui_lava_infuser.png");
        IDrawableStatic flameDrawable = guiHelper.createDrawable(furnaceBackgroundLocation, 176, 0, 16, 43);
        this.infuser = guiHelper.createAnimatedDrawable(flameDrawable, burnTime, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(ItemStack.class, inputs);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        infuser.draw(minecraft, 2, 0);
        minecraft.fontRenderer.drawString(infuseCountString, 28, 8, Color.gray.getRGB());
        minecraft.fontRenderer.drawString(burnTimeString, 4, 6, Color.gray.getRGB());
    }

    public List<List<ItemStack>> getInputs() {
        return inputs;
    }
}