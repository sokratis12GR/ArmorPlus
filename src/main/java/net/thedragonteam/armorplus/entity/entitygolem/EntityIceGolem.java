package net.thedragonteam.armorplus.entity.entitygolem;

import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.thedragonteam.armorplus.util.Utils.setRL;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class EntityIceGolem extends EntityIronGolem {

    private ResourceLocation loot = setRL("entities/ice_golem");

    EntityIceGolem(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return loot;
    }

}
