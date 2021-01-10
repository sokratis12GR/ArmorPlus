package com.sofodev.armorplus.registry.items.materials;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;

import static net.minecraft.util.text.TextFormatting.AQUA;

/**
 * @author Sokratis Fotkatzikis
 **/
public class FrostCrystalItem extends APItem {

    private final boolean isInfused;

    public FrostCrystalItem(boolean isInfused) {
        this.isInfused = isInfused;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create("Crystalic", AQUA);
    }

}