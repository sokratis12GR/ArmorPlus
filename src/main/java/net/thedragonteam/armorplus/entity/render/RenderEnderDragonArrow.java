package net.thedragonteam.armorplus.entity.render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.entity.entityarrow.EntityEnderDragonArrow;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
public class RenderEnderDragonArrow extends RenderArrow<EntityEnderDragonArrow> {

    private ResourceLocation res = setRL("textures/entity/projectiles/ender_dragon_arrow.png");

    public RenderEnderDragonArrow(RenderManager rm) {
        super(rm);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityEnderDragonArrow entity) {
        return res;
    }

}