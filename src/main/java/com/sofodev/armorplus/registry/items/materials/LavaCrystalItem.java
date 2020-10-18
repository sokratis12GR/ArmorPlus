package com.sofodev.armorplus.registry.items.materials;

import com.sofodev.armorplus.registry.items.APItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.DamageSource;

import static net.minecraft.util.text.TextFormatting.GOLD;

/**
 * @author Sokratis Fotkatzikis
 **/
public class LavaCrystalItem extends APItem {

    private final boolean isInfused;

    private int[] burnTime = new int[]{20000, 22000};

    public LavaCrystalItem(boolean isInfused) {
        super(new Properties().isImmuneToFire());
        this.isInfused = isInfused;
    }

    @Override
    public boolean isDamageable(DamageSource damageSource) {
        return super.isDamageable(damageSource);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create("Lava Crystalic", GOLD);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return isInfused ? burnTime[1] : burnTime[0];
    }

}