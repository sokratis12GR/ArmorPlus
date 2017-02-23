/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.entity.entitygolem

import net.minecraft.entity.monster.EntityIronGolem
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.thedragonteam.armorplus.ArmorPlus

class EntityIceGolem(worldIn: World) : EntityIronGolem(worldIn) {

    override fun getLootTable(): ResourceLocation? {
        return LOOT
    }

    companion object {
        val LOOT = ResourceLocation(ArmorPlus.MODID, "entities/ice_golem")
    }
}
