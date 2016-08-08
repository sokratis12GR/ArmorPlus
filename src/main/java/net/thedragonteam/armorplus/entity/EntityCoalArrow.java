package net.thedragonteam.armorplus.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.registry.ModItems;

public class EntityCoalArrow extends EntityArrow {

    public EntityCoalArrow(World worldIn) {
        super(worldIn);
    }

    public EntityCoalArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    public EntityCoalArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.worldObj.isRemote && !this.inGround) {
            this.worldObj.spawnParticle(EnumParticleTypes.CLOUD, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }

    @Override
    public ItemStack getArrowStack() {
        return new ItemStack(ModItems.COAL_ARROW);
    }

    @Override
    public void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        World world = living.getEntityWorld();
        living.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 180, 0, false, true));
        if (living != shootingEntity) {
            world.createExplosion(shootingEntity, living.posX, living.posY, living.posZ, 4.0F, true);
        }
    }

}