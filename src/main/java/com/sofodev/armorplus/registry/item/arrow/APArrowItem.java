package com.sofodev.armorplus.registry.item.arrow;

import com.sofodev.armorplus.registry.entity.arrow.ArrowType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class APArrowItem extends ArrowItem {

    private final ArrowType type;

    public APArrowItem(ArrowType type) {
        super(new Item.Properties().rarity(Rarity.EPIC));
        this.type = type;
    }

//
//    @Override
//    public Rarity getRarity(ItemStack stack) {
//        return Rarity.create(type.getName(), type.getFormatting());
//    }

    @Override
    public AbstractArrow createArrow(
            Level level,
            ItemStack ammo,
            LivingEntity shooter,
            ItemStack weapon
    ) {
        return type.createArrow(level, ammo, shooter, weapon);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
        type.appendHoverText(tooltip);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, LivingEntity owner) {
        return super.isInfinite(stack, bow, owner);
    }

//    @Override
//    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
//        int enchant = getItemEnchantmentLevel(INFINITY_ARROWS, bow);
//        return enchant > 0;
//    }
}