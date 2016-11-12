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
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ARPConfig;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.Swords;

import java.util.List;

import static net.thedragonteam.armorplus.util.Utils.setARPUnlocalizedName;
import static net.thedragonteam.thedragonlib.util.TextHelper.localize;

public class BaseSpecialSword extends ItemSword {

    public static Item.ToolMaterial swordCoalMaterial = EnumHelper.addToolMaterial("swordCoalMaterial", 1, ARPConfig.coalSwordDurability, 1.0F, (float) ARPConfig.coalSwordDamage, 15);
    public static Item.ToolMaterial swordLapisMaterial = EnumHelper.addToolMaterial("swordLapisMaterial", 1, ARPConfig.lapisSwordDurability, 1.0F, (float) ARPConfig.lapisSwordDamage, 30);
    public static Item.ToolMaterial swordRedstoneMaterial = EnumHelper.addToolMaterial("swordRedstoneMaterial", 1, ARPConfig.redstoneSwordDurability, 1.0F, (float) ARPConfig.redstoneSwordDamage, 20);
    public static Item.ToolMaterial swordEmeraldMaterial = EnumHelper.addToolMaterial("swordEmeraldMaterial", 1, ARPConfig.emeraldSwordDurability, 1.0F, (float) ARPConfig.emeraldSwordDamage, 20);
    public static Item.ToolMaterial swordObsidianMaterial = EnumHelper.addToolMaterial("swordObsidianMaterial", 1, ARPConfig.obsidianSwordDurability, 1.0F, (float) ARPConfig.obsidianSwordDamage, 20);
    public static Item.ToolMaterial swordLavaMaterial = EnumHelper.addToolMaterial("swordLavaMaterial", 1, ARPConfig.lavaSwordDurability, 1.0F, (float) ARPConfig.lavaSwordDamage, 20);
    public static Item.ToolMaterial swordGuardianMaterial = EnumHelper.addToolMaterial("swordGuardianMaterial", 1, ARPConfig.guardianSwordDurability, 1.0F, (float) ARPConfig.guardianSwordDamage, 30);
    public static Item.ToolMaterial swordSuperStarMaterial = EnumHelper.addToolMaterial("swordSuperStarMaterial", 1, ARPConfig.superStarSwordDurability, 1.0F, (float) ARPConfig.superStarSwordDamage, 20);
    public static Item.ToolMaterial swordEnderDragonMaterial = EnumHelper.addToolMaterial("swordEnderDragonMaterial", 1, ARPConfig.enderDragonSwordDurability, 1.0F, (float) ARPConfig.enderDragonSwordDamage, 20);
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
        setUnlocalizedName(setARPUnlocalizedName(swords.getName()));
        GameRegistry.register(this);
        this.setCreativeTab(ArmorPlus.tabArmorplusWeapons);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        switch (swords) {
            case COAL:
                target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 180, 0, false, true));
                break;
            case LAPIS:
                target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 120, 1, false, true));
                break;
            case REDSTONE:
                target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 180, 1, false, true));
                break;
            case EMERALD:
                target.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 120, 1, false, true));
                break;
            case OBSIDIAN:
                target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 120, 1, false, true));
                break;
            case LAVA:
                target.setFire(6);
                break;
            case GUARDIAN:
                target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 120, 1, false, true));
                break;
            case SUPER_STAR:
                target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 120, 1, false, true));
                break;
            case ENDER_DRAGON:
                target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 3, false, true));
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
        switch (ARPConfig.recipes) {
            case 0:
                return repair.getItem() == itemEasy;
            case 1:
                return repair.getItem() == itemExpert;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
