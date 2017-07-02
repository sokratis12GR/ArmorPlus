package net.thedragonteam.armorplus.entity.dungeon.wither.projectile;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.dungeon.base.RenderProjectile;

/**
 * ArmorPlus - Kotlin created by sokratis12GR
 * - TheDragonTeam
 */
@SideOnly(Side.CLIENT)
public class RenderWitherMinion extends RenderProjectile {
    private static final ResourceLocation WITHER_MINION_TEXTURE = new ResourceLocation(ArmorPlus.MODID, "textures/entity/wither/wither_minion.png");

    public RenderWitherMinion(RenderManager renderManager) {
        super(renderManager, WITHER_MINION_TEXTURE);
        this.shadowSize = 0.5F;
    }

}