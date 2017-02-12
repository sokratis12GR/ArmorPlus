/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.arrows;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entityarrow.EntityLapisArrow;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.util.ArrowUtils;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemLapisArrow extends ItemArrow implements IModelHelper {

    public ItemLapisArrow() {
        this.setRegistryName("lapis_arrow");
        this.setUnlocalizedName(ArmorPlus.MODID + "." + "lapis_arrow");
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    public void initModel() {
        this.initModel(this, getRegistryName(), 0);
    }

    @Override
    @Nonnull
    public EntityArrow createArrow(@Nonnull World world, @Nonnull ItemStack itemstack, EntityLivingBase shooter) {
        return new EntityLapisArrow(world, shooter);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        ArrowUtils.addArrowInformation(tooltip, "Applies Nausea", 3.5D, TextFormatting.DARK_AQUA);
    }
}