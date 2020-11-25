package com.sofodev.armorplus.registry.entities.bosses;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.sofodev.armorplus.utils.Utils;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import javax.swing.text.html.parser.Entity;

/**
 * @author Sokratis Fotkatzikis
 **/
@OnlyIn(Dist.CLIENT)
public class WitherlingRenderer extends BipedRenderer<WitherlingEntity, SkeletalKingModel<WitherlingEntity>> {

    public WitherlingRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SkeletalKingModel<>(0), 1.0f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(WitherlingEntity entity) {
        return Utils.setRL("textures/entity/dungeon/skeletal_king.png");
    }

    @Override
    protected void preRenderCallback(WitherlingEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.0F, 1.0F, 1.0F);
    }

}