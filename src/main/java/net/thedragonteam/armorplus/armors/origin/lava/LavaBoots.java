/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.origin.lava;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModItems;

import java.util.List;

import static net.thedragonteam.core.util.TextHelper.localize;

/**
 * net.thedragonteam.armorplus.armors.origin.lava
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 * - TheDragonTeam
 */
public class LavaBoots extends ItemArmor {

    public static int armorPreffix = 0;

    public LavaBoots() {
        super(ModItems.LAVA_ARMOR_MATERIAL, armorPreffix, EntityEquipmentSlot.FEET);
        setMaxStackSize(1);
        setRegistryName("lava_boots");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("LavaBoots");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:LavaBoots", "inventory"));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
        int lavaArmorEffectlevel = ARPConfig.lavaArmorEffectlevel + 1;
        if (ARPConfig.enableLavaBEffects) {
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

        if (ARPConfig.enableLavaBEffects && entity instanceof EntityLivingBase && !ARPConfig.enableFullLavaArmorEffect) {
            entity.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 120, ARPConfig.lavaArmorEffectlevel, true, true));
            entity.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 120, 0, true, true));
        }
        if (head != null && head.getItem() == ModItems.LAVA_HELMET && chest != null && chest.getItem() == ModItems.LAVA_CHESTPLATE && legs != null && legs.getItem() == ModItems.LAVA_LEGGINGS && feet != null && feet.getItem() == ModItems.LAVA_BOOTS) {
            entity.extinguish();
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.GOLD + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.LAVA_CRYSTAL;
    }
}