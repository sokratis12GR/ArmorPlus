package com.sofodev.armorplus.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import net.minecraft.resources.ResourceLocation;

import static com.sofodev.armorplus.utils.Utils.setRL;

@JeiPlugin
public class APPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return setRL("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
    }


}
