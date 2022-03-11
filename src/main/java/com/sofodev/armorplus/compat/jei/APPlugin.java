package com.sofodev.armorplus.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IAdvancedRegistration;
import net.minecraft.util.ResourceLocation;

import static com.sofodev.armorplus.utils.Utils.setRL;

@JeiPlugin
public class APPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return setRL("jei_plugin");
    }

    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();

    }
}
