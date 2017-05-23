/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.client.IModGuiFactory

class ConfigGuiFactory : IModGuiFactory {
    override fun createConfigGui(parentScreen: GuiScreen?): GuiScreen = ConfigGui(parentScreen)

    override fun hasConfigGui(): Boolean = true

    override fun initialize(minecraftInstance: Minecraft) = Unit

    @Suppress("OverridingDeprecatedMember")
    override fun mainConfigGuiClass(): Class<out GuiScreen> = ConfigGui::class.java

    override fun runtimeGuiCategories(): Set<IModGuiFactory.RuntimeOptionCategoryElement>? = null

    @Suppress("DEPRECATION")
    override fun getHandlerFor(element: IModGuiFactory.RuntimeOptionCategoryElement): IModGuiFactory.RuntimeOptionGuiHandler? = null

}