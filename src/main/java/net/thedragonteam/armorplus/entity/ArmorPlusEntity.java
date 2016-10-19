/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.entity;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entityarrow.EntityCoalArrow;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLapisArrow;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLavaArrow;
import net.thedragonteam.armorplus.entity.entityarrow.EntityRedstoneArrow;

public class ArmorPlusEntity {

    //Arrows ID 0 to 20
    private static final int COAL_ARROW = 0;
    private static final int LAPIS_ARROW = 1;
    private static final int REDSTONE_ARROW = 2;
    private static final int LAVA_ARROW = 3;

    public ArmorPlusEntity() {
        register();
    }

    private void register() {
        EntityRegistry.registerModEntity(EntityCoalArrow.class, "coal_arrow", COAL_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityLapisArrow.class, "lapis_arrow", LAPIS_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityRedstoneArrow.class, "redstone_arrow", REDSTONE_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityLavaArrow.class, "lava_arrow", LAVA_ARROW, ArmorPlus.instance, 64, 1, true);
    }

}