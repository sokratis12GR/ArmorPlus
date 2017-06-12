/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.consumables

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.MobEffects
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemFood
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionEffect
import net.minecraft.util.NonNullList
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.util.Utils.setName

/**
 * net.thedragonteam.armorplus.items.consumables
 * ArmorPlus created by sokratis12GR
 * - TheDragonTeam
 */
class RedstoneApple : ItemFood(4, 2.0f, false), IModelHelper {
    init {
        this.setHasSubtypes(true)
        this.setRegistryName("redstone_apple")
        this.unlocalizedName = setName("redstone_apple")
        GameRegistry.register(this)
        this.setAlwaysEdible()
        this.creativeTab = ArmorPlus.tabArmorplusItems
    }

    @SideOnly(Side.CLIENT)
    override fun hasEffect(stack: ItemStack): Boolean = stack.metadata > 0

    /**
     * Return an item rarity from EnumRarity
     */
    override fun getRarity(stack: ItemStack): EnumRarity {
        return if (stack.metadata == 0) EnumRarity.RARE else EnumRarity.EPIC
    }

    override fun onFoodEaten(stack: ItemStack?, worldIn: World, player: EntityPlayer) {
        if (!worldIn.isRemote) {
            if (stack != null) player.addPotionEffect(if (stack.metadata > 0) PotionEffect(MobEffects.SPEED, 6000, 1) else PotionEffect(MobEffects.SPEED, Integer.MAX_VALUE, 1))
        }
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    override fun getSubItems(tab: CreativeTabs?, subItems: NonNullList<ItemStack>) {
        subItems.add(ItemStack(this))
        subItems.add(ItemStack(this, 1, 1))
    }

    override fun initModel() = 0.rangeTo(1).forEach { i -> this.initModel(this, registryName, i) }
}
