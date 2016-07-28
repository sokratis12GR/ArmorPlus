package sokratis12gr.armorplus.armors.reinforced.rdarmor;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.registry.ModItems;

/**
 * sokratis12gr.armorplus.armors.dev
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 */
public class RDHelmet extends ItemArmor {

    public static int armorPreffix = 0;

    public RDHelmet() {
        super(ModItems.RD_ARMOR_MATERIAL, armorPreffix, EntityEquipmentSlot.HEAD);
        setMaxStackSize(1);
        setRegistryName("reinforced_diamond_helmet");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("RDHelmet");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:RDHelmet", "inventory"));
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.CHAINMAIL;
    }
}