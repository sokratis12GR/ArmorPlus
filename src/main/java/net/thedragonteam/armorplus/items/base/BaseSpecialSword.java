/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.APConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.Swords;

import java.util.List;

import static net.thedragonteam.armorplus.APConfig.*;
import static net.thedragonteam.armorplus.util.ArmorPlusItemUtils.isItemRepairable;
import static net.thedragonteam.armorplus.util.PotionUtils.EffectType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.addEffect;
import static net.thedragonteam.armorplus.util.PotionUtils.getPotion;
import static net.thedragonteam.armorplus.util.Utils.setName;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseSpecialSword extends ItemSword {

    public static Item.ToolMaterial swordCoalMaterial = EnumHelper.addToolMaterial("swordCoalMaterial", 1, APConfig.coalSwordDurability, 1.0F, (float) APConfig.coalSwordDamage, 15);
    public static Item.ToolMaterial swordLapisMaterial = EnumHelper.addToolMaterial("swordLapisMaterial", 1, APConfig.lapisSwordDurability, 1.0F, (float) APConfig.lapisSwordDamage, 30);
    public static Item.ToolMaterial swordRedstoneMaterial = EnumHelper.addToolMaterial("swordRedstoneMaterial", 1, APConfig.redstoneSwordDurability, 1.0F, (float) APConfig.redstoneSwordDamage, 20);
    public static Item.ToolMaterial swordEmeraldMaterial = EnumHelper.addToolMaterial("swordEmeraldMaterial", 1, APConfig.emeraldSwordDurability, 1.0F, (float) APConfig.emeraldSwordDamage, 20);
    public static Item.ToolMaterial swordObsidianMaterial = EnumHelper.addToolMaterial("swordObsidianMaterial", 1, APConfig.obsidianSwordDurability, 1.0F, (float) APConfig.obsidianSwordDamage, 20);
    public static Item.ToolMaterial swordLavaMaterial = EnumHelper.addToolMaterial("swordLavaMaterial", 1, APConfig.lavaSwordDurability, 1.0F, (float) APConfig.lavaSwordDamage, 20);
    public static Item.ToolMaterial swordGuardianMaterial = EnumHelper.addToolMaterial("swordGuardianMaterial", 1, APConfig.guardianSwordDurability, 1.0F, (float) APConfig.guardianSwordDamage, 30);
    public static Item.ToolMaterial swordSuperStarMaterial = EnumHelper.addToolMaterial("swordSuperStarMaterial", 1, APConfig.superStarSwordDurability, 1.0F, (float) APConfig.superStarSwordDamage, 20);
    public static Item.ToolMaterial swordEnderDragonMaterial = EnumHelper.addToolMaterial("swordEnderDragonMaterial", 1, APConfig.enderDragonSwordDurability, 1.0F, (float) APConfig.enderDragonSwordDamage, 20);
    public Item itemEasy;
    public Item itemExpert;
    public TextFormatting formatting;
    public String effect;
    private Swords swords;

    public BaseSpecialSword(Swords swords) {
        super(swords.getToolMaterial());
        this.swords = swords;
        this.itemEasy = swords.getRepairEasy();
        this.itemExpert = swords.getRepairExpert();
        this.formatting = swords.getTextFormatting();
        this.effect = swords.getEffect();
        setRegistryName(swords.getName());
        setUnlocalizedName(setName(swords.getName()));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        switch (swords) {
            case COAL:
                if (enableCoalWeaponsEffects)
                    addEffect(target, getPotion(coalWeaponsAddPotionEffect), 180, coalWeaponsEffectLevel, BAD);
                break;
            case LAPIS:
                if (enableLapisWeaponsEffects)
                    addEffect(target, getPotion(lapisWeaponsAddPotionEffect), lapisWeaponsEffectLevel, BAD);
                break;
            case REDSTONE:
                if (enableRedstoneWeaponsEffects)
                    addEffect(target, getPotion(redstoneWeaponsAddPotionEffect), 180, redstoneWeaponsEffectLevel, BAD);
                break;
            case EMERALD:
                if (enableEmeraldWeaponsEffects)
                    addEffect(target, getPotion(emeraldWeaponsAddPotionEffect), emeraldWeaponsEffectLevel, BAD);
                break;
            case OBSIDIAN:
                if (enableObsidianWeaponsEffects)
                    addEffect(target, getPotion(obsidianWeaponsAddPotionEffect), obsidianWeaponsEffectLevel, BAD);
                break;
            case LAVA:
                target.setFire(8);
                break;
            case GUARDIAN:
                if (enableGuardianWeaponsEffects)
                    addEffect(target, getPotion(guardianWeaponsAddPotionEffect), guardianWeaponsEffectLevel, BAD);
                break;
            case SUPER_STAR:
                if (enableSuperStarWeaponsEffects)
                    addEffect(target, getPotion(superStarWeaponsAddPotionEffect), superStarWeaponsEffectLevel, BAD);
                break;
            case ENDER_DRAGON:
                if (enableEnderDragonWeaponsEffects)
                    addEffect(target, getPotion(enderDragonWeaponsAddPotionEffect), 60, enderDragonWeaponsEffectLevel, BAD);
                break;
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247r" + effect);
            tooltip.add("\2473Use: " + "\247rHit a Target");
        } else
            tooltip.add(I18n.format("tooltip.tesla.showinfo", formatting, keyBindSneak.getDisplayName(), TextFormatting.GRAY));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (formatting + localize(this.getUnlocalizedNameInefficiently(stack) + ".name")).trim();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return isItemRepairable(toRepair, itemEasy, itemExpert);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
