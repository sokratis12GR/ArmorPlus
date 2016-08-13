package net.thedragonteam.armorplus.items.arrows;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLapisArrow;

public class ItemLapisArrow extends ItemArrow {

    public ItemLapisArrow() {
        super();
    }

    @Override
    public EntityArrow createArrow(World world, ItemStack itemstack, EntityLivingBase shooter) {
        return new EntityLapisArrow(world, shooter);
    }

}