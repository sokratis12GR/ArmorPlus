package com.sofodev.armorplus.registry.entities.arrows;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import static com.sofodev.armorplus.registry.ModItems.*;
import static com.sofodev.armorplus.registry.items.extras.EffectData.create;
import static net.minecraft.particles.ParticleTypes.*;
import static net.minecraft.potion.Effects.*;
import static net.minecraft.util.text.TextFormatting.*;

public class APArrowProperty {

    public static final ArrowProperty COAL_ARROW_PROP = ArrowProperty.create("coal", 3.0, CLOUD, create(BLINDNESS));
    public static final ArrowProperty LAPIS_ARROW_PROP = ArrowProperty.create("lapis", 3.5, DRIPPING_WATER, create(NAUSEA));
    public static final ArrowProperty REDSTONE_ARROW_PROP = ArrowProperty.create("redstone", 3.5, POOF, create(SLOWNESS));
    public static final ArrowProperty EMERALD_ARROW_PROP = ArrowProperty.create("emerald", 5.0, HAPPY_VILLAGER, create(MINING_FATIGUE));
    public static final ArrowProperty OBSIDIAN_ARROW_PROP = ArrowProperty.create("obsidian", 6.0, SMOKE, create(WEAKNESS, 1));
    public static final ArrowProperty INFUSED_LAVA_ARROW_PROP = new ArrowProperty("infused_lava", 7.0, FLAME) {
        @Override
        public IArrow hit(LivingEntity living, Entity shootingEntity) {
            living.setFire(6);
            return this;
        }
    };
    public static final ArrowProperty GUARDIAN_ARROW_PROP = ArrowProperty.create("guardian", 10.5, FALLING_WATER, create(NAUSEA, 2));
    public static final ArrowProperty SUPER_STAR_ARROW_PROP = ArrowProperty.create("super_star", 10.5, CRIT, create(WITHER, 2));
    public static final ArrowProperty ENDER_DRAGON_ARROW_PROP = ArrowProperty.create("ender_dragon", 10.5, DRAGON_BREATH, create(WITHER, 4));
}