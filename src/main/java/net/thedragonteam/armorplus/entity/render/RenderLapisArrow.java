package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLapisArrow;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
public class RenderLapisArrow extends RenderArrow<EntityLapisArrow> {

    private ResourceLocation res = setRL("textures/entity/projectiles/coal_arrow.png");

    public RenderLapisArrow(RenderManager rm) {
        super(rm);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLapisArrow entity) {
        return res;
    }

}