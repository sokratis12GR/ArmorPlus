package com.sofodev.armorplus.registry.entity.arrow;

import com.sofodev.armorplus.registry.entity.arrow.impl.AmethystArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.CoalArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.EmeraldArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.EnderDragonArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.GlowstoneArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.GuardianArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.HoneyArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.InfusedLavaArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.LapisArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.ObsidianArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.QuartzArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.RedstoneArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.SuperStarArrowEntity;
import com.sofodev.armorplus.registry.entity.arrow.impl.WardenArrowEntity;
import com.sofodev.armorplus.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;

import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.AMETHYST_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.COAL_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.EMERALD_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.ENDER_DRAGON_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.GLOWSTONE_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.GUARDIAN_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.HONEY_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.INFUSED_LAVA_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.LAPIS_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.OBSIDIAN_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.QUARTZ_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.REDSTONE_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.SUPER_STAR_ARROW_PROP;
import static com.sofodev.armorplus.registry.entity.arrow.APArrowProperty.WARDEN_ARROW_PROP;
import static net.minecraft.ChatFormatting.AQUA;
import static net.minecraft.ChatFormatting.DARK_AQUA;
import static net.minecraft.ChatFormatting.DARK_GRAY;
import static net.minecraft.ChatFormatting.DARK_GREEN;
import static net.minecraft.ChatFormatting.DARK_PURPLE;
import static net.minecraft.ChatFormatting.DARK_RED;
import static net.minecraft.ChatFormatting.GOLD;
import static net.minecraft.ChatFormatting.GRAY;
import static net.minecraft.ChatFormatting.LIGHT_PURPLE;
import static net.minecraft.ChatFormatting.WHITE;
import static net.minecraft.ChatFormatting.YELLOW;

public class ArrowType {

    public static final ArrowType COAL = new ArrowType(COAL_ARROW_PROP, "coal", 3.0, GRAY) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new CoalArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType LAPIS = new ArrowType(LAPIS_ARROW_PROP, "lapis", 3.5, DARK_AQUA) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new LapisArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType REDSTONE = new ArrowType(REDSTONE_ARROW_PROP, "redstone", 3.5, DARK_RED) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new RedstoneArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType EMERALD = new ArrowType(EMERALD_ARROW_PROP, "emerald", 5.0, DARK_GREEN) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new EmeraldArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType OBSIDIAN = new ArrowType(OBSIDIAN_ARROW_PROP, "obsidian", 6.0, DARK_GRAY) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new ObsidianArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType INFUSED_LAVA = new ArrowType(INFUSED_LAVA_ARROW_PROP, "infused_lava", 10.5, GOLD) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new InfusedLavaArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType GUARDIAN = new ArrowType(GUARDIAN_ARROW_PROP, "guardian", 10.5, AQUA) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new GuardianArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType SUPER_STAR = new ArrowType(SUPER_STAR_ARROW_PROP, "super_star", 10.5, WHITE) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new SuperStarArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType ENDER_DRAGON = new ArrowType(ENDER_DRAGON_ARROW_PROP, "ender_dragon", 10.5, DARK_PURPLE) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new EnderDragonArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType WARDEN = new ArrowType(WARDEN_ARROW_PROP, "warden", 10.5, DARK_AQUA) {
        @Override
        public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new WardenArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType AMETHYST = new ArrowType(AMETHYST_ARROW_PROP, "amethyst", 3.5, LIGHT_PURPLE) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new AmethystArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType QUARTZ = new ArrowType(QUARTZ_ARROW_PROP, "quartz", 3.5, WHITE) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new QuartzArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType GLOWSTONE = new ArrowType(GLOWSTONE_ARROW_PROP, "glowstone", 3.0, YELLOW) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new GlowstoneArrowEntity(shooter, world, stack, result);
        }
    };
    public static final ArrowType HONEY = new ArrowType(HONEY_ARROW_PROP, "honey", 3.0, GOLD) {
        @Override
        public APArrowEntity createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack result) {
            return new HoneyArrowEntity(shooter, world, stack, result);
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

    public void appendHoverText(Consumer<Component> tooltip) {
        ToolTipUtils.appendArrowHoverText(tooltip, this.getAbilityDescription(), this.getDmg(), this.getFormatting());
    }
}