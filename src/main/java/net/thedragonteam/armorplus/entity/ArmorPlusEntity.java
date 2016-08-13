package net.thedragonteam.armorplus.entity;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entityarrow.EntityCoalArrow;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLapisArrow;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLavaArrow;
import net.thedragonteam.armorplus.entity.entityarrow.EntityRedstoneArrow;

public class ArmorPlusEntity {

    public static int COAL_ARROW = 0;
    public static int LAPIS_ARROW = 1;
    public static int REDSTONE_ARROW = 2;
    public static int LAVA_ARROW = 3;

    public ArmorPlusEntity() {
        register();
    }

    private void register() {
        EntityRegistry.registerModEntity(EntityCoalArrow.class, "COAL_ARROW", COAL_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityLapisArrow.class, "LAPIS_ARROW", LAPIS_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityRedstoneArrow.class, "REDSTONE_ARROW", REDSTONE_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityLavaArrow.class, "LAVA_ARROW", LAVA_ARROW, ArmorPlus.instance, 64, 1, true);
    }

}