/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.dev

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.thedragonteam.armorplus.items.base.BaseItem

class NBTItem : BaseItem("dev_item") {

    override fun onCreated(stack: ItemStack?, worldIn: World?, playerIn: EntityPlayer?) {
        val nbt: NBTTagCompound = if (stack!!.hasTagCompound()) stack.tagCompound as NBTTagCompound else NBTTagCompound()

        nbt.setInteger("Level", if (nbt.hasKey("Level")) nbt.getInteger("Level") + 1 else 1)
        stack.tagCompound = nbt
    }

    override fun onEntitySwing(entityLiving: EntityLivingBase?, stack: ItemStack?): Boolean {
        val nbt: NBTTagCompound = if (stack!!.hasTagCompound()) stack.tagCompound as NBTTagCompound else NBTTagCompound()

        nbt.setInteger("Level", if (nbt.hasKey("Level")) nbt.getInteger("Level") + 1 else 1)
        stack.tagCompound = nbt
        return super.onEntitySwing(entityLiving, stack)
    }

    override fun setCreativeTab(tab: CreativeTabs): Item {
        return this
    }

    override fun addInformation(stack: ItemStack?, playerIn: EntityPlayer?, tooltip: MutableList<String>?, advanced: Boolean) {
        if (stack!!.hasTagCompound() && stack.tagCompound!!.hasKey("Level"))
            tooltip!!.add("Item Level: " + Integer.toString(stack.tagCompound!!.getInteger("Level")))
    }
}
