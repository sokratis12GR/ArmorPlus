/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.armors.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.registry.ModItems;
import net.thedragonteam.armorplus.util.Utils;

import java.util.List;

import static net.thedragonteam.armorplus.ARPConfig.enableTheUltimateArmorDeBuffs;
import static net.thedragonteam.armorplus.ArmorPlus.getArmorPlusLocation;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseUltimateArmor extends ItemArmor {

    public static ItemArmor.ArmorMaterial theUltimateArmor = EnumHelper.addArmorMaterial("theUltimateArmor", getArmorPlusLocation() + "the_ultimate_armor", 160,
            ARPConfig.theUltimateArmorProtectionPoints, 1, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, (float) ARPConfig.theUltimateArmorToughnessPoints);

    public BaseUltimateArmor(EntityEquipmentSlot slot) {
        super(theUltimateArmor, 0, slot);
        ItemArmor.MAX_DAMAGE_ARRAY = new int[]{93, 95, 96, 91};
        setMaxStackSize(1);
        switch (slot) {
            case FEET:
                setRegistryName("the_ultimate_boots_full");
                setUnlocalizedName(ArmorPlus.MODID + "." + "the_ultimate_boots_full");
                break;
            case LEGS:
                setRegistryName("the_ultimate_leggings_full");
                setUnlocalizedName(ArmorPlus.MODID + "." + "the_ultimate_leggings_full");
                break;
            case CHEST:
                setRegistryName("the_ultimate_chestplate_full");
                setUnlocalizedName(ArmorPlus.MODID + "." + "the_ultimate_chestplate_full");
                break;
            case HEAD:
                setRegistryName("the_ultimate_helmet_full");
                setUnlocalizedName(ArmorPlus.MODID + "." + "the_ultimate_helmet_full");
                break;
        }
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.tabArmorplus);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;

        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rThe Most OverPowered Armor");
            tooltip.add("\2473Use: " + "\247rEquip The Full Set");
        } else
            tooltip.add(I18n.format("tooltip.shift.showinfo", TextFormatting.GREEN, keyBindSneak.getDisplayName(), TextFormatting.GRAY, TextFormatting.GREEN));
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        Utils.setUnbreakable(stack);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack) {
        ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if (ARPConfig.enableFlightAbility)
            if (head != null && head.getItem() == ModItems.theUltimateHelmet && chest != null && chest.getItem() == ModItems.theUltimateChestplate && legs != null && legs.getItem() == ModItems.theUltimateLeggings && feet != null && feet.getItem() == ModItems.theUltimateBoots || entity.capabilities.isCreativeMode || entity.isSpectator())
                entity.capabilities.allowFlying = true;
            else {
                entity.capabilities.isFlying = false;
                entity.capabilities.allowFlying = false;
            }
        if (ARPConfig.enableTheUltimateArmorInvincibility)
            if (head != null && head.getItem() == ModItems.theUltimateHelmet && chest != null && chest.getItem() == ModItems.theUltimateChestplate && legs != null && legs.getItem() == ModItems.theUltimateLeggings && feet != null && feet.getItem() == ModItems.theUltimateBoots || entity.capabilities.isCreativeMode || entity.isSpectator())
                entity.capabilities.disableDamage = true;
            else entity.capabilities.disableDamage = false;
        if (head != null && head.getItem() == ModItems.theUltimateHelmet && chest != null && chest.getItem() == ModItems.theUltimateChestplate && legs != null && legs.getItem() == ModItems.theUltimateLeggings && feet != null && feet.getItem() == ModItems.theUltimateBoots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
        } else if (enableTheUltimateArmorDeBuffs) {
            entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 2, true, true));
            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 2, true, true));
            entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60, 0, true, true));

            entity.motionX = 0;
            if (((EntityLivingBase) entity).onGround)
                entity.motionY = 0;
            entity.motionZ = 0;
            ((EntityPlayer) entity).velocityChanged = true; // assumes that entity instanceof EntityPlayer
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (TextFormatting.GREEN + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        switch (ARPConfig.recipes) {
            case 0:
                return repair.getItem() == ModItems.theUltimateMaterial;
            case 1:
                return repair.getItem() == ModItems.theUltimateMaterial;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
