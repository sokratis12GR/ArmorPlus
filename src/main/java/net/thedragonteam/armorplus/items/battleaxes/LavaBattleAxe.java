/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.battleaxes;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseSword;
import net.thedragonteam.armorplus.registry.ModItems;

/**
 * net.thedragonteam.armorplus.items.battleaxes
 * ArmorPlus created by sokratis12GR on 4/19/2016.
 * - TheDragonTeam
 */
public class LavaBattleAxe extends BaseSword {

    public LavaBattleAxe() {
        super(ModItems.battleAxeLavaMaterial, "lava_battle_axe", ModItems.lavaCrystal, new ItemStack(ModItems.lavaCrystal, 1, 1), TextFormatting.GOLD, "Sets on Fire");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (target instanceof EntityLivingBase) {
            ((EntityLivingBase) target).setFire(6);
        }
        return true;
    }
}
