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
import static net.thedragonteam.armorplus.util.ArrowUtils.addArrowInformation;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public enum ArrowType {
    COAL {
        @Override
        public EntityArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityCoalArrow(world, shooter);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(List<String> tooltip) {
            addArrowInformation(tooltip, "Applies Blindness", 2.0, TextFormatting.GRAY);
        }
    },
    LAPIS {
        @Override
        public EntityArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityLapisArrow(world, shooter);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(List<String> tooltip) {
            ArrowUtils.addArrowInformation(tooltip, "Applies Nausea", 3.5, TextFormatting.DARK_AQUA);
        }
    },
    REDSTONE {
        @Override
        public EntityArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityRedstoneArrow(world, shooter);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(List<String> tooltip) {
            ArrowUtils.addArrowInformation(tooltip, "Applies Slowness", 3.5D, TextFormatting.DARK_RED);
        }
    },
    INFUSED_LAVA {
        @Override
        public EntityArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityLavaArrow(world, shooter);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(List<String> tooltip) {
            ArrowUtils.addArrowInformation(tooltip, "Sets on Fire", 5.5D, TextFormatting.GOLD);
        }
    },
    ENDER_DRAGON {
        @Override
        public EntityArrow createArrow(World world, EntityLivingBase shooter) {
            return new EntityEnderDragonArrow(world, shooter);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(List<String> tooltip) {
            ArrowUtils.addArrowInformation(tooltip, "Applies Wither 4", 8.5, TextFormatting.DARK_PURPLE);
        }
    };

    ArrowType() {
    }

    public String getName() {
        return this.name().toLowerCase(ROOT);
    }

    public String getItemArrowName() {
        return this.getName() + "_arrow";
    }

    public abstract EntityArrow createArrow(World world, EntityLivingBase shooter);

    @SideOnly(Side.CLIENT)
    public abstract void addInformation(List<String> tooltip);

}
