package net.thedragonteam.armorplus.items.arrows;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.entity.entityarrow.EntityRedstoneArrow;

public class ItemRedstoneArrow extends ItemArrow {

    public ItemRedstoneArrow() {
        super();
    }

    @Override
    public EntityArrow createArrow(World world, ItemStack itemstack, EntityLivingBase shooter) {
        return new EntityRedstoneArrow(world, shooter);
    }

}