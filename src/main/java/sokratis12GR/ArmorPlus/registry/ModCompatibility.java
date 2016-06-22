package sokratis12GR.ArmorPlus.registry;

import net.minecraftforge.fml.common.Loader;
import sokratis12GR.ArmorPlus.compat.ICompatibility;
import sokratis12GR.ArmorPlus.compat.jei.CompatibilityJustEnoughItems;

import java.util.ArrayList;

/**
 * sokratis12GR.ArmorPlus.registry
 * ArmorPlus created by sokratis12GR on 6/22/2016 12:20 AM.
 */
public class ModCompatibility {
    private static ArrayList<ICompatibility> compatibilities = new ArrayList<ICompatibility>();

    public static void registerModCompat() {
        compatibilities.add(new CompatibilityJustEnoughItems());
    }

    public static void loadCompat(ICompatibility.InitializationPhase phase) {
        for (ICompatibility compatibility : compatibilities)
            if (Loader.isModLoaded(compatibility.getMODID()) && compatibility.enableCompat())
                compatibility.loadCompatibility(phase);
    }
}