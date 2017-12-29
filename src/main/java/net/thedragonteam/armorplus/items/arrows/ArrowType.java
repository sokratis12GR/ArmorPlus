package net.thedragonteam.armorplus.items.arrows;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.entity.entityarrow.*;
import net.thedragonteam.armorplus.util.ArrowUtils;

import java.util.List;

import static java.util.Locale.ROOT;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public enum ArrowType {
    COAL("Applies Blindness", 2.0, TextFormatting.GRAY) {
        @Override
        public EntityArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityCoalArrow(world, shooter);
        }
    },
    LAPIS("Applies Nausea", 3.5, TextFormatting.DARK_AQUA) {
        @Override
        public EntityArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityLapisArrow(world, shooter);
        }
    },
    REDSTONE("Applies Slowness", 3.5D, TextFormatting.DARK_RED) {
        @Override
        public EntityArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityRedstoneArrow(world, shooter);
        }
    },
    INFUSED_LAVA("Sets on Fire", 5.5D, TextFormatting.GOLD) {
        @Override
        public EntityArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityLavaArrow(world, shooter);
        }
    },
    ENDER_DRAGON("Applies Wither 4", 8.5, TextFormatting.DARK_PURPLE) {
        @Override
        public EntityArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityEnderDragonArrow(world, shooter);
        }
    };

    private final String abilityDescription;
    private final double damage;
    private final TextFormatting formatting;

    ArrowType(String abilityDescription, double damage, TextFormatting formatting) {
        this.abilityDescription = abilityDescription;
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

    public abstract EntityArrow createArrow(World world, EntityLivingBase shooter);

    @SideOnly(Side.CLIENT)
    public void addInformation(List<String> tooltip) {
        ArrowUtils.addArrowInformation(tooltip, getAbilityDescription(), getDamage(), getFormatting());
    }

}
