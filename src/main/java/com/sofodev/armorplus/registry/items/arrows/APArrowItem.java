package com.sofodev.armorplus.registry.items.arrows;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.entities.arrows.ArrowType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel;
import static net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS;

public class APArrowItem extends ArrowItem {

    private final ArrowType type;

    public APArrowItem(ArrowType type) {
        super(new Item.Properties().tab(ArmorPlus.AP_WEAPON_GROUP).rarity(Rarity.create("ARROW_TYPE", type.getFormatting())));
        this.type = type;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create(type.getName(), type.getFormatting());
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter) {
        return type.createArrow(shooter, world);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        type.appendHoverText(tooltip);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = getItemEnchantmentLevel(INFINITY_ARROWS, bow);
        return enchant > 0;
    }
}