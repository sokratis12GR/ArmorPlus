package com.sofodev.armorplus.registry.item.tool.render;

import com.sofodev.armorplus.registry.item.tool.APMaceItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class APMaceRenderer extends GeoItemRenderer<APMaceItem> {
    public APMaceRenderer() {
        super(new MaceModel());
    }
}