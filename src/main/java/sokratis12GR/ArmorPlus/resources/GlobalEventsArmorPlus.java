package sokratis12GR.ArmorPlus.resources;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.armors.*;
import sokratis12GR.ArmorPlus.armors.special.EnderDragonArmor;
import sokratis12GR.ArmorPlus.armors.special.GuardianArmor;
import sokratis12GR.ArmorPlus.armors.special.SuperStarArmor;
import sokratis12GR.ArmorPlus.armors.special.TheUltimateArmor;
import sokratis12GR.ArmorPlus.util.ARPAchievements;
import sokratis12GR.ArmorPlus.util.TextHelper;

public class GlobalEventsArmorPlus {
    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {

        Item i = event.crafting.getItem();

        ItemStack itemStack = event.crafting;


        //Guardian Armor Thorns
        if (i == GuardianArmor.helmet || i == GuardianArmor.chestplate || i == GuardianArmor.legs || i == GuardianArmor.boots || i == TheUltimateArmor.helmet || i == TheUltimateArmor.chestplate || i == TheUltimateArmor.legs || i == TheUltimateArmor.boots)
            itemStack.addEnchantment(Enchantment.getEnchantmentByID(7), 3);
        //Guardian Armor Unbreaking 3
        if (i == GuardianArmor.helmet || i == GuardianArmor.chestplate || i == GuardianArmor.legs || i == GuardianArmor.boots || i == TheUltimateArmor.helmet || i == TheUltimateArmor.chestplate || i == TheUltimateArmor.legs || i == TheUltimateArmor.boots)
            itemStack.addEnchantment(Enchantment.getEnchantmentByID(34), 3);
        //Full of Thorns! - Achievement Trigger
        if (i == GuardianArmor.helmet || i == GuardianArmor.chestplate || i == GuardianArmor.legs || i == GuardianArmor.boots)
            event.player.addStat(ARPAchievements.craftGuardianArmor, 1);
        // Guardian Boots Enchantments
        if (i == GuardianArmor.boots || i == TheUltimateArmor.boots)
            itemStack.addEnchantment(Enchantment.getEnchantmentByID(8), 3);
        else {
            return;
        }
        //Vision Like A Bat! - Achievement Trigger
        if (i == CoalArmor.helmet || i == CoalArmor.chestplate || i == CoalArmor.legs || i == CoalArmor.boots)
            event.player.addStat(ARPAchievements.craftCoalArmor, 1);
        //Never Drown Again - Achievement Trigger
        if (i == LapisArmor.helmet || i == LapisArmor.chestplate || i == LapisArmor.legs || i == LapisArmor.boots)
            event.player.addStat(ARPAchievements.craftLapisArmor, 1);
        //Speeedy! - Achievement Trigger
        if (i == RedstoneArmor.helmet || i == RedstoneArmor.chestplate || i == RedstoneArmor.legs || i == RedstoneArmor.boots)
            event.player.addStat(ARPAchievements.craftRedstoneArmor, 1);
        //Swing Swing Faster! - Achievement Trigger
        if (i == EmeraldArmor.helmet || i == EmeraldArmor.chestplate || i == EmeraldArmor.legs || i == EmeraldArmor.boots)
            event.player.addStat(ARPAchievements.craftEmeraldArmor, 1);
        //Undestructable! - Achievement Trigger
        if (i == ObsidianArmor.helmet || i == ObsidianArmor.chestplate || i == ObsidianArmor.legs || i == ObsidianArmor.boots)
            event.player.addStat(ARPAchievements.craftObsidianArmor, 1);
        //The Overpowered! - Achievement Trigger
        if (i == LavaArmor.helmet || i == LavaArmor.chestplate || i == LavaArmor.legs || i == LavaArmor.boots)
            event.player.addStat(ARPAchievements.craftLavaArmor, 1);
        //Godlike! - Achievement Trigger
        if (i == SuperStarArmor.helmet || i == SuperStarArmor.chestplate || i == SuperStarArmor.legs || i == SuperStarArmor.boots)
            event.player.addStat(ARPAchievements.craftSuperStarArmor, 1);
        //The Power of the Ender Dragon! - Achievement Trigger
        if (i == EnderDragonArmor.helmet || i == EnderDragonArmor.chestplate || i == EnderDragonArmor.legs || i == SuperStarArmor.boots)
            event.player.addStat(ARPAchievements.craftEnderDragonArmor, 1);
        //The Ultimate Power! - Achievement Trigger
        if (i == TheUltimateArmor.helmet || i == TheUltimateArmor.chestplate || i == TheUltimateArmor.legs || i == TheUltimateArmor.boots)
            event.player.addStat(ARPAchievements.craftTheUltimateArmor, 1);
    }

    @SubscribeEvent
    public void onArmorTick(TickEvent.PlayerTickEvent event) {
        {

            EntityPlayer entity = event.player;

            ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            if (ConfigHandler.enableFlightAbility) {
                if (head != null && head.getItem() == EnderDragonArmor.helmet && chest != null && chest.getItem() == EnderDragonArmor.chestplate && legs != null && legs.getItem() == EnderDragonArmor.legs && feet != null && feet.getItem() == EnderDragonArmor.boots || head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots || entity.capabilities.isCreativeMode || entity.isSpectator()) {
                    entity.capabilities.allowFlying = true;
                } else {
                    entity.capabilities.allowFlying = false;
                    entity.capabilities.isFlying = false;
                }
            }
            if (head != null && head.getItem() == TheUltimateArmor.helmet && chest != null && chest.getItem() == TheUltimateArmor.chestplate && legs != null && legs.getItem() == TheUltimateArmor.legs && feet != null && feet.getItem() == TheUltimateArmor.boots)
            {
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.waterBreathing, 120, 0));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.regeneration, 120, 1));
                if (entity instanceof EntityLivingBase)
                    ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.nightVision, 240, 0));
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        ConfigHandler.syncConfig();
        ArmorPlus.logger.info(TextHelper.localize("info." + ArmorPlus.MODID + ".console.config.refresh"));
    }
}
