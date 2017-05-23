/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.armors.base

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.MobEffects
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.EnumAction
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.potion.Potion
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.thedragonteam.armorplus.APConfig.*
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.armors.APArmorMaterial
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.registry.ModPotions
import net.thedragonteam.armorplus.util.ArmorPlusItemUtils.isItemRepairable
import net.thedragonteam.armorplus.util.EnumHelperUtil.*
import net.thedragonteam.armorplus.util.EnumTiers
import net.thedragonteam.armorplus.util.PotionUtils.PotionType.GOOD
import net.thedragonteam.armorplus.util.PotionUtils.addPotion
import net.thedragonteam.armorplus.util.PotionUtils.getPotion
import net.thedragonteam.armorplus.util.PotionUtils.removePotion
import net.thedragonteam.armorplus.util.Utils.setLocation
import net.thedragonteam.armorplus.util.Utils.setName

open class ItemArmorBase(private val material: APArmorMaterial, private val slot: EntityEquipmentSlot) : ItemArmor(material.armorMaterial, 0, slot), IModelHelper {
    var formattingName: EnumRarity
    var itemEasy: ItemStack = material.itemEasy
    var itemExpert: ItemStack = material.itemExpert
    var formatting: TextFormatting = material.formatting

    init {
        this.setMaxStackSize(1)
        when (slot) {
            EntityEquipmentSlot.FEET -> {
                val boots = material.getName() + "_boots"
                this.setRegistryName(boots)
                this.unlocalizedName = setName(boots)
            }
            EntityEquipmentSlot.LEGS -> {
                val leggings = material.getName() + "_leggings"
                this.setRegistryName(leggings)
                this.unlocalizedName = setName(leggings)
            }
            EntityEquipmentSlot.CHEST -> {
                val chestplate = material.getName() + "_chestplate"
                this.setRegistryName(chestplate)
                this.unlocalizedName = setName(chestplate)
            }
            EntityEquipmentSlot.HEAD -> {
                val helmet = material.getName() + "_helmet"
                this.setRegistryName(helmet)
                this.unlocalizedName = setName(helmet)
            }
            else -> {
            }
        }
        GameRegistry.register(this)
        this.creativeTab = ArmorPlus.tabArmorplus
        this.formattingName = addRarity("ARMOR_COLOR", formatting, "Armor Color")
    }

    override fun onArmorTick(world: World?, entity: EntityPlayer?, itemStack: ItemStack?) {
        material.onArmorTick(world, entity, itemStack)
        addEffects(entity as EntityPlayer)
    }

    fun addEffects(entity: EntityPlayer) {
        when (slot) {
            EntityEquipmentSlot.FEET -> addAbilities(entity, material.areEffectsEnabled[0], material.enableFullArmorEffect(), getPotion(material.addPotionEffect) as Potion, material.addPotionEffectAmplifier, getPotion(material.removePotionEffect))
            EntityEquipmentSlot.LEGS -> addAbilities(entity, material.areEffectsEnabled[1], material.enableFullArmorEffect(), getPotion(material.addPotionEffect) as Potion, material.addPotionEffectAmplifier, getPotion(material.removePotionEffect))
            EntityEquipmentSlot.CHEST -> addAbilities(entity, material.areEffectsEnabled[2], material.enableFullArmorEffect(), getPotion(material.addPotionEffect) as Potion, material.addPotionEffectAmplifier, getPotion(material.removePotionEffect))
            EntityEquipmentSlot.HEAD -> addAbilities(entity, material.areEffectsEnabled[3], material.enableFullArmorEffect(), getPotion(material.addPotionEffect) as Potion, material.addPotionEffectAmplifier, getPotion(material.removePotionEffect))
            else -> {
            }
        }
    }

    override fun getRarity(stack: ItemStack): EnumRarity {
        return this.formattingName
    }

    override fun getIsRepairable(toRepair: ItemStack?, repair: ItemStack): Boolean {
        return isItemRepairable(repair, itemEasy, itemExpert)
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack?, playerIn: EntityPlayer?, tooltip: List<String>?, advanced: Boolean) {
        material.addInformation(stack, playerIn, tooltip, advanced)
    }

    override fun getItemUseAction(stack: ItemStack?): EnumAction {
        return wear
    }

    override fun initModel() {
        this.initModel(this, registryName, 0)
    }

