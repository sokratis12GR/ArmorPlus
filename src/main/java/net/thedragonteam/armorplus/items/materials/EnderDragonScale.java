/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseItem;

import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class EnderDragonScale extends BaseItem {

    public EnderDragonScale() {
        super("ender_dragon_scale");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.DARK_PURPLE + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
}
