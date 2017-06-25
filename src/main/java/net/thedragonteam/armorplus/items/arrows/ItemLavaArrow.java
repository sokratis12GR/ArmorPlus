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
import net.thedragonteam.armorplus.entity.entityarrow.EntityLavaArrow;
import net.thedragonteam.armorplus.iface.IModelHelper;
import net.thedragonteam.armorplus.util.ArrowUtils;

import java.util.List;

import static net.thedragonteam.armorplus.util.Utils.setName;

public class ItemLavaArrow extends ItemArrow implements IModelHelper {

    public ItemLavaArrow() {
        this.setRegistryName("infused_lava_arrow");
        this.setUnlocalizedName(setName("infused_lava_arrow"));
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        this.initModel(getRegistryName(), "infused_lava");
    }

    @Override
    public EntityArrow createArrow(World world, ItemStack itemstack, EntityLivingBase shooter) {
        return new EntityLavaArrow(world, shooter);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        ArrowUtils.addArrowInformation(tooltip, "Sets on Fire", 5.5D, TextFormatting.GOLD);
    }
}