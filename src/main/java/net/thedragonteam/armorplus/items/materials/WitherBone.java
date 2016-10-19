/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseItem;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class WitherBone extends BaseItem {

    public WitherBone() {
        super("wither_bone");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.WHITE + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
}
