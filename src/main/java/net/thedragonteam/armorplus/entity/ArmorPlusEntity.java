package net.thedragonteam.armorplus.entity;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.thedragonteam.armorplus.ArmorPlus;

public class ArmorPlusEntity {

    public static int COAL_ARROW = 0;

    public ArmorPlusEntity() {
        register();
    }

    private void register() {
        EntityRegistry.registerModEntity(EntityCoalArrow.class, "COAL_ARROW", COAL_ARROW, ArmorPlus.instance, 64, 1, true);
    }

}