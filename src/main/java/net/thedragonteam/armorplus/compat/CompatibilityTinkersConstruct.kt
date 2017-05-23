/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.compat

import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.relauncher.Side
import net.thedragonteam.armorplus.compat.tinkers.TiC
import net.thedragonteam.armorplus.compat.tinkers.TiCMaterials
import net.thedragonteam.armorplus.compat.tinkers.modifiers.TiCModifiers

/**
 * net.thedragonteam.armorplus.compat.jei
 * ArmorPlus created by sokratis12GR on 6/21/2016 10:54 PM.
 * - TheDragonTeam
 */
class CompatibilityTinkersConstruct : ICompatibility {
    override fun loadCompatibility(phase: ICompatibility.InitializationPhase) {
        when (phase) {
            ICompatibility.InitializationPhase.PRE_INIT -> {
                TiC.preInit()
                if (FMLCommonHandler.instance().effectiveSide == Side.CLIENT) {
                    TiCModifiers.initRender()
                }
            }
            ICompatibility.InitializationPhase.INIT -> {
                TiC.init()
            }
            ICompatibility.InitializationPhase.POST_INIT -> {
                if (FMLCommonHandler.instance().effectiveSide == Side.CLIENT) {
                    TiCMaterials.registerMaterialRendering()
                }
                TiC.postInit()
            }
            else -> {
            }
        }
    }

    override val modid: String
        get() = "tconstruct"

    override fun enableCompat(): Boolean {
        return true
    }
}