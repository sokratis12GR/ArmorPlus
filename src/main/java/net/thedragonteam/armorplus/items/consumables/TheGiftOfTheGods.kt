/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.consumables

import net.minecraft.client.Minecraft
import net.minecraft.client.settings.GameSettings
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.util.text.TextFormatting.*
import net.minecraft.world.World
import net.minecraftforge.common.util.EnumHelper.addRarity
import net.thedragonteam.armorplus.APConfig
import net.thedragonteam.armorplus.APConfig.*
import net.thedragonteam.armorplus.iface.IModelHelper
import net.thedragonteam.armorplus.items.base.BaseItem
import net.thedragonteam.thedragonlib.util.ItemStackUtils
import net.thedragonteam.thedragonlib.util.LogHelper
import net.thedragonteam.thedragonlib.util.TextUtils
import net.thedragonteam.thedragonlib.util.TextUtils.setTextTranslation
import java.util.*

/**
 * net.thedragonteam.armorplus.items.consumables
 * ArmorPlus created by sokratis12GR on 6/30/2016 2:59 PM.
 * - TheDragonTeam
 */
class TheGiftOfTheGods : BaseItem("the_gift_of_the_gods"), IModelHelper {

    var golden: EnumRarity = addRarity("GOLD", GOLD, "GOLD") as EnumRarity

    var maxUsable: Int = 0

    init {
        this.maxUsable = maxUses - 1
        this.maxDamage = maxUsable
    }

    override fun initModel() = this.initModel(this, registryName, 0)

    override fun getRarity(stack: ItemStack): EnumRarity = golden

    override fun shouldRotateAroundWhenRendering(): Boolean = true

    override fun isFull3D(): Boolean = true

    override fun setFull3D(): Item = this

    override fun onItemRightClick(worldIn: World?, playerIn: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        val blackListedItems = APConfig.blackListedItems.toList()

        val nbt: NBTTagCompound = if (playerIn.getHeldItem(hand).hasTagCompound()) playerIn.getHeldItem(hand).tagCompound as NBTTagCompound else NBTTagCompound()

        nbt.setInteger("Clicked", if (nbt.hasKey("Clicked")) nbt.getInteger("Clicked") + 1 else 1)
        playerIn.getHeldItem(hand).tagCompound = nbt

        var count: Int
        var item: Item? = ItemStack.EMPTY.item
        do {
            when {
                APConfig.enableWhiteList -> if (APConfig.enableWhiteList)
                    item = Item.getByNameOrId(whiteListedItems[random.nextInt(whitelistMax - whitelistMin + 1) + whitelistMin])
                else -> {
                    count = 256 + random.nextInt(32000 - 256)
                    item = Item.getItemById(count)
                }
            }
        } while (item == null || item == ItemStack.EMPTY.item || item == ItemStackUtils.getItem(blackListedItems.toString()) && enableBlackList)

        if (!worldIn!!.isRemote) {
            if (enableTheGiftOfTheGods) {
                val cooldown = 0
                if (!playerIn.heldItemMainhand.isEmpty && playerIn.heldItemMainhand.item === playerIn.getHeldItem(hand).item || !playerIn.heldItemOffhand.isEmpty && playerIn.heldItemOffhand.item === playerIn.getHeldItem(hand).item)
                    when {
                        !debugMode && !playerIn.cooldownTracker.hasCooldown(playerIn.getHeldItem(hand).item) -> playerIn.cooldownTracker.setCooldown(playerIn.heldItemMainhand.item, cooldownTicks)
                        debugMode && debugModeTGOTG -> playerIn.cooldownTracker.setCooldown(playerIn.heldItemMainhand.item, cooldown)
                    }

                playerIn.dropItem(item, 1)
                playerIn.sendStatusMessage(setTextTranslation("status.armorplus.tgotg.gained_item", item.getItemStackDisplayName(playerIn.getHeldItem(hand)), item.registryName!!), false)
                if (debugMode && debugModeTGOTG)
                    LogHelper.info("Item's Registry Name: ${item.registryName};\n Item's Creative Tab: ${item.creativeTab}\n" +
                            " ; Item's Unlocalized Name: ${item.unlocalizedName};\n Does the Item have Subtypes: ${item.hasSubtypes}\n" +
                            " ; Item's Max Damage: ${getMaxDamage(ItemStack(item))}")
            }
            playerIn.getHeldItem(hand).damageItem(1, playerIn)
        }
        return ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(hand))
    }

    override fun addInformation(stack: ItemStack?, playerIn: EntityPlayer?, tooltip: MutableList<String>?, advanced: Boolean) {
        val keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak
        val maxUses = APConfig.maxUses
        tooltip!!.add("$ITALIC${RED}This item can summon items which can potentially cause crashes")
        when {
            GameSettings.isKeyDown(keyBindSneak) -> {
                tooltip.add("\u00a79Ability: \u00a7rGrants Random Item")
                tooltip.add("\u00a79Max Uses: \u00a7r$maxUses")
                tooltip.add("\u00a73Use: \u00a7rRight-Click")
            }
            else -> {
                tooltip.add(TextUtils.formattedText(GRAY, "tooltip.showinfo.beginning", TextUtils.formattedText(GOLD, "tooltip.showinfo.keybind", keyBindSneak.displayName,
                        TextUtils.formattedText(GRAY, "tooltip.showinfo.end"))))
            }
        }
    }

    companion object {
        private val random = Random()
    }
}