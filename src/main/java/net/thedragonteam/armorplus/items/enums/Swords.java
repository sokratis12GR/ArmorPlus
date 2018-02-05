/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.enums;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.api.properties.IEffectHolder;
import net.thedragonteam.armorplus.api.properties.IRemovable;
import net.thedragonteam.armorplus.api.properties.IRepairable;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.util.LavaWeaponsUtils;
import net.thedragonteam.armorplus.util.ToolTipUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.items.base.ItemSpecialSword.*;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum Swords implements IEffectHolder, IRemovable, IRepairable {
    COAL(swordCoalMaterial, "coal", getItemStack(COAL_BLOCK), getValueByName(coal.weapons.itemNameColor),
        coal.weapons.enableEffects, coal.weapons.addPotionEffects, coal.weapons.effectLevels, global_registry.enableCoalWeapons[0]
    ),
    LAPIS(swordLapisMaterial, "lapis", getItemStack(LAPIS_BLOCK), getValueByName(lapis.weapons.itemNameColor),
        lapis.weapons.enableEffects, lapis.weapons.addPotionEffects, lapis.weapons.effectLevels, global_registry.enableLapisWeapons[0]
    ),
    REDSTONE(swordRedstoneMaterial, "redstone", getItemStack(REDSTONE_BLOCK), getValueByName(redstone.weapons.itemNameColor),
        redstone.weapons.enableEffects, redstone.weapons.addPotionEffects, redstone.weapons.effectLevels, global_registry.enableRedstoneWeapons[0]
    ),
    EMERALD(swordEmeraldMaterial, "emerald", getItemStack(EMERALD_BLOCK), getValueByName(emerald.weapons.itemNameColor),
        emerald.weapons.enableEffects, emerald.weapons.addPotionEffects, emerald.weapons.effectLevels, global_registry.enableEmeraldWeapons[0]
    ),
    OBSIDIAN(swordObsidianMaterial, "obsidian", getItemStack(ModBlocks.compressedObsidian), getValueByName(obsidian.weapons.itemNameColor),
        obsidian.weapons.enableEffects, obsidian.weapons.addPotionEffects, obsidian.weapons.effectLevels, global_registry.enableObsidianWeapons[0]
    ),
    LAVA(swordLavaMaterial, "infused_lava", getItemStack(lavaCrystal, 1), getValueByName(lava.weapons.itemNameColor),
        lava.weapons.enableEffects, lava.weapons.addPotionEffects, lava.weapons.effectLevels, global_registry.enableLavaWeapons[0]
    ) {
        @Override
        @SideOnly(Side.CLIENT)
        public void addInformation(List<String> tooltip) {
            LavaWeaponsUtils.addLavaInformation(tooltip, getApplyEffectNames(), getApplyAmplifierLevels(), getTextFormatting());
        }

        @Override
        public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
            stack.damageItem(1, attacker);
            if (lava.weapons.shouldApplyFire) {
                target.setFire(lava.weapons.onFireSeconds);
            }
            return true;
        }
    },
    GUARDIAN(swordGuardianMaterial, "guardian", getItemStack(materials, 1), getValueByName(guardian.weapons.itemNameColor),
        guardian.weapons.enableEffects, guardian.weapons.addPotionEffects, guardian.weapons.effectLevels, global_registry.enableGuardianWeapons[0]
    ),
    SUPER_STAR(swordSuperStarMaterial, "super_star", getItemStack(materials, 2), getValueByName(super_star.weapons.itemNameColor),
        super_star.weapons.enableEffects, super_star.weapons.addPotionEffects, super_star.weapons.effectLevels, global_registry.enableSuperStarWeapons[0]
    ),
    ENDER_DRAGON(swordEnderDragonMaterial, "ender_dragon", getItemStack(materials, 3), getValueByName(ender_dragon.weapons.itemNameColor),
        ender_dragon.weapons.enableEffects, ender_dragon.weapons.addPotionEffects, ender_dragon.weapons.effectLevels, global_registry.enableEnderDragonWeapons[0]
    );

    private final String name;
    private final Item.ToolMaterial material;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final boolean isEnabled;
    private final List<String> effect;
    private final boolean enabledEffects;
    private final String[] addNegativePotionEffect;
    private final int[] addNegativePotionEffectAmplifier;

    Swords(Item.ToolMaterial materialIn, String nameIn, ItemStack repairStackIn, TextFormatting textFormattingIn,
           boolean enableEffect, String[] addNegativeEffect, int[] addNegativeEffectAmplifier, boolean isEnabled) {
        this.material = materialIn;
        this.name = nameIn;
        this.repairStack = repairStackIn == null ? EMPTY : repairStackIn;
        this.textFormatting = textFormattingIn;
        this.isEnabled = isEnabled;
        this.effect = setToolTip(addNegativeEffect, addNegativeEffectAmplifier);
        this.enabledEffects = enableEffect;
        this.addNegativePotionEffect = addNegativeEffect;
        this.addNegativePotionEffectAmplifier = addNegativeEffectAmplifier;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public List<String> getApplyEffectNames() {
        return Arrays.asList(this.addNegativePotionEffect);
    }

    @Override
    public List<Integer> getApplyAmplifierLevels() {
        return Arrays.stream(addNegativePotionEffectAmplifier).boxed().collect(Collectors.toList());
    }

    public static List<String> setToolTip(String[] effectName, int[] effectLevel) {
        return range(0, effectLevel.length).mapToObj(i -> localizePotion(effectName[i]) + " " + (effectLevel[i] + 1)).collect(Collectors.toList());
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public Item.ToolMaterial getToolMaterial() {
        return material;
    }

    public List<String> getEffects() {
        return effect;
    }

    public ItemStack getRepairStack() {
        return repairStack;
    }

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public boolean areEffectsEnabled() {
        return enabledEffects;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (this.areEffectsEnabled()) {
            IntStream.range(0, addNegativePotionEffect.length).forEach(i ->
                addPotion(target, getPotion(this.getApplyEffectNames().get(i)), this.getApplyAmplifierLevels().get(i), BAD)
            );
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(List<String> tooltip) {
        ToolTipUtils.addWeaponToolTip(tooltip, effect, getTextFormatting());
    }
}
