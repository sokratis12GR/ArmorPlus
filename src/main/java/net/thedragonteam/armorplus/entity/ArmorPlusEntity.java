package net.thedragonteam.armorplus.entity;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.thedragonteam.armorplus.ArmorPlus;

public class ArmorPlusEntity {

    public static int COAL_ARROW = 0;
    public static int LAPIS_ARROW = 1;

    public ArmorPlusEntity() {
        register();
    }

    private void register() {
        EntityRegistry.registerModEntity(EntityCoalArrow.class, "COAL_ARROW", COAL_ARROW, ArmorPlus.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityLapisArrow.class, "LAPIS_ARROW", LAPIS_ARROW, ArmorPlus.instance, 64, 1, true);
    }

}