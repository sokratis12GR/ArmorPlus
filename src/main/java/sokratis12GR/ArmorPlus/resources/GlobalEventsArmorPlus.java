package sokratis12GR.ArmorPlus.resources;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import sokratis12GR.ArmorPlus.armors.*;
import sokratis12GR.ArmorPlus.util.ARPAchievements;

public class GlobalEventsArmorPlus {
    @SubscribeEvent
    public void onPlayerCraftedItem(PlayerEvent.ItemCraftedEvent event) {
        Item i = event.crafting.getItem();
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
    }

    @SubscribeEvent
    public void onArmorTick(TickEvent.PlayerTickEvent event) {
        {

            EntityPlayer entity = event.player;

            ItemStack head = entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            ItemStack legs = entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            ItemStack feet = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

            if (ConfigHandler.enableEnderDragonFlight) {
                if (head != null && head.getItem() == EnderDragonArmor.helmet && chest != null && chest.getItem() == EnderDragonArmor.chestplate && legs != null && legs.getItem() == EnderDragonArmor.legs && feet != null && feet.getItem() == EnderDragonArmor.boots) {
                    entity.capabilities.allowFlying = true;
                } else {
                    entity.capabilities.allowFlying = false;
                    entity.capabilities.isFlying = false;
                }
            }
        }
    }

}
