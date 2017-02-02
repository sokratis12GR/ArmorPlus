/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.arrows;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entityarrow.EntityRedstoneArrow;
import net.thedragonteam.armorplus.util.ArrowUtils;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemRedstoneArrow extends ItemArrow {

    public ItemRedstoneArrow() {
        this.setRegistryName("redstone_arrow");
        this.setUnlocalizedName(ArmorPlus.MODID + "." + "redstone_arrow");
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    @Nonnull
    public EntityArrow createArrow(@Nonnull World world, @Nonnull ItemStack itemstack, EntityLivingBase shooter) {
        return new EntityRedstoneArrow(world, shooter);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        ArrowUtils.addArrowInformation(tooltip, "Applies Slowness", 3.5D, TextFormatting.DARK_RED);
    }
}