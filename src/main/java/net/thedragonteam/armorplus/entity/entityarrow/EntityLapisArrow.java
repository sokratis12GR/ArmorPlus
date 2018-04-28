package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import static net.thedragonteam.armorplus.entity.entityarrow.ModdedArrows.lapisArrow;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class EntityLapisArrow extends EntityModdedArrow {

    public EntityLapisArrow(World worldIn) {
        super(worldIn, lapisArrow);
    }

    public EntityLapisArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, lapisArrow);
    }

    public EntityLapisArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, lapisArrow);
    }
}