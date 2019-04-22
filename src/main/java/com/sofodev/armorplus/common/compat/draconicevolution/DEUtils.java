package com.sofodev.armorplus.common.compat.draconicevolution;

import net.minecraft.util.DamageSource;

public class DEUtils {

    private static String[] matchingTypes = {
        "de.GuardianFireball",
        "de.GuardianEnergyBall",
        "de.GuardianChaosBall",
        "chaosImplosion",
        "damage.de.fusionExplode",
        "chaosBurst",
        "de.islandImplode",
        "chaos"
    };

    private static String chaosDamageClassDescr = "com.brandon3055.draconicevolution.lib.DEDamageSources.DamageSourceChaos";
    private static Class<?> chaosDmgClass;

    public static boolean isChaosDamage(DamageSource source) {
        if (chaosDmgClass != null && chaosDmgClass.isAssignableFrom(source.getClass())) {
            return true;
        }
        String type = source.damageType;
        if (type != null) {
            for (String match : matchingTypes) {
                if (match != null && match.equalsIgnoreCase(type)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    static {
        try {
            chaosDmgClass = Class.forName(chaosDamageClassDescr);
        } catch (Exception exc) {
            chaosDmgClass = null;
        }
    }
}
