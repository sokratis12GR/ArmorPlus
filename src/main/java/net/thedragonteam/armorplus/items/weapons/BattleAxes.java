/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.weapons;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.api.properties.IEffectHolder;
import net.thedragonteam.armorplus.api.properties.IRemovable;
import net.thedragonteam.armorplus.api.properties.IRepairable;
import net.thedragonteam.armorplus.items.weapons.effects.Ignite;
import net.thedragonteam.armorplus.items.weapons.effects.Negative;
import net.thedragonteam.armorplus.registry.ModBlocks;
import net.thedragonteam.armorplus.util.ToolTipUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        new Negative(coal), new Ignite(coal), global_registry.enableCoalWeapons[1]
    ),
    LAPIS(battleAxeLapisMaterial, "lapis", getItemStack(LAPIS_BLOCK), getValueByName(lapis.weapons.itemNameColor), 9.0F,
        new Negative(lapis), new Ignite(lapis), global_registry.enableLapisWeapons[1]
    ),
    REDSTONE(battleAxeRedstoneMaterial, "redstone", getItemStack(REDSTONE_BLOCK), getValueByName(redstone.weapons.itemNameColor), 9.0F,
        new Negative(redstone), new Ignite(redstone), global_registry.enableRedstoneWeapons[1]
    ),
    EMERALD(battleAxeEmeraldMaterial, "emerald", getItemStack(EMERALD_BLOCK), getValueByName(emerald.weapons.itemNameColor), 10.0F,
        new Negative(emerald), new Ignite(emerald), global_registry.enableEmeraldWeapons[1]
    ),
    OBSIDIAN(battleAxeObsidianMaterial, "obsidian", getItemStack(ModBlocks.compressedObsidian), getValueByName(obsidian.weapons.itemNameColor), 10.5F,
        new Negative(obsidian), new Ignite(obsidian), global_registry.enableObsidianWeapons[1]
    ),
    LAVA(battleAxeLavaMaterial, "infused_lava", getItemStack(lavaCrystal, 1), getValueByName(lava.weapons.itemNameColor), 11.5F,
        new Negative(lava), new Ignite(lava), global_registry.enableLavaWeapons[1]
    ),
    GUARDIAN(battleAxeGuardianMaterial, "guardian", getItemStack(materials, 1), getValueByName(guardian.weapons.itemNameColor), 14.0F,
        new Negative(guardian), new Ignite(guardian), global_registry.enableGuardianWeapons[1]
    ),
    SUPER_STAR(battleAxeSuperStarMaterial, "super_star", getItemStack(materials, 2), getValueByName(super_star.weapons.itemNameColor), 15.0F,
        new Negative(super_star), new Ignite(super_star), global_registry.enableSuperStarWeapons[1]
    ),
    ENDER_DRAGON(battleAxeEnderDragonMaterial, "ender_dragon", getItemStack(materials, 3), getValueByName(ender_dragon.weapons.itemNameColor), 16.0F,
        new Negative(ender_dragon), new Ignite(ender_dragon), global_registry.enableEnderDragonWeapons[1]
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
    private final Negative negative;
    private final Ignite ignite;

    BattleAxes(Item.ToolMaterial materialIn, String nameIn, ItemStack repairStackIn, TextFormatting textFormattingIn, float efficiencyIn, Negative negativeEffect, Ignite igniteEffect,
               boolean isEnabled
    ) {
        this.material = materialIn;
        this.name = nameIn;
        this.repairStack = repairStackIn;
        this.textFormatting = textFormattingIn;
        this.isEnabled = isEnabled;
        this.efficiency = efficiencyIn;
        this.negative = negativeEffect;
        this.ignite = igniteEffect;
        this.effect = setToolTip(negative.getNegativeEffects(), negative.getNegativeEffectsAmplifier());
    }

    @Override
    public List<String> getApplyEffectNames() {
        return Arrays.asList(this.negative.getNegativeEffects());
    }

    @Override
    public List<Integer> getApplyAmplifierLevels() {
        return Arrays.stream(this.negative.getNegativeEffectsAmplifier()).boxed().collect(Collectors.toList());
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
        return this.negative.isEnabled();
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (this.ignite.isEnabled()) {
            target.setFire(ignite.getFireSeconds());
        }
        if (this.areEffectsEnabled()) {
            IntStream.range(0, this.negative.getNegativeEffects().length).forEach(
                potionID -> addPotion(target, getPotion(this.getApplyEffectNames().get(potionID)), this.getApplyAmplifierLevels().get(potionID), BAD)
            );
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(List<String> tooltip) {
        ToolTipUtils.addSpecialInformation(tooltip, this.negative, this.ignite, getTextFormatting());
    }
}
