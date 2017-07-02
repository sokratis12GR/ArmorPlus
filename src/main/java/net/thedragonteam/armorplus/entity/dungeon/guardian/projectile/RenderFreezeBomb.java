package net.thedragonteam.armorplus.entity.dungeon.guardian.projectile;

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
public class RenderFreezeBomb extends RenderProjectile {
    private static final ResourceLocation FREEZE_BOMB_TEXTURES = new ResourceLocation(ArmorPlus.MODID, "textures/entity/guardian/freeze_bomb.png");

    public RenderFreezeBomb(RenderManager renderManager) {
        super(renderManager, FREEZE_BOMB_TEXTURES);
        this.shadowSize = 0.5F;
    }

}