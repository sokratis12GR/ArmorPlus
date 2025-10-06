package com.sofodev.armorplus.registry.entity.arrow;

import com.sofodev.armorplus.registry.entity.arrow.impl.*;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.*;
import static net.minecraft.ChatFormatting.*;

public class ArrowType {

    public static final ArrowType COAL = new ArrowType(COAL_ARROW_PROP, "coal", 3.0, GRAY) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new CoalArrowEntity(shooter, world);
        }
    };
    public static final ArrowType LAPIS = new ArrowType(LAPIS_ARROW_PROP, "lapis", 3.5, DARK_AQUA) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new LapisArrowEntity(shooter, world);
        }
    };
    public static final ArrowType REDSTONE = new ArrowType(REDSTONE_ARROW_PROP, "redstone", 3.5, DARK_RED) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new RedstoneArrowEntity(shooter, world);
        }
    };
    public static final ArrowType EMERALD = new ArrowType(EMERALD_ARROW_PROP, "emerald", 5.0, DARK_GREEN) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new EmeraldArrowEntity(shooter, world);
        }
    };
    public static final ArrowType OBSIDIAN = new ArrowType(OBSIDIAN_ARROW_PROP, "obsidian", 6.0, DARK_GRAY) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new ObsidianArrowEntity(shooter, world);
        }
    };
    public static final ArrowType INFUSED_LAVA = new ArrowType(INFUSED_LAVA_ARROW_PROP, "infused_lava", 10.5, GOLD) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new InfusedLavaArrowEntity(shooter, world);
        }
    };
    public static final ArrowType GUARDIAN = new ArrowType(GUARDIAN_ARROW_PROP, "guardian", 10.5, AQUA) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new GuardianArrowEntity(shooter, world);
        }
    };
    public static final ArrowType SUPER_STAR = new ArrowType(SUPER_STAR_ARROW_PROP, "super_star", 10.5, WHITE) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new SuperStarArrowEntity(shooter, world);
        }
    };
    public static final ArrowType ENDER_DRAGON = new ArrowType(ENDER_DRAGON_ARROW_PROP, "ender_dragon", 10.5, DARK_PURPLE) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new EnderDragonArrowEntity(shooter, world);
        }
    };

    private final ArrowProperty property;
    private final String name;
    private final double dmg;
    private final ChatFormatting formatting;

    ArrowType(ArrowProperty property, String name, double dmg, ChatFormatting formatting) {
        this.property = property;
        this.name = name;
        this.dmg = dmg;
        this.formatting = formatting;
    }

    public String getName() {
        return name;
    }

    public Component getAbilityDescription() {
        return Component.translatable("tooltip.armorplus." + getName() + "_arrow.ability_desc");
    }

    public ChatFormatting getFormatting() {
        return formatting;
    }

    public double getDmg() {
        return dmg;
    }

    public String getItemArrowName() {
        return this.getName() + "_arrow";
    }

    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
        return new Arrow(world, shooter, stack, result);
    }

    public void appendHoverText(List<Component> tooltip) {
        ToolTipUtils.appendArrowHoverText(tooltip, this.getAbilityDescription(), this.getDmg(), this.getFormatting());
    }
}