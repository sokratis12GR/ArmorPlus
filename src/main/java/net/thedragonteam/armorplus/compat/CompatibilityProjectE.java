/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat;

import com.google.common.collect.ImmutableMap;
import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.thedragonteam.armorplus.registry.APItems;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class CompatibilityProjectE implements ICompatibility {

    @Override
    public void loadCompatibility(InitializationPhase phase) {
        if (phase == InitializationPhase.POST_INIT) {
            ProjectEAPI.getConversionProxy().addConversion(1, getItemStack(APItems.superStarHelmet),
                ImmutableMap.of(getItemStack(ModItems.materials, 2), 8, getItemStack(Items.NETHER_STAR), 5, getItemStack(Blocks.SOUL_SAND), 2)
            );
        }
    }

    @Override
    public String getMODID() {
        return "projecte";
    }

    @Override
    public boolean enableCompat() {
        return true;
    }
}