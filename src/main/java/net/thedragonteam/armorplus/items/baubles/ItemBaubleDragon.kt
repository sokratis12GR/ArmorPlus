/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.baubles

import baubles.api.BaubleType
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.MobEffects
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect
import net.minecraft.world.World

class ItemBaubleDragon : ItemBauble("bauble_cosmetic_dragon") {

    // ========= Baubles =========

    override fun getBaubleType(arg0: ItemStack): BaubleType {
        return BaubleType.HEAD
    }

    override fun onEquipped(itemstack: ItemStack?, player: EntityLivingBase?) {}

    override fun onWornTick(itemstack: ItemStack?, player: EntityLivingBase?) {}

    override fun onArmorTick(world: World?, player: EntityPlayer?, itemStack: ItemStack?) {
        if (player!!.getActivePotionEffect(MobEffects.ABSORPTION) == null)
            player.addPotionEffect(PotionEffect(MobEffects.ABSORPTION, 20, 1))
    }

    override fun onUnequipped(itemstack: ItemStack?, player: EntityLivingBase?) {}

    override fun canEquip(itemstack: ItemStack?, player: EntityLivingBase?): Boolean {
        return true
    }

    override fun canUnequip(itemstack: ItemStack?, player: EntityLivingBase?): Boolean {
        return true
    }
}