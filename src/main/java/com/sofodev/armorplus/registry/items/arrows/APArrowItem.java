package com.sofodev.armorplus.registry.items.arrows;

import com.sofodev.armorplus.ArmorPlus;
import com.sofodev.armorplus.registry.entities.arrows.ArrowType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.Enchantments.INFINITY;

public class APArrowItem extends ArrowItem {

    private final ArrowType type;

    public APArrowItem(ArrowType type) {
        super(new Item.Properties().group(ArmorPlus.AP_WEAPON_GROUP).rarity(Rarity.create("ARROW_TYPE", type.getFormatting())));
        this.type = type;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.create(type.getName(), type.getFormatting());
    }

    @Override
    public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return type.createArrow(shooter, world);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        type.addInformation(tooltip);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
        int enchant = getEnchantmentLevel(INFINITY, bow);
        return enchant > 0;
    }
}