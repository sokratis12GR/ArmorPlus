package com.sofodev.armorplus.registry.items.tools.render;

import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class APMaceRenderer extends GeoItemRenderer<APMaceItem> {
    public APMaceRenderer() {
        super(new MaceModel());
    }
}