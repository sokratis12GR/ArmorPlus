package com.sofodev.armorplus.common.data;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class ArmorData implements IArmorMaterial {

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String textureName;
    private final int durability;
    private final int[] armorPoints;
    private final int enchantability;
    private final SoundEvent soundOnEquip;
    private final double toughnessPoints;
    private final Supplier<Supplier<Ingredient>> supplier;

    public ArmorData(String textureName, int durability, int[] armorPoints, int enchantability, SoundEvent soundOnEquip, double toughnessPoints, Supplier<Supplier<Ingredient>> supplier) {
        this.textureName = textureName;
        this.durability = durability;
        this.armorPoints = armorPoints;
        this.enchantability = enchantability;
        this.soundOnEquip = soundOnEquip;
        this.toughnessPoints = toughnessPoints;
        this.supplier = supplier;
    }

    @Override
    public int getDurability(EntityEquipmentSlot slot) {
        return MAX_DAMAGE_ARRAY[slot.getIndex()] * this.durability;
    }

    @Override
    public int getDamageReductionAmount(EntityEquipmentSlot slot) {
        return this.armorPoints[slot.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return soundOnEquip;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return supplier.get().get();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return textureName;
    }

    @Override
    public float getToughness() {
        return (float) toughnessPoints;
    }
}
