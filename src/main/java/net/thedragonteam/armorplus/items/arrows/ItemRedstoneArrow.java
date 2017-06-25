package net.thedragonteam.armorplus.items.arrows;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.entity.entityarrow.EntityRedstoneArrow;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.util.ArrowUtils;

import java.util.List;

public class ItemRedstoneArrow extends ItemArrow implements IModelHelper {

    public ItemRedstoneArrow() {
        this.setRegistryName("redstone_arrow");
        this.setUnlocalizedName(ArmorPlus.MODID + "." + "redstone_arrow");
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(getRegistryName(), "redstone");
    }

    @Override
    public EntityArrow createArrow(World world, ItemStack itemstack, EntityLivingBase shooter) {
        return new EntityRedstoneArrow(world, shooter);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        ArrowUtils.addArrowInformation(tooltip, "Applies Slowness", 3.5D, TextFormatting.DARK_RED);
    }
}