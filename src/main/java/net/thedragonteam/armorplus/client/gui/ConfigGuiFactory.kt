/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.client.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.client.IModGuiFactory

class ConfigGuiFactory : IModGuiFactory {

    override fun hasConfigGui(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createConfigGui(parentScreen: GuiScreen?): GuiScreen {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initialize(minecraftInstance: Minecraft) {}

    override fun mainConfigGuiClass(): Class<out GuiScreen> {
        return ConfigGui::class.java
    }

    override fun runtimeGuiCategories(): Set<IModGuiFactory.RuntimeOptionCategoryElement>? {
        return null
    }

    override fun getHandlerFor(element: IModGuiFactory.RuntimeOptionCategoryElement): IModGuiFactory.RuntimeOptionGuiHandler? {
        return null
    }

}