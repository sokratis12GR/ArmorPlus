/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.consumables

import net.minecraft.client.Minecraft
import net.minecraft.client.settings.GameSettings
import net.minecraft.client.util.ITooltipFlag
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
import net.thedragonteam.armorplus.APConfig
import net.thedragonteam.armorplus.APConfig.*
import net.thedragonteam.armorplus.ArmorPlus
import net.thedragonteam.armorplus.items.base.BaseItem
import net.thedragonteam.armorplus.util.EnumHelperUtil.addRarity
import net.thedragonteam.armorplus.util.TextUtils.setTextTranslation
import net.thedragonteam.armorplus.util.ToolTipUtils
import net.thedragonteam.thedragonlib.util.ItemStackUtils
import net.thedragonteam.thedragonlib.util.LogHelper
import java.util.*

/**
 * net.thedragonteam.armorplus.items.consumables
 * ArmorPlus created by sokratis12GR on 6/30/2016 2:59 PM.
 * - TheDragonTeam
 */
class TheGiftOfTheGods(var maxUsable: Int = 0, var golden: EnumRarity = addRarity("GOLD", GOLD, "GOLD")) : BaseItem("the_gift_of_the_gods") {

    init {
        this.maxUsable = maxUses - 1
        this.maxDamage = maxUsable
        this.creativeTab = ArmorPlus.tabArmorplusItems
    }

    override fun getRarity(stack: ItemStack): EnumRarity = golden

    override fun onItemRightClick(worldIn: World, playerIn: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
        val blackListedItems = APConfig.blackListedItems.toList()

        val nbt: NBTTagCompound = if (playerIn.getHeldItem(hand).hasTagCompound()) playerIn.getHeldItem(hand).tagCompound as NBTTagCompound else NBTTagCompound()

        nbt.setInteger("Clicked", if (nbt.hasKey("Clicked")) nbt.getInteger("Clicked") + 1 else 1)
        playerIn.getHeldItem(hand).tagCompound = nbt

        var count: Int
        var item: Item? = ItemStack.EMPTY.item
        do when {
            APConfig.enableWhiteList -> {
                if (APConfig.enableWhiteList)
                    item = Item.getByNameOrId(whiteListedItems[random.nextInt(whitelistMax - whitelistMin + 1) + whitelistMin])
            }
            else -> {
                count = 256 + random.nextInt(32000 - 256)
                item = Item.getItemById(count)
            }
        }
        while (item == null || item == ItemStack.EMPTY.item || item == ItemStackUtils.getItem(blackListedItems.toString()) && enableBlackList)

        if (!worldIn.isRemote) {
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

    override fun addInformation(stack: ItemStack?, worldIn: World?, tooltip: MutableList<String>?, advanced: ITooltipFlag?) {
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
                ToolTipUtils.showInfo(tooltip, keyBindSneak, GOLD)
            }
        }
    }

    private val random = Random()
}