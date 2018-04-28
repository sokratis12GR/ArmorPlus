package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

import static net.thedragonteam.armorplus.entity.entityarrow.ModdedArrows.redstoneArrow;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class EntityRedstoneArrow extends EntityModdedArrow {

    public EntityRedstoneArrow(World worldIn) {
        super(worldIn, redstoneArrow);
    }

    public EntityRedstoneArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z, redstoneArrow);
    }

    public EntityRedstoneArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter, redstoneArrow);
    }
}