package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;

import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public abstract class ArrowProperties {

    private final double dmg;
    private final EnumParticleTypes particleType;
    private final ItemStack arrowItem;

    public ArrowProperties(double dmg, EnumParticleTypes particleType, Object arrowItem) {
        this(dmg, particleType, getItemStack(arrowItem));
    }

    public ArrowProperties(double dmg, EnumParticleTypes particleType, ItemStack arrowItem) {
        this.dmg = dmg;
        this.particleType = particleType;
        this.arrowItem = arrowItem;
    }

    public double getDmg() {
        return dmg;
    }

    public EnumParticleTypes getParticleType() {
        return particleType;
    }

    public ItemStack getArrowItem() {
        return arrowItem;
    }

    public abstract void arrowHit(EntityLivingBase living, Entity shootingEntity);
}
