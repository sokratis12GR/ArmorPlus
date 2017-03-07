/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.baubles


import baubles.api.BaublesApi
import baubles.api.IBauble
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import net.thedragonteam.armorplus.items.base.BaseItem

abstract class ItemBauble(name: String) : BaseItem(name), IBauble {

    init {
        this.setMaxStackSize(1)
    }

    override fun onItemRightClick(world: World?, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        val stack = player.getHeldItem(hand)
        val toEquip = stack.copy()
        toEquip.count = 1
        if (canEquip(toEquip, player)) {
            val baubles = BaublesApi.getBaublesHandler(player)
            for (i in 0 .. baubles.slots - 1) {
                val simulate = baubles.insertItem(i, toEquip, true)
                if (simulate.isEmpty) {
                    val stackInSlot = baubles.getStackInSlot(i)
                    if (stackInSlot.isEmpty || (stackInSlot.item as IBauble).canUnequip(stackInSlot, player)) {
                        if (!world!!.isRemote) {
                            baubles.setStackInSlot(i, toEquip)
                            stack.shrink(1)
                        }

                        if (!stackInSlot.isEmpty) {
                            (stackInSlot.item as IBauble).onUnequipped(stackInSlot, player)
                            return ActionResult.newResult(EnumActionResult.SUCCESS, stackInSlot.copy())
                        }
                        break
                    }
                }
            }
        }

        return ActionResult.newResult(EnumActionResult.PASS, stack)
    }


    override fun getRarity(par1ItemStack: ItemStack): EnumRarity {
        return EnumRarity.RARE
    }

    override fun onEquipped(itemstack: ItemStack?, player: EntityLivingBase?) {
        player!!.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.75f, 1.9f)
    }

    override fun onUnequipped(itemstack: ItemStack?, player: EntityLivingBase?) {
        player!!.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.75f, 2f)
    }

}