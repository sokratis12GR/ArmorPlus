package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import static net.thedragonteam.armorplus.entity.entityarrow.ModdedArrows.enderDragonArrow;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class EntityEnderDragonArrow extends EntityModdedArrow {

    public EntityEnderDragonArrow(World worldIn) {
        super(worldIn, enderDragonArrow);
    }

    public EntityEnderDragonArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, enderDragonArrow);
    }

    public EntityEnderDragonArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, enderDragonArrow);
    }
}