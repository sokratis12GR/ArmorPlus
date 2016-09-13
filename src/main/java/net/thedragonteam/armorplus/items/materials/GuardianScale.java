/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.materials;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.items.base.BaseItem;

import static net.thedragonteam.core.util.TextHelper.localize;

public class GuardianScale extends BaseItem {

    public GuardianScale() {
        super("guardian_scale");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.AQUA + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }
}