    companion object {

        var coalArmor: ItemArmor.ArmorMaterial = addArmorMaterial("COAL", setLocation("coal_armor"), 2,
                coalArmorProtectionPoints, coalArmorToughnessPoints, EnumTiers.TIER_1)
        var emeraldArmor: ItemArmor.ArmorMaterial = addArmorMaterial("EMERALD", setLocation("emerald_armor"), 35,
                emeraldArmorProtectionPoints, emeraldArmorToughnessPoints, EnumTiers.TIER_1)
        var lapisArmor: ItemArmor.ArmorMaterial = addArmorMaterial("LAPIS", setLocation("lapis_armor"), 11,
                lapisArmorProtectionPoints, lapisArmorToughnessPoints, EnumTiers.TIER_1)
        var lavaArmor: ItemArmor.ArmorMaterial = addArmorMaterial("LAVA", setLocation("lava_armor"), 45,
                lavaArmorProtectionPoints, lavaArmorToughnessPoints, EnumTiers.TIER_1)
        var obsidianArmor: ItemArmor.ArmorMaterial = addArmorMaterial("OBSIDIAN", setLocation("obsidian_armor"), 40,
                obsidianArmorProtectionPoints, obsidianArmorToughnessPoints, EnumTiers.TIER_1)
        var redstoneArmor: ItemArmor.ArmorMaterial = addArmorMaterial("REDSTONE", setLocation("redstone_armor"), 11,
                redstoneArmorProtectionPoints, redstoneArmorToughnessPoints, EnumTiers.TIER_1)
        var chickenArmor: ItemArmor.ArmorMaterial = addArmorMaterial("CHICKEN", setLocation("chicken_armor"), 1,
                chickenArmorProtectionPoints, chickenArmorToughnessPoints, EnumTiers.TIER_1)
        var slimeArmor: ItemArmor.ArmorMaterial = addArmorMaterial("SLIME", setLocation("slime_armor"), 1,
                slimeArmorProtectionPoints, slimeArmorToughnessPoints, EnumTiers.TIER_1)
        var arditeArmor: ItemArmor.ArmorMaterial = addArmorMaterial("ARDITE", setLocation("ardite_armor"), 55,
                arditeArmorProtectionPoints, arditeArmorToughnessPoints, EnumTiers.TIER_2)
        var cobaltArmor: ItemArmor.ArmorMaterial = addArmorMaterial("COBALT", setLocation("cobalt_armor"), 44,
                cobaltArmorProtectionPoints, cobaltArmorToughnessPoints, EnumTiers.TIER_2)
        var knightSlimeArmor: ItemArmor.ArmorMaterial = addArmorMaterial("KNIGHT_SLIME", setLocation("knight_slime_armor"), 33,
                knightSlimeArmorProtectionPoints, knightSlimeArmorToughnessPoints, EnumTiers.TIER_2)
        var manyullynArmor: ItemArmor.ArmorMaterial = addArmorMaterial("MANYULLYN", setLocation("manyullyn_armor"), 66,
                manyullynArmorProtectionPoints, manyullynArmorToughnessPoints, EnumTiers.TIER_2)
        var pigIronArmor: ItemArmor.ArmorMaterial = addArmorMaterial("PIG_IRON", setLocation("pig_iron_armor"), 33,
                pigIronArmorProtectionPoints, pigIronArmorToughnessPoints, EnumTiers.TIER_2)
        var enderDragonArmor: ItemArmor.ArmorMaterial = addArmorMaterial("ENDER_DRAGON", setLocation("ender_dragon_armor"), 60,
                enderDragonArmorProtectionPoints, enderDragonArmorToughnessPoints, EnumTiers.TIER_3)
        var guardianArmor: ItemArmor.ArmorMaterial = addArmorMaterial("GUARDIAN", setLocation("guardian_armor"), 50,
                guardianArmorProtectionPoints, guardianArmorToughnessPoints, EnumTiers.TIER_3)
        var superStarArmor: ItemArmor.ArmorMaterial = addArmorMaterial("SUPER_STAR", setLocation("super_star_armor"), 50,
                superStarArmorProtectionPoints, superStarArmorToughnessPoints, EnumTiers.TIER_3)
        var wear: EnumAction = addAction("WEAR")

        fun addAbilities(entity: EntityPlayer, isEnabled: Boolean, isFullArmorSet: Boolean, addPotion: Potion, potionAmplifier: Int, removePotion: Potion?) {
            when {
                isFullArmorSet -> return
                removePotion == ModPotions.EMPTY -> return
                addPotion == ModPotions.EMPTY -> return
                isEnabled -> {
                    if (entity.getActivePotionEffect(addPotion) == null || addPotion == MobEffects.NIGHT_VISION) {
                        addPotion(entity, addPotion, potionAmplifier, GOOD)
                    }
                    if (removePotion != null) {
                        removePotion(entity, removePotion)
                    }
                }
            }
        }
    }
}
