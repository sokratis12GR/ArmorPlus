package com.sofodev.armorplus.registry.items.tools.render;

import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import com.sofodev.armorplus.registry.items.tools.properties.mace.IAPMace;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.sofodev.armorplus.utils.Utils.setRL;

public class MaceModel extends AnimatedGeoModel<APMaceItem> {
    private final String material;

    public MaceModel(IAPMace material) {
        this.material = material.getName();
    }

    @Override
    public ResourceLocation getModelLocation(APMaceItem jackInTheBoxItem) {
        return setRL("geo/mace.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(APMaceItem jackInTheBoxItem) {
        return setRL("textures/items/" + material + "_mace.png");
        /* material.equals("infused_lava") ? new ResourceLocation("textures/block/lava_still.png") :
                material.equals("guardian") ? new ResourceLocation("textures/misc/underwater.png") :
           */
    }

    @Override
    public ResourceLocation getAnimationFileLocation(APMaceItem jackInTheBoxItem) {
        return setRL("animations/mace.animation.json");
    }
}
