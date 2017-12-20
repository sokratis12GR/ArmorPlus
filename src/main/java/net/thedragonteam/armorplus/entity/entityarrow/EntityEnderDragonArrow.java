package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.registry.ModItems;

import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.addPotion;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;
import static net.thedragonteam.thedragonlib.util.ParticlesHelper.spawnParticle;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 */
public class EntityEnderDragonArrow extends EntityArrow {

    public EntityEnderDragonArrow(World worldIn) {
        super(worldIn);
    }

    public EntityEnderDragonArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityEnderDragonArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(10.5);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote && !this.inGround) {
            //noinspection MethodCallSideOnly
            spawnParticle(this, EnumParticleTypes.DRAGON_BREATH, this.posX, this.posY, this.posZ);
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return getItemStack(ModItems.itemEnderDragonArrow);
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        if (living != shootingEntity) addPotion(living, MobEffects.WITHER, 180, 4, BAD);
    }

}