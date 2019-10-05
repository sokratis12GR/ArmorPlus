/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.common.registry.items.arrows;

import com.sofodev.armorplus.common.registry.entities.entityarrow.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpellcasterIllager;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

import static com.sofodev.armorplus.client.utils.ToolTipUtils.addArrowInformation;
import static com.sofodev.armorplus.common.registry.entities.entityarrow.ModdedArrows.*;
import static java.util.Locale.ROOT;
import static net.minecraft.util.text.TextFormatting.*;

/**
 * @author Sokratis Fotkatzikis
 */
public enum ArrowType {
    NONE(0, RESET) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityModdedArrow(world);
        }
    },
    COAL(3.0, GRAY) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityCoalArrow(world, shooter);
        }
    },
    LAPIS(3.5, DARK_AQUA) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityLapisArrow(world, shooter);
        }
    },
    REDSTONE(3.5, DARK_RED) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityRedstoneArrow(world, shooter);
        }
    },
    EMERALD(5.0, DARK_GREEN) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityEmeraldArrow(world, shooter);
        }
    },
    OBSIDIAN(6.0, DARK_GRAY) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityObsidianArrow(world, shooter);
        }
    },
    INFUSED_LAVA(7.0, GOLD) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityInfusedLavaArrow(world, shooter);
        }
    },
    GUARDIAN(10.5, AQUA) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityGuardianArrow(world, shooter);
        }
    },
    SUPER_STAR(10.5, WHITE) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntitySuperStarArrow(world, shooter);
        }
    },
    ENDER_DRAGON(10.5, DARK_PURPLE) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityEnderDragonArrow(world, shooter);
        }
    };

    private final String abilityDescription;
    private final double damage;
    private final TextFormatting formatting;

    ArrowType(double damage, TextFormatting formatting) {
        this.abilityDescription = new TextComponentTranslation("item.armorplus." + this.name().toLowerCase() + "_arrow.ability_desc").getFormattedText();
        this.damage = damage;
        this.formatting = formatting;
    }

    public String getAbilityDescription() {
        return abilityDescription;
    }

    public double getDamage() {
        return damage;
    }

    public TextFormatting getFormatting() {
        return formatting;
    }

    public String getName() {
        return this.name().toLowerCase(ROOT);
    }

    public String getItemArrowName() {
        return this.getName() + "_arrow";
    }

    public abstract EntityModdedArrow createArrow(World world, EntityLivingBase shooter);

    @SideOnly(Side.CLIENT)
    public void addInformation(List<String> tooltip) {
        addArrowInformation(tooltip, getAbilityDescription(), getDamage(), getFormatting());
    }

    public static ArrowType getFromId(int idIn) {
        return Arrays.stream(values()).filter(arrowType -> idIn == arrowType.ordinal()).findFirst().orElse(NONE);
    }
}
