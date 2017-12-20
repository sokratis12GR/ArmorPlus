package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.entity.entitygolem.EntityIceGolem;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.util.Utils.setRL;


/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
public class RenderIceGolem extends RenderLiving<EntityIceGolem> {

    private static final ResourceLocation ICE_GOLEM_TEXTURES = setRL("textures/entity/ice_golem.png");

    public RenderIceGolem(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelArmorStand(), 0.5f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityIceGolem entity) {
        return ICE_GOLEM_TEXTURES;
    }

    @Override
    protected void applyRotations(EntityIceGolem entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);


        if (entityLiving.limbSwingAmount >= 0.01f) {
            //  val f = 13.0f
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0f - partialTicks) + 6.0f;
            float f2 = (Math.abs(f1 % 13.0f - 6.5f) - 3.25f) / 3.25f;
            GlStateManager.rotate(6.5f * f2, 0.0f, 0.0f, 1.0f);
        }
    }

}
