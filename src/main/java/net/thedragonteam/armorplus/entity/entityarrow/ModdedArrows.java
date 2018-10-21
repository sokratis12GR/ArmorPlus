package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;

import static net.minecraft.util.EnumParticleTypes.*;
import static net.thedragonteam.armorplus.registry.ModItems.*;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.addPotion;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class ModdedArrows {

    public static final ArrowProperties coalArrow = new ArrowProperties(3.0, CLOUD, itemCoalArrow) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.BLINDNESS, 180, 0, BAD);
        }
    };
    public static final ArrowProperties lapisArrow = new ArrowProperties(3.5, WATER_DROP, itemLapisArrow) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.NAUSEA, 180, 0, BAD);
        }
    };
    public static final ArrowProperties redstoneArrow = new ArrowProperties(3.5, REDSTONE, itemRedstoneArrow) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.SLOWNESS, 180, 0, BAD);
        }
    };
    public static final ArrowProperties lavaArrow = new ArrowProperties(5.5, FLAME, itemLavaArrow) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) living.setFire(6);
        }
    };
    public static final ArrowProperties enderDragonArrow = new ArrowProperties(10.5, DRAGON_BREATH, itemEnderDragonArrow) {
        @Override
        public void arrowHit(EntityLivingBase living, Entity shootingEntity) {
            if (living != shootingEntity) addPotion(living, MobEffects.WITHER, 180, 4, BAD);
        }
    };

}
