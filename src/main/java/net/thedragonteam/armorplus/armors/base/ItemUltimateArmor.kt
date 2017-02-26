/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors.base

import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.client.settings.GameSettings
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.MobEffects
import net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.util.text.TextFormatting
import net.minecraft.util.text.TextFormatting.getValueByName
import net.minecraft.world.World
import net.minecraftforge.common.util.EnumHelper.addArmorMaterial
import net.minecraftforge.fml.common.registry.GameRegistry
import net.thedragonteam.armorplus.APConfig
import net.thedragonteam.armorplus.APConfig.*
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.registry.APItems.*
import net.thedragonteam.armorplus.registry.ModItems.materials
import net.thedragonteam.armorplus.util.ArmorPlusItemUtils.isItemRepairable
import net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity
import net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD
import net.thedragonteam.armorplus.util.PotionUtils.addPotion
import net.thedragonteam.armorplus.util.Utils
import net.thedragonteam.armorplus.util.Utils.setLocation
import net.thedragonteam.armorplus.util.Utils.setName
import net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack

class ItemUltimateArmor(slot: EntityEquipmentSlot) : ItemArmor(ItemUltimateArmor.theUltimateArmor, 0, slot), IModelHelper {

    var formattingName: EnumRarity

    init {
        this.setMaxStackSize(1)
        when (slot) {
            EntityEquipmentSlot.FEET -> {
                val boots = "the_ultimate_boots"
                this.setRegistryName(boots)
                this.unlocalizedName = setName(boots)
            }
            EntityEquipmentSlot.LEGS -> {
                val leggings = "the_ultimate_leggings"
                this.setRegistryName(leggings)
                this.unlocalizedName = setName(leggings)
            }
            EntityEquipmentSlot.CHEST -> {
                val chestplate = "the_ultimate_chestplate"
                this.setRegistryName(chestplate)
                this.unlocalizedName = setName(chestplate)
            }
            EntityEquipmentSlot.HEAD -> {
                val helmet = "the_ultimate_helmet"
                this.setRegistryName(helmet)
                this.unlocalizedName = setName(helmet)
            }
            else -> {
            }
        }
        GameRegistry.register(this)
        this.setCreativeTab(ArmorPlus.tabArmorplus)
        this.formattingName = addRarity("ULTIMATE_ARMOR_COLOR", theUltimateArmorItemNameColor, "Ultimate Armor Color")
    }

    override fun getRarity(stack: ItemStack): EnumRarity {
        return this.formattingName
    }

    override fun isBookEnchantable(stack: ItemStack?, book: ItemStack?): Boolean {
        return false
    }

    override fun canApplyAtEnchantingTable(stack: ItemStack, enchantment: Enchantment): Boolean {
        return false
    }

    override fun addInformation(stack: ItemStack?, playerIn: EntityPlayer?, tooltip: MutableList<String>?, advanced: Boolean) {
        val keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak

        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip!!.add("\u00a79Question: " + "\u00a7rAre you the chosen one ?")
            tooltip.add("\u00a73Use: " + "\u00a7rEquip The Full Set")
        } else
            tooltip!!.add(I18n.format("tooltip.shift.showinfo", getValueByName(theUltimateArmorItemNameColor), keyBindSneak.displayName, TextFormatting.GRAY, TextFormatting.GREEN))
    }

    override fun onUpdate(stack: ItemStack?, worldIn: World?, entityIn: Entity?, itemSlot: Int, isSelected: Boolean) {
        if (makeTheUltimateArmorUnbreakable)
            Utils.setUnbreakable(stack!!)
    }

    override fun onArmorTick(world: World?, entity: EntityPlayer?, itemStack: ItemStack?) {
        val head = entity!!.getItemStackFromSlot(EntityEquipmentSlot.HEAD)
        val chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST)
        val legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS)
        val feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET)
        if (APConfig.enableFlightAbility)
            when {
                !head.isEmpty && head.item === theUltimateHelmet && !chest.isEmpty && chest.item === theUltimateChestplate && !legs.isEmpty && legs.item === theUltimateLeggings && !feet.isEmpty && feet.item === theUltimateBoots || entity.capabilities.isCreativeMode || entity.isSpectator -> entity.capabilities.allowFlying = true
                else -> {
                    entity.capabilities.isFlying = false
                    entity.capabilities.allowFlying = false
                }
            }
        if (APConfig.enableTheUltimateArmorInvincibility)
            entity.capabilities.disableDamage = !head.isEmpty && head.item === theUltimateHelmet && !chest.isEmpty && chest.item === theUltimateChestplate && !legs.isEmpty && legs.item === theUltimateLeggings && !feet.isEmpty && feet.item === theUltimateBoots || entity.capabilities.isCreativeMode || entity.isSpectator
        if ((head.isEmpty || head.item !== theUltimateHelmet || chest.isEmpty || chest.item !== theUltimateChestplate || legs.isEmpty || legs.item !== theUltimateLeggings || feet.isEmpty || feet.item !== theUltimateBoots) && !entity.capabilities.isCreativeMode && !entity.isSpectator && enableTheUltimateArmorDeBuffs) {
            addPotion(entity, MobEffects.POISON, 60, 2, BAD)
            addPotion(entity, MobEffects.SLOWNESS, 60, 2, BAD)
            addPotion(entity, MobEffects.BLINDNESS, 60, 0, BAD)

            entity.motionX = 0.0
            if (entity.onGround) entity.motionY = 0.0
            entity.motionZ = 0.0
            entity.velocityChanged = true // assumes that entity instanceof EntityPlayer
        }
    }

    override fun getIsRepairable(toRepair: ItemStack?, repair: ItemStack): Boolean {
        return isItemRepairable(repair, getItemStack(materials, 4), getItemStack(materials, 4))
    }

    override fun initModel() {
        this.initModel(this, registryName, 0)
    }

    companion object {

        private val theUltimateArmor = addArmorMaterial("THE_ULTIMATE_ARMOR", setLocation("the_ultimate_armor"), 160,
                theUltimateArmorProtectionPoints, 1, ITEM_ARMOR_EQUIP_DIAMOND, theUltimateArmorToughnessPoints.toFloat())
    }
}
