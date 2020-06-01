package com.sofodev.armorplus.registry.entities.arrows;

import com.sofodev.armorplus.utils.Utils;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class APArrowRenderer<T extends APArrowEntity> extends ArrowRenderer<T> {

    private ResourceLocation res;

    public APArrowRenderer(EntityRendererManager renderManagerIn, String name) {
        super(renderManagerIn);
        this.res = Utils.setRL("textures/entity/projectiles/" + name + "_arrow.png");
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(APArrowEntity entity) {
        return res;
    }

}