/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.baubles;


import baubles.api.BaublesApi;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.thedragonteam.armorplus.items.base.BaseItem;

import javax.annotation.Nonnull;

public abstract class ItemBauble extends BaseItem implements IBauble {

    public ItemBauble(String name) {
        super(name);
        setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        ItemStack toEquip = stack.copy();
        toEquip.setCount(1);
        if (canEquip(toEquip, player)) {
            IItemHandlerModifiable baubles = BaublesApi.getBaublesHandler(player);
            for (int i = 0; i < baubles.getSlots(); i++) {
                ItemStack simulate = baubles.insertItem(i, toEquip, true);
                if (simulate.isEmpty()) {
                    ItemStack stackInSlot = baubles.getStackInSlot(i);
                    if (stackInSlot.isEmpty() || ((IBauble) stackInSlot.getItem()).canUnequip(stackInSlot, player)) {
                        if (!world.isRemote) {
                            baubles.setStackInSlot(i, toEquip);
                            stack.shrink(1);
                        }

                        if (!stackInSlot.isEmpty()) {
                            ((IBauble) stackInSlot.getItem()).onUnequipped(stackInSlot, player);
                            return ActionResult.newResult(EnumActionResult.SUCCESS, stackInSlot.copy());
                        }
                        break;
                    }
                }
            }
        }

        return ActionResult.newResult(EnumActionResult.PASS, stack);
    }


    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.RARE;
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.75F, 1.9f);
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.75F, 2f);
    }
}