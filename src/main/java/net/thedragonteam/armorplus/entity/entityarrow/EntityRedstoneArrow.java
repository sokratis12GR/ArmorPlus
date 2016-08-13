package net.thedragonteam.armorplus.entity.entityarrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.registry.ModItems;

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
    public void onUpdate() {
        super.onUpdate();

        if (this.worldObj.isRemote && !this.inGround) {
            this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }

    @Override
    public ItemStack getArrowStack() {
        return new ItemStack(ModItems.REDSTONE_ARROW);
    }

    @Override
    public void arrowHit(EntityLivingBase living) {
        super.arrowHit(living);
        World world = living.getEntityWorld();
        if (living != shootingEntity) {
            living.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 180, 0, false, true));
        }
    }

}