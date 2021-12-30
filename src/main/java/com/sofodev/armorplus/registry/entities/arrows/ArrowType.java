package com.sofodev.armorplus.registry.entities.arrows;

import com.sofodev.armorplus.registry.entities.arrows.impl.*;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;

import java.util.List;

import static net.minecraft.ChatFormatting.*;

public class ArrowType {

    public static final ArrowType COAL = new ArrowType("coal", 3.0, GRAY) {
        @Override
        public APArrowEntity createArrow(LivingEntity shooter, Level world) {
            return new CoalArrowEntity(shooter, world);
        }
    };
    public static final ArrowType LAPIS = new ArrowType("lapis", 3.5, DARK_AQUA) {
        @Override
        public APArrowEntity createArrow(LivingEntity shooter, Level world) {
            return new LapisArrowEntity(shooter, world);
        }
    };
    public static final ArrowType REDSTONE = new ArrowType("redstone", 3.5, DARK_RED) {
        @Override
        public APArrowEntity createArrow(LivingEntity shooter, Level world) {
            return new RedstoneArrowEntity(shooter, world);
        }
    };
    public static final ArrowType EMERALD = new ArrowType("emerald", 5.0, DARK_GREEN) {
        @Override
        public APArrowEntity createArrow(LivingEntity shooter, Level world) {
            return new EmeraldArrowEntity(shooter, world);
        }
    };
    public static final ArrowType OBSIDIAN = new ArrowType("obsidian", 6.0, DARK_GRAY) {
        @Override
        public AbstractArrow createArrow(LivingEntity shooter, Level world) {
            return new ObsidianArrowEntity(shooter, world);
        }
    };
    public static final ArrowType INFUSED_LAVA = new ArrowType("infused_lava", 10.5, GOLD) {
        @Override
        public AbstractArrow createArrow(LivingEntity shooter, Level world) {
            return new InfusedLavaArrowEntity(shooter, world);
        }
    };
    public static final ArrowType GUARDIAN = new ArrowType("guardian", 10.5, AQUA) {
        @Override
        public AbstractArrow createArrow(LivingEntity shooter, Level world) {
            return new GuardianArrowEntity(shooter, world);
        }
    };
    public static final ArrowType SUPER_STAR = new ArrowType("super_star", 10.5, WHITE) {
        @Override
        public AbstractArrow createArrow(LivingEntity shooter, Level world) {
            return new SuperStarArrowEntity(shooter, world);
        }
    };
    public static final ArrowType ENDER_DRAGON = new ArrowType("ender_dragon", 10.5, DARK_PURPLE) {
        @Override
        public AbstractArrow createArrow(LivingEntity shooter, Level world) {
            return new EnderDragonArrowEntity(shooter, world);
        }
    };

    private final String name;
    private final double dmg;
    private final ChatFormatting formatting;

    ArrowType(String name, double dmg, ChatFormatting formatting) {
        this.name = name;
        this.dmg = dmg;
        this.formatting = formatting;
    }

    public String getName() {
        return name;
    }

    public TranslatableComponent getAbilityDescription() {
        return new TranslatableComponent("tooltip.armorplus." + getName() + "_arrow.ability_desc");
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

    public AbstractArrow createArrow(LivingEntity shooter, Level world) {
        return new Arrow(world, shooter);
    }

    public void appendHoverText(List<Component> tooltip) {
        ToolTipUtils.appendArrowHoverText(tooltip, this.getAbilityDescription(), this.getDmg(), this.getFormatting());
    }
}