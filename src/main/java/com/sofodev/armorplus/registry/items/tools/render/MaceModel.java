package com.sofodev.armorplus.registry.items.tools.render;

import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.sofodev.armorplus.utils.Utils.setRL;

public class MaceModel extends AnimatedGeoModel<APMaceItem> {

    @Override
    public ResourceLocation getModelResource(APMaceItem item) {
        return setRL("geo/mace.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(APMaceItem item) {
        return setRL("textures/items/" + item.mat.getName() + "_mace.png");
    }

    @Override
    public ResourceLocation getAnimationResource(APMaceItem item) {
        return setRL("animations/mace.animation.json");
    }
}