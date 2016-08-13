package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.registry.ModItems;

public class EntityLavaArrow extends EntityArrow {

    public EntityLavaArrow(World worldIn) {
        super(worldIn);
    }

    public EntityLavaArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityLavaArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.worldObj.isRemote && !this.inGround) {
            this.worldObj.spawnParticle(EnumParticleTypes.LAVA, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D,new int[0]);
        }
    }

    @Override
    public ItemStack getArrowStack() {
        return new ItemStack(ModItems.LAVA_ARROW);
    }

    @Override
    public void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        World world = living.getEntityWorld();
        if (living != shootingEntity) {
            living.setFire(6);
        }
    }

}