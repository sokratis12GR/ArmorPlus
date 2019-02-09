/*
 * Copyright (c) Sokratis Fotkatzikis (sokratis12GR) 2015-2019.
 */

package com.sofodev.armorplus.items.arrows;

import com.sofodev.armorplus.entity.entityarrow.*;
import com.sofodev.armorplus.util.ArrowUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static java.util.Locale.ROOT;
import static net.minecraft.util.text.TextFormatting.*;
import static net.minecraft.util.text.TextFormatting.DARK_AQUA;

/**
 * @author Sokratis Fotkatzikis
 */
public enum ArrowType {
    COAL(2.0, GRAY) {
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
    REDSTONE(3.5D, DARK_RED) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityRedstoneArrow(world, shooter);
        }
    },
    INFUSED_LAVA(5.5D, GOLD) {
        @Override
        public EntityModdedArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityLavaArrow(world, shooter);
        }
    },
    ENDER_DRAGON(8.5, DARK_PURPLE) {
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
        ArrowUtils.addArrowInformation(tooltip, getAbilityDescription(), getDamage(), getFormatting());
    }

}
