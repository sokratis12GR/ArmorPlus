package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import static net.thedragonteam.armorplus.entity.entityarrow.ModdedArrows.lavaArrow;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class EntityLavaArrow extends EntityModdedArrow {

    public EntityLavaArrow(World worldIn) {
        super(worldIn, lavaArrow);
    }

    public EntityLavaArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, lavaArrow);
    }

    public EntityLavaArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, lavaArrow);
    }
}