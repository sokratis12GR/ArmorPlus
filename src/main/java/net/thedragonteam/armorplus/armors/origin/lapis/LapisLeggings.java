/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.origin.lapis;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
 * sokratis12gr.armorplus.armors.dev
 * ArmorPlus created by sokratis12GR on 7/25/2016 10:08 AM.
 */
public class LapisLeggings extends ItemArmor {

    public static int armorPreffix = 0;

    public LapisLeggings() {
        super(ModItems.LAPIS_ARMOR_MATERIAL, armorPreffix, EntityEquipmentSlot.LEGS);
        setMaxStackSize(1);
        setRegistryName("lapis_leggings");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("LapisLeggings");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.TAB_ARMORPLUS);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("armorplus:LapisLeggings", "inventory"));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
        if (ARPConfig.enableLapisHBreathing) {
            infoList.add("\2479Ability: " + "\247rWater Breathing ");
            infoList.add("\2473Use: " + "\247rEquip A Piece");
        }
        if (ARPConfig.enableFullLapisArmorEffect) {
            infoList.add("\2479Ability: " + "\247rWater Breathing ");
            infoList.add("\2473Use: " + "\247rEquip The Full Set");
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        if (ARPConfig.enableLapisLBreathing && entity instanceof EntityLivingBase && !ARPConfig.enableFullLapisArmorEffect) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 120, 0, true, true));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.DARK_BLUE + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == getItemFromBlock(Blocks.LAPIS_BLOCK);
    }
}