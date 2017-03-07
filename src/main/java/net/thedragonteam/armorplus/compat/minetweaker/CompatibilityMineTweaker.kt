/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat.minetweaker

import net.thedragonteam.armorplus.compat.ICompatibility
import net.thedragonteam.thedragonlib.util.LogHelper

/**
 * net.thedragonteam.armorplus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 10:54 PM.
 * - TheDragonTeam
 */
class CompatibilityMineTweaker : ICompatibility {
    override fun loadCompatibility(phase: ICompatibility.InitializationPhase) {
        when {
            phase === ICompatibility.InitializationPhase.POST_INIT -> try {
                MTArmorPlusPlugin.init()
            } catch (e: Throwable) {
                LogHelper.error("ArmorPlus seems to be having trouble with CraftTweaker.")
            }
        }
    }

    override val modid: String
        get() = "crafttweaker"

    override fun enableCompat(): Boolean = true
}