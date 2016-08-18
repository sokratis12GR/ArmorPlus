/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.items.swords;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;

import java.util.List;

import static net.thedragonteam.core.util.TextHelper.localize;

/**
 * Created by Socrates on 4/19/2016.
 */
public class RedstoneSword extends ItemSword {

    public RedstoneSword(ToolMaterial material) {
        super(material);
        setRegistryName("redstone_sword");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("RedstoneSword");     // Used for localization (en_US.lang)
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.TAB_ARMORPLUS_WEAPONS);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (target instanceof EntityLivingBase) {
            ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 180, 1, false, true));
        }
        return true;
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean par4) {
        infoList.add("\2479Ability: " + "\247rApplies Slowness 2");
        infoList.add("\2473Use: " + "\247rHit a Target");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.DARK_RED + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(ArmorPlus.MODID + ":" + "RedstoneSword", "inventory"));
    }

}
