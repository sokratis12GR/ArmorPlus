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

import static java.util.stream.IntStream.range;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.items.base.ItemSpecialBattleAxe.*;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.armorplus.util.PotionUtils.PotionType.BAD;
import static net.thedragonteam.armorplus.util.PotionUtils.*;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum BattleAxes implements IEffectHolder, IRemovable, IRepairable {
    COAL(battleAxeCoalMaterial, "coal", getItemStack(COAL_BLOCK), getValueByName(coal.weapons.itemNameColor), 8.0F,
        coal.weapons.enableEffects, coal.weapons.addPotionEffects, coal.weapons.effectLevels,
        coal.weapons.shouldApplyFire, coal.weapons.onFireSeconds, global_registry.enableCoalWeapons[1]
    ),
    LAPIS(battleAxeLapisMaterial, "lapis", getItemStack(LAPIS_BLOCK), getValueByName(lapis.weapons.itemNameColor), 9.0F,
        lapis.weapons.enableEffects, lapis.weapons.addPotionEffects, lapis.weapons.effectLevels,
        lapis.weapons.shouldApplyFire, lapis.weapons.onFireSeconds, global_registry.enableLapisWeapons[1]
    ),
    REDSTONE(battleAxeRedstoneMaterial, "redstone", getItemStack(REDSTONE_BLOCK), getValueByName(redstone.weapons.itemNameColor), 9.0F,
        redstone.weapons.enableEffects, redstone.weapons.addPotionEffects, redstone.weapons.effectLevels,
        redstone.weapons.shouldApplyFire, redstone.weapons.onFireSeconds, global_registry.enableRedstoneWeapons[1]
    ),
    EMERALD(battleAxeEmeraldMaterial, "emerald", getItemStack(EMERALD_BLOCK), getValueByName(emerald.weapons.itemNameColor), 10.0F,
        emerald.weapons.enableEffects, emerald.weapons.addPotionEffects, emerald.weapons.effectLevels,
        emerald.weapons.shouldApplyFire, emerald.weapons.onFireSeconds, global_registry.enableEmeraldWeapons[1]
    ),
    OBSIDIAN(battleAxeObsidianMaterial, "obsidian", getItemStack(ModBlocks.compressedObsidian), getValueByName(obsidian.weapons.itemNameColor), 10.5F,
        obsidian.weapons.enableEffects, obsidian.weapons.addPotionEffects, obsidian.weapons.effectLevels,
        obsidian.weapons.shouldApplyFire, obsidian.weapons.onFireSeconds, global_registry.enableObsidianWeapons[1]
    ),
    LAVA(battleAxeLavaMaterial, "infused_lava", getItemStack(lavaCrystal, 1), getValueByName(lava.weapons.itemNameColor), 11.5F,
        lava.weapons.enableEffects, lava.weapons.addPotionEffects, lava.weapons.effectLevels,
        lava.weapons.shouldApplyFire, lava.weapons.onFireSeconds, global_registry.enableLavaWeapons[1]
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
    GUARDIAN(battleAxeGuardianMaterial, "guardian", getItemStack(materials, 1), getValueByName(guardian.weapons.itemNameColor), 14.0F,
        guardian.weapons.enableEffects, guardian.weapons.addPotionEffects, guardian.weapons.effectLevels,
        guardian.weapons.shouldApplyFire, guardian.weapons.onFireSeconds, global_registry.enableGuardianWeapons[1]
    ),
    SUPER_STAR(battleAxeSuperStarMaterial, "super_star", getItemStack(materials, 2), getValueByName(super_star.weapons.itemNameColor), 15.0F,
        super_star.weapons.enableEffects, super_star.weapons.addPotionEffects, super_star.weapons.effectLevels,
        super_star.weapons.shouldApplyFire, super_star.weapons.onFireSeconds, global_registry.enableSuperStarWeapons[1]
    ),
    ENDER_DRAGON(battleAxeEnderDragonMaterial, "ender_dragon", getItemStack(materials, 3), getValueByName(ender_dragon.weapons.itemNameColor), 16.0F,
        ender_dragon.weapons.enableEffects, ender_dragon.weapons.addPotionEffects, ender_dragon.weapons.effectLevels,
        ender_dragon.weapons.shouldApplyFire, ender_dragon.weapons.onFireSeconds, global_registry.enableEnderDragonWeapons[1]
    ),
    // WOOD(, "wooden"),
    // STONE(, "stone"),
    // IRON(, "iron"),
    // GOLD(, "golden"),
    // DIAMOND(, "diamond")
    ;

    private final String name;
    private final Item.ToolMaterial material;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final boolean isEnabled;
    private final List<String> effect;
    private final float efficiency;
    private final boolean enabledEffects;
    private final String[] addNegativePotionEffect;
    private final int[] addNegativePotionEffectAmplifier;
    private final boolean shouldApplyFire;
    private final int fireSecond;

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, ItemStack repairStackIn, TextFormatting textFormattingIn, float efficiencyIn,
               boolean enableEffect, String[] addNegativeEffect, int[] addNegativeEffectAmplifier,
               boolean shouldApplyFire, int fireSecond, boolean isEnabled
    ) {
        this.material = materialIn;
        this.name = nameIn;
        this.repairStack = repairStackIn;
        this.textFormatting = textFormattingIn;
        this.isEnabled = isEnabled;
        this.effect = setToolTip(addNegativeEffect, addNegativeEffectAmplifier);
        this.efficiency = efficiencyIn;
        this.enabledEffects = enableEffect;
        this.addNegativePotionEffect = addNegativeEffect;
        this.addNegativePotionEffectAmplifier = addNegativeEffectAmplifier;
        this.shouldApplyFire = shouldApplyFire;
        this.fireSecond = fireSecond;
    }

    @Override
    public List<String> getApplyEffectNames() {
        return Arrays.asList(this.addNegativePotionEffect);
    }

    @Override
    public List<Integer> getApplyAmplifierLevels() {
        return Arrays.stream(addNegativePotionEffectAmplifier).boxed().collect(Collectors.toList());
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
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

    public float getEfficiency() {
        return efficiency;
    }

    public boolean areEffectsEnabled() {
        return this.enabledEffects;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (shouldApplyFire) {
            target.setFire(fireSecond);
        }
        if (this.areEffectsEnabled()) {
            for (int i = 0; i < addNegativePotionEffect.length; i++) {
                addPotion(target, getPotion(this.getApplyEffectNames().get(i)), this.getApplyAmplifierLevels().get(i), BAD);
            }
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(List<String> tooltip) {
        ToolTipUtils.addWeaponToolTip(tooltip, effect, getTextFormatting());
    }
}
