package sokratis12gr.armorplus.armors.reinforced.rcarmor;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sokratis12gr.armorplus.ARPConfig;
import sokratis12gr.armorplus.ArmorPlus;
import sokratis12gr.armorplus.registry.ModItems;

import java.util.List;

/**
 * sokratis12gr.armorplus.armors.dev
 * ArmorPlus created by sokratis12GR on 7/28/2016 9:19 AM.
 */
public class RCBoots extends ItemArmor {

    public static int armorPreffix = 0;

    public RCBoots() {
        super(ModItems.RC_ARMOR_MATERIAL, armorPreffix, EntityEquipmentSlot.FEET);
        setMaxStackSize(1);
        setRegistryName("reinforced_chain_boots");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("RCBoots");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:RCBoots", "inventory"));
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.CHAINMAIL;
    }
}