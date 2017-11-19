package net.thedragonteam.armorplus.compat.jei.lavainfuser;

import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.util.ResourceLocation;
import net.thedragonteam.armorplus.compat.jei.ArmorPlusPlugin;

public abstract class LavaInfuserRecipeCategory implements IRecipeCategory {

    protected ResourceLocation backgroundLocation = new ResourceLocation("armorplus", "textures/gui/container/gui_lava_infuser.png");
    protected IDrawableAnimated fusion;
    protected IDrawableAnimated arrow;
    protected IDrawableStatic lavaBucket;
    public static int inputSlot = 0;
    public static int outputSlot = 2;

    LavaInfuserRecipeCategory() {
        IDrawableStatic flameDrawable = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(backgroundLocation, 176, 0, 16, 44);
        fusion = ArmorPlusPlugin.jeiHelper.getGuiHelper().createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);

        IDrawableStatic arrowDrawable = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(backgroundLocation, 176, 44, 24, 17);
        this.arrow = ArmorPlusPlugin.jeiHelper.getGuiHelper().createAnimatedDrawable(arrowDrawable, 200, IDrawableAnimated.StartDirection.LEFT, false);

        this.lavaBucket = ArmorPlusPlugin.jeiHelper.getGuiHelper().createDrawable(new ResourceLocation("minecraft", "textures/items/bucket_lava.png"), 0, 0, 16, 16);

    }
}