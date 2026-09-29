package com.sofodev.armorplus.registry.item.arrow;

import com.sofodev.armorplus.registry.entity.arrow.ArrowType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.item.component.TooltipDisplay;

public class APArrowItem extends ArrowItem {

    private final ArrowType type;

    public APArrowItem(ArrowType type) {
        super(com.sofodev.armorplus.registry.RegistryContext.itemProperties().rarity(Rarity.EPIC));
        this.type = type;
    }

//
//    @Override
//    public Rarity getRarity(ItemStack stack) {
//        return Rarity.create(type.getName(), type.getFormatting());
//    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
        return type.createArrow(world, stack, shooter, result);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
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