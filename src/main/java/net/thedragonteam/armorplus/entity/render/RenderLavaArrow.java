package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLavaArrow;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
public class RenderLavaArrow extends RenderArrow<EntityLavaArrow> {

    private ResourceLocation res = setRL("textures/entity/projectiles/lava_arrow.png");

    public RenderLavaArrow(RenderManager rm) {
        super(rm);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLavaArrow entity) {
        return res;
    }

}