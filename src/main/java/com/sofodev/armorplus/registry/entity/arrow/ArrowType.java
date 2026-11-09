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
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

    public static final ArrowType COAL = new ArrowType(COAL_ARROW_PROP, GRAY, CoalArrowEntity::new);
    public static final ArrowType LAPIS = new ArrowType(LAPIS_ARROW_PROP, DARK_AQUA, LapisArrowEntity::new);
    public static final ArrowType REDSTONE = new ArrowType(REDSTONE_ARROW_PROP, DARK_RED, RedstoneArrowEntity::new);
    public static final ArrowType EMERALD = new ArrowType(EMERALD_ARROW_PROP, DARK_GREEN, EmeraldArrowEntity::new);
    public static final ArrowType OBSIDIAN = new ArrowType(OBSIDIAN_ARROW_PROP, DARK_GRAY, ObsidianArrowEntity::new);
    public static final ArrowType INFUSED_LAVA = new ArrowType(INFUSED_LAVA_ARROW_PROP, GOLD, InfusedLavaArrowEntity::new);
    public static final ArrowType GUARDIAN = new ArrowType(GUARDIAN_ARROW_PROP, AQUA, GuardianArrowEntity::new);
    public static final ArrowType SUPER_STAR = new ArrowType(SUPER_STAR_ARROW_PROP, WHITE, SuperStarArrowEntity::new);
    public static final ArrowType ENDER_DRAGON = new ArrowType(ENDER_DRAGON_ARROW_PROP, DARK_PURPLE, EnderDragonArrowEntity::new);
    public static final ArrowType WARDEN = new ArrowType(WARDEN_ARROW_PROP, DARK_AQUA, WardenArrowEntity::new);
    public static final ArrowType AMETHYST = new ArrowType(AMETHYST_ARROW_PROP, LIGHT_PURPLE, AmethystArrowEntity::new);
    public static final ArrowType QUARTZ = new ArrowType(QUARTZ_ARROW_PROP, WHITE, QuartzArrowEntity::new);
    public static final ArrowType GLOWSTONE = new ArrowType(GLOWSTONE_ARROW_PROP, YELLOW, GlowstoneArrowEntity::new);
    public static final ArrowType HONEY = new ArrowType(HONEY_ARROW_PROP, GOLD, HoneyArrowEntity::new);

    /** Makes the arrow entity of a type. */
    @FunctionalInterface
    private interface Factory {
        AbstractArrow create(LivingEntity shooter, Level level, ItemStack ammo, @Nullable ItemStack weapon);
    }

    private final ArrowProperty property;
    private final ChatFormatting formatting;
    private final Factory factory;

    private ArrowType(ArrowProperty property, ChatFormatting formatting, Factory factory) {
        this.property = property;
        this.formatting = formatting;
        this.factory = factory;
    }

    public String getName() {
        return property.getName();
    }

    public Component getAbilityDescription() {
        return Component.translatable("tooltip.armorplus." + getName() + "_arrow.ability_desc");
    }

    public ChatFormatting getFormatting() {
        return formatting;
    }

    public double getDmg() {
        return property.getDmg();
    }

    public String getItemArrowName() {
        return this.getName() + "_arrow";
    }

    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        return factory.create(shooter, level, ammo, weapon);
    }

    public void appendHoverText(List<Component> tooltip) {
        ToolTipUtils.appendArrowHoverText(tooltip, this.getAbilityDescription(), this.getDmg(), this.getFormatting());
    }
}
