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
public class EntityRedstoneArrow extends EntityArrow {

    public EntityRedstoneArrow(World worldIn) {
        super(worldIn);
    }

    public EntityRedstoneArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityRedstoneArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(3.5);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote && !this.inGround) {
            //noinspection MethodCallSideOnly
            spawnParticle(this, EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ);
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return getItemStack(ModItems.itemRedstoneArrow);
    }

    @Override
    protected void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        if (living != shootingEntity) addPotion(living, MobEffects.SLOWNESS, 180, 0, BAD);
    }

}