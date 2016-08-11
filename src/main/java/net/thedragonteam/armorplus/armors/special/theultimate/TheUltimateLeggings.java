package net.thedragonteam.armorplus.armors.special.theultimate;

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
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.armorplus.ARPConfig.enableTheUltimateArmorDeBuffs;

/**
 * sokratis12gr.armorplus.armors.dev
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 */
public class TheUltimateLeggings extends ItemArmor {

    public static int armorPreffix = 0;

    public TheUltimateLeggings() {
        super(ModItems.THE_ULTIMATE_ARMOR_MATERIAL, armorPreffix, EntityEquipmentSlot.LEGS);
        setMaxStackSize(1);
        setRegistryName("the_ultimate_leggings_full");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("TheUltimateLeggings");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:TheUltimateLeggings", "inventory"));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
        infoList.add("\2479Ability: " + "\247rThe Most OverPowered Armor");
        infoList.add("\2473Use: " + "\247rEquip The Full Set");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if (ARPConfig.enableFlightAbility) {

            if (head != null && head.getItem() == ModItems.THE_ULTIMATE_HELMET && chest != null && chest.getItem() == ModItems.THE_ULTIMATE_CHESTPLATE && legs != null && legs.getItem() == ModItems.THE_ULTIMATE_LEGGINGS && feet != null && feet.getItem() == ModItems.THE_ULTIMATE_BOOTS || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                entity.capabilities.allowFlying = true;
            } else {
                entity.capabilities.isFlying = false;
                entity.capabilities.allowFlying = false;
            }
        }
        if (ARPConfig.enableTheUltimateArmorInvincibility) {
            if (head != null && head.getItem() == ModItems.THE_ULTIMATE_HELMET && chest != null && chest.getItem() == ModItems.THE_ULTIMATE_CHESTPLATE && legs != null && legs.getItem() == ModItems.THE_ULTIMATE_LEGGINGS && feet != null && feet.getItem() == ModItems.THE_ULTIMATE_BOOTS || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                entity.capabilities.disableDamage = true;
            } else {
                entity.capabilities.disableDamage = false;
            }
        }
        if (head != null && head.getItem() == ModItems.THE_ULTIMATE_HELMET && chest != null && chest.getItem() == ModItems.THE_ULTIMATE_CHESTPLATE && legs != null && legs.getItem() == ModItems.THE_ULTIMATE_LEGGINGS && feet != null && feet.getItem() == ModItems.THE_ULTIMATE_BOOTS) {
        } else {
            if (entity instanceof EntityLivingBase && enableTheUltimateArmorDeBuffs) {
                entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 2, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 2, true, true));
                entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60, 0, true, true));
            }
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.GREEN + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.THE_ULTIMATE_MATERIAL;
    }
}