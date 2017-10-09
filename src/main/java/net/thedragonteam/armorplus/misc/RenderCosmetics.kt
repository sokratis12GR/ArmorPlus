/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.misc

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.client.renderer.block.model.ItemCameraTransforms
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EnumPlayerModelParts
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Vec3d
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
class RenderCosmetics(private val renderedItemStack: ItemStack) {

    companion object {
        @JvmStatic fun render(renderCosmetics: RenderCosmetics, player: EntityPlayer, partialTicks: Float) {
            if (player.isInvisible || !player.isWearing(EnumPlayerModelParts.CAPE)) return
            val isBlock: Boolean = renderCosmetics.renderedItemStack.item is ItemBlock

            GlStateManager.pushMatrix()

            val currentPos: Vec3d = Minecraft.getMinecraft().player.getPositionEyes(partialTicks)
            val playerPos: Vec3d = player.getPositionEyes(partialTicks)
            GlStateManager.translate(playerPos.x - currentPos.x, playerPos.y - currentPos.y, playerPos.z - currentPos.z)

            GlStateManager.translate(0.0, 2.375 - (if (player.isSneaking) 0.125 else 0.0) + if (isBlock) 0.0 else 0.1875, 0.0)
            GlStateManager.rotate(180f, 1.0f, 0.0f, 1.0f)

            val size = if (isBlock) 0.5f else 0.4f
            GlStateManager.scale(size, size, size)

            val boop = Minecraft.getSystemTime() / 1000.0
            GlStateManager.translate(0.0, Math.sin(boop % (2 * Math.PI)) * 0.25, 0.0)
            GlStateManager.rotate((boop * 40.0 % 360).toFloat(), 0f, 1f, 0f)

            GlStateManager.disableLighting()
            GlStateManager.pushMatrix()
            if (!isBlock) GlStateManager.translate(0.0, 0.5, 0.0)
            GlStateManager.rotate(180f, 1f, 0f, 0f)
            renderItemInWorld(renderCosmetics.renderedItemStack)
            GlStateManager.popMatrix()
            GlStateManager.enableLighting()

            GlStateManager.popMatrix()
        }

        @SideOnly(Side.CLIENT)
        @JvmStatic fun renderItemInWorld(stack: ItemStack) {
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
    }


}