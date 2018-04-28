package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import static net.thedragonteam.armorplus.entity.entityarrow.ModdedArrows.coalArrow;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class EntityCoalArrow extends EntityModdedArrow {

    public EntityCoalArrow(World worldIn) {
        super(worldIn, coalArrow);
    }

    public EntityCoalArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, coalArrow);
    }

    public EntityCoalArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, coalArrow);
    }
}