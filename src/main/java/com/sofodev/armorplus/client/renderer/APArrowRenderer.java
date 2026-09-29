package com.sofodev.armorplus.client.renderer;

import com.sofodev.armorplus.registry.entity.arrow.APArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public class APArrowRenderer extends ArrowRenderer<APArrowEntity, ArrowRenderState> {

    private final Identifier texture;

    public APArrowRenderer(EntityRendererProvider.Context context, Identifier texture) {
        super(context);
        this.texture = texture;
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    protected Identifier getTextureLocation(ArrowRenderState state) {
        return texture;
    }
}
