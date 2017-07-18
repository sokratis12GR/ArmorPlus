/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.util

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.client.renderer.block.model.ItemCameraTransforms
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.oredict.OreDictionary
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.ArmorPlus.MODID
import net.thedragonteam.armorplus.api.util.NBTHelper


/**
 * net.thedragonteam.armorplus.util
 * ArmorPlus created by sokratis12GR on 7/18/2016 8:17 PM.
 * - TheDragonTeam
 */
object Utils {

    val WILDCARD = OreDictionary.WILDCARD_VALUE
    val BUCKET = Fluid.BUCKET_VOLUME

    fun setUnbreakable(stack: ItemStack): ItemStack {
        NBTHelper.checkNBT(stack)
        stack.tagCompound!!.setBoolean("Unbreakable", true)
        return stack
    }

    @SideOnly(Side.CLIENT)
    fun renderItemInWorld(stack: ItemStack) {
        if (stack.count > 0) {
            GlStateManager.pushMatrix()
            GlStateManager.disableLighting()
            GlStateManager.pushAttrib()
            RenderHelper.enableStandardItemLighting()
            Minecraft.getMinecraft().renderItem.renderItem(stack, ItemCameraTransforms.TransformType.FIXED)
            RenderHelper.disableStandardItemLighting()
            GlStateManager.popAttrib()
            GlStateManager.enableLighting()
            GlStateManager.popMatrix()
        }
    }

    fun setName(name: String): String = "$MODID.$name"

    fun setResourceLocation(path: String): ResourceLocation = ResourceLocation(ArmorPlus.MODID, path)

    fun setLocation(path: String): String = "$MODID:$path"

    fun areItemsEqual(stack1: ItemStack, stack2: ItemStack, checkWildcard: Boolean): Boolean = isValid(stack1) && isValid(stack2) && (stack1.isItemEqual(stack2) || checkWildcard && stack1.item === stack2.item && (stack1.itemDamage == WILDCARD || stack2.itemDamage == WILDCARD))

    fun isValid(stack: ItemStack): Boolean {
        return !stack.isEmpty
    }

    fun isNotNull(`object`: Any?): Boolean {
        return `object` != null
    }
}