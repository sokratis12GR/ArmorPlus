/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.thedragonteam.armorplus.api.crafting.lavainfuser.LavaInfuserManager;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Collections;
import java.util.List;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

/**
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
public class InfusingRecipe extends BlankRecipeWrapper {
    private final List<List<ItemStack>> inputs;
    private final ItemStack output;

    public InfusingRecipe(List<ItemStack> inputs, ItemStack output) {
        this.inputs = Collections.singletonList(inputs);
        this.output = output;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInputLists(ItemStack.class, inputs);
        ingredients.setOutput(ItemStack.class, output);
    }

    public List<List<ItemStack>> getInputs() {
        return inputs;
    }

    public List<ItemStack> getOutputs() {
        return Collections.singletonList(output);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        LavaInfuserManager lavaInfuserManager = LavaInfuserManager.getInstance();
        double experience = lavaInfuserManager.getSmeltingExperience(output);
        if (experience > 0) {
            String experienceString = localize("gui.jei.category.armorplus.infusing.experience", experience);
            FontRenderer fontRendererObj = minecraft.fontRenderer;
            int stringWidth = fontRendererObj.getStringWidth(experienceString);
            fontRendererObj.drawString(experienceString, recipeWidth - stringWidth, 0, Color.gray.getRGB());
        }
    }
}