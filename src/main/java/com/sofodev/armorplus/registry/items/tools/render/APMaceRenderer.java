package com.sofodev.armorplus.registry.items.tools.render;

import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class APMaceRenderer extends GeoItemRenderer<APMaceItem> {
    public APMaceRenderer() {
        super(new MaceModel());
    }
}