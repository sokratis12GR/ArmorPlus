package com.sofodev.armorplus.registry.entity.arrow;

import com.sofodev.armorplus.utils.Utils;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class APArrowRenderer<T extends APArrowEntity> extends ArrowRenderer<T> {

    private ResourceLocation res;

    public APArrowRenderer(EntityRendererProvider.Context ctx, String name) {
        super(ctx);
        this.res = Utils.setRL("textures/entity/projectiles/" + name + "_arrow.png");
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(APArrowEntity entity) {
        return res;
    }

}