package com.sofodev.armorplus.registry.items.tools.render;

import com.sofodev.armorplus.registry.items.tools.APMaceItem;
import com.sofodev.armorplus.registry.items.tools.properties.mace.IAPMace;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

import java.util.concurrent.Callable;

@OnlyIn(Dist.CLIENT)
public class APMaceRenderer extends GeoItemRenderer<APMaceItem> implements Callable<ItemStackTileEntityRenderer> {
    public APMaceRenderer(IAPMace material) {
        super(new MaceModel(material));
    }

    @Override
    public ItemStackTileEntityRenderer call() throws Exception {
        return this;
    }
}