package sokratis12gr.armorplus.armors.origin.lava;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
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
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 */
public class LavaHelmet extends ItemArmor {

    public static int armorPreffix = 0;

    public LavaHelmet() {
        super(ModItems.LAVA_ARMOR_MATERIAL, armorPreffix, EntityEquipmentSlot.HEAD);
        setMaxStackSize(1);
        setRegistryName("lava_helmet");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("LavaHelmet");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:LavaHelmet", "inventory"));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
        int lavaArmorEffectlevel = ARPConfig.lavaArmorEffectlevel + 1;
        if (ARPConfig.enableLavaHEffects) {
            infoList.add("\2479Ability: " + "\247rResistance " + lavaArmorEffectlevel + " And Fire Resistance");
            infoList.add("\2473Use: " + "\247rEquip A Piece");
        }
        if (ARPConfig.enableFullLavaArmorEffect) {
            infoList.add("\2479Ability: " + "\247rResistance " + lavaArmorEffectlevel + " And Fire Resistance");
            infoList.add("\2473Use: " + "\247rEquip The Full Set");
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {

        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

        if (ARPConfig.enableLavaHEffects && entity instanceof EntityLivingBase && !ARPConfig.enableFullLavaArmorEffect) {
            entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.lavaArmorEffectlevel, true, true));
            entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
        }
        if (head != null && head.getItem() == ModItems.LAVA_HELMET && chest != null && chest.getItem() == ModItems.LAVA_CHESTPLATE && legs != null && legs.getItem() == ModItems.LAVA_LEGGINGS && feet != null && feet.getItem() == ModItems.LAVA_BOOTS) {
            entity.extinguish();
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.GOLD + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.LAVA_CRYSTAL;
    }
}